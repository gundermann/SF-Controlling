package com.sfcontrolling.gui;

import javafx.beans.property.SimpleStringProperty;

public class CostTableData {
	
	private SimpleStringProperty cbCosttype;
	
	private SimpleStringProperty value;
	
	public CostTableData(){
		initTableData();
	}

	private void initTableData() {
		this.cbCosttype = new SimpleStringProperty("Kostenart wählen");
		
	}

	public String getCbCosttype() {
		return cbCosttype.get();
	}

	public void setCbCosttype(String cbCosttype) {
		this.cbCosttype.set(cbCosttype);
	}

	public String getValue() {
		if(value == null){
			return "0";
		}else{
			return value.getValue();
		}
	}

	public void setValue(String value) {
		this.value.setValue(value);
	}
	
	

}
