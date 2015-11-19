package de.tud.swt.cleaningrobots.model;

import java.util.HashMap;
import java.util.Map;

import cleaningrobots.CleaningrobotsFactory;
import de.tud.evaluation.AbstractState;

public class State extends AbstractState {
	
	private Map<String, State> states = new HashMap<String, State>();
	
	public State(String name) {
		super(name);
	}
	
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

	public cleaningrobots.State exportModel() {
		cleaningrobots.State modelState = null;
		
		modelState = CleaningrobotsFactory.eINSTANCE.createState();
		modelState.setName(name);				
		
		return modelState;
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		if (obj != null && obj instanceof State){
			result = ((State)obj).getName().equals(this.getName());
		}
		return result;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
}
