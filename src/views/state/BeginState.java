package views.state;


import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import views.App;


public class BeginState {
	private VBox root;

	private Label titleLabel;

	public BeginState() {
		root = new VBox();
		root.setAlignment(Pos.CENTER);
		root.setSpacing(10);
		
		titleLabel = new Label(App.TITLE);
		titleLabel.setBackground(new Background(new BackgroundFill(Color.YELLOWGREEN, new CornerRadii(5), Insets.EMPTY)));
		titleLabel.setFont(new Font("Arial", 90));	
		Button hero = new Button("Choose Your Hero");
		
		hero.setPrefSize(200, 50);
		hero.setOnMouseClicked(new EventHandler<Event>(){

			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				App.controller.gotoStartState();
			}
			
		});
		
		Button rules = new Button("Rules");
		rules.setPrefSize(100, 50);
		rules.setOnMouseClicked(new EventHandler<Event>(){

			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				App.controller.gotoRulesState();
			}
			
		});
		Button quit = new Button("Quit");
		quit.setPrefSize(100, 50);
		
		root.getChildren().addAll(titleLabel,hero, rules, quit);
	}

	public VBox getRoot() {
		return root;
	}
}