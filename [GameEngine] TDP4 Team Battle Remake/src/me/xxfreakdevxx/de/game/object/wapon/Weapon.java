package me.xxfreakdevxx.de.game.object.wapon;

import java.awt.Color;
import java.awt.Point;

import me.xxfreakdevxx.de.game.Camera;
import me.xxfreakdevxx.de.game.Game;
import me.xxfreakdevxx.de.game.Location;
import me.xxfreakdevxx.de.game.MouseMotion;
import me.xxfreakdevxx.de.game.object.GameObject;
import me.xxfreakdevxx.de.game.object.ID;
import me.xxfreakdevxx.de.game.object.entity.Bullet;
import me.xxfreakdevxx.de.game.object.entity.LivingEntity;

public abstract class Weapon {
	
	protected WeaponType type = WeaponType.FIST;
	
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
	
	public Weapon(WeaponType type, LivingEntity shooter) {
		this.type = type;
		this.shooter = shooter;
		this.shootIntervall = type.shootIntervall;
		this.damage_min = type.damage_min;
		this.damage_max = type.damage_max;
		this.reloadTime = type.reloadTime;
		this.ammo = type.ammo;
		this.current_ammo = ammo;
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
					Game.getInstance().getHandler().addObject(new Bullet(ID.BULLET, new Location(pX, pY), ((LivingEntity)tempObject), sizeX, sizeY, mx, my).setColor(bullet_color));
				}
			}
			current_ammo--;
		}
	}
	public int reload = 0;
	public void reload() {
		current_ammo=0;
		if(current_ammo <= 0) {		
			isReloading = true;
			if(reload == reloadTime) {
				intervall=0;
				current_ammo = ammo;
				isReloading = false;
				reload = 0;
			}else reload+=1;
		}
	}
	public void tick() {
		if(MouseMotion.mouseLocation != null)shoot(new Point(MouseMotion.mouseLocation.getIntX(), MouseMotion.mouseLocation.getIntY()));
	}
	public void trigger() {
		isTriggered = true;
	}
	public void stopTrigger() {
		isTriggered = false;
	}
	public WeaponType getType() {
		return type;
	}
	
}
