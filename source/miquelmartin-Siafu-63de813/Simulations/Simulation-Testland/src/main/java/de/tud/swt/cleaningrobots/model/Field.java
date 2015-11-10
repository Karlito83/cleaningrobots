package de.tud.swt.cleaningrobots.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import cleaningrobots.CleaningrobotsFactory;
import de.tud.swt.cleaningrobots.util.ImportExportConfiguration;

public class Field {
	
	private Position pos;
	private boolean isPassable;
	private int changedIteration;
	private Map<State, Integer> states;
	
	public Field(int x, int y, boolean isPassable, int iteration)
	{
		this.changedIteration = iteration;
		this.pos = new Position(x, y);
		this.isPassable = isPassable;
		this.states = new HashMap<State, Integer>();		
	}
	
	public Position getPos() {
		return this.pos;
	}
	
	public int getChangeIteration () {
		return this.changedIteration;
	}

	public void addState(State state, int iteration) {
		this.changedIteration = iteration;
		states.put(state, this.changedIteration);
	}
	
	public boolean isPassable() {
		return isPassable;
	}
	
	public boolean containsState(State state) {
		return this.states.keySet().contains(state);
	}	

	public Set<State> getStates() {
		return this.states.keySet();
	}
	
	@Override
	public String toString() {
		return "Field: " + pos.toString() + "[" + states + "]";
	}

	public cleaningrobots.Field exportModel(ImportExportConfiguration config) {
		cleaningrobots.Field modelField = null;
		
		modelField = CleaningrobotsFactory.eINSTANCE.createField();
		
		modelField.setPos(pos.exportModel());
		if (config.iteration == -1) 
		{
			if (config.knownStates.isEmpty())
			{
				//Field mit allen States soll zurück gegeben werden
				for (State state : states.keySet()){
					modelField.getStates().add(state.exportModel());
				}
			} else {
				boolean proof = false;
				//nur wenn State auch bei knownstates dann hinzufügen
				for (State state : states.keySet()){
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
		} else {
			if (this.changedIteration <= config.iteration)
				return null;
			boolean proof = false;
			if (config.knownStates.isEmpty())
			{
				//Field mit allen States soll zurück gegeben werden
				for (State state : states.keySet()){
					//nur hinzufügen wenn später erstellt wurde als iteration ist
					if (states.get(state) > config.iteration) {
						proof = true;
						modelField.getStates().add(state.exportModel());
					}
				}
			} else {
				//nur wenn State auch bei knownstates dann hinzufügen
				for (State state : states.keySet()) {
					if (config.knownStates.contains(state) && states.get(state) > config.iteration)
					{
						proof = true;
						modelField.getStates().add(state.exportModel());
					}				
				}
			}
			if (!proof) {
				return null;
			}
		}
		return modelField;
	}
}
