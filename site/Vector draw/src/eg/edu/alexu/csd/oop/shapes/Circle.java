package eg.edu.alexu.csd.oop.shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;

public class Circle extends Ellipse{

	private Map<String, Double> map = new HashMap<String, Double>();
	
	public Circle(){
		this.map.put("radius", null);
	}
	
	@Override
	public void setProperties(Map<String, Double> properties) {
		
		if(properties == null){
			this.map = null;
			return;
		}
		super.getProperties().put("horizontalRadius", properties.get("radius"));
		super.getProperties().put("verticalRadius", properties.get("radius"));
		this.map.put("radius", properties.get("radius"));
	}
	@Override
	public Map<String, Double> getProperties() {
		
		return this.map;
	}	
}