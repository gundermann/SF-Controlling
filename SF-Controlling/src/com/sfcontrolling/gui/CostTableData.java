package com.sfcontrolling.gui;


import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class CostTableData {
	
	private SimpleObjectProperty<ComboBox<String>> cbCosttype;
	
	private SimpleObjectProperty<TextField> value;
	
	public CostTableData(){
		cbCosttype = new SimpleObjectProperty<ComboBox<String>>();
		value = new SimpleObjectProperty<TextField>();
	}

	public ComboBox<String> getCbCosttype() {
		if(cbCosttype.get() == null){
			return new ComboBox<String>();
		}else{
			return  cbCosttype.get();
		}
	}

	public void setCbCosttype(ComboBox<String> box) {
		this.cbCosttype.set(box);
	}

	public TextField getValue() {
		if(value.get() == null){
			return new TextField();
		}
		else{
			return value.get();
		}
	}

	public void setValue(TextField tf) {
		this.value.setValue(tf);
	}
	
	

}
