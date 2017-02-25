package eg.edu.alexu.csd.oop.engine;

import java.awt.Color;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import eg.edu.alexu.csd.oop.draw.Shape;

public class XMLReader {

	public List<Shape> engine(String path){
		List<Shape> shapes = new LinkedList<Shape>(); 
		try {
			File fXmlFile = new File(path);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder;
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			Element rootElement = doc.getDocumentElement();
			
			NodeList list = rootElement.getChildNodes();
			for(int i=0; i<list.getLength()-1; i++){
				Node classType = list.item(i);
				if(classType.getNodeType() == Node.ELEMENT_NODE){
					Shape shape = null;
					try {
				    	Class<?> structure = Class.forName(classType.getNodeName());
						shape = (Shape) structure.newInstance();
						} catch (InstantiationException e) {
							// TODO Auto-generated catch block
							throw new RuntimeException("heeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeey1", e);
						} catch (IllegalAccessException e1) {
							// TODO Auto-generated catch block
							throw new RuntimeException("heeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeey2", e1);
						} catch (ClassNotFoundException e2){
							throw new RuntimeException("heeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeey3", e2);
			    		}
					Map<String, Double> m;
					NodeList tags = classType.getChildNodes();
					if(tags.item(0).getTextContent().equals("null")){
						m = null;
					}
					else if(tags.item(0).getTextContent().equals("empty")){
						m = new HashMap<String, Double>();
					}
					else {
						m = shape.getProperties();
						NodeList prop = tags.item(0).getChildNodes();
						for(int j=0; j<prop.getLength();j++){
							Node propType = prop.item(j);
							if(propType.getTextContent().equals("null"))
								m.put(propType.getNodeName(), null);
							else
								m.put(propType.getNodeName(), Double.parseDouble(propType.getTextContent()));
						}
					}
					shape.setProperties(m);
					Element e = (Element)tags.item(1);
					Point p = new Point();
					if(e.getElementsByTagName("xValue").item(0).getTextContent().equals("null")){
						p = null;
					}
					else {
						p.x = Integer.parseInt(e.getElementsByTagName("xValue").item(0).getTextContent());
						p.y = Integer.parseInt(e.getElementsByTagName("yValue").item(0).getTextContent());
					}
					shape.setPosition(p);
					Color setColor, fillColor;
					if(tags.item(2).getTextContent().equals("null"))
						setColor = null;
					else
						setColor = new Color(Integer.parseInt(tags.item(2).getTextContent()));
					if(tags.item(3).getTextContent().equals("null"))
						fillColor = null;
					else
						fillColor = new Color(Integer.parseInt(tags.item(3).getTextContent()));
					shape.setColor(setColor);
					shape.setFillColor(fillColor);
					shapes.add(shape);
				}
			}
			for(int i=0; i<Integer.parseInt(list.item(list.getLength()-1).getTextContent()); i++){
				shapes.add(null);
			}
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return shapes;
	}
}