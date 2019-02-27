package com.stackroute.datamunger.query.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/*There are total 4 DataMungerTest file:
 *
 * 1)DataMungerTestTask1.java file is for testing following 4 methods
 * a)getBaseQuery()  b)getFileName()  c)getOrderByClause()  d)getGroupByFields()
 *
 * Once you implement the above 4 methods,run DataMungerTestTask1.java
 *
 * 2)DataMungerTestTask2.java file is for testing following 2 methods
 * a)getFields() b) getAggregateFunctions()
 *
 * Once you implement the above 2 methods,run DataMungerTestTask2.java
 *
 * 3)DataMungerTestTask3.java file is for testing following 2 methods
 * a)getRestrictions()  b)getLogicalOperators()
 *
 * Once you implement the above 2 methods,run DataMungerTestTask3.java
 *
 * Once you implement all the methods run DataMungerTest.java.This test case consist of all
 * the test cases together.
 */

public class QueryParser {

	private QueryParameter queryParameter = new QueryParameter();

	/*
	 * This method will parse the queryString and will return the object of
	 * QueryParameter class
	 */
	public QueryParameter parseQuery(String queryString) {
		queryParameter.setFileName(getFileName(queryString));
		queryParameter.setBaseQuery(getBaseQuery(queryString));
		queryParameter.setOrderByFields(getOrderByFields(queryString));
		queryParameter.setGroupByFields(getGroupByFields(queryString));
		queryParameter.setFields(getFields(queryString));
		queryParameter.setLogicalOperators(getLogicalOperators(queryString));
		queryParameter.setRestrictions(getRestrictions(queryString));
		queryParameter.setAggregateFunctions(getAggregate(queryString));



		return queryParameter;
	}

	/*
	 * Extract the name of the file from the query. File name can be found after the
	 * "from" clause.
	 */

	public String getFileName(String queryString) {
		int pos1 = queryString.indexOf("from") +5;
		int pos2 = queryString.indexOf("csv") +3;
		queryString= queryString.substring(pos1,pos2);
		return queryString;
	}

	/*
	 *
	 * Extract the baseQuery from the query.This method is used to extract the
	 * baseQuery from the query string. BaseQuery contains from the beginning of the
	 * query till the where clause
	 */

	public String getBaseQuery(String queryString) {
		int pos1 = queryString.indexOf("from") + 5;
		int pos2 = queryString.indexOf(" ",pos1);
		if(pos2 == -1)
			pos2 = queryString.length();


		queryString = queryString.substring(0,pos2);
		// System.out.println(queryString);

		return queryString;
	}

	/*
	 * extract the order by fields from the query string. Please note that we will
	 * need to extract the field(s) after "order by" clause in the query, if at all
	 * the order by clause exists. For eg: select city,winner,team1,team2 from
	 * data/ipl.csv order by city from the query mentioned above, we need to extract
	 * "city". Please note that we can have more than one order by fields.
	 */
	public List<String> getOrderByFields(String queryString) {
		//int pos1 = queryString.indexOf("order by");
		if (!queryString.contains("order by")) {
			return null;
		}
		String [] arrofStr = queryString.split("order by")[1].trim().split(" ");



		//System.out.println(Arrays.toString(arrofStr));
		return Arrays.asList(arrofStr);

	}

	/*
	 * Extract the group by fields from the query string. Please note that we will
	 * need to extract the field(s) after "group by" clause in the query, if at all
	 * the group by clause exists. For eg: select city,max(win_by_runs) from
	 * data/ipl.csv group by city from the query mentioned above, we need to extract
	 * "city". Please note that we can have more than one group by fields.
	 */
	public List<String> getGroupByFields(String queryString) {
		String[] groupTempArray;
		String[] groupByArray;
		List<String> groupByFields = null;

		if (queryString.contains("group by")) {
			groupByFields = new ArrayList<String>();
			groupTempArray = queryString.trim().split(" group by ");
			if (groupTempArray[1].contains("order by")) {
				final String[] groupOrderBySplit = groupTempArray[1].trim().split(" order by");
				groupByArray = groupOrderBySplit[0].trim().split(",");
				if (groupByArray.length == 1) {
					groupByFields.add(groupByArray[0]);
				} else {
					for (int i = 0; i < groupByArray.length; i++) {
						groupByFields.add(groupByArray[i]);
					}
				}
			}

			else {
				groupByArray = groupTempArray[1].trim().split(",");
				if (groupByArray.length == 1) {
					groupByFields.add(groupByArray[0]);
				}

				else {
					for (int i = 0; i < groupByArray.length; i++) {
						groupByFields.add(groupByArray[i]);
					}
				}
			}
		}
		return groupByFields;

	}

	/*
	 * Extract the selected fields from the query string. Please note that we will
	 * need to extract the field(s) after "select" clause followed by a space from
	 * the query string. For eg: select city,win_by_runs from data/ipl.csv from the
	 * query mentioned above, we need to extract "city" and "win_by_runs". Please
	 * note that we might have a field containing name "from_date" or "from_hrs".
	 * Hence, consider this while parsing.
	 */
	public List<String> getFields(String queryString) {
		queryString = queryString.toLowerCase();
		String [] arrofStr = queryString.split(" ")[1].split(",");
		//System.out.println(arrofStr[0]);
		return Arrays.asList(arrofStr);
	}

	/*
	 * Extract the conditions from the query string(if exists). for each condition,
	 * we need to capture the following: 1. Name of field 2. condition 3. value
	 *
	 * For eg: select city,winner,team1,team2,player_of_match from data/ipl.csv
	 * where season >= 2008 or toss_decision != bat
	 *
	 * here, for the first condition, "season>=2008" we need to capture: 1. Name of
	 * field: season 2. condition: >= 3. value: 2008
	 *
	 * the query might contain multiple conditions separated by OR/AND operators.
	 * Please consider this while parsing the conditions.
	 *
	 */
	public List<Restriction> getRestrictions(String queryString) {
		List<Restriction> conditions = null;
		// queryString = queryString.toLowerCase();
		String[] whereQuery;
		// List<String> Cond = new ArrayList<String>();
		String tempString;
		String[] conditionQuery;
		String[] getCondition = null;
		if (queryString.contains("where")) {
			conditions = new ArrayList<Restriction>();
			whereQuery = queryString.trim().split("where ");
			if (whereQuery[1].contains("group by")) {
				conditionQuery = whereQuery[1].trim().split("group by");
				tempString = conditionQuery[0];
			} else if (whereQuery[1].contains("order by")) {
				conditionQuery = whereQuery[1].trim().split("order by");
				tempString = conditionQuery[0];
			} else {
				tempString = whereQuery[1];
			}
			getCondition = tempString.trim().split(" and | or ");
			// for (String s : getCondition) {
			// System.out.println(s.trim());
			// }
			String[] condSplit = null;
			if (getCondition != null) {
				for (int i = 0; i < getCondition.length; i++) {
					if (getCondition[i].contains("=")) {
						condSplit = getCondition[i].trim().split("\\W+");
						conditions.add(new Restriction(condSplit[0], condSplit[1], "="));
					} else if (getCondition[i].contains(">")) {
						condSplit = getCondition[i].trim().split("\\W+");
						conditions.add(new Restriction(condSplit[0], condSplit[1], ">"));
					} else if (getCondition[i].contains("<")) {
						condSplit = getCondition[i].trim().split("\\W+");
						conditions.add(new Restriction(condSplit[0], condSplit[1], "<"));
					}

				}
			}
			// Conditions.add(new Restriction(getCondition[0].substring(0, 6),
			// getCondition[0].substring(9, 12), getCondition[0].substring(7, 8)));

		}
		return conditions;

	}
	public String[] getSplitStrings(String queryString) {
		queryString=queryString.toLowerCase();
		String [] s = queryString.split(" ",-1);
		return s;


	}


	/*
	 * Extract the logical operators(AND/OR) from the query, if at all it is
	 * present. For eg: select city,winner,team1,team2,player_of_match from
	 * data/ipl.csv where season >= 2008 or toss_decision != bat and city =
	 * bangalore
	 *
	 * The query mentioned above in the example should return a List of Strings
	 * containing [or,and]
	 */

	public List<String> getLogicalOperators(String queryString) {
		int pos1 = queryString.indexOf("where");
		if (pos1 == -1) {
			return null;
		}       String [] arrofStr = queryString.split("where")[1].trim().split(" ");
		int i,count=0,j;
		for (i=0;i<arrofStr.length;i++) {
			if (arrofStr[i].equals("and") || arrofStr[i].equals("or")) {
				count++;
			}
		}
		String[] ray = new String[count] ;
		for (i=0,j=0 ;i<arrofStr.length;i++) {
			if (arrofStr[i].equals("and")) {
				ray[j] = arrofStr[i];
				j++;
			}
			else if (arrofStr[i].equals("or")) {
				ray[j] = arrofStr[i];
				j++;
			}
			else {
				continue;
			}
		}       System.out.println(Arrays.toString(ray));
		return Arrays.asList(ray);
	}

	/*
	 * Extract the aggregate functions from the query. The presence of the aggregate
	 * functions can determined if we have either "min" or "max" or "sum" or "count"
	 * or "avg" followed by opening braces"(" after "select" clause in the query
	 * string. in case it is present, then we will have to extract the same. For
	 * each aggregate functions, we need to know the following: 1. type of aggregate
	 * function(min/max/count/sum/avg) 2. field on which the aggregate function is
	 * being applied.
	 *
	 * Please note that more than one aggregate function can be present in a query.
	 *
	 *
	 */
	public List<AggregateFunction> getAggregate(String queryString) {
		final List<AggregateFunction> aggregate = new ArrayList<AggregateFunction>();
		// boolean state = false;
		// String getAggregate = "";
		final int selectIndex = queryString.toLowerCase(Locale.US).indexOf("select");
		final int fromIndex = queryString.toLowerCase(Locale.US).indexOf(" from");
		final String query = queryString.toLowerCase(Locale.US).substring(selectIndex + 7, fromIndex);
		String[] aggQuery = null;
		aggQuery = query.split(",");
		for (int i = 0; i < aggQuery.length; i++) {
			if (aggQuery[i].startsWith("max(") && aggQuery[i].endsWith(")")
					|| aggQuery[i].startsWith("min(") && aggQuery[i].endsWith(")")
					|| aggQuery[i].startsWith("avg(") && aggQuery[i].endsWith(")")
					|| aggQuery[i].startsWith("sum") && aggQuery[i].endsWith(")")) {
				aggregate.add(new AggregateFunction(aggQuery[i].substring(4, aggQuery[i].length() - 1),
						aggQuery[i].substring(0, 3)));
				// getAggregate += aggQuery[i] + " ";
				// state = true;
			} else if (aggQuery[i].startsWith("count(") && aggQuery[i].endsWith(")")) {
				aggregate.add(new AggregateFunction(aggQuery[i].substring(6, aggQuery[i].length() - 1),
						aggQuery[i].substring(0, 5)));
				// } else {
				// Aggregate = null;
				// }
			}

		}
		return aggregate;



	}
}