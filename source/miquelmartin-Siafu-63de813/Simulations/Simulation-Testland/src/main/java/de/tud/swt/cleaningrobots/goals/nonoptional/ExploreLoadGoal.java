package de.tud.swt.cleaningrobots.goals.nonoptional;

import de.tud.swt.cleaningrobots.AgentRole;
import de.tud.swt.cleaningrobots.behaviours.DiscoverBehaviour;
import de.tud.swt.cleaningrobots.behaviours.MoveBehaviour;
import de.tud.swt.cleaningrobots.behaviours.DiscoverAroundAtDestinationBehaviour;
import de.tud.swt.cleaningrobots.goals.NonOptionalGoal;

/**
 * Non optional goal to discover the world without any merge functions. 
 * Discovers as long as no more unknown field exists and then drive back to Loadstation
 * 
 * @author Christopher Werner
 *
 */
public class ExploreLoadGoal extends NonOptionalGoal {

	private DiscoverBehaviour d;
	
	public ExploreLoadGoal(AgentRole role, boolean relative) {
		super(role);
		
		DiscoverAroundAtDestinationBehaviour s = new DiscoverAroundAtDestinationBehaviour(role);
		System.out.println("Correct SeeAround: " + s.isHardwarecorrect());
		if (s.isHardwarecorrect()) {
			behaviours.add(s);
		} else {
			correct = false;
		}
		
		d = new DiscoverBehaviour(role, relative);
		System.out.println("Correct Discover: " + d.isHardwarecorrect());
		if (d.isHardwarecorrect()) {
			behaviours.add(d);
		} else {
			correct = false;
		}
		
		MoveBehaviour m = new MoveBehaviour(role);
		System.out.println("Correct Move: " + m.isHardwarecorrect());
		if (m.isHardwarecorrect()) {
			behaviours.add(m);
		} else {
			correct = false;
		}
	}

	@Override
	public boolean preCondition() {		
		if (this.getAgentCore().getWorld().getNextUnknownFieldPosition() != null)
			return true;
		return false;
	}

	@Override
	public boolean postCondition() {
		if (!d.isFinishDiscovering())
			return false;
		else {
			boolean proof = true;
			for (AgentRole rr : getAgentCore().getRoles()) {
				if (rr.hasNewInformation())
					proof = false;
			}
			return proof;
		}
		//return d.isFinishDiscovering();
		/*if (d.noMoreDiscovering)
			return true;
		if (this.getRobotCore().getWorld().getNextUnknownFieldPosition() == null 
				&& this.getRobotCore().getPosition().equals(this.getRobotCore().loadStationPosition))
			return true;
		return false;*/
	}
}
