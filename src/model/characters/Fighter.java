package model.characters;

/**
 * A class representing fighters in the game.
 * @author Ahmed, Mostafa, Rasheed
 *
 */

public class Fighter extends Hero{
	
	private boolean supplyUsed;

	public Fighter(String name, int maxHp, int attackDmg, int maxActions) {
		super(name, maxHp, attackDmg, maxActions);
	}
	
	
	
	public boolean isSupplyUsed() {
		return supplyUsed;
	}



	public void setSupplyUsed(boolean supplyUsed) {
		this.supplyUsed = supplyUsed;
	}
	
}
