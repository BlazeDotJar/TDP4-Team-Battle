package me.xxfreakdevxx.de.game;

import me.xxfreakdevxx.de.game.object.GameObject;

public class Camera {
	private float x,y;
	
	public Camera(float x, float y) {
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

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
}
