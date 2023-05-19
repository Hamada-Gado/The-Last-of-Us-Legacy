package ai;

import java.awt.Point;

public class Cell {

	private int x, y;
	
	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
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

}
