package model.world;
/**
 * A class representing Character Cells in the game.
 * @author Ahmed, Mostafa, Rasheed
 *
 */
import model.characters.Character;
public class CharacterCell extends Cell{
	private Character character;
	private boolean isSafe;
	public CharacterCell(Character character) {
		super();
		this.character = character;
	}
	public CharacterCell(Character character, boolean isSafe) {
		super();
		this.character = character;
		this.isSafe=isSafe;
	}
	public Character getCharacter() {
		return character;
	}
	public void setCharacter(Character character) {
		this.character=character;
	} 
	public boolean isSafe() {
		return isSafe;
	}
	public void setSafe(boolean isSafe) {
		this.isSafe = isSafe;
	}
	
}
