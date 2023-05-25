package controller;

import engine.Game;
import exceptions.GameActionException;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.characters.Direction;

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
	
	public KeyHandler(Controller controller) {
		this.controller = controller;
		
		up = KeyCode.W;
		down = KeyCode.S;
		right = KeyCode.D;
		left = KeyCode.A;

		attack = KeyCode.J;
		cure = KeyCode.K;
		specialAction = KeyCode.L;
		endTurn = KeyCode.H;
	}

	@Override
	public void handle(KeyEvent event) {
		if (controller.getSelectedHero() == null) return;
		
		KeyCode code = event.getCode();
		
		if (event.getEventType() == KeyEvent.KEY_PRESSED) {				
			try {
				makeAnAction(code);
				controller.update();
			}
			catch(GameActionException e) {
				controller.setError(e.getMessage());
			}
		}
	}
	
	public void makeAnAction(KeyCode code) throws GameActionException {
		int currentHp = controller.getSelectedHero().getCurrentHp();
		
		if (code == up) {
			controller.getSelectedHero().move(Direction.UP);
			controller.setActionText("Moved UP");
		}
		else if (code == down) {
			controller.getSelectedHero().move(Direction.DOWN);
			controller.setActionText("Moved DOWN");			
		}
		else if (code == right) {
			controller.getSelectedHero().move(Direction.RIGHT);
			controller.setActionText("Moved RIGHT");			
		}
		else if (code == left) {
			controller.getSelectedHero().move(Direction.LEFT);
			controller.setActionText("Moved LEFT");
		}
		else if (code == attack) {
			controller.getSelectedHero().attack();
			controller.setActionText("Attacked" + controller.getSelectedHero().getTarget().getName() + "\n" + "Zombie Defended, Damage Taken:" + controller.getSelectedHero().getTarget().getAttackDmg()/2);
			if (controller.getSelectedHero().getTarget().getCurrentHp() <= 0) 
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
}
