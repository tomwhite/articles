package example2;

/**
 * ValidityCheckFailedException indicates that a calculation
 * has failed a validity check. The message gives details of
 * how the calculation failed.
 */
public class ValidityCheckFailedException
	extends RuntimeException {

	public ValidityCheckFailedException(String message) {
		super(message);
	}

}