package me.xxfreakdevxx.de.game;

public class Location {
	
	private double x, y;
	
	public Location(double x, double y) {
		this.x=x;
		this.y=y;
	}
	public Location clone() {
		return new Location(getX(), getY());
	}
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public Location setX(double x) {
		this.x=x;
		return this;
	}
	public Location setY(double y) {
		this.y=y;
		return this;
	}
	public Location setXY(double x, double y) {
		this.x=x;
		this.y=y;
		return this;
	}
	public Location add(double x, double y) {
		this.x+=x;
		this.y+=y;
		return this;
	}

	public int getIntX() {
		return (int)x;
	}
	public int getIntY() {
		return (int)y;
	}
	
}
