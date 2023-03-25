package model.world;

import model.characters.Character;

/**
 * A class representing Character Cells in the game.
 * @author Ahmed, Mostafa, Rasheed
 *
 */
public class CharacterCell extends Cell{
	private Character character;
	private boolean isSafe;
	
	public CharacterCell(Character character) {
		this.character = character;
	}
	
	public CharacterCell(Character character, boolean isSafe) {
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
