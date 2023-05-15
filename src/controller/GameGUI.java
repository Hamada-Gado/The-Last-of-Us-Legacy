package controller;

import java.util.ArrayList;

import engine.Game;
import javafx.scene.control.TextField;
import model.characters.Hero;
import views.StartView;

public class GameGUI {
	
	private StartView sv;
	private ArrayList<TextField> heroes;
	
	public GameGUI() {

		// create a new instance of the SupermarketView
		sv = new StartView();
		
		// add heroes images
		for(Hero hero : Game.availableHeroes) {
			
		}
		
	}
	
}
