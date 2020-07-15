package me.xxfreakdevxx.de.game.object.wapon;

import java.awt.Color;

import me.xxfreakdevxx.de.game.object.entity.LivingEntity;

public class SteyrAug extends Weapon {

	public SteyrAug(LivingEntity shooter) {
		super(WeaponType.STEYR_AUG, shooter);
		bullet_color = new Color(95, 19, 189);
	}
	
}
