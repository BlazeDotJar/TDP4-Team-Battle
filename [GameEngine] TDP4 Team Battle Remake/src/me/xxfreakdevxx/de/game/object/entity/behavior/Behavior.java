package me.xxfreakdevxx.de.game.object.entity.behavior;

import me.xxfreakdevxx.de.game.object.entity.LivingEntity;

public abstract class Behavior {
	
	private LivingEntity entity;
	protected boolean standby = false;
	
	public Behavior(LivingEntity entity) {
		this.entity=entity;
	}
	
	public abstract void tick();
	public abstract void collision();
	
	public void standby() {
		standby = true;
	}
	public void stopStandby() {
		standby = false;
	}
	
	public LivingEntity getEntity() {
		return entity;
	}
	
}
