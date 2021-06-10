package com.hackerrank.sample.enums;

public enum DurationType {
	MILLISECOND(1), SECOND(1000), MINUTE(60_000), HOUR(3600_000), DAY(24*3600_000), WEEK(7*24*3600_000), MONTH(30*24*3600_000), YEAR(365*24*3600_000);

	private final int duration;

	DurationType(int duration) {
		this.duration = duration;
	}

	public int getDuration() {
		return duration;
	} 

}
