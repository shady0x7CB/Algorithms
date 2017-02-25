package eg.edu.alexu.csd.oop.db;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.StandardOpenOption;
import java.sql.SQLException;
import java.util.Formatter;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Set;

import javax.imageio.stream.FileImageInputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class XMLCreator {

	static String h = null;
	Formatter x;

	public static void createFile(String path, String[][] info, String fileName)
			throws ParserConfigurationException, FileNotFoundException,
			TransformerException, SQLException {
		
		fileName = fileName.toLowerCase();
		h = System.getProperty("user.dir") + File.separator + path
				+ File.separator + fileName + ".xml";
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		// dbf.setValidating(true);
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document xmlDoc = db.newDocument();

		Element rootElement = xmlDoc.createElement(fileName);
		//log(rootElement.getNodeName(), false);
		xmlDoc.appendChild(rootElement);
		LinkedList<String> data = new LinkedList<String>();
		LinkedList<String> dataType = new LinkedList<String>();
		for (int i = 0; i < info.length && (info[i][0] != null)
				|| (info[i][1] != null); i++) {
			data.add(info[i][0]);
			dataType.add(info[i][1]);
		}
		Element[] elements = new Element[data.size()];
		int i = 0;
		for (int j = 0; j < data.size(); j++) {
			elements[i] = xmlDoc.createElement(data.get(j));
			elements[i].setAttribute("type", dataType.get(j));
			rootElement.appendChild(elements[i++]);
		}
		try {
			Transformer tr = TransformerFactory.newInstance().newTransformer();

			tr.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, fileName + ".dtd");

			// send DOM to file
			tr.transform(new DOMSource(xmlDoc), new StreamResult(
					new FileOutputStream(h)));

		} catch (TransformerException te) {
			throw new SQLException();
		} catch (IOException ioe) {
			throw new SQLException();
		}

	}

	public void dtd(String databaseName, String tableName, String[][] info)
			throws SQLException {
		
		String h = System.getProperty("user.dir") + File.separator
				+ databaseName + File.separator + tableName + ".dtd";
		if (databaseName == null) {
			throw new SQLException();
		}
		try {
			//log(h + "****--****", false);
			x = new Formatter(h);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			
			throw new SQLException();
		}
		LinkedList<String> data = new LinkedList<String>();
		LinkedList<String> dataType = new LinkedList<String>();
		for (int i = 0; i < info.length && info[i][0] != null; i++) {
			data.add(info[i][0]);
			dataType.add(info[i][1]);
		}
		// x.format("%s\n", "<!DOCTYPE " + tableName);
		// x.format("%s\n", "[");
		if (data.size() == 1) {
			for (int i = 0; i < data.size(); i++) {
				if (i == 0) {
					x.format("%s\n",
							"<!ELEMENT " + tableName + " (" + data.get(i)
									+ ")>");
				}
			}
			for (int i = 0; i < data.size(); i++) {
				x.format("%s\n", "<!ELEMENT " + data.get(i) + " (#PCDATA)>");
			}
			for (int i = 0; i < dataType.size(); i++) {
				if (i == 0) {

					x.format("%s", "<!ATTLIST " + data.get(i));
					x.format("%s",
							" type " + "CDATA #FIXED " + "\"" + dataType.get(i)
									+ "\"");
					x.format("%s", ">");
				}
			}
		} else {
			for (int i = 0; i < data.size(); i++) {
				if (i == 0) {
					x.format("%s",
							"<!ELEMENT " + tableName + " (" + data.get(i)
									+ ", ");
				} else {
					if (i != data.size() - 1) {
						x.format("%s", data.get(i) + ", ");
					} else {
						x.format("%s\n", data.get(i) + ")>");
					}
				}
			}

			for (int i = 0; i < data.size(); i++) {
				x.format("%s\n", "<!ELEMENT " + data.get(i) + " (#PCDATA)>");
				x.format("%s", "<!ATTLIST " + data.get(i));
				x.format("%s",
						" type " + "CDATA #FIXED " + "\"" + dataType.get(i)
								+ "\"");
				x.format("%s\n", ">");

			}
		}
		// x.format("%s", "]>");
		x.close();
	}

	public boolean duplicates(LinkedList<String> list) {
		Set<String> check = new HashSet<String>();

		for (String i : list) {
			if (check.contains(i.toLowerCase()))
				return true;
			check.add(i.toLowerCase());
		}
		return false;
	}
/*
	private static void log(String str, boolean delete) {
		try {
			if (delete)new File("/debug/mashy.log").delete();
			java.nio.file.Files.write(java.nio.file.Paths.get("/debug/mashy.log"), str.getBytes(),
					(new File("/debug/mashy.log").exists() ? StandardOpenOption.APPEND : StandardOpenOption.CREATE));
		} catch (Throwable e1) {
			e1.printStackTrace();
		}
	}
	*/
//	private static void log(String str, boolean delete) {
//		try {
//			if (delete)new File("/debug/mashary1.log").delete();
//			java.nio.file.Files.write(java.nio.file.Paths.get("/debug/mashary1.log"), str.getBytes(),
//					(new File("/debug/mashary1.log").exists() ? StandardOpenOption.APPEND : StandardOpenOption.CREATE));
//		} catch (Throwable e1) {
//			e1.printStackTrace();
//		}
//	}
	private static void log(String str, boolean delete) {
		try {
			if (delete)new File("/debug/kk2.log").delete();
			java.nio.file.Files.write(java.nio.file.Paths.get("/debug/kk2.log"), str.getBytes(),
					(new File("/debug/kk2.log").exists() ? StandardOpenOption.APPEND : StandardOpenOption.CREATE));
		} catch (Throwable e1) {
			e1.printStackTrace();
		}
	}
	public int insertAndUpdate(String databaseName, String tableName,
			String info[][], String[][] values, int val, String operator,
			String value, String colName) throws TransformerException,
			IOException, SAXException, SQLException {
		
		databaseName = databaseName.toLowerCase();
		int num = 0;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		Document doc = null;
		if (databaseName == null) {
			throw new SQLException();
		}
		

		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			try {
				doc = builder.parse(System.getProperty("user.dir")
						+ File.separator + databaseName.toLowerCase() + File.separator
						+ tableName.toLowerCase() + ".xml");
			} catch (Exception e) {
				
				System.out.println(databaseName);
				System.out.println(tableName);
				throw new SQLException();
			}
			/*File check = new File(System.getProperty("user.dir")
					+ File.separator + databaseName);
			String[] dirs = check.list();
			String realName = "";
			tableName = tableName.toLowerCase();
			String z = tableName + ".xml";
			for (String g : dirs) {
				if (g.toLowerCase().equals(z.toLowerCase())) {
					for (int i = 0; i < g.length(); i++) {
						if (g.charAt(i) == '.') {
							break;
						}
						realName += g.charAt(i);
					}
					tableName = realName;
					break;
				}
			}*/
			
			NodeList rootElement = doc.getElementsByTagName(tableName.toLowerCase());
			NodeList elements = null;
			try{
				elements = rootElement.item(0).getChildNodes();
			}catch(Exception e){
				String q =  System.getProperty("user.dir");
				File v1 = new File(q);
				String[] o = v1.list();
				for(int i = 0 ; i < o.length ; i++){
					log("*-*" + o[i] + "*-*", false);
				}
				log("--" + databaseName + " " + tableName + "--" + o, false);
				
			}
			if (val == 1) {
				/*
				 * int p = 0; for(p = 0 ; p < values.length ; p++){
				 * if(values[p][0] == null){ break; } } if(elements.getLength()
				 * != p){ throw new RuntimeException(); }
				 */

				LinkedList<String> d = new LinkedList<String>();
				LinkedList<String> d1 = new LinkedList<String>();
				LinkedList<String> d2 = new LinkedList<String>();
				for (int i = 0; i < elements.getLength(); i++) {
					d.add(elements.item(i).getNodeName());
					d2.add(elements.item(i).getAttributes().item(0)
							.getNodeValue());
				}

				for (int i = 0; i < d2.size(); i++) {
					if (!d2.get(i).toLowerCase().equals("int")
							&& !d2.get(i).toLowerCase().equals("varchar")) {
						throw new SQLException();
					}
				}

				for (int i = 0; i < values.length && values[i][0] != null; i++) {
					d1.add(values[i][0]);

				}

				for (int i = 0; i < d2.size(); i++) {
					if (d2.get(i).toLowerCase().equals("varchar")) {
						d1.set(i, "\"" + d1.get(i) + "\"");
					}
				}

				for (int i = 0; i < d.size(); i++) {
					if (d2.get(i).toLowerCase().equals("int")
							&& !d1.get(i).toLowerCase()
									.matches("-?\\d+(\\.\\d+)?")) {
						throw new SQLException();
					}
					if (d2.get(i).toLowerCase().equals("varchar")
							&& d1.get(i).toLowerCase()
									.matches("-?\\d+(\\.\\d+)?")) {
						throw new SQLException();
					}

				}
				if (d1.size() != d.size()) {
					throw new SQLException();
				}

				int k = 0;
				for (int i = 0; i < elements.getLength(); i++) {
					elements.item(i).appendChild(doc.createTextNode(" "));
					elements.item(i).appendChild(
							doc.createTextNode(d1.get(k++)));
					elements.item(i).appendChild(doc.createTextNode(" "));
				}
				num = 1;
			} else if (val == 2) {

				LinkedList<String> d0 = new LinkedList<String>();
				/*
				 * for(int i = 0 ; i < elements.getLength() ; i++){
				 * d0.add(elements
				 * .item(i).getAttributes().item(0).getNodeValue());
				 * 
				 * }
				 */

				LinkedList<String> d = new LinkedList<String>();
				for (int i = 0; i < elements.getLength(); i++) {
					d.add(elements.item(i).getNodeName());
				}

				LinkedList<String> d2 = new LinkedList<String>();
				for (int i = 0; i < values.length && values[i][0] != null; i++) {

					d2.add(values[i][0]);
				}

				LinkedList<String> d3 = new LinkedList<String>();
				for (int i = 0; i < values.length && values[i][1] != null; i++) {
					d3.add(values[i][1]);
				}
				if (duplicates(d3)) {
					throw new SQLException();
				}
				if (d2.size() != d.size() || d.size() != d3.size()
						|| d2.size() != d3.size()) {
					throw new SQLException();
				}
				LinkedList<LinkedList<String>> d1 = new LinkedList<LinkedList<String>>();
				for (int i = 0; i < values[0].length; i++) {
					d1.add(i, new LinkedList<String>());
				}
				for (int i = 0; i < values[0].length; i++) {
					for (int j = 0; j < values.length && values[j][i] != null; j++) {
						d1.get(i).add(values[j][i]);
					}
				}
				// ba3mel d0[int, var..]bs bel tarteeb elle hwa meda5alo
				// we ba5aly lw hw ameda5a; ID msh id yekamel 3ady
				for (int i = 0; i < d1.get(0).size(); i++) {
					for (int j = 0; j < elements.getLength(); j++) {
						if (elements.item(j).getNodeName().toLowerCase()
								.equals(d1.get(1).get(i).toLowerCase())) {
							d1.get(1).set(i, elements.item(j).getNodeName());
							d0.add(elements.item(j).getAttributes().item(0)
									.getNodeValue());
						}
					}
				}
				for (int i = 0; i < d1.size(); i++) {
					System.out.println(d1.get(i) + " h");

				}
				for (int i = 0; i < d1.get(1).size(); i++) {
					if (isFound(d, d1.get(1).get(i).toLowerCase()) == false) {
						//throw new SQLException();
					}
				}
				if(d0.size() == 0){
					return 0;
				}
				for (int i = 0; i < d1.get(0).size(); i++) {
					if (d0.get(i).equals("int")
							&& !d1.get(0).get(i).matches("-?\\d+(\\.\\d+)?")) {
						throw new SQLException();
					} else if (d0.get(i).equals("varchar")
							&& d1.get(0).get(i).matches("-?\\d+(\\.\\d+)?")) {
						throw new SQLException();
					} else if (d0.get(i).equals("varchar")
							&& !d1.get(0).get(i).matches("-?\\d+(\\.\\d+)?")) {
						String g = "\"" + d1.get(0).get(i) + "\"";
						d1.get(0).set(i, g);
					}
				}

				for (int i = 0; i < d1.get(0).size(); i++) {
					for (int j = 0; j < elements.getLength(); j++) {
						if (d1.get(1).get(i)
								.equals(elements.item(j).getNodeName())) {
							elements.item(j).appendChild(
									doc.createTextNode(" "));
							elements.item(j).appendChild(
									doc.createTextNode(d1.get(0).get(i)));
							elements.item(j).appendChild(
									doc.createTextNode(" "));
						}
					}
				}
				num = 1;
			} else if (val == 3) {
				
				if (elements.getLength() == 0) {
					throw new SQLException();
				}
				/*if (elements.item(0).getTextContent().replaceAll(" ", "")
						.equals("")) {
//////////					return 0;
				}*/
				String g = elements.item(0).getTextContent();
				String[] tokens = g.trim().split("\\s+");
				LinkedList<String> d = new LinkedList<String>();
				for (String y : tokens) {
					if (!y.equals(" ")) {
						d.add(y);
					}
				}
				for (int i = 0; i < elements.getLength(); i++) {
					elements.item(i).setTextContent("");
				}
				num = d.size();
			} else if (val == 4) {

				if(!value.matches("-?\\d+(\\.\\d+)?")){
					value = "\"" + value + "\"";
				}
				if (operator.equals("=")) {
					/*if(elements.item(0).getTextContent().replaceAll(" ", "").equals("")){
						return 0;
					}*/
					LinkedList<LinkedList<String>> str = new LinkedList<LinkedList<String>>();
					for (int i = 0; i < elements.getLength(); i++) {
						str.add(new LinkedList<String>());
					}
					// ba7ot el text elle fe kol col fe list of lists
					for (int i = 0; i < elements.getLength(); i++) {
						String g = elements.item(i).getTextContent();
						String[] tokens = g.trim().split("\\s+");
						str.get(i).add(elements.item(i).getNodeName());
						str.get(i).add(
								elements.item(i).getAttributes().item(0)
										.getTextContent());
						for (String h : tokens) {
							if (!h.equals(" ")) {
								str.get(i).add(h);
							}
						}
					}

					
					/*for(int i = 0 ; i < str.size() ; i++){
						for(int j = 0 ; j < str.get(0).size() ; j++){
							log(str.get(i).get(j) + " **---** ", false);
						}
					}*/
					// 3alashan 23raf 2na 3adelt fe kam 7eta
					LinkedList<Integer> f = new LinkedList<Integer>();
					for (int i = 0; i < str.size(); i++) {
						if (str.get(i).get(0).toLowerCase()
								.equals(colName.toLowerCase())) {
							for (int j = 1; j < str.get(i).size(); j++) {
								if (str.get(i).get(j).toLowerCase()
										.equals(value.toLowerCase())) {
									f.add(j);
								}
							}
						}
					}
					for (int i = 0; i < str.size(); i++) {
						for (int j = 0; j < f.size(); j++) {
							// str.get(i).remove(f.get(j));
							str.get(i).set(f.get(j), "");
						}
					}

					for (int i = 0; i < str.size(); i++) {
						for (int j = 3; j < str.get(0).size(); j += 2) {
							str.get(i).add(j, " ");
						}
					}
					String g = "";
					for (int i = 0; i < elements.getLength(); i++) {
						for (int j = 2; j < str.get(0).size(); j++) {
							g += str.get(i).get(j);
						}
						elements.item(i).setTextContent(g);
						// System.out.println(g);
						g = "";
					}
					num = f.size();
				} else if (operator.equals("<")) {
					LinkedList<LinkedList<String>> str = new LinkedList<LinkedList<String>>();
					for (int i = 0; i < elements.getLength(); i++) {
						str.add(new LinkedList<String>());
					}
					for (int i = 0; i < elements.getLength(); i++) {
						String g = elements.item(i).getTextContent();
						String[] tokens = g.trim().split("\\s+");
						str.get(i).add(elements.item(i).getNodeName());
						str.get(i).add(
								elements.item(i).getAttributes().item(0)
										.getTextContent());
						for (String h : tokens) {
							if (!h.equals(" ")) {
								str.get(i).add(h);
							}
						}
					}
					if(elements.item(0).getTextContent().replaceAll(" ", "").equals("")){
						return 0;
					}
					LinkedList<Integer> f = new LinkedList<Integer>();
					for (int i = 0; i < str.size(); i++) {
						if (str.get(i).get(0).toLowerCase()
								.equals(colName.toLowerCase())) {
							for (int j = 2; j < str.get(i).size(); j++) {
								if (str.get(i).get(1).toString().toLowerCase()
										.equals("int")) {
									if (Integer.parseInt(str.get(i).get(j))
											- Integer.parseInt(value) < 0) {
										f.add(j);
									}
								} else {
									if (str.get(i).get(j).toString()
											.toLowerCase()
											.compareTo(value.toLowerCase()) < 0) {
										f.add(j);
									}
								}
							}
						}
					}
					for (int i = 0; i < str.size(); i++) {
						for (int j = 0; j < f.size(); j++) {
							// str.get(i).remove(f.get(j));
							str.get(i).set(f.get(j), "");
						}
					}
					
					for (int i = 0; i < str.size(); i++) {
						for (int j = 3; j < str.get(0).size(); j += 2) {
							str.get(i).add(j, " ");
						}
					}
					String g = "";
					for (int i = 0; i < elements.getLength(); i++) {
						for (int j = 2; j < str.get(0).size(); j++) {
							g += str.get(i).get(j);
						}
						elements.item(i).setTextContent(g);
						// System.out.println(g);
						g = "";
					}
					num = f.size();

				} else if (operator.equals(">")) {

					if(elements.item(0).getTextContent().replaceAll(" ", "").equals("")){
						return 0;
					}
					LinkedList<LinkedList<String>> str = new LinkedList<LinkedList<String>>();
					for (int i = 0; i < elements.getLength(); i++) {
						str.add(new LinkedList<String>());
					}
					for (int i = 0; i < elements.getLength(); i++) {
						String g = elements.item(i).getTextContent();
						String[] tokens = g.trim().split("\\s+");
						str.get(i).add(elements.item(i).getNodeName());
						str.get(i).add(
								elements.item(i).getAttributes().item(0)
										.getTextContent());
						for (String h : tokens) {
							if (!h.equals(" ")) {
								str.get(i).add(h);
							}
						}
					}
					for(int i = 0 ; i < str.size() ; i++){
						System.out.println(str.get(i));
					}
					
					LinkedList<Integer> f = new LinkedList<Integer>();
					for (int i = 0; i < str.size(); i++) {
						if (str.get(i).get(0).toLowerCase()
								.equals(colName.toLowerCase())) {
							
							for (int j = 2; j < str.get(i).size(); j++) {
								
								if (str.get(i).get(1).toString().toLowerCase()
										.equals("int")) {System.out.println(Integer.parseInt(str.get(i).get(j)));
									if (Integer.parseInt(str.get(i).get(j))
											- Integer.parseInt(value) > 0) {
										
										f.add(j);
									}
								} else {
									if (str.get(i).get(j).toString()
											.toLowerCase()
											.compareTo(value.toLowerCase()) > 0) {
										f.add(j);
									}
								}
							}
						}
					}
					for (int i = 0; i < str.size(); i++) {
						for (int j = 0; j < f.size(); j++) {
							// str.get(i).remove(f.get(j));
							str.get(i).set(f.get(j), "");
						}
					}

					for (int i = 0; i < str.size(); i++) {
						for (int j = 3; j < str.get(0).size(); j += 2) {
							str.get(i).add(j, " ");
						}
					}
					String g = "";
					for (int i = 0; i < elements.getLength(); i++) {
						for (int j = 2; j < str.get(0).size(); j++) {
							g += str.get(i).get(j);
						}
						elements.item(i).setTextContent(g);
						// System.out.println(g);
						g = "";
					}
					num = f.size();
				}

			} else if (val == 5) {
				
				if(elements.item(0).getTextContent().replaceAll(" ", "").equals("")){
					return 0;
				}
				for (int i = 0; i < elements.getLength(); i++) {
					if (colName.toLowerCase().equals(
							elements.item(i).getNodeName().toLowerCase())) {
						if (elements.item(i).getAttributes().item(0)
								.getNodeValue().equals("int")
								&& !value.matches("-?\\d+(\\.\\d+)?")) {
							//throw new SQLException();
						} else if (elements.item(i).getAttributes().item(0)
								.getNodeValue().equals("varchar")
								&& value.matches("-?\\d+(\\.\\d+)?")) {
							//throw new SQLException();
						}
					}
				}
				if(!value.matches("-?\\d+(\\.\\d+)?")){
					value = "\"" + value + "\"";
				}
				LinkedList<String> d6 = new LinkedList<String>();
				for (int i = 0; i < values.length && values[i][0] != null; i++) {
					d6.add(values[i][0]);
				}
				System.out.println(d6);
				//System.out.println("kgsdfkhsbd" + duplicates(d6));
				if (duplicates(d6)) {
					throw new SQLException();
				}
				LinkedList<String> d = new LinkedList<String>();
				for (int i = 0; i < elements.getLength(); i++) {
					d.add(elements.item(i).getNodeName());
				}
				LinkedList<LinkedList<String>> d1 = new LinkedList<LinkedList<String>>();
				for (int i = 0; i < values[0].length; i++) {
					d1.add(i, new LinkedList<String>());
				}
				for (int i = 0; i < values[0].length; i++) {
					for (int j = 0; j < values.length && values[j][i] != null; j++) {
						d1.get(i).add(values[j][i]);
					}
				}
				LinkedList<String> d0 = new LinkedList<String>();

				for (int i = 0; i < d1.get(0).size(); i++) {
					for (int j = 0; j < elements.getLength(); j++) {
						if (elements.item(j).getNodeName().toLowerCase()
								.equals(d1.get(0).get(i).toLowerCase())) {
							d1.get(0).set(i, elements.item(j).getNodeName());
							d0.add(elements.item(j).getAttributes().item(0)
									.getNodeValue());
						}
					}
				}
				for (int i = 0; i < d1.get(0).size(); i++) {
					if (d0.get(i).equals("int")
							&& !d1.get(1).get(i).matches("-?\\d+(\\.\\d+)?")) {
						//throw new SQLException();
					} else if (d0.get(i).equals("varchar")
							&& d1.get(1).get(i).matches("-?\\d+(\\.\\d+)?")) {
						//throw new SQLException();
					} else if (d0.get(i).equals("varchar")
							&& !d1.get(1).get(i).matches("-?\\d+(\\.\\d+)?")) {
						String g = "\"" + d1.get(1).get(i) + "\"";
						d1.get(1).set(i, g);
					}
				}

				if (operator.equals("=")) {

					LinkedList<LinkedList<String>> str = new LinkedList<LinkedList<String>>();
					for (int i = 0; i < elements.getLength(); i++) {
						str.add(new LinkedList<String>());
					}
					for (int i = 0; i < elements.getLength(); i++) {
						String g = elements.item(i).getTextContent();
						g = g.trim();
						String[] tokens = g.split("\\s+");
						str.get(i).add(elements.item(i).getNodeName());
						str.get(i).add(
								elements.item(i).getAttributes().item(0)
										.getTextContent());
						for (String h : tokens) {
							if (!h.equals(" ")) {
								str.get(i).add(h);
							}
						}
					}
					
//					 for(int i = 0 ; i < str.size() ; i++){
//						 for(int j = 0 ;j < str.get(0).size() ; j++){
//							 log("**------**" + str.get(i).get(j) + "**------**", false);
//						 }
//					 }
//					log("++++++++++++" + elements.item(0).getParentNode().getNodeName() + "++++++++", false);
					 for(int i = 0 ; i < str.size() ; i++){
						 System.out.println(str.get(i) + " plapla"); }
					 value = value.toLowerCase();
					 //System.out.println(!checkTrue(str, d1, value, colName,"="));
					LinkedList<Integer> f = new LinkedList<Integer>();
					for (int i = 0; i < str.size(); i++) {
						if (str.get(i).get(0).toLowerCase()
								.equals(colName.toLowerCase())) {
							
							for (int j = 2; j < str.get(i).size(); j++) {
								//System.out.println(str.get(i).get(j));
								if (str.get(i).get(j).equals(value)
										&& !checkTrue(str, d1, value, colName,
												operator)) {
									
									f.add(j);
								}
							}
						}
					}

					for (int i = 0; i < d1.get(0).size(); i++) {
						for (int j = 0; j < str.size(); j++) {
							if (d1.get(0).get(i).equals(str.get(j).get(0))) {
								// System.out.println(d1.get(0).get(i));
								for (int k = 0; k < f.size(); k++) {
									// System.out.println(f.get(k));
									str.get(j).set(f.get(k), d1.get(1).get(i));
								}
							}
						}
					}
					for (int i = 0; i < str.size(); i++) {
						for (int j = 3; j < str.get(0).size(); j += 2) {
							str.get(i).add(j, " ");
							// str.get(i).add(j, " ");
						}
					}
					String g = "";
					for (int i = 0; i < elements.getLength(); i++) {
						for (int j = 2; j < str.get(0).size(); j++) {
							g += str.get(i).get(j);
						}
						elements.item(i).setTextContent(g);
						// System.out.println(g);
						g = "";
					}
					
					num = f.size();

				} else if (operator.equals("<")) {

					LinkedList<LinkedList<String>> str = new LinkedList<LinkedList<String>>();
					for (int i = 0; i < elements.getLength(); i++) {
						str.add(new LinkedList<String>());
					}
					for (int i = 0; i < elements.getLength(); i++) {
						String g = elements.item(i).getTextContent();
						g = g.trim();
						String[] tokens = g.split("\\s+");
						str.get(i).add(elements.item(i).getNodeName());
						str.get(i).add(
								elements.item(i).getAttributes().item(0)
										.getTextContent());
						for (String h : tokens) {
							if (!h.equals(" ")) {
								str.get(i).add(h);
							}
						}
					}
					/*
					 * for(int i = 0 ; i < d1.size() ; i++){
					 * System.out.println(d1.get(i)); } for(int i = 0 ; i <
					 * str.size() ; i++){ System.out.println(str.get(i)); }
					 */
					System.out.println(!checkTrue(str, d1, value, colName,
							operator) + " yes");
					LinkedList<Integer> f = new LinkedList<Integer>();
					for (int i = 0; i < str.size(); i++) {
						if (str.get(i).get(0).toLowerCase()
								.equals(colName.toLowerCase())) {
							for (int j = 2; j < str.get(i).size(); j++) {

								if (str.get(i).get(1).toString().toLowerCase()
										.equals("int")) {

									if (Integer.parseInt(str.get(i).get(j))
											- Integer.parseInt(value) < 0
											&& !checkTrue(str, d1, value,
													colName, operator)) {

										f.add(j);
									}
								} else {
									if (str.get(i).get(j).compareTo(value) < 0
											&& !checkTrue(str, d1, value,
													colName, operator)) {
										f.add(j);
									}
								}
							}
						}
					}

					for (int i = 0; i < d1.get(0).size(); i++) {
						for (int j = 0; j < str.size(); j++) {
							if (d1.get(0).get(i).equals(str.get(j).get(0))) {
								// System.out.println(d1.get(0).get(i));
								for (int k = 0; k < f.size(); k++) {
									// System.out.println(f.get(k));
									str.get(j).set(f.get(k), d1.get(1).get(i));
								}
							}
						}
					}
					for (int i = 0; i < str.size(); i++) {
						for (int j = 3; j < str.get(0).size(); j += 2) {
							str.get(i).add(j, " ");
						}
					}
					String g = "";
					for (int i = 0; i < elements.getLength(); i++) {
						for (int j = 2; j < str.get(0).size(); j++) {
							g += str.get(i).get(j);
						}
						elements.item(i).setTextContent(g);
						// System.out.println(g);
						g = "";
					}
					num = f.size();
				} else if (operator.equals(">")) {
					LinkedList<LinkedList<String>> str = new LinkedList<LinkedList<String>>();
					for (int i = 0; i < elements.getLength(); i++) {
						str.add(new LinkedList<String>());
					}
					for (int i = 0; i < elements.getLength(); i++) {
						String g = elements.item(i).getTextContent();
						g = g.trim();
						String[] tokens = g.split("\\s+");
						str.get(i).add(elements.item(i).getNodeName());
						str.get(i).add(
								elements.item(i).getAttributes().item(0)
										.getTextContent());
						for (String h : tokens) {
							if (!h.equals(" ")) {
								str.get(i).add(h);
							}
						}
					}
					/*
					 * for(int i = 0 ; i < d1.size() ; i++){
					 * System.out.println(d1.get(i)); }
					 */
					/*
					 * for(int i = 0 ; i < str.size() ; i++){
					 * System.out.println(str.get(i)); }
					 */
					if (elements.item(0).getTextContent().replace(" ", "")
							.equals("")) {
						return 0;
					}
					LinkedList<Integer> f = new LinkedList<Integer>();
					for (int i = 0; i < str.size(); i++) {
						if (str.get(i).get(0).toLowerCase()
								.equals(colName.toLowerCase())) {
							for (int j = 2; j < str.get(i).size(); j++) {
								if (str.get(i).get(1).toString().toLowerCase()
										.equals("int")) {
									if (Integer.parseInt(str.get(i).get(j))
											- Integer.parseInt(value) > 0
											&& !checkTrue(str, d1, value,
													colName, operator)) {
										f.add(j);
									}
								} else {
									if (str.get(i).get(j).compareTo(value) > 0
											&& !checkTrue(str, d1, value,
													colName, operator)) {
										f.add(j);
									}
								}
							}
						}
					}

					for (int i = 0; i < d1.get(0).size(); i++) {
						for (int j = 0; j < str.size(); j++) {
							if (d1.get(0).get(i).equals(str.get(j).get(0))) {
								// System.out.println(d1.get(0).get(i));
								for (int k = 0; k < f.size(); k++) {
									// System.out.println(f.get(k));
									str.get(j).set(f.get(k), d1.get(1).get(i));
								}
							}
						}
					}
					for (int i = 0; i < str.size(); i++) {
						for (int j = 3; j < str.get(0).size(); j += 2) {
							str.get(i).add(j, " ");
						}
					}
					String g = "";
					for (int i = 0; i < elements.getLength(); i++) {
						for (int j = 2; j < str.get(0).size(); j++) {
							g += str.get(i).get(j);
						}
						elements.item(i).setTextContent(g);
						// System.out.println(g);
						g = "";
					}
					num = f.size();
				}

			}else if(val == 6){
				LinkedList<LinkedList<String>> str = new LinkedList<LinkedList<String>>();
				for (int i = 0; i < elements.getLength(); i++) {
					str.add(new LinkedList<String>());
				}
				for (int i = 0; i < elements.getLength(); i++) {
					String g = elements.item(i).getTextContent();
					g = g.trim();
					String[] tokens = g.split("\\s+");
					str.get(i).add(elements.item(i).getNodeName());
					str.get(i).add(
							elements.item(i).getAttributes().item(0)
									.getTextContent());
					for (String h : tokens) {
						if (!h.equals(" ")) {
							str.get(i).add(h);
						}
					}
				}
				if(str.get(0).size() == 3 && str.get(0).get(2).replaceAll(" ", "").equals("")){
					//update table fady
					return 0;
					//throw new SQLException();
				}
				LinkedList<String> k = new LinkedList<String>();
				for(int i = 0 ; i < values.length && values[i][0]!=null ; i++){
					
					k.add(values[i][0]);
				}
				LinkedList<String> v = new LinkedList<String>();
				for(int i = 0 ; i < values.length && values[i][1]!=null ; i++){
					v.add(values[i][1]);
				}
				
				LinkedList<LinkedList<Integer>> size = new LinkedList<LinkedList<Integer>>();
				for(int i = 0 ; i < k.size() ; i++){
					size.add(new LinkedList<Integer>());
				}
				for(int i = 0 ; i < str.size() ; i++){
					System.out.println(str.get(i) + " before");
				}
				for(int i = 0 ; i < k.size() ; i++){
					for(int j = 0 ; j < str.size() ; j++){
						if(k.get(i).toLowerCase().equals(str.get(j).get(0).toLowerCase())){
							for(int p = 0 ; p < str.get(0).size()-2 ; p++){
								if(!v.get(i).equals(str.get(j).get(p+2).replaceAll("\"", ""))){
									size.get(i).add(1);
									//System.out.println(v.get(i) + " " + str.get(j).get(p+2));
									str.get(j).set(p+2, v.get(i));
								}
							}
						}
					}
				}
				for(int i= 0 ; i < str.size() ; i++){
					if(str.get(i).get(1).toLowerCase().equals("varchar")){
						for(int j = 2 ; j < str.get(0).size() ; j++){
							if(str.get(i).get(j).charAt(0) == '"' && str.get(i).get(j).charAt(str.get(i).get(j).length()-1) == '"'){
								
							}else{
								String g = "\"" + str.get(i).get(j) + "\"";
								str.get(i).set(j, g);
							}
						}
					}
				}
				for(int i = 0 ; i < str.size() ; i++){
					System.out.println(str.get(i) + " after");
				}
				for(int i = 0; i < size.size() ; i++){
					System.out.println(size.get(i));
				}
				String g = "";
				for (int i = 0; i < elements.getLength(); i++) {
					for (int j = 2; j < str.get(0).size(); j++) {
						g += str.get(i).get(j) + "  ";
					}
					System.out.println(g + "  yess");
					elements.item(i).setTextContent(g);
					// System.out.println(g);
					g = "";
				}
				int updated = size.get(0).size();
				for(int i = 1 ; i < size.size() ; i++){
					if(updated < size.get(i).size()){
						updated = size.get(i).size();
					}
				}
				num = updated;
			}

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			throw new SQLException();
		}

		TransformerFactory tr = TransformerFactory.newInstance();
		Transformer transformer = tr.newTransformer();

		transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, tableName
				+ ".dtd");

		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(
				System.getProperty("user.dir") + File.separator + databaseName
						+ File.separator + tableName + ".xml"));
		transformer.transform(source, result);

		return num;
	}

	private boolean checkTrue(LinkedList<LinkedList<String>> g,
			LinkedList<LinkedList<String>> g1, String val, String colName,
			String operator) {

		LinkedList<LinkedList<String>> k = new LinkedList<LinkedList<String>>();

		for (int i = 0; i < g.size(); i++) {
			System.out.println(g.get(i));
		}
		for (int i = 0; i < g.size(); i++) {
			k.add(new LinkedList<String>());
			k.get(i).add(g.get(i).get(0));
		}

		int r = 0;
		for (int i = 0; i < g.size(); i++) {
			if (colName.toLowerCase().equals(g.get(i).get(0))) {
				for (int j = 2; j < g.get(0).size(); j++) {
					if (operator.equals("=")) {
						if (val.equals(g.get(i).get(j))) {
							for (int c = 0; c < g.size(); c++) {
								k.get(c).add(g.get(c).get(j));
							}

						}
					} else if (operator.equals("<")) {
						if (val.matches("-?\\d+(\\.\\d+)?")) {
							if (Integer.parseInt(val)
									- Integer.parseInt(g.get(i).get(j)
											.toString()) > 0) {
								for (int c = 0; c < g.size(); c++) {

									k.get(c).add(g.get(c).get(j));
								}

							}
						} else {
							if (val.compareTo(g.get(i).get(j)) > 0) {
								for (int c = 0; c < g.size(); c++) {

									k.get(c).add(g.get(c).get(j));
								}

							}
						}
					} else if (operator.equals(">")) {
						if (val.matches("-?\\d+(\\.\\d+)?")) {
							if (Integer.parseInt(val)
									- Integer.parseInt(g.get(i).get(j)) < 0) {
								for (int c = 0; c < g.size(); c++) {

									k.get(c).add(g.get(c).get(j));
								}

							}
						} else {
							if (val.compareTo(g.get(i).get(j)) < 0) {
								for (int c = 0; c < g.size(); c++) {

									k.get(c).add(g.get(c).get(j));
								}

							}
						}
					}
				}
			}
		}
		System.out.println(k.size() + "  op");
		for (int i = 0; i < k.size(); i++) {
			System.out.println(k.get(i));
		}
		if (k.get(0).size() > 1) {
			for (int i = 0; i < g1.get(0).size(); i++) {
				for (int j = 0; j < k.size(); j++) {
					if (g1.get(0).get(i).toLowerCase().equals(k.get(j).get(0))) {
						if (!g1.get(1).get(i).toLowerCase()
								.equals(k.get(j).get(1))) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}

	
	public boolean isFound(LinkedList<String> g, String v) {
		for (int i = 0; i < g.size(); i++) {
			if (v.toLowerCase().equals(g.get(i).toLowerCase())) {
				return true;
			}
		}
		return false;

	}
}