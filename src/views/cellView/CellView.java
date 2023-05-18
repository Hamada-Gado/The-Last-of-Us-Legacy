package views.cellView;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.world.Cell;

public abstract class CellView extends ImageView {
	
	
	public static Image zombieImage;
	public static Image fighterImage;
	public static Image MedicImage;
	public static Image ExplorerImage;
	public static Image SupplyImage;
	public static Image VaccineImage;
	public static Image emptyCell;
	public static Image NotvisibleCellImage;
	
	private Image visibleCellImage;
	
	private Cell cell;
	
	public CellView() {
		
	}	

	public Cell getCell() {
		return cell;
	}

	public void setCell(Cell cell) {
		this.cell = cell;
	}
	
}
