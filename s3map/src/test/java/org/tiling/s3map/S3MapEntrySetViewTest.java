package org.tiling.s3map;

import java.util.Iterator;
import java.util.Set;
import java.util.Map.Entry;

public class S3MapEntrySetViewTest extends S3MapBaseTest {

	public void testEntrySet() {
		map.put("fruit", "apple");
		map.put("drink", "tea");
		
		Set<Entry<String, String>> entrySet = map.entrySet();
		
		assertThat(entrySet.size(), eq(2));
		assertThat(entrySet.contains(fruitEntry), eq(true));
		assertThat(entrySet.contains(drinkEntry), eq(true));

		assertThat(map.size(), eq(2));
		assertThat(map.containsKey("fruit"), eq(true));
		assertThat(map.containsKey("drink"), eq(true));
		
		assertThat(entrySet.remove(fruitEntry), eq(true));
		
		assertThat(entrySet.size(), eq(1));
		assertThat(entrySet.contains(fruitEntry), eq(false));
		assertThat(entrySet.contains(drinkEntry), eq(true));
		
		assertThat(map.size(), eq(1));
		assertThat(map.containsKey("fruit"), eq(false));
		assertThat(map.containsKey("drink"), eq(true));
		
		entrySet.clear();
		
		assertThat(entrySet.size(), eq(0));
		assertThat(entrySet.contains(fruitEntry), eq(false));
		assertThat(entrySet.contains(drinkEntry), eq(false));
		
		assertThat(map.size(), eq(0));
		assertThat(map.containsKey("fruit"), eq(false));
		assertThat(map.containsKey("drink"), eq(false));
	}
	
	public void testEmptyEntrySetIterator() {
		Iterator<Entry<String, String>> i = map.entrySet().iterator();
		
		assertThat(i.hasNext(), eq(false));
		
		assertThatNextThrowsNoSuchElementException(i);
		assertThatRemoveThrowsIllegalStateException(i);		
	}	
	
	public void testNonEmptyEntrySetIterator() {
		map.put("fruit", "apple");
		map.put("drink", "tea");
		
		Iterator<Entry<String, String>> i = map.entrySet().iterator();
		
		// First element
		
		assertThat(i.hasNext(), eq(true));
		assertThat(i.next(), eq(drinkEntry));
		
		i.remove();
		
		assertThat(map.entrySet().size(), eq(1));
		assertThat(map.entrySet().contains(drinkEntry), eq(false));
		
		assertThat(map.size(), eq(1));
		assertThat(map.containsKey("drink"), eq(false));
		
		assertThatRemoveThrowsIllegalStateException(i);		
		
		// Second element
		
		assertThat(i.hasNext(), eq(true));
		assertThat(i.next(), eq(fruitEntry));
		
		i.remove();
		
		assertThat(map.entrySet().size(), eq(0));
		assertThat(map.entrySet().contains(fruitEntry), eq(false));
		
		assertThat(map.size(), eq(0));
		assertThat(map.containsKey("fruit"), eq(false));
		
		assertThatRemoveThrowsIllegalStateException(i);				
		
		// No more elements
		
		assertThatNextThrowsNoSuchElementException(i);
		assertThatRemoveThrowsIllegalStateException(i);		
	}
}
