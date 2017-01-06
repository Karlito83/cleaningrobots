package de.tud.swt.cleaningrobots;

import java.util.LinkedList;
import java.util.List;

import cleaningrobots.CleaningrobotsFactory;
import de.tud.evaluation.ExchangeMeasurement;
import de.tud.swt.cleaningrobots.model.FollowerRoleModel;
import de.tud.swt.cleaningrobots.model.MasterRoleModel;
import de.tud.swt.cleaningrobots.model.Position;
import de.tud.swt.cleaningrobots.model.RoleModel;
import de.tud.swt.cleaningrobots.model.State;

/**
 * Robot knowledge which has information about other robots. 
 * 
 * @author Christopher Werner
 *
 */
public class AgentKnowledge {
	
	private String name;
	private int lastArrange;
	private Position lastDestination;
	private List<String> components;
	private List<RoleModel> roles;
	private List<State> knownStates;	
	
	public AgentKnowledge (String name) {
		this.name = name;
		components = new LinkedList<String>();
		roles = new LinkedList<RoleModel>();
		knownStates = new LinkedList<State>();
	}
	
	/**
	 * Get the name of the robot.
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Get the information about the last arrange.
	 * @return
	 */
	public int getLastArrange() {
		return lastArrange;
	}
	
	public void setLastArrange(int lastArrange) {
		this.lastArrange = lastArrange;
	}
	
	/**
	 * Get the last destination of the other robot.
	 * @return
	 */
	public Position getLastDestination() {
		return lastDestination;
	}
	
	public void setLastDestination(Position lastDestination) {
		this.lastDestination = lastDestination;
	}

	/**
	 * Get the names of the hardware components the other robot has.
	 * @return
	 */
	public List<String> getComponents() {
		return components;
	}

	public void setComponents(List<String> components) {
		this.components = components;
	}

	/**
	 * Get the supported states of the other robot.
	 * @return
	 */
	public List<State> getKnownStates() {
		return knownStates;
	}

	public void setKnownStates(List<State> knownStates) {
		this.knownStates = knownStates;
	}

	/**
	 * Get the roles from the other robot.
	 * @return
	 */
	public List<RoleModel> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleModel> roles) {
		this.roles = roles;
	}
	
	/**
	 * Get the evaluation information for data measurement.
	 * @return
	 */
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
		for (RoleModel r : roles) {
			if (r instanceof MasterRoleModel) {
				MasterRoleModel m = (MasterRoleModel)r;
				em.addKnowledgeStringNumber( m.getFollowers().size());
				for (String s : m.getFollowers()) {
					em.addKnowledgeStringByteNumber(s.getBytes().length);
				}
			} else if (r instanceof FollowerRoleModel) {
				FollowerRoleModel f = (FollowerRoleModel)r;
				em.addKnowledgeStringByteNumber(f.getMaster().getBytes().length);
				em.addKnowledgeStringNumber(1);
			}
			em.addKnowledgeStringByteNumber(r.getRoleName().getBytes().length);
			em.addKnowledgeStringNumber(1);			
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
			//roles
			for (RoleModel r : roles) {
				if (r instanceof MasterRoleModel) {
					cleaningrobots.MasterRole m = CleaningrobotsFactory.eINSTANCE.createMasterRole();
					m.getFollowerNames().addAll(((MasterRoleModel)r).getFollowers());
					robotknowledge.getRoles().add(m);
				} else if (r instanceof FollowerRoleModel) {
					cleaningrobots.FollowerRole f = CleaningrobotsFactory.eINSTANCE.createFollowerRole();
					f.setMasterName(((FollowerRoleModel)r).getMaster());
					robotknowledge.getRoles().add(f);
				}
			}					
			result = robotknowledge;
		} catch (Exception e) {
		}

		return result;
	}
}
