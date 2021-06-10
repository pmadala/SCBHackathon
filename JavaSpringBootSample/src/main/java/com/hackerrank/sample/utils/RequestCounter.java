package com.hackerrank.sample.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import com.hackerrank.sample.enums.DurationType;

@Component
public class RequestCounter {

	private Map<String, Pair<Long, Integer>> map = new HashMap<>();

	public boolean increment(String username, String methodName, int duration, int usage, String type) {
		long now = System.currentTimeMillis();
		String key = username + methodName;
		
		if (!map.containsKey(key)) {
			map.put(key, Pair.of(now, 0));
		}

		Pair<Long, Integer> pair = map.get(key);
		Long startTime = pair.getFirst();
		Integer count = pair.getSecond();
		
		int actualDuration = DurationType.valueOf(type).getDuration() * duration;

		if (now - startTime <= actualDuration) {
			map.put(key, Pair.of(startTime, count + 1));
		} else {
			map.put(key, Pair.of(now, 1));
		}
		return map.get(key).getSecond() <= usage;
	}

}
