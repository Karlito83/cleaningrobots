package de.tud.evaluation;

public abstract class AbstractState {
	
	protected String name;

	protected AbstractState (String name) {
		this.name = name;
	}

	public String getName() {
		return name;		
	}
	
	@Override
	public String toString() {
		return this.name;
	}
}
