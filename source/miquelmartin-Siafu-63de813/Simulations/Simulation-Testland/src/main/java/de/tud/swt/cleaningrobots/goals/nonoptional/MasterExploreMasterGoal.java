package de.tud.swt.cleaningrobots.goals.nonoptional;

import de.tud.swt.cleaningrobots.MasterRole;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.behaviours.MasterCalculateExploreBehaviour;
import de.tud.swt.cleaningrobots.goals.NonOptionalGoal;
import de.tud.swt.cleaningrobots.model.State;

/**
 * Non optional goal for a master to coordinate the followers to discover the world. 
 * 
 * @author Christopher Werner
 *
 */
public class MasterExploreMasterGoal extends NonOptionalGoal {
	
	private MasterCalculateExploreBehaviour mceb;
	private State WORLDSTATE_DISCOVERED;

	public MasterExploreMasterGoal(RobotCore robot, MasterRole mr, boolean relative) {
		super(robot);
		
		this.WORLDSTATE_DISCOVERED = robot.configuration.createState("Discovered");
		
		mceb = new MasterCalculateExploreBehaviour(robot, mr, relative);
		System.out.println("Correct SeeAround: " +mceb.isHardwarecorrect());
		if (mceb.isHardwarecorrect()) {
			behaviours.add(mceb);
		} else {
			correct = false;
		}
	}

	@Override
	public boolean preCondition() {
		if (getRobotCore().getWorld().containsWorldState(WORLDSTATE_DISCOVERED))
			return false;
		return true;
	}

	@Override
	public boolean postCondition() {
		return mceb.isFinishDisscovering();
	}
}
