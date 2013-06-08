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
		if(isApplied.get() != null)
			this.substityValue.get().setEditable(!isApplied());
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
		if(substityValue.get() != null)
			this.substityValue.get().setEditable(!isApplied());
	}

	public boolean isApplied() {
			return isApplied.get().isSelected();
	}
	
	

}
