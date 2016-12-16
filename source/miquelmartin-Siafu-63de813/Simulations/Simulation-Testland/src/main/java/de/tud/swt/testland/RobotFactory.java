package de.tud.swt.testland;

import de.nec.nle.siafu.exceptions.PlaceNotFoundException;
import de.nec.nle.siafu.model.World;
import de.tud.swt.cleaningrobots.Configuration;
import de.tud.swt.cleaningrobots.hardware.Hoover;
import de.tud.swt.cleaningrobots.hardware.LoadStation;
import de.tud.swt.cleaningrobots.hardware.LookAroundSensor;
import de.tud.swt.cleaningrobots.hardware.Motor;
import de.tud.swt.cleaningrobots.hardware.Rechner;
import de.tud.swt.cleaningrobots.hardware.Wiper;
import de.tud.swt.cleaningrobots.hardware.Wlan;

/**
 * With user interface in Siafu.
 * Create the different robot type and create the different test case only abstract class.
 * 
 * @author Christopher Werner
 *
 */
public class RobotFactory implements ICreateAgents {
	
	private int counter;
	private Configuration configuration;
	private World world;
	
	public RobotFactory (Configuration configuration, World world) {
		this.world = world;
		this.configuration = configuration;
		this.counter = 0;
	}
	
	/**
	 * Create only a loadstation without rechner.
	 * 
	 * @param world
	 *            the world to create it in
	 * @return the new agent
	 */
	@Override
	public IRobotAgent createLoadStation() {
		try {
			counter++;
			RobotAgent agent = new RobotAgent("Robbi_" + counter, world
					.getRandomPlaceOfType("Center").getPos(), "Master",
					world, configuration);

			//add hardware components
			agent.getRobot().addHardwareComponent(new LoadStation());
			
			return agent;
		} catch (PlaceNotFoundException e) {
			throw new RuntimeException(
					"You didn't define the right type of places", e);
		}
	}
	
	/**
	 * Create a loadstation agent.
	 * 
	 * @param world
	 *            the world to create it in
	 * @return the new agent
	 */
	@Override
	public IRobotAgent createLoadStationAgent() {
		try {
			counter++;
			RobotAgent agent = new RobotAgent("Robbi_" + counter, world
					.getRandomPlaceOfType("Center").getPos(), "Master",
					world, configuration);
			
			//add hardware components
			agent.getRobot().addHardwareComponent(new Rechner());
			agent.getRobot().addHardwareComponent(new LoadStation());
			agent.getRobot().addHardwareComponent(new Wlan());
			
			return agent;
		} catch (PlaceNotFoundException e) {
			throw new RuntimeException(
					"You didn't define the right type of places", e);
		}
	}
	
	/**
	 * Create a explore agent.
	 * 
	 * @param world
	 *            the world to create it in
	 * @return the new agent
	 */
	@Override
	public IRobotAgent createExploreAgent() {
		try {
			counter++;
			RobotAgent agent = new RobotAgent("Robbi_" + counter, world
					.getRandomPlaceOfType("Center").getPos(), "HumanMagenta",
					world, configuration);

			//add hardware components
			agent.getRobot().addHardwareComponent(new Rechner());
			agent.getRobot().addHardwareComponent(new Wlan());
			agent.getRobot().addHardwareComponent(new Motor());
			agent.getRobot().addHardwareComponent(new LookAroundSensor());	
			
			return agent;
		} catch (PlaceNotFoundException e) {
			throw new RuntimeException(
					"You didn't define the right type of places", e);
		}
	}	
	
	/**
	 * Create a wipe agent.
	 * 
	 * @param world
	 *            the world to create it in
	 * @return the new agent
	 */
	@Override
	public IRobotAgent createWipeAgent() {
		try {
			counter++;
			RobotAgent agent = new RobotAgent("Robbi_" + counter, world
					.getRandomPlaceOfType("Center").getPos(), "HumanYellow",
					world, configuration);
			
			//add hardware components
			agent.getRobot().addHardwareComponent(new Rechner());
			agent.getRobot().addHardwareComponent(new Wlan());
			agent.getRobot().addHardwareComponent(new Motor());
			agent.getRobot().addHardwareComponent(new Wiper());
			
			return agent;
		} catch (PlaceNotFoundException e) {
			throw new RuntimeException(
					"You didn't define the right type of places", e);
		}
	}
	
	/**
	 * Create a hoove agent.
	 * 
	 * @param world
	 *            the world to create it in
	 * @return the new agent
	 */
	@Override
	public IRobotAgent createHooveAgent() {
		try {
			counter++;
			RobotAgent agent = new RobotAgent("Robbi_" + counter, world
					.getRandomPlaceOfType("Center").getPos(), "HumanGreen",
					world, configuration);

			//add hardware components
			agent.getRobot().addHardwareComponent(new Rechner());
			agent.getRobot().addHardwareComponent(new Wlan());
			agent.getRobot().addHardwareComponent(new Motor());
			agent.getRobot().addHardwareComponent(new Hoover());
			
			return agent;
		} catch (PlaceNotFoundException e) {
			throw new RuntimeException(
					"You didn't define the right type of places", e);
		}
	}
		
	/**
	 * Create a number of random agents.
	 * 
	 * @param world
	 *            the world where the agents will dwell
	 * @return an ArrayList with the created agents
	 */
	//public abstract ArrayList<Agent> createRobots(World world);

}
