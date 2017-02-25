package eg.edu.alexu.csd.oop.shapes;

import java.awt.Color;
import java.awt.Graphics;

public class Ellipse extends MyShape{

	public Ellipse(){
		
		map.put("horizontalRadius", null);
		map.put("verticalRadius", null);
	}
	
	private void draw(Graphics canvas, boolean checkFill, Color color){
		if(checkFill){
			canvas.setColor(color);
			canvas.fillArc(getPosition().x , getPosition().y,
					this.map.get("horizontalRadius").intValue(),
					this.map.get("verticalRadius").intValue(), 0, 360);
		}else{
			canvas.setColor(color);
			canvas.drawArc(getPosition().x , getPosition().y,
					this.map.get("horizontalRadius").intValue(),
					this.map.get("verticalRadius").intValue(), 0, 360);
		}
	}
	@Override
	public void draw(Graphics canvas) {
		
		if(getFillColor() != null){
			draw(canvas, true, getFillColor());
		}
		else{	
			draw(canvas, false, Color.BLACK);
		}
		if(getColor() != null){
			draw(canvas, false, getColor());
		}
		else{
			draw(canvas, false, Color.black);			
		}
	}
}