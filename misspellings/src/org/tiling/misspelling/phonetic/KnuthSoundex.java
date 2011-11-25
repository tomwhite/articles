package org.tiling.misspelling.phonetic;

/**
 * This is an implementation of Knuth's Soundex algorithm. Note that it relies
 * on Java 1.4 regex features, for simplicity of illustration (i.e. the
 * performance may be lamentable). 
 * <p>
 * Quote from Knuth, TAOCP, Volume 3, p394.
 * <blockquote>
 * 1. Retain the first letter of the name, and drop all occurrences of a, e, h, i, o, u, w, y in other positions.
 * 2. Assign the following numbers to the remaining letters after the first:
 *     b, f, p, v -> 1
 *     c, g, j, k, q, s, x, z -> 2
 *     d, t -> 3
 *     l -> 4
 *     m, n -> 5
 *     r -> 6
 * 3. If two or more letters with the same code were adjacent in the original name (before step 1),
 *    or adjacent except for interveneing h's and w's, omit all but the first.
 * 4. Convert to the form "letter, digit, digit, digit" by adding trailing zeros (if there are less than
 *    three digits), or by dropping rightmost digits (if there are more than three).
 * <blockquote>
 */
public class KnuthSoundex implements PhoneticEncoder {
	//                                            ABCDEFGHIJKLMNOPQRSTUVWXYZ
	private static final String SOUNDEX_DIGITS = "01230120022455012623010202";
	
	public String calculateCode(String string) {
		String word = string.toUpperCase();                                 // 01 ASHCROFT
		word = word.replaceAll("[^A-Z]", "");                               // 02
		if (word.length() == 0) {                                           // 03
			return "";                                                        // 04
		}                                                                   // 05
		char first = word.charAt(0);                                        // 06
		word = first + word.substring(1).replaceAll("[HW]", "");            // 07 ASCROFT
		StringBuffer sndx = new StringBuffer();                             // 08
		for (int i = 0; i < word.length(); i++) {                           // 09
			sndx.append(SOUNDEX_DIGITS.charAt((int) (word.charAt(i) - 'A'))); // 10
		}                                                                   // 11
		word = sndx.toString().replaceAll("(.)\\1+", "$1");                 // 12 026013
		word = first + word.substring(1);                                   // 13 A26013
		word = word.replaceAll("0", "");                                    // 14 A2613
		return (word + "000").substring(0, 4);                              // 15 A261
	}
}