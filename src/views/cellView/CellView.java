package views.cellView;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import views.App;
import views.state.GameState;

public class CellView implements EventHandler<MouseEvent> {
	
	public static final Color TRANSPARENT = Color.TRANSPARENT;
	public static final Color HERO_COLOR = Color.DARKBLUE;
	public static final Color ZOMBIE_TARGET_COLOR = Color.INDIANRED;
	public static final Color HERO_TARGET_COLOR = Color.LIMEGREEN;
		
	public static final double BORDER_WIDTH = 3;
	
	public static final Image ZOMBIE_IMAGE = new Image("file:./res/zombie.jpg", GameState.GRID_WIDTH - BORDER_WIDTH, GameState.GRID_HEIGHT - BORDER_WIDTH, false, false);
	public static final Image FIGHTER_IMAGE = new Image("file:./res/fighter.png", GameState.GRID_WIDTH - BORDER_WIDTH, GameState.GRID_HEIGHT - BORDER_WIDTH, false, false);
	public static final Image MEDIC_IMAGE = new Image("file:./res/medic.jpg", GameState.GRID_WIDTH - BORDER_WIDTH, GameState.GRID_HEIGHT - BORDER_WIDTH, false, false);
	public static final Image EXPLORER_IMAGE = new Image("file:./res/explorer.gif", GameState.GRID_WIDTH - BORDER_WIDTH, GameState.GRID_HEIGHT - BORDER_WIDTH, false, false);
	public static final Image SUPPLY_IMAGE = new Image("file:./res/supply.jpg", GameState.GRID_WIDTH - BORDER_WIDTH, GameState.GRID_HEIGHT - BORDER_WIDTH, false, false);
	public static final Image VACCINE_IMAGE = new Image("file:./res/vaccine.jpg", GameState.GRID_WIDTH - BORDER_WIDTH, GameState.GRID_HEIGHT - BORDER_WIDTH, false, false);
	public static final Image EMPTY_CELL = new Image("file:./res/background.png", GameState.GRID_WIDTH - BORDER_WIDTH, GameState.GRID_HEIGHT - BORDER_WIDTH, false, false);
	public static final Image NOT_VISIBLE_CELL_IMAGE = new Image("file:./res/not visible.png", GameState.GRID_WIDTH - BORDER_WIDTH, GameState.GRID_HEIGHT - BORDER_WIDTH, false, false);
	
	private int x, y;
	private Image cellImage;
	private Image currentImage;
	private ImageView imageView;
	private Button button;
	
	public CellView(Image cellImage, int x, int y, boolean visible) {
		this.cellImage = cellImage;
		imageView = new ImageView();
		this.x = x;
		this.y = y;
		
		setCurrentImage(visible);
		setImageView();
		
		button = new Button();
		button.setMinSize(GameState.GRID_WIDTH, GameState.GRID_WIDTH);
		button.setMaxSize(GameState.GRID_WIDTH, GameState.GRID_WIDTH);
		button.setPrefSize(GameState.GRID_WIDTH, GameState.GRID_WIDTH);		
		button.setGraphic(imageView);
		button.addEventFilter(MouseEvent.ANY, this);
		button.setStyle("-fx-focus-color: transparent");
		
		setBorderStrokeColor(TRANSPARENT);
	}
	
	public Image getCellImage() {
		return cellImage;
	}
	
	public void setCellImage(Image cellImage) {
		this.cellImage = cellImage;
	}
	
	public void setCurrentImage(boolean visible) {
		if (visible) {
			currentImage = cellImage;
		} else {
			currentImage = NOT_VISIBLE_CELL_IMAGE;
		}
	}

	public void setBorderStrokeColor(Color color) {
		button
		.setBorder( 
			new Border(
				new BorderStroke(
					color, 
					BorderStrokeStyle.SOLID,
					new CornerRadii(5),
					new BorderWidths(BORDER_WIDTH)
		)));
	}

	public void setImageView() {
		imageView.setImage(currentImage);
	}
	
	public Button getButton() {
		return button;
	}
	
	public void update(Image cellImage, boolean visible) {
		setCellImage(cellImage);
		setCurrentImage(visible);
		setImageView();
	}
	
	@Override
	public void handle(MouseEvent event) {

		if (event.getEventType() == MouseEvent.MOUSE_ENTERED) App.controller.setInfo(x, y);

		if (event.getButton() == MouseButton.PRIMARY) {
			App.controller.setSelectedHero(x, y);
		} else if (event.getButton() == MouseButton.SECONDARY) {
			App.controller.setSelectedHeroTarget(x, y);
		}
		
		event.consume();
	}
	
}
