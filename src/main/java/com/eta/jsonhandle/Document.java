package com.eta.jsonhandle;

public class Document {
	
	private Issuer issuer;
	private Receiver receiver;
	
    private String documentType;
    private String documentTypeVersion;
    private String dateTimeIssued;
    private String taxpayerActivityCode;
    private String internalID;
    private String purchaseOrderReference;
    private String purchaseOrderDescription;
    private String salesOrderReference;
	private String salesOrderDescription;
    private String proformaInvoiceNumber;
    private Payment payment;
    private Delivery delivery;
    
    private InvoiceLines[] invoiceLines;
    
    private String totalDiscountAmount;
    private Double totalSalesAmount;
    private Double netAmount;

    private TaxTotals[] taxTotals;
    
    private float totalAmount = 0.00000f;
    private float extraDiscountAmount = 0.00000f;
    private float totalItemsDiscountAmount = 0.00000f;
    
	private Signature[] signatures;


	public Issuer getIssuer() {
		return issuer;
	}


	public void setIssuer(Issuer issuer) {
		this.issuer = issuer;
	}


	public Receiver getReceiver() {
		return receiver;
	}


	public void setReceiver(Receiver receiver) {
		this.receiver = receiver;
	}


	public String getDocumentType() {
		return documentType;
	}


	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}


	public String getDocumentTypeVersion() {
		return documentTypeVersion;
	}


	public void setDocumentTypeVersion(String documentTypeVersion) {
		this.documentTypeVersion = documentTypeVersion;
	}


	public String getDateTimeIssued() {
		return dateTimeIssued;
	}


	public void setDateTimeIssued(String dateTimeIssued) {
		this.dateTimeIssued = dateTimeIssued;
	}


	public String getTaxpayerActivityCode() {
		return taxpayerActivityCode;
	}


	public void setTaxpayerActivityCode(String taxpayerActivityCode) {
		this.taxpayerActivityCode = taxpayerActivityCode;
	}


	public String getInternalID() {
		return internalID;
	}


	public void setInternalID(String internalID) {
		this.internalID = internalID;
	}


	public String getPurchaseOrderReference() {
		return purchaseOrderReference;
	}


	public void setPurchaseOrderReference(String purchaseOrderReference) {
		this.purchaseOrderReference = purchaseOrderReference;
	}


	public String getPurchaseOrderDescription() {
		return purchaseOrderDescription;
	}


	public void setPurchaseOrderDescription(String purchaseOrderDescription) {
		this.purchaseOrderDescription = purchaseOrderDescription;
	}


	public String getSalesOrderReference() {
		return salesOrderReference;
	}


	public void setSalesOrderReference(String salesOrderReference) {
		this.salesOrderReference = salesOrderReference;
	}


	public String getSalesOrderDescription() {
		return salesOrderDescription;
	}


	public void setSalesOrderDescription(String salesOrderDescription) {
		this.salesOrderDescription = salesOrderDescription;
	}


	public String getProformaInvoiceNumber() {
		return proformaInvoiceNumber;
	}


	public void setProformaInvoiceNumber(String proformaInvoiceNumber) {
		this.proformaInvoiceNumber = proformaInvoiceNumber;
	}


	public Payment getPayment() {
		return payment;
	}


	public void setPayment(Payment payment) {
		this.payment = payment;
	}


	public Delivery getDelivery() {
		return delivery;
	}


	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
	}


	public InvoiceLines[] getInvoiceLines() {
		return invoiceLines;
	}


	public void setInvoiceLines(InvoiceLines[] invoiceLines) {
		this.invoiceLines = invoiceLines;
	}


	public String getTotalDiscountAmount() {
		return totalDiscountAmount;
	}


	public void setTotalDiscountAmount(String totalDiscountAmount) {
		this.totalDiscountAmount = totalDiscountAmount;
	}


	public Double getTotalSalesAmount() {
		return totalSalesAmount;
	}


	public void setTotalSalesAmount(Double totalSalesAmount) {
		this.totalSalesAmount = totalSalesAmount;
	}


	public Double getNetAmount() {
		return netAmount;
	}


	public void setNetAmount(Double netAmount) {
		this.netAmount = netAmount;
	}


	public TaxTotals[] getTaxTotals() {
		return taxTotals;
	}


	public void setTaxTotals(TaxTotals[] taxTotals) {
		this.taxTotals = taxTotals;
	}


	public float getTotalAmount() {
		return totalAmount;
	}


	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}


	public float getExtraDiscountAmount() {
		return extraDiscountAmount;
	}


	public void setExtraDiscountAmount(float extraDiscountAmount) {
		this.extraDiscountAmount = extraDiscountAmount;
	}


	public float getTotalItemsDiscountAmount() {
		return totalItemsDiscountAmount;
	}


	public void setTotalItemsDiscountAmount(float totalItemsDiscountAmount) {
		this.totalItemsDiscountAmount = totalItemsDiscountAmount;
	}


	public Signature[] getSignatures() {
		return signatures;
	}


	public void setSignatures(Signature[] signatures) {
		this.signatures = signatures;
	}
   
	
}
