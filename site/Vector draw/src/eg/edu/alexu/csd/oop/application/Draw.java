package eg.edu.alexu.csd.oop.application;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import eg.edu.alexu.csd.oop.draw.DrawingEngine;
import eg.edu.alexu.csd.oop.engine.MyEngine;

public class Draw extends JPanel{

	public DrawingEngine drawingEngine = MyEngine.getInstance();
	public Draw(){
		super();
		this.setBounds(175, 0, 1188, 660);
		setBorder(new LineBorder(Color.RED));
		this.setBackground(Color.WHITE);
		
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		drawingEngine.refresh(g);
	}
}
