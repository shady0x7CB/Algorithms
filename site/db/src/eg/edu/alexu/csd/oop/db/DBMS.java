package eg.edu.alexu.csd.oop.db;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.StandardOpenOption;
import java.sql.SQLException;
import java.util.Formatter;
import java.util.LinkedList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;



public class DBMS implements Database{

	/*private static void log(String str, boolean delete) {
		try {
			if (delete)new File("/debug/hodaa.log").delete();
			java.nio.file.Files.write(java.nio.file.Paths.get("/debug/hodaa.log"), str.getBytes(),
					(new File("/debug/hodaa.log").exists() ? StandardOpenOption.APPEND : StandardOpenOption.CREATE));
		} catch (Throwable e1) {
			e1.printStackTrace();
		}
	}*/
	LinkedList<String> databases = new LinkedList<String>();
	Parser p = new Parser();
	XMLCreator xmlc = new XMLCreator();
	XMLReader xmlr = new XMLReader();
	@Override
	public String createDatabase(String databaseName, boolean dropIfExists) {
		
		//p.setDatabaseName(databaseName.toLowerCase());
		String h = System.getProperty("user.dir");
		h+=File.separator;
		
		//String o = System.getProperty("user.dir") ;
		//File f = new File(o);
		
//		String[] g = f.list();
//		for(String y : g){
//			if(y.toLowerCase().equals(databaseName.toLowerCase())){
//				
//				databaseName = y.toLowerCase();
//				
//				//throw new RuntimeException();
//			}
//		}
		h+=databaseName.toLowerCase();
		File folder = new File(h);
		
		if(dropIfExists == true){
			try{
				if(folder.exists()){
					for(String temp : folder.list()){
						File tempo = new File(System.getProperty("user.dir") + File.separator + databaseName + File.separator + temp);
						tempo.delete();
					}
					folder.delete();
					
					if(true){
						if(!isFound(databases, databaseName.toLowerCase())){
							databases.add(databaseName.toLowerCase());
						}
						folder.mkdir();
					}
				}else{
					databases.add(databaseName.toLowerCase());
					folder.mkdir();
				}
			}catch(Exception e){}
		}else{
			try{
				if(folder.exists()){
				}else{
					folder.mkdir();
				}
			}catch(Exception e){}
		}
		
		return folder.getAbsolutePath();
	}

//	private static void log(String str, boolean delete) {
//		try {
//			if (delete)new File("/debug/mashady1.log").delete();
//			java.nio.file.Files.write(java.nio.file.Paths.get("/debug/mashady1.log"), str.getBytes(),
//					(new File("/debug/mashady1.log").exists() ? StandardOpenOption.APPEND : StandardOpenOption.CREATE));
//		} catch (Throwable e1) {
//			e1.printStackTrace();
//		}
//	}
	private boolean isFound(LinkedList<String> x, String y){
		for(int i = 0 ; i < x.size() ; i++){
			if(y.toLowerCase().equals(x.get(i).toLowerCase())){
				return true;
			}
		}
		return false;
	}
	@Override
	public boolean executeStructureQuery(String query) throws SQLException {
		try{
			p.checkCreationQuery(query);
		}catch(RuntimeException e){
			System.out.println("kollo t7t el sytara");
			throw new SQLException();
			}
		
		if(p.checkCreationQuery(query) == 1){
			//log("****" + query + "*****", false);
			String path = System.getProperty("user.dir") + File.separator + p.getDatabaseName().toLowerCase();
			File folder = new File(path);
			if(folder.exists()){
				if(!isFound(databases, p.getDatabaseName().toLowerCase())){
					databases.add(p.getDatabaseName().toLowerCase());
				}
				return true;
			}else{
				databases.add(p.getDatabaseName().toLowerCase());
				folder.mkdir();
				return true;
			}
			
			
		}
		else if(p.checkCreationQuery(query) == 2){
			String path = System.getProperty("user.dir") + File.separator + p.getDatabaseName().toLowerCase();
			File folder = new File(path);
			if(folder.exists()){
				for(String temp : folder.list()){
					File tempo = new File(System.getProperty("user.dir") + File.separator + p.getDatabaseName().toLowerCase() + File.separator + temp);
					tempo.delete();
				}
				folder.delete();
				databases.remove(p.getDatabaseName().toLowerCase());
				System.out.println(databases);
				return true;
			}else{
				return false;
			}
		}
		else if(p.checkCreationQuery(query) == 3){
			
			if(databases.size() == 0){
				throw new SQLException();
 			}
			System.out.println(databases.getLast());
			String h = System.getProperty("user.dir");
			h+=File.separator;
			h+=databases.getLast();
			File file = new File(h + File.separator + p.getTableName().toLowerCase() + ".xml");
			
			if(file.exists()){
				return false;
			}
			else{
				
				try {
					xmlc.dtd(databases.getLast(), p.getTableName(), p.getInfo());
					xmlc.createFile(databases.getLast(), p.getInfo(), p.getTableName());
					
				} catch (FileNotFoundException | ParserConfigurationException
						| TransformerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return true;
			}
			
		}
		else if(p.checkCreationQuery(query) == 4){
			String h = System.getProperty("user.dir");
			h+=File.separator;
			h+=databases.getLast();
			File file = new File(h + File.separator + p.getTableName() + ".xml");
			File file1 = new File(h + File.separator + p.getTableName() + ".dtd");
			if(file.exists()){
				file.delete();
				file1.delete();
				return true;
			}
			else{
				return false;
			}
		}
		return false;
	}

	@Override
	public Object[][] executeQuery(String query) throws SQLException {
		// TODO Auto-generated method stub
		try{
			p.checkSelectionQuery(query);
		}catch(Exception e){
			//log(query, false);
			throw new SQLException();}
		

		
		return xmlr.Read(databases.getLast(), p.getTableName(), p.checkSelectionQuery(query),
				p.getColumnName(), p.getOperator(), p.getValue(),
				p.getValues(), p.getColumnName2());
	}

	@Override
	public int executeUpdateQuery(String query) throws SQLException {
		// TODO Auto-generated method stub
		//throw new RuntimeException("");
		
		//log(query, false);
		//log(p.getTableName(), false);
		try{
			p.checkUpdateQuery(query);
		}catch(Exception e){
			//throw new RuntimeException();
			//log(query + "*------------------------------------*", false);
			throw new SQLException();
		}
		int i = 0;
		try {
			
			i =  xmlc.insertAndUpdate(databases.getLast(), p.getTableName(), p.getInfo(),
					p.getValues(), p.checkUpdateQuery(query), p.getOperator(), p.getValue(), p.getColumnName());
			
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			throw new SQLException();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new SQLException();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			throw new SQLException();
		}
		return i;
	}


}