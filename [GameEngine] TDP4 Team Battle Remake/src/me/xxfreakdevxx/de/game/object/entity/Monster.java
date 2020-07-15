package me.xxfreakdevxx.de.game.object.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import me.xxfreakdevxx.de.game.Game;
import me.xxfreakdevxx.de.game.Location;
import me.xxfreakdevxx.de.game.object.ID;
import me.xxfreakdevxx.de.game.object.entity.behavior.AttackBehavior;

public class Monster extends LivingEntity {
	
	private AttackBehavior attackBehavior;
	
	public Monster(ID id, Location location, int width, int height, double health) {
		super(id, location, width, height, health);
		this.attackBehavior=new AttackBehavior(this);
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Game.getInstance().getTextureAtlas().getTextures().get("monster"), location.getIntX(), location.getIntY(), width, height, null);
		renderHealthbar(g);
		renderDisplayname(g);
		
		if(showIndicator) {
			g.setColor(new Color(1f,0f,0f,0.3f));
			g.fillRect(getLocation().getIntX()+2,getLocation().getIntY()+2, width-4, height-4);
		}
	}

	@Override
	public void remove() {
		this.isDead=true;
		Game.getInstance().getHandler().removeObject(this);
	}

	@Override
	public void damage(double damage, LivingEntity shooter) {
		attackBehavior.onDamage(shooter);
		if(!((health - damage) <= 0)) {
			health-=damage;
			showIndicator = true;
			indicatorTime = 0;
		}else{
			remove();
		}
	}

	@Override
	public void tick() {
		attackBehavior.tick();
		if(showIndicator) {			
			if(indicatorTime >= damageIndicatorCooldown) {
				showIndicator = false;
				indicatorTime = 0;
			}else{
				indicatorTime += 1;
			}
		}
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(location.getIntX(), location.getIntY(), width, height);
	}

}
