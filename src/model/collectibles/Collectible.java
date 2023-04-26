package model.collectibles;

import model.characters.Hero;

/**
 * Interface containing the methods available to all Collectible objects within the game map.
 * All Vaccines and Supplies are Collectible. For this milestone, you will be leaving this interface
 * empty.
 * @author Ahmed, Mostafa, Rasheed
 *
 */
public interface Collectible {
	
	void pickUp(Hero h);
	void use(Hero h);

}
