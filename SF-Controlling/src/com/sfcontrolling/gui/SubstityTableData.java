package com.sfcontrolling.gui;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;

public class SubstityTableData {
	
	private SimpleStringProperty costtype;
	
	private SimpleObjectProperty<TextField> substityValue;
	
	private SimpleObjectProperty<ToggleButton> isApplied;
	
	public SubstityTableData(){
		costtype = new SimpleStringProperty();
		substityValue = new SimpleObjectProperty<TextField>();
		isApplied = new SimpleObjectProperty<ToggleButton>();
	}

	public String getCosttype() {
		if(costtype == null){
			return "";
		}
		else{
			return costtype.get();
		}
	}

	public void setCosttype(String costtype) {
		this.costtype.set(costtype);
	}

	public TextField getSubstityValue() {
		if(substityValue == null){
			return new TextField();
		}else{
			return substityValue.get();
		}
	}

	public void setSubstityValue(TextField substityValue) {
		this.substityValue.set(substityValue);
		this.substityValue.get().editableProperty().bind(new SimpleBooleanProperty(!isApplied()));
	}

	public ToggleButton getIsApplied() {
		if(isApplied == null){
			return new ToggleButton();
		}
		else{
			return isApplied.get();
		}
	}

	public void setIsApplied(ToggleButton isApplied) {
		this.isApplied.set(isApplied);
	}

	public boolean isApplied() {
		if(isApplied.get() == null){
			return false;
		}else{
			return isApplied.get().isSelected();
		}
	}
	
	

}
