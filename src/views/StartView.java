package views;

import java.util.ArrayList;

import engine.Game;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class StartView extends Application {
	
	private Label titleLabel;
	private FlowPane heroesPane;
	private BorderPane root;
	private final String TITLE = "The Last of Us: Legacy";

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		root = new BorderPane();
		
		// add label with the title as text
		titleLabel = new Label(TITLE);
		root.setTop(titleLabel);
		
		// add heroes pane that contains available heroes in the start of the game
		heroesPane = new FlowPane();
		FlowPane.setMargin(heroesPane, new Insets(0, 10, 0, 10));
		root.setCenter(heroesPane);
		
		Scene s = new Scene(root, Game.WINDOW_WIDTH, Game.WINDOW_HEIGHT);
		
		primaryStage.setScene(s);
		primaryStage.setTitle(TITLE);
		primaryStage.show();
	}
	
	public void updateHeroesPane(ArrayList<TextField> heroes) {
		for(TextField hero : heroes) {
			heroesPane.getChildren().add(hero);		
		}
		
	}

}
