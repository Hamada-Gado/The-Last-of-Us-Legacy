package model.characters;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughActionsException;
import model.world.CharacterCell;

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
		Hero[] heroes = new Hero[8];
		
		int x = getLocation().x;
		int y = getLocation().y;
		
		int c = 0;
		
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (i == 0 && j == 0) continue;
				heroes[c] = getAdjacentHeroes(x + i, y + j);
				c++;
			}			
		}
		
		int min = Integer.MAX_VALUE, target = -1;
		
		for (int i = 0; i < 8; i++) {
			if (heroes[i] != null && heroes[i].getCurrentHp() < min) {
				target = i;
				min = heroes[i].getCurrentHp();
			}
		}
		
		if (target == -1) return;
		setTarget(heroes[target]);
				
		getTarget().applyDamage(getAttackDmg());
		
		getTarget().defend(this);
		getTarget().onCharacterDeath();
		onCharacterDeath();
	}
	
	@Override
	public void onCharacterDeath() {
		if (getCurrentHp() > 0) return;
		
		super.onCharacterDeath();
		Game.addRandomZombie();
	}
	
	public Hero getAdjacentHeroes(int x, int y) {
		if (x < 0 || x >= Game.HEIGHT || y < 0 || y >= Game.WIDTH) return null;
		if (!(Game.map[x][y] instanceof CharacterCell)) return null;
		if (!(((CharacterCell) Game.map[x][y]).getCharacter() instanceof Hero)) return null;
		
		return (Hero) ((CharacterCell) Game.map[x][y]).getCharacter();
	}

}
