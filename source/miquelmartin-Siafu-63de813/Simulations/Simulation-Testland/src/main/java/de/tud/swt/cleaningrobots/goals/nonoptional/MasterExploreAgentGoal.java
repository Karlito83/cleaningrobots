package de.tud.swt.cleaningrobots.goals.nonoptional;

import de.tud.swt.cleaningrobots.AgentRole;
import de.tud.swt.cleaningrobots.behaviours.MasterMoveBehaviour;
import de.tud.swt.cleaningrobots.behaviours.MasterDiscoverAroundBehaviour;
import de.tud.swt.cleaningrobots.behaviours.WlanOnBehaviour;
import de.tud.swt.cleaningrobots.goals.NonOptionalGoal;
import de.tud.swt.cleaningrobots.model.State;

/**
 * Non optional goal for a follower which get information from a master.
 * Drive to a place discover it and give the information back to the master. 
 * 
 * @author Christopher Werner
 *
 */
public class MasterExploreAgentGoal extends NonOptionalGoal {

	private State WORLDSTATE_DISCOVERED;
	
	public MasterExploreAgentGoal(AgentRole role) {
		super(role);
		
		this.WORLDSTATE_DISCOVERED = getAgentCore().getConfiguration().createState("Discovered");
		
		WlanOnBehaviour w = new WlanOnBehaviour(role);
		System.out.println("Correct SeeAround: " + w.isHardwarecorrect());
		if (w.isHardwarecorrect()) {
			behaviours.add(w);
		} else {
			correct = false;
		}
		
		MasterMoveBehaviour m = new MasterMoveBehaviour(role);
		System.out.println("Correct Move: " + m.isHardwarecorrect());
		if (m.isHardwarecorrect()) {
			behaviours.add(m);
		} else {
			correct = false;
		}
		
		MasterDiscoverAroundBehaviour s = new MasterDiscoverAroundBehaviour(role);
		System.out.println("Correct Discover: " + s.isHardwarecorrect());
		if (s.isHardwarecorrect()) {
			behaviours.add(s);
		} else {
			correct = false;
		}
	}

	@Override
	public boolean preCondition() {
		if (getAgentCore().getWorld().containsWorldState(WORLDSTATE_DISCOVERED))
			return false;
		return true;
	}

	@Override
	public boolean postCondition() {
		if (getAgentCore().getPosition().equals(getAgentCore().getDestinationContainer().getLoadStationPosition()) && 
				getAgentCore().getWorld().containsWorldState(WORLDSTATE_DISCOVERED))
			return true;
		return false;
	}

}
