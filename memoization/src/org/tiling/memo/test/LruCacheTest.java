package org.tiling.memo.test;

import org.tiling.memo.CacheStatistics;
import org.tiling.memo.LruCacheFactory;
import org.tiling.memo.Memoizer;

import junit.framework.TestCase;

public class LruCacheTest extends TestCase {

	public LruCacheTest(String name) {
		super(name);
	}

	public void test() {
		Foo foo = (Foo) Memoizer.memoize(new Foo() {
			String state = "";
			public String bar(String s) {
				state += s;
				return state;
			}
		},
		new LruCacheFactory(2));
		
		CacheStatistics cacheStatistics = (CacheStatistics) Memoizer.getCacheStatistics(foo);
		assertEquals("hits", 0, cacheStatistics.getHits());
		assertEquals("misses", 0, cacheStatistics.getMisses());

		assertEquals("a", foo.bar("a"));
		assertEquals("hits", 0, cacheStatistics.getHits());
		assertEquals("misses", 1, cacheStatistics.getMisses());

		assertEquals("a", foo.bar("a"));
		assertEquals("hits", 1, cacheStatistics.getHits());
		assertEquals("misses", 1, cacheStatistics.getMisses());
		
		assertEquals("ab", foo.bar("b"));
		assertEquals("hits", 1, cacheStatistics.getHits());
		assertEquals("misses", 2, cacheStatistics.getMisses());

		assertEquals("a", foo.bar("a"));
		assertEquals("hits", 2, cacheStatistics.getHits());
		assertEquals("misses", 2, cacheStatistics.getMisses());
		
		assertEquals("abc", foo.bar("c"));
		assertEquals("hits", 2, cacheStatistics.getHits());
		assertEquals("misses", 3, cacheStatistics.getMisses());
		
		assertEquals("abcb", foo.bar("b"));
		assertEquals("hits", 2, cacheStatistics.getHits());
		assertEquals("misses", 4, cacheStatistics.getMisses());
		
	}


}
