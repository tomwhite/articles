package org.tiling.scheduling.examples.test;

import java.util.Calendar;

import org.tiling.scheduling.ScheduleIterator;

import org.tiling.scheduling.examples.iterators.OffsetIterator;

import junit.framework.TestCase;

public class OffsetIteratorTest extends TestCase {
	public OffsetIteratorTest(String name) {
		super(name);
	}

	public void test() {
		ScheduleIterator t1 = new ArithmeticProgressionScheduleIterator(10, 1, 12);
		ScheduleIterator iterator = new OffsetIterator(t1, Calendar.MILLISECOND, -5);
		assertEquals(5, iterator.next().getTime());
		assertEquals(6, iterator.next().getTime());
		assertEquals(7, iterator.next().getTime());
		assertEquals(null, iterator.next());
	}
}
