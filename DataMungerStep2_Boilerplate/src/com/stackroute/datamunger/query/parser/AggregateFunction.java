package com.stackroute.datamunger.query.parser;/* This class is used for storing name of field, aggregate function for
 * each aggregate function
 * generate getter and setter for this class,
 * Also override toString method
 * */public class AggregateFunction {
	private String field,function;
	public String getField() {
		return field;
	}    public void setField(String field) {
		this.field = field;
	}    // Write logic for constructor    public String getFunction() {
	public String getFunction()
	{
		return function;
	}    public void setFunction(String function) {
		this.function = function;
	}    public AggregateFunction(String field, String function) {
		this.field = field;
		this.function = function;    }
	public String getAggregateFunction() {
		return this.toString();
	}
	public void setAggregateFunction(String field, String aggFunction) {
		this.field = field;
		this.function = aggFunction;    }
	public String toString() {
		return this.field +""+ this.function;
	}
}