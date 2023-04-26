package model.characters;

import exceptions.InvalidTargetException;
import exceptions.NotEnoughActionsException;

/**
 * A class representing zombies in the game.
 * @author Ahmed, Mostafa, Rasheed
 *
 */

public class Zombie extends Character{
	private static int ZOMBIES_COUNT = 0;
	
	public Zombie() {
		super("Zombie " + ++ZOMBIES_COUNT, 40, 10);
	}
	
	@Override
	public void attack() throws InvalidTargetException, NotEnoughActionsException {
		super.attack();
		if (getTarget() instanceof Zombie) throw new InvalidTargetException("A zombie can not attack a zombie");
		
		getTarget().applyDamage(getAttackDmg());
		
		if(getTarget().getCurrentHp() == 0) {
			getTarget().onCharacterDeath();
		} else {			
			getTarget().defend(this);
			if (getCurrentHp() == 0) this.onCharacterDeath();
		}
	}

}
