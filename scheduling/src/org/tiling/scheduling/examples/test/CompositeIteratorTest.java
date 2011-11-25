package org.tiling.scheduling.examples.test;

import org.tiling.scheduling.ScheduleIterator;

import org.tiling.scheduling.examples.iterators.CompositeIterator;

import junit.framework.TestCase;

public class CompositeIteratorTest extends TestCase {

	public CompositeIteratorTest(String name) {
		super(name);
	}

	public void testZero() {
		ScheduleIterator compositeIterator = new CompositeIterator(new ScheduleIterator[0]);
		assertEquals(null, compositeIterator.next());
		assertEquals(null, compositeIterator.next());
	}

	public void testOne() {
		ScheduleIterator t1 = new ArithmeticProgressionScheduleIterator(0, 3);
		ScheduleIterator compositeIterator = new CompositeIterator(new ScheduleIterator[] { t1 });
		assertEquals(0, compositeIterator.next().getTime());
		assertEquals(3, compositeIterator.next().getTime());
		assertEquals(6, compositeIterator.next().getTime());
	}

	public void testTwo() {
		ScheduleIterator t1 = new ArithmeticProgressionScheduleIterator(0, 3);
		ScheduleIterator t2 = new ArithmeticProgressionScheduleIterator(1, 2);
		ScheduleIterator compositeIterator = new CompositeIterator(new ScheduleIterator[] { t1, t2 });
		assertEquals(0, compositeIterator.next().getTime());
		assertEquals(1, compositeIterator.next().getTime());
		assertEquals(3, compositeIterator.next().getTime());
		assertEquals(5, compositeIterator.next().getTime());
		assertEquals(6, compositeIterator.next().getTime());
		assertEquals(7, compositeIterator.next().getTime());
		assertEquals(9, compositeIterator.next().getTime());
		assertEquals(11, compositeIterator.next().getTime());
		assertEquals(12, compositeIterator.next().getTime());
	}

	public void testTwoWithNull() {
		ScheduleIterator t1 = new ArithmeticProgressionScheduleIterator(0, 3, 3);
		ScheduleIterator t2 = new ArithmeticProgressionScheduleIterator(1, 2, 7);
		ScheduleIterator compositeIterator = new CompositeIterator(new ScheduleIterator[] { t1, t2 });
		assertEquals(0, compositeIterator.next().getTime());
		assertEquals(1, compositeIterator.next().getTime());
		assertEquals(3, compositeIterator.next().getTime());
		assertEquals(5, compositeIterator.next().getTime());
		assertEquals(7, compositeIterator.next().getTime());
		assertNull(compositeIterator.next());
		assertNull(compositeIterator.next());
	}

}
