package com.sfcontrolling.gui;

import java.util.ArrayList;
import java.util.HashMap;

import com.sfcontroll.business.Entry;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainGuiLoader extends Application{

	MainGuiController control;
	public static HashMap<String, Integer> categoryContainer = new HashMap<String, Integer>();
	public static ArrayList<Entry> entries = new ArrayList<Entry>();
	
	@Override
	public void start(Stage stage) throws Exception {
		 FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
				 "SF-Controlling_MAIN.fxml"));

		 AnchorPane root = (AnchorPane) fxmlLoader.load();;
		 
         stage.setTitle("SF-Controlling");
         stage.setScene(new Scene(root));
         stage.setResizable(false);
         stage.show();
    
//         MainGuiController.reactOnNewEntry();
	}

	public static void main(String[] arg){
		categoryContainer.put("testCat", 1);
//		for(DTOCostsEntry costEntry : CostEntryDAO.getAllEntry()){
//			Entry entry = new Entry();
//			entry.setName(costEntry.getName());
//			entry.setDate(costEntry.getDate());
//			entry.setCategory(costEntry.getCategory());
//			entries.add(entry);
//		}
		launch(arg);
	}

}
