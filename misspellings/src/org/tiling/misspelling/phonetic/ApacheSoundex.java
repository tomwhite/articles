package org.tiling.misspelling.phonetic;

import org.apache.commons.codec.language.Soundex;

/**
 * This is a wrapper around the Apache Jakarta Commons Codec implementation
 * of Knuth's Soundex algorithm.
 */
public class ApacheSoundex implements org.tiling.misspelling.phonetic.PhoneticEncoder {
	private Soundex soundex = new Soundex();
	public String calculateCode(String string) {
		return soundex.soundex(string);
	}

}
