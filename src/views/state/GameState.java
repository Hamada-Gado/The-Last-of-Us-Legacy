package views.state;

import java.awt.Point;

import engine.Game;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import views.App;
import views.images.ImageCell;

public class GameState {
	
	public static final int GRID_WIDTH = 50;
	public static final int GRID_HEIGHT = 50;
	public static final int MARGIN = 5;
	public static final int TEXT_AREA_WIDHT = 210;
	
	private HBox root;
	private GridPane gameGrid;
	private VBox logPanel;
	private TextArea infoTextArea;
	private TextArea errorTextArea;
	private TextArea actionTextArea;

	public GameState() {
		
		root = new HBox();
		root.setAlignment(Pos.CENTER);
		root.setSpacing(10);
		root.setPadding(new Insets(MARGIN));
		BackgroundImage myBI= new BackgroundImage(new Image("file:./res//backgrounds/game.png",App.WINDOW_WIDTH,App.WINDOW_HEIGHT,false,true),
		        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
		          BackgroundSize.DEFAULT);
		root.setBackground(new Background(myBI));
		// game map
		gameGrid = new GridPane();
		
		for (int i = 0; i < Game.COLS; i++) {
            ColumnConstraints colConst = new ColumnConstraints();
            gameGrid.getColumnConstraints().add(colConst);
        }
        for (int i = 0; i < Game.ROWS; i++) {
            RowConstraints rowConst = new RowConstraints();
            gameGrid.getRowConstraints().add(rowConst);
        }
        
        // log panel to show info for player or illegal moves
        logPanel = new VBox();
        
		// info of hero
        infoTextArea = new TextArea();
        infoTextArea.setStyle("-fx-control-inner-background: #00254d; -fx-highlight-fill: #dce775; -fx-highlight-text-fill: #00254d; -fx-text-fill: #dce775; ");                
        // error because of illegal action
        errorTextArea = new TextArea();
        errorTextArea.setStyle("-fx-control-inner-background: #00254d; -fx-highlight-fill: #dce775; -fx-highlight-text-fill: #00254d; -fx-text-fill: #dce775; ");                

        
        // actions taken during the turn
        actionTextArea = new TextArea();
        actionTextArea.setStyle("-fx-control-inner-background: #00254d; -fx-highlight-fill: #dce775; -fx-highlight-text-fill: #00254d; -fx-text-fill: #dce775; ");                
        
        logPanel.getChildren().addAll(infoTextArea, errorTextArea, actionTextArea);
        logPanel.getChildren().forEach(t -> {
        	((TextArea) t).setEditable(false);
        	((TextArea) t).setWrapText(true);
        	((TextArea) t).setPrefWidth(TEXT_AREA_WIDHT);
        	VBox.setVgrow(t, Priority.ALWAYS);
        });
        
        // add components to root
        root.getChildren().addAll(gameGrid, logPanel);
	}

	public static Point getLocationAtPixel(double x, double y) {
		int newX = (Game.ROWS - 1) - (int) ((y - MARGIN) / GRID_HEIGHT);
		int newY =  (int) ((x - MARGIN) / GRID_WIDTH);
		
		return new Point(newX, newY);
	}
	
	public void setImageInGrid(ImageCell cellview, int x, int y) {
		// y represents cols & x represents rows in GridPane
    	gameGrid.add(cellview.getButton(), y, x);
	}
	
	public void setInfo(String info) {
		infoTextArea.setText(info);
	}
	
	public void setError(String error) {
		errorTextArea.setText(error);
	}
	
	public void setActionTextArea(String actionText) {
		actionTextArea.setText(actionText);
	}
	
	public GridPane getGameGrid() {
		return gameGrid;
	}

	public HBox getRoot() {
		return root;
	}
	

}
