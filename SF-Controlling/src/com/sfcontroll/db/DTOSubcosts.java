package com.sfcontroll.db;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "subcosts")
public class DTOSubcosts {

	@Id
	private int subcostid;
	
	private int entryid;
	
	private String costtype;
	
	private double value;
	
	private double substity;

	public int getSubcostid() {
		return subcostid;
	}

	public void setSubcostid(int subcostid) {
		this.subcostid = subcostid;
	}

	public int getEntryid() {
		return entryid;
	}

	public void setEntryid(int entryid) {
		this.entryid = entryid;
	}

	public String getCosttype() {
		return costtype;
	}

	public void setCosttype(String costtype) {
		this.costtype = costtype;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public double getSubstity() {
		return substity;
	}

	public void setSubstity(double substity) {
		this.substity = substity;
	}
	
	
}
