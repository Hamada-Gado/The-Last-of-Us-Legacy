package views.cellView;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.world.Cell;

public abstract class CellView extends ImageView {
	
	
	public static final Image ZOMBIE_IMAGE = null;
	public static final Image FIGHTER_IMAGE = null;
	public static final Image MEDIC_IMAGE = null;
	public static final Image EXPLORER_IMAGE = null;
	public static final Image SUPPLY_IMAGE = null;
	public static final Image VACCINE_IMAGE = null;
	public static final Image EMPTY_CELL = null;
	public static final Image NOT_VISIBLE_CELL_IMAGE = null;
	
	private Image cellImage;
	private Image currentImage;
	private Cell cell;
	
	public CellView(Cell cell) {
		this.cell = cell;
	}
	
	public Image getCellImage() {
		return cellImage;
	}
	
	public void setCellImage(Image visibleCellImage) {
		this.cellImage = visibleCellImage;
	}
	
	public Cell getCell() {
		return cell;
	}
	
	public void setCell(Cell cell) {
		this.cell = cell;
	}

	public Image getCurrentImage() {
		return currentImage;
	}

	public void setCurrentImage() {
		if (cell.isVisible()) {
			currentImage = cellImage;
		} else {
			currentImage = NOT_VISIBLE_CELL_IMAGE;
		}
	}
	
}
