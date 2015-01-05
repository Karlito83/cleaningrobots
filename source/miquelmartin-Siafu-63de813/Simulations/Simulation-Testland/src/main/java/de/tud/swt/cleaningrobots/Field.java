package de.tud.swt.cleaningrobots;

import java.util.Set;

public class Field {
	private int x;
	private int y;
	private Set<State> states;

	public Field(int x, int y)
	{
		this.x = x;
		this.y = y;
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
}
