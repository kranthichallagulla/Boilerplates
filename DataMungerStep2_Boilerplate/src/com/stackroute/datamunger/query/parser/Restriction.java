package com.stackroute.datamunger.query.parser;/*
 * This class is used for storing name of field, condition and value for
 * each conditions
 * generate getter and setter for this class,
 * Also override toString method
 * */public class Restriction {
	private String name;
	private String value;    public String getName() {
		return name;
	}    public String getValue() {
		return value;
	}    public String getCondition() {
		return condition;
	}    private String condition;
	// Write logic for constructor
	public Restriction(String name, String value, String condition) {
		this.name = name;
		this.value = value;
		this.condition = condition;
	}
	public String getRestriction() {
		return this.toString();
	}    public void setRestriction(String name, String value, String condition) {
		this.name= name;
		this.condition = condition;
		this.value = value;
	}
	public String toString() {
		return this.name + " " + this.value + " " + this.condition;
	}
}