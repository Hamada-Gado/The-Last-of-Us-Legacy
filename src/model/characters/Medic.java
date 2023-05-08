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
	
	public boolean targetIsAdjacentToHeal() throws InvalidTargetException {
		if (getTarget() == null) throw new InvalidTargetException("Target can not be null");
		
		double distanceSq = getLocation().distanceSq(getTarget().getLocation());
		return distanceSq ==  0 || distanceSq == 1 || distanceSq == 2;
	}
	
	@Override
	public void useSpecial() throws NoAvailableResourcesException, InvalidTargetException  {
		if (getTarget() instanceof Zombie) 
			throw new InvalidTargetException("Can not use special <Heal> as target is a zombie");			
		
		if (!targetIsAdjacentToHeal()) 
			throw new InvalidTargetException("Can not use special <Heal> as target is not in an adjacent cell.");		
		
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
