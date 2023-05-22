package views;

import controller.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.characters.Hero;
import views.state.BeginState;
import views.state.EndState;
import views.state.GameState;
import views.state.StartState;

public class App extends Application {
	
	public static final String TITLE = "The Last of Us: Legacy";
	public static final String WIN = "Congratulations. You have won";
	public static final String LOSE = "Game Over. You have Lost. Better Luck Next Time.";
	public static final int WINDOW_HEIGHT = 800;
	public static final int WINDOW_WIDTH = 1000;
	
	public static Controller controller;
	private BeginState beginState = new BeginState();

	private StartState startState = new StartState();
	private GameState gameState = new GameState();
	private EndState endState = new EndState();
	private Stage stage;

	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		
		primaryStage.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
			if (event.getCode() == KeyCode.ESCAPE) System.exit(0);});
		primaryStage.setWidth(WINDOW_WIDTH);
		primaryStage.setHeight(WINDOW_HEIGHT);
		primaryStage.setResizable(false);
		primaryStage.setTitle(TITLE);
		primaryStage.setScene(new Scene(beginState.getRoot()));
		primaryStage.show();
		
		controller = new Controller(this);
	}
	
	public void changeSceneToStartScene() {
		stage.getScene().setRoot(startState.getRoot());
	}
	public void changeSceneToGameScene(Hero hero) {
		stage.getScene().setRoot(gameState.getRoot());
	}
	public void changeSceneToEndScene(boolean win) {
		endState.setRoot(win);
		stage.getScene().setRoot(endState.getRoot());
	}

	public BeginState getBeginState() {
		return beginState;
	}
	public StartState getStartState() {
		return startState;
	}
	
	public GameState getGameState() {
		return gameState;
	}
	public EndState getEndState() {
		return endState;
	}
	
	public Stage getStage() {
		return stage;
	}
	
	public static void main(String[] args) {
		App.launch(App.class);
	}
	
}
