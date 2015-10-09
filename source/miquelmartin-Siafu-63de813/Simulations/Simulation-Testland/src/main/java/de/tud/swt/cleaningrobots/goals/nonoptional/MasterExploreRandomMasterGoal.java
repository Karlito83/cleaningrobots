package de.tud.swt.cleaningrobots.goals.nonoptional;

import de.tud.swt.cleaningrobots.MasterRole;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.behaviours.MasterCalculateRandomBehaviour;
import de.tud.swt.cleaningrobots.goals.NonOptionalGoal;

public class MasterExploreRandomMasterGoal extends NonOptionalGoal {

	private MasterCalculateRandomBehaviour mceb;

	public MasterExploreRandomMasterGoal(RobotCore robot, MasterRole mr) {
		super(robot);
		
		mceb = new MasterCalculateRandomBehaviour(robot, mr);
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
		if (this.getRobotCore().getWorld().getNextUnknownFieldPosition() != null)
			return true;
		return false;
	}

	@Override
	public boolean postCondition() {
		return mceb.isFinishDisscovering();
	}
}
