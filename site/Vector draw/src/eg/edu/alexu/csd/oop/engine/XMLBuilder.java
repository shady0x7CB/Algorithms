package eg.edu.alexu.csd.oop.engine;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import eg.edu.alexu.csd.oop.draw.Shape;

public class XMLBuilder {
	
	public void engine(Shape[] shapes, String path){
		try
		{
			//path = "/debug/toto.xml";
			int nullShapes = 0;
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("shapes");
			doc.appendChild(rootElement);
			if(shapes!=null)
				for(Shape shape : shapes){
					if(shape == null) {
						nullShapes++;
					}
					else {
						//shapes
						Element e = doc.createElement(shape.getClass().getName());
						rootElement.appendChild(e);
						
						//properties elements
						Element properties = doc.createElement("properties");
						if(shape.getProperties()==null){
							properties.setTextContent("null");
						}
						else if(shape.getProperties().isEmpty()){
							properties.setTextContent("empty");
						}
						else {
							for(String st : shape.getProperties().keySet()){
								Element e1 = doc.createElement(st);
								if (shape.getProperties().get(st)==null){
									e1.appendChild(doc.createTextNode("null"));
								}
								else {
									e1.appendChild(doc.createTextNode(shape.getProperties().get(st).toString()));
								}
								properties.appendChild(e1);
							}
						}
						e.appendChild(properties);
						
						//position elements
						Element position = doc.createElement("Position");
						e.appendChild(position);
						Element x = doc.createElement("xValue");
						Element y = doc.createElement("yValue");
						if(shape.getPosition()==null){
							x.appendChild(doc.createTextNode("null"));
							y.appendChild(doc.createTextNode("null"));
						}
						else{
							x.appendChild(doc.createTextNode(String.valueOf(shape.getPosition().x)));
							y.appendChild(doc.createTextNode(String.valueOf(shape.getPosition().y)));
						}
						position.appendChild(x);
						position.appendChild(y);
						
						Element setColor = doc.createElement("setColor");
						if(shape.getColor()==null){
							setColor.appendChild(doc.createTextNode("null"));
						}
						else {
							setColor.appendChild(doc.createTextNode(String.valueOf(shape.getColor().getRGB())));
						}
						e.appendChild(setColor);
						
						Element fillColor = doc.createElement("fillColor");
						if(shape.getFillColor()==null){
							fillColor.appendChild(doc.createTextNode("null"));
						}
						else {
							fillColor.appendChild(doc.createTextNode(String.valueOf(shape.getFillColor().getRGB())));
						}
						e.appendChild(fillColor);
					}
				}
			Element e2 = doc.createElement("nullShapes");
			e2.appendChild(doc.createTextNode(String.valueOf(nullShapes)));
			rootElement.appendChild(e2);
			//write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			
			StreamResult result =  new StreamResult(new File(path));
			transformer.transform(source, result);	
			
		}catch(ParserConfigurationException pce){
			pce.printStackTrace();
		}catch(TransformerException tfe){
			tfe.printStackTrace();
		}
	}
}