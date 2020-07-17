package me.xxfreakdevxx.de.game.audio;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import me.xxfreakdevxx.de.game.object.weapon.WeaponType;

public class AudioManager {
	
	private HashMap<String, GameAudio> audioAtlas = new HashMap<String, GameAudio>();
	private List<GameAudio> audioQueue = new LinkedList<GameAudio>();
	private Random r = new Random();
	private List<String> tinkList = new LinkedList<String>();
	private List<String> destroyList = new LinkedList<String>();
	
	public AudioManager() {
		tinkList.add("tink_0.wav");
		tinkList.add("tink_1.wav");
		tinkList.add("tink_2.wav");
		destroyList.add("dig_0.wav");
		destroyList.add("dig_1.wav");
		destroyList.add("dig_2.wav");
		loadAudioFiles();
	}
	
	public void playNextAudio() {
		if(audioQueue.isEmpty() == false) {
			audioQueue.get(0).playOnce();
			audioQueue.remove(0);			
		}
	}
	public GameAudio current_loop = null;
	public void playNextAudioAsLoop() {
		if(audioQueue.isEmpty() == false) {
			current_loop = audioQueue.get(0);
			current_loop.play();
			
			audioQueue.remove(0);			
		}
	}
	
	public void stopLoop() {
		if(current_loop != null) current_loop.stop();
	}
	
	public void loadAudioFiles() {
		List<String> files = new LinkedList<String>();
		for(int i = 0; i < WeaponType.values().length; i++) {
			String filePath = WeaponType.values()[i].getAudioFilePath();
			if(filePath.equals("")) break;
			String name = filePath.replace("/audio/", "");
			if(!files.contains(name)) {
				files.add(name);
				audioAtlas.put(name, new GameAudio(name, filePath));
			}
		}
		if(!files.contains("zombie-24-new.wav")){ audioAtlas.put("zombie-24-new.wav", new GameAudio("zombie-24-new.wav", "/audio/zombie/zombie-24-new.wav")); files.add("zombie-24-new.wav"); }
//		if(!files.contains("zombie-5-new.wav")){ audioAtlas.put("zombie-5-new.wav", new GameAudio("zombie-5-new.wav", "/audio/zombie/zombie-5-new.wav")); files.add("zombie-5-new.wav"); }
		if(!files.contains("zombie-3-new.wav")){ audioAtlas.put("zombie-3-new.wav", new GameAudio("zombie-3-new.wav", "/audio/zombie/zombie-3-new.wav")); files.add("zombie-3-new.wav"); }
		if(!files.contains("zombie-6-new.wav")){ audioAtlas.put("zombie-6-new.wav", new GameAudio("zombie-6-new.wav", "/audio/zombie/zombie-6-new.wav")); files.add("zombie-6-new.wav"); }
		
		if(!files.contains("Dig_0.wav")){ audioAtlas.put("Dig_0.wav", new GameAudio("Dig_0.wav", "/audio/Dig_0.wav")); files.add("Dig_0.wav"); }
		if(!files.contains("Dig_1.wav")){ audioAtlas.put("Dig_1.wav", new GameAudio("Dig_1.wav", "/audio/Dig_1.wav")); files.add("Dig_1.wav"); }
		if(!files.contains("Dig_2.wav")){ audioAtlas.put("Dig_2.wav", new GameAudio("Dig_2.wav", "/audio/Dig_2.wav")); files.add("Dig_2.wav"); }
		
		if(!files.contains("Tink_0.wav")){ audioAtlas.put("Tink_0.wav", new GameAudio("Tink_0.wav", "/audio/Tink_0.wav")); files.add("Tink_0.wav"); }
		if(!files.contains("Tink_1.wav")){ audioAtlas.put("Tink_1.wav", new GameAudio("Tink_1.wav", "/audio/Tink_1.wav")); files.add("Tink_1.wav"); }
		if(!files.contains("Tink_2.wav")){ audioAtlas.put("Tink_2.wav", new GameAudio("Tink_2.wav", "/audio/Tink_2.wav")); files.add("Tink_2.wav"); }
		
		if(!files.contains("steyer_aug_shot.wav")){ audioAtlas.put("steyer_aug_shot.wav", new GameAudio("steyer_aug_shot.wav", "/audio/steyer_aug_shot.wav")); files.add("steyer_aug_shot.wav"); }
		if(!files.contains("steyer_aug_shot_1.wav")){ audioAtlas.put("steyer_aug_shot_1.wav", new GameAudio("steyer_aug_shot_1.wav", "/audio/steyer_aug_shot_1.wav")); files.add("steyer_aug_shot_1.wav"); }
		
		if(!files.contains("rifle_shot_1.wav")){ audioAtlas.put("rifle_shot_1.wav", new GameAudio("rifle_shot_1.wav", "/audio/rifle_shot_1.wav")); files.add("rifle_shot_1.wav"); }
		if(!files.contains("rifle_shot_2.wav")){ audioAtlas.put("rifle_shot_2.wav", new GameAudio("rifle_shot_2.wav", "/audio/rifle_shot_2.wav")); files.add("rifle_shot_2.wav"); }
		
		if(!files.contains("gun_cock.wav")){ audioAtlas.put("gun_cock.wav", new GameAudio("gun_cock.wav", "/audio/gun_cock.wav")); files.add("gun_cock.wav"); }
		if(!files.contains("gun_cocked.wav")){ audioAtlas.put("gun_cocked.wav", new GameAudio("gun_cocked.wav", "/audio/gun_cocked.wav")); files.add("gun_cocked.wav"); }
		
		if(!files.contains("bullet_hit_stone_1.wav")){ audioAtlas.put("bullet_hit_stone_1.wav", new GameAudio("bullet_hit_stone_1.wav", "/audio/bullet_hit_stone_1.wav")); files.add("bullet_hit_stone_1.wav"); }
		if(!files.contains("bullet_hit_stone_2.wav")){ audioAtlas.put("bullet_hit_stone_2.wav", new GameAudio("bullet_hit_stone_2.wav", "/audio/bullet_hit_stone_2.wav")); files.add("bullet_hit_stone_2.wav"); }
		if(!files.contains("bullet_hit_stone_3.wav")){ audioAtlas.put("bullet_hit_stone_3.wav", new GameAudio("bullet_hit_stone_3.wav", "/audio/bullet_hit_stone_3.wav")); files.add("bullet_hit_stone_3.wav"); }
		
		if(!files.contains("body_hit_1.wav")){ audioAtlas.put("body_hit_1.wav", new GameAudio("body_hit_1.wav", "/audio/body_hit_1.wav")); files.add("body_hit_1.wav"); }
		if(!files.contains("body_hit_2.wav")){ audioAtlas.put("body_hit_2.wav", new GameAudio("body_hit_2.wav", "/audio/body_hit_2.wav")); files.add("body_hit_2.wav"); }
		if(!files.contains("body_hit_3.wav")){ audioAtlas.put("body_hit_3.wav", new GameAudio("body_hit_3.wav", "/audio/body_hit_3.wav")); files.add("body_hit_3.wav"); }
	}
	public GameAudio getGameAudio(String name) {
		GameAudio audio = audioAtlas.get(name);
		return audio.getClone();
	}

	public void addSoundToQueue(GameAudio audio) {
		this.audioQueue.add(audio);
	}

	public void playByName(String string) {
//		addSoundToQueue(audioAtlas.get((string+".wav")).getClone());
		audioAtlas.get((string+".wav")).getClone().playOnce();
	}
	public void playRandomWallHit() {
		String name = "bullet_hit_stone_{}.wav";
		name = name.replace("{}", (r.nextInt(3)+1)+"");
		addSoundToQueue(audioAtlas.get(name).getClone().setVolume(0.05f));
	}
	public void playDestroy() {
		addSoundToQueue(audioAtlas.get("Dig_Grass.wav").getClone());
	}
	public void playRandomBodyDamage() {
		String name = "body_hit_{}.wav";
		name = name.replace("{}", (r.nextInt(3)+1)+"");
		addSoundToQueue(audioAtlas.get(name).getClone().setVolume(0.05f));
	}
}
