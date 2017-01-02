package de.tud.swt.cleaningrobots.goals.nonoptional;

import de.tud.swt.cleaningrobots.RobotRole;
import de.tud.swt.cleaningrobots.behaviours.MasterCalculateWipeBehaviour;
import de.tud.swt.cleaningrobots.goals.NonOptionalGoal;
import de.tud.swt.cleaningrobots.model.State;
import de.tud.swt.cleaningrobots.roles.MasterRole;

/**
 * Non optional goal for a master to coordinate the followers to wipe the world. 
 * 
 * @author Christopher Werner
 *
 */
public class MasterWipeMasterGoal extends NonOptionalGoal {

	private MasterCalculateWipeBehaviour mceb;
	private State WORLDSTATE_WIPED;

	public MasterWipeMasterGoal(RobotRole role, boolean relative) {
		super(role);
		
		this.WORLDSTATE_WIPED = getRobotCore().getConfiguration().createState("Wiped");
		
		mceb = new MasterCalculateWipeBehaviour(getRobotCore(), (MasterRole) role, relative);
		System.out.println("Correct SeeAround: " +mceb.isHardwarecorrect());
		if (mceb.isHardwarecorrect()) {
			behaviours.add(mceb);
		} else {
			correct = false;
		}
	}

	@Override
	public boolean preCondition() {
		if (getRobotCore().getWorld().containsWorldState(WORLDSTATE_WIPED))
			return false;
		return true;
	}

	@Override
	public boolean postCondition() {
		return mceb.isFinishWiping();
	}
}
