package views.state;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import views.App;

public class EndState {
	
	private VBox root;
	private Label titleLabel;
	private Scene scene;
	private Label endLabel;
	
	public EndState() {
		root = new VBox();
		root.setAlignment(Pos.CENTER);
		root.setSpacing(40);
		BackgroundImage myBI= new BackgroundImage(new Image("file:./res//backgrounds/game.png",App.WINDOW_WIDTH,App.WINDOW_HEIGHT,false,true),
		        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
		          BackgroundSize.DEFAULT);
		root.setBackground(new Background(myBI));
		titleLabel = new Label(App.TITLE);
        titleLabel.setStyle("-fx-control-inner-background: #000000; -fx-highlight-fill: #dce775; -fx-highlight-text-fill: #000000; -fx-text-fill: #dce775; ");
		titleLabel.setFont(new Font("Arial", 90));			
		endLabel = new Label();
        endLabel.setStyle("-fx-control-inner-background: #000000; -fx-highlight-fill: #dce775; -fx-highlight-text-fill: #000000; -fx-text-fill: #dce775; ");
		endLabel.setFont(new Font("Arial", 40));
		
		root.getChildren().addAll(titleLabel, endLabel);
	}
	
	public void setRoot(boolean win) {
		if(win) {
			endLabel.setText(App.WIN);
		}
		else
			endLabel.setText(App.LOSE);
	}
	
	public Scene getScene() {
		return scene;
	}

	public VBox getRoot() {
		return root;
	}
	
}
