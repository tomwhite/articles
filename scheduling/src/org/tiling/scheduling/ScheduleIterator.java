package org.tiling.scheduling;

import java.util.Date;

// Subsequent calls to next must return strictly larger dates
public interface ScheduleIterator {
	public Date next();
}
