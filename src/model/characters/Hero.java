package model.characters;

import java.util.ArrayList;
import java.util.Random;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.MovementException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;
import model.collectibles.Supply;
import model.collectibles.Vaccine;
import model.world.Cell;
import model.world.CharacterCell;
import model.world.CollectibleCell;
import model.world.TrapCell;

/**
 * An abstract class representing characters in the game.
 * @author Ahmed, Mostafa, Rasheed
 *
 */

public abstract class Hero extends Character{
	private int actionsAvailable;
	private int maxActions;
	private boolean specialAction;
	private ArrayList<Vaccine> vaccineInventory;
	private ArrayList<Supply> supplyInventory;
	
	public Hero(String name, int maxHp, int attackDmg, int maxActions) {
		super(name, maxHp, attackDmg);
		this.maxActions = maxActions;
		this.actionsAvailable = maxActions;
		vaccineInventory = new ArrayList<Vaccine>();
		supplyInventory = new ArrayList<Supply>();
	}

	public int getActionsAvailable() {
		return actionsAvailable;
	}

	public void setActionsAvailable(int actionsAvailable) {
		if (actionsAvailable < 0) this.actionsAvailable = 0;
		else this.actionsAvailable = actionsAvailable;
	}

	public int getMaxActions() {
		return maxActions;
	}

	public boolean isSpecialAction() {
		return specialAction;
	}

	public void setSpecialAction(boolean specialAction) {
		this.specialAction = specialAction;
	}

	public ArrayList<Vaccine> getVaccineInventory() {
		return vaccineInventory;
	}

	public ArrayList<Supply> getSupplyInventory() {
		return supplyInventory;
	}
	
	@Override
	public void attack() throws InvalidTargetException, NotEnoughActionsException {
		super.attack();
		if (getTarget() instanceof Hero) throw new InvalidTargetException("A hero can not attack a hero");
		
		if (this instanceof Fighter && this.specialAction) {
			getTarget().applyDamage(getAttackDmg());
		} else if(actionsAvailable != 0) {
			getTarget().applyDamage(getAttackDmg());			
			actionsAvailable--;
		} else {
			throw new NotEnoughActionsException("Can not Attack as their is not enough action points");
		}
		
		getTarget().defend(this);
		getTarget().onCharacterDeath();
		onCharacterDeath();
	}
	
	public void makeCellVisible(int x, int y) {
		if (x < 0 || x >= Game.HEIGHT || y < 0 || y >= Game.WIDTH) return;
		
		Game.map[y][x].setVisible(true);
	}
	
	public void makeAdjacentCellsVisible() {
		int x = getLocation().x;
		int y = getLocation().y;
		
		makeCellVisible(x, y-1);
		makeCellVisible(x, y+1);
		makeCellVisible(x-1, y);
		makeCellVisible(x+1, y);
		makeCellVisible(x-1, y-1);
		makeCellVisible(x-1, y+1);
		makeCellVisible(x+1, y-1);
		makeCellVisible(x+1, y+1);
	}
	
	public void move(Direction d) throws MovementException {
		int x = getLocation().x;
		int y = getLocation().y;
		int dx = 0;
		int dy = 0;
		
		switch(d) {
		case UP:
			if(y >= Game.HEIGHT - 1) {
				throw new MovementException("Can not go UP");
			} else {
				dy = 1;
			}
			break;
		case DOWN:
			if(y <= 0) {
				throw new MovementException("Can not go DOWN");
			} else {
				dy = -1;
			}
			break;
		case LEFT:
			if(x <= 0) {
				throw new MovementException("Can not go LEFT");
			} else {
				dx = -1;
			}
			break;
		case RIGHT:
			if(x >= Game.WIDTH - 1) {
				throw new MovementException("Can not go RIGHT");
			} else {
				dx = 1;
			}
			break;
		}
		
		((CharacterCell) Game.map[y][x]).setCharacter(null);
		
		getLocation().translate(dx, dy);
		
		Cell c = Game.map[y][x];
		
		if (c instanceof CollectibleCell) {
			((CollectibleCell) c).getCollectible().pickUp(this);
		} else if (c instanceof TrapCell) {
			applyDamage(((TrapCell) c).getTrapDamage());
			onCharacterDeath();
		}
		
		Game.map[y][x] = new CharacterCell(this, true);
		
		makeCellVisible(x, y);
		makeAdjacentCellsVisible();
		
		actionsAvailable--;
	}
	
	public void useSpecial() throws NoAvailableResourcesException, InvalidTargetException  {
		if(supplyInventory.isEmpty()) throw new NoAvailableResourcesException("Can not use special as their is no supply");
				
		supplyInventory.get(0).use(this);
		specialAction = true;
	}

	public void cure() throws InvalidTargetException, NoAvailableResourcesException {
		if (getTarget() instanceof Hero) throw new InvalidTargetException("Can not cure target as target is a hero");			
		if(vaccineInventory.isEmpty()) throw new NoAvailableResourcesException("Can not cure zombie as their is no vaccine");
		if (!targetIsAdjacent()) throw new InvalidTargetException("Can not cure zombie as target is not in an adjacent cell.");
		
		int randIndex = new Random().nextInt(Game.availableHeroes.size());
		Hero h = Game.availableHeroes.get(randIndex);
		Game.heroes.add(h);
		Game.availableHeroes.remove(randIndex);
		
		Game.map[getTarget().getLocation().x][getTarget().getLocation().y] = new CharacterCell(h, true);
		
		Game.zombies.remove(getTarget());
		
		Game.vaccinesUsed++;
		vaccineInventory.get(0).use(this);
		
		actionsAvailable--;
	}
	
}