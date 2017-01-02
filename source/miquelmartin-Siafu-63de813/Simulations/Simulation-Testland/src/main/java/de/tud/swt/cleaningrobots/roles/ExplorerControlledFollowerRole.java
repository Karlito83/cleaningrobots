package de.tud.swt.cleaningrobots.roles;

import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.goals.nonoptional.MasterExploreRobotGoal;

/**
 * Add the goals where is a complete communication with the master.
 */
public class ExplorerControlledFollowerRole extends FollowerRole {

	public ExplorerControlledFollowerRole(RobotCore robotCore, MasterRole master) {
		super(robotCore, master);
	}

	@Override
	public boolean createGoals() {
		
		MasterExploreRobotGoal merg = new MasterExploreRobotGoal(this);		
		if (merg.isHardwareCorrect()) 
		{
			this.addGoals(merg);
		}
		else
		{
			throw new RuntimeException("Hardeware is not correct for this goal!");
		}
		return merg.isHardwareCorrect();
	}

}
