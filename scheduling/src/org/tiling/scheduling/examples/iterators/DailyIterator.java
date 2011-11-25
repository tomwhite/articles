package org.tiling.scheduling.examples.iterators;

import org.tiling.scheduling.ScheduleIterator;

import java.util.Calendar;
import java.util.Date;

/**
 * A <code>DailyIterator</code> returns a sequence of dates on subsequent days
 * representing the same time each day.
 */
public class DailyIterator implements ScheduleIterator {
	private final int hourOfDay, minute, second;
	private final Calendar calendar = Calendar.getInstance();

	public DailyIterator(int hourOfDay, int minute, int second) {
		this(hourOfDay, minute, second, new Date());
	}

	public DailyIterator(int hourOfDay, int minute, int second, Date date) {
		this.hourOfDay = hourOfDay;
		this.minute = minute;
		this.second = second;
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
		calendar.set(Calendar.MINUTE, minute);
		calendar.set(Calendar.SECOND, second);
		calendar.set(Calendar.MILLISECOND, 0);
		if (!calendar.getTime().before(date)) {
			calendar.add(Calendar.DATE, -1);
		}
	}

	public Date next() {
		calendar.add(Calendar.DATE, 1);
		return calendar.getTime();
	}

}
