package example4;

import junit.framework.TestCase;

public class PerformanceTest extends TestCase {

	public PerformanceTest(String name) {
		super(name);
	}
	
	private long time(final BinaryDigitsCalculator calculator, final int n, final int loops) {
				
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < loops; i++) {
			calculator.calculateBinaryDigit(n);
		}
		return System.currentTimeMillis() - startTime;
	}

	private void check(final int n, final int loops) {
				
		BinaryDigitsCalculator memoizedCalculator =
					(BinaryDigitsCalculator) Memoizer.memoize(new PiBinaryDigitsCalculator());
		long millis = time(memoizedCalculator, n, loops);
		System.out.println("Memoized. n = " + n + ", loops = " + loops + ": " + millis + "ms");
		
		BinaryDigitsCalculator calculator = new PiBinaryDigitsCalculator();
		millis = time(calculator, n, loops);
		System.out.println("Not memoized. n = " + n + ", loops = " + loops + ": " + millis + "ms");
	}

	public void testSmallN() {
		check(-1, 1000);
	} 

	public void testLargeN() {
		check(-1000, 1000);
	} 

}
