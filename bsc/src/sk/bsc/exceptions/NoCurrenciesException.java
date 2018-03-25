package sk.bsc.exceptions;

/**
 * This exception is thrown when no currencies are implemented in this application.
 * 
 * @author matej
 *
 */
@SuppressWarnings("serial")
public class NoCurrenciesException extends RuntimeException {
	
	public NoCurrenciesException(String message) {
       super(message);
    }
}
