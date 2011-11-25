package org.tiling.misspelling.phonetic;

import org.apache.commons.codec.language.Metaphone;

/**
 * This is a wrapper around the Apache Jakarta Commons Codec implementation
 * of the Metaphone algorithm.
 */
public class ApacheMetaphone implements PhoneticEncoder {
	private Metaphone metaphone = new Metaphone();
	public String calculateCode(String string) {
		return metaphone.metaphone(string);
	}
}
