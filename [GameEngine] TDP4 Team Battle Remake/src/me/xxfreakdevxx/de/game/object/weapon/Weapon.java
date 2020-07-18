package me.xxfreakdevxx.de.game.object.weapon;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Random;

import me.xxfreakdevxx.de.game.Camera;
import me.xxfreakdevxx.de.game.Game;
import me.xxfreakdevxx.de.game.Location;
import me.xxfreakdevxx.de.game.MouseMotion;
import me.xxfreakdevxx.de.game.TextureAtlas;
import me.xxfreakdevxx.de.game.object.entity.Bullet;
import me.xxfreakdevxx.de.game.object.entity.LivingEntity;

public abstract class Weapon {
	
	protected WeaponType type = WeaponType.FIST;
	
	public Random r = new Random();
	protected double intervall = 0d;
	protected double shootIntervall = 0.66d; //Seconds
	protected double damage_min = 1.1d;
	protected double damage_max = 1.8d;
	protected int reloadTime = 1200; //Seconds
	protected int ammo = 30;
	public int current_ammo = 30;
	protected LivingEntity shooter = null;
	protected Point targetLocation = null;
	protected boolean isTriggered = false;
	protected boolean isReloading = false;
	public Color bullet_color = Color.ORANGE;
	protected BufferedImage texture = null;
	
	public Weapon(WeaponType type, LivingEntity shooter) {
		this.type = type;
		this.shooter = shooter;
		this.shootIntervall = type.shootIntervall;
		this.damage_min = type.damage_min;
		this.damage_max = type.damage_max;
		this.reloadTime = type.reloadTime;
		this.ammo = type.ammo;
		this.current_ammo = ammo;
		this.texture = TextureAtlas.getTexture(type.getName());
		WeaponHandler.weapons.add(this);
	}
	
	private void shoot(Point p) {
		targetLocation = (Point) p.clone();
		shoot(p.getX(), p.getY());
	}
	Camera camera = null;
	private void shoot(double x, double y) {
		/*
		 * Shuss abfeuern
		 */
		if(current_ammo <= 0) reload();
		if(intervall < shootIntervall) {
			intervall+=1;
			return;
		}else intervall=0;
		if(current_ammo > 0 && isReloading == false && isTriggered) {
			camera = Game.getCamera();
			int mx = (int) (MouseMotion.mouseLocation.getIntX() + camera.getX());
			int my = (int) (MouseMotion.mouseLocation.getIntY() + camera.getY());
			
			int w = (int)Game.player.getBounds().getWidth();
			int h = (int)Game.player.getBounds().getHeight();
			
			int pX = Game.player.getLocation().getIntX()+(w/2);
			int pY = Game.player.getLocation().getIntY()+(h/2);
			int sizeX = 8;
			int sizeY = 8;
			Game.getInstance().getHandler().addObject(new Bullet(new Location(pX, pY), Game.player, sizeX, sizeY, mx, my).setColor(bullet_color));
			playRandomShot();
			current_ammo--;
		}
	}
	public int reload = 0;
	public void reload() {
		if(current_ammo == ammo) return;
		if(isReloading == false) Game.audiomanager.playByName("gun_cock");
		current_ammo=0;
		if(current_ammo <= 0) {
			isReloading = true;
			if(reload == reloadTime) {
				intervall=0;
				current_ammo = ammo;
				isReloading = false;
				reload = 0;
				Game.audiomanager.playByName("gun_cocked");
			}else reload+=1;
		}
	}
	public void tick() {
		if(MouseMotion.mouseLocation != null)shoot(new Point(MouseMotion.mouseLocation.getIntX(), MouseMotion.mouseLocation.getIntY()));
		else System.out.println("Mouselocation == null");
	}
	public void trigger() {
		isTriggered = true;
		if(MouseMotion.mouseLocation != null)shoot(new Point(MouseMotion.mouseLocation.getIntX(), MouseMotion.mouseLocation.getIntY()));
		else System.out.println("Mouselocation == null");
	}
	public void stopTrigger() {
		isTriggered = false;
	}
	public WeaponType getType() {
		return type;
	}
	public BufferedImage getTexture() {
		return texture;
	}
	public abstract void playRandomShot();
	
}
