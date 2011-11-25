package org.tiling.scheduling.examples.test;

import java.util.Calendar;
import java.util.Date;

import junit.framework.TestCase;

import org.tiling.scheduling.examples.iterators.SunsetIterator;

import calendrica.Gregorian;

/**
 * As noted in the test comments below, the time returned by {@link SunsetIterator}
 * may not be totally accurate. It is worth considering upgrading to the
 * calendrica code from the second edition of the book. 
 */
public class SunsetIteratorTest extends TestCase {

	public SunsetIteratorTest(String name) {
		super(name);
	}

	// Test data from Calendrical Calculations by Nachum Dershowitz and
	// Edward M. Reingold (Cambridge University Press, 1997).
	// See http://emr.cs.iit.edu/home/reingold/calendar-book/first-edition/.
	// Note that the data is different to that in the second edition!
	// It is also different to the data at http://www.timeanddate.com/worldclock/results.html?query=jerusalem
	public void testJerusalemSunset() {
		Gregorian g = new Gregorian(728714);
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, g.month - 1);
		cal.set(Calendar.DATE, g.day);
		cal.set(Calendar.YEAR, g.year);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
	
		Date start = cal.getTime();
	
		SunsetIterator iterator = new SunsetIterator(31.8, 35.2, start);
		TimeAssert.assertDateTimeEquals("1996.02.25T17:54:29.303", iterator.next());
	}

	// N.B. the data is slightly different to that at http://www.timeanddate.com/worldclock/city.html?n=136
	public void testLondonSunset() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, Calendar.JUNE);
		cal.set(Calendar.DATE, 21);
		cal.set(Calendar.YEAR, 2003);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
	
		Date start = cal.getTime();
	
		SunsetIterator iterator = new SunsetIterator(51.0 + 30 / 60, -(0.0 + 7 / 60), start);
		TimeAssert.assertDateTimeEquals("2003.06.21T21:18:07.559", iterator.next());
		TimeAssert.assertDateTimeEquals("2003.06.22T21:18:18.999", iterator.next());
	}

}
