package example4;

import junit.framework.TestCase;

public class Test extends TestCase {

	public Test(String name) {
		super(name);
	}

	public void test() {
		BinaryDigitsCalculator inconsistentCalculator = new BinaryDigitsCalculator() {
			private byte flip = 0;
			public byte calculateBinaryDigit(int n) {
				flip ^= 1;
				return flip;
			}
		};
		assertEquals("First call to inconsistentCalculator",
			1, inconsistentCalculator.calculateBinaryDigit(0));
		assertEquals("Different second time for inconsistentCalculator",
			0, inconsistentCalculator.calculateBinaryDigit(0));
					
		BinaryDigitsCalculator memoizedCalculator =
			(BinaryDigitsCalculator) Memoizer.memoize(inconsistentCalculator);
			
		assertEquals("First call to memoizedCalculator",
			1, memoizedCalculator.calculateBinaryDigit(0));
		assertEquals("Same second time for memoizedCalculator",
			1, memoizedCalculator.calculateBinaryDigit(0));
					
	}

}
