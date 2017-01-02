package de.tud.swt.cleaningrobots.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import cleaningrobots.CleaningrobotsFactory;
import de.tud.swt.cleaningrobots.util.ImportExportConfiguration;

/**
 * Represent a Position and a list of states for the world representation.
 * 
 * @author Christopher Werner
 *
 */
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
		return "Field: " + pos.toString() + " [" + states + "]";
	}
	
	public Field exportWithoutModel (ImportExportConfiguration config, int iteration) {
		Field modelField = new Field(pos.getX(), pos.getY(), isPassable, iteration);
		
		if (config.iteration == -1) 
		{
			if (config.knownStates.isEmpty())
			{
				//return Field with all States
				for (State state : states.keySet()){
					modelField.addState(state, iteration);
				}
			} else {
				boolean proof = false;
				//add if state is also in known states
				for (State state : states.keySet()){
					if (config.knownStates.contains(state))
					{
						proof = true;
						modelField.addState(state, iteration);
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
				//return Field with all States
				for (State state : states.keySet()){
					//add if it is later created than this one
					if (states.get(state) > config.iteration) {
						proof = true;
						modelField.addState(state, iteration);
					}
				}
			} else {
				//add if state is also in known states
				for (State state : states.keySet()) {
					if (config.knownStates.contains(state) && states.get(state) > config.iteration)
					{
						proof = true;
						modelField.addState(state, iteration);
					}				
				}
			}
			if (!proof) {
				return null;
			}
		}
		return modelField;
	}

	public cleaningrobots.Field exportModel(ImportExportConfiguration config) {
		cleaningrobots.Field modelField = null;
		
		modelField = CleaningrobotsFactory.eINSTANCE.createField();
		
		modelField.setPos(pos.exportModel());
		if (config.iteration == -1) 
		{
			if (config.knownStates.isEmpty())
			{
				//return Field with all States
				for (State state : states.keySet()){
					modelField.getStates().add(state.exportModel());
				}
			} else {
				boolean proof = false;
				//add if state is also in known states
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
				//return Field with all States
				for (State state : states.keySet()){
					//add if it is later created than this one
					if (states.get(state) > config.iteration) {
						proof = true;
						modelField.getStates().add(state.exportModel());
					}
				}
			} else {
				//add if state is also in known states
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
