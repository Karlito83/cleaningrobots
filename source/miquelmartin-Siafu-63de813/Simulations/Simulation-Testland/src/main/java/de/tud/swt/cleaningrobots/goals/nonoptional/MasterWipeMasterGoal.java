package de.tud.swt.cleaningrobots.goals.nonoptional;

import de.tud.swt.cleaningrobots.AgentRole;
import de.tud.swt.cleaningrobots.behaviours.MasterCalculateWipeBehaviour;
import de.tud.swt.cleaningrobots.goals.NonOptionalGoal;
import de.tud.swt.cleaningrobots.model.State;

/**
 * Non optional goal for a master to coordinate the followers to wipe the world. 
 * 
 * @author Christopher Werner
 *
 */
public class MasterWipeMasterGoal extends NonOptionalGoal {

	private MasterCalculateWipeBehaviour mceb;
	private State WORLDSTATE_WIPED;

	public MasterWipeMasterGoal(AgentRole role, boolean relative) {
		super(role);
		
		this.WORLDSTATE_WIPED = getAgentCore().getConfiguration().createState("Wiped");
		
		mceb = new MasterCalculateWipeBehaviour(role, relative);
		System.out.println("Correct SeeAround: " +mceb.isHardwarecorrect());
		if (mceb.isHardwarecorrect()) {
			behaviours.add(mceb);
		} else {
			correct = false;
		}
	}

	@Override
	public boolean preCondition() {
		if (getAgentCore().getWorld().containsWorldState(WORLDSTATE_WIPED))
			return false;
		return true;
	}

	@Override
	public boolean postCondition() {
		return mceb.isFinishWiping();
	}
}
