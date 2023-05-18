package views.cellView;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.world.Cell;

public abstract class CellView {
	
	
	public static final Image ZOMBIE_IMAGE = new Image("file:./res/zombie.jpg", 50, 50, false, false);
	public static final Image FIGHTER_IMAGE = new Image("file:./res/fighter.png", 50, 50, false, false);
	public static final Image MEDIC_IMAGE = new Image("file:./res/medic.jpg", 50, 50, false, false);
	public static final Image EXPLORER_IMAGE = new Image("file:./res/explorer.gif", 50, 50, false, false);
	public static final Image SUPPLY_IMAGE = new Image("file:./res/supply.jpg", 50, 50, false, false);
	public static final Image VACCINE_IMAGE = new Image("file:./res/vaccine.jpg", 50, 50, false, false);
	public static final Image EMPTY_CELL = new Image("file:./res/background.png", 50, 50, false, false);
	public static final Image NOT_VISIBLE_CELL_IMAGE = new Image("file:./res/not visible.png", 50, 50, false, false);
	
	private Image cellImage;
	private Image currentImage;
	private Cell cell;
	private ImageView imageView;
	
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

	public ImageView getImageView() {
		return imageView;
	}

	public void setImageView() {
		imageView = new ImageView(currentImage);
	}
	
}
