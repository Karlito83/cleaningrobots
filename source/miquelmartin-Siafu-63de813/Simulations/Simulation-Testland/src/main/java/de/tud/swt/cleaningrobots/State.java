package de.tud.swt.cleaningrobots;

import java.util.HashMap;
import java.util.Map;

public class State {
	private static Map<String, State> states = new HashMap<String, State>();
	private String name;

	private State(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public static State createState(String name)
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