package org.tiling.scheduling.examples;

import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;

import org.tiling.scheduling.ScheduleIterator;
import org.tiling.scheduling.Scheduler;
import org.tiling.scheduling.SchedulerTask;
import org.tiling.scheduling.examples.iterators.CompositeIterator;
import org.tiling.scheduling.examples.iterators.RestrictedDailyIterator;

public class HeatingController {

	private final Scheduler scheduler = new Scheduler();
	private final SimpleDateFormat dateFormat =
		new SimpleDateFormat("dd MMM yyyy HH:mm:ss.SSS");
	private final int weekdayHourOfDay, weekdayMinute, weekdaySecond;
	private final int weekendHourOfDay, weekendMinute, weekendSecond;

	public HeatingController(int weekdayHourOfDay, int weekdayMinute, int weekdaySecond,
			int weekendHourOfDay, int weekendMinute, int weekendSecond) {
		this.weekdayHourOfDay = weekdayHourOfDay;
		this.weekdayMinute = weekdayMinute;
		this.weekdaySecond = weekdaySecond;
		this.weekendHourOfDay = weekendHourOfDay;
		this.weekendMinute = weekendMinute;
		this.weekendSecond = weekendSecond;
	}

	public void start() {
		int[] weekdays = new int[] {
		  Calendar.MONDAY,
		  Calendar.TUESDAY,
		  Calendar.WEDNESDAY,
		  Calendar.THURSDAY,
		  Calendar.FRIDAY
		};
		int[] weekend = new int[] {
		  Calendar.SATURDAY,
		  Calendar.SUNDAY
		};
		ScheduleIterator i = new CompositeIterator(
		  new ScheduleIterator[] {
			new RestrictedDailyIterator(weekdayHourOfDay, weekdayMinute, weekdaySecond, weekdays),
			new RestrictedDailyIterator(weekendHourOfDay, weekendMinute, weekendSecond, weekend)
		  }
		);		
		scheduler.schedule(new SchedulerTask() {
			public void run() {
				switchHeatingOn();
			}
			private void switchHeatingOn() {
				System.out.println("Switch heating on at " +
					dateFormat.format(new Date()));
				// Start a new thread to switch the heating on...
			}
		}, i);
	}

	public static void main(String[] args) {
		HeatingController heatingController =
			new HeatingController(8, 0, 0, 9, 0, 0);
		heatingController.start();
	}
}