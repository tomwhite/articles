@snip_start@package org.tiling.misspelling.test;

@snip_end@import junit.framework.TestCase;

import org.apache.commons.codec.language.DoubleMetaphone;

public class ApacheDoubleMetaphoneTest extends TestCase {
	public void test() {
		DoubleMetaphone metaphone = new DoubleMetaphone();
		
		assertEquals("HJMN", metaphone.encode("hegemony"));
		assertEquals("HJMN", metaphone.doubleMetaphone("hegemony"));
		assertEquals("HJMN", metaphone.doubleMetaphone("hegemony", false));
		
		assertEquals("HKMN", metaphone.doubleMetaphone("hegemony", true));
	}
}
