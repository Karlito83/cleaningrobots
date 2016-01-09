package de.tud.swt.cleaningrobots.goals.nonoptional;

import de.tud.swt.cleaningrobots.MasterRole;
import de.tud.swt.cleaningrobots.RobotCore;
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

	public MasterHooveMasterGoal(RobotCore robot, MasterRole mr, boolean relative) {
		super(robot);
		
		this.WORLDSTATE_HOOVED = robot.configuration.createState("Hooved");
		
		mceb = new MasterCalculateHooveBehaviour(robot, mr, relative);
		System.out.println("Correct SeeAround: " +mceb.isHardwarecorrect());
		if (mceb.isHardwarecorrect()) {
			behaviours.add(mceb);
		} else {
			correct = false;
		}
	}

	@Override
	public boolean preCondition() {
		if (getRobotCore().getWorld().containsWorldState(WORLDSTATE_HOOVED))
			return false;
		return true;
	}

	@Override
	public boolean postCondition() {
		return mceb.isFinishHooving();
	}
}
