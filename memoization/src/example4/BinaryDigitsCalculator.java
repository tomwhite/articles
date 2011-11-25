package example4;

public interface BinaryDigitsCalculator {

	/**
	 * Returns the coefficient of 2^n in the binary
	 * expansion of a number (the number depends on the
	 * implementing class).
	 * @param n the binary digit of the number to calculate.
	 * @throws ValidityCheckFailedException if the validity 
	 * check fails, this means the implementation is buggy
	 * or n is too large for sufficent precision to be
	 * retained.
	 */
	public byte calculateBinaryDigit(final int n);
}