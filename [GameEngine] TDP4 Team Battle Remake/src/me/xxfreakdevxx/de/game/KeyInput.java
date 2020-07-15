package me.xxfreakdevxx.de.game;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.concurrent.ConcurrentLinkedQueue;

import me.xxfreakdevxx.de.game.object.wapon.Glock18;
import me.xxfreakdevxx.de.game.object.wapon.SteyrAug;

public class KeyInput extends KeyAdapter {
	
	public ConcurrentLinkedQueue<Integer> pressed_keys = new ConcurrentLinkedQueue<Integer>();
	public boolean use_W_TpJump = true;
	public float movement_speed = 4.5f;
	
	public KeyInput() {}

	
	private int ticked = 0;
	public void tick() {
		ticked++;
		if(ticked == 10) ticked = 0;
		else return;
		for(int key : pressed_keys) {
			switch(key) {
			case KeyEvent.VK_W:
				Game.getInstance().player.getLocation().add(0, -movement_speed);
				break;
			case KeyEvent.VK_A:
				Game.getInstance().player.getLocation().add(-movement_speed, 0);
				break;
			case KeyEvent.VK_S:
				Game.getInstance().player.getLocation().add(0, movement_speed);
				break;
			case KeyEvent.VK_D:
				Game.getInstance().player.getLocation().add(movement_speed, 0);
				break;
			case KeyEvent.VK_X:
				Game.getInstance().player.weapon = new SteyrAug(Game.getInstance().player);
				break;
			case KeyEvent.VK_C:
				Game.getInstance().player.weapon = new Glock18(Game.getInstance().player);
				break;
			case KeyEvent.VK_R:
				Game.getInstance().player.weapon.reload();
				break;
				
			}
		}
	}
	int height = 20;
	int space = 2;
	int amount = 1;
	public void render(Graphics g) {
		if(pressed_keys.isEmpty()) {
			g.drawString("Kein Cooldown registriert", 10, 10+space+height);
		}else {
			for(int key : pressed_keys) {
				g.drawString("Key: "+KeyEvent.getKeyText(key), 10, 10+((amount+1)*space)+(amount*height));
				amount++;
			}
		}
		amount=1;
	}
	public void press(int key) {
		if(pressed_keys.contains(key) == false) {
			pressed_keys.add(key);
		}
	}
	public void release(int key) {
		pressed_keys.remove(key);
		//Spieler Movement zur�cksetzen
		switch(key) {
		case KeyEvent.VK_SPACE:
			break;

		}
	}
	
	//TODO: Get Interaction
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		press(key);
	}
	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		release(key);
	}
	
	
}
