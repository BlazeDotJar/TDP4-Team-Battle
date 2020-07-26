package me.xxfreakdevxx.de.game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class MouseMotion extends MouseMotionAdapter {
	
	public static Location mouseLocation = null;
	public boolean flipWeapon = false;
	
	public static int quadrant = 0;
	private int halfWidth = 0;
	private int halfHeight = 0;
	
	@Override
	public void mouseMoved(MouseEvent e) {
		Camera camera = Game.getCamera();
		if(mouseLocation == null) mouseLocation = new Location(0, 0);
		if(camera == null)mouseLocation.setXY(e.getX(), e.getY());
		else mouseLocation.setXY(e.getX(), e.getY());
		
		/* Quadranten Indentifizierung */
		halfWidth = Game.windowWidth/2;
		halfHeight = Game.windowHeight/2;
		if(mouseLocation.getX() < halfWidth && mouseLocation.getY() < halfHeight) quadrant = 1;
		else if(mouseLocation.getX() > halfWidth && mouseLocation.getY() < halfHeight) quadrant = 2;
		else if(mouseLocation.getX() < halfWidth && mouseLocation.getY() > halfHeight) quadrant = 3;
		else if(mouseLocation.getX() > halfWidth && mouseLocation.getY() > halfHeight) quadrant = 4;
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		Camera camera = Game.getCamera();
		if(mouseLocation == null) mouseLocation = new Location(0, 0);
		if(camera == null)mouseLocation.setXY(e.getX(), e.getY());
		else mouseLocation.setXY(e.getX(), e.getY());
		
		/* Quadranten Indentifizierung */
		halfWidth = Game.windowWidth/2;
		halfHeight = Game.windowHeight/2;
		if(mouseLocation.getX() < halfWidth && mouseLocation.getY() < halfHeight) quadrant = 1;
		else if(mouseLocation.getX() > halfWidth && mouseLocation.getY() < halfHeight) quadrant = 2;
		else if(mouseLocation.getX() < halfWidth && mouseLocation.getY() > halfHeight) quadrant = 3;
		else if(mouseLocation.getX() > halfWidth && mouseLocation.getY() > halfHeight) quadrant = 4;
	}
	
}
