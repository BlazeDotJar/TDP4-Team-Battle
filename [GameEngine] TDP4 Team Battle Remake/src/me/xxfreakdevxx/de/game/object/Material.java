package me.xxfreakdevxx.de.game.object;

public enum Material {
	
	STONE_WALL(1, "stone_wall", "Stonewall"),
	BACKGROUND(2, "background", "Background");
	
	int id = 0;
	String name, displayname;
	
	Material(int id, String name, String displayname) {
		this.id=id;
		this.name=name;
		this.displayname=displayname;
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
	
	
	
}
