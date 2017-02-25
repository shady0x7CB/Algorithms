package eg.edu.alexu.csd.oop.application;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import eg.edu.alexu.csd.oop.draw.DrawingEngine;
import eg.edu.alexu.csd.oop.draw.Shape;
import eg.edu.alexu.csd.oop.engine.MyEngine;

public class Application extends JFrame implements ActionListener, MouseListener, MouseMotionListener{

	private Draw drawPanel = new Draw();
	private int index = 0;
	private int shapeIndex;
	private Shape shape2 ;
	private String msg, path;
	private Point point1; 
	private boolean isOk = false;
	private Map<String, Double> map;
	private String[] choose = {"choose a shape"};
	private JPanel menuPanel, drawingPanel, labelPanel;
	private JButton ok, draw;
	private JMenuBar menuBar, menuBar1;
	private JMenu tools, clr, shape;
	private JMenuItem chooseColor, chooseFillColor, undo, redo, save, load, zoomIn, zoomOut, remove;
	private JComboBox cb = new JComboBox(choose);
	private JTextField ans;
	private JComboBox cb1;
    private Shape clickedShape = null;
    private Shape draggedShape = null;
    private int prevDragX;
    private int prevDragY;
    private boolean isDragged = false;
    private JPopupMenu rightClick;
	
    
    public Application(){
		super("Paint");
		
		add(drawPanel);
		drawingPanel = new JPanel();
		drawingPanel.setBackground(Color.WHITE);
		add(drawingPanel, BorderLayout.CENTER);
		drawingPanel.addMouseListener(this);
		drawingPanel.addMouseMotionListener(this);
		drawingPanel.setBounds(224, 0, 1140, 660);
		
																
		menuPanel = new JPanel();
		menuPanel.setBackground(Color.BLACK);
		add(menuPanel, BorderLayout.WEST);
		menuBar = new JMenuBar();
		shape = new JMenu("Shape");
		menuBar.add(shape);
		shape.addActionListener(this);
		menuPanel.add(menuBar);
		
		
		for(int i = 0 ; i < drawPanel.drawingEngine.getSupportedShapes().size() ; i++){
			shape.add(new JMenuItem(drawPanel.drawingEngine.getSupportedShapes().get(i).getSimpleName()));
			shape.getItem(i).addActionListener(this);
		}
		
		
		menuBar1 = new JMenuBar();
		tools = new JMenu("Tools");
		menuBar1.add(tools);
		menuPanel.add(menuBar1);
		undo = new JMenuItem("undo");
		redo = new JMenuItem("redo");
		save = new JMenuItem("save");
		load = new JMenuItem("load");
		tools.add(undo);
		tools.add(redo);
		tools.add(save);
		tools.add(load);
		setAction(tools);
		clr = new JMenu("Color");
		setAction(clr);
		
		
		draw = new JButton("Draw");
		menuPanel.add(draw);
		draw.addActionListener(this);
		
		
		labelPanel = new JPanel();
		labelPanel.setBackground(Color.BLUE);
		add(labelPanel, BorderLayout.SOUTH);
		labelPanel.add(cb);
		ans = new JTextField(null, 20);
		labelPanel.add(ans);
		ok = new JButton("Ok");
		labelPanel.add(ok);
		ok.addActionListener(this);
		cb.addActionListener(this);
		
		
		remove = new JMenuItem("remove");
		zoomIn = new JMenuItem("zoom in");
		zoomOut = new JMenuItem("zoom out");
		remove.addActionListener(this);
		zoomIn.addActionListener(this);
		zoomOut.addActionListener(this);
		chooseColor = new JMenuItem("choose color");
		chooseFillColor = new JMenuItem("choose fill color");
		chooseColor.addActionListener(this);
		chooseFillColor.addActionListener(this);
		rightClick = new JPopupMenu();
		rightClick.add(zoomIn);
		rightClick.add(zoomOut);
		rightClick.add(chooseColor);
		rightClick.add(chooseFillColor);
		rightClick.add(remove);
	}
	
	private boolean containsPoint(Shape s, int x, int y){
		if(x >= s.getPosition().x && x <= s.getPosition().x + 150 && y>= s.getPosition().y && y<= s.getPosition().y + 150){
			return true;
		}
		    return false;
	}
	private void moveBy(Shape s, int x, int y){
		Point point = new Point(s.getPosition().x + x, s.getPosition().y +y);
		s.setPosition(point);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		
		Point point = new Point(e.getX(), e.getY());
		point1 = point;
	}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {
		
		int x = e.getX(), y = e.getY();
		if(drawPanel.drawingEngine.getShapes() != null){
			for(shapeIndex = drawPanel.drawingEngine.getShapes().length-1 ; shapeIndex >=0  ; shapeIndex--){
				if(containsPoint(drawPanel.drawingEngine.getShapes()[shapeIndex], x, y)){
					try {
						clickedShape = (Shape) drawPanel.drawingEngine.getShapes()[shapeIndex].clone();
					} catch (CloneNotSupportedException e1) {
						e1.printStackTrace();
					}
					prevDragX = x;
					prevDragY = y;
					break;
				}
			}
		}
		if(clickedShape == null){
			return;
		}
		else if(e.isPopupTrigger()){
			rightClick.show(this, e.getX(), e.getY());
		}
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		if(clickedShape != null && isDragged){
			int x = e.getX();
			int y = e.getY();
			moveBy(clickedShape, x - prevDragX, y - prevDragY);
			drawPanel.drawingEngine.updateShape(drawPanel.drawingEngine.getShapes()[shapeIndex], clickedShape);
			isDragged = false;
			repaint();
		}
		if (e.isPopupTrigger()) {
			int x = e.getX(), y = e.getY();
			for(int i = 0 ; i < drawPanel.drawingEngine.getShapes().length ; i++){
				if(containsPoint(drawPanel.drawingEngine.getShapes()[i], x, y)){
					rightClick.show(this,e.getX()+100, e.getY());
				}
			}
			
      }
		clickedShape = null;
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		
		if(clickedShape != null){
			isDragged = true;
			
			int x = e.getX();
			int y = e.getY();
			
			moveBy(clickedShape, x-prevDragX, y-prevDragY);
			
			prevDragX = x;
			prevDragY = y;
			repaint();
		}
	}
	@Override
	public void mouseMoved(MouseEvent e) {}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		for(int i = 0 ; i < shape.getItemCount() ; i++){
			if(e.getSource().equals(shape.getItem(i))){
				try {
					map = new HashMap<String, Double>();
					shape2 = drawPanel.drawingEngine.getSupportedShapes().get(i).newInstance();
					
				} catch (InstantiationException e1) {e1.printStackTrace();}
				  catch (IllegalAccessException e1) {e1.printStackTrace();}
				cb.removeAllItems();
				if(shape2.getProperties()!=null && shape2.getProperties().keySet().size()!=0){
					for(String k : shape2.getProperties().keySet()){
						cb.addItem(k);
					}
				}
			}
		}
		if(e.getSource().equals(undo)){
			
			try {
				drawPanel.drawingEngine.undo();
				repaint();
			}catch (Exception e1){}
			
		}else if(e.getSource().equals(redo)){
			
			try{
				drawPanel.drawingEngine.redo();
				repaint();
			}catch (Exception e1){}
			
		}else if(e.getSource().equals(save)){
			
			JFileChooser c = new JFileChooser();
		    
		    int rVal = c.showSaveDialog(Application.this);
		    if (rVal == JFileChooser.APPROVE_OPTION) {
		    	path = c.getCurrentDirectory().toString() + File.separator + c.getSelectedFile().getName(); 
		    	try{ 
		    		drawPanel.drawingEngine.save(path);
		    		repaint();
		    	} catch (Exception e1){
		    		JOptionPane.showMessageDialog(null, "File Format not correct , It must be .xml or .json");
		    	}
		    }
		}else if(e.getSource().equals(load)){
			
			
			JFileChooser c = new JFileChooser();
		    int rVal = c.showOpenDialog(Application.this);
		    if (rVal == JFileChooser.APPROVE_OPTION) {
		    	path = c.getCurrentDirectory().toString() + File.separator + c.getSelectedFile().getName();
		    	try {
		    		drawPanel.drawingEngine.load(path);
		    		
			    	repaint();
		    	} catch (Exception e1){
		    		JOptionPane.showMessageDialog(null, "File Format not correct , It must be .xml or .json");
		    	}
		    }
		}else if(e.getSource().equals(draw)){
			
			
			if(getSelectedShape() != null){
				if(point1 == null){
					Point point = new Point(10, 10);
					getSelectedShape().setPosition(point);
				}else{
					getSelectedShape().setPosition(point1);
				}
				point1 = null;
				if(getSelectedShape().getProperties() == null || getSelectedShape().getProperties().keySet().size() == 0){
					isOk =true;
				}
				if(isOk == true){
					drawPanel.drawingEngine.addShape(getSelectedShape());
					isOk = false;
				}
				repaint();
			}
			
		}else if(e.getSource().equals(cb)){
			
			cb1 = (JComboBox)e.getSource();
			msg = (String)cb1.getSelectedItem();
		}else if(e.getSource().equals(ok)){
			if(!ans.getText().isEmpty()){
				String value = null;
				value = ans.getText().toString().replaceAll(" ", "");	
				index++;
				map.put(msg, Double.parseDouble(value));
				if(index >= shape2.getProperties().size()){
					shape2.setProperties(map);
					isOk = true;
					index = 0;
				}
				ans.setText("");
				
			}
		}
		else if(e.getSource().equals(zoomIn)){
			
			
			drawPanel.drawingEngine.updateShape(drawPanel.drawingEngine.getShapes()[shapeIndex], 
					getReplaceableShape(drawPanel.drawingEngine.getShapes()[shapeIndex],
							true, false, null, null));
			
			repaint();
		}
		else if(e.getSource().equals(zoomOut)){
			drawPanel.drawingEngine.updateShape(drawPanel.drawingEngine.getShapes()[shapeIndex], 
					getReplaceableShape(drawPanel.drawingEngine.getShapes()[shapeIndex],
							false, true, null, null));
			repaint();
		}
		else if(e.getSource().equals(remove)){
			drawPanel.drawingEngine.removeShape(drawPanel.drawingEngine.getShapes()[shapeIndex]);
			repaint();
		}
		else if(e.getSource().equals(chooseColor)){
			Color color = null;
			color = JColorChooser.showDialog(null, "choose a color", color);
			if(color!=null){
				drawPanel.drawingEngine.updateShape(drawPanel.drawingEngine.getShapes()[shapeIndex], 
						getReplaceableShape(drawPanel.drawingEngine.getShapes()[shapeIndex],
								false, false, color, null));
			}
			repaint();
		}
		else if(e.getSource().equals(chooseFillColor)){
			Color fillColor = null;
			fillColor = JColorChooser.showDialog(null, "choose a color", fillColor);
			if(fillColor != null){
				drawPanel.drawingEngine.updateShape(drawPanel.drawingEngine.getShapes()[shapeIndex],
						getReplaceableShape(drawPanel.drawingEngine.getShapes()[shapeIndex],
						false, false, null, fillColor));
			}
			repaint();
		}
	}
	private Shape getSelectedShape(){
		return shape2;
	}
	private void setAction(JMenu x){
		for(int i = 0 ; i < x.getItemCount() ; i++){
			x.getItem(i).addActionListener(this);
		}
	}
	private Map<String, Double> getMap(Shape shape, boolean zoomIn, boolean zoomOut){
		Map<String, Double> q = new HashMap<String, Double>();
		
		Object[] y = shape.getProperties().values().toArray();
		int x = 0;
		if(zoomIn == true){
			for(String g : shape.getProperties().keySet()){
				q.put(g, Double.parseDouble(y[x++].toString()) + 10.0);
			}
		}else if(zoomOut == true){
			for(String g : shape.getProperties().keySet()){
				q.put(g, Double.parseDouble(y[x++].toString()) - 10.0);
			}
		}else{
			for(String g : shape.getProperties().keySet()){
				q.put(g, Double.parseDouble(y[x++].toString()));
			}
		}
		return q;
	}
	public Shape getReplaceableShape(Shape shape, boolean zoomIn, boolean zoomOut,
			Color color, Color fillColor){
		Shape temp = null;
		try {
			temp = (Shape) shape.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(color!=null && temp.getClass().getSimpleName().equals("Line")){
			temp.setColor(color);
			temp.setFillColor(color);
		}
		if(fillColor!=null && temp.getClass().getSimpleName().equals("Line")){
			temp.setColor(fillColor);
			temp.setFillColor(fillColor);
		}
		if(color!=null){
			temp.setColor(color);
		}
		if(fillColor!=null){
			temp.setFillColor(fillColor);
		}
		if(zoomIn){
			temp.setProperties(getMap(shape, true, false));
		}else if(zoomOut){
			temp.setProperties(getMap(shape, false, true));
		}else{
			temp.setProperties(getMap(shape, false, false));
		}
		return temp;
	}
}