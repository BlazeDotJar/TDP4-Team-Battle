package me.xxfreakdevxx.de.game;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import me.xxfreakdevxx.de.game.object.GameObject;
import me.xxfreakdevxx.de.game.object.ID;
import me.xxfreakdevxx.de.game.object.entity.Bullet;
import me.xxfreakdevxx.de.game.object.entity.LivingEntity;

public class MouseInput extends MouseAdapter {

	public boolean isPressed = false;
	
	public MouseInput() {}
	
	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) {			
			isPressed = true;
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1) {			
			isPressed = false;
		}
	}


	
	Camera camera = null;
	int shootIntervall = 120;
	int cooldown = 0;
	
	public void shoot() {
		if(cooldown == shootIntervall) {		
			if(isPressed == false) return;

			camera = Game.getInstance().getCamera();
			int mx = (int) (MouseMotion.mouseLocation.getIntX() + camera.getX());
			int my = (int) (MouseMotion.mouseLocation.getIntY() + camera.getY());
			
			for(int i = 0; i < Game.getInstance().getHandler().object.size(); i++) {
				GameObject tempObject = Game.getInstance().getHandler().object.get(i);
				
				if(tempObject.getId() == ID.PLAYER) {
					int w = (int)tempObject.getBounds().getWidth();
					int h = (int)tempObject.getBounds().getHeight();
					
					int pX = tempObject.getLocation().getIntX()+(w/2);
					int pY = tempObject.getLocation().getIntY()+(h/2);
					int sizeX = 8;
					int sizeY = 8;
					Game.getInstance().getHandler().addObject(new Bullet(ID.BULLET, new Location(pX, pY), ((LivingEntity)tempObject), sizeX, sizeY, mx, my));
				}
			}
			cooldown = 0;
		}else cooldown++;
	}
}
