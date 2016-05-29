package de.tud.swt.cleaningrobots.merge;

import de.tud.evaluation.ExchangeMeasurement;
import de.tud.swt.cleaningrobots.Configuration;

public abstract class Merge {

	protected Configuration configuration;
	protected ExchangeMeasurement em;
	
	public Merge (Configuration configuration) {
		this.configuration = configuration;
	}
	
	protected void preRun (String name1, String name2)
	{
		this.em = new ExchangeMeasurement(name1, name2, this.configuration.wc.iteration);
	}
	
	protected void postRun ()
	{
		this.configuration.wc.exchange.add(em);
	}
}
