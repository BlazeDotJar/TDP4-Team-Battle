package me.xxfreakdevxx.de.game.object.block;

import java.awt.Graphics;

import me.xxfreakdevxx.de.game.Location;
import me.xxfreakdevxx.de.game.object.GameObject;
import me.xxfreakdevxx.de.game.object.ID;
import me.xxfreakdevxx.de.game.object.Material;

public abstract class Block extends GameObject {
	
	protected Material material;
	
	public Block(ID id, Location location, int width, int height) {
		super(id, location, width, height);
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	
	public Material getMaterial() {
		return material;
	}
	

}
