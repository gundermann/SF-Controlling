package com.sfcontrolling.gui;


import java.net.URL;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

import com.sfcontroll.business.Entry;
import com.sfcontroll.db.CostEntryDAO;
import com.sfcontroll.db.DTOCostsEntry;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;

public class ApplycationGuiController implements Initializable{

	@FXML
	private ComboBox<String> cbCategory;
	
	@FXML
	private TitledPane tpCosts;
	
	@FXML
	private TitledPane tpSubsities;
	
	@FXML
	private Button btAplly;
	
	@FXML
	private TableView<String> tCosts;
	
	@FXML
	private Button btAddCosts;
	
	@FXML
	private Button btDeleteCost;
	
	@FXML
	private TextField tfTitle;
	
	@FXML
	private Label lbDate;
	
	@FXML
	private TextField tfDate;
	
	private static Entry currentEntry;
	
	private void initCostsPane() {
		for(String category : MainGuiLoader.categoryContainer.keySet()){
			cbCategory.getItems().add(category);
		}
	}
	
	@FXML
	protected void applyEntry(){
		int day = Integer.parseInt(tfDate.getText().substring(0, 2));
		int month = Integer.parseInt(tfDate.getText().substring(3, 5));
		int year = Integer.parseInt(tfDate.getText().substring(6));
		Date date = new Date();
		date.setDate(day);
		date.setMonth(month);
		date.setYear(year);
		
		Entry newEntry = new Entry(tfTitle.getText(), cbCategory.getSelectionModel().getSelectedItem(), date);
		
		CostEntryDAO.saveOrUpdateEntry(newEntry);
		MainGuiController.reactOnNewEntry();
	}
	
	@FXML
	private void addCost(){
		
	}
	
	@FXML
	private void deleteCost(){
		
	}
	
	@FXML
	protected void checkDate(){
		
	}
	
	@FXML
	protected void checkTitle(){
		
	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initCostsPane();
		initBindings();
	}

	private void initBindings() {
		SimpleStringProperty name = new SimpleStringProperty();
		SimpleStringProperty cat = new SimpleStringProperty();
		SimpleStringProperty date = new SimpleStringProperty();
		name.set("");
		cat.set("Kategorie wählen");
		date.set("");
		
		if(currentEntry != null){
			if(currentEntry.getName() != null){
				name.set(currentEntry.getName());
			}
			if(currentEntry.getCategory() != null){
				cat.set(currentEntry.getCategory());
			}
			if(currentEntry.getDate() != null){
				date.set(currentEntry.getDate().toString());
			}
		}
		
		tfTitle.textProperty().bind(name);
		tfDate.textProperty().bind(date);
		cbCategory.valueProperty().bind(cat);
	}

	public static void setCurrentEntry(DTOCostsEntry dtoEntry) {
		if(dtoEntry == null){
			currentEntry = null;
		}
		else{
			currentEntry = new Entry(dtoEntry.getName(), dtoEntry.getCategory(), dtoEntry.getDate());
		}
	}


}
