package de.tud.swt.cleaningrobots.goals.nonoptional;

import de.tud.swt.cleaningrobots.MasterRole;
import de.tud.swt.cleaningrobots.RobotCore;
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

	public MasterWipeMasterGoal(RobotCore robot, MasterRole mr, boolean relative) {
		super(robot);
		
		this.WORLDSTATE_WIPED = robot.configuration.createState("Wiped");
		
		mceb = new MasterCalculateWipeBehaviour(robot, mr, relative);
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
