package de.tud.swt.cleaningrobots.roles;

import de.tud.swt.cleaningrobots.AgentCore;
import de.tud.swt.cleaningrobots.AgentRole;
import de.tud.swt.cleaningrobots.goals.optional.WlanOnGoal;

public class CommunicationInterfaceRole extends AgentRole {

	public CommunicationInterfaceRole(AgentCore robotCore) {
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
