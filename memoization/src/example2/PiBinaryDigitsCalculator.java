package example2;

import java.util.HashMap;

public class PiBinaryDigitsCalculator {

	private HashMap cache = new HashMap(); 

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
	public synchronized byte calculateBinaryDigit(
			final int n) {
				 
		final Integer N = new Integer(n); 
		Byte B = (Byte) cache.get(N);
		if (B == null) {
			byte b = runBBPAlgorithm(n); 
			cache.put(N, new Byte(b)); 
			return b; 
		} else {
			return B.byteValue(); 
		}
	} 
	
	/**
	 * The "machine epsilon".
	 * Used as a termination criterion for infinite sums.
	 */
	private static double EPSILON = 1e-10;

	private byte runBBPAlgorithm(final int n) {

		// Deal with cases before the "decimal" point
		if (n > 1) {
			return 0;
		}
		if (n == 0 || n == 1) {
			return 1;
		}
		
		final int pos = -n;

		// To calculate the nth binary digit of pi, we need
		// 2^(pos - 1) * pi (mod 1)

		double fractional = calculateFractionalPart(pos - 1);
		byte binary = (byte) (fractional * 2);

		// Perform a validity check by re-calculating and shifting along by one digit.
		// This changes all the calculations so it makes a good check.
		if (pos > 1) {

			double fractionalCheck = calculateFractionalPart(pos - 2);
			fractionalCheck *= 2;
			fractionalCheck = fractionalCheck - Math.floor(fractionalCheck); // fractionalCheck mod 1
			byte binaryCheck = (byte) (fractionalCheck * 2);

			if (binary != binaryCheck) {
				throw new ValidityCheckFailedException("Validity check failed. Binary digit "
					 + n + " calculated as " + binary + " and " + binaryCheck);
			}

		}

		return binary;

	}

	/**
	 * Calculates 2^n * pi (mod 1)
	 */
	private final double calculateFractionalPart(int n) {

		int b = 2, c = 4;

		int floor_n_over_c = n / c;

		int b_raised = n; // n - c * k

		int p_k = -2;
		double S;
		double S_1 = 0.0, S_2 = 0.0, S_3 = 0.0, S_4 = 0.0;

		int k = 0;
		for ( ; k <= floor_n_over_c; k++) {

			p_k += 3; // 8k + 1
			S_1 += modPow(b, b_raised, p_k) / (double) p_k; 
			S_1 = S_1 - Math.floor(S_1); // S_1 mod 1

			p_k += 3; // 8k + 4
			S_2 += modPow(b, b_raised, p_k) / (double) p_k; 
			S_2 = S_2 - Math.floor(S_2); // S_2 mod 1

			p_k++; // 8k + 5
			S_3 += modPow(b, b_raised, p_k) / (double) p_k; 
			S_3 = S_3 - Math.floor(S_3); // S_3 mod 1

			p_k++; // 8k + 6
			S_4 += modPow(b, b_raised, p_k) / (double) p_k; 
			S_4 = S_4 - Math.floor(S_4); // S_4 mod 1

			b_raised -= c;
		}

		// Now k == floor_n_over_c + 1

		double b_lowered = 1.0 / pow(b, c * k - n); // c * k - n > 0
		int b_to_the_c = (int) pow(b, c);

		do {
			p_k += 3; // 8k + 1
			S_1 += b_lowered / p_k;
			S_1 = S_1 - Math.floor(S_1); // S_1 mod 1

			p_k += 3; // 8k + 4
			S_2 += b_lowered / p_k;
			S_2 = S_2 - Math.floor(S_2); // S_2 mod 1

			p_k++; // 8k + 5
			S_3 += b_lowered / p_k;
			S_3 = S_3 - Math.floor(S_3); // S_3 mod 1

			p_k++; // 8k + 6
			S_4 += b_lowered / p_k;
			S_4 = S_4 - Math.floor(S_4); // S_4 mod 1

			b_lowered /= b_to_the_c;

		} while (b_lowered > EPSILON);

		S = 4 * S_1 - 2 * S_2 - S_3 - S_4;
		S = S - Math.floor(S);  // S mod 1

		return S;

	}

	/**
	 * Calculates a<sup>n</sup> mod N using the binary scheme for exponentiation. 
	 * See <i>"The Book of Prime Number Records"</i> by Paulo Ribenboim, p38.
	 * Note that 0<sup>0</sup> returns 0 in this implementation.
	 * @throws IllegalArgumentException if any of the arguments are negative.
	 */
	public final static int modPow(int a, int n, int N) {

		if (a < 0 || n < 0 || N < 0) {
			throw new IllegalArgumentException();
		}

		if (a == 0 || N == 0) {
			return 0;
		}
		if (n == 0) {
			return 1;
		}
		// Find the binary expansion of n
		// n = n_bin[k] * 2^k + n_bin[k - 1] * 2^(k - 1) + ... + n_bin[1] * 2 + n_bin[0] 
		int exp = n;
		int n_bin[] = new int[(int) (Math.log(n) / Math.log(2) + 2)]; // guarantees enough space
		int k = -1;
		while (exp != 0) {
			n_bin[++k] = exp % 2;
			exp = exp / 2;
		}
		
		//Loop to calculate r_j
		long r_j = a % N; // r_0
		for (int j = 1; j <= k; j++) {
			if (n_bin[k - j] == 0) {
				r_j = r_j * r_j % N;
			} else {
				r_j = a * r_j * r_j % N;
			}
		}
		
		return (int) r_j;

	}

	/**
	 * Calculates a<sup>n</sup> using the binary scheme for exponentiation. 
	 * See <i>"The Book of Prime Number Records"</i> by Paulo Ribenboim, p38.
	 * Note that 0<sup>0</sup> returns 0 in this implementation. Beware of
	 * overflow - no warning is given!
	 * @throws IllegalArgumentException if any of the arguments are negative.
	 */
	public final static long pow(int a, int n) throws IllegalArgumentException {

	   if (a < 0 || n < 0)
		  throw new IllegalArgumentException();

	   if (a == 0)
		  return 0;

	   if (n == 0)
		  return 1;

	   // Find the binary expansion of n
	   // n = n_bin[k] * 2^k + n_bin[k - 1] * 2^(k - 1) + ... + n_bin[1] * 2 + n_bin[0] 
	   int exp = n;
	   int n_bin[] = new int[(int) (Math.log(n) / Math.log(2) + 2)]; // guarantees enough space
	   int k = -1;
	   while (exp != 0) {
		  n_bin[++k] = exp % 2;
		  exp = exp / 2;
	   }

	   // Loop to calculate r_j
	   long r_j = a; // r_0
	   for (int j = 1; j <= k; j++) {
		  if (n_bin[k - j] == 0)
			 r_j = r_j * r_j;
		  else
			 r_j = a * r_j * r_j;
	   }

	   return r_j;

	}



}
