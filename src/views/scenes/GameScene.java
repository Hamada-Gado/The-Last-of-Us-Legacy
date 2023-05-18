package views.scenes;


import engine.Game;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import views.cellView.CellView;

public class GameScene implements MyScene{
	
	public static final int GRID_WIDTH = 50;
	public static final int GRID_HEIGHT = 50;
	public static final int TEXT_AREA_WIDHT = 170;
	
	private HBox root;
	private GridPane gameGrid;
	private VBox logPanel;
	private TextArea infoTextArea;
	
	private Scene scene;

	public GameScene() {
		
		root = new HBox();
		root.setAlignment(Pos.CENTER_LEFT);
		root.setSpacing(10);
		root.setPadding(new Insets(5));
		
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
        
        // log panel to show info for player either illegal moves or number of vaccines
        logPanel = new VBox();
        
		// info of hero
        infoTextArea = new TextArea();
        infoTextArea.setEditable(false);
        infoTextArea.setWrapText(true);
        infoTextArea.setPrefWidth(TEXT_AREA_WIDHT);
        
        VBox.setVgrow(infoTextArea, Priority.ALWAYS);
        logPanel.getChildren().add(infoTextArea);
        
        // add components to root
        root.getChildren().addAll(gameGrid, logPanel);
        
		scene = new Scene(root);
	}
	
	public void setImageInGrid(CellView cellview, int x, int y) {
    	gameGrid.add(cellview.getImageView(), x, y);
	}
	
	public void setInfo(String info) {
		infoTextArea.setText(info);
	}

	@Override
	public Scene getScene() {
		return scene;
	}
	

}
