package model.collectibles;

import model.characters.Hero;

/**
 * A class representing Supplies in the game.
 * @author Ahmed, Mostafa, Rasheed
 *
 */
public class Supply implements Collectible{
	public Supply() {
		
	}

	@Override
	public void pickUp(Hero h) {
		h.getSupplyInventory().add(this);
	}

	@Override
	public void use(Hero h) {
		h.setSpecialAction(true);
		h.getSupplyInventory().remove(this);
	}
	
	
}
