package de.tud.swt.cleaningrobots.goals.nonoptional;

import de.tud.swt.cleaningrobots.MasterRole;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.behaviours.MasterCalculateHooveBehaviour;
import de.tud.swt.cleaningrobots.goals.NonOptionalGoal;
import de.tud.swt.cleaningrobots.model.State;

public class MasterHooveMasterGoal extends NonOptionalGoal {

	private MasterCalculateHooveBehaviour mceb;
	private final State WORLDSTATE_HOOVED = State.createState("Hooved");

	public MasterHooveMasterGoal(RobotCore robot, MasterRole mr, boolean relative) {
		super(robot);
		
		mceb = new MasterCalculateHooveBehaviour(robot, mr, relative);
		System.out.println("Correct SeeAround: " +mceb.isHardwarecorrect());
		if (mceb.isHardwarecorrect()) {
			//robot.addBehaviour(s);
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