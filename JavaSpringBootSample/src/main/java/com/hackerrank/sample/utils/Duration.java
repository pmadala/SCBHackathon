package com.hackerrank.sample.utils;

import com.hackerrank.sample.enums.DurationType;

public class Duration {

	private final int value;
	private final DurationType type;

	private Duration(int value, DurationType type) {
		this.value = value;
		this.type = type;
	}

	public static Duration of(int value, DurationType type) {
		return new Duration(value, type);
	}

	public DurationType getType() {
		return type;
	}

	public int getValue() {
		return value;
	}
}
