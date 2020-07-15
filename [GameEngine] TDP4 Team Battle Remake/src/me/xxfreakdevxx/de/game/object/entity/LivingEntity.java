package me.xxfreakdevxx.de.game.object.entity;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import me.xxfreakdevxx.de.game.Location;
import me.xxfreakdevxx.de.game.object.GameObject;
import me.xxfreakdevxx.de.game.object.ID;

public abstract class LivingEntity extends GameObject {
	
	/* Variables */
	protected final int damageIndicatorCooldown = 120;
	protected boolean showIndicator = false;
	protected int indicatorTime = 0;
	protected double health = 20.0D;
	protected double maxHealth = 20.0D;
	protected float movementSpeed = 0f;
	protected boolean isDead = false;
	
	public LivingEntity(ID id, Location location, int width, int height, double health) {
		super(id, location, width, height);
		this.maxHealth=health;
		this.health=health;
	}

	
	public abstract void render(Graphics g);
	public abstract void remove();
	public abstract void damage(double damage, LivingEntity shooter);
	
	/* Healthbar */
	private double healthbarPrefferedLength = 100;
	private double healthbarHeight = 3;
	private Color healthColorFriendly = new Color(21, 221, 17);
	private Color healthColorEnemy = new Color(235, 19, 22);
	private String name = "LivingEntity";/* Max 12 Chars */
	private int healthbarX = 0;
	private int healthbarY = 0;
	public void renderHealthbar(Graphics g) {
		healthbarX = (int) ((getLocation().getIntX() + (width/2)) - (healthbarPrefferedLength/2));
		healthbarY = (int) (getLocation().getIntY() - (healthbarHeight));
		double one = maxHealth / 100;
		double total = health / one;
		g.setColor(Color.BLACK);
		g.fillRect(healthbarX, healthbarY, (int) healthbarPrefferedLength, (int) healthbarHeight);
		if(getId() == ID.PLAYER) g.setColor(healthColorFriendly);
		if(getId() == ID.ENEMY) g.setColor(healthColorEnemy);
		g.fillRect(healthbarX, healthbarY, (int) ((int) total), (int) healthbarHeight);
	}
	public void renderDisplayname(Graphics g) {
		g.setFont(new Font("consolas", 0, 14));
		g.setColor(new Color(0f,0f,0f,0.4f));
		g.fillRect((getLocation().getIntX()+(width/2) - 50), (int) (getLocation().getIntY()-19-healthbarHeight), 100, 16);
		g.setColor(Color.WHITE);
		g.drawString(name, (getLocation().getIntX()+(width/2) - 48), getLocation().getIntY()-7-(int)healthbarHeight);
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public double getHealth() {
		return health;
	}

	public void setHealth(double health) {
		this.health = health;
	}
	
	public double getMaxHealth() {
		return health;
	}

	public void setMaxHealth(double health) {
		this.health = health;
	}
	
	public float getMovemetSpeed() {
		return movementSpeed;
	}
	
	public void setMovemetSpeed(int movementSpeed) {
		this.movementSpeed = movementSpeed;
	}


	public boolean isDead() {
		return isDead;
	}
	
	
}
