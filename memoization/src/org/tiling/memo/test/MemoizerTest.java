package org.tiling.memo.test;

import org.tiling.memo.CacheStatistics;
import org.tiling.memo.Memoizer;

import junit.framework.TestCase;

public class MemoizerTest extends TestCase {

	public MemoizerTest(String name) {
		super(name);
	}
	
	public void testNonNullValues() {
		Foo foo = (Foo) Memoizer.memoize(new Foo() {
			String state = "";
			public String bar(String s) {
				state += s;
				return state;
			}
		});
		
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
		
	}

	public void testNullArgument() {
		Foo foo = (Foo) Memoizer.memoize(new Foo() {
			public String bar(String s) {
				return "Hi";
			}
		});
		
		CacheStatistics cacheStatistics = (CacheStatistics) Memoizer.getCacheStatistics(foo);
		assertEquals("hits", 0, cacheStatistics.getHits());
		assertEquals("misses", 0, cacheStatistics.getMisses());

		assertEquals("Hi", foo.bar(null));
		assertEquals("hits", 0, cacheStatistics.getHits());
		assertEquals("misses", 1, cacheStatistics.getMisses());

		assertEquals("Hi", foo.bar(null));
		assertEquals("hits", 1, cacheStatistics.getHits());
		assertEquals("misses", 1, cacheStatistics.getMisses());
	}
	
	public void testNullReturnValue() {
		Foo foo = (Foo) Memoizer.memoize(new Foo() {
			public String bar(String s) {
				return null;
			}
		});
		
		CacheStatistics cacheStatistics = (CacheStatistics) Memoizer.getCacheStatistics(foo);
		assertEquals("hits", 0, cacheStatistics.getHits());
		assertEquals("misses", 0, cacheStatistics.getMisses());

		assertNull(foo.bar(null));
		assertEquals("hits", 0, cacheStatistics.getHits());
		assertEquals("misses", 1, cacheStatistics.getMisses());

		assertNull(foo.bar(null));
		assertEquals("hits", 1, cacheStatistics.getHits());
		assertEquals("misses", 1, cacheStatistics.getMisses());		
	}
	
	public void testVoidMethod() {
		VoidFoo foo = (VoidFoo) Memoizer.memoize(new VoidFoo() {
			public void bar(String s) {
			}
		});
		
		CacheStatistics cacheStatistics = (CacheStatistics) Memoizer.getCacheStatistics(foo);
		assertEquals("hits", 0, cacheStatistics.getHits());
		assertEquals("misses", 0, cacheStatistics.getMisses());

		foo.bar("a");
		assertEquals("hits", 0, cacheStatistics.getHits());
		assertEquals("misses", 1, cacheStatistics.getMisses());

		foo.bar("a");
		assertEquals("hits", 0, cacheStatistics.getHits());
		assertEquals("misses", 2, cacheStatistics.getMisses());		
		
	}

	public void testCheckedException() {
		ExceptionalFoo foo = (ExceptionalFoo) Memoizer.memoize(new ExceptionalFoo() {
			public String bar(String s) throws Exception {
				throw new Exception("Oops");
			}
		});
		
		CacheStatistics cacheStatistics = (CacheStatistics) Memoizer.getCacheStatistics(foo);
		assertEquals("hits", 0, cacheStatistics.getHits());
		assertEquals("misses", 0, cacheStatistics.getMisses());

		try {
			foo.bar("a");
			fail("bar should throw Exception");
		} catch (Exception e) {
			// expected
		}
		assertEquals("hits", 0, cacheStatistics.getHits());
		assertEquals("misses", 1, cacheStatistics.getMisses());

		try {
			foo.bar("a");
			fail("bar should throw Exception");
		} catch (Exception e) {
			// expected
		}
		assertEquals("hits", 0, cacheStatistics.getHits());
		assertEquals("misses", 2, cacheStatistics.getMisses());
		
	}

	public void testUncheckedException() {
		Foo foo = (Foo) Memoizer.memoize(new Foo() {
			public String bar(String s) {
				throw new RuntimeException("Oops");
			}
		});
		
		CacheStatistics cacheStatistics = (CacheStatistics) Memoizer.getCacheStatistics(foo);
		assertEquals("hits", 0, cacheStatistics.getHits());
		assertEquals("misses", 0, cacheStatistics.getMisses());

		try {
			foo.bar("a");
			fail("bar should throw RuntimeException");
		} catch (RuntimeException e) {
			// expected
		}
		assertEquals("hits", 0, cacheStatistics.getHits());
		assertEquals("misses", 1, cacheStatistics.getMisses());

		try {
			foo.bar("a");
			fail("bar should throw RuntimeException");
		} catch (RuntimeException e) {
			// expected
		}
		assertEquals("hits", 0, cacheStatistics.getHits());
		assertEquals("misses", 2, cacheStatistics.getMisses());
		
		
	}

}
