package com.sfcontroll.business;

import java.util.Date;




public class Entry {

	private int id;
	
	private String name;
	
	private Date date;
	
	private String category;
	
	private CostContainer subcosts;
	
	public Entry(String name, String cat, Date date){
		this.name = name;
		this.category = cat;
		this.date = date;
		subcosts = new CostContainer();
	}

	
	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public CostContainer getSubcosts() {
		return subcosts;
	}

	public void setSubcosts(CostContainer subcosts) {
		this.subcosts = subcosts;
	}
	
}
