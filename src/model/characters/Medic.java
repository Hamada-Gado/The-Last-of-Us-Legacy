package model.characters;

import exceptions.InvalidTargetException;
import exceptions.NoAvailableResourcesException;

/**
 * A class representing medics in the game.
 * @author Ahmed, Mostafa, Rasheed
 *
 */

public class Medic extends Hero{

	public Medic(String name, int maxHp, int attackDmg, int maxActions) {
		super(name, maxHp, attackDmg, maxActions);
	}
	
	
	@Override
	public void useSpecial() throws NoAvailableResourcesException, InvalidTargetException  {
		if (!targetIsAdjacent()) throw new InvalidTargetException("Can not use special <Heal> as target is not in an adjacent cell.");
		if (getTarget() instanceof Zombie) throw new InvalidTargetException("Can not use special <Heal> as target is a zombie");			
		
		super.useSpecial();

		getTarget().setCurrentHp(getTarget().getMaxHp());
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return new Medic(
				getName(),
				getMaxHp(),
				getAttackDmg(),
				getMaxActions());
	}
	
	
}
