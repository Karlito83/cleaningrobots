package de.tud.swt.cleaningrobots.model;

import java.util.HashSet;
import java.util.Set;

import cleaningrobots.CleaningrobotsFactory;

public class Field {
	private int x;
	private int y;
	private boolean isPassable;
	private Set<State> states;
	
	public Field(int x, int y, boolean isPassable)
	{
		this.x = x;
		this.y = y;
		this.isPassable = isPassable;
		this.states = new HashSet<State>();
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}

	public void addState(State state) {
		states.add(state);
	}
	
	public boolean isPassable() {
		return isPassable;
	}
	
	@Override
	public String toString() {
		return "Field: " + x + "," + y + "[" + states + "]";
	}

	public Set<State> getStates() {
		return this.states;
	}

	public cleaningrobots.Field exportModel() {
		cleaningrobots.Field modelField = null;
		
		modelField = CleaningrobotsFactory.eINSTANCE.createField();
		modelField.setXpos(x);
		modelField.setYpos(y);
		
		for (State state : states){
			modelField.getStates().add(state.exportModel());
		}
		
		return modelField;
	}
}
