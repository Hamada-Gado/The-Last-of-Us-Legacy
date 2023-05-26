package views.state;


import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
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


public class BeginState {
	private VBox root;

	private Label titleLabel;

	public BeginState() {
		root = new VBox();
		root.setAlignment(Pos.CENTER);
		root.setSpacing(25);
		BackgroundImage myBI= new BackgroundImage(new Image("file:./res//backgrounds/game.png",App.WINDOW_WIDTH,App.WINDOW_HEIGHT,false,true),
		        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
		          BackgroundSize.DEFAULT);
		root.setBackground(new Background(myBI));
		titleLabel = new Label(App.TITLE);
        titleLabel.setStyle("-fx-control-inner-background: #000000; -fx-highlight-fill: #dce775; -fx-highlight-text-fill: #000000; -fx-text-fill: #dce775; ");
		titleLabel.setFont(new Font("Arial", 90));	
		Button hero = new Button("Choose Your Hero");
		hero.setStyle("-fx-background-color: #dce775; "); 
		hero.setPrefSize(200, 50);
		hero.setOnMouseClicked(new EventHandler<Event>(){

			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				App.controller.gotoStartState();
			}
			
		});
		
		Button description = new Button("Description");
		description.setStyle("-fx-background-color: #dce775; "); 
		description.setPrefSize(100, 50);
		description.setOnMouseClicked(new EventHandler<Event>(){
		
			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				App.controller.gotoRulesState();
			}
			
		});
		Button quit = new Button("Quit");
		quit.setStyle("-fx-background-color: #dce775; "); 
		quit.setPrefSize(100, 50);
		quit.setOnMouseClicked(new EventHandler<Event>(){

			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
			
		});
		root.getChildren().addAll(titleLabel,hero, description, quit);
	}

	public VBox getRoot() {
		return root;
	}
}