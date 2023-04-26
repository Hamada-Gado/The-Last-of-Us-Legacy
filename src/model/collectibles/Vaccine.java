package model.collectibles;

import model.characters.Hero;

/**
 * A class representing Vaccines in the game.
 * @author Ahmed, Mostafa, Rasheed
 *
 */
public class Vaccine implements Collectible{
	public Vaccine() {
		
	}

	@Override
	public void pickUp(Hero h) {
		h.getVaccineInventory().add(this);
	}

	@Override
	public void use(Hero h) {
		h.getVaccineInventory().remove(this);
	}
}
