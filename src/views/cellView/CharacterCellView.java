package views.cellView;

import model.characters.Character;
import model.characters.Fighter;
import model.characters.Medic;
import model.characters.Zombie;
import model.world.CharacterCell;

public class CharacterCellView extends CellView{
	
	public CharacterCellView(CharacterCell cell) {
		super(cell);
		
		Character charater = cell.getCharacter();
		
		if (charater == null) {
			setCellImage(EMPTY_CELL);
		} else if (charater instanceof Zombie) {
			setCellImage(ZOMBIE_IMAGE);
		} else if (charater instanceof Fighter) {
			setCellImage(FIGHTER_IMAGE);
		} else if (charater instanceof Medic) {
			setCellImage(MEDIC_IMAGE);
		} else {
			setCellImage(EXPLORER_IMAGE);
		}
	
		setCurrentImage();
	}

}
