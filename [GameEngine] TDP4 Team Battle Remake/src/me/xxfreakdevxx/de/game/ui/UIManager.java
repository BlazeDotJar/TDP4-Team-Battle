package me.xxfreakdevxx.de.game.ui;

import java.awt.Color;
import java.awt.Graphics;

import me.xxfreakdevxx.de.game.object.entity.Player;

public class UIManager {
	
	public static void render(Graphics g, Player p) {
//		g.setFont(new Font("Candara", 20, 1));
		g.setColor(Color.BLACK);
		g.drawString("Weapon: "+p.weapon.getType().getDisplayname(), 10, 90);
		g.drawString("Reload: "+p.weapon.reload, 10, 105);
		g.drawString("Ammo: "+p.weapon.current_ammo, 10, 120);
	}
	
}
