package views.state;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import views.App;

public class ControlsState {
	private VBox root;

	private Label titleLabel;
	private TextArea controlsTextArea;
	public ControlsState() {
		root = new VBox();
		root.setAlignment(Pos.CENTER);
		root.setSpacing(30);
		BackgroundImage myBI= new BackgroundImage(new Image("file:./res//backgrounds/game.png",App.WINDOW_WIDTH,App.WINDOW_HEIGHT,false,true),
		        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
		          BackgroundSize.DEFAULT);
		root.setBackground(new Background(myBI));
		titleLabel = new Label(App.TITLE);
        titleLabel.setStyle("-fx-control-inner-background: #000000; -fx-highlight-fill: #dce775; -fx-highlight-text-fill: #000000; -fx-text-fill: #dce775; ");
		titleLabel.setFont(new Font("Arial", 90));	
		root.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
			if(event.getCode() == KeyCode.BACK_SPACE) {
				App.controller.gotoBeginState();
			}
		});
		controlsTextArea = new TextArea();
		controlsTextArea.setPadding(new Insets(5,20,5,20));
	    controlsTextArea.setStyle("-fx-control-inner-background: #00254d; -fx-highlight-fill: #dce775; -fx-highlight-text-fill: #00254d; -fx-text-fill: #dce775; ");
        controlsTextArea.setFont(new Font("Times New Roman", 40));
	    controlsTextArea.setEditable(false);
	    controlsTextArea.setWrapText(true);
	    controlsTextArea.setPrefWidth(600);
	    controlsTextArea.setPrefHeight(530);
	    controlsTextArea.setText(setControls());
		root.getChildren().addAll(titleLabel, controlsTextArea);
		
}
	public String setControls() {
		return "Movement :\r\n "
				+ "		up --> W\r\n"
				+ "		down --> S\r\n"
				+ "		right --> D\r\n"
				+ "		left --> A\r\n"
				+ "Actions :\r\n "
				+ "		attack --> J\r\n"
				+ "		cure --> K\r\n"
				+ "		specialAction --> L\r\n"
				+ "		endTurn --> H";
	}
	public VBox getRoot() {
		return root;
	}
}

