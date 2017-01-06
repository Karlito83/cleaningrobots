package de.tud.swt.cleaningrobots.roles;

import de.tud.swt.cleaningrobots.AgentCore;
import de.tud.swt.cleaningrobots.AgentRole;
import de.tud.swt.cleaningrobots.goals.optional.XmlDumpGoal;

/**
 * Add the goal for a XML output of the current map.
 */
public class LoggingXmlRole extends AgentRole {

	public LoggingXmlRole(AgentCore robotCore) {
		super(robotCore);
	}

	@Override
	public boolean createGoals() {
		
		this.addGoals(new XmlDumpGoal(this));
		return true;
	}

}
