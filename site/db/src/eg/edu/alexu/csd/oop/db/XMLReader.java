package eg.edu.alexu.csd.oop.db;


import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLReader {

	public Object[][] Read(String databaseName, String fileName, int getArray, String colName, String operator, String value, String[][] values, String colName2) throws SQLException{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setValidating(true);
		Object[][] g = null;
		String t = System.getProperty("user.dir");
		try {
			
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(t+File.separator+ databaseName + File.separator +fileName +  ".xml");
			
			File check = new File(t+File.separator+ databaseName);
			
			String[] files = check.list();
			for(String u:files){
				if(u.toLowerCase().equals((fileName+".xml").toLowerCase())){
					fileName = u;
					System.out.println(u);
					System.out.println(fileName);
					break;
				}
			}
			String q = "";
			for(int i = 0 ; i < fileName.length() ;i++){
				if(fileName.charAt(i)=='.'){
					break;
				}
				q+=fileName.charAt(i);
			}
			fileName = q;
			NodeList rootElement = doc.getElementsByTagName(fileName);
			NodeList elements = rootElement.item(0).getChildNodes();
			LinkedList<LinkedList<Object>> data = new LinkedList<LinkedList<Object>>();
			
			for(int i = 0 ; i < elements.getLength() ; i++){
				data.add(new LinkedList<Object>());
			}
			for(int i = 0 ; i < elements.getLength() ; i++){
//				System.out.println(elements.item(i).getNodeName());
//				System.out.println(elements.item(i).getTextContent());
				String[] x = elements.item(i).getTextContent().trim().split("\\s+");
				LinkedList<String> h = new LinkedList<String>();
				for(String y : x){
					if(!y.equals(" ")){
						h.add(y);
					}
				}
				for(int j = 0 ; j <= h.size() ; j++){
					if(j == 0){
						data.get(i).add(elements.item(i).getNodeName());
						data.get(i).add(elements.item(i).getAttributes().item(0).getTextContent());//str.get(i).add(elements.item(i).getAttributes().item(0).getTextContent());
					}
					else{
						data.get(i).add(h.get(j-1));
					}
				}
			}
			
			if(data.get(0).size() == 3 && data.get(0).get(2).toString().replaceAll(" ", "").equals("")){
				throw new SQLException();
			}
			/*for(int i = 0 ; i < data.size() ; i++){
				System.out.println(data.get(i));
			}*/
			int size = data.get(0).size();
			for(int i = 1 ; i < data.size() ; i++){
				if(data.get(i).size() != size || data.get(i).size() == 2){
					g = new Object[1][1];
					return g;
				}
			}
			if(getArray == 1){
				g = new Object[data.get(0).size()-2][elements.getLength()];
				for(int i = 0 ; i < g[0].length ; i++){
					for(int j = 0 ; j < g.length ; j++){
						if(data.get(i).get(1).toString().toLowerCase().equals("int")){
							g[j][i] = Integer.parseInt(data.get(i).get(j+2).toString());
						}
						else if(data.get(i).get(1).toString().toLowerCase().equals("varchar")){
							g[j][i] = data.get(i).get(j+2).toString();
						}
					}
				}
			}
			else if(getArray == 2){
				
				for(int i = 0 ; i < data.size() ; i++){
				System.out.println(data.get(i));
				}
				XMLCreator xml = new XMLCreator();
				LinkedList<String> data2 = new LinkedList<String>();
				for(int i = 0 ; i < values.length && values[i][0] != null; i++){
					
					if(xml.isFound(data2, values[i][0].toLowerCase())){
						throw new SQLException();
					}
					data2.add(values[i][0]);
				}
				LinkedList<String> o = new LinkedList<String>();
				for(int i = 0 ; i < data.size() ; i++){
					o.add(data.get(i).get(0).toString());
				}
				g = new Object[data.get(0).size()-2][data2.size()];
				LinkedList<Integer> index = new LinkedList<Integer>();
				for(int i = 0 ; i < data2.size() ; i++){
					if(xml.isFound(o, data2.get(i)) ==false){
						g = new Object[1][1];
						g[0][0] = "";
						return g;
					}
				}
				for(int i = 0 ; i < data2.size() ; i++){
					for(int j = 0 ; j < data.size() ; j++){
						if(data2.get(i).toLowerCase().equals(data.get(j).get(0).toString().toLowerCase())){
							index.add(j);
						}
					}
				}
				if(index.size() == 0){
					g = new Object[1][1];
					g[0][0] = "";
					return g;
				}
				for(int i = 0 ; i < index.size() ; i++){
					for(int j = 0 ; j < g.length ;j++){
						if(data.get(index.get(i)).get(1).toString().toLowerCase().equals("int")){
							//System.out.println(Integer.parseInt(data.get(index.get(i)).get(j+2).toString()) + "iamo");
							g[j][i] = Integer.parseInt(data.get(index.get(i)).get(j+2).toString());
						}else{
							g[j][i] = data.get(index.get(i)).get(j+2).toString();
						}
					}
				}
				
				return g;
			}
			else if(getArray == 3){
				
				
				XMLCreator x = new XMLCreator();
				LinkedList<String> k1 = new LinkedList<String>();
				for(int i= 0 ; i < data.size() ; i++){
					k1.add(data.get(i).get(0).toString());
				}
				LinkedList<Object> k = new LinkedList<Object>();
				for(int l = 0 ; l < values.length && values[l][0]!=null;l++){
					k.add(values[l][0]);
				}
				/*for(int i = 0 ; i < data.size() ; i++){
					System.out.println(data.get(i) + " ok");
				}*/
				for(int p = 0 ; p < k.size() ;p++){
					if(x.isFound(k1, k.get(p).toString().toLowerCase()) == false){
						throw new SQLException();
						/*g = new Object[1][1];
						g[0][0] = "";
						return g;*/
					}	
				}
				
				LinkedList<Integer> k2 = new LinkedList<Integer>();
				for(int i = 0 ; i < k.size() ; i++){
					for(int j = 0 ; j < k1.size() ; j++){
						if(k.get(i).toString().toLowerCase().equals(k1.get(j).toLowerCase())){
							k2.add(j);
						}
					}
				}
				//System.out.println(k2);
				int y = -1 ;
				for(int i = 0 ; i < data.size();i++){
					if(colName2.toLowerCase().equals(data.get(i).get(0).toString().toLowerCase())){
						y = i;
					}
				}
				if(y == -1){
					throw new SQLException();
//					g = new Object[1][1];
//					g[0][0] = "";
//					return g;
				}
				LinkedList<Integer> p = new LinkedList<Integer>();
				if(!operator.equals("=") && !operator.equals("<") && !operator.equals(">")){
					throw new SQLException();
				}
				if(data.get(y).get(1).toString().toLowerCase().equals("int") && !value.matches("-?\\d+(\\.\\d+)?")){
					throw new SQLException();
				}
				if(data.get(y).get(1).toString().toLowerCase().equals("varchar") && value.matches("-?\\d+(\\.\\d+)?")){
					throw new SQLException();
				}
				if(operator.equals("=")){
					if(data.get(y).get(1).toString().toLowerCase().equals("int")){
						for(int i = 2 ; i < data.get(y).size() ; i++){
							if(value.equals(data.get(y).get(i))){
								p.add(i);
							}
						}
					}else{
						for(int i = 2 ; i < data.get(y).size() ; i++){
							if(value.equals(data.get(y).get(i))){
								p.add(i);
							}
						}
					}
					g = new Object[p.size()][k2.size()];
					for(int i = 0 ; i < k.size() ; i++){
						for(int m = 0 ; m < p.size() ; m++){
							if(data.get(k2.get(i)).get(1).toString().toLowerCase().equals("int")){
								g[m][i] = Integer.parseInt(data.get(k2.get(i)).get(p.get(m)).toString());
							}else{
								g[m][i] = data.get(k2.get(i)).get(p.get(m)).toString();
							}
						}
					}
					if(g.length == 0){
						g = new Object[1][1];
						
						return g;
					}
				}else if(operator.equals("<")){
					if(data.get(y).get(1).toString().toLowerCase().equals("int")){
						for(int i = 2 ; i < data.get(y).size() ; i++){
							if(Integer.parseInt(value)- Integer.parseInt(data.get(y).get(i).toString()) > 0 ){
								p.add(i);
							}
						}
					}else{
						for(int i = 2 ; i < data.get(y).size() ; i++){
							if(value.compareTo(data.get(y).get(i).toString()) > 0){
								p.add(i);
							}
						}
					}
					g = new Object[p.size()][k2.size()];
					for(int i = 0 ; i < k.size() ; i++){
						for(int m = 0 ; m < p.size() ; m++){
							if(data.get(k2.get(i)).get(1).toString().toLowerCase().equals("int")){
								g[m][i] = Integer.parseInt(data.get(k2.get(i)).get(p.get(m)).toString());
							}else{
								g[m][i] = data.get(k2.get(i)).get(p.get(m)).toString();
							}
						}
					}
					if(g.length == 0){
						g = new Object[1][1];
						
						return g;
					}
				}else if(operator.equals(">")){
					if(data.get(y).get(1).toString().toLowerCase().equals("int")){
						for(int i = 2 ; i < data.get(y).size() ; i++){
							if(Integer.parseInt(value)- Integer.parseInt(data.get(y).get(i).toString()) < 0 ){
								p.add(i);
							}
						}
					}else{
						for(int i = 2 ; i < data.get(y).size() ; i++){
							if(value.compareTo(data.get(y).get(i).toString()) < 0){
								p.add(i);
							}
						}
					}
					g = new Object[p.size()][k2.size()];
					for(int i = 0 ; i < k.size() ; i++){
						for(int m = 0 ; m < p.size() ; m++){
							if(data.get(k2.get(i)).get(1).toString().toLowerCase().equals("int")){
								g[m][i] = Integer.parseInt(data.get(k2.get(i)).get(p.get(m)).toString());
							}else{
								g[m][i] = data.get(k2.get(i)).get(p.get(m)).toString();
							}
						}
					}
				}
				if(g.length == 0){
					g = new Object[1][1];
					
					return g;
				}

			}else if(getArray == 4){
				LinkedList<Integer> index = new LinkedList<Integer>();
				
				if(operator.equals("=")){
					
					for(int i = 0 ; i < data.size() ; i++){
						if(colName2.toLowerCase().equals(data.get(i).get(0).toString().toLowerCase())){
							for(int j = 2 ; j < data.get(0).size() ; j++){
								if(value.toLowerCase().equals(data.get(i).get(j).toString().toLowerCase())){
									index.add(j);
								}
							}
						}
					}
					g = new Object[index.size()][data.size()];
					for(int i = 0 ; i < data.size(); i++){
						for(int j = 0 ; j < index.size() ; j++){
							if(data.get(i).get(1).toString().toLowerCase().equals("int")){
								g[j][i] = Integer.parseInt(data.get(i).get(index.get(j)).toString());
							}else{
								g[j][i] = data.get(i).get(index.get(j)).toString();
							}
						}
					}
					if(index.size() == 0){
						g = new Object[1][1];
						
						return g;
					}
					if(g.length == 0){
						g = new Object[1][1];
						
						return g;
					}
				}else if(operator.equals("<")){
					for(int i = 0 ; i < data.size() ; i++){
						if(colName2.toLowerCase().equals(data.get(i).get(0).toString().toLowerCase())){
							for(int j = 2 ; j < data.get(0).size() ; j++){
								if(data.get(i).get(1).toString().toLowerCase().equals("int")){
									if(Integer.parseInt(value) - Integer.parseInt(data.get(i).get(j).toString()) > 0){
										index.add(j);
									}
								}else{
									if(value.toLowerCase().compareTo(data.get(i).get(j).toString().toLowerCase()) > 0){
										index.add(j);
									}
								}
							}
						}
					}
					g = new Object[index.size()][data.size()];
					for(int i = 0 ; i < data.size(); i++){
						for(int j = 0 ; j < index.size() ; j++){
							if(data.get(i).get(1).toString().toLowerCase().equals("int")){
								g[j][i] = Integer.parseInt(data.get(i).get(index.get(j)).toString());
							}else{
								g[j][i] = data.get(i).get(index.get(j)).toString();
							}
						}
					}
					if(index.size() == 0){
						g = new Object[1][1];
						
						return g;
					}
					if(g.length == 0){
						g = new Object[1][1];
						
						return g;
					}
					
				}else if(operator.equals(">")){
					for(int i = 0 ; i < data.size() ; i++){
						if(colName2.toLowerCase().equals(data.get(i).get(0).toString().toLowerCase())){
							for(int j = 2 ; j < data.get(0).size() ; j++){
								if(data.get(i).get(1).toString().toLowerCase().equals("int")){
									if(Integer.parseInt(value) - Integer.parseInt(data.get(i).get(j).toString()) < 0){
										index.add(j);
									}
								}else{
									if(value.toLowerCase().compareTo(data.get(i).get(j).toString().toLowerCase()) < 0){
										index.add(j);
									}
								}
							}
						}
					}
					g = new Object[index.size()][data.size()];
					for(int i = 0 ; i < data.size(); i++){
						for(int j = 0 ; j < index.size() ; j++){
							if(data.get(i).get(1).toString().toLowerCase().equals("int")){
								g[j][i] = Integer.parseInt(data.get(i).get(index.get(j)).toString());
							}else{
								g[j][i] = data.get(i).get(index.get(j)).toString();
							}
						}
					}
					if(index.size() == 0){
						g = new Object[1][1];
						
						return g;
					}
					if(g.length == 0){
						g = new Object[1][1];
						
						return g;
					}
				}
				
			}
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			throw new SQLException();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			throw new SQLException();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new SQLException();
		}
		return g;
	}
}

//LinkedList<Object> k = new LinkedList<Object>();
//
//int j = 0;
//int n = 0;
//LinkedList<Integer> k1 = new LinkedList<Integer>();
//for(int i = 0 ; i < data.size() ; i++){
//	if(colName.equals(data.get(i).get(0).toString())){
//		found = true;
//		n = i;
//	}
//	if(colName2.equals(data.get(i).get(0).toString())){
//		found1 = true;
//		j = i;
//	}
//}
//
//if(found == true && found1 == true){
//	if(operator.equals("=")){
//		
//		for(int i = 0 ; i < data.get(0).size()-2 ; i++){
//			if(value.toLowerCase().equals(data.get(j).get(i+2).toString().toLowerCase())){
//				found2 = true;
//				if(data.get(n).get(1).toString().toLowerCase().equals("int")){
//					k.add(Integer.parseInt(data.get(n).get(i+2).toString()));
//				}else if(data.get(n).get(1).toString().toLowerCase().equals("varchar")){
//					k.add(data.get(n).get(i+2).toString());
//				}
//				
//			}
//		}
//		if(found2 == true){
//			g = new Object[k.size()][1];
//			for(int i = 0 ; i < k.size() ; i++){
//				g[i][0] = k.get(i);
//			}
//			return g;
//		}else{
//			g = new Object[1][1];
//			return g;
//		}
//	}else if(operator.equals("<")){
//		if(data.get(j).get(1).toString().toLowerCase().equals("int")){
//			for(int i = 0 ; i < data.get(0).size()-2 ; i++){
//				
//				if(Integer.parseInt(value) > Integer.parseInt(data.get(j).get(i+2).toString())){
//					found2 = true;
//					if(data.get(n).get(1).toString().toLowerCase().equals("int")){
//						k.add(Integer.parseInt(data.get(n).get(i+2).toString()));
//					}else if(data.get(n).get(1).toString().toLowerCase().equals("varchar")){
//						k.add(data.get(n).get(i+2).toString());
//					}
//					
//				}
//			}
//		}else{
//			for(int i = 0 ; i < data.get(0).size()-2 ; i++){
//				if(value.toLowerCase().compareTo(data.get(j).get(i+2).toString().toLowerCase()) > 0){
//					found2 = true;
//					if(data.get(n).get(1).toString().toLowerCase().equals("int")){
//						k.add(Integer.parseInt(data.get(n).get(i+2).toString()));
//					}else if(data.get(n).get(1).toString().toLowerCase().equals("varchar")){
//						k.add(data.get(n).get(i+2).toString());
//					}
//					
//				}
//			}
//		}
//		if(found2 == true){
//			g = new Object[k.size()][1];
//			for(int i = 0 ; i < k.size() ; i++){
//				g[i][0] = k.get(i);
//			}
//			return g;
//		}else{
//			g = new Object[1][1];
//			return g;
//		}
//	}else if(operator.equals(">")){
//		if(data.get(j).get(1).toString().toLowerCase().equals("int")){
//			for(int i = 0 ; i < data.get(0).size()-2 ; i++){
//				if(Integer.parseInt(value) < Integer.parseInt(data.get(j).get(i+2).toString())){
//					found2 = true;
//					if(data.get(n).get(1).toString().toLowerCase().equals("int")){
//						k.add(Integer.parseInt(data.get(n).get(i+2).toString()));
//					}else if(data.get(n).get(1).toString().toLowerCase().equals("varchar")){
//						k.add(data.get(n).get(i+2).toString());
//					}
//					
//				}
//			}
//		}else{
//			for(int i = 0 ; i < data.get(0).size()-2 ; i++){
//				if(value.toLowerCase().compareTo(data.get(j).get(i+2).toString().toLowerCase()) < 0){
//					found2 = true;
//					if(data.get(n).get(1).toString().toLowerCase().equals("int")){
//						k.add(Integer.parseInt(data.get(n).get(i+2).toString()));
//					}else if(data.get(n).get(1).toString().toLowerCase().equals("varchar")){
//						k.add(data.get(n).get(i+2).toString());
//					}
//					
//				}
//			}
//		}
//		if(found2 == true){
//			g = new Object[k.size()][1];
//			for(int i = 0 ; i < k.size() ; i++){
//				g[i][0] = k.get(i);
//			}
//			return g;
//		}else{
//			g = new Object[1][1];
//			return g;
//		}
//	}else if(operator.equals("<=")){
//		if(data.get(j).get(1).toString().toLowerCase().equals("int")){
//			for(int i = 0 ; i < data.get(0).size()-2 ; i++){
//				if(Integer.parseInt(value) >= Integer.parseInt(data.get(j).get(i+2).toString())){
//					found2 = true;
//					if(data.get(n).get(1).toString().toLowerCase().equals("int")){
//						k.add(Integer.parseInt(data.get(n).get(i+2).toString()));
//					}else if(data.get(n).get(1).toString().toLowerCase().equals("varchar")){
//						k.add(data.get(n).get(i+2).toString());
//					}
//					
//				}
//			}
//		}else{
//			for(int i = 0 ; i < data.get(0).size()-2 ; i++){
//				if(value.toLowerCase().compareTo(data.get(j).get(i+2).toString().toLowerCase()) >= 0){
//					found2 = true;
//					if(data.get(n).get(1).toString().toLowerCase().equals("int")){
//						k.add(Integer.parseInt(data.get(n).get(i+2).toString()));
//					}else if(data.get(n).get(1).toString().toLowerCase().equals("varchar")){
//						k.add(data.get(n).get(i+2).toString());
//					}
//					
//				}
//			}
//		}
//		if(found2 == true){
//			g = new Object[k.size()][1];
//			for(int i = 0 ; i < k.size() ; i++){
//				g[i][0] = k.get(i);
//			}
//			return g;
//		}else{
//			g = new Object[1][1];
//			return g;
//		}
//	}else if(operator.equals(">=")){
//		if(data.get(j).get(1).toString().toLowerCase().equals("int")){
//			for(int i = 0 ; i < data.get(0).size()-2 ; i++){
//				if(Integer.parseInt(value) <= Integer.parseInt(data.get(j).get(i+2).toString())){
//					found2 = true;
//					if(data.get(n).get(1).toString().toLowerCase().equals("int")){
//						k.add(Integer.parseInt(data.get(n).get(i+2).toString()));
//					}else if(data.get(n).get(1).toString().toLowerCase().equals("varchar")){
//						k.add(data.get(n).get(i+2).toString());
//					}
//					
//				}
//			}
//		}else{
//			for(int i = 0 ; i < data.get(0).size()-2 ; i++){
//				if(value.toLowerCase().compareTo(data.get(j).get(i+2).toString().toLowerCase()) <= 0){
//					found2 = true;
//					if(data.get(n).get(1).toString().toLowerCase().equals("int")){
//						k.add(Integer.parseInt(data.get(n).get(i+2).toString()));
//					}else if(data.get(n).get(1).toString().toLowerCase().equals("varchar")){
//						k.add(data.get(n).get(i+2).toString());
//					}
//					
//				}
//			}
//		}
//		if(found2 == true){
//			g = new Object[k.size()][1];
//			for(int i = 0 ; i < k.size() ; i++){
//				g[i][0] = k.get(i);
//			}
//			return g;
//		}else{
//			g = new Object[1][1];
//			return g;
//		}
//	}
//}
//else{
//	g = new Object[1][1];
//	return g;
//}