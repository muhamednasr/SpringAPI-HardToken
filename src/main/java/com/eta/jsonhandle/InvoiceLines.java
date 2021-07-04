package com.eta.jsonhandle;

public class InvoiceLines {
	
	private String description;
	private String itemType;
	private String itemCode;
	private String unitType;
	private Double quantity;
	private String internalCode;
	private Double salesTotal;
	private Double total;
	private Double valueDifference;
	private Double totalTaxableFees;
	private Double netTotal;
	private Double itemsDiscount;
	
	private UnitValue unitValue;
	private Discount discount;
	
	private TaxableItems[] taxableItems;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getUnitType() {
		return unitType;
	}

	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public String getInternalCode() {
		return internalCode;
	}

	public void setInternalCode(String internalCode) {
		this.internalCode = internalCode;
	}

	public Double getSalesTotal() {
		return salesTotal;
	}

	public void setSalesTotal(Double salesTotal) {
		this.salesTotal = salesTotal;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Double getValueDifference() {
		return valueDifference;
	}

	public void setValueDifference(Double valueDifference) {
		this.valueDifference = valueDifference;
	}

	public Double getTotalTaxableFees() {
		return totalTaxableFees;
	}

	public void setTotalTaxableFees(Double totalTaxableFees) {
		this.totalTaxableFees = totalTaxableFees;
	}

	public Double getNetTotal() {
		return netTotal;
	}

	public void setNetTotal(Double netTotal) {
		this.netTotal = netTotal;
	}

	public Double getItemsDiscount() {
		return itemsDiscount;
	}

	public void setItemsDiscount(Double itemsDiscount) {
		this.itemsDiscount = itemsDiscount;
	}

	public UnitValue getUnitValue() {
		return unitValue;
	}

	public void setUnitValue(UnitValue unitValue) {
		this.unitValue = unitValue;
	}

	public Discount getDiscount() {
		return discount;
	}

	public void setDiscount(Discount discount) {
		this.discount = discount;
	}

	public TaxableItems[] getTaxableItems() {
		return taxableItems;
	}

	public void setTaxableItems(TaxableItems[] taxableItems) {
		this.taxableItems = taxableItems;
	}
	
	
	
}
