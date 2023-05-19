package ai;

import java.util.HashMap;

import model.characters.Direction;

public class Cell {

	private int x, y;
	private boolean visited;
	private HashMap<Direction, Cell> connected;
	
	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
		visited = false;
		connected = new HashMap<Direction, Cell>();
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

	public HashMap<Direction, Cell> getConnected() {
		return connected;
	}
}
