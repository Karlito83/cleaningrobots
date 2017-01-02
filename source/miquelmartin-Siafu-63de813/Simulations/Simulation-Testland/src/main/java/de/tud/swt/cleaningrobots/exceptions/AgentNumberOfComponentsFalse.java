package de.tud.swt.cleaningrobots.exceptions;

public class AgentNumberOfComponentsFalse extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1779681387579930733L;

	public AgentNumberOfComponentsFalse ()
	{
		super("The Agent has not so much Components of one Type.");
	}

}
