package model.characters;

/**
 * A class representing fighters in the game.
 * @author Ahmed, Mostafa, Rasheed
 *
 */

public class Fighter extends Hero{
	
	public Fighter(String name, int maxHp, int attackDmg, int maxActions) {
		super(name, maxHp, attackDmg, maxActions);
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return new Fighter(
				getName(),
				getMaxHp(),
				getAttackDmg(),
				getMaxActions());
	}
	
	
}
