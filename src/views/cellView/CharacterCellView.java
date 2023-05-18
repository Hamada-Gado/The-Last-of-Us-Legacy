package views.cellView;

import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import model.characters.Character;
import model.characters.Fighter;
import model.characters.Medic;
import model.characters.Zombie;
import model.world.CharacterCell;
import views.App;

public class CharacterCellView extends CellView {
	
	public CharacterCellView(CharacterCell cell) {
		super(cell);
		
		Character character = cell.getCharacter();
		
		if (character == null) {
			setCellImage(EMPTY_CELL);
		} else if (character instanceof Zombie) {
			setCellImage(ZOMBIE_IMAGE);
		} else if (character instanceof Fighter) {
			setCellImage(FIGHTER_IMAGE);
		} else if (character instanceof Medic) {
			setCellImage(MEDIC_IMAGE);
		} else {
			setCellImage(EXPLORER_IMAGE);
		}
	
		setCurrentImage();
		setImageView();
		getImageView().setOnMouseClicked(new EventHandler<MouseEvent>() {
		
			@Override
			public void handle(MouseEvent event) {
				if (event.getButton() == MouseButton.SECONDARY) {
					App.controller.setInfo(((CharacterCell) getCell()).getCharacter().toString());
				}
			}
		
		});
	}

	
	
}
