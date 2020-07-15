package me.xxfreakdevxx.de.game.object.entity.behavior;

import me.xxfreakdevxx.de.game.Game;
import me.xxfreakdevxx.de.game.Handler;
import me.xxfreakdevxx.de.game.object.GameObject;
import me.xxfreakdevxx.de.game.object.ID;
import me.xxfreakdevxx.de.game.object.Material;
import me.xxfreakdevxx.de.game.object.block.Block;
import me.xxfreakdevxx.de.game.object.entity.LivingEntity;

public class AttackBehavior extends Behavior {
	
	@SuppressWarnings("unused")
	private LivingEntity target = null;
	
	public AttackBehavior(LivingEntity entity) {
		super(entity);
	}
	int max = 100;
	int min = 0;

	public void tick() {
		if(min >= max) {
			min=0;
//			Game.getInstance().getHandler().addObject(new Bullet(ID.BULLET, getEntity().getLocation(), getEntity(), 8, 8, 0, 1));
//			Game.getInstance().getHandler().addObject(new Bullet(ID.BULLET, getEntity().getLocation(), getEntity(), 8, 8, 1, 0));
//			Game.getInstance().getHandler().addObject(new Bullet(ID.BULLET, getEntity().getLocation(), getEntity(), 8, 8, 0, -1));
//			Game.getInstance().getHandler().addObject(new Bullet(ID.BULLET, getEntity().getLocation(), getEntity(), 8, 8, -1, 0));
		}else{
			min++;
		}
//		collision();
//		if(target != null && !(target.isDead())) {
//			stopStandby();
//			int pX = target.getLocation().getIntX();
//			int pY = target.getLocation().getIntY();
//			int eX = getEntity().getLocation().getIntX();
//			int eY = getEntity().getLocation().getIntY();
//			
//			if(pX < eX) getEntity().getLocation().add((int)-(getEntity().getMovemetSpeed() / 2), 0);
//			else getEntity().getLocation().add((int)(getEntity().getMovemetSpeed() / 2), 0);
//			
//			if(pY < eY) getEntity().getLocation().add(0, (int)-(getEntity().getMovemetSpeed() / 2));
//			else getEntity().getLocation().add(0, (int)(getEntity().getMovemetSpeed() / 2));
//		}else{
//			standby();
//		}
	}
	public void collision() {
		Handler handler = Game.getInstance().getHandler();
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.BLOCK && ((Block) tempObject).getMaterial() != Material.BACKGROUND) {
				if(getEntity().getBounds().intersects(tempObject.getBounds())) {
					standby();
				}
			}
		}
	}
	
	public void onDamage(LivingEntity damager) {
		this.target = damager;
	}
	
	
	
}
