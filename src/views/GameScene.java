package views;

import javafx.scene.Group;
import javafx.scene.Scene;

public class GameScene implements MyScene{
	
	private Group root;
	
	private Scene scene;

	public GameScene() {
		
		root = new Group();
		
		scene = new Scene(root);
	}

	@Override
	public Scene getScene() {
		return scene;
	}

}
