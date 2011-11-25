package example3;

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
					
		BinaryDigitsCalculator cachingCalculator = new CachingBinaryDigitsCalculator(inconsistentCalculator);
		assertEquals("First call to cachingCalculator",
			1, cachingCalculator.calculateBinaryDigit(0));
		assertEquals("Same second time for cachingCalculator",
			1, cachingCalculator.calculateBinaryDigit(0));
					
	}

}
