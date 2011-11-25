package org.tiling.scheduling.test;

import org.tiling.scheduling.SchedulerTask;

public class TestSchedulerTask extends SchedulerTask {

	private final Object waiter;
	private int runCount = 0;
	
	public TestSchedulerTask(Object waiter) {
		this.waiter = waiter;		
	}
	
	public void run() {
		runCount++;
		synchronized(waiter) {
			waiter.notify();
		}
	}

	public int runCount() {
		return runCount;
	}
}
