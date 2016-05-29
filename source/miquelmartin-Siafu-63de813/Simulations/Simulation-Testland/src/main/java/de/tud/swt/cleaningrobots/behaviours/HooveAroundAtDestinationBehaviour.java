package de.tud.swt.cleaningrobots.behaviours;

import java.util.ArrayList;
import java.util.Collection;

import de.tud.swt.cleaningrobots.Behaviour;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.RobotRole;
import de.tud.swt.cleaningrobots.hardware.Components;
import de.tud.swt.cleaningrobots.hardware.Hoover;
import de.tud.swt.cleaningrobots.model.Field;
import de.tud.swt.cleaningrobots.model.Position;
import de.tud.swt.cleaningrobots.model.State;

/**
 * Behavior that activate the hoover if the robot is at the destination and hoove the place.
 * 
 * @author Christopher Werner
 *
 */
public class HooveAroundAtDestinationBehaviour extends Behaviour {
	
	private int visionRadius;
	
	private State STATE_HOOVE;
	private State STATE_FREE;
		
	public HooveAroundAtDestinationBehaviour(RobotCore robot) {
		super(robot);
		
		Hoover las = (Hoover) d.getHardwareComponent(Components.HOOVER);
		this.visionRadius = las.getRadius();
	}
	
	@Override
	protected void addSupportedStates ()
	{
		//create and add the states
		this.STATE_HOOVE = robot.configuration.createState("Hoove");
		this.STATE_FREE = robot.configuration.createState("Free");
						
		this.supportedStates.add(this.STATE_HOOVE);
		this.supportedStates.add(this.STATE_FREE);
	}
	
	@Override
	protected void addHardwareComponents ()
	{
		this.d.addDemandPair(Components.HOOVER, 1);
	}

	@Override
	public boolean action() throws Exception {
		
		if (robot.getDestinationContainer().isAtDestination() && robot.getDestinationContainer().isDestinationSet() 
				&& !robot.getDestinationContainer().isAtLoadDestination()) {
			//start all hardware components
			this.d.switchAllOn();
			
			//Activate flag that he has new information
			for (RobotRole rr : robot.getRoles()) {
				rr.setNewInformation(true);
			}
					
			//hoove area
			//add the new field to the world of the robot
			try {
				this.robot.getWorld().addFields(getData());
			} catch (Exception e) {
				throw e;
			}
				
		} else {
			//switch off the hardware components if not needed
			this.d.switchAllOff();
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
	
	//give new hooved field back
	private Field getField(int xOffset, int yOffset)
	{		
		Field result = null;
		
		//set together the Offset with the Agent position
		int y =  robot.getPosition().getY() + yOffset;
		int x =  robot.getPosition().getX() + xOffset;
		
		Position p = new Position(x, y);
		//could only hoove positions he knows about
		if (robot.getWorld().hasState(p, STATE_FREE))
		{
			result = robot.getWorld().getField(p);
			result.addState(STATE_HOOVE, this.robot.configuration.wc.iteration);
		}	
		return result;
	}

}
