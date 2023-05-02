package exceptions;

/**
 *  A subclass of GameActionException representing an exception that occurs when a
 *  character tries to make an invalid movement.
 * @author Ahmed, Mostafa, Rasheed
 *
 */
@SuppressWarnings("serial")
public class MovementException extends GameActionException {
	
	public MovementException() {
		super();
	}
	
	public MovementException(String s) {
		super(s);
	}

}
