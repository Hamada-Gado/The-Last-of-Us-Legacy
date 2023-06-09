package exceptions;

/**
 * Class representing a generic exception that can occur during the game play. These exceptions arise from any invalid action that is performed.
 * No objects of type GameActionException can be instantiated.
 * This class is a subclass of the java Exception class. This class has four subclasses; InvalidTargetException, MovementException, NoAvailableResourcesException, and NotEnoughActionsException.
 * @author Ahmed, Mostafa, Rasheed
 *
 */
@SuppressWarnings("serial")
public abstract class GameActionException extends Exception {
	
	public GameActionException() {
		super();
	}
	
	public GameActionException(String s) {
		super(s);
	}

}
