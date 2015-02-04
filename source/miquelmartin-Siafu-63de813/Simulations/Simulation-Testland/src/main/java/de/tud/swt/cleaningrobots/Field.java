package de.tud.swt.cleaningrobots;

import java.util.HashSet;
import java.util.Set;

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
}
