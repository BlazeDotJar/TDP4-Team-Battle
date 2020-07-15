package me.xxfreakdevxx.de.game.object.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Optional;
import java.util.Random;

import me.xxfreakdevxx.de.game.Game;
import me.xxfreakdevxx.de.game.Handler;
import me.xxfreakdevxx.de.game.Location;
import me.xxfreakdevxx.de.game.object.GameObject;
import me.xxfreakdevxx.de.game.object.ID;

public class Bullet extends GameObject {
	
	private Handler handler;
	private double damage = 5;
	public int size = 10;
	private LivingEntity shooter;
	private static int speed = 3;
	private GameVector locationVector = null;
	private GameVector mouseVector = null;
	private GameVector moveVector = null;
	Random r = new Random();
	public Color color = Color.ORANGE;
	
	public Bullet(ID id, Location location, LivingEntity shooter, int width, int height, int mx, int my) {
		super(id, location, width, height);
		this.shooter = shooter;
		this.handler = Game.getInstance().getHandler();
		
//		Game.audiomanager.playByName("Tink_"+r.nextInt(3));
		
		locationVector = new GameVector(location.getX(), location.getY());
		mouseVector = new GameVector(mx, my);
		mouseVector.normalize();
		moveVector = new GameVector(mouseVector.getX()-locationVector.getX(),
									mouseVector.getY()-locationVector.getY());

	}

	@Override
	public void tick() {
		getLocation().add(moveVector.getXVelocity(), moveVector.getYVelocity());
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.BLOCK) {
				if(getBounds().intersects(tempObject.getBounds())) {
					handler.removeObject(this);
//					Game.audiomanager.playByName("Dig_"+r.nextInt(3));
				}
			}else if(tempObject.getId() == ID.ENEMY && (tempObject != shooter)) {
				if(getBounds().intersects(tempObject.getBounds())) {
					((LivingEntity)tempObject).damage(damage, shooter);
					handler.removeObject(this);
					switch(r.nextInt(4)) {
						case 0:
//							Game.audiomanager.playByName("zombie-3-new");
							break;
						case 1:
//							Game.audiomanager.playByName("zombie-5-new");
							break;
						case 2:
//							Game.audiomanager.playByName("zombie-6-new");
							break;
						case 3:
//							Game.audiomanager.playByName("zombie-24-new");
							break;
					}
				}
			}
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(color);
		g.drawLine(((int)getLocation().getIntX()),
				   ((int)getLocation().getIntY()),
				   ((int)getLocation().clone().add(moveVector.getXVelocity()*size, 0).getIntX()),
				   ((int)getLocation().clone().add(0, moveVector.getYVelocity()*size).getIntY() ));
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(getLocation().getIntX(), getLocation().getIntY(), width, height);
	}
	
	public static class GameVector {
		
		double x=0;
		double y=0;
		double velX=0;
		double velY=0;
		
		boolean mxNegative = false;
		boolean myNegative = false;
		
		boolean isNormalized = false;
		
		public GameVector(double x, double y) {
			this.x=x;
			this.y=y;
//			if(x < 0) {mxNegative = true; System.out.println("x < 0 (x="+x+")"); x = x * (-1);}
//			if(y < 0) {myNegative = true; System.out.println("y < 0 (y="+y+")"); y = y * (-1);}
		}
		
		public double getXVelocity() {
			normalize();
			return velX;
		}
		public double getYVelocity() {
			normalize();
			return velY;
		}
		public double getX() {
			return x;
		}
		public double getY() {
			return y;
		}
		
		public void normalize() {
			if(isNormalized == false) {				
				double l /* L�nge */ = Math.sqrt( ( x * x ) + ( y * y ) );
				velX = (x / l) * speed;
				velY = (y / l) * speed;
				
//				System.out.println("l = "+l);
//				System.out.println("velX = "+velX);
//				System.out.println("velY = "+velY);
				
//			if(mxNegative) velX = velX * (-1);
//			if(myNegative) velY = velY * (-1);
//			
//			System.out.println("velX2 = "+velX);
//			System.out.println("velY2 = "+velY);
				isNormalized = true;
//				calculateIntersectionPoint(1, 1, 1, 1).get().
			}
		}
		
		public Optional<Point> calculateIntersectionPoint(
			    double m1, 
			    double b1, 
			    double m2, 
			    double b2) {
			 
			    if (m1 == m2) {
			        return Optional.empty();
			    }
			 
			    double x = (b2 - b1) / (m1 - m2);
			    double y = m1 * x + b1;
			 
			    Point point = new Point();
			    point.setLocation(x, y);
			    return Optional.of(point);
			}
		
	}
	public Bullet setColor(Color color) {
		this.color = color;
		return this;
	}
	
}
