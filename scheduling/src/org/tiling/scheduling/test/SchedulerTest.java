package org.tiling.scheduling.test;

import org.tiling.scheduling.Scheduler;

import junit.framework.TestCase;

public class SchedulerTest extends TestCase {

	public SchedulerTest(String name) {
		super(name);
	}

	public void testCancelScheduler() {
		Scheduler scheduler = new Scheduler();
		TestSchedulerTask task = new TestSchedulerTask(this);
		TestScheduleIterator iterator = new TestScheduleIterator();
		scheduler.schedule(task, iterator);
		waitFor(task, 2);

		scheduler.cancel();
		try {
			scheduler.schedule(new TestSchedulerTask(this), new TestScheduleIterator());
			fail("Can't schedule using a cancelled Scheduler.");
		} catch (IllegalStateException e) {
			// expected
		}
	}

	public void testCancelSchedulerTask() {
		Scheduler scheduler = new Scheduler();
		TestSchedulerTask task = new TestSchedulerTask(this);
		assertTrue("Cancel method does not prevent further executions, " + 			"since it has not been scheduled", !task.cancel());
		task = new TestSchedulerTask(this);
		TestScheduleIterator iterator = new TestScheduleIterator();
		scheduler.schedule(task, iterator);
		waitFor(task, 2);
		
		assertTrue("Cancel method prevents further executions", task.cancel());
		try {
			scheduler.schedule(task, new TestScheduleIterator());
			fail("Can't schedule a cancelled task.");
		} catch (IllegalStateException e) {
			// expected
		}
		
		scheduler.schedule(new TestSchedulerTask(this), new TestScheduleIterator());
		waitFor(task, 2);

		scheduler.cancel();		

	}

	public void testCancelScheduleIterator() {
		Scheduler scheduler = new Scheduler();
		TestSchedulerTask task = new TestSchedulerTask(this);
		TestScheduleIterator iterator = new TestScheduleIterator(2);
		scheduler.schedule(task, iterator);
		waitFor(task, 2);
		
		try {
			scheduler.schedule(task, new TestScheduleIterator());
			fail("Can't schedule a cancelled task.");
		} catch (IllegalStateException e) {
			// expected
		}
		
		scheduler.schedule(new TestSchedulerTask(this), new TestScheduleIterator());
		waitFor(task, 2);

		scheduler.cancel();			
	}

	public void testDoubleSchedule() {
		Scheduler scheduler = new Scheduler();
		TestSchedulerTask task = new TestSchedulerTask(this);
		scheduler.schedule(task, new TestScheduleIterator());
		try {
			scheduler.schedule(task, new TestScheduleIterator());
			fail("Can't schedule a task twice.");
		} catch (IllegalStateException e) {
			// expected
		}
		scheduler.cancel();			
	}

	private void waitFor(TestSchedulerTask task, int iterations) {
		synchronized(this) {
			while (task.runCount() < iterations) {
				try {
					wait();
				} catch (InterruptedException e1) {
					return;
				}
			}
		}
	}

}
