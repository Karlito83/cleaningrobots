package de.tud.swt.cleaningrobots.measure;

import java.util.LinkedList;
import java.util.List;

public class MinMaxAveEvaluation {
	
	public int numberRobots;
	
	public List<Long> benchmarkTime;
	public List<Integer> completeTicks;
	public List<Double> completeTime;
	public List<Double> completeEnergie;
	public List<Integer> completeExchange;
	
	public MinMaxAveEvaluation (int numberRobots) {
		this.numberRobots = numberRobots;
		this.benchmarkTime = new LinkedList<Long>();
		this.completeEnergie = new LinkedList<Double>();
		this.completeExchange = new LinkedList<Integer>();
		this.completeTicks = new LinkedList<Integer>();
		this.completeTime = new LinkedList<Double>();
	}
	
	//Time Values
	public double getMaxTime () {		
		double value = 0;
		for (double v : completeTime) {
			value = Math.max(value, v);
		}
		return value;
	}
	
	public double getMinTime () {
		double value = Double.MAX_VALUE;
		for (double v : completeTime) {
			value = Math.min(value, v);
		}
		return value;
	}

	public double getAveTime () {
		double value = 0;
		for (double v : completeTime) {
			value += v;
		}
		return value/completeTime.size();
	}
		
	//Energie Values
	public double getMaxEnergie () {		
		double value = 0;
		for (double v : completeEnergie) {
			value = Math.max(value, v);
		}
		return value;
	}
		
	public double getMinEnergie () {
		double value = Integer.MAX_VALUE;
		for (double v : completeEnergie) {
			value = Math.min(value, v);
		}
		return value;
	}

	public double getAveEnergie () {
		double value = 0;
		for (double v : completeEnergie) {
			value += v;
		}
		return value/completeEnergie.size();
	}
	
	//Ticks Values
	public int getMaxTicks () {		
		int value = 0;
		for (int v : completeTicks) {
			value = Math.max(value, v);
		}
		return value;
	}
	
	public int getMinTicks () {
		int value = Integer.MAX_VALUE;
		for (int v : completeTicks) {
			value = Math.min(value, v);
		}
		return value;
	}

	public int getAveTicks () {
		int value = 0;
		for (int v : completeTicks) {
			value += v;
		}
		return value/completeTicks.size();
	}
	
	//Exchange Values
	public int getMaxExchange () {		
		int value = 0;
		for (int v : completeExchange) {
			value = Math.max(value, v);
		}
		return value;
	}
	
	public int getMinExchange () {
		int value = Integer.MAX_VALUE;
		for (int v : completeExchange) {
			value = Math.min(value, v);
		}
		return value;
	}

	public int getAveExchange () {
		int value = 0;
		for (int v : completeExchange) {
			value += v;
		}
		return value/completeExchange.size();
	}
	
	//BenchmarkTime Values
	public long getMaxBench () {		
		long bench = 0;
		for (long b : benchmarkTime) {
			bench = Math.max(bench, b);
		}
		return bench;
	}
	
	public long getMinBench () {
		long bench = Long.MAX_VALUE;
		for (long b : benchmarkTime) {
			bench = Math.min(bench, b);
		}
		return bench;
	}

	public long getAveBench () {
		long bench = 0;
		for (long b : benchmarkTime) {
			bench += b;
		}
		return bench/benchmarkTime.size();
	}

}
