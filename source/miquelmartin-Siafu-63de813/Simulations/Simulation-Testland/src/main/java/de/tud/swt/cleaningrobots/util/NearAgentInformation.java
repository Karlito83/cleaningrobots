package de.tud.swt.cleaningrobots.util;

/**
 * Implements a counter for near robots.
 * 
 * @author Christopher Werner
 *
 */
public class NearAgentInformation {

	private int counter;
	private String name;
	
	public NearAgentInformation (String name) {
		this.name = name;
		this.counter = -1;
	}
	
	public String getName () {
		return this.name;
	}
	
	public int getCounter () {
		return this.counter;
	}
	
	public void addCounterOne () {
		this.counter++;
	}
	
	public void resetCounter () {
		this.counter = -1;
	}
}
