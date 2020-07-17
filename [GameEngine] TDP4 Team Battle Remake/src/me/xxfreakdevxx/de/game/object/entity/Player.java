package me.xxfreakdevxx.de.game.object.entity;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

import me.xxfreakdevxx.de.game.Game;
import me.xxfreakdevxx.de.game.Handler;
import me.xxfreakdevxx.de.game.Location;
import me.xxfreakdevxx.de.game.MouseMotion;
import me.xxfreakdevxx.de.game.TextureAtlas;
import me.xxfreakdevxx.de.game.object.ID;
import me.xxfreakdevxx.de.game.object.Material;
import me.xxfreakdevxx.de.game.object.weapon.SteyrAug;
import me.xxfreakdevxx.de.game.object.weapon.Weapon;

public class Player extends LivingEntity {
	
	public AffineTransform at = null;
	public double angle;
	
	public Player(Location location, double health) {
		super(ID.PLAYER, location, 20, 20, health);
		this.handler = Game.getInstance().getHandler();
		this.weapon = new SteyrAug(this);
	}
	
	public Weapon weapon = null;

	Handler handler;
	


	@Override
	public void tick() {
		getLocation().add(velX, velY);
		
		collision();
		
		/* Graphic */
		if(MouseMotion.mouseLocation != null) {
			int x = (int) Game.getInstance().getCamera().getX();
			int y = (int) Game.getInstance().getCamera().getY();
			
			
			double width = MouseMotion.mouseLocation.getX() - (getLocation().getX()  - x + 20);
			double height = MouseMotion.mouseLocation.getY() - (getLocation().getY() - y + 20);
			
			angle = Math.atan(height / width);
			
			if(width < 0)
				angle = -Math.PI + angle;
			
			
			at = AffineTransform.getTranslateInstance(getLocation().getX() - x, getLocation().getY() - y);
			
			
			at.rotate(angle, 20, 20);
		}
		

		
		/* Movement */
		if(handler.isUp()) velY = -movementSpeed;
		else if(!handler.isDown()) velY = 0;
		
		if(handler.isDown()) velY = movementSpeed;
		else if(!handler.isUp()) velY = 0;
		
		if(handler.isLeft()) velX = -movementSpeed;
		else if(!handler.isRight()) velX = 0;
		
		if(handler.isRight()) velX = movementSpeed;
		else if(!handler.isLeft()) velX = 0;
		
		if(showIndicator) {			
			if(indicatorTime >= damageIndicatorCooldown) {
				showIndicator = false;
				indicatorTime = 0;
			}else{
				indicatorTime += 1;
			}
		}
	}
	
	private void collision() {
//		for(int i = 0; i < handler.object.size(); i++) {
//			GameObject tempObject = handler.object.get(i);
//			if(tempObject.getId() == ID.BLOCK && ((Block) tempObject).getMaterial() != Material.BACKGROUND) {
//				if(getBounds().intersects(tempObject.getBounds())) {
//					getLocation().add((velX * -1), (velY * -1));
//				}
//			}
//		}
	}

	@Override
	public void render(Graphics g) {
		renderDisplayname(g);
		renderHealthbar(g);
		g.setColor(Color.ORANGE);
		g.fillRect(getLocation().getIntX(), getLocation().getIntY(), width, height);
		Graphics2D g2d = (Graphics2D)g;
		
		if(at != null) g2d.drawImage(Game.getInstance().getTextureAtlas().getTexture(Material.STONE_WALL.getName()), at, null);
//		/* Displayname */
//		g.setColor(new Color(0f,0f,0f,0.4f));
//		g.fillRect((getLocation().getIntX()+(width/2) - 22), getLocation().getIntY()-19, 58, 16);
//		g.setColor(Color.WHITE);
//		g.drawString("Player", (getLocation().getIntX()+(width/2) - 17), getLocation().getIntY()-7);
		if(showIndicator) {
			g.setColor(new Color(1f,0f,0f,0.3f));
			g.fillRect(getLocation().getIntX()+2,getLocation().getIntY()+2, width-4, height-4);
		}
	}

	@Override
	public Rectangle getBounds() {
		/* Hitbox */
		return new Rectangle(getLocation().getIntX(), getLocation().getIntY(), width, height);
	}

	@Override
	public void remove() {
		this.isDead=true;
	}

	@Override
	public void damage(double damage, LivingEntity shooter) {
		if(!((health - damage) <= 0)) {
			health-=damage;
			showIndicator = true;
			indicatorTime = 0;
		}else{
			remove();
		}
	}

}
