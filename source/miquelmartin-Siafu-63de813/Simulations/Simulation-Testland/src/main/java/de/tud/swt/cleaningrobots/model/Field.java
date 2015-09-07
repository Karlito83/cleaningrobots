package de.tud.swt.cleaningrobots.model;

import java.util.HashSet;
import java.util.Set;

import cleaningrobots.CleaningrobotsFactory;
import de.tud.swt.cleaningrobots.util.ImportExportConfiguration;

public class Field {
	private Position pos;
	private boolean isPassable;
	private Set<State> states;
	
	public Field(int x, int y, boolean isPassable)
	{
		this.pos = new Position(x, y);
		this.isPassable = isPassable;
		this.states = new HashSet<State>();
	}
	
	public Position getPos() {
		return this.pos;
	}
	
	/*public int getX() {
		return this.pos.getX();
	}
	public int getY() {
		return this.pos.getY();
	}*/

	public void addState(State state) {
		states.add(state);
	}
	
	public boolean isPassable() {
		return isPassable;
	}
	
	@Override
	public String toString() {
		return "Field: " + pos.toString() + "[" + states + "]";
	}

	public Set<State> getStates() {
		return this.states;
	}

	public cleaningrobots.Field exportModel(ImportExportConfiguration config) {
		cleaningrobots.Field modelField = null;
		
		modelField = CleaningrobotsFactory.eINSTANCE.createField();
		
		modelField.setPos(pos.exportModel());
		if (config.knownStates.isEmpty())
		{
			//Field mit allen States soll zurück gegeben werden
			for (State state : states){
				modelField.getStates().add(state.exportModel());
			}
		} else {
			boolean proof = false;
			//nur wenn State auch bei knownstates dann hinzufügen
			for (State state : states){
				if (config.knownStates.contains(state))
				{
					proof = true;
					modelField.getStates().add(state.exportModel());
				}				
			}
			if (!proof) {
				return null;
			}
		}
		
		return modelField;
	}
}
