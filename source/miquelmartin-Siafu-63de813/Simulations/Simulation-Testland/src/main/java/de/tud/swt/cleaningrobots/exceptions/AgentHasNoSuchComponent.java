package de.tud.swt.cleaningrobots.exceptions;

public class AgentHasNoSuchComponent extends RuntimeException {
	
	public AgentHasNoSuchComponent (String name)
	{
		super("Agent has no Component with the name: " + name + "!");
	}

}
