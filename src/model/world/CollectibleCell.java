package model.world;

import model.collectibles.Collectible;

public class CollectibleCell extends Cell implements Collectible{
	Collectible collectible;
	public CollectibleCell(Collectible c) {
		super();
		collectible= c;
	}
	public Collectible getCollectible() {
		return collectible;
	}		
	
	
}
