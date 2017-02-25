package eg.edu.alexu.csd.oop.db;


public class Parser {
	
	private String database, tableName, columnName, columnName2, operator, val;
	private String[][] info, values;
	
	public int checkCreationQuery(String query){
		if(query.matches("[cC][rR][eE][aA][tT][eE]\\s+[dD][aA][tT][aA][bB][aA][sS][eE]\\s+\\w+\\s*;?\\s*")){
			parseDBName(query);
			return 1;  //to create database
		}
		else if(query.matches("[dD][rR][oO][pP]\\s+[dD][aA][tT][aA][bB][aA][sS][eE]\\s+\\w+\\s*;?\\s*")){
			parseDBName(query);
			return 2;    //to drop database
		}
		else if(query.matches("[cC][rR][eE][aA][tT][eE]\\s+[tT][aA][bB][lL][eE]\\s+\\w+\\s*" +
				"\\((\\s*\\w+\\s+([iI][nN][tT]|[vV][aA][rR][cC][hH][aA][rR])\\s*\\,\\s*)*" +
				"\\s*\\w+\\s+([iI][nN][tT]|[vV][aA][rR][cC][hH][aA][rR])\\s*\\)\\s*;?\\s*")){
			query = query.replaceAll("[(,;)]", " ");
			String[] words = query.split("\\s+");
			tableName = words[2];
			String[][] column = new String[100][2];
			int j=0;
			for(int i=3; i<words.length; i+=2){
				column[j][0] = words[i];
				column[j][1] = words[i+1];
				j++;
			}
			this.info = column;
			//this.values = column;
			return 3;    //to create table
		}
		else if(query.matches("[dD][rR][oO][pP]\\s+[tT][aA][bB][lL][eE]\\s+\\w+\\s*;?\\s*")){
			query = query.replaceAll("[;]", " ");
			String[] words = query.split("\\s+");
			tableName = words[2];
			return 4;   //to drop table
		}
		else throw new RuntimeException();
	}
	
	public int checkSelectionQuery(String query){
		if(query.matches("[sS][eE][lL][eE][cC][tT]\\s+\\*\\s+[fF][rR][oO][mM]\\s+\\w+\\s*;?\\s*")){
			query = query.replaceAll("[;]", " ");
			String[] words = query.split("\\s+");
			tableName = words[3];
			return 1;   //to execute the whole table
		}
		else if(query.matches("[sS][eE][lL][eE][cC][tT]\\s+(\\w+\\s*,\\s*)*\\w+\\s+[fF][rR][oO][mM]\\s+\\w+\\s*;?\\s*")){
			query = query.replaceAll("[;]", " ");
			String[] words = query.split("\\s+");
			String columnStr = query.substring(7, query.indexOf(words[words.length-2]));
			parseColStr(columnStr);
			//this.info = values;
			tableName = words[words.length-1];
			return 2;   //to execute some columns from table
		}
		else if(query.matches("[sS][eE][lL][eE][cC][tT]\\s+(\\w+\\s*,\\s*)*\\w+\\s+[fF][rR][oO][mM]\\s+\\w+\\s+" +
				"[wW][hH][eE][rR][eE]\\s+\\w+\\s*(=|<|>|>=|<=)\\s*(\\d+|\\'.+\\')\\s*;?\\s*")){
			query = query.replaceAll("[;]", " ");
			String[] words = query.split("\\s+");
			int end = getWhereIndex(words);
			String whereStr = query.substring(query.indexOf(words[end])+6);
			parseWhereStr(whereStr, false);
			String columnStr = query.substring(7, query.indexOf(words[end-2]));
			parseColStr(columnStr);
			//this.info = values;
			tableName = words[end-1];
			return 3;   //to execute values from some columns
		}else if(query.matches("[sS][eE][lL][eE][cC][tT]\\s+\\*\\s+[fF][rR][oO][mM]\\s+\\w+\\s+" +
				"[wW][hH][eE][rR][eE]\\s+\\w+\\s*(=|<|>|>=|<=)\\s*(\\d+|\\'.+\\')\\s*;?\\s*")){
			query = query.replaceAll("[;]", " ");
			String[] words = query.split("\\s+");
			String whereStr = query.substring(query.indexOf(words[4])+6);
			parseWhereStr(whereStr, false);
			tableName = words[3];
			return 4;   //to execute values from the table
		}
		else throw new RuntimeException();
	}
	
	public int checkUpdateQuery(String query){
		if(query.matches("[iI][nN][sS][eE][rR][tT]\\s+[iI][nN][tT][oO]\\s+\\w+\\s+[vV][aA][lL][uU][eE][sS]" +
				"\\s*\\((\\s*(\\d+|\\'.+\\')\\s*\\,\\s*)*\\s*(\\d+|\\'.+\\')\\s*\\)\\s*;?\\s*")){
			query = query.replaceAll("[;]", " ");
			String[] words = query.split("\\s+");
			tableName = words[2];
			String valStr = query.substring(query.indexOf("(")+1, query.indexOf(")")); 
			values = new String[100][1];
			parseValString(valStr);
			//this.info = values;
			return 1;    //to insert values respectively
		}
		else if(query.matches("[iI][nN][sS][eE][rR][tT]\\s+[iI][nN][tT][oO]\\s+(\\*\\s+)?\\w+\\s*" +
				"\\((\\s*\\w+\\s*\\,\\s*)*\\s*\\w+\\s*\\)\\s*[vV][aA][lL][uU][eE][sS]" +
				"\\s*\\((\\s*(\\d+|\\'.+\\')\\s*\\,\\s*)*\\s*(\\d+|\\'.+\\')\\s*\\)\\s*;?\\s*")){
			query = query.replaceAll("[*]", "");
			query = query.replaceAll("[(]", " (");
			query = query.replaceAll("[;]", " ");
			String[] words = query.split("\\s+");
			tableName = words[2];
			values = new String[100][2];
			String colStr = query.substring(query.indexOf("(")+1, query.indexOf(")"));
			String[] colArr = colStr.split("\\s*,\\s*");
			int j=0;
			for(String str : colArr){
				values[j][1] = str.trim();
				j++;
			}
			query = query.replaceFirst("[(]", "");
			query = query.replaceFirst("[)]", "");
			String valStr = query.substring(query.indexOf("(")+1, query.indexOf(")"));
			int i = parseValString(valStr);
			if(i!=j) throw new RuntimeException();
			//this.info = values;
			return 2;    //to insert values in the specified column
		}
		else if(query.matches("[dD][eE][lL][eE][tT][eE]\\s+(\\*\\s+)?[fF][rR][oO][mM]\\s+\\w+\\s*;?\\s*")){
			query = query.replaceAll("[;]", " ");
			String[] words = query.split("\\s+");
			if(words[1].equals("*")) tableName = words[3];
			else tableName = words[2];
			return 3;   //to delete the whole table
		}
		else if(query.matches("[dD][eE][lL][eE][tT][eE]\\s+[fF][rR][oO][mM]\\s+\\w+\\s+" +
				"[wW][hH][eE][rR][eE]\\s+\\w+\\s*(=|<|>|>=|<=)\\s*(\\d+|\\'.+\\')\\s*;?\\s*")){
			query = query.replaceAll("[;]", " ");
			String[] words = query.split("\\s+");
			tableName = words[2];
			String whereStr = query.substring(query.indexOf(words[3])+6);
			parseWhereStr(whereStr, true);
			return 4;   //to delete row or more
		}
		else if(query.matches("[uU][pP][dD][aA][tT][eE]\\s+\\w+\\s+[sS][eE][tT]\\s+" +
				"(\\w+\\s*=\\s*(\\d+|\\'.+\\')\\s*,\\s*)*\\w+\\s*=\\s*(\\d+|\\'.+\\')\\s+" +
				"[wW][hH][eE][rR][eE]\\s+\\w+\\s*(=|<|>|>=|<=)\\s*(\\d+|\\'.+\\')\\s*;?\\s*")){
			
			values = new String[100][2];
			query = query.replaceAll("[;]", " ");
			String[] words = query.split("\\s+");
			tableName = words[1];
			int end = getWhereIndex(words);
			String setStr = query.substring(query.indexOf(words[2])+3, query.indexOf(words[end]));
			parseSetStr(setStr);
			//this.info = values;
			String whereStr = query.substring(query.indexOf(words[end])+6);
			parseWhereStr(whereStr, true);
			return 5;   //to update row or more
		}else if(query.matches("[uU][pP][dD][aA][tT][eE]\\s+\\w+\\s+[sS][eE][tT]\\s+" +
				"(\\w+\\s*=\\s*(\\d+|\\'.+\\')\\s*,\\s*)*\\w+\\s*=\\s*(\\d+|\\'.+\\')\\s*;?\\s*")){
			values = new String[100][2];
			query = query.replaceAll("[;]", " ");
			String[] words = query.split("\\s+");
			tableName = words[1];
			String setStr = query.substring(query.indexOf(words[2])+3);
			parseSetStr(setStr);
			//this.info = values;
			return 6;
		}
		else throw new RuntimeException();
	}
	private void parseDBName(String query){
		query = query.replaceAll("[;]", " ");
		String[] words = query.split("\\s+");
		database = words[2];
	}
	private int parseValString(String valStr){
		String[] valArr = valStr.split("\\s*,\\s*"); 
		int i=0;
		for(String str : valArr){
			if(str.contains("'"))
				values[i][0] = str.replaceAll("\\s*'\\s*", "");
			else
				values[i][0] = str.trim();
			i++;
		}
		return i;
	}
	private void parseColStr(String columnStr){
		String[] columns = columnStr.split("\\s*,\\s*");
		values = new String[100][1];
		int i=0;
		for(String str : columns){
			values[i][0] = str.trim();
			i++;
		}
	}
	private void parseSetStr(String setStr){
		String[] setArr = setStr.split("\\s*,\\s*");
		int i=0;
		for(String str : setArr){
			String[] ele = str.split("=");
			values[i][0] = ele[0].trim();
			if(ele[1].contains("'"))
				values[i][1] = ele[1].replaceAll("\\s*'\\s*", "");
			else
				values[i][1] = ele[1].trim();
			i++;
		}
	}
	private void parseWhereStr(String whereStr, boolean oneOrTwo){
		operator = whereStr.replaceAll("['a-zA-z0-9]", "").trim();
		String[] whereArr = whereStr.split(operator);
		if(oneOrTwo) columnName = whereArr[0].trim();
		else columnName2 = whereArr[0].trim();
		if(whereArr[1].contains("'"))
			val = whereArr[1].replaceAll("\\s*'\\s*", "");
		else
			val = whereArr[1].trim();
	}
	private int getWhereIndex(String[] words){
		int end=0;
		if(words[words.length-4].compareToIgnoreCase("WHERE")==0){
			end = words.length-4;
		}
		else if(words[words.length-3].compareToIgnoreCase("WHERE")==0){
			end = words.length-3;
		}
		else if(words[words.length-2].compareToIgnoreCase("WHERE")==0){
			end = words.length-2;
		}
		return end;
	}
	public String[][] getValues(){
		return this.values;
	}
	public String getTableName(){
		return this.tableName;
	}
	public String getColumnName(){
		return this.columnName;
	}
	public String getColumnName2(){
		return this.columnName2;
	}
	public String getOperator(){
		return this.operator;
	}
	public String getValue(){
		return this.val;
	}
	public String getDatabaseName(){
		return this.database;
	}
	public String[][] getInfo(){
		return this.info;
	}
}