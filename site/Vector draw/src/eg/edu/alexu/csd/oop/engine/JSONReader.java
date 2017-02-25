package eg.edu.alexu.csd.oop.engine;

import java.awt.Color;
import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import eg.edu.alexu.csd.oop.draw.Shape;

public class JSONReader {
	
	public List<Shape> readJSON(String path){
		
		List<Shape> shapes = new LinkedList<Shape>();
		 try {
			Scanner s = new Scanner(new File(path));
			s.nextLine();
			String numOfShapes = s.nextLine();
			if(numOfShapes.equals("")) return shapes; 
			numOfShapes = numOfShapes.replaceAll("[^-?0-9]+", "");
			for(int i=0; i<Integer.parseInt(numOfShapes); ++i){
				String className = s.nextLine().replaceAll("[:{\"]", "").trim();
				Shape shape = null;
				try {
					Class<?> structure = Class.forName(className);
					shape = (Shape) structure.newInstance();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				s.nextLine();
				String in;
				Map<String, Double> m = shape.getProperties();
				in = s.nextLine().trim();
				if(in.equals("\"null\"")){
					m = null;
				}
				else {
					for(String key : m.keySet()){
						in = s.nextLine().replaceAll("[,:{\"a-zA-Z]", "").trim();
						if(in.equals(""))
							m.put(key, null);
						else
							m.put(key, Double.parseDouble(in));
					}
				}
				shape.setProperties(m);
				s.nextLine();
				s.nextLine();
				Point p = new Point();
				in = s.nextLine().replaceAll("[,:{\"a-zA-Z]", "").trim();
				if(in.equals("")){
					s.nextLine();
					p = null;
				}
				else{
					p.x = Integer.parseInt(in);
					p.y = Integer.parseInt(s.nextLine().replaceAll("[,:{\"a-zA-Z]", "").trim());
				}
				shape.setPosition(p);
				s.nextLine();
				in = s.nextLine().replaceAll("[,:{\"a-zA-Z]", "").trim();
				Color setColor, fillColor;
				if(in.equals(""))
					setColor = null;
				else
					setColor = new Color(Integer.parseInt(in));
				in = s.nextLine().replaceAll("[,:{\"a-zA-Z]", "").trim();
				if(in.equals(""))
					fillColor = null;
				else
					fillColor = new Color(Integer.parseInt(in));
				shape.setColor(setColor);
				shape.setFillColor(fillColor);
				s.nextLine();
				shapes.add(shape);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return shapes;
	}
}