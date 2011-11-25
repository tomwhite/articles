package org.tiling.computefarm.article;

import java.util.ArrayList;
import java.util.List;

public class MultiprocessorScheduler {

	public Schedule[] findBestSchedules(final int[] times) {
		
		final int len = times.length;
		// TODO range check
		
		int totalTimes = 0;
		for (int i = 0; i < times.length; i++) {
			totalTimes += times[i];
		}
		
		List schedules = new ArrayList();
		int minSum = Integer.MAX_VALUE;
		final int N = 1 << (len - 1);
		for (int i = 0; i < N; i++) {
			int sum = 0;
			for (int j = 0; j < times.length; j++) {
				if ((i & (1 << j)) == 0) {
					sum += times[j];
				}
			}
			sum = Math.max(sum, totalTimes - sum);
			if (sum < minSum) {
				minSum = sum;
				schedules.clear();
				schedules.add(new Schedule(i));
			} else if (sum == minSum) {
				schedules.add(new Schedule(i));
			}
		}
		return (Schedule[]) schedules.toArray(new Schedule[schedules.size()]);
	}

}
