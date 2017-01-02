package de.tud.swt.cleaningrobots.roles;

import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.RobotRole;
import de.tud.swt.cleaningrobots.goals.optional.WlanOnGoal;

public class CommunicationInterfaceRole extends RobotRole {

	public CommunicationInterfaceRole(RobotCore robotCore) {
		super(robotCore);
	}

	@Override
	public boolean createGoals() {
		
		WlanOnGoal wog = new WlanOnGoal(this);
		
		if (wog.isHardwareCorrect())
		{
			this.addGoals(wog);
		}
		else
		{
			throw new RuntimeException("Hardeware is not correct for this goal!");
		}
		return wog.isHardwareCorrect();
	}

}
