package org.tiling.memo;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class LruCacheFactory implements CacheFactory {

	private int maxEntries;

	public LruCacheFactory(final int maxEntries) {
		this.maxEntries = maxEntries;
	}

	public Map createCache() {
		Map cache =
			new LinkedHashMap(maxEntries + 1, 0.75F, true) {
			// Remove the oldest entry if there are more
			// than maxEntries
			public boolean removeEldestEntry(Map.Entry e) {
				return size() > maxEntries;
			}
		};
		return Collections.synchronizedMap(cache);
	}

}
