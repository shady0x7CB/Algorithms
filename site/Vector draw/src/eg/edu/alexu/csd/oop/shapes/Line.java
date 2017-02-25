package eg.edu.alexu.csd.oop.shapes;

import java.awt.Color;
import java.awt.Graphics;

public class Line extends MyShape{

	public Line(){
		map.clear();
		map.put("lineLength", null);
		
	}
	
	private void draw(Graphics canvas, Color color){
		canvas.setColor(color);
		canvas.drawLine(getPosition().x,
				getPosition().y,
				getPosition().x,
				getPosition().y + this.map.get("lineLength").intValue());
	}
	@Override
	public void draw(Graphics canvas) {
		
		
		if(getColor()!= null){
			draw(canvas, getColor());
		}if(getFillColor()!= null){
			draw(canvas, getFillColor());
		}else{
			draw(canvas, Color.BLACK);
		}
	}
}