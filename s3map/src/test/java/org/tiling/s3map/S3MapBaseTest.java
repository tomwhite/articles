package org.tiling.s3map;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Map.Entry;

import org.jmock.MockObjectTestCase;

public abstract class S3MapBaseTest extends MockObjectTestCase {

	protected Entry<String, String> fruitEntry = new SimpleEntry<String, String>("fruit", "apple");
	protected Entry<String, String> drinkEntry = new SimpleEntry<String, String>("drink", "tea");
	
	protected Properties connectionProperties;
	protected Map<String, String> map;
	
	@Override
	protected void setUp() throws IOException {
		connectionProperties = new Properties();
		connectionProperties.load(getClass().getResourceAsStream("S3MapTest.properties"));
		map = new S3Map<String>(connectionProperties.getProperty("bucket"),
				connectionProperties.getProperty("awsAccessKeyId"),
				connectionProperties.getProperty("awsSecretAccessKey"));
		//map = new java.util.Hashtable<String, String>();
		
		map.clear();
	}

	protected void assertThatNextThrowsNoSuchElementException(Iterator<?> i) {
		try {
			i.next();
			fail("Should throw NoSuchElementException.");
		} catch (NoSuchElementException e) {
			// expected
		}
	}

	protected void assertThatRemoveThrowsIllegalStateException(Iterator<?> i) {
		try {
			i.remove();
			fail("Should throw IllegalStateException.");
		} catch (IllegalStateException e) {
			// expected
		}
	}
}
