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
		updateGameGrid();
		setInfo(infoTextAreaObject.toString());
		setActionTextArea(actionText);
		setError("");
		
		if (Game.checkGameOver()) {
			gotoEndState(Game.checkWin());
		}
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
			imageCells[selectedHero.getLocation().x][selectedHero.getLocation().y].setBorderStrokeColor(ImageCell.TRANSPARENT);
		
		imageCells[x][y].setBorderStrokeColor(ImageCell.HERO_COLOR);
		selectedHero = (Hero) ((CharacterCell) cell).getCharacter();
	}
	
	public void setSelectedHeroTarget(int x, int y) {
		Cell cell = Game.map[x][y];
		
		if (!(cell instanceof CharacterCell)) return;
		
		if (selectedHero.getTarget() != null)
			imageCells[selectedHero.getTarget().getLocation().x][selectedHero.getTarget().getLocation().y].setBorderStrokeColor(ImageCell.TRANSPARENT);
		
		if (((CharacterCell) cell).getCharacter() instanceof Zombie) {
			imageCells[x][y].setBorderStrokeColor(ImageCell.ZOMBIE_TARGET_COLOR);
		} else {
			imageCells[x][y].setBorderStrokeColor(ImageCell.HERO_TARGET_COLOR);
		}
		
		selectedHero.setTarget(((CharacterCell) cell).getCharacter());
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
	
	public Image getCellImage(Cell cell) {
		Image cellImage;
		Character character;
		Collectible collectible;

		if (cell instanceof CharacterCell) {
			character = ((CharacterCell) cell).getCharacter();
			
			if (character instanceof Zombie) {
				cellImage = ImageLoader.getZombieImage();
			} else if (character instanceof Fighter) {
				cellImage = ImageLoader.getWarriorImage();
			} else if (character instanceof Medic) {
				cellImage = ImageLoader.getMedicImage();
			} else if (character instanceof Explorer) {
				cellImage = ImageLoader.getExplorerImage();
			} else {
				cellImage = ImageLoader.EMPTY_CELL;
			}
			
		} else if (cell instanceof CollectibleCell) {
			collectible = ((CollectibleCell) cell).getCollectible();
			
			if (collectible instanceof Vaccine) {
				cellImage = ImageLoader.VACCINE_IMAGE;
			} else {
				cellImage = ImageLoader.SUPPLY_IMAGE;
			}
			
		} else {
			cellImage = ImageLoader.EMPTY_CELL;
		}
		
		return cellImage;
	}
	
	public void setGameGrid() {
		Cell cell;		
		
		for (int x = 0; x < Game.ROWS; x++) {
			for (int y = 0; y < Game.COLS; y++) {
				cell = Game.map[x][y];
				
				imageCells[x][y] = new ImageCell(getCellImage(cell), x, y, cell.isVisible());
				app.getGameState().setImageInGrid(imageCells[x][y], (Game.ROWS - 1) - x, y); // x= 0, y= 0 is left bottom
			}
		}
		
		imageCells[selectedHero.getLocation().x][selectedHero.getLocation().y].setBorderStrokeColor(ImageCell.HERO_COLOR);
	}
	
	public void updateGameGrid() {
		Cell cell;		
		
		for (int x = 0; x < Game.ROWS; x++) {
			for (int y = 0; y < Game.COLS; y++) {
				cell = Game.map[x][y];
				imageCells[x][y].setBorderStrokeColor(ImageCell.TRANSPARENT);;
				imageCells[x][y].update(getCellImage(cell), cell.isVisible());
			}
		}
		
		if (selectedHero.getCurrentHp() > 0)
			imageCells[selectedHero.getLocation().x][selectedHero.getLocation().y].setBorderStrokeColor(ImageCell.HERO_COLOR);
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
	
}
