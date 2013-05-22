package com.sfcontrolling.gui;

import com.sfcontroll.business.Costtype;
import com.sfcontroll.db.CosttypeDAO;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class CostTableData {
	
	private SimpleStringProperty cbCosttype;
	
	private SimpleStringProperty value;
	
	public CostTableData(){
		initTableData();
	}

	private void initTableData() {
//		for(Costtype costtype : CosttypeDAO.getAllCategeriesFromDB()){
//			cb.getItems().add(costtype.getCategoryName());
//		}
//		
//		this.cbCosttype = new SimpleStringProperty(cb);
		
//		cbCosttype.setCellFactory(new Callback<TableColumn<CostTableData, cbCosttype>,TableCell<CostTableData,cbCosttype>>(){        
//			@Override
//			public TableCell<CostTableData, String> call(TableColumn<CostTableData, String> param) {                
//				TableCell<CostTableData, String> cell = new TableCell<CostTableData, String>(){
//					@Override
//					public void updateItem(String item, boolean empty) {
//						if(item!=null){
//
//						   ComboBox<String> box = new ComboBox<String>();                                                      
////						   box.getSelectionModel().select();
//						   //SETTING ALL THE GRAPHICS COMPONENT FOR CELL
//						   setGraphic(box);
//						} 
//					}
//				};                           
//				return cell;
//			}	
//		});
	}

	public String getCbCosttype() {
		return cbCosttype.get();
	}

	public void setCbCosttype(String cbCosttype) {
		this.cbCosttype.set(cbCosttype);
	}

	public String getValue() {
		return value.getValue();
	}

	public void setValue(String value) {
		this.value.setValue(value);
	}
	
	

}
