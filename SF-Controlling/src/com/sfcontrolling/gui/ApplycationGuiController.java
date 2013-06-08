package com.sfcontrolling.gui;


import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;


import com.sfcontroll.business.Cost;
import com.sfcontroll.business.CostContainer;
import com.sfcontroll.business.Costtype;
import com.sfcontroll.business.Entry;
import com.sfcontroll.db.CostEntryDAO;
import com.sfcontroll.db.CosttypeDAO;
import com.sfcontroll.db.DTOCostsEntry;
import com.sfcontroll.db.DTOSubcosts;
import com.sfcontroll.db.SubcostDAO;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Text;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

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
	private TableView<CostTableData> tCosts;
	
	@FXML
	private TableColumn<CostTableData, ComboBox<String>> ctcCosttype;
	
	@FXML 
	private TableColumn<CostTableData, TextField> ctcValue;
	
	@FXML
	private TableView<SubstityTableData> tSubstities;
	
	@FXML
	private TableColumn<SubstityTableData, String> stcCosttype;
	
	@FXML
	private TableColumn<SubstityTableData, TextField> stcValue;
	
	@FXML
	private TableColumn<SubstityTableData, ToggleButton> stcApplied;
	
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
		int year = Integer.parseInt(tfDate.getText().substring(0, 4));
		int month = Integer.parseInt(tfDate.getText().substring(5, 7));
		int day = Integer.parseInt(tfDate.getText().substring(8));
		Date date = new Date();
		date.setDate(day);
		date.setMonth(month);
		date.setYear(year);
		
		if( currentEntry == null){
			currentEntry = new Entry();
		}
		currentEntry.setName(tfTitle.getText());
		currentEntry.setCategory(cbCategory.getSelectionModel().getSelectedItem());
		currentEntry.setDate(date);
		
		int lineCounter = -1;
		for(CostTableData data : tCosts.getItems()){
			lineCounter++;
			if(!isSubcostAlreadyApplied(data)){
			Cost subcost = new Cost();
			Costtype type = CosttypeDAO.getCategoryById(data.getCbCosttype().getSelectionModel().getSelectedItem());
			subcost.setCosttype(type);
			subcost.setValue(Double.parseDouble(data.getValue().getText()));
			SubstityTableData substityData = tSubstities.getItems().get(lineCounter);
			if(substityData.isApplied()){
				subcost.setSubsityValue(Double.parseDouble(substityData.getSubstityValue().getText()));
			}
			else{
				subcost.setSubsityValue(-1);
			}
			currentEntry.getSubcosts().add(subcost);
			
			}
		}
		
		makeCurrentEntryPersistent();
		
		MainGuiController.reactOnNewEntry();
		
	}	
	private boolean isSubcostAlreadyApplied(CostTableData data) {
		String costtypeName = data.getCbCosttype().getSelectionModel().getSelectedItem();
		int entryId = currentEntry.getId();
		
		return (SubcostDAO.findSubcostByData(costtypeName, entryId) != null);
	}

	private void makeCurrentEntryPersistent() {
		DTOCostsEntry newEntry;
		if(currentEntry.getId() == 0){
			newEntry = new DTOCostsEntry();
			newEntry.setId(Math.round(CostEntryDAO.findNextId()));
		}
		else{
			newEntry = CostEntryDAO.getEntryByPossibleIdAndName(currentEntry.getId(), currentEntry.getName());
		}
		newEntry.setCategory(currentEntry.getCategory());
		newEntry.setDate(currentEntry.getDate());
		newEntry.setName(currentEntry.getName());
		
		CostEntryDAO.saveOrUpdateEntry(newEntry);
		
		for(Cost subcost: currentEntry.getSubcosts()){
			DTOSubcosts subcostsInDB = SubcostDAO.findSubcostByData(subcost.getCosttype(), currentEntry.getId());
			if(subcostsInDB == null){
				subcostsInDB = new DTOSubcosts();
				subcostsInDB.setSubcostid(SubcostDAO.findNextId());
			}
			subcostsInDB.setEntryid(CostEntryDAO.getEntrieID(newEntry));
			subcostsInDB.setCosttype(subcost.getCosttype());
			subcostsInDB.setValue(subcost.getValue());
			SubcostDAO.saveOrUpedateSubcost(subcostsInDB);
		}
	}

	@FXML
	private void addCost(){
		updateSubcosts(false);
	}

	
	private CostTableData createNewTableEntryCosts() {
		final CostTableData data = new CostTableData();
		
		ComboBox<String> box = new ComboBox<String>();
		box.promptTextProperty().set("Kostentyp wählen");
		for(Costtype type : CosttypeDAO.getAllCategeriesFromDB()){
			box.getItems().add(type.getCategoryName());
		}
		
		box.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent paramT) {
				// TODO Auto-generated method stub
				
			}
		});
		
		data.setCbCosttype(box);
		
		TextField tf = new TextField();
		
		tf.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent paramT) {
				// TODO Auto-generated method stub
				
			}
		});
		
		data.setValue(tf);
		return data;
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
		initTable();
	}

	private void initTable() {
		ctcCosttype.setCellValueFactory(new PropertyValueFactory<CostTableData, ComboBox<String>>("cbCosttype"));
		ctcValue.setCellValueFactory(new PropertyValueFactory<CostTableData, TextField>("value"));
		ctcValue.setEditable(true);
		tCosts.setEditable(true);
		
		stcCosttype.setCellValueFactory(new PropertyValueFactory<SubstityTableData, String>("costtype"));
		stcCosttype.setEditable(false);
		stcValue.setCellValueFactory(new PropertyValueFactory<SubstityTableData, TextField>("substityValue"));
		stcValue.setEditable(true);
		stcApplied.setCellValueFactory(new PropertyValueFactory<SubstityTableData, ToggleButton>("isApplied"));
		tSubstities.setEditable(true);
		
		if(currentEntry != null){
			updateSubcosts(true);
		}
	}

	private void updateSubcosts(boolean initialization) {
		if(initialization){
			for(Cost subcost : currentEntry.getSubcosts()){
				final CostTableData data = createNewTableEntryCosts();
				
				data.getCbCosttype().getSelectionModel().select(subcost.getCosttype());
				data.getValue().setText(String.valueOf(subcost.getValue()));
				
				tCosts.getItems().add(data);
				
				final SubstityTableData substityData = createNewTableEntrySubstity();
				
				substityData.setCosttype(subcost.getCosttype());
				if(subcost.getSubsityValue() < 0){
					substityData.getSubstityValue().setText(String.valueOf(subcost.getCalculatedSubstityValue()));
				}else{
					substityData.getSubstityValue().setText(String.valueOf(subcost.getSubsityValue()));
					substityData.getIsApplied().setSelected(true);
				}
				
				tSubstities.getItems().add(substityData);
			}
		}
		else{
			tCosts.getItems().add(createNewTableEntryCosts());
			tSubstities.getItems().add(createNewTableEntrySubstity());
		}
	}

	private SubstityTableData createNewTableEntrySubstity() {
		final SubstityTableData data = new SubstityTableData();
		
		data.setCosttype("Kategorie wählen");
		
		TextField tf = new TextField("0");
		data.setSubstityValue(tf);
		
		ToggleButton btApply = new ToggleButton();
		data.setIsApplied(btApply);
		
		return data;
	}

	private void initBindings() {
		if(currentEntry != null){
		SimpleStringProperty name = new SimpleStringProperty();
		SimpleStringProperty cat = new SimpleStringProperty();
		SimpleStringProperty date = new SimpleStringProperty();
		name.set("");
		cat.set("Kategorie wählen");
		date.set("");
		
			if(currentEntry.getName() != null){
				name.set(currentEntry.getName());
			}
			if(currentEntry.getCategory() != null){
				cat.set(currentEntry.getCategory());
			}
			if(currentEntry.getDate() != null){
				date.set(currentEntry.getDate().toString());
			}
		
		tfTitle.textProperty().bind(name);
		tfDate.textProperty().bind(date);
		cbCategory.valueProperty().bind(cat);
		}
	}

	public static void setCurrentEntry(DTOCostsEntry dtoEntry) {
		if(dtoEntry == null){
			currentEntry = null;
		}
		else{
			currentEntry = new Entry(dtoEntry.getName(), dtoEntry.getCategory(), dtoEntry.getDate());
			currentEntry.setId(dtoEntry.getId());
			currentEntry.setSubcosts(createSubcostContainer(dtoEntry.getId()));
		}
	}

	private static CostContainer createSubcostContainer(int id) {
		CostContainer container = new CostContainer();
		for(DTOSubcosts dtoSubcost : SubcostDAO.getAllSubcostsByEntryId(id)){
			Cost subcost = new Cost();
			Costtype type = CosttypeDAO.getCategoryById(dtoSubcost.getCosttype());
			subcost.setCosttype(type);
			subcost.setValue(dtoSubcost.getValue());
			subcost.setSubsityValue(dtoSubcost.getSubstity());
			container.add(subcost);
		}
		return container;
	}


}
