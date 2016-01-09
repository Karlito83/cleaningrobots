package de.tud.swt.cleaningrobots.behaviours;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.Map;

import de.tud.swt.cleaningrobots.Behaviour;
import de.tud.swt.cleaningrobots.Demand;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.RobotRole;
import de.tud.swt.cleaningrobots.hardware.Components;
import de.tud.swt.cleaningrobots.hardware.HardwareComponent;
import de.tud.swt.cleaningrobots.hardware.LookAroundSensor;
import de.tud.swt.cleaningrobots.model.Field;
import de.tud.swt.cleaningrobots.model.State;

/**
 * Behavior that activate the laser scanner if the robot is at the destination and scan the place.
 * 
 * @author Christopher Werner
 *
 */
public class DiscoverAroundAtDestinationBehaviour extends Behaviour {

	private int visionRadius = 0;
	
	private State STATE_BLOCKED;
	private State STATE_FREE;
	
	public DiscoverAroundAtDestinationBehaviour(RobotCore robot) {
		super(robot);
		
		//create and add the states
		this.STATE_BLOCKED = robot.configuration.createState("Blocked");
		this.STATE_FREE = robot.configuration.createState("Free");
		
		supportedStates.add(STATE_BLOCKED);
		supportedStates.add(STATE_FREE);
		
		//add the hardware components and proof there correctness
		Map<Components, Integer> hardware = new EnumMap<Components, Integer> (Components.class);
		hardware.put(Components.LOOKAROUNDSENSOR, 1);
		
		d = new Demand(hardware, robot);
		hardwarecorrect = d.isCorrect();
				
		for (HardwareComponent robothc : d.getHcs()) {
			if (robothc.getComponents() == Components.LOOKAROUNDSENSOR)
			{
				LookAroundSensor las = (LookAroundSensor) robothc;
				visionRadius = las.getRadius();
			}
		}
	}

	@Override
	public boolean action() throws Exception {
		
		if (robot.getDestinationContainer().isAtDestination() && robot.getDestinationContainer().isDestinationSet()
				&& !robot.getDestinationContainer().isAtLoadDestination()) {
			//start all hardware components
			for (HardwareComponent hard : d.getHcs())
			{
				hard.switchOn();
			}
			
			//Activate flag that he has new information
			for (RobotRole rr : robot.getRoles()) {
				rr.setNewInformation(true);
			}
			
			//scan area
			//add the new field to the world of the robot
			try {
				this.robot.getWorld().addFields(getData());
			} catch (Exception e) {
				throw e;
			}
			
		} else {
			//switch off the hardware components if not needed
			for (HardwareComponent hard : d.getHcs())
			{
				hard.switchOff();
			}
		}
		return false;
	}
	
	private Collection<Field> getData() {
		
		Collection<Field> data = new ArrayList<Field>();
		
		for (int xOffset=-visionRadius; xOffset<=visionRadius; xOffset++){
			for (int yOffset = -visionRadius; yOffset<=visionRadius; yOffset++ )
			{
				data.add(getField(xOffset, yOffset));
			}
		}
				
		return data;
	}	
	
	private Field getField(int xOffset, int yOffset)
	{
		Field result = null;
		
		//set together the Offset with the Agent position
		int row =  robot.getPosition().getY() + yOffset;
		int col =  robot.getPosition().getX() + xOffset;
		
		//proof if it is a wall
		boolean positionIsAtWall = robot.getICommunicationAdapter().isWall(row, col);
		
		//add new field
		result = new Field(col, row, !positionIsAtWall, this.robot.configuration.wc.iteration);
		if(positionIsAtWall)
		{
			result.addState(STATE_BLOCKED, this.robot.configuration.wc.iteration);
		}
		else
		{
			result.addState(STATE_FREE, this.robot.configuration.wc.iteration);
		}
		
		
		return result;
	}
}
