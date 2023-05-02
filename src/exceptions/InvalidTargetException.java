package exceptions;

/**
 * A subclass of GameActionException representing an exception that occurs upon trying
 * to target a wrong character with an action.
 * @author Ahmed, Mostafa, Rasheed
 *
 */
@SuppressWarnings("serial")
public class InvalidTargetException extends GameActionException {
	
	public InvalidTargetException() {
		super();
	}
	
	public InvalidTargetException(String s) {
		super(s);
	}

}
