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
		KeyCode code = event.getCode();
		
		try {			
			makeAnAction(code);
			controller.update();
		} catch(GameActionException e) {
			controller.setError(e.getMessage());
		}
		
	}
	
	public void makeAnAction(KeyCode code) throws GameActionException {
		if (code == up)
			controller.getSelectedHero().move(Direction.UP);
		else if (code == down)
			controller.getSelectedHero().move(Direction.DOWN);
		else if (code == right)
			controller.getSelectedHero().move(Direction.RIGHT);
		else if (code == left)
			controller.getSelectedHero().move(Direction.LEFT);
		else if (code == attack)
			controller.getSelectedHero().attack();
		else if (code == cure)
			controller.getSelectedHero().cure();
		else if (code == specialAction)
			controller.getSelectedHero().useSpecial();
		else if (code == endTurn)
			Game.endTurn();
		
	}
}
