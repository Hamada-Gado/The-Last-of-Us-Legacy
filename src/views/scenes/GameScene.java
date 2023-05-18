package views.scenes;


import engine.Game;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import views.cellView.CellView;

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
		
		// info of hero
        infoLabel = new Label();
        
        
        root.setCenter(gameGrid);
        root.setRight(infoLabel);
        
		scene = new Scene(root);
	}
	
	public void setImageInGrid(CellView cellview, int x, int y) {
    	gameGrid.add(cellview.getImageView(), x, y);
	}
	
	public void setInfo(String info) {
		infoLabel.setText(info);
	}

	@Override
	public Scene getScene() {
		return scene;
	}
	

}
