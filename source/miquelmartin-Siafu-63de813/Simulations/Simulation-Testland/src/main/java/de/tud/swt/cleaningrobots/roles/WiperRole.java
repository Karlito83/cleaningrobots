package de.tud.swt.cleaningrobots.roles;

import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.RobotRole;
import de.tud.swt.cleaningrobots.goals.nonoptional.WithoutMasterWipeGoal;

/**
 * Add the goals where no master is needed.
 */
public class WiperRole extends RobotRole {

	public WiperRole(RobotCore robotCore) {
		super(robotCore);
	}

	@Override
	public boolean createGoals() {
		
		WithoutMasterWipeGoal wmg = new WithoutMasterWipeGoal(this);			
		if (wmg.isHardwareCorrect())
		{
			this.addGoals(wmg);
		}
		else
		{
			throw new RuntimeException("Hardeware is not correct for this goal!");
		}
		return wmg.isHardwareCorrect();
	}

}
