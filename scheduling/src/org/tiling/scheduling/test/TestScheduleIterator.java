package org.tiling.scheduling.test;

import java.util.Calendar;
import java.util.Date;

import org.tiling.scheduling.ScheduleIterator;

public class TestScheduleIterator implements ScheduleIterator {
	private final Calendar calendar = Calendar.getInstance();
	
	private int nextCount = 0;
	private final int maxCount;
	
	public TestScheduleIterator() {
		this(-1);
	}

	public TestScheduleIterator(int maxCount) {
		this.maxCount = maxCount;
	}

	public Date next() {
		nextCount++;

		if (maxCount == -1 || nextCount <= maxCount) {
			calendar.add(Calendar.SECOND, 1);
			return calendar.getTime();
		} else {
			return null;
		}

	}

}
