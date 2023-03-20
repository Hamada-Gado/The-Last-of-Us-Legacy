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
	public Character getCharacter() {
		return character;
	}
	public void setCharacter(Character c) {
		character.setCurrentHp(c.getCurrentHp());
	} 
	public boolean isSafe() {
		return isSafe;
	}
	public void setSafe(boolean isSafe) {
		this.isSafe = isSafe;
		setVisible(isSafe);
	}
	
}
