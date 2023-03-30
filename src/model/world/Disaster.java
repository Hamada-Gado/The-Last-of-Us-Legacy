package model.world;

public abstract class Disaster {
	
	private static int ID = 0;
	private String name;
	private int timer;
	
	public Disaster(String name) {
		ID++;
		this.name = name + " " + ID;
		this.timer = 2;
	}
	
	public static int getID() {
		return ID;
	}
	public String getName() {
		return name;
	}
	public int getTimer() {
		return timer;
	}

}
