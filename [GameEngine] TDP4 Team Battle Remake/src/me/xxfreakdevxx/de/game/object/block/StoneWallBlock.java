package me.xxfreakdevxx.de.game.object.block;

import java.awt.Graphics;
import java.awt.Rectangle;

import me.xxfreakdevxx.de.game.Game;
import me.xxfreakdevxx.de.game.Location;
import me.xxfreakdevxx.de.game.object.ID;
import me.xxfreakdevxx.de.game.object.Material;

public class StoneWallBlock extends Block {

	public StoneWallBlock(ID id, Location location, int width, int height) {
		super(id, location, width, height);
		this.material = Material.STONE_WALL;
		setTexture(Game.getInstance().getTextureAtlas().getTextures().get(material.getName()));
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(texture, getLocation().getIntX(), getLocation().getIntY(), Game.blocksize, Game.blocksize, null, null);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(getLocation().getIntX(), getLocation().getIntY(), Game.blocksize, Game.blocksize);
	}

}
