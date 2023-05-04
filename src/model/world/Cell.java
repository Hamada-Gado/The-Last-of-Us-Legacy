package model.world;

import engine.Game;
import model.characters.Hero;

/**
 * 
 * An abstract class representing Cells in the game.
 * No objects of type Cell can be instantiated.
 * @author Ahmed, Mostafa, Rasheed
 *
 */
public abstract class Cell {
	private boolean isVisible;
	
	public boolean isVisible() {
		return isVisible;
	}
	
	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}
	
	
	public static void makeCellVisible(int x, int y) {
		if (x < 0 || x >= Game.HEIGHT || y < 0 || y >= Game.WIDTH) return;

		Game.map[y][x].setVisible(true);
	}
	
	public static void makeAdjacentCellsVisible(Hero h) {
		int x = h.getLocation().x;
		int y = h.getLocation().y;
		
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				makeCellVisible(x + i, y + j);
			}
		}
	}
	
}
