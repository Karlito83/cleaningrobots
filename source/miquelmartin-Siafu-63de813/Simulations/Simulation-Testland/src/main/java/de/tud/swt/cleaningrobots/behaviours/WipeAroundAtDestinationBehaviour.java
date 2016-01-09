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
import de.tud.swt.cleaningrobots.hardware.Wiper;
import de.tud.swt.cleaningrobots.model.Field;
import de.tud.swt.cleaningrobots.model.Position;
import de.tud.swt.cleaningrobots.model.State;

/**
 * Behavior that activate the wiper if the robot is at the destination and wipe the place.
 * 
 * @author Christopher Werner
 *
 */
public class WipeAroundAtDestinationBehaviour extends Behaviour {
	
	private int visionRadius = 0;
	
	private State STATE_WIPE;
	private State STATE_HOOVE;
		
	public WipeAroundAtDestinationBehaviour(RobotCore robot) {
		super(robot);
		
		//create and add the states
		this.STATE_WIPE = robot.configuration.createState("Wipe");
		this.STATE_HOOVE = robot.configuration.createState("Hoove");
				
		supportedStates.add(STATE_WIPE);
		supportedStates.add(STATE_HOOVE);
		
		//add the hardware components and proof there correctness
		Map<Components, Integer> hardware = new EnumMap<Components, Integer> (Components.class);
		hardware.put(Components.WIPER, 1);
		
		d = new Demand(hardware, robot);
		hardwarecorrect = d.isCorrect();
				
		for (HardwareComponent robothc : d.getHcs()) {
			if (robothc.getComponents() == Components.WIPER)
			{
				Wiper las = (Wiper) robothc;
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
				
			//wipe area
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
				Field field = getField(xOffset, yOffset);
				if (field != null)
					data.add(field);
			}
		}
				
		return data;
	}	
	
	//give new wiped field back
	private Field getField(int xOffset, int yOffset)
	{		
		Field result = null;
		
		//set together the Offset with the Agent position
		int y =  robot.getPosition().getY() + yOffset;
		int x =  robot.getPosition().getX() + xOffset;
		
		Position p = new Position(x, y);
		//could only wipe positions he knows about
		if (robot.getWorld().hasState(p, STATE_HOOVE))
		{
			result = robot.getWorld().getField(p);
			result.addState(STATE_WIPE, this.robot.configuration.wc.iteration);
		}	
		return result;
	}

}
