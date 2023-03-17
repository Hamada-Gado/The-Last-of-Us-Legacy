package model.world;

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
