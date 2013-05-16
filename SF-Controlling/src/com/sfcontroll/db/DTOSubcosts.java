package com.sfcontroll.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DTOSubcosts {

	@Id
	@Column
	private int id;
	
	@Column (name = "costtype")
	private int costtype;
	
	@Column (name = "value")
	private double value;
	
	@Column (name = "subsityValue")
	private double subsityValue;
}
