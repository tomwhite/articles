package org.tiling.s3map;

import java.util.Iterator;
import java.util.Set;

public class S3MapKeySetViewTest extends S3MapBaseTest {

	public void testKeySet() {
		map.put("fruit", "apple");
		map.put("drink", "tea");
		
		Set<String> keySet = map.keySet();
		
		assertThat(keySet.size(), eq(2));
		assertThat(keySet.contains("fruit"), eq(true));
		assertThat(keySet.contains("drink"), eq(true));

		assertThat(map.size(), eq(2));
		assertThat(map.containsKey("fruit"), eq(true));
		assertThat(map.containsKey("drink"), eq(true));
		
		assertThat(keySet.remove("fruit"), eq(true));
		
		assertThat(keySet.size(), eq(1));
		assertThat(keySet.contains("fruit"), eq(false));
		assertThat(keySet.contains("drink"), eq(true));
		
		assertThat(map.size(), eq(1));
		assertThat(map.containsKey("fruit"), eq(false));
		assertThat(map.containsKey("drink"), eq(true));
		
		keySet.clear();
		
		assertThat(keySet.size(), eq(0));
		assertThat(keySet.contains("fruit"), eq(false));
		assertThat(keySet.contains("drink"), eq(false));
		
		assertThat(map.size(), eq(0));
		assertThat(map.containsKey("fruit"), eq(false));
		assertThat(map.containsKey("drink"), eq(false));
	}
	
	public void testEmptyKeySetIterator() {
		Iterator<String> i = map.keySet().iterator();
		
		assertThat(i.hasNext(), eq(false));
		
		assertThatNextThrowsNoSuchElementException(i);
		assertThatRemoveThrowsIllegalStateException(i);		
	}

	public void testNonEmptyKeySetIterator() {
		map.put("fruit", "apple");
		map.put("drink", "tea");
		
		Iterator<String> i = map.keySet().iterator();
		
		// First element
		
		assertThat(i.hasNext(), eq(true));
		assertThat(i.next(), eq("drink"));
		
		i.remove();
		
		assertThat(map.keySet().size(), eq(1));
		assertThat(map.keySet().contains("drink"), eq(false));
		
		assertThat(map.size(), eq(1));
		assertThat(map.containsKey("drink"), eq(false));
		
		assertThatRemoveThrowsIllegalStateException(i);		
		
		// Second element
		
		assertThat(i.hasNext(), eq(true));
		assertThat(i.next(), eq("fruit"));
		
		i.remove();
		
		assertThat(map.keySet().size(), eq(0));
		assertThat(map.keySet().contains("fruit"), eq(false));
		
		assertThat(map.size(), eq(0));
		assertThat(map.containsKey("fruit"), eq(false));
		
		assertThatRemoveThrowsIllegalStateException(i);				
		
		// No more elements
		
		assertThatNextThrowsNoSuchElementException(i);
		assertThatRemoveThrowsIllegalStateException(i);		
	}
}
