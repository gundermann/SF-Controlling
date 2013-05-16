package com.sfcontrolling.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.sfcontroll.db.CostEntryDAO;
import com.sfcontroll.db.DTOCostsEntry;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainGuiController {

	@FXML
	private VBox vBoxEntries;
	
	@FXML
	private TextField tfSearch;
	
	@FXML
	private static TreeView<String> tvEntries;
	
	@FXML
	private Button btNeuerEintrag;
	
	@FXML
	private Button btEintragBearbeiten;
	
	@FXML
	private Button btSummary;
	
	@FXML
	private Button btSettings;
	
	@FXML
	private BorderPane bpMain;
	
	@FXML
	public void findEntry(){
		if(tfSearch.getText().equals("")){
			reactOnNewEntry();
		}else{
			tvEntries.setRoot(new TreeItem<String>("Kosten"));
			for(DTOCostsEntry dtoEntry : CostEntryDAO.getEntriesByName(tfSearch.getText())){
				TreeItem<String> newEntry = new TreeItem<String>(dtoEntry.getName());
				tvEntries.getRoot().getChildren().add(newEntry);
				}
		}
	}
	
	@FXML
	public void selectEntry(){
		ApplycationGuiController.setCurrentEntry(CostEntryDAO.getEntryByPossibleIdAndName(tvEntries.getSelectionModel().getSelectedIndex(), tvEntries.getSelectionModel().getSelectedItem().getValue()));
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
				 "SF-Controlling_APPLYCATION.fxml"));

		 AnchorPane root;
		try {
			root = (AnchorPane) fxmlLoader.load();
			bpMain.setCenter(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void newEntry(){
		ApplycationGuiController.setCurrentEntry(null);
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
				 "SF-Controlling_APPLYCATION.fxml"));

		 AnchorPane root;
		try {
			root = (AnchorPane) fxmlLoader.load();
			bpMain.setCenter(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
        
	}
	
	@FXML
	public void changeEntry(){
		
	}
	
	@FXML
	public void openSummary(){
		
	}
	
	@FXML
	public void openSettings(){
		Stage settingStage = new Stage();
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
				 "SF-Controlling_COSTTYPESETTINGS.fxml"));

		 AnchorPane root;
		try {
			root = (AnchorPane) fxmlLoader.load();
			settingStage.setScene(new Scene(root));
			settingStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
	}

	public static void reactOnNewEntry() {
		tvEntries.setRoot(new TreeItem<String>("Kosten"));
//		tvEntries.getRoot().getChildren().clear();
		for(DTOCostsEntry dtoEntry : CostEntryDAO.getAllEntriesFromDB()){
		TreeItem<String> newEntry = new TreeItem<String>(dtoEntry.getName());
		tvEntries.getRoot().getChildren().add(newEntry);
		}
	}



}
