package org.tiling.scheduling.examples.iterators;

import calendrica.Gregorian;
import calendrica.ProtoDate;
import calendrica.Time;

import org.tiling.scheduling.ScheduleIterator;

import java.util.Calendar;
import java.util.Date;

/**
 * <code>SunsetIterator</code> returns a sequence of dates on subsequent days
 * representing the time of the local sunset for a given location,
 * specified by latitude and longitude.
 */
public class SunsetIterator implements ScheduleIterator {
	private final double latitude, longitude;
	private final Calendar calendar = Calendar.getInstance();

	public SunsetIterator(double latitude, double longitude) {
		this(latitude, longitude, new Date());
	}

	public SunsetIterator(double latitude, double longitude, Date date) {
		this.latitude = latitude;
		this.longitude = longitude;
		calendar.setTime(date);
		Date sunset = calculateSunset(latitude, longitude, date);
		if (!sunset.before(date)) {
			calendar.add(Calendar.DATE, -1);
		}
	}

	private static Date calculateSunset(double latitude, double longitude, Date date) {
		Calendar sunsetCalendar = Calendar.getInstance();
		sunsetCalendar.setTime(date);
		int fixedDate = Gregorian.toFixed(
			sunsetCalendar.get(Calendar.MONTH) + 1,
			sunsetCalendar.get(Calendar.DATE),
			sunsetCalendar.get(Calendar.YEAR)
		);

		int offset = - sunsetCalendar.getTimeZone().getOffset(
			sunsetCalendar.get(Calendar.ERA),
			sunsetCalendar.get(Calendar.YEAR),
			sunsetCalendar.get(Calendar.MONTH),
			sunsetCalendar.get(Calendar.DAY_OF_MONTH),
			sunsetCalendar.get(Calendar.DAY_OF_WEEK),
			sunsetCalendar.get(Calendar.HOUR_OF_DAY) * 60 * 60 * 1000 +
				sunsetCalendar.get(Calendar.MINUTE) * 60 * 1000 +
				sunsetCalendar.get(Calendar.SECOND) * 1000 +
				sunsetCalendar.get(Calendar.MILLISECOND)
		) / (60 * 1000);
	
		Time time = new Time(ProtoDate.standardFromLocal(ProtoDate.sunset(fixedDate, latitude, longitude), offset));
		sunsetCalendar.set(Calendar.HOUR_OF_DAY, time.hour);
		sunsetCalendar.set(Calendar.MINUTE, time.minute);
		sunsetCalendar.set(Calendar.SECOND, (int) time.second);
		sunsetCalendar.set(Calendar.MILLISECOND, (int) Math.round((time.second * 1000) % 1000));
		return sunsetCalendar.getTime();
	}

	public Date next() {
		calendar.add(Calendar.DATE, 1);
		return calculateSunset(latitude, longitude, calendar.getTime());
	}
}
