package de.tud.swt.cleaningrobots.exceptions;

public class AgentHasNoSuchComponent extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1798671255326056599L;

	public AgentHasNoSuchComponent (String name)
	{
		super("Agent has no Component with the name: " + name + "!");
	}

}
