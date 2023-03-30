package model.world;

public class Collapse extends Disaster{
	
	private int damagedCells;
	
	public Collapse() {
		super("Collapse");
		damagedCells = 3;
	}

	public int getDamagedCells() {
		return damagedCells;
	}

}
