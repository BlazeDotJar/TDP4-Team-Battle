package me.xxfreakdevxx.de.game.object.weapon;

public enum WeaponType {
	/*         ID  Codename      Displayname        Ammo    Shoot-Inter.   ReloadTi     DMG_min      DMG_max     accuracy */
	FIST(      0,  "fist",       "Faust",             1,          0,             0,         1,            1,         1d, "", ""),
	GLOCK_18(  1,  "glock_18",   "Glock 18",          9,        600,          750,        15,           30,      0.68d, "", "/audio/Tink_0.wav"),
	STEYER_AUG(2,  "steyer_aug", "Steyer AUG",      900,        160,          1700,       170,          200,      0.68d, "", "/audio/steyer_aug_shot.wav"),
	PUMPGUN(   3,  "pump_gun",   "Schrotflinte",     35,        160,          1700,       170,          200,      0.68d, "", "/audio/shotgun_shot.wav");
	
	int id = 0, ammo = 0, shootIntervall = 0, reloadTime = 0;
	double damage_min, damage_max, accuracy;
	String name, displayname, texture_path, audio_path;
	
	WeaponType(int id, String name, String displayname, int ammo, int shootIntervall, int reloadTime, double damage_min, double damage_max, double accuracy, String texture_path, String audio_path) {
		this.id=id;
		this.name=name;
		this.displayname=displayname;
		this.ammo = ammo;
		this.shootIntervall = shootIntervall;
		this.reloadTime = reloadTime;
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
	
	public int getShootIntervall() {
		return shootIntervall;
	}
	
	public double getReloadTime() {
		return reloadTime;
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
