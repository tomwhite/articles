package org.tiling.s3map;

import java.util.Collection;
import java.util.Iterator;

public class S3MapValuesViewTest extends S3MapBaseTest {
	
	public void testValues() {
		map.put("fruit", "apple");
		map.put("drink", "tea");
		
		Collection<String> values = map.values();
		
		assertThat(values.size(), eq(2));
		assertThat(values.contains("apple"), eq(true));
		assertThat(values.contains("tea"), eq(true));

		assertThat(map.size(), eq(2));
		assertThat(map.containsValue("apple"), eq(true));
		assertThat(map.containsValue("tea"), eq(true));
		
		assertThat(values.remove("apple"), eq(true));
		
		assertThat(values.size(), eq(1));
		assertThat(values.contains("apple"), eq(false));
		assertThat(values.contains("tea"), eq(true));

		assertThat(map.size(), eq(1));
		assertThat(map.containsValue("apple"), eq(false));
		assertThat(map.containsValue("tea"), eq(true));
		
		values.clear();
		
		assertThat(values.size(), eq(0));
		assertThat(values.contains("apple"), eq(false));
		assertThat(values.contains("tea"), eq(false));

		assertThat(map.size(), eq(0));
		assertThat(map.containsValue("apple"), eq(false));
		assertThat(map.containsValue("tea"), eq(false));
	}
	
	public void testEmptyValuesIterator() {
		Iterator<String> i = map.values().iterator();
		
		assertThat(i.hasNext(), eq(false));
		
		assertThatNextThrowsNoSuchElementException(i);
		assertThatRemoveThrowsIllegalStateException(i);		
	}		
	
	public void testNonEmptyValuesIterator() {
		map.put("fruit", "apple");
		map.put("drink", "tea");
		
		Iterator<String> i = map.values().iterator();
		
		// First element
		
		assertThat(i.hasNext(), eq(true));
		assertThat(i.next(), eq("tea"));
		
		i.remove();
		
		assertThat(map.values().size(), eq(1));
		assertThat(map.values().contains("tea"), eq(false));
		
		assertThat(map.size(), eq(1));
		assertThat(map.containsValue("tea"), eq(false));
		
		assertThatRemoveThrowsIllegalStateException(i);		
		
		// Second element
		
		assertThat(i.hasNext(), eq(true));
		assertThat(i.next(), eq("apple"));
		
		i.remove();
		
		assertThat(map.values().size(), eq(0));
		assertThat(map.values().contains("apple"), eq(false));
		
		assertThat(map.size(), eq(0));
		assertThat(map.containsValue("apple"), eq(false));
		
		assertThatRemoveThrowsIllegalStateException(i);				
		
		// No more elements
		
		assertThatNextThrowsNoSuchElementException(i);
		assertThatRemoveThrowsIllegalStateException(i);		
	}	
}
