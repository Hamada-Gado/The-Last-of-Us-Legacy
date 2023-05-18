package views.cellView;

import model.collectibles.Collectible;
import model.collectibles.Vaccine;
import model.world.CollectibleCell;

public class CollectibleCellView extends CellView {

	public CollectibleCellView(CollectibleCell cell) {
		super(cell);
		
		Collectible collectible = cell.getCollectible();
		
		if (collectible instanceof Vaccine) {
			setCellImage(VACCINE_IMAGE);
		} else {
			setCellImage(SUPPLY_IMAGE);
		}
		
		setCurrentImage();
	}
}
