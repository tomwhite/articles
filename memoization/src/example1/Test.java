package example1;

import junit.framework.TestCase;

public class Test extends TestCase {

	public Test(String name) {
		super(name);
	}

	public void test() {
		// Pi in binary is 11.00100100...
		PiBinaryDigitsCalculator calculator = new PiBinaryDigitsCalculator();
		assertEquals("2", 0, calculator.calculateBinaryDigit(2));
		assertEquals("1", 1, calculator.calculateBinaryDigit(1));
		assertEquals("0", 1, calculator.calculateBinaryDigit(0));
		assertEquals("-1", 0, calculator.calculateBinaryDigit(-1));
		assertEquals("-2", 0, calculator.calculateBinaryDigit(-2));
		assertEquals("-3", 1, calculator.calculateBinaryDigit(-3));
		assertEquals("-4", 0, calculator.calculateBinaryDigit(-4));
		assertEquals("-5", 0, calculator.calculateBinaryDigit(-5));
		assertEquals("-6", 1, calculator.calculateBinaryDigit(-6));
		assertEquals("-7", 0, calculator.calculateBinaryDigit(-7));
		assertEquals("-8", 0, calculator.calculateBinaryDigit(-8));

		assertEquals("-100", 0, calculator.calculateBinaryDigit(-100));
		assertEquals("-1000", 1, calculator.calculateBinaryDigit(-1000));
		assertEquals("-10000", 1, calculator.calculateBinaryDigit(-10000));
		assertEquals("-100000", 0, calculator.calculateBinaryDigit(-100000));
		assertEquals("-1000000", 1, calculator.calculateBinaryDigit(-1000000));
	}

}
