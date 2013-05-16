package com.sfcontroll.business;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Costtype implements Serializable{

	private static final long serialVersionUID = 778729181498362685L;

	@Id
	private String type;
	
	private double subsityRate;
	
	public Costtype(String name, double rate){
		this.type = name;
		this.subsityRate = rate;
	}
	
	public Costtype(){
		
	}

	public String getCategoryName() {
		return type;
	}

	public void setCategoryName(String categoryName) {
		this.type = categoryName;
	}

	public Double getSubsityRate() {
		return subsityRate;
	}

	public void setSubsityRate(Double subsityRate) {
		this.subsityRate = subsityRate;
	}

	
	
}
