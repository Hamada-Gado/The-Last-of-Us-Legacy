package model.world;

public class Fire extends Disaster {

	private int intensity;
	
	public Fire() {
		super("Fire");
		intensity = 20;
	}
	
	public int getIntensity() {
		return intensity;
	}
}
