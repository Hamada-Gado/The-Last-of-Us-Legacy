package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import engine.Game;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import model.characters.Fighter;
import model.characters.Hero;
import model.characters.Medic;
import model.characters.Zombie;
import model.collectibles.Collectible;
import model.collectibles.Vaccine;
import model.characters.Character;
import model.characters.Direction;
import model.characters.Explorer;
import model.world.Cell;
import model.world.CharacterCell;
import model.world.CollectibleCell;
import views.App;
import views.images.ImageCell;
import views.images.ImageLoader;


public class Controller {
	
	private App app;
	private ArrayList<TextArea> heroes;
	private Hero selectedHero;
	private KeyHandler keyHandler;
	private ImageCell[][] imageCells;
	private Object infoTextAreaObject;
	private String actionText;

	public Controller(App app) {
		keyHandler = new KeyHandler(this);
		imageCells = new ImageCell[Game.COLS][Game.ROWS];
		infoTextAreaObject = "";
		actionText = "";
		heroes = new ArrayList<TextArea>();
		
		this.app = app;
	}
	
	public void update() {
		setInfo(infoTextAreaObject.toString());
		setActionTextArea(actionText);
		setError("");
		
		if (Game.checkGameOver()) {
			gotoEndState(Game.checkWin());
		}
	}

	public void gotoBeginState() {
		app.changeSceneToBeginScene();
	}
	
	public void gotoStartState() {
		
		try {
			Game.loadHeroes("./res/Heros.csv");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		// add heroes images and info ! images not yet implemented
		for(Hero hero : Game.availableHeroes) {
			TextArea ta = new TextArea();
			ta.setPrefSize(300, 150);
			ta.setEditable(false);
			ta.setText(hero.toString());
			ta.setOnMouseClicked((event) -> goToGameState(((TextArea) event.getSource()).getText()));
			heroes.add(ta);
		}
		
		this.app.getStartState().updateHeroesPane(heroes);
		app.changeSceneToStartScene();
	}
	
	public void gotoRulesState() {
		app.changeSceneToRulesScene();
	}
	
	public void goToGameState(String text) {
		
		Pattern pattern = Pattern.compile("Name: (.*)", Pattern.CASE_INSENSITIVE);
	    Matcher matcher = pattern.matcher(text);
		
	    String name = "";
	    
	    while (matcher.find()) {
	    	name = matcher.group(1);
	    }
	    
	    for (Hero hero : Game.availableHeroes) {
	    	String heroname = hero.toString();
	    	if (heroname.contains(name)) {
	    		selectedHero = hero;
	    		infoTextAreaObject = hero;
	    		
	    		app.changeSceneToGameScene(hero);
	    		app.getStage().getScene().getRoot().addEventFilter(KeyEvent.ANY, keyHandler);

	    		Game.startGame(hero);
	    		setGameGrid();
	    		
	    		return;
	    	}
	    }
		
	}
	
	public void gotoEndState(boolean win) {
		app.changeSceneToEndScene(win);
	}
	
	public Image getIdleCellImage(Cell cell, Direction direction) {
		Image image;
		Character character;
		Collectible collectible;

		if (cell instanceof CharacterCell) {
			character = ((CharacterCell) cell).getCharacter();
			
			if (character instanceof Zombie) {
				image = ImageLoader.getCharacterImage("Zombie", direction, "Idle");
				
			} else if (character instanceof Fighter) {
				image = ImageLoader.getCharacterImage("Fighter", direction, "Idle");
				
			} else if (character instanceof Medic) {
				image = ImageLoader.getCharacterImage("Medic", direction, "Idle");

			} else if (character instanceof Explorer) {
				image = ImageLoader.getCharacterImage("Explorer", direction, "Idle");

			} else {
				image = ImageLoader.EMPTY_CELL;

			}
			
		} else if (cell instanceof CollectibleCell) {
			collectible = ((CollectibleCell) cell).getCollectible();
			
			if (collectible instanceof Vaccine) {
				image = ImageLoader.VACCINE_IMAGE;
				
			} else {
				image = ImageLoader.SUPPLY_IMAGE;

			}
			
		} else {
			image = ImageLoader.EMPTY_CELL;
		}
		
		return image;
	}
	
	public void setGameGrid() {
		Cell cell;		
		
		for (int x = 0; x < Game.ROWS; x++) {
			for (int y = 0; y < Game.COLS; y++) {
				cell = Game.map[x][y];
				
				getImageCells()[x][y] = new ImageCell(getIdleCellImage(cell, Direction.RIGHT), x, y, cell.isVisible());
				app.getGameState().setImageInGrid(getImageCells()[x][y], (Game.ROWS - 1) - x, y); // x= 0, y= 0 is left bottom
			}
		}
		
		getImageCells()[selectedHero.getLocation().x][selectedHero.getLocation().y].setBorderStrokeColor(ImageCell.HERO_COLOR);
	}
	
	public void updateGameGrid() {
		Cell cell;		
		
		for (int x = 0; x < Game.ROWS; x++) {
			for (int y = 0; y < Game.COLS; y++) {
				cell = Game.map[x][y];
				getImageCells()[x][y].setBorderStrokeColor(ImageCell.TRANSPARENT);
				getImageCells()[x][y].update(getIdleCellImage(cell, getImageCells()[x][y].getDirection()), cell.isVisible());
			}
		}
		
		if (selectedHero.getCurrentHp() > 0)
			getImageCells()[selectedHero.getLocation().x][selectedHero.getLocation().y].setBorderStrokeColor(ImageCell.HERO_COLOR);
		else selectedHero = null;
	}
	
	public void setInfo(int x, int y) {

		Cell cell = Game.map[x][y];
		
		if (!cell.isVisible()) return;
		if (!(cell instanceof CharacterCell)) return;
		if (((CharacterCell) cell).getCharacter() == null) return;
		
		infoTextAreaObject = ((CharacterCell) cell).getCharacter();
		app.getGameState().setInfo(infoTextAreaObject.toString());
	}
	
	public void setInfo(String text) {
		app.getGameState().setInfo(text);
	}
	
	public void setError(String error) {
		app.getGameState().setError(error);
	}
	
	public void setActionTextArea(String actionText) {
		app.getGameState().setActionTextArea(actionText);
	}

	public String getActionText() {
		return actionText;
	}

	public void setActionText(String actionText) {
		this.actionText = actionText;
	}
	
	public Hero getSelectedHero() {
		return selectedHero;
	}

	public void setSelectedHero(int x, int y) {
		Cell cell = Game.map[x][y];
		
		if (!(cell instanceof CharacterCell)) return;
		if (((CharacterCell) cell).getCharacter() == null) return;
		if (((CharacterCell) cell).getCharacter() instanceof Zombie) return;
		
		if (selectedHero != null)
			getImageCells()[selectedHero.getLocation().x][selectedHero.getLocation().y].setBorderStrokeColor(ImageCell.TRANSPARENT);
		
		getImageCells()[x][y].setBorderStrokeColor(ImageCell.HERO_COLOR);
		selectedHero = (Hero) ((CharacterCell) cell).getCharacter();
	}
	
	public void setSelectedHeroTarget(int x, int y) {
		Cell cell = Game.map[x][y];
		
		if (!(cell instanceof CharacterCell)) return;
		
		if (selectedHero.getTarget() != null)
			getImageCells()[selectedHero.getTarget().getLocation().x][selectedHero.getTarget().getLocation().y].setBorderStrokeColor(ImageCell.TRANSPARENT);
		
		if (((CharacterCell) cell).getCharacter() instanceof Zombie) {
			getImageCells()[x][y].setBorderStrokeColor(ImageCell.ZOMBIE_TARGET_COLOR);
		} else {
			getImageCells()[x][y].setBorderStrokeColor(ImageCell.HERO_TARGET_COLOR);
		}
		
		selectedHero.setTarget(((CharacterCell) cell).getCharacter());
	}
	
	
	public ImageCell[][] getImageCells() {
		return imageCells;
	}
	
}
