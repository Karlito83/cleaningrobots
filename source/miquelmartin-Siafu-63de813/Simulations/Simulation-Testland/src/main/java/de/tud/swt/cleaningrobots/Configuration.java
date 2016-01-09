package de.tud.swt.cleaningrobots;

import java.util.HashMap;
import java.util.Map;

import de.tud.evaluation.WorkingConfiguration;
import de.tud.swt.cleaningrobots.model.State;

/**
 * The configuration class contains the values of the test case and a map with all states in it.
 * 
 * @author Christopher Werner
 *
 */
public class Configuration {
	
	/**
	 * Values of the test case.
	 */
	public WorkingConfiguration wc;
	private Map<String, State> states = new HashMap<String, State>();
	
	public Configuration (WorkingConfiguration wc) {
		this.wc = wc;
	}
		
	/**
	 * Create a state or give one back when it already exists.
	 * @param name
	 * @return
	 */
	public State createState(String name)
	{
		State result = null;
		if(states.containsKey(name))
		{
			result = states.get(name);
		}
		else
		{
			result = new State(name);
			states.put(name, result);
		}
		return result;
	}
}
