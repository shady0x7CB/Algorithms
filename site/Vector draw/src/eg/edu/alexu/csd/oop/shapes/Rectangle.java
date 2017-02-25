package eg.edu.alexu.csd.oop.shapes;
import java.awt.Color;
import java.awt.Graphics;

public class Rectangle extends MyShape{
	
	public Rectangle(){
		map.put("height", null);
		map.put("width", null);
	}
		
	private void draw(Graphics canvas, boolean checkFill, Color color){
		if(checkFill){
			canvas.setColor(color);
			canvas.fillRect(getPosition().x, getPosition().y,
					this.map.get("width").intValue(),
					this.map.get("height").intValue());
		}else{
			canvas.setColor(color);
			canvas.drawRect(getPosition().x, getPosition().y,
					this.map.get("width").intValue(),
					this.map.get("height").intValue());
		}
	}
	@Override
	public void draw(Graphics canvas) {
		
		if(getFillColor() != null){
			draw(canvas, true, getFillColor());
		}
		else{
			draw(canvas, false, Color.black);
		}
		
		if(getColor() != null){
			draw(canvas, false, getColor());
		}
		else{
			draw(canvas, false, Color.black);
		}
	
	}
}