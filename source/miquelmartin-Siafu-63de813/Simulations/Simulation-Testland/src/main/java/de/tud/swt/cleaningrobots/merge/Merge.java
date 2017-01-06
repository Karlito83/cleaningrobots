package de.tud.swt.cleaningrobots.merge;

import de.tud.evaluation.ExchangeMeasurement;
import de.tud.swt.cleaningrobots.Configuration;
import de.tud.swt.cleaningrobots.AgentCore;

/**
 * Abstract Merge class which must be implemented for the exchange of data.
 * 
 * @author Christopher Werner
 *
 */
public abstract class Merge {

	protected Configuration configuration;
	protected ExchangeMeasurement em;
	
	public Merge (Configuration configuration) {
		this.configuration = configuration;
	}
	
	/**
	 * Create the new exchange Measurement object.
	 * @param name1
	 * @param name2
	 */
	protected void preRun (String name1, String name2)
	{
		this.em = new ExchangeMeasurement(name1, name2, this.configuration.getWc().iteration);
	}
	
	/**
	 * Save the new measurement object to the configuration.
	 */
	protected void postRun ()
	{
		this.configuration.getWc().exchange.add(em);
	}
	
	/**
	 * Called for every exchange of information.
	 * @param from (AgentCore where the data comes from)
	 * @param to (AgentCore where the data goes to)
	 * @param object (Data or information about what should be send)
	 */
	public void run (AgentCore from, AgentCore to, Object object)
	{
		this.preRun(from.getName(), to.getName());
		this.action(from, to, object);
		this.postRun();
	}

	/**
	 * Must be implement for a new Merge algorithm.
	 * @param from (AgentCore where the data comes from)
	 * @param to (AgentCore where the data goes to)
	 * @param object (Data or information about what should be send)
	 */
	protected abstract void action (AgentCore from, AgentCore to, Object object);
}
