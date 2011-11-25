package org.tiling.misspelling.phonetic;

/**
 * This is an implementation of the Soundex algorithm published in the O'Reilly
 * Python cookbook, coverted from Python to Java.
 */
public class OReillyPythonSoundex implements PhoneticEncoder {
	
	private static final String SOUNDEX_DIGITS = "01230120022455012623010202";

	public String calculateCode(String string) {
		String word = string.toUpperCase();
		char fc = 0;
		StringBuffer sndx = new StringBuffer();
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if (Character.isLetter(c)) {
				if (fc == 0) {
					fc = c;
				}
				char digit = SOUNDEX_DIGITS.charAt((int) (c - 'A'));
				if (sndx.length() == 0 || digit != sndx.charAt(sndx.length() - 1)) {
					sndx.append(digit);
				}
			}
		}
		String soundex = fc + sndx.substring(1);
		soundex = soundex.replaceAll("0", "");
		return (soundex + "000").substring(0, 4);
	}

}
