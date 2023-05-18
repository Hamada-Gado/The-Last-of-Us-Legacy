package views.scenes;


import engine.Game;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class GameScene implements MyScene{
	
	private BorderPane root;
	private GridPane gameGrid;
	private Label infoLabel;
	
	private Scene scene;

	public GameScene() {
		
		root = new BorderPane();
//		root.setAlignment(gameGrid, Pos.CENTER);
		
		// game map
		gameGrid = new GridPane();
		
		for (int i = 0; i < Game.COLS; i++) {
            ColumnConstraints colConst = new ColumnConstraints();
//            colConst.setPercentWidth(100.0 / Game.COLS);
            gameGrid.getColumnConstraints().add(colConst);
        }
        for (int i = 0; i < Game.ROWS; i++) {
            RowConstraints rowConst = new RowConstraints();
//            rowConst.setPercentHeight(100.0 / Game.ROWS);
            gameGrid.getRowConstraints().add(rowConst);         
        }
        
        
        int x, y;
        Image image = new Image("file:./res/image.png", 45, 45, false, false);
        ImageView imageView;
        for (int i = 0; i < Game.COLS * Game.ROWS; i++) {
        	x = i % Game.COLS;
        	y = i / Game.ROWS;

        	imageView = new ImageView(image);
        	
        	gameGrid.add(imageView, x, y);
        }
        
		
		// info of hero
        
        root.setCenter(gameGrid);
        
		scene = new Scene(root);
	}

	@Override
	public Scene getScene() {
		return scene;
	}

}
