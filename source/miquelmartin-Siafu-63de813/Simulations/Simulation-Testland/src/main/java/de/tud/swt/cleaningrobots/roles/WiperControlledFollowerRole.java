package de.tud.swt.cleaningrobots.roles;

import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.goals.nonoptional.MasterWipeRobotGoal;

/**
 * Add the goals where is a complete communication with the master.
 */
public class WiperControlledFollowerRole extends FollowerRole {

	public WiperControlledFollowerRole(RobotCore robotCore, MasterRole master) {
		super(robotCore, master);
	}

	@Override
	public boolean createGoals() {
		
		MasterWipeRobotGoal mrg = new MasterWipeRobotGoal(core);		
		if (mrg.isHardwareCorrect()) 
		{
			this.addGoals(mrg);
		}
		else
		{
			throw new RuntimeException("Hardeware is not correct for this goal!");
		}
		return mrg.isHardwareCorrect();
	}

}
