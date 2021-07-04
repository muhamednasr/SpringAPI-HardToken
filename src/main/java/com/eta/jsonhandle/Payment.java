package com.eta.jsonhandle;

public class Payment {
	
	private String bankName;
	private String bankAddress;
	private String bankAccountNo;
	private String bankAccountIBAN;
	private String swiftCode;
	private String terms;
	
	
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankAddress() {
		return bankAddress;
	}
	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}
	public String getBankAccountNo() {
		return bankAccountNo;
	}
	public void setBankAccountNo(String bankAccountNo) {
		this.bankAccountNo = bankAccountNo;
	}
	public String getBankAccountIBAN() {
		return bankAccountIBAN;
	}
	public void setBankAccountIBAN(String bankAccountIBAN) {
		this.bankAccountIBAN = bankAccountIBAN;
	}
	public String getSwiftCode() {
		return swiftCode;
	}
	public void setSwiftCode(String swiftCode) {
		this.swiftCode = swiftCode;
	}
	public String getTerms() {
		return terms;
	}
	public void setTerms(String terms) {
		this.terms = terms;
	}

	
}
