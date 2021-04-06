package mx.erick.library.exeption;

@SuppressWarnings("serial")
public class InvalidParamsException extends Exception {

	public InvalidParamsException(String defaultMessage) {
		super(defaultMessage);
	}
	
}
