package model.characters;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.MovementException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;
import model.collectibles.Supply;
import model.collectibles.Vaccine;

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
		if(actionsAvailable <0) this.actionsAvailable=0;
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
		
		if(getTarget().getCurrentHp() == 0) {
			getTarget().onCharacterDeath();
		} else {			
			getTarget().defend(this);
			if (getCurrentHp() == 0) onCharacterDeath();
		}
	}
	
	public void makeCellVisible(int y, int x) {
		if (x < 0 || x >= Game.WIDTH || y < 0 || y >= Game.WIDTH) return;
		
		Game.map[y][x].setVisible(true);
	}
	
	public void makeAdjacentCellsVisible() {
		int y = getLocation().y;
		int x = getLocation().x;
		
		makeCellVisible(y, x-1);
		makeCellVisible(y, x+1);
		makeCellVisible(y-1, x);
		makeCellVisible(y+1, x);
		makeCellVisible(y-1, x-1);
		makeCellVisible(y-1, x+1);
		makeCellVisible(y+1, x-1);
		makeCellVisible(y+1, x+1);
	}
	
	public void move(Direction d) throws MovementException {
		switch(d) {
		case UP:
			if(getLocation().y == Game.HEIGHT - 1) {
				throw new MovementException("Can not go UP");
			} else {
				setLocation(new Point(getLocation().x, getLocation().y + 1));
			}
			break;
		case DOWN:
			if(getLocation().y == 0) {
				throw new MovementException("Can not go DOWN");
			} else {
				setLocation(new Point(getLocation().x, getLocation().y - 1));
			}
			break;
		case LEFT:
			if(getLocation().x == 0) {
				throw new MovementException("Can not go LEFT");
			} else {
				setLocation(new Point(getLocation().x - 1, getLocation().y));
			}
			break;
		case RIGHT:
			if(getLocation().x == Game.WIDTH - 1) {
				throw new MovementException("Can not go RIGHT");
			} else {
				setLocation(new Point(getLocation().x + 1, getLocation().y));
			}
			break;
		}
		
		makeAdjacentCellsVisible();
	}
	
	public void useSpecial() throws NoAvailableResourcesException, InvalidTargetException  {
		if(supplyInventory.isEmpty()) throw new NoAvailableResourcesException("Can not use special as their is no supply");
		
		if (this instanceof Medic) {
			
			if (!targetIsAdjacent()) throw new InvalidTargetException("Can not use special <Heal> as target is not in an adjacent cell.");
			if (getTarget() instanceof Zombie) throw new InvalidTargetException("Can not use special <Heal> as target is a zombie");			
			getTarget().setCurrentHp(Integer.MAX_VALUE);
		
		} else if (this instanceof Explorer) {
			
			for (int y = 0; y < Game.HEIGHT; y++) {
				for (int x = 0; x < Game.WIDTH; x++) {
					Game.map[y][x].setVisible(true);
				}
			}
		}
		
		supplyInventory.get(0).use(this);
		specialAction = true;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		if (this instanceof Fighter) {
			return new Fighter(
				this.getName(),
				this.getMaxHp(),
				this.getAttackDmg(),
				this.maxActions);
		} else if (this instanceof Medic) {
			return new Medic(
				this.getName(),
				this.getMaxHp(),
				this.getAttackDmg(),
				this.maxActions);
		} else {
			return new Explorer(
				this.getName(),
				this.getMaxHp(),
				this.getAttackDmg(),
				this.maxActions);
		}
	}


	public void cure() throws InvalidTargetException, NoAvailableResourcesException, CloneNotSupportedException {
		if(vaccineInventory.isEmpty()) throw new NoAvailableResourcesException("Can not cure zombie as their is no vaccine");
		if (!targetIsAdjacent()) throw new InvalidTargetException("Can not cure zombie as target is not in an adjacent cell.");
		if (getTarget() instanceof Hero) throw new InvalidTargetException("Can not cure target as target is a hero");			
		
		Game.zombies.remove(getTarget());
		Game.zombies.add(new Zombie());
		
		int randIndex = new Random().nextInt(Game.availableHeroes.size());
		Game.heroes.add((Hero) Game.availableHeroes.get(randIndex).clone());
		
		vaccineInventory.get(0).use(this);
	}
	
}
