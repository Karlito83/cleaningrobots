package de.tud.swt.testland;


public interface ICreateAgents {

	/**
	 * Create a loadstation without computational hardware agent.
	 * 
	 * @param world
	 *            the world to create it in
	 * @return the new agent
	 */
	public IRobotAgent createLoadStation();
	
	/**
	 * Create a loadstation agent.
	 * 
	 * @param world
	 *            the world to create it in
	 * @return the new agent
	 */
	public IRobotAgent createLoadStationAgent();
	
	/**
	 * Create a explore agent.
	 * 
	 * @param world
	 *            the world to create it in
	 * @return the new agent
	 */
	public IRobotAgent createExploreAgent();
	
	/**
	 * Create a wipe agent.
	 * 
	 * @param world
	 *            the world to create it in
	 * @return the new agent
	 */
	public IRobotAgent createWipeAgent();
	
	/**
	 * Create a hoove agent.
	 * 
	 * @param world
	 *            the world to create it in
	 * @return the new agent
	 */
	public IRobotAgent createHooveAgent();
	
}
