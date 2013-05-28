package com.sfcontrolling.gui;

import java.net.URL;
import java.util.ResourceBundle;

import com.sfcontroll.business.Costtype;
import com.sfcontroll.db.CosttypeDAO;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CosttypeSettingsGuiController implements Initializable{

	@FXML
	private ComboBox<String> cbNewCategory;
	
	@FXML
	private TextField tfNewTitle;
	
	@FXML
	private Button btNewCAtegory;
	
	@FXML
	private Button btDeleteCategory;
	
	@FXML
	private Slider slSubsity;
	
	@FXML 
	private TextField tfSubsity;
	
	
	@FXML
	private void delSelectedCategory(){
		if(cbNewCategory.getSelectionModel().getSelectedIndex() != 0){
			CosttypeDAO.getDeleteCategory(cbNewCategory.getSelectionModel().getSelectedItem());
			clearOrInitAllElements();
		}
		
	}
	
	private void clearOrInitAllElements() {
		tfNewTitle.setText("");
		tfSubsity.setText(String.valueOf(0));
		cbNewCategory.getItems().clear();
		cbNewCategory.getItems().add("Neue Kostenart");
		for(Costtype cat : CosttypeDAO.getAllCategeriesFromDB()){
			cbNewCategory.getItems().add(cat.getCategoryName());
		}
		cbNewCategory.getSelectionModel().select(0);
	}

	@FXML
	private void saveNewCategory(){
		Costtype cat = new Costtype(tfNewTitle.getText(), Double.parseDouble(tfSubsity.getText()));
		CosttypeDAO.saveOrUpdateCategory(cat);
		Stage stage = (Stage) btNewCAtegory.getScene().getWindow();
	    stage.close();
	}
	
	@FXML
	private void selectCategory(){
		if(cbNewCategory.getSelectionModel().getSelectedIndex() != 0){
			Costtype cat = CosttypeDAO.getCategoryById(cbNewCategory.getSelectionModel().getSelectedItem());
			tfNewTitle.setText(cat.getCategoryName());
			tfSubsity.setText(String.valueOf(cat.getSubsityRate()));
			updateSlider();
		}
	}

	@FXML
	private void changeSubsityRateBySlider(){
		tfSubsity.setText(String.valueOf(Math.round(slSubsity.getValue())));
	}
	
	@FXML
	private void changeSubsityRateByTextField(){
		try{
			updateSlider();
		}
		catch(NumberFormatException nfe){
			slSubsity.setValue(0);
		}
	}
	
	private void updateSlider() {
		slSubsity.setValue(Double.parseDouble(tfSubsity.getText()));
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		clearOrInitAllElements();
	}
	
}
