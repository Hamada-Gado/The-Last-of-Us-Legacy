package views;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class StartScene implements MyScene {
	private VBox root;

	private Label titleLabel;
	private FlowPane heroesPane;

	private Scene scene;
	
	public StartScene() {
		root = new VBox();
		root.setAlignment(Pos.CENTER);
		root.setSpacing(10);
		
		// add label with the title as text
		titleLabel = new Label(App.TITLE);
		titleLabel.setBackground(new Background(new BackgroundFill(Color.YELLOWGREEN, new CornerRadii(5), Insets.EMPTY)));
		titleLabel.setFont(new Font("Arial", 90));		
		
		// add heroes pane that contains available heroes in the start of the game
		heroesPane = new FlowPane();
		heroesPane.setAlignment(Pos.CENTER);
		heroesPane.setHgap(10);
		heroesPane.setVgap(10);
		
		// add components to root
		root.getChildren().addAll(titleLabel, heroesPane);
		
		scene = new Scene(root);
		
	}
	
	public void updateHeroesPane(ArrayList<TextArea> heroesTextArea) {
		// add heroes images
		for(TextArea heroTextField : heroesTextArea) {
			heroesPane.getChildren().add(heroTextField);		
		}
	}
	
	public Scene getScene() {
		return scene;
	}

}
