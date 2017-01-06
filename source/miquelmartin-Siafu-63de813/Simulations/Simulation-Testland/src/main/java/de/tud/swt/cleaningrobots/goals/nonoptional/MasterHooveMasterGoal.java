package de.tud.swt.cleaningrobots.goals.nonoptional;

import de.tud.swt.cleaningrobots.AgentRole;
import de.tud.swt.cleaningrobots.behaviours.MasterCalculateHooveBehaviour;
import de.tud.swt.cleaningrobots.goals.NonOptionalGoal;
import de.tud.swt.cleaningrobots.model.State;

/**
 * Non optional goal for a master to coordinate the followers to hoove the world. 
 * 
 * @author Christopher Werner
 *
 */
public class MasterHooveMasterGoal extends NonOptionalGoal {

	private MasterCalculateHooveBehaviour mceb;
	private State WORLDSTATE_HOOVED;

	public MasterHooveMasterGoal(AgentRole role, boolean relative) {
		super(role);
		
		this.WORLDSTATE_HOOVED = getAgentCore().getConfiguration().createState("Hooved");
		
		mceb = new MasterCalculateHooveBehaviour(role, relative);
		System.out.println("Correct SeeAround: " +mceb.isHardwarecorrect());
		if (mceb.isHardwarecorrect()) {
			behaviours.add(mceb);
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
		return mceb.isFinishHooving();
	}
}
