package views;

import java.io.File;

import controller.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import model.characters.Hero;
import views.images.ImageLoader;
import views.state.BeginState;
import views.state.ControlsState;
import views.state.EndState;
import views.state.GameState;
import views.state.RulesState;
import views.state.StartState;

public class App extends Application {
	
	public static final String TITLE = "The Last of Us: Legacy";
	public static final String WIN = "Congratulations. You have won";
	public static final String LOSE = "Game Over. You have Lost. Better Luck Next Time.";
	public static final int WINDOW_HEIGHT = 800;
	public static final int WINDOW_WIDTH = 1000;
	
	public static Controller controller;
	private BeginState beginState = new BeginState();
	private RulesState rulesState = new RulesState();
	private ControlsState controlsState = new ControlsState();

	private StartState startState = new StartState();
	private GameState gameState = new GameState();
	private EndState endState = new EndState();
	private Stage stage;

	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		Media sound=new Media(new File("./res//sounds/wrong-place-129242.mp3").toURI().toString());
		MediaPlayer mediaPlayer=new MediaPlayer(sound);
		mediaPlayer.play();
		primaryStage.addEventFilter(KeyEvent.KEY_RELEASED, event -> {
			if (event.getCode() == KeyCode.ESCAPE) System.exit(0);});
		primaryStage.setWidth(WINDOW_WIDTH);
		primaryStage.setHeight(WINDOW_HEIGHT);
		primaryStage.setResizable(false);
		primaryStage.setTitle(TITLE);
		primaryStage.setScene(new Scene(beginState.getRoot()));
		primaryStage.show();
		
		controller = new Controller(this);
		ImageLoader.get_images();
	}
	
	public void changeSceneToBeginScene() {
		stage.getScene().setRoot(beginState.getRoot());
	}
	public void changeSceneToRulesScene() {
		stage.getScene().setRoot(rulesState.getRoot());
	}
	public void changeSceneToControlsScene() {
		stage.getScene().setRoot(controlsState.getRoot());
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
