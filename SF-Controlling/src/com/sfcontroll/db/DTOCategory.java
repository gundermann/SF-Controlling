package com.sfcontroll.db;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "category")
public class DTOCategory {
	
	
	@Id
	private String categoryName;
	
	private boolean subsityFLEK;

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public boolean isSubstityFLEKset() {
		return subsityFLEK;
	}

	public void setSubstityFLEK(boolean substityFLEK) {
		this.subsityFLEK = substityFLEK;
	}
	
	

}
