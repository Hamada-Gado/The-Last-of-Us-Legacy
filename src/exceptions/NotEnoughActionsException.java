package exceptions;

/**
 *  A subclass of GameActionException representing an exception that occurs when a
 *  character tries take an action without the sufficient action points available.
 * @author Ahmed, Mostafa, Rasheed
 *
 */
@SuppressWarnings("serial")
public class NotEnoughActionsException extends GameActionException {
	
	public NotEnoughActionsException() {
		super();
	}
	
	public NotEnoughActionsException(String s) {
		super(s);
	}

}
