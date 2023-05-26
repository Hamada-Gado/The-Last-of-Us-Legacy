package views.state;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import views.App;

public class StartState {
	private VBox root;

	private Label titleLabel;
	private FlowPane heroesPane;

	public StartState() {
		root = new VBox();
		root.setAlignment(Pos.CENTER);
		root.setSpacing(20);
		BackgroundImage myBI= new BackgroundImage(new Image("file:./res//backgrounds/game.png",App.WINDOW_WIDTH,App.WINDOW_HEIGHT,false,true),
		        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
		          BackgroundSize.DEFAULT);
		root.setBackground(new Background(myBI));
		// add label with the title as text
		titleLabel = new Label(App.TITLE);
        titleLabel.setStyle("-fx-control-inner-background: #000000; -fx-highlight-fill: #dce775; -fx-highlight-text-fill: #000000; -fx-text-fill: #dce775; ");
		titleLabel.setFont(new Font("Arial", 90));			
		
		// add heroes pane that contains available heroes in the start of the game
		heroesPane = new FlowPane();
		heroesPane.setAlignment(Pos.CENTER);
		heroesPane.setHgap(15);
		heroesPane.setVgap(20);
		
		// add components to root
		root.getChildren().addAll(titleLabel, heroesPane);
	}
	
	public void updateHeroesPane(ArrayList<TextArea> heroesTextArea) {
		// add heroes images
		for(TextArea heroTextArea: heroesTextArea) {
	        heroTextArea.setStyle("-fx-control-inner-background: #00254d; -fx-highlight-fill: #dce775; -fx-highlight-text-fill: #00254d; -fx-text-fill: #dce775; ");                

			heroesPane.getChildren().add(heroTextArea);		
		}
	}

	public VBox getRoot() {
		return root;
	}

}
