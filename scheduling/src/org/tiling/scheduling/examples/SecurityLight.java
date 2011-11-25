package org.tiling.scheduling.examples;

import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;

import org.tiling.scheduling.ScheduleIterator;
import org.tiling.scheduling.Scheduler;
import org.tiling.scheduling.SchedulerTask;
import org.tiling.scheduling.examples.iterators.OffsetIterator;
import org.tiling.scheduling.examples.iterators.SunsetIterator;

public class SecurityLight {
	
	private final Scheduler scheduler = new Scheduler();
	private final SimpleDateFormat dateFormat =
		new SimpleDateFormat("dd MMM yyyy HH:mm:ss.SSS");
	private final double latitude, longitude;

	public SecurityLight(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public void start() {
		ScheduleIterator i = new OffsetIterator(
			new SunsetIterator(latitude, longitude),
			Calendar.MINUTE, -15
		);
		scheduler.schedule(new SchedulerTask() {
			public void run() {
				switchLightOn();
			}
			private void switchLightOn() {
				System.out.println("Switch light on at " +
					dateFormat.format(new Date()));
				// Start a new thread to switch the light on,
				// and schedule the light to be turned off when desired
			}
		}, i);
	}

	public static void main(String[] args) {
		// London is at 51° 30' North, 0° 07' West
		SecurityLight securityLight = new SecurityLight(51.0 + 30 / 60, -(0.0 + 7 / 60));
		securityLight.start();
	}
}