package eg.edu.alexu.csd.oop.shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import eg.edu.alexu.csd.oop.draw.Shape;

public abstract class MyShape implements Shape, Cloneable{

	protected Map<String, Double> map = new HashMap<String, Double>();
	private Point point;
	private Color color;
	private Color fillColor;
	
	@Override
	public void setPosition(Point position) {
		// TODO Auto-generated method stub
		point = new Point();
		this.point = position;
	}

	@Override
	public Point getPosition() {
		// TODO Auto-generated method stub
		return this.point;
	}

	@Override
	public void setProperties(Map<String, Double> properties) {
		// TODO Auto-generated method stub
		this.map = properties;
		//this.map.putAll(properties);
	}
	
	
	@Override
	public Map<String, Double> getProperties() {
		// TODO Auto-generated method stub
		return this.map;
	}

	@Override
	public void setColor(Color color) {
		// TODO Auto-generated method stub
		this.color = color;
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return this.color;
	}


	@Override
	public void setFillColor(Color color) {
		// TODO Auto-generated method stub
		this.fillColor = color;
	}

	@Override
	public Color getFillColor() {
		// TODO Auto-generated method stub
		return this.fillColor;
	}

	
	public abstract void draw(Graphics canvas);

	@Override
	public Shape clone()throws CloneNotSupportedException{
		Shape ms;
		try {
			ms = (MyShape) super.clone();
			 
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			throw new AssertionError();
		}
		return ms;
	}
}