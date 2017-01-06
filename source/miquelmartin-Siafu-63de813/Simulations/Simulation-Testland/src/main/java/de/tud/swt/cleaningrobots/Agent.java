package de.tud.swt.cleaningrobots;

/**
 * Abstract robot class with the information of new knowledge.
 * 
 * @author Christopher Werner
 *
 */
public abstract class Agent {
	
	private boolean newInformation;	

	/**
	 * Get new information info.
	 * @return
	 */
	public boolean hasNewInformation () {
		return newInformation;
	}
	
	/**
	 * Set new information value.
	 * @param newInformation
	 */
	public void setNewInformation (boolean newInformation) {
		this.newInformation = newInformation;
	}
	
	public abstract boolean addRole (AgentRole role);	
	public abstract boolean hasRole(AgentRole role);
	public abstract boolean removeRole (AgentRole role);
	
	@Override
	public String toString() {
		return " NewInfo: " + newInformation;
	}	
}
