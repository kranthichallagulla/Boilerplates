package com.stackroute.datamunger.query;

//header class
public class Header {

	/*
	 * this class should contain a member variable which is a String array, to hold
	 * the headers and should override toString() method.
	 */
	String[] header;
	public Header(String[] header) {
		this.header = header;
	}

	public String[] getHeaders() {

		return header;
	}
	public void setHeaders(String[] header) {
		this.header = header;
	}

}