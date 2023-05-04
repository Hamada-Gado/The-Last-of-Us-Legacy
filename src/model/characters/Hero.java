package model.characters;

import java.awt.Point;
import java.util.ArrayList;

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

	public void move(Direction d) throws MovementException,NotEnoughActionsException {
		int dx = 0;
		int dy = 0;
		
		if(actionsAvailable == 0)
			throw new NotEnoughActionsException("Not enough action points to move");
		
		switch(d) {
		case UP:
			if(getLocation().y == Game.HEIGHT - 1) {
				throw new MovementException("Can not go UP");
			}
			dy = 1;
			break;
		case DOWN:
			if(getLocation().y == 0) {
				throw new MovementException("Can not go DOWN");
			}
			dy = -1;
			break;
		case LEFT:
			if(getLocation().x == 0) {
				throw new MovementException("Can not go LEFT");
			}
			dx = -1;
			break;
		case RIGHT:
			if(getLocation().x == Game.WIDTH - 1) {
				throw new MovementException("Can not go RIGHT");
			}
			dx = 1;
			break;
		}
		
		Point newLocation = new Point(getLocation().x + dx, getLocation().y + dy);
		
		Cell cell = Game.map[newLocation.y][newLocation.x];
		
		if (cell instanceof CharacterCell) {
			if (((CharacterCell) cell).getCharacter() != null) {
				throw new MovementException("Can't move to an occupied cell");
			}
		} else if (cell instanceof CollectibleCell) {
			((CollectibleCell) cell).getCollectible().pickUp(this);
		} else if (cell instanceof TrapCell) {
			applyDamage(((TrapCell) cell).getTrapDamage());
		}
		
		((CharacterCell) Game.map[getLocation().y][getLocation().x]).setCharacter(null);
		
		setLocation(newLocation);
		
		Game.map[getLocation().y][getLocation().x] = new CharacterCell(this);
		
		Game.changeAdjacentCellsVisibility(this, true);

		actionsAvailable--;
		
		//TODO should I check on the character death every move?
		onCharacterDeath();
	}
	
	public void useSpecial() throws NoAvailableResourcesException, InvalidTargetException  {
		if(supplyInventory.isEmpty()) throw new NoAvailableResourcesException("Can not use special as their is no supply");
		
		supplyInventory.get(0).use(this);
		specialAction = true;
	}

	public void cure() throws InvalidTargetException, NoAvailableResourcesException, NotEnoughActionsException {
		if (getTarget() instanceof Hero) throw new InvalidTargetException("Can not cure target as target is a hero");			
		if (vaccineInventory.isEmpty()) throw new NoAvailableResourcesException("Can not cure zombie as their is no vaccine");
		if (!targetIsAdjacent()) throw new InvalidTargetException("Can not cure zombie as target is not in an adjacent cell.");
		if (actionsAvailable == 0) throw new NotEnoughActionsException("Not enough action points to cure zombie");
		
		vaccineInventory.get(0).use(this);
		
		setTarget(null);
		actionsAvailable--;
	}
	
}