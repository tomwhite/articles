package org.tiling.computefarm.article;

public class Schedule {
	
	private final int firstPartitionBits;

	public Schedule(final int firstPartitionBits) {
		this.firstPartitionBits = firstPartitionBits;
	}
	
	public int getFirstPartitionBits() {
		return firstPartitionBits;
	}

}
