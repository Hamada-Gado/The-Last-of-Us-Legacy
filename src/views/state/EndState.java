package views.state;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import views.App;

public class EndState {
	
	private VBox root;
	private Label titleLabel;
	private Scene scene;
	private Label endLabel;
	
	public EndState() {
		root = new VBox();
		root.setAlignment(Pos.CENTER_LEFT);
		root.setSpacing(10);
		
		titleLabel = new Label(App.TITLE);
		titleLabel.setBackground(new Background(new BackgroundFill(Color.YELLOWGREEN, new CornerRadii(5), Insets.EMPTY)));
		titleLabel.setFont(new Font("Arial", 90));	
		
		endLabel = new Label();
		endLabel.setBackground(new Background(new BackgroundFill(Color.YELLOWGREEN, new CornerRadii(5), Insets.EMPTY)));
		endLabel.setFont(new Font("Arial", 40));
		
		root.getChildren().addAll(titleLabel, endLabel);
		
		scene = new Scene(root);	
	}
	
	public void setScene(boolean win) {
		if(win) {
			endLabel.setText(App.WIN);
		}
		else
			endLabel.setText(App.LOSE);
	}
	
	public Scene getScene() {
		return scene;
	}
	
}
