package me.xxfreakdevxx.de.game.object;

public enum Weapon {
	
	STEYR_AUG(1, "steyr_aug", "Steyr AUG", 35, 170, 200, 0.68d, "", "/audio/Tink_0.wav");
	
	int id = 0, ammo = 0;
	double damage_min, damage_max, accuracy;
	String name, displayname, texture_path, audio_path;
	
	Weapon(int id, String name, String displayname, int ammo, double damage_min, double damage_max, double accuracy, String texture_path, String audio_path) {
		this.id=id;
		this.name=name;
		this.displayname=displayname;
		this.ammo = ammo;
		this.damage_min = damage_min;
		this.damage_max = damage_max;
		this.accuracy = accuracy;
		this.texture_path = texture_path;
		this.audio_path = audio_path;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDisplayname() {
		return displayname;
	}

	public int getAmmo() {
		return ammo;
	}

	public double getDamage_min() {
		return damage_min;
	}

	public double getDamage_max() {
		return damage_max;
	}

	public double getAccuracy() {
		return accuracy;
	}

	public String getTextureFilePath() {
		return texture_path;
	}

	public String getAudioFilePath() {
		return audio_path;
	}
	
	
	
	
	
}
