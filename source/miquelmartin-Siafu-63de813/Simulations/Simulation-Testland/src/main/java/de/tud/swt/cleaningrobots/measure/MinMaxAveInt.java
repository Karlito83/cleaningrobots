package de.tud.swt.cleaningrobots.measure;

import java.util.LinkedList;
import java.util.List;

public class MinMaxAveInt {

	public int numberRobots;
	public List<Integer> values;
	
	public MinMaxAveInt (int numberRobots) {
		this.numberRobots = numberRobots;
		this.values = new LinkedList<Integer>();
	}
	
	public int getMaxValue () {		
		int value = 0;
		for (int v : values) {
			value = Math.max(value, v);
		}
		return value;
	}
	
	public int getMinValue () {
		int value = Integer.MAX_VALUE;
		for (int v : values) {
			value = Math.min(value, v);
		}
		return value;
	}

	public int getAveValue () {
		int value = 0;
		for (int v : values) {
			value += v;
		}
		return value/values.size();
	}
}
