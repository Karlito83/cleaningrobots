package de.tud.swt.cleaningrobots.goals.nonoptional;

import de.tud.swt.cleaningrobots.AgentRole;
import de.tud.swt.cleaningrobots.behaviours.MasterHooveAroundBehaviour;
import de.tud.swt.cleaningrobots.behaviours.MasterMoveBehaviour;
import de.tud.swt.cleaningrobots.behaviours.WlanOnBehaviour;
import de.tud.swt.cleaningrobots.goals.NonOptionalGoal;
import de.tud.swt.cleaningrobots.model.State;

/**
 * Non optional goal for a follower which get information from a master.
 * Drive to a place hoove it and give the information back to the master. 
 * 
 * @author Christopher Werner
 *
 */
public class MasterHooveAgentGoal extends NonOptionalGoal {

	private State WORLDSTATE_HOOVED;
	
	public MasterHooveAgentGoal(AgentRole role) {
		super(role);
		
		this.WORLDSTATE_HOOVED = getAgentCore().getConfiguration().createState("Hooved");
		
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
		
		MasterHooveAroundBehaviour s = new MasterHooveAroundBehaviour(role);
		System.out.println("Correct Discover: " + s.isHardwarecorrect());
		if (s.isHardwarecorrect()) {
			behaviours.add(s);
		} else {
			correct = false;
		}
	}

	@Override
	public boolean preCondition() {
		if (getAgentCore().getWorld().containsWorldState(WORLDSTATE_HOOVED))
			return false;
		return true;
	}

	@Override
	public boolean postCondition() {
		if (getAgentCore().getPosition().equals(getAgentCore().getDestinationContainer().getLoadStationPosition()) && 
				getAgentCore().getWorld().containsWorldState(WORLDSTATE_HOOVED))
			return true;
		return false;
	}
}
