package org.tiling.scheduling.examples.test;

import java.util.Calendar;

import junit.framework.TestCase;

import org.tiling.scheduling.ScheduleIterator;
import org.tiling.scheduling.examples.iterators.DailyIterator;

public class DailyIteratorTest extends TestCase {

	public DailyIteratorTest(String name) {
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
	
		ScheduleIterator iterator = new DailyIterator(9, 0, 0, cal.getTime());
		TimeAssert.assertDateTimeEquals("2003.06.21T09:00:00.000", iterator.next());
		TimeAssert.assertDateTimeEquals("2003.06.22T09:00:00.000", iterator.next());
	}
}
