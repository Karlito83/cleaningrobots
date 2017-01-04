package de.tud.swt.cleaningrobots.model;

import cleaningrobots.CleaningrobotsFactory;

/**
 * State of a field or of the world.
 * 
 * @author Christopher Werner
 *
 */
public class State {
		
	private String name;
	
	public State(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;		
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
