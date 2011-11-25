@snip_start@package org.tiling.misspelling.test;

@snip_end@import junit.framework.TestCase;

import org.apache.commons.codec.language.Metaphone;

public class ApacheMetaphoneTest extends TestCase {
	public void test() {
		Metaphone metaphone = new Metaphone();
	    assertEquals("LM", metaphone.encode("lam"));
	    assertEquals("LM", metaphone.metaphone("lam"));
	    assertEquals(metaphone.encode("lamb"), metaphone.encode("lam"));
	    assertTrue(metaphone.isMetaphoneEqual("lamb", "lam"));
	}
}
