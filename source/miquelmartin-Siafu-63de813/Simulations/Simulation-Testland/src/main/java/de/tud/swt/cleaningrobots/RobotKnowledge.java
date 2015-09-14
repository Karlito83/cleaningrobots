package de.tud.swt.cleaningrobots;

import java.util.LinkedList;
import java.util.List;

import cleaningrobots.CleaningrobotsFactory;
import de.tud.swt.cleaningrobots.model.Position;
import de.tud.swt.cleaningrobots.model.State;

public class RobotKnowledge {
	
	private String name;
	private int lastArrange;
	private Position lastDestination;
	private List<String> components;
	private List<cleaningrobots.Role> roles;
	private List<State> knownStates;	
	
	public RobotKnowledge (String name) {
		this.name = name;
		components = new LinkedList<String>();
		roles = new LinkedList<cleaningrobots.Role>();
		knownStates = new LinkedList<State>();
	}
	
	public String getName() {
		return name;
	}
	
	public int getLastArrange() {
		return lastArrange;
	}
	
	public void setLastArrange(int lastArrange) {
		this.lastArrange = lastArrange;
	}
	
	public Position getLastDestination() {
		return lastDestination;
	}
	
	public void setLastDestination(Position lastDestination) {
		this.lastDestination = lastDestination;
	}

	public List<String> getComponents() {
		return components;
	}

	public void setComponents(List<String> components) {
		this.components = components;
	}

	public List<State> getKnownStates() {
		return knownStates;
	}

	public void setKnownStates(List<State> knownStates) {
		this.knownStates = knownStates;
	}

	public List<cleaningrobots.Role> getRoles() {
		return roles;
	}

	public void setRoles(List<cleaningrobots.Role> roles) {
		this.roles = roles;
	}

	public cleaningrobots.RobotKnowledge exportModel() {
		// TODO: Consider caching
		cleaningrobots.RobotKnowledge result = null;

		try {
			cleaningrobots.RobotKnowledge robotknowledge = CleaningrobotsFactory.eINSTANCE.createRobotKnowledge();
			robotknowledge.setName(getName());
			if (lastDestination != null)
				robotknowledge.setDestination(lastDestination.exportModel());
			robotknowledge.setLastArrange(lastArrange);
			robotknowledge.getComponents().addAll(components);
			for (State state : getKnownStates()) {
				robotknowledge.getKnowStates().add(state.exportModel());
			}
			//roles noch hinzufügen
			robotknowledge.getRoles().addAll(roles);
					
			result = robotknowledge;
		} catch (Exception e) {
			//Logger hier nicht drin
		}

		return result;
	}
}
