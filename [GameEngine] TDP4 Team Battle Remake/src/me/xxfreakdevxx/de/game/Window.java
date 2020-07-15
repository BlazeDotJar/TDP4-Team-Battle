package me.xxfreakdevxx.de.game;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window {
	
	JFrame frame = null;
	public Window(int width, int height, String title, Game game) {
		frame = new JFrame(title);
		
		frame.setPreferredSize(new Dimension(width, height));
//		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		
		frame.add(game);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
}
