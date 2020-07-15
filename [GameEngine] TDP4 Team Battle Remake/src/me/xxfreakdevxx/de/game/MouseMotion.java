package me.xxfreakdevxx.de.game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class MouseMotion extends MouseMotionAdapter {
	
	public static Location mouseLocation = null;
	
	@Override
	public void mouseMoved(MouseEvent e) {
		Camera camera = Game.getInstance().getCamera();
		if(mouseLocation == null) mouseLocation = new Location(0, 0);
		if(camera == null)mouseLocation.setXY(e.getX(), e.getY());
		else mouseLocation.setXY(e.getX(), e.getY());
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		Camera camera = Game.getInstance().getCamera();
		if(mouseLocation == null) mouseLocation = new Location(0, 0);
		if(camera == null)mouseLocation.setXY(e.getX(), e.getY());
		else mouseLocation.setXY(e.getX(), e.getY());
	}
	
}
