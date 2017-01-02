package de.tud.swt.cleaningrobots.roles;

import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.RobotRole;
import de.tud.swt.cleaningrobots.goals.optional.LoadIfRobotWantGoal;

/**
 * Add the goal to load robots.
 */
public class LoadstationRole extends RobotRole {

	public LoadstationRole(RobotCore robotCore) {
		super(robotCore);
	}

	@Override
	public boolean createGoals() {
		
		LoadIfRobotWantGoal lirwg = new LoadIfRobotWantGoal(this);		
		if (lirwg.isHardwareCorrect())
		{
			this.addGoals(lirwg);
		}
		else
		{
			throw new RuntimeException("Hardeware is not correct for this goal!");
		}
		return lirwg.isHardwareCorrect();
	}

}
