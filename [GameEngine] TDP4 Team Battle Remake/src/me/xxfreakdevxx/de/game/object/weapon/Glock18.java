package me.xxfreakdevxx.de.game.object.weapon;

import me.xxfreakdevxx.de.game.Game;
import me.xxfreakdevxx.de.game.object.entity.LivingEntity;

public class Glock18 extends Weapon {

	public Glock18(LivingEntity shooter) {
		super(WeaponType.GLOCK_18, shooter);
	}

	@Override
	public void playRandomShot() {
		switch(r.nextInt(2)) {
		case 0:
			Game.audiomanager.playByName("rifle_shot_1");
			break;
		case 1:
			Game.audiomanager.playByName("rifle_shot_2");
			break;
		}
	}
	
}
