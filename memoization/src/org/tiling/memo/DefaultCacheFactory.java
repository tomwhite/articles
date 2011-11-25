package org.tiling.memo;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class DefaultCacheFactory implements CacheFactory {

	public Map createCache() {
		return Collections.synchronizedMap(new HashMap());
	}

}
