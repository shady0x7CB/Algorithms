package eg.edu.alexu.csd.oop.db;

import java.sql.SQLException;

import javax.xml.transform.TransformerException;

public class Main {

	public static void main(String[] args) throws SQLException, TransformerException {
		// TODO Auto-generated method stub

		
		//DBMS fe = new DBMS(); 
		//XMLReader f = new XMLReader();
		//XMLCreator k = new XMLCreator();
		String g = "1";
		Database db = new DBMS();
		System.out.println(db.executeStructureQuery("CREATE DATABASE TestDB"));
		System.out.println(db.executeStructureQuery("CREATE DATABASE TestDB1"));
		//Return : true
		System.out.println(db.executeStructureQuery("DROP DATABASE TestDB"));
		//Return : true
		System.out.println(db.executeStructureQuery("CREATE TABLE table_name1(column_name1 varchar, column_name2 int, column_name3 varchar)"));
		//System.out.println(db.executeStructureQuery("CREATE DATABASE TestDB"));
		//Return : true
	System.out.println(db.executeStructureQuery("CREATE TABLE table_name2(column_name1 varchar, column_name2 int, column_name3 varchar)"));
		//Return : true
		System.out.println(db.executeUpdateQuery("INSERT INTO table_name2(column_NAME1, COLUMN_name3, column_name2) VALUES ('value1', 'value3', 4)"));
//////		//Return : 1
		System.out.println(db.executeUpdateQuery("INSERT INTO table_name2(column_NAME1, COLUMN_name3, column_name2) VALUES ('value1', 'value3', 5)"));
////////		//Return : 1
		System.out.println(db.executeUpdateQuery("INSERT INTO table_name2(column_name1, COLUMN_NAME3, column_NAME2) VALUES ('value2', 'value4', 6)"));
////////		//Return : 1
		System.out.println(db.executeUpdateQuery("DELETE From table_name2  WHERE coLUmn_NAME1='VAluE1'"));
////////		//Return : 2
		System.out.println(db.executeUpdateQuery("UPDATE table_name2 SET column_name1='11111111', COLUMN_NAME2=22222222, column_name3='333333333' WHERE coLUmn_NAME2=4"));
////////		//Return : 0
		//System.out.println(fe.createDatabase("TestDB_1", false));
//		System.out.println(db.executeStructureQuery("CREATE DATABASE TestDB_2"));
//		System.out.println(db.executeStructureQuery("CREATE DATABASE TestDB_3"));
//		System.out.println(db.executeStructureQuery("CREATE DATABASE TestDB_4"));
		//System.out.println(g.matches("-?\\d+(\\.\\d+)?"));
		//System.out.println(fe.createDatabase("A", false));
		//System.out.println(fe.createDatabase("B", false));
		//System.out.println(fe.createDatabase("C", false));
		//System.out.println(fe.executeStructureQuery("CREATE DATABASE TestDB"));
		//System.out.println(fe.executeStructureQuery("CREATE TABLE table_name2(column_name1 varchar, column_name2 int, column_name3 varchar)"));
		//System.out.println(fe.executeStructureQuery("create database a_b"));
		//System.out.println(fe.executeUpdateQuery("LOGinsert into table_name13(column_name1, column_name3, column_name2) values ('value1', 'value3', 4)"));
		//System.out.println(fe.executeUpdateQuery("insert into table_name2 (column_name1, column_name2, column_name3) values ('value2', 5, 'value4')"));
		//System.out.println(fe.executeStructureQuery("create table students (id int, name varchar, grade int);"));
		//System.out.println(fe.executeUpdateQuery("insert into students (id , name, grade) values (5, 'ahmed', 5000)"));
		//System.out.println(fe.executeUpdateQuery("insert into aLi (ID, nAme, GrAdE) values (4, 'swqqey', 500)"));
		//System.out.println(fe.executeUpdateQuery("insert into students values (0, 'shady', 105)"));
		//System.out.println(fe.executeStructureQuery("drop table stuDeNts;"));
		
		//System.out.println(fe.executeStructureQuery("create table ali (width int, name varchar, length int, height int, father varchar, mother varchar);"));
		//System.out.println(fe.executeUpdateQuery("delete * from table_name8"));
		//System.out.println(fe.executeUpdateQuery("delete from students where name = 'o'"));
		//System.out.println(fe.executeUpdateQuery("UPDATE table_name8 SET column_name1='11111111', COLUMN_NAME2=22222222, column_name3='333333333' WHERE coLUmn_NAME3='VALUE3'"));
		//System.out.println(fe.executeStructureQuery("create table ali (id int, name varchar, grade int);"));
		//System.out.println(fe.executeUpdateQuery("insert into atudents values (9, '7oda', 100)"));
		//System.out.println(fe.executeUpdateQuery("insert into students values (10, 'shady', 110)"));
		//System.out.println(fe.executeStructureQuery("create table students (id int, name varchar, grade int);"));
		//System.out.println(fe.p.checkUpdateQuery("insert into students values (7, 'shddaa', 80)"));
		//System.out.println(fe.executeUpdateQuery("update studenTS set id = 9, grade = 99, name = 'o' where id = 5"));
		//k.insertAndUpdate(fe.p.getDatabaseName(), fe.p.getTableName(), fe.p.getInfo(), fe.p.getValues(), 5, fe.p.getOperator(), fe.p.getValue(), fe.p.getColumnName());
		
		//System.out.println(fe.executeStructureQuery("create table students (id int, name varchar, grade int);"));
		//System.out.println(fe.p.checkUpdateQuery("update students set id = 8, grade = 59, name = 'updated' where id = 4"));
		//System.out.println(fe.p.checkUpdateQuery("insert into students values (4, 'ahmed', 40)"));
		//System.out.println(fe.p.checkUpdateQuery("insert into students values (4, 'shady', 50)"));
		//System.out.println(fe.p.checkUpdateQuery("delete * from Atudents"));
		//System.out.println(k.insertAndUpdate(fe.p.getDatabaseName(), fe.p.getTableName(), fe.p.getInfo(), fe.p.getValues(), 3, fe.p.getOperator(), fe.p.getValue(), fe.p.getColumnName()));
		/*Object[][] h = fe.executeQuery("select * from students where id = 5");
		for(int i = 0 ; i < h.length ; i++){
			for(int j = 0 ; j < h[0].length ; j++){
				System.out.print(h[i][j] +" "+   h[i][j].getClass().getName());
			}
		System.out.printf("\n");
		}*/
		//System.out.println(fe.createDatabase("E", true));
		//System.out.println(fe.executeStructureQuery("creat table students (id int, grade int, name varchar);"));
		//System.out.println(fe.executeStructureQuery("drop database E"));
		//System.out.println(fe.executeStructureQuery("creat table students (id int, grade int, name varchar);"));
//		System.out.println(fe.executeStructureQuery("creat table shady (idw int, gradwe int, namew varchar);"));
////		p.checkCreationQuery("creat database hamada;");
//		System.out.println(p.getDatabaseName());
//		p.checkCreationQuery("drop database mahmoud;");
//		System.out.println(p.getDatabaseName());
//		p.checkCreationQuery("drop table 7ommos;");
//		System.out.println(p.getTableName());
//		p.checkCreationQuery("creat table students (id int, grade int, name varchar, hamada varchar, ahmed int);");
//		System.out.println(p.getTableName());
//		for(int i=0; p.getInfo()[i][0]!=null; i++){
//			System.out.println(p.getInfo()[i][0]+" "+p.getInfo()[i][1]);
//		}
		
		
		
//		p.checkSelectionQuery("sELEct * frOM teacher ; ");
//		p.checkSelectionQuery("sELEct id frOM student ; ");
//		p.checkSelectionQuery("sELEct id frOM student whERE grade < 5 ; ");
//		System.out.println(p.getTableName());
//		System.out.println(p.getColumnName());
//		System.out.println(p.getColumnName2());
//		System.out.println(p.getOperator());
//		System.out.println(p.getValue());
	}

}

