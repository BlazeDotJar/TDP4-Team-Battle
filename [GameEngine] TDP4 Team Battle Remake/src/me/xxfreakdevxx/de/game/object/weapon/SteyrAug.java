package me.xxfreakdevxx.de.game.object.weapon;

import java.awt.Color;

import me.xxfreakdevxx.de.game.Game;
import me.xxfreakdevxx.de.game.object.entity.LivingEntity;

public class SteyrAug extends Weapon {

	public SteyrAug(LivingEntity shooter) {
		super(WeaponType.STEYER_AUG, shooter);
		bullet_color = new Color(3, 252, 252);
	}
	
	public void playRandomShot() {
		switch(r.nextInt(2)) {
		case 0:
			Game.audiomanager.playByName("steyer_aug_shot");
			break;
		case 1:
			Game.audiomanager.playByName("steyer_aug_shot_1");
			break;
		}
	}
	
}
