package me.xxfreakdevxx.de.game.object.manager;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import me.xxfreakdevxx.de.game.Location;

public class PlatformManager {
	
	private static ArrayList<Platform> platforms = new ArrayList<Platform>();
	private static ArrayList<Hitbox> hitboxes = new ArrayList<Hitbox>();
	
	public PlatformManager() {
		addPlatform(new Platform(350, 90, 500, 40));//Oben
		addPlatform(new Platform(350, 610, 200, 40));//Unten links
		addPlatform(new Platform(650, 610, 200, 40));//Unten rechts
		addPlatform(new Platform(320, 120, 40, 500));//links
		addPlatform(new Platform(840, 120, 40, 500));//rechts
	}
	
	public static void addPlatform(Platform p) {
		platforms.add(p);
	}
	
	public static void removePlatform(Platform p) {
		platforms.remove(p);
	}
	
	public static void render(Graphics g) {
		for(Platform p : platforms) p.render(g);
		for(Hitbox h : hitboxes) h.render(g);
	}
	
	public static boolean isCollidingWithPlatform(Location loc) {
		for(Platform p : platforms) if(p.isColiding(loc)) return true;
		return false;
	}
	
	public static class Platform {
		
		public int x = 0;
		public int y = 0;
		public int w = 0;
		public int h = 0;
		public Color fillcolor = new Color(0.203921f, 0.203921f, 0.921562f, 0.5f);
		public Color bordercolor = new Color(52, 52, 235);
		
		public Platform(int x, int y, int w, int h) {
			this.x = x;
			this.y = y;
			this.w = w;
			this.h = h;
		}
		
		public void render(Graphics g) {
			g.setColor(fillcolor);
			g.fillRect(x, y, w, h);
			g.setColor(bordercolor);
			g.drawRect(x, y, w, h);
		}
		
		public boolean isColiding(Location loc) {
			if(loc.getX() > x && loc.getX() < (x + w) && loc.getY() > y && loc.getY() < (y + h)) return true;
			else return false;
		}
		
	}
	
	public static class Hitbox {
		
		public int x = 0;
		public int y = 0;
		public int w = 0;
		public int h = 0;
		public Color fillcolor = new Color(0.203921f, 0.203921f, 0.921562f, 0.5f);
		public Color bordercolor = new Color(52, 52, 235);
		
		public Hitbox(int x, int y, int w, int h) {
			this.x = x;
			this.y = y;
			this.w = w;
			this.h = h;
		}
		
		public void render(Graphics g) {
			g.setColor(fillcolor);
			g.fillRect(x, y, w, h);
			g.setColor(bordercolor);
			g.drawRect(x, y, w, h);
		}
		
		public boolean isColiding(Location loc) {
			if(loc.getX() > x && loc.getX() < (x + w) && loc.getY() > y && loc.getY() < (y + h)) return true;
			else return false;
		}
		
	}
	
}
