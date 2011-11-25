package org.tiling.misspelling.phonetic;

/**
 * This is an implementation of the original Odell and Russel Soundex algorithm.
 * The details were found from the patent documents. 
 */
public class OdellRussellSoundex implements PhoneticEncoder {
	public String calculateCode(String string) {
		if (string.length() > 0) {
			char c = Character.toLowerCase(string.charAt(string.length() - 1));
			if (c == 's' || c == 'z') {
				string = string.substring(0, string.length() - 1);
			}
		}
		StringBuffer sb = new StringBuffer();
		String prev = "?";
		char next = '?';
		boolean usedVowel = false;
		for (int i = 0; i < string.length(); i++) {
			char c = string.charAt(i);
			if (!Character.isLetter(c)) { // other characters?
				continue;
			}
			next = (i < string.length() - 1) ? (string.charAt(i + 1)) : '?';
			if (sb.length() == 0) {
				sb.append(c);
				String mapped = map(c, next, usedVowel);
				if ("1".equals(mapped)) {
					usedVowel = true;
				}
				if (mapped.length() == 1) {
					prev = mapped;
				}
				continue;
			}
			String mapped = map(c, next, usedVowel);
			if ("1".equals(mapped)) {
				usedVowel = true;
			}
			if (!mapped.equals(prev)
				&& !".".equals(mapped)
				&& !"-".equals(mapped)) {
				sb.append(mapped);
			}
			if (mapped.length() == 1) {
				prev = mapped;
			}

		}
		return sb.toString();
	}
	private String map(char c, char next, boolean usedVowel) {
		switch (Character.toLowerCase(c)) {
			case 'h' :
			case 'j' :
			case 'w' :
				return "";
			case 'a' :
			case 'e' :
			case 'i' :
			case 'o' :
			case 'u' :
			case 'y' :
				return usedVowel ? "." : "1";
			case 'b' :
			case 'f' :
			case 'p' :
			case 'v' :
				return "2";
			case 'g' :
				if (Character.toLowerCase(next) == 'h') {
					return "-";
				}
				return "3";
			case 'c' :
			case 'k' :
			case 'q' :
			case 'x' :
			case 's' :
			case 'z' :
				return "3";
			case 'd' :
			case 't' :
				return "4";
			case 'l' :
				return "5";
			case 'm' :
				return "6";
			case 'n' :
				return "7";
			case 'r' :
				return "8";
			default :
				return "";
		}
	}
}