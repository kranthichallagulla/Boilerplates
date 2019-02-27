package com.stackroute.datamunger.query;

import java.util.Arrays;

public class DataTypeDefinitions {

	/*
	 * This class should contain a member variable which is a String array, to hold
	 * the data type for all columns for all data types
	 */
	private String[] columnType;//array//


	public String[] getColumnType() {
		return columnType;
	}

	public void setColumnType(String[] columnType) {
		this.columnType = columnType;
	}

	public DataTypeDefinitions(String[] columnType) {
		this.columnType = columnType;
	}
	public String[] getDataTypes() {
		return columnType;
	}
    /*
    //getter method of "DataTypes"//


    //setter method of "DataTypes//
    public DataTypeDefinitions(String[] dataTypes) {
        this.DataTypes = dataTypes;
    }


    }
    */

    /*
    public String[] getColumnnamedatatypes() {
        return columnnamedatatypes;
    }
*/

}