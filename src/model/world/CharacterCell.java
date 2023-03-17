package model.world;
/**
 * A class representing Character Cells in the game.
 * @author Ahmed, Mostafa, Rasheed
 *
 */
public class CharacterCell extends Cell{
	Character character;
	boolean isSafe;
	public CharacterCell(Character character, boolean isSafe) {
		super();
		this.character = character;
		this.isSafe = isSafe;
	}
	public Character getCharacter() {
		return character;
	}
	public void setCharacter(Character character) {
		this.character = character;
	}
	public boolean isSafe() {
		return isSafe;
	}
	public void setSafe(boolean isSafe) {
		this.isSafe = isSafe;
	}
	
}