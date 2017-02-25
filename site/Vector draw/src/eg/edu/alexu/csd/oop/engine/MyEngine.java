package eg.edu.alexu.csd.oop.engine;

import java.awt.Graphics;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

import eg.edu.alexu.csd.oop.draw.DrawingEngine;
import eg.edu.alexu.csd.oop.draw.Shape;

public class MyEngine implements DrawingEngine{
 
	private Stack<List<Shape>> undoStk = new Stack<List<Shape>>();
	private Stack<List<Shape>> redoStk = new Stack<List<Shape>>(); 
	private static DrawingEngine instance = new MyEngine();
	private MyEngine(){}
	public static DrawingEngine getInstance(){
		return instance;
	}
	@Override
	public void refresh(Graphics canvas) {
		// TODO Auto-generated method stub
		if(getShapes()==null){
			canvas.drawString("", 0, 0);
			return;
		}
		Shape[] shapes =  getShapes();
		for(int i=0; i<getShapes().length;i++){
			shapes[i].draw(canvas);
    	}
	}

	@Override
	public void addShape(Shape shape) {
		// TODO Auto-generated method stub
		List<Shape> list;
		if(getShapes()==null){
			undoStk.push(new LinkedList<Shape>());
			list = new LinkedList<>();
		}else{
			Shape[] shapes =  getShapes().clone();
			list = new ArrayList<Shape>(Arrays.asList(shapes));
		}
		list.add(shape);
		undoStk.push(list);
		redoStk.clear();
	}

	@Override
	public void removeShape(Shape shape) {
		// TODO Auto-generated method stub
				
		Shape[] shapes =  getShapes().clone();
		List<Shape> list = new ArrayList<Shape>(Arrays.asList(shapes));
		if(!list.remove(shape)) throw new RuntimeException();
		undoStk.push(list);
		redoStk.clear();
	}

	@Override
	public void updateShape(Shape oldShape, Shape newShape) {
		// TODO Auto-generated method stub
		Shape[] shapes = getShapes().clone();
		List<Shape> list = new ArrayList<Shape>(Arrays.asList(shapes));
		int ind = list.indexOf(oldShape);
		list.remove(oldShape);
		list.add(ind, newShape);
		undoStk.push(list);
		redoStk.clear();
	}

	@Override
	public Shape[] getShapes() {
		// TODO Auto-generated method stub
		if(undoStk.empty()) return null;
		else return undoStk.peek().toArray(new Shape[0]);
	}

	@Override
	public List<Class<? extends Shape>> getSupportedShapes() {
		// TODO Auto-generated method stub
		ClassFinder c = new ClassFinder();
	    return c.find();
		
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		
		if(undoStk.size()==1){throw new RuntimeException();}
		else {
			redoStk.push(undoStk.pop());
			if(redoStk.size()==21){
				undoStk.clear();
				undoStk.push(redoStk.pop());
				throw new RuntimeException();
			}
		}
	}

	@Override
	public void redo() {
		// TODO Auto-generated method stub
		
		if(redoStk.isEmpty()){throw new RuntimeException();}
		else {
			undoStk.push(redoStk.pop());
		}
	}

	@Override
	public void save(String path) {
		// TODO Auto-generated method stub
		String xml = path.substring(path.length()-4);
		String json = path.substring(path.length()-5);
		if(xml.equalsIgnoreCase(".xml")){
			path = path.substring(0, path.length()-4);
			path += xml.toLowerCase();
			XMLBuilder build = new XMLBuilder();
			build.engine(getShapes(), path);
		}
		else if(json.equalsIgnoreCase(".json")){
			path = path.substring(0, path.length()-5);
			path += json.toLowerCase();
			JSONBuilder j = new JSONBuilder();
			j.buildJSON(getShapes(), path);
		}
		else throw new RuntimeException("file format not supported"); //file format not supported
	}

	@Override
	public void load(String path) {
		// TODO Auto-generated method stub
		String xml = path.substring(path.length()-4);
		String json = path.substring(path.length()-5);
		if(getShapes()==null)
			undoStk.push(new LinkedList<Shape>());
		if(xml.equalsIgnoreCase(".xml")){
			path = path.substring(0, path.length()-4);
			path += xml.toLowerCase();
			XMLReader read = new XMLReader();
			undoStk.clear();
			redoStk.clear();
			undoStk.push(read.engine(path));
		}
		else if(json.equalsIgnoreCase(".json")){
			path = path.substring(0, path.length()-5);
			path += json.toLowerCase();
			JSONReader read2 = new JSONReader();
			undoStk.clear();
			redoStk.clear();
			undoStk.push(read2.readJSON(path));
		}
		else throw new RuntimeException("Cannot load file"); //file format not supported
	}
	
}