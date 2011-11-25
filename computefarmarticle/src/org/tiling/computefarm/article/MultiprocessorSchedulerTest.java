package org.tiling.computefarm.article;

import junit.framework.TestCase;

public class MultiprocessorSchedulerTest extends TestCase {
	public void test1() {
		int[] times = { 1, 2, 3, 4 };
		MultiprocessorScheduler scheduler = new MultiprocessorScheduler();
		Schedule[] schedules = scheduler.findBestSchedules(times);
		assertEquals(1, schedules.length);
		assertEquals("0110", 6, schedules[0].getFirstPartitionBits());
	}

	public void test2() {
		int[] times = { 1, 2, 4, 3 };
		MultiprocessorScheduler scheduler = new MultiprocessorScheduler();
		Schedule[] schedules = scheduler.findBestSchedules(times);
		assertEquals(1, schedules.length);
		assertEquals("0101", 5, schedules[0].getFirstPartitionBits());
	}

	public void test3() {
		int[] times = { 2, 3, 5, 7, 11 };
		MultiprocessorScheduler scheduler = new MultiprocessorScheduler();
		Schedule[] schedules = scheduler.findBestSchedules(times);
		assertEquals(1, schedules.length);
		assertEquals("01101", 13, schedules[0].getFirstPartitionBits());
	}

	public void longtest4() {
		int[] times = { 1, 2, 4, 3, 45, 5, 23, 17, 19, 23, 6, 21, 108, 67, 68, 69, 70, 65, 789, 656, 453, 4, 54, 24, 25, 26, 27 };
		MultiprocessorScheduler scheduler = new MultiprocessorScheduler();
		Schedule[] schedules = scheduler.findBestSchedules(times);
		//assertEquals(1, schedules.length);
		//assertEquals("0101", 5, schedules[0].getFirstPartitionBits());
	}

}
