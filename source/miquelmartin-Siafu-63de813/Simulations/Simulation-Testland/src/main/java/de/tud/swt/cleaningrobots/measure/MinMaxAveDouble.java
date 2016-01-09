package de.tud.swt.cleaningrobots.measure;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Minimal, Maximal and Averages vales of a list with Double values.
 * 
 * @author Christopher Werner
 *
 */
public class MinMaxAveDouble {
	
	public int numberRobots;
	public List<Double> values;
	
	public MinMaxAveDouble (int numberRobots) {
		this.numberRobots = numberRobots;
		this.values = new LinkedList<Double>();
	}
	
	//Time Values
	public double getMaxValue () {		
		double value = 0;
		for (double v : values) {
			value = Math.max(value, v);
		}
		return value;
	}
	
	public double getMinValue () {
		double value = Double.MAX_VALUE;
		for (double v : values) {
			value = Math.min(value, v);
		}
		return value;
	}

	public double getAveValue () {
		Collections.sort(values);
		return values.get(values.size()/2);
		/*double value = 0;
		for (double v : values) {
			value += v;
		}
		return value/values.size();*/
	}
}
