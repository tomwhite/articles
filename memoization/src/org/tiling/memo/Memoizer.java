package org.tiling.memo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Memoizer implements CacheStatistics, InvocationHandler {
	
	private static final CacheFactory
		DEFAULT_CACHE_FACTORY = new DefaultCacheFactory(); 
	
	public static Object memoize(Object object) {
		return memoize(object, DEFAULT_CACHE_FACTORY);
	}
	public static Object memoize(Object object,
			CacheFactory cacheFactory) {
		return Proxy.newProxyInstance(
			object.getClass().getClassLoader(),
			object.getClass().getInterfaces(),
			new Memoizer(object, cacheFactory)
		);
	}
	
	public static CacheStatistics getCacheStatistics(Object memoizedClass) {
		return (CacheStatistics) Proxy.getInvocationHandler(memoizedClass);
	}
	
	private Object object;
	private CacheFactory cacheFactory;
	private Map caches = new HashMap();
		
	private Memoizer(Object object,
			CacheFactory cacheFactory) {
		this.object = object;
		this.cacheFactory = cacheFactory;
	}

	public Object invoke(Object proxy, Method method,
			Object[] args) throws Throwable {
				
		if (method.getReturnType().equals(Void.TYPE)) {
			// Don't cache void methods
			misses++;
			return invoke(method, args);
		} else {
			Map cache = getCache(method);
			List key = Arrays.asList(args);
			Object value = cache.get(key);
			if (value == null && !cache.containsKey(key)) {
				misses++;
				value = invoke(method, args);
				cache.put(key, value);
			} else {
				hits++;
			}
			return value;
		}
	}

	private Object invoke(Method method, Object[] args)
			throws Throwable {
		try {
			return method.invoke(object, args);
		} catch (InvocationTargetException e) {
			throw e.getTargetException();
		}
	}
	
	private synchronized Map getCache(Method m) {
		Map cache = (Map) caches.get(m);
		if (cache == null) {
			cache = cacheFactory.createCache();
			caches.put(m, cache);
		}
		return cache;
	}

	private int hits, misses;

	public int getHits() {
		return hits;
	}

	public int getMisses() {
		return misses;
	}

}
