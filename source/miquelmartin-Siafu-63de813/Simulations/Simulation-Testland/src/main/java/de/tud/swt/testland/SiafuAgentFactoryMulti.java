package de.tud.swt.testland;

import de.nec.nle.siafu.model.MultiWorld;
import de.tud.swt.cleaningrobots.Configuration;
import de.tud.swt.cleaningrobots.hardware.Hoover;
import de.tud.swt.cleaningrobots.hardware.LoadStation;
import de.tud.swt.cleaningrobots.hardware.LookAroundSensor;
import de.tud.swt.cleaningrobots.hardware.Motor;
import de.tud.swt.cleaningrobots.hardware.Rechner;
import de.tud.swt.cleaningrobots.hardware.Wiper;
import de.tud.swt.cleaningrobots.hardware.Wlan;
import de.tud.swt.cleaningrobots.model.Position;

/**
 * Without user interface in Siafu.
 * Create the different robot type and create the different test case only abstract class.
 * 
 * @author Christopher Werner
 *
 */
public class SiafuAgentFactoryMulti implements ISimulatorAgentFactory {
	
	private int counter;
	protected Configuration configuration;
	private Position position;
	private MultiWorld world;
	
	public SiafuAgentFactoryMulti (Configuration configuration, MultiWorld world) {
		this.configuration = configuration;
		this.world = world;
		//X: 139 Y: 133 hall / labyrinth
		//X: 199 Y: 206 faculty
		switch (configuration.getWc().map) {
			case 0:  this.position = new Position(139, 133);
					 break;
	        case 1:  this.position = new Position(139, 133);
	                 break;	            
	        case 2:  this.position = new Position(199, 206);
	                 break;
	        case 3:  this.position = new Position(199, 206);
	                 break;
	        default: this.position = new Position(139, 133);
	                 break;
		}
		counter = 0;
	}
	
	/**
	 * Create a loadstation agent.
	 * 
	 * @param world
	 *            the world to create it in
	 * @return the new agent
	 */
	public ISimulatorAgent createLoadStationAgent() {
		counter++;
		SiafuAgentMulti agent = new SiafuAgentMulti("Robbi_" + counter, position, world, configuration);
		
		//add hardware components
		agent.getRobot().addHardwareComponent(new Rechner());
		agent.getRobot().addHardwareComponent(new LoadStation());
		agent.getRobot().addHardwareComponent(new Wlan());
		
		return agent;
	}
	
	/**
	 * Create a Loadstation without PC.
	 * 
	 * @param world
	 *            the world to create it in
	 * @return the new agent
	 */
	public ISimulatorAgent createLoadStation() {
		counter++;
		SiafuAgentMulti agent = new SiafuAgentMulti("Robbi_" + counter, position, world, configuration);
		
		//add hardware components
		agent.getRobot().addHardwareComponent(new LoadStation());
		
		return agent;
	}
	
	/**
	 * Create a explore agent.
	 * 
	 * @param world
	 *            the world to create it in
	 * @return the new agent
	 */
	public ISimulatorAgent createExploreAgent() {
		counter++;
		SiafuAgentMulti agent = new SiafuAgentMulti("Robbi_" + counter, position, world, configuration);
		
		//add hardware components
		agent.getRobot().addHardwareComponent(new Rechner());
		agent.getRobot().addHardwareComponent(new Wlan());
		agent.getRobot().addHardwareComponent(new Motor());
		agent.getRobot().addHardwareComponent(new LookAroundSensor());	
		
		return agent;
	}	
	
	/**
	 * Create a wipe agent.
	 * 
	 * @param world
	 *            the world to create it in
	 * @return the new agent
	 */
	public ISimulatorAgent createWipeAgent() {
		counter++;
		SiafuAgentMulti agent = new SiafuAgentMulti("Robbi_" + counter, position, world, configuration);
		
		//add hardware components
		agent.getRobot().addHardwareComponent(new Rechner());
		agent.getRobot().addHardwareComponent(new Wlan());
		agent.getRobot().addHardwareComponent(new Motor());
		agent.getRobot().addHardwareComponent(new Wiper());
		
		return agent;
	}
	
	/**
	 * Create a hoove agent.
	 * 
	 * @param world
	 *            the world to create it in
	 * @return the new agent
	 */
	public ISimulatorAgent createHooveAgent() {
		counter++;
		SiafuAgentMulti agent = new SiafuAgentMulti("Robbi_" + counter, position, world, configuration);
		
		//add hardware components
		agent.getRobot().addHardwareComponent(new Rechner());
		agent.getRobot().addHardwareComponent(new Wlan());
		agent.getRobot().addHardwareComponent(new Motor());
		agent.getRobot().addHardwareComponent(new Hoover());
		
		return agent;
	}
		
	/**
	 * Create a number of random agents.
	 * 
	 * @param world
	 *            the world where the agents will dwell
	 * @return an ArrayList with the created agents
	 */
	//public abstract ArrayList<MultiAgent> createRobots(MultiWorld world);

}
