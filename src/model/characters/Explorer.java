package model.characters;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.NoAvailableResourcesException;

/**
 * A class representing explorers in the game.
 * @author Ahmed, Mostafa, Rasheed
 *
 */

public class Explorer extends Hero{

	public Explorer(String name, int maxHp, int attackDmg, int maxActions) {
		super(name, maxHp, attackDmg, maxActions);
	}
	
	@Override
	public void useSpecial() throws NoAvailableResourcesException, InvalidTargetException  {
		super.useSpecial();

		for (int y = 0; y < Game.HEIGHT; y++) {
			for (int x = 0; x < Game.WIDTH; x++) {
				Game.map[x][y].setVisible(true);
			}
		}
	
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return new Explorer(
				getName(),
				getMaxHp(),
				getAttackDmg(),
				getMaxActions());
	}
	
}
