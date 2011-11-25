package org.tiling.scheduling.examples.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import junit.framework.Assert;

public class TimeAssert extends Assert {

	public static void assertTimeEquals(String expectedTimeString, Date time) {
		assertEquals(expectedTimeString, new SimpleDateFormat("HH:mm:ss.SSS").format(time));
	}
	public static void assertDateEquals(String expectedDateString, Date date) {
		assertEquals(expectedDateString, new SimpleDateFormat("yyyy.MM.dd").format(date));
	}
	public static void assertDateTimeEquals(String expectedDateTimeString, Date dateTime) {
		assertEquals(expectedDateTimeString, new SimpleDateFormat("yyyy.MM.dd'T'HH:mm:ss.SSS").format(dateTime));
	}
}