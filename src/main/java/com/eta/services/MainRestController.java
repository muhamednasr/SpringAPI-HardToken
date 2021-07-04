package com.eta.services;

import java.util.Iterator;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.eta.dsignature.DigitalSignature;
import com.eta.jsonhandle.Signature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
public class MainRestController {
	
	@PostMapping("/dsign")
	@ResponseStatus(HttpStatus.CREATED)
	public JsonNode getSign(@RequestBody String Docs) throws Exception {
		
		JsonNode mm ;
		String json = "";
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		objectMapper.configure(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS, true);	
		objectMapper.setNodeFactory(JsonNodeFactory.withExactBigDecimals(true));
		
		 JsonNode jsonData = objectMapper.readTree(Docs);
		
		JsonNode DOCS = jsonData.get("documents");	
		Iterator<JsonNode> docsNodeList =DOCS.iterator() ;	
		
		while(docsNodeList.hasNext()) {
			
		try{
			DigitalSignature DSS = new DigitalSignature();
			JsonNode Doc = docsNodeList.next();
			
			String  signature = DSS.getSignature(Doc);
			
			Signature ss[] = new Signature[1];
			ss[0] = new Signature();
			ss[0].setSignatureType("I");
			ss[0].setValue(signature);

			 json = objectMapper.writeValueAsString(ss);
			 mm = objectMapper.readTree(json);
            
			((ObjectNode) Doc).set("signatures", mm);
			
		}
		catch(Exception e ) {
			System.out.println("exception:"+e.getMessage());
		}  
		
		}
	      
		  String SDOCS = DOCS.toPrettyString();
		  
		  SDOCS = "{\r\n    \"documents\":" +SDOCS+ "\r\n}";
          DOCS =  objectMapper.readTree(SDOCS);
		  
	      return DOCS;
	      
    	}
	  
      }
