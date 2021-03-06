package de.tud.swt.cleaningrobots.goals.nonoptional;

import de.tud.swt.cleaningrobots.AgentRole;
import de.tud.swt.cleaningrobots.behaviours.HooveAroundAtDestinationBehaviour;
import de.tud.swt.cleaningrobots.behaviours.HooveBehaviour;
import de.tud.swt.cleaningrobots.behaviours.MoveBehaviour;
import de.tud.swt.cleaningrobots.goals.NonOptionalGoal;
import de.tud.swt.cleaningrobots.model.State;

/**
 * Non optional goal to hoove the world without any merge functions. 
 * 
 * @author Christopher Werner
 *
 */
public class HooveLoadGoal extends NonOptionalGoal {

	private HooveBehaviour d;
	
	private State STATE_HOOVE;	
	private State WORLDSTATE_DISCOVERED;
	
	public HooveLoadGoal(AgentRole role, boolean relative) {
		super(role);
		
		this.STATE_HOOVE = getAgentCore().getConfiguration().createState("Hoove");
		this.WORLDSTATE_DISCOVERED = getAgentCore().getConfiguration().createState("Discovered");
		
		HooveAroundAtDestinationBehaviour s = new HooveAroundAtDestinationBehaviour(role);
		System.out.println("Correct SeeAround: " + s.isHardwarecorrect());
		if (s.isHardwarecorrect()) {
			behaviours.add(s);
		} else {
			correct = false;
		}
		
		d = new HooveBehaviour(role, relative);
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
		boolean discovered = this.getAgentCore().getWorld().containsWorldState(WORLDSTATE_DISCOVERED);
		if (!discovered || this.getAgentCore().getWorld().getNextPassablePositionWithoutState(STATE_HOOVE) != null)
			return true;
		return false;
	}

	@Override
	public boolean postCondition() {
		//if hoove behavior is finish and has no new information
		if (!d.isFinishHoove())
			return false;
		else {
			boolean proof = true;
			for (AgentRole rr : getAgentCore().getRoles()) {
				if (rr.hasNewInformation())
					proof = false;
			}
			return proof;
		}
		//return d.isFinishHoove();
		/*if (d.noMoreDiscovering)
			return true;
		if (this.getRobotCore().getWorld().getNextUnknownFieldPosition() == null 
				&& this.getRobotCore().getPosition().equals(this.getRobotCore().loadStationPosition))
			return true;
		return false;*/
	}
}
