package controller;

import java.util.HashMap;

import engine.Game;
import exceptions.GameActionException;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.characters.Direction;
import views.images.ImageCell;
import views.images.ImageLoader;

public class KeyHandler implements EventHandler<KeyEvent> {
	
	private Controller controller;
	
	private KeyCode up;
	private KeyCode down;
	private KeyCode right;
	private KeyCode left;

	private KeyCode attack;
	private KeyCode cure;
	private KeyCode specialAction;
	private KeyCode endTurn;
	
	private HashMap<KeyCode, String> states;
	private HashMap<KeyCode, Direction> directions;
	
	private boolean inAction;
	
	public KeyHandler(Controller controller) {
		this.controller = controller;
		inAction = false;
		
		up = KeyCode.W;
		down = KeyCode.S;
		right = KeyCode.D;
		left = KeyCode.A;

		attack = KeyCode.J;
		cure = KeyCode.K;
		specialAction = KeyCode.L;
		endTurn = KeyCode.H;
		
		
		states = new HashMap<KeyCode, String>();
		
		states.put(up, ImageLoader.MOVE);
		states.put(down, ImageLoader.MOVE);
		states.put(right, ImageLoader.MOVE);
		states.put(left, ImageLoader.MOVE);
		
		states.put(attack, ImageLoader.ATTACK);
		states.put(cure, ImageLoader.IDLE);
		states.put(specialAction, ImageLoader.IDLE);
		states.put(endTurn, ImageLoader.IDLE);
		
		
		directions = new HashMap<KeyCode, Direction>();
		
		directions.put(up, Direction.UP);
		directions.put(down, Direction.DOWN);
		directions.put(right, Direction.RIGHT);
		directions.put(left, Direction.LEFT);
	}

	@Override
	public void handle(KeyEvent event) {
		if (controller.getSelectedHero() == null) return;
		
		inAction = true;
		KeyCode code = event.getCode();
		
		if (event.getEventType() == KeyEvent.KEY_PRESSED) {				
			try {
				makeAnAction(code);
				controller.update();
				controller.updateGameGrid();
				animate(code);
			}
			catch(GameActionException e) {
				controller.setError(e.getMessage());
			}
		}
		
		inAction = false;
	}
	
	public void makeAnAction(KeyCode code) throws GameActionException {
		int currentHp = controller.getSelectedHero().getCurrentHp();
		
		if (code == up) {
			controller.getSelectedHero().move(directions.get(code));
			controller.setActionText("Moved UP");
		}
		else if (code == down) {
			controller.getSelectedHero().move(directions.get(code));
			controller.setActionText("Moved DOWN");			
		}
		else if (code == right) {
			controller.getSelectedHero().move(directions.get(code));
			controller.setActionText("Moved RIGHT");
		}
		else if (code == left) {
			controller.getSelectedHero().move(directions.get(code));
			controller.setActionText("Moved LEFT");
		}
		else if (code == attack) {
			controller.getSelectedHero().attack();
			controller.setActionText("Attacked" + controller.getSelectedHero().getTarget().getName() + "\n" + "Zombie Defended, Damage Taken:" + controller.getSelectedHero().getTarget().getAttackDmg()/2);
			controller.getSelectedHero().setTarget(null);
		}
		else if (code == cure) {
			controller.getSelectedHero().cure();
			controller.setActionText("Cure zombie, a new Hero appeared");
		}
		else if (code == specialAction) {
			controller.getSelectedHero().useSpecial();
			controller.setActionText("Special Action is used");
		}
		else if (code == endTurn) {
			Game.endTurn();
			controller.setActionText("End Turn\nBEWARE all Zombies try to attack");
		}
		
		if ((code == up || code == down || code == right || code == left) && currentHp - controller.getSelectedHero().getCurrentHp() != 0)
			controller.setActionText("A trap got activated\nDamage Taken: " + (currentHp - controller.getSelectedHero().getCurrentHp()));
		
	}
	
	
	public void animate(KeyCode code) {
		int x = controller.getSelectedHero().getLocation().x;
		int y = controller.getSelectedHero().getLocation().y;
		
		ImageCell imageCell = controller.getImageCells()[x][y];

		String type = controller.getSelectedHero().getClass().getSimpleName();
		Direction direction = (code == up || code == down || code == right || code == left) ? directions.get(code) : imageCell.getDirection();
		
		controller.getImageCells()[x][y].setDirection(direction);
		controller.getImageCells()[x][y].update(ImageLoader.getCharacterImage(type, direction, states.get(code)), true);
				
		new java.util.Timer().schedule( 
		        new java.util.TimerTask() {
		            @Override
		            public void run() {
		            	if (!inAction)
		            		controller.updateGameGrid();
		            }
		        }, 
		        1000
		);
	}
	
	public void getDirectionOfTarget() {
		//TODO get direction of target to face him when attacking or curing
	}
}
