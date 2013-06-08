package com.sfcontrolling.gui;

import java.net.URL;
import java.util.ResourceBundle;

import com.sfcontroll.business.Costtype;
import com.sfcontroll.db.CategoryDAO;
import com.sfcontroll.db.CosttypeDAO;
import com.sfcontroll.db.DTOCategory;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CosttypeSettingsGuiController implements Initializable{

	@FXML
	private ComboBox<String> cbNewType;
	
	@FXML
	private ComboBox<String> cbNewCategory;
	
	@FXML
	private TextField tfNewTitleType;
	
	@FXML
	private TextField tfNewTitleCategory;
	
	@FXML
	private Button btSaveType;
	
	@FXML
	private Button btSaveCategory;
	
	@FXML
	private Button btDeleteType;
	
	@FXML
	private Button btDeleteCategory;
	
	@FXML
	private Slider slSubsity;
	
	@FXML 
	private TextField tfSubsity;
	
	@FXML
	private CheckBox chbSubstity;
	
	
	@FXML
	private void delSelectedType(){
		if(cbNewType.getSelectionModel().getSelectedIndex() != 0){
			CosttypeDAO.getDeleteCategory(cbNewType.getSelectionModel().getSelectedItem());
			clearOrInitAllElements();
		}
		
	}
	
	@FXML
	private void delSelectedCategory(){
		
	}
	
	private void clearOrInitAllElements() {
		clearOrInitTypes();
		clearOrInitCategories();
	}

	private void clearOrInitCategories() {
		tfNewTitleCategory.setText("");
		cbNewCategory.getItems().clear();
		cbNewCategory.getItems().add("Neue Kategorie");
		for(DTOCategory cat : CategoryDAO.getAllCategoriesFromDB()){
			cbNewCategory.getItems().add(cat.getCategoryName());
		}
	}

	private void clearOrInitTypes() {
		tfNewTitleType.setText("");
		tfSubsity.setText(String.valueOf(0));
		cbNewType.getItems().clear();
		cbNewType.getItems().add("Neue Kostenart");
		for(Costtype type : CosttypeDAO.getAllCosttypesFromDB()){
			cbNewType.getItems().add(type.getCategoryName());
		}
		cbNewType.getSelectionModel().select(0);
		
	}

	@FXML
	private void saveNewType(){
		Costtype type = new Costtype(tfNewTitleType.getText(), Double.parseDouble(tfSubsity.getText()));
		CosttypeDAO.saveOrUpdateType(type);
		Stage stage = (Stage) btSaveType.getScene().getWindow();
	    stage.close();
	}
	
	@FXML
	private void saveNewCategory(){
		DTOCategory cat = new DTOCategory();
		cat.setCategoryName(tfNewTitleCategory.getText());
		cat.setSubstityFLEK(chbSubstity.isSelected());
		CategoryDAO.saveOrUpdateCategory(cat);
	}
	
	@FXML
	private void selectType(){
		if(cbNewType.getSelectionModel().getSelectedIndex() != 0){
			Costtype type = CosttypeDAO.getCategoryById(cbNewType.getSelectionModel().getSelectedItem());
			tfNewTitleType.setText(type.getCategoryName());
			tfSubsity.setText(String.valueOf(type.getSubsityRate()));
			updateSlider();
		}
	}
	
	@FXML
	private void selectCategory(){
		if(cbNewCategory.getSelectionModel().getSelectedIndex() != 0){
			DTOCategory cat = CategoryDAO.getCategoryByName(cbNewCategory.getSelectionModel().getSelectedItem());
			tfNewTitleCategory.setText(cat.getCategoryName());
			chbSubstity.setSelected(cat.isSubstityFLEKset());
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
