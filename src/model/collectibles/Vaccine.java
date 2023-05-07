package model.collectibles;

import java.util.Random;

import engine.Game;
import model.characters.Hero;
import model.world.CharacterCell;

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
		
		int randIndex = new Random().nextInt(Game.availableHeroes.size());
		Hero newHero = Game.availableHeroes.get(randIndex);
		Game.availableHeroes.remove(randIndex);

		Game.heroes.add(newHero);
		
		newHero.setLocation(h.getTarget().getLocation());

		((CharacterCell) Game.map[h.getTarget().getLocation().x][h.getTarget().getLocation().y]).setCharacter(newHero);
		
		Game.zombies.remove(h.getTarget());
		
		
		h.getVaccineInventory().remove(this);
	}
}
