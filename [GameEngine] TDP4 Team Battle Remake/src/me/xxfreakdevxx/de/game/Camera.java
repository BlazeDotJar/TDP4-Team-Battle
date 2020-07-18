package me.xxfreakdevxx.de.game;

import me.xxfreakdevxx.de.game.object.GameObject;

public class Camera {
	private double x,y;
	
	public Camera(double x, double y) {
		this.x=x;
		this.y=y;
	}
	
	public void tick(GameObject object) {
		x += ((object.getLocation().getX() - x) - Game.windowWidth/2) * 0.05f;
		y += ((object.getLocation().getY() - y) - Game.windowHeight/2) * 0.05f;
	
		if(x <= 0) x = 0; 
		if(x >= (Game.windowWidth + 32)) x = (Game.windowWidth+32);
		if(y <= 0) y = 0; 
		if(x >= (Game.windowHeight + 16)) x = (Game.windowHeight+16);
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
}
