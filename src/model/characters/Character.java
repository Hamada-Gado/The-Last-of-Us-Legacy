package model.characters;
import java.awt.Point;

import exceptions.InvalidTargetException;
import exceptions.NotEnoughActionsException;

/**
 * An abstract class representing characters in the game.
 * @author Ahmed, Mostafa, Rasheed
 *
 */

public abstract class Character {
	private String name;
	private Point location;
	private int maxHp;
	private int currentHp;
	private int attackDmg;
	private Character target;
	
	public Character(String name, int maxHp, int attackDmg) {
		this.name = name;
		this.maxHp = maxHp;
		this.attackDmg = attackDmg;
		this.currentHp= maxHp;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Point getLocation() {
		return this.location;
	}
	
	public void setLocation(Point location) {
		this.location = location;
	}
	
	public int getMaxHp() {
		return this.maxHp;
	}
	
	public int getCurrentHp() {
		return this.currentHp;
	}
	
	public void setCurrentHp(int currentHp) {
		if(currentHp > maxHp) {
			this.currentHp = maxHp;
		}
		else if(currentHp < 0) {
			this.currentHp = 0;
		}
		else {			
			this.currentHp = currentHp;
		}
	}
	
	public int getAttackDmg() {
		return this.attackDmg;
	}
	
	public Character getTarget() {
		return this.target;
	}
	
	public void setTarget(Character target) {
		this.target = target;
	}
	
	private boolean targetIsAdjacent() {
		double distanceSq = location.distanceSq(target.location);
		return distanceSq == 1 || distanceSq == 2;
	}
	
	protected void applyDamage(int damage) {
		setCurrentHp(currentHp - damage);
	}
	
	public void attack() throws InvalidTargetException, NotEnoughActionsException {
		if (!targetIsAdjacent()) throw new InvalidTargetException("Target is not in an Adjacent cell.");
	}
}
