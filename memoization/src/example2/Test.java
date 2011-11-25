package example2;

import junit.framework.TestCase;

public class Test extends TestCase {

	public Test(String name) {
		super(name);
	}

	public void test() {
		PiBinaryDigitsCalculator calculator = new PiBinaryDigitsCalculator();
		assertEquals("-1000", 1, calculator.calculateBinaryDigit(-1000));
		assertEquals("-1000 second time", 1, calculator.calculateBinaryDigit(-1000));		
	}

}
