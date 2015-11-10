package de.tud.swt.cleaningrobots;

import java.util.LinkedList;
import java.util.List;

import cleaningrobots.CleaningrobotsFactory;
import de.tud.evaluation.ExchangeMeasurement;
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
	
	public ExchangeMeasurement getMeasurement () {
		ExchangeMeasurement em = new ExchangeMeasurement("", "", 0);		
		//name
		em.addKnowledgeStringByteNumber(name.getBytes().length);
		em.addKnowledgeStringNumber(1);
		//lastArrang
		em.addKnowledgeIntegerNumber(1);
		//lastdestination
		if (lastDestination != null)
			em.addKnowledgeIntegerNumber(2);
		//components
		em.addKnowledgeStringNumber(components.size());
		for (String s : components) {
			em.addKnowledgeStringByteNumber(s.getBytes().length);
		}
		//roles
		for (cleaningrobots.Role r : roles) {
			if (r instanceof cleaningrobots.MasterRole) {
				cleaningrobots.MasterRole m = (cleaningrobots.MasterRole)r;
				em.addKnowledgeStringNumber( m.getFollowerNames().size());
				for (String s : m.getFollowerNames()) {
					em.addKnowledgeStringByteNumber(s.getBytes().length);
				}
			} else {
				cleaningrobots.FollowerRole f = (cleaningrobots.FollowerRole)r;
				em.addKnowledgeStringByteNumber(f.getMasterName().getBytes().length);
				em.addKnowledgeStringNumber(1);
			}
		}
		//knownstates
		em.addKnowledgeStringNumber(knownStates.size());
		for (State s : knownStates) {
			em.addKnowledgeStringByteNumber(s.getName().getBytes().length);
		}	
		return em;
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
		}

		return result;
	}
}
