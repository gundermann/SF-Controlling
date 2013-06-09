package com.sfcontroll.business;

import java.util.GregorianCalendar;




public class Entry {

	private int id = 0;
	
	private String name;
	
	private GregorianCalendar date;
	
	private String category;
	
	private CostContainer subcosts;
	
	public Entry(String name, String cat, GregorianCalendar date){
		this.name = name;
		this.category = cat;
		this.date = date;
		subcosts = new CostContainer();
	}

	
	
	public Entry() {
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

	public GregorianCalendar getDate() {
		return date;
	}
	
	public String getDateAsString(){
		StringBuilder sb = new StringBuilder();
		sb.append(date.get(GregorianCalendar.DAY_OF_MONTH));
		sb.append("-");
		sb.append(date.get(GregorianCalendar.MONTH));
		sb.append("-");
		sb.append(date.get(GregorianCalendar.YEAR));
		return sb.toString();
	}

	public void setDate(GregorianCalendar date) {
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
