package me.xxfreakdevxx.de.game.object.wapon;

import java.awt.Point;
import java.util.Timer;
import java.util.TimerTask;

import me.xxfreakdevxx.de.game.object.WeaponType;
import me.xxfreakdevxx.de.game.object.entity.LivingEntity;

public abstract class Weapon {
	
	protected WeaponType type = WeaponType.FIST;
	protected Timer timer = null;
	protected TimerTask task = null;
	protected double shootIntervall = 0.36; //Seconds
	protected double damage_min = 1.1;
	protected double damage_max = 1.8;
	protected int ammo = 30;
	protected double reloadTime = 1.2; //seconds
	protected LivingEntity shooter = null;
	
	public Weapon(WeaponType type, LivingEntity shooter) {
		this.type = type;
		this.shooter = shooter;
	}
	
	private void shoot(Point p) {
		shoot(p.getX(), p.getY());
	}
	private void shoot(double x, double y) {
		/*
		 * Shuss abfeuern
		 */
	}
	public void trigger() {
		
	}
	
}
