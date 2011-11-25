package org.tiling.misspelling.test;

import org.tiling.misspelling.phonetic.ApacheSoundex;

public class ApacheSoundexTest extends AbstractSoundexTest {
	public void setUp() {
		soundex = new ApacheSoundex();
	}
}
