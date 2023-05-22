package views.state;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import views.App;

public class RulesState {
	private VBox root;

	private Label titleLabel;
	private TextArea rulesTextArea;
	public RulesState() {
		root = new VBox();
		root.setAlignment(Pos.CENTER);
		root.setSpacing(30);
		root.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
			if(event.getCode() == KeyCode.BACK_SPACE) {
				App.controller.gotoBeginState();
			}
		});
		
		titleLabel = new Label(App.TITLE);
		titleLabel.setBackground(new Background(new BackgroundFill(Color.YELLOWGREEN, new CornerRadii(5), Insets.EMPTY)));
		titleLabel.setFont(new Font("Arial", 90));
		rulesTextArea = new TextArea();
	    rulesTextArea.setPadding(new Insets(5,20,5,20));
	    rulesTextArea.setStyle("-fx-control-inner-background: #F5F5DC; -fx-font-family: Consolas; -fx-highlight-fill: #00008b; -fx-highlight-text-fill: #F5F5DC; -fx-text-fill: #00008b;");
	    rulesTextArea.setFont(new Font("Times New Roman", 12));
	    rulesTextArea.setEditable(false);
	    rulesTextArea.setWrapText(true);
	    rulesTextArea.setPrefWidth(600);
	    rulesTextArea.setPrefHeight(530);
	    rulesTextArea.setText(setRules());
	    
	    root.getChildren().addAll(titleLabel, rulesTextArea);
	       
	}
	public String setRules() {
		return 	  "	The Last of Us: Legacy is a single player survival game set in a zombie apocalyptic world. The game is conducted in a turn based manner, "
				+ "in which each player character receives a specific number of action points per turn, which they can use to move, attack or cure zombies,"
				+ "or use special actions."
				+ " The player starts the game controlling only one hero, but can gain additional heroes by curing "
				+ "zombies. The objective of the game for the player is to survive as long as it takes in order to "
				+ "cure a sufficient number of zombies enough to build a community to survive the apocalypse. "
				+ "\r\n"
				+ "		1\r\n"
				+ "\r\n"
				+ "	Characters\r\n"
				+ "\r\n"
				+ "	Characters in the game are split into Heroes or Zombies.\r\n"
				+ "\r\n"
				+ "	Heroes\r\n"
				+ "\r\n"
				+ "	Heroes are the types of characters that the player can control. There are several types of heroes "
				+ "available in the game, each one provides different assets for the player in order to win the game. "
				+ "Any character; Zombie or Hero, has an initial amount of health that decreases whenever they "
				+ "are attacked. If the character’s health ever reaches 0 they are killed and removed from the "
				+ "game.\r\n"
				+ "Considering that the aim of the game is to build a large enough community to survive the "
				+ "apocalypse thus the player must try to maintain and protect their heroes at all costs, as well as "
				+ "try to expand their available pool of heroes.\r\n"
				+ "	Each hero type has a unique action they can add to the player’s team:\r\n"
				+ "		• Explorer: Allows the player to be able to see the entirety of the map for 1 turn whenever"
				+ "	a supply is used.\r\n"
				+ "		• Medic: Can heal and restore health to other heroes or themselves, each process of healing"
				+ "	uses 1 supply.\r\n"
				+ "		• Fighter: Can attack as many times in a turn without costing action points, for 1 turn"
				+ "	whenever a supply is used.\r\n"
				+ "	Possible actions that can be done by a character:\r\n"
				+ "		• Move\r\n"
				+ "		• Attack a zombie\r\n"
				+ "		• Cure a zombie\r\n"
				+ "		• Use their class dependant unique action\r\n"
				+"\r\n"
				+ "		2\r\n"
				+ "\r\n"
				+ "	Zombies\r\n"
				+ "\r\n"
				+ "	Zombies are the types of characters that threaten the player during the game. Zombies cannot "
				+ "be controlled, however they can be cured or attacked. Each time a zombie is killed another "
				+ "zombie will spawn somewhere on the map. In addition to extra zombies spawning every time "
				+ "the player ends a turn.\r\n"
				+ "Whenever a zombie is cured an extra hero will take its place and be available for the player to "
				+ "use in future turns.\r\n"
				+ "\r\n"
				+ "	Collectibles\r\n"
				+ "\r\n"
				+ "	Collectibles are scattered objects across the map that can help the player survive and advance "
				+ "in the game. Each collectible is only usable once, and after that is discarded from the hero’s "
				+ "inventory and cannot be reused.\r\n"
				+ "		• Vaccines: Vaccines are an integral and important part of the game. As the player can "
				+ "only win the game once all vaccines have been collected and used. Vaccines are also the "
				+ "only means through which players can cure zombies and recruit new heroes."
				+ "\r\n"
				+ "		3\r\n"
				+ "\r\n"
				+ "	Supplies\r\n"
				+ "\r\n"
				+ " Supplies are the other type of collectible available in the game. Supplies "
				+ "enable the carrying hero to use their special action."
				+ "\r\n"
				+ "		4\r\n"
				+ "\r\n"
				+ "\r Gameplay Flow\r\n"
				+ "\r\n"
				+ "	The player starts off in a 15x15 grid map with just one hero and 10 zombies. The player can "
				+ "only see the directly adjacent cells next to their pool of heroes. The player then keeps taking "
				+ "his turn trying to collect vaccines, and cure or kill zombies. The game ends when the player "
				+ "has collected and used all vaccines or when all heroes have been overwhelmed and defeated by "
				+ "the zombies. "
				+ "The player only wins if he has successfully collected and used all vaccines and has 5 or more "
				+ "heroes alive.";
				
	}
	public VBox getRoot() {
		return root;
	}
}
