package me.xxfreakdevxx.de.game;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import me.xxfreakdevxx.de.game.object.Material;

public class TextureAtlas {
	
	private static BufferedImage image;
	private static HashMap<String, BufferedImage> textures;
	
	public TextureAtlas() {
		TextureAtlas.textures=new HashMap<String, BufferedImage>();
		reloadTextures();
	}
	
	public BufferedImage loadImage(String path) {
		try {
			image = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}
	public void reloadTextures() {
		/* Lädt alle Texturen neu und f�gt sie zum Atlas(textures(hashmap)) hinzu */
		textures.clear();
		for(int i = 0; i < Material.values().length; i++) {
			Material material = Material.values()[i];
			textures.put(material.getName(), loadImage("/"+material.getName()+".png"));
		}
		textures.put("monster", loadImage("/monster.png"));
		textures.put("steyer_aug", loadImage("/weapon/steyer_aug.png"));
		textures.put("muzzle_1", loadImage("/weapon/muzzle_1.png"));
		textures.put("muzzle_2", loadImage("/weapon/muzzle_2.png"));
		textures.put("muzzle_3", loadImage("/weapon/muzzle_3.png"));
	}
	
	public static HashMap<String, BufferedImage> getTextures() {
		return textures;
	}
	public static BufferedImage getTexture(String name) {
		return getTextures().get(name);
	}
	
}
