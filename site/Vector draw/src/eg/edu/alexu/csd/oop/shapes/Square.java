package eg.edu.alexu.csd.oop.shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;

public class Square extends Rectangle{

private Map<String, Double> map = new HashMap<String, Double>();
	
	public Square(){
		this.map.put("length", null);
	}
	
	@Override
	public void setProperties(Map<String, Double> properties) {
		
		if(properties == null){
			this.map = null;
			return;
		}
		super.getProperties().put("width", properties.get("length"));
		super.getProperties().put("height", properties.get("length"));
		this.map.put("length", properties.get("length"));
	}
	@Override
	public Map<String, Double> getProperties() {
		
		return this.map;
	}
}