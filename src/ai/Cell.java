package ai;

import java.awt.Point;

public class Cell {

	private int x, y;
	private boolean occupied;
	private boolean containsZombie;
	private boolean containsSupply;
	private boolean containsVaccine;
	
	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
		occupied = false;
		containsZombie = false;
		containsSupply = false;
		containsVaccine = false;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Point getPoint() {
		return new Point(x, y);
	}

	public boolean isOccupied() {
		return occupied;
	}

	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}

	public boolean isContainsZombie() {
		return containsZombie;
	}
	
	public void setContainsZombie(boolean containsZombie) {
		this.containsZombie = containsZombie;
	}
	
	public boolean isContainsSupply() {
		return containsSupply;
	}
	
	public void setContainsSupply(boolean containsSupply) {
		this.containsSupply = containsSupply;
	}
	
	public boolean isContainsVaccine() {
		return containsVaccine;
	}
	
	public void setContainsVaccine(boolean containsVaccine) {
		this.containsVaccine = containsVaccine;
	}
	
}
