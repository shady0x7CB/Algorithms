package eg.edu.alexu.csd.oop.shapes;

import java.awt.Color;
import java.awt.Graphics;

public class Triangle extends MyShape{
	
	public Triangle() {
		map.clear();
		map.put("halfWidth", null);
		map.put("height", null);
		
	}
	private void draw(Graphics canvas, boolean checkFill, Color color, int[]x, int[]y){
		if(checkFill){
			canvas.setColor(color);
			canvas.fillPolygon(x, y, 3);
		}else{
			canvas.setColor(color);
			canvas.drawPolygon(x, y, 3);
		}
	}
	@Override
	public void draw(Graphics canvas) {
		
		int x[] = {getPosition().x - this.map.get("halfWidth").intValue(), 
				   getPosition().x, 
				   getPosition().x + this.map.get("halfWidth").intValue()};
		int y[] = {getPosition().y + this.map.get("height").intValue(), 
				   getPosition().y, 
			   	   getPosition().y + this.map.get("height").intValue()};
		if(getFillColor() != null){
			draw(canvas, true, getFillColor(), x, y);
		}
		else{
			draw(canvas, false, Color.BLACK, x, y);
		}
		if(getColor() != null){
			draw(canvas, false, getColor(), x, y);
		}else{
			draw(canvas, false, Color.BLACK, x, y);
		}
	}

}