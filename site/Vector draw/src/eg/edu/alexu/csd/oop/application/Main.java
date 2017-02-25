package eg.edu.alexu.csd.oop.application;

import java.util.LinkedList;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		
		Application g = new Application();
		g.setVisible(true);
		g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		g.setSize(2000, 725);
		g.setResizable(false);
	}

}