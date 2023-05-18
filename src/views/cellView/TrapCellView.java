package views.cellView;

import model.world.TrapCell;

public class TrapCellView extends CellView {

	public TrapCellView(TrapCell cell) {
		super(cell);
		
		setCellImage(EMPTY_CELL);
		
		setCurrentImage();
		setImageView();
	}
	
}
