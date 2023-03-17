package model.world;
/**
 * A class representing Trap Cells in the game.
 * @author Ahmed, Mostafa, Rasheed
 *
 */
public class TrapCell extends Cell{
	int trapDamage;
	public TrapCell(int trap) {
		super();
		trapDamage=trap;
	}
	public int getTrapDamage() {
		return trapDamage;
	}
	
}
