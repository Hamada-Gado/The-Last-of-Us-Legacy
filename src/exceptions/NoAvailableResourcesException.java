package exceptions;

/**
 * A subclass of GameActionException representing an exception that occurs when a
 * character tries to use a Collectible he does not have.
 * @author Ahmed, Mostafa, Rasheed
 *
 */
@SuppressWarnings("serial")
public class NoAvailableResourcesException extends GameActionException {
	
	public NoAvailableResourcesException() {
		super();
	}
	
	public NoAvailableResourcesException(String s) {
		super(s);
	}
	
}
