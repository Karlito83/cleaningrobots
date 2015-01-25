package de.tud.swt.cleaningrobots;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Field {
	private int x;
	private int y;
	private boolean isPassabel;
	private Set<State> states;

	public Field(int x, int y, boolean isPassabel)
	{
		this.x = x;
		this.y = y;
		this.isPassabel = isPassabel;
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
		return isPassabel;
	}
	
	@Override
	public String toString() {
		return "Field: " + x + "," + y + "[" + states + "]";
	}
}
