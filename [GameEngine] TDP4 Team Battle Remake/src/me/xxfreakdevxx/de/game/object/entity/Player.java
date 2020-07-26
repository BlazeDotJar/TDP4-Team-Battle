package me.xxfreakdevxx.de.game.object.entity;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import me.xxfreakdevxx.de.game.Game;
import me.xxfreakdevxx.de.game.Handler;
import me.xxfreakdevxx.de.game.Location;
import me.xxfreakdevxx.de.game.MouseMotion;
import me.xxfreakdevxx.de.game.object.ID;
import me.xxfreakdevxx.de.game.object.weapon.SteyerAug;
import me.xxfreakdevxx.de.game.object.weapon.Weapon;

public class Player extends LivingEntity {
	
	public AffineTransform at = null;
	public double angle;
	public double rotate;
	
	public Player(Location location, double health) {
		super(ID.PLAYER, location, 20, 20, health);
		this.handler = Game.getInstance().getHandler();
		this.weapon = new SteyerAug(this);
	}
	
	public Weapon weapon = null;
	double mouse_width = 0;
	double mouse_height = 0; 
	Handler handler;
	


	@Override
	public void tick() {

		getLocation().add(velX, velY);
		
		collision();
		
		/* Graphic */
		if(MouseMotion.mouseLocation != null) {
			int x = (int) Game.getCamera().getX();
			int y = (int) Game.getCamera().getY();
			
			
			mouse_width = MouseMotion.mouseLocation.getX() - (getLocation().getX() - x + getBounds().width / 2);
			mouse_height = MouseMotion.mouseLocation.getY() - (getLocation().getY() - y + getBounds().height / 2);
			
			angle = Math.atan(mouse_width / mouse_height);
			
			if(width < 0)
				angle = -Math.PI + angle;
			
            int xx = (int) MouseMotion.mouseLocation.getX();
            int yy = (int) MouseMotion.mouseLocation.getY();  
            double x_dist = xx - getLocation().getX()+Game.getCamera().getX();
            double y_dist = yy - getLocation().getY()+Game.getCamera().getY()-5;
            angle = Math.atan2( y_dist , x_dist );  
            rotate = angle * ( 180 / Math.PI );  
			
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
		Graphics2D g2d = (Graphics2D)g.create();
        g2d.rotate( Math.toRadians( Game.player.rotate ), getLocation().getIntX(), getLocation().getIntY());
        switch(MouseMotion.quadrant) {
        case 1:
        	g2d.drawImage(createFlipped(weapon.getTexture()), getLocation().getIntX(), getLocation().getIntY(), 60, 20, null);
        	break;
        case 2:
        	g2d.drawImage(              weapon.getTexture(),  getLocation().getIntX(), getLocation().getIntY(), 60, 20, null);
        	break;
        case 3:
        	g2d.drawImage(createFlipped(weapon.getTexture()), getLocation().getIntX(), getLocation().getIntY(), 60, 20, null);
        	break;
        case 4:
        	g2d.drawImage(              weapon.getTexture(),  getLocation().getIntX(), getLocation().getIntY(), 60, 20, null);
        	break;
        }
        
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

	
    private static BufferedImage createFlipped(BufferedImage image)
    {
        AffineTransform at = new AffineTransform();
        at.concatenate(AffineTransform.getScaleInstance(1, -1));
        at.concatenate(AffineTransform.getTranslateInstance(0, -image.getHeight()));
        return createTransformed(image, at);
    }

    private static BufferedImage createRotated(BufferedImage image)
    {
        AffineTransform at = AffineTransform.getRotateInstance(
            Math.PI, image.getWidth()/2, image.getHeight()/2.0);
        return createTransformed(image, at);
    }
    private static BufferedImage createTransformed(BufferedImage image, AffineTransform at) {
    	BufferedImage newImage = new BufferedImage( image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
    	Graphics2D g = newImage.createGraphics();
    	g.transform(at);
    	g.drawImage(image, 0, 0, null);
    	g.dispose();
    	return newImage;
    }
}
