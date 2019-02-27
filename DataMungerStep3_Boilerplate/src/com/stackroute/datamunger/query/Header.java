package com.stackroute.datamunger.query;

public class Header {



	/*
	 * This class should contain a member variable which is a String array, to hold
	 * the headers.
	 */
	String[] header;

	public String[] getHeaders()
	{
		return header;
	}

	public void setHeader(String[] header)
	{
		this.header = header;
	}


	public Header(String[] header)

	{
		this.header=header;
	}


}