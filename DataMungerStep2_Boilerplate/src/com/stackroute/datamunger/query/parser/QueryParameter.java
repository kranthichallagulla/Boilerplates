package com.stackroute.datamunger.query.parser;

import java.util.ArrayList;
import java.util.List;

/*
 * This class will contain the elements of the parsed Query String such as conditions,
 * logical operators,aggregate functions, file name, fields group by fields, order by
 * fields, Query Type
 * */

public class QueryParameter {
	private String queryString;
	private String queryType;
	private List<String> fields, logicalOperators, groupByFields, orderByFields;
	private List<Restriction> restrictions;
	private List<AggregateFunction> aggregateFunctions;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	private String fileName;

	public String getBaseQuery() {
		return baseQuery;
	}

	public void setBaseQuery(String baseQuery) {
		this.baseQuery = baseQuery;
	}

	private String baseQuery;


	public String getQueryString()
	{
		return queryString;
	}

	public void setQueryString(String queryString)
	{
		this.queryString = queryString;
	}

	public String getQueryType()
	{
		return queryType;
	}

	public void setQueryType(String queryType)
	{
		this.queryType = queryType;
	}

	public List<String> getFields()
	{
		return fields;
	}

	public void setFields(List<String> fields)
	{
		this.fields = fields;
	}

	public List<String> getLogicalOperators()
	{
		return logicalOperators;
	}

	public void setLogicalOperators(List<String> logicalOperators)
	{
		this.logicalOperators = logicalOperators;
	}

	public List<String> getGroupByFields()
	{
		return groupByFields;
	}

	public void setGroupByFields(List<String> groupByFields)
	{
		this.groupByFields = groupByFields;
	}

	public List<String> getOrderByFields()
	{
		return orderByFields;
	}

	public void setOrderByFields(List<String> orderByFields)
	{
		this.orderByFields = orderByFields;
	}

	public List<Restriction> getRestrictions() {
		return restrictions;
	}

	public void setRestrictions(List<Restriction> restrictions)
	{
		this.restrictions = restrictions;
	}

	public List<AggregateFunction> getAggregateFunctions()
	{
		return aggregateFunctions;
	}

	public void setAggregateFunctions(List<AggregateFunction> aggregateFunctions)

	{
		this.aggregateFunctions = aggregateFunctions;
	}


}