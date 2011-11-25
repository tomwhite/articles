package org.tiling.scheduling.examples.test;

import java.util.Calendar;

import junit.framework.TestCase;

import org.tiling.scheduling.ScheduleIterator;
import org.tiling.scheduling.examples.iterators.RestrictedDailyIterator;

public class RestrictedDailyIteratorTest extends TestCase {

	public RestrictedDailyIteratorTest(String name) {
		super(name);
	}
	
	public void test() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, Calendar.JUNE);
		cal.set(Calendar.DATE, 21);
		cal.set(Calendar.YEAR, 2003);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		int[] weekdays = new int[] {
		  Calendar.MONDAY,
		  Calendar.TUESDAY,
		  Calendar.WEDNESDAY,
		  Calendar.THURSDAY,
		  Calendar.FRIDAY
		};
		ScheduleIterator iterator =
			new RestrictedDailyIterator(9, 0, 0, weekdays, cal.getTime());
		// N.B. 21 June 2003 was a Saturday
		TimeAssert.assertDateTimeEquals("2003.06.23T09:00:00.000", iterator.next());
		TimeAssert.assertDateTimeEquals("2003.06.24T09:00:00.000", iterator.next());
		TimeAssert.assertDateTimeEquals("2003.06.25T09:00:00.000", iterator.next());
		TimeAssert.assertDateTimeEquals("2003.06.26T09:00:00.000", iterator.next());
		TimeAssert.assertDateTimeEquals("2003.06.27T09:00:00.000", iterator.next());
		TimeAssert.assertDateTimeEquals("2003.06.30T09:00:00.000", iterator.next());
	}
}
