package model.world;
/**
 * 
 * An abstract class representing Cells in the game.
 * No objects of type Cell can be instantiated.
 * @author Ahmed, Mostafa, Rasheed
 *
 */
public abstract class Cell {
	private boolean isVisible;
	public Cell() {
		isVisible = false;
	}
	public boolean isVisible() {
		return isVisible;
	}
	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}
	
}
