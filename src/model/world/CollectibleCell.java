package model.world;
/**
 * A class representing Collectible Cells in the game.
 * @author Ahmed, Mostafa, Rasheed
 *
 */
import model.collectibles.Collectible;

public class CollectibleCell extends Cell implements Collectible{
	private Collectible collectible;
	public CollectibleCell(Collectible c) {
		super();
		collectible= c;
	}
	public Collectible getCollectible() {
		return collectible;
	}		
	
	
}
