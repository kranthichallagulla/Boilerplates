package com.stackroute.datamunger.reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.stackroute.datamunger.query.DataTypeDefinitions;
import com.stackroute.datamunger.query.Header;

public class CsvQueryProcessor extends QueryProcessingEngine {

	BufferedReader br = null;
	String fileName;


	// Parameterized constructor to initialize filename
	public CsvQueryProcessor(String fileName) throws FileNotFoundException {

		this.fileName = fileName;
		br = new BufferedReader(new FileReader(fileName));

	}

	/*
	 * Implementation of getHeader() method. We will have to extract the headers
	 * from the first line of the file.
	 * Note: Return type of the method will be Header
	 */

	@Override
	public Header getHeader() throws IOException {


		br=new BufferedReader(new FileReader(fileName));
		String line1=br.readLine();
		String[] fline=line1.split(",");
		Header hr=new Header(fline);
		//String fs= Arrays.toString(fline);
		//Header hr=new Header(fline);

		// populate the header object with the String array containing the header names
		return hr;
	}


	@Override
	public void getDataRow() {

	}

	/*
	 * Implementation of getColumnType() method. To find out the data types, we will
	 * read the first line from the file and extract the field values from it. If a
	 * specific field value can be converted to Integer, the data type of that field
	 * will contain "java.lang.Integer", otherwise if it can be converted to Double,
	 * then the data type of that field will contain "java.lang.Double", otherwise,
	 * the field is to be treated as String.
	 * Note: Return Type of the method will be DataTypeDefinitions
	 */

	@Override
	public DataTypeDefinitions getColumnType() throws IOException {

		br.readLine();
		String sb=br.readLine();//fetch the second line of the .csv file
		String array[]=sb.split(",",-1);//split till the second last cell
		String[] datatype=new String[array.length-1];
		//System.out.println("iaug"+array.length);
		for (int i = 0; i <array.length-1; i++)
		{
			datatype[i] = findDataType(array[i]).getClass().getName();
		}

		DataTypeDefinitions dtd = new DataTypeDefinitions(datatype);
		return dtd;


	}

	private Object findDataType(String value)
	{
		if(value.isEmpty())
		{
			return " ";
		}
		try
		{
			int i=Integer.parseInt(value);
			return i;
		}
		catch (NumberFormatException ne)
		{
			return value;
		}
	}

	}
