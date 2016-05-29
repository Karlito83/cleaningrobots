package de.tud.swt.cleaningrobots.merge;

import de.tud.swt.cleaningrobots.Configuration;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.util.ImportExportConfiguration;

public abstract class AgentMerge extends Merge {

	public AgentMerge(Configuration configuration) {
		super(configuration);
	}
	
	public void run (RobotCore from, RobotCore to, ImportExportConfiguration config)
	{
		this.preRun(from.getName(), to.getName());
		this.action(from, to, config);
		this.postRun();
	}

	protected abstract void action (RobotCore from, RobotCore to, ImportExportConfiguration config);
}
