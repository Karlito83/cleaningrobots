package de.tud.swt.cleaningrobots.measure;

import java.util.LinkedList;
import java.util.List;

public class MinMaxAveLong {
	
	public int numberRobots;
	public List<Long> values;
	
	public MinMaxAveLong (int numberRobots) {
		this.numberRobots = numberRobots;
		this.values = new LinkedList<Long>();
	}
	
	public long getMaxValue () {		
		long bench = 0;
		for (long b : values) {
			bench = Math.max(bench, b);
		}
		return bench;
	}
	
	public long getMinValue () {
		long bench = Long.MAX_VALUE;
		for (long b : values) {
			bench = Math.min(bench, b);
		}
		return bench;
	}

	public long getAveValue () {
		long bench = 0;
		for (long b : values) {
			bench += b;
		}
		return bench/values.size();
	}
}
