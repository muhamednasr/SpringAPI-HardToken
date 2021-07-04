package com.eta.dsignature;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.cert.CertStore;
import java.security.cert.Certificate;
import java.security.cert.CollectionCertStoreParameters;
import java.security.cert.X509Certificate;
import java.util.Base64;
import java.util.Collections;
import java.util.Properties;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.DERObjectIdentifier;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSet;
import org.bouncycastle.asn1.cms.Attribute;
import org.bouncycastle.asn1.cms.AttributeTable;
import org.bouncycastle.asn1.ess.ESSCertIDv2;
import org.bouncycastle.asn1.ess.SigningCertificateV2;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cms.CMSAttributeTableGenerator;
import org.bouncycastle.cms.CMSProcessable;
import org.bouncycastle.cms.CMSProcessableByteArray;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.cms.CMSSignedDataGenerator;
import org.bouncycastle.cms.DefaultSignedAttributeTableGenerator;
import org.bouncycastle.cms.SignerInfoGeneratorBuilder;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.operator.jcajce.JcaDigestCalculatorProviderBuilder;

import com.eta.jsonhandle.CanonicalJson;
import com.fasterxml.jackson.databind.JsonNode;

import sun.security.pkcs11.SunPKCS11;


public class DigitalSignature {

	private final String configFile = "Data/config.cfg";
	private final String passFile = "Data/Pass.cfg";
	
	protected char[] keystorePassword;
	protected String providerName;
	protected KeyStore keystore;
	private Certificate certificateAuth;
	private Key privateKeyAuth;
	private String serialJSON;
	byte[] DataSignatureForInv;
	 
	public String getSignature(JsonNode document) throws Exception{
     
		 
		 CanonicalJson CanJSON = new CanonicalJson();
		 
		 try {
		  if (certificateAuth == null) { getCertificate(); }
		  
			serialJSON = CanJSON.canonicalizeJson(document);
			
			this.DataSignatureForInv = signDocument(serialJSON);
		 }
		 catch(Exception ex) {
			 
			 System.out.println(ex.getMessage());
			 
		 }
		return  Base64.getEncoder().encodeToString(DataSignatureForInv);
		
		
	}
	
	public byte[] signDocument(String JSON) throws Exception{
		
		MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
		MessageDigest sha256d = MessageDigest.getInstance("SHA-256");
		
//		CMSSignedData cmSignedData ;

		ASN1EncodableVector signedAttributes = new ASN1EncodableVector();
		byte[] documentSerHashed = sha256.digest( JSON.getBytes(StandardCharsets.UTF_8) );
		byte[] digestedCert = sha256d.digest(getX509CertificateAuth().getEncoded());

		signedAttributes.add(new Attribute(new ASN1ObjectIdentifier("1.2.840.113549.1.9.4"), new DERSet(new DEROctetString(documentSerHashed))));
		
		AlgorithmIdentifier alCertV2 = new AlgorithmIdentifier(new DERObjectIdentifier("1.2.840.113549.1.9.16.2.47"));
		
		ESSCertIDv2 essCert1 = new ESSCertIDv2(alCertV2,digestedCert);
		ESSCertIDv2[] essCert1Arr = { essCert1 };	
		
		SigningCertificateV2 signingCertificateV2 = new SigningCertificateV2(essCert1Arr);

		signedAttributes.add(new Attribute(new ASN1ObjectIdentifier("1.2.840.113549.1.9.16.2.47"), new  DERSet(signingCertificateV2) ));
		
		AttributeTable signedAttributesTable = new AttributeTable(signedAttributes);
		signedAttributesTable.toASN1EncodableVector();		
		
	    CMSAttributeTableGenerator signedAttributeGenerator = new DefaultSignedAttributeTableGenerator(signedAttributesTable);
		   
	    SignerInfoGeneratorBuilder signerInfoBuilder = new SignerInfoGeneratorBuilder(new JcaDigestCalculatorProviderBuilder().setProvider(providerName).build());
	    	    
	    signerInfoBuilder.setSignedAttributeGenerator(signedAttributeGenerator);
	    signerInfoBuilder.setUnsignedAttributeGenerator(null) ;
	    
	    CMSSignedDataGenerator signedDataGenerator = new CMSSignedDataGenerator();
 
	    JcaContentSignerBuilder contentSigner = new JcaContentSignerBuilder("SHA256withRSAEncryption");
	    contentSigner.setProvider(providerName);
	    
	    signedDataGenerator.addSignerInfoGenerator(signerInfoBuilder.build(contentSigner.build(this.getPrivateKeyAuth()), 
	    		new X509CertificateHolder(this.getX509CertificateAuth().getEncoded())));
	    
	    CertStore certStore = CertStore.getInstance("Collection",
				new CollectionCertStoreParameters(
									Collections.singletonList(this.getX509CertificateAuth())));	    
		
	    signedDataGenerator.addCertificatesAndCRLs(certStore);
	    CMSProcessable cmsProcessable = new CMSProcessableByteArray(PKCSObjectIdentifiers.digestedData ,documentSerHashed);
	    CMSSignedData signedData =signedDataGenerator.generate(cmsProcessable, false, providerName);	    
	    
		byte[] DataSignatureForInv = signedData.getEncoded();
		return DataSignatureForInv ;
		
	}
		
	public KeyStore getCertificate() throws Exception {
		
		String aliasAuth;
		//getting password from Config file
		this.keystorePassword = getPassword();
		
		Provider prov = new SunPKCS11(configFile);		
		Security.addProvider(prov) ;
		this.providerName     = prov.getName() ;
				
		this.keystore = KeyStore.getInstance("PKCS11");
		this.keystore.load(null, this.keystorePassword ); 

		aliasAuth = keystore.aliases().nextElement();
		
	    // get certificate and private key
		this.certificateAuth = (Certificate) keystore.getCertificate(aliasAuth);
		this.privateKeyAuth = keystore.getKey(aliasAuth,keystorePassword);

		return this.keystore;
	}

	public char[] getPassword() {

		String password;
		Properties prop = new Properties();
		InputStream is = null;

		try {
		    is = new FileInputStream(passFile);
		} catch (FileNotFoundException ex) {

		}
		try {
		    prop.load(is);
		} catch (IOException ex) {
		}
		
		password = prop.getProperty("Password");
		return password.toCharArray();
		
	}
	
	private PrivateKey getPrivateKeyAuth() throws Exception {
		if (privateKeyAuth instanceof PrivateKey) {
			return (PrivateKey) privateKeyAuth;
		}
		return null;
	}

	private X509Certificate getX509CertificateAuth() throws Exception {
		if (certificateAuth instanceof X509Certificate) {
			return (X509Certificate) certificateAuth;
		}
		return null;
	}

}
