package com.sfcontroll.business;

public class Cost {

	private Costtype costtype;
	
	private double value;
	
	private double subsityValue;

	public String getCosttype() {
		return costtype.getCategoryName();
	}

	public void setCosttype(Costtype costtype) {
		this.costtype = costtype;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public double getSubsityValue() {
		return subsityValue;
	}

	public void setSubsityValue(double subsityValue) {
		this.subsityValue = subsityValue;
	}
	
	public double getCalculatedSubstityValue(){
		return value*costtype.getSubsityRate()/100;
	}
	
	
	
	
}
