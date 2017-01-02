package de.tud.swt.cleaningrobots.merge;

import de.tud.evaluation.ExchangeMeasurement;
import de.tud.swt.cleaningrobots.Configuration;
import de.tud.swt.cleaningrobots.RobotCore;

public abstract class Merge {

	protected Configuration configuration;
	protected ExchangeMeasurement em;
	
	public Merge (Configuration configuration) {
		this.configuration = configuration;
	}
	
	protected void preRun (String name1, String name2)
	{
		this.em = new ExchangeMeasurement(name1, name2, this.configuration.getWc().iteration);
	}
	
	protected void postRun ()
	{
		this.configuration.getWc().exchange.add(em);
	}
	
	public void run (RobotCore from, RobotCore to, Object object)
	{
		this.preRun(from.getName(), to.getName());
		this.action(from, to, object);
		this.postRun();
	}

	protected abstract void action (RobotCore from, RobotCore to, Object object);
}
