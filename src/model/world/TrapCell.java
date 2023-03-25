package model.world;

import java.util.Random;

/**
 * A class representing Trap Cells in the game.
 * @author Ahmed, Mostafa, Rasheed
 *
 */
public class TrapCell extends Cell{
	private int trapDamage;
	
	public TrapCell() {
		trapDamage = (new Random().nextInt(3) + 1) * 10;
	}
	
	public int getTrapDamage() {
		return trapDamage;
	}
	
	public static void main(String[] args) {
		System.out.println(new TrapCell().getTrapDamage());
	}
	
}
