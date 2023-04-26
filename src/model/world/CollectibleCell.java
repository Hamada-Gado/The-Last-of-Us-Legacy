package model.world;

import model.collectibles.Collectible;

/**
 * A class representing Collectible Cells in the game.
 * @author Ahmed, Mostafa, Rasheed
 *
 */
public class CollectibleCell extends Cell {
	private Collectible collectible;
	
	public CollectibleCell(Collectible collectible) {
		this.collectible= collectible;
	}
	
	public Collectible getCollectible() {
		return collectible;
	}		
	
	
}
