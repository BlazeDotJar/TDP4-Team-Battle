package me.xxfreakdevxx.de.game.object;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import me.xxfreakdevxx.de.game.Location;

public abstract class GameObject {
	/* GameObject ist ein Object, das von anderen Klassen geerbt werden kann.
	 * Z.b. bei Entities */
	protected Location location;
	protected int width=32,height=32;
	public double velX = 0;
	public double velY = 0;
	protected ID id;
	protected BufferedImage texture;
	
	public GameObject(ID id, Location location, int width, int height) {
		this.id=id;
		this.location=location;
		this.width=width;
		this.height=height;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public double getVelX() {
		return velX;
	}

	public void setVelX(float velX) {
		this.velX = velX;
	}

	public double getVelY() {
		return velY;
	}

	public void setVelY(float velY) {
		this.velY = velY;
	}

	public BufferedImage getTexture() {
		return texture;
	}

	public void setTexture(BufferedImage texture) {
		this.texture = texture;
	}
	
	
}
