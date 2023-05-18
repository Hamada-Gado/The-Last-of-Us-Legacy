package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import engine.Game;
import javafx.event.Event;
import javafx.scene.control.TextArea;
import model.characters.Hero;
import model.world.Cell;
import model.world.CharacterCell;
import model.world.CollectibleCell;
import model.world.TrapCell;
import views.App;
import views.cellView.CellView;
import views.cellView.CharacterCellView;
import views.cellView.CollectibleCellView;
import views.cellView.TrapCellView;
import views.scenes.GameScene;
import views.scenes.StartScene;

public class Controller {
	
	private App app;
	private ArrayList<TextArea> heroes;
	
	public Controller(App app) throws FileNotFoundException, IOException {
		this.app = app;
		
		Game.loadHeroes("./res/Heros.csv");
		heroes = new ArrayList<TextArea>();
		
		// add heroes images and info ! images not yet implemented
		for(Hero hero : Game.availableHeroes) {
			TextArea ta = new TextArea();
			ta.setPrefSize(300, 120);
		
			ta.setEditable(false);
			ta.setText(hero.toString());
			ta.setOnMouseClicked((event) -> goToGameScene(event));
			heroes.add(ta);
		}
		
		((StartScene) this.app.getCurrentScene()).updateHeroesPane(heroes);
	}
	

	public void setGameGrid() {
		CellView cellView;
		
		for (Cell[] cells : Game.map) {
			for (Cell cell : cells) {
				
				if (cell instanceof CharacterCell) {
					cellView = new CharacterCellView((CharacterCell) cell);
				} else if (cell instanceof CollectibleCell) {
					cellView = new CollectibleCellView((CollectibleCell) cell);
				} else {
					cellView = new TrapCellView((TrapCell) cell);
				}
				
				((GameScene) app.getCurrentScene()).setImageInGrid(cellView, 0, 0);;
			}
		}
		
	}
	
	public void goToGameScene(Event event) {
		String text = ((TextArea) event.getSource()).getText();
		
		Pattern pattern = Pattern.compile("Name: (.*)", Pattern.CASE_INSENSITIVE);
	    Matcher matcher = pattern.matcher(text);
		
	    String name = "";
	    
	    while (matcher.find()) {
	    	name = matcher.group(1);
	    }
	    
	    for (Hero hero : Game.availableHeroes) {
	    	String heroname = hero.toString();
	    	if (heroname.contains(name)) {
	    		app.changeSceneToGameScene(hero);
	    		Game.startGame(hero);
	    		return;
	    	}
	    }
		
	}
	
}
