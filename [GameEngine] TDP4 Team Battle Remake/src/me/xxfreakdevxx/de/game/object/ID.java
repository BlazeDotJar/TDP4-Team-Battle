package me.xxfreakdevxx.de.game.object;

public enum ID {
	/* Die ID ist die spezialisierung der GameObjects */
	PLAYER(1),
	BLOCK(2),
	BACKGROUND(3),
	CREATE(4),
	BULLET(5),
	ENEMY(6);
	
	private int id = 0;
	
	ID(int id) {
		this.id=id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
