package me.xxfreakdevxx.de.game.object.weapon;

import java.util.ArrayList;

public class WeaponHandler {
	
	public static ArrayList<Weapon> weapons = new ArrayList<Weapon>();
	
	
	public static void tick() {
		for(Weapon w : weapons) w.tick();
	}
	
}
