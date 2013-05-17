package com.sfcontrolling.gui;

import com.sfcontroll.business.Costtype;
import com.sfcontroll.db.CosttypeDAO;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.ComboBox;

public class CostTableData {
	
	private SimpleObjectProperty<ComboBox<String>> cbCosttype;
	
	private SimpleStringProperty value;
	
	public CostTableData(){
		initTableData();
	}

	private void initTableData() {
		ComboBox<String> cb = new ComboBox<String>();
		for(Costtype costtype : CosttypeDAO.getAllCategeriesFromDB()){
			cb.getItems().add(costtype.getCategoryName());
		}
		
		this.cbCosttype = new SimpleObjectProperty<ComboBox<String>>(cb);
	}

	public ComboBox<String> getCbCosttype() {
		return cbCosttype.get();
	}

	public void setCbCosttype(ComboBox<String> cbCosttype) {
		this.cbCosttype.set(cbCosttype);
	}

	public String getValue() {
		return value.getValue();
	}

	public void setValue(String value) {
		this.value.setValue(value);
	}
	
	

}
