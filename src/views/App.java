package views;

import controller.Controller;
import javafx.application.Application;
import javafx.stage.Stage;
import model.characters.Hero;
import views.scenes.GameScene;
import views.scenes.MyScene;
import views.scenes.StartScene;

public class App extends Application {
	
	public static final String TITLE = "The Last of Us: Legacy";

	public static final int WINDOW_HEIGHT = 800;
	public static final int WINDOW_WIDTH = 960;
	
	public static Controller controller;
	
	private StartScene startScene = new StartScene();
	private GameScene gameScene = new GameScene();	
	private MyScene currentScene = startScene;
	private Stage stage;
	
	public MyScene getCurrentScene() {
		return currentScene;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		
		primaryStage.setScene(startScene.getScene());
		primaryStage.setWidth(WINDOW_WIDTH);
		primaryStage.setHeight(WINDOW_HEIGHT);
		primaryStage.setResizable(false);
		primaryStage.setTitle(TITLE);
		primaryStage.show();
		
		controller = new Controller(this);
	}
	
	public void changeSceneToGameScene(Hero hero) {
		currentScene = gameScene;
		stage.setScene(currentScene.getScene());
		
	}
	
	public static void main(String[] args) {
		App.launch(App.class);
	}
	
}
