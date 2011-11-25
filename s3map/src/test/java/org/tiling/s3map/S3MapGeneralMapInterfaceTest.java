package org.tiling.s3map;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class S3MapGeneralMapInterfaceTest extends S3MapBaseTest {

	public void testZeroLengthKey() {
		try {
			map.put("", "value");
			fail("Should throw InvalidKeyException.");
		} catch (InvalidKeyException e) {
			// expected
		}		
	}
	
	public void testKeyTooLong() {
		String key = "";
		for (int i = 0; i < 1024; i++) {
			key += "a";
		}		
		assertThat(map.put(key, "value"), NULL);
		try {
			map.put(key + "a", "value");
			fail("Should throw InvalidKeyException.");
		} catch (InvalidKeyException e) {
			// expected
		}		
	}
	
	public void testPutNullValue() {
		try {
			map.put("fruit", null);
			fail("Should throw NullPointerException.");
		} catch (NullPointerException e) {
			// expected
		}
	}
	
	public void testSeparateInstances() {
		map.put("fruit", "apple");
		Map<String, String> secondMap = new S3Map<String>(connectionProperties.getProperty("bucket"),
				connectionProperties.getProperty("awsAccessKeyId"),
				connectionProperties.getProperty("awsSecretAccessKey"));
		assertThat(secondMap.containsKey("fruit"), eq(true));
		assertThat(secondMap.remove("fruit"), eq("apple"));
		assertThat(map.containsKey("fruit"), eq(false));
	}
	
	
	public void testLifecycle() {
		assertThat(map.isEmpty(), eq(true));
		assertThat(map.size(), eq(0));
		assertThat(map.containsKey("fruit"), eq(false));
		assertThat(map.get("fruit"), NULL);
		assertThat(map.keySet().isEmpty(), eq(true));
		
		assertThat(map.put("fruit", "apple"), NULL);
		map.putAll(Collections.singletonMap("drink", "tea"));
		
		assertThat(map.size(), eq(2));
		assertThat(map.containsKey("fruit"), eq(true));
		assertThat(map.get("fruit"), eq("apple"));
		assertThat(map.containsKey("drink"), eq(true));
		assertThat(map.get("drink"), eq("tea"));
		assertThat(map.keySet(), and(collectionContaining(eq("fruit")), collectionContaining(eq("drink"))));
		
		assertThat(map.remove("fruit"), eq("apple"));
		assertThat(map.put("drink", "coffee"), eq("tea"));
		map.clear();
		
		assertThat(map.isEmpty(), eq(true));
		assertThat(map.size(), eq(0));
		assertThat(map.containsKey("fruit"), eq(false));
		assertThat(map.get("fruit"), NULL);
		assertThat(map.keySet().isEmpty(), eq(true));
	}
	
	public void testCopyConstructor() {
		Map<String, String> data = new HashMap<String, String>();
		data.put("fruit", "apple");
		data.put("drink", "tea");
		map = new S3Map<String>(connectionProperties.getProperty("bucket"),
				connectionProperties.getProperty("awsAccessKeyId"),
				connectionProperties.getProperty("awsSecretAccessKey"),
				data);
		
		assertThat(map.size(), eq(2));
		assertThat(map.containsKey("fruit"), eq(true));
		assertThat(map.get("fruit"), eq("apple"));
		assertThat(map.containsKey("drink"), eq(true));
		assertThat(map.get("drink"), eq("tea"));		
		
	}

}
