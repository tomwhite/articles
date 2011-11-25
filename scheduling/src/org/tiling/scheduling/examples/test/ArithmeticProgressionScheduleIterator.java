package org.tiling.scheduling.examples.test;

import java.util.Date;

import org.tiling.scheduling.ScheduleIterator;

class ArithmeticProgressionScheduleIterator implements ScheduleIterator {
	private int time;
	private final int gap;
	private final int end;
	public ArithmeticProgressionScheduleIterator(int start, int gap) {
		this(start, gap, -1);
	}
	public ArithmeticProgressionScheduleIterator(int start, int gap, int end) {
		this.time = start;
		this.gap = gap;
		this.end = end;
	}
	public Date next() {
		if (end != -1 && time > end) {
			return null;
		} else {
			Date d = new Date(time);
			time += gap;
			return d;
		}
	}
}