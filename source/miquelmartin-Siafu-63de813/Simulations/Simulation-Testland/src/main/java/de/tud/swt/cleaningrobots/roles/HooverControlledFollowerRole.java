package de.tud.swt.cleaningrobots.roles;

import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.goals.nonoptional.MasterHooveRobotGoal;

/**
 * Add the goals where is a complete communication with the master.
 */
public class HooverControlledFollowerRole extends FollowerRole {

	public HooverControlledFollowerRole(RobotCore robotCore, MasterRole master) {
		super(robotCore, master);
	}

	@Override
	public boolean createGoals() {
		
		MasterHooveRobotGoal mrg = new MasterHooveRobotGoal(this);			
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
