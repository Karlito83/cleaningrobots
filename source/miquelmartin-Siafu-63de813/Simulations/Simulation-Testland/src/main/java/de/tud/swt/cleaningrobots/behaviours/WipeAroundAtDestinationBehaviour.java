package de.tud.swt.cleaningrobots.behaviours;

import java.util.ArrayList;
import java.util.Collection;
import de.tud.swt.cleaningrobots.Behaviour;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.RobotRole;
import de.tud.swt.cleaningrobots.hardware.ComponentTypes;
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
	
	private int visionRadius;
	
	private State STATE_WIPE;
	private State STATE_HOOVE;
		
	public WipeAroundAtDestinationBehaviour(RobotCore robot) {
		super(robot);
		
		Wiper las = (Wiper) this.d.getHardwareComponent(ComponentTypes.WIPER);
		this.visionRadius = las.getMeasurementRange();
	}
	
	@Override
	protected void addSupportedStates() {
		//create and add the states
		this.STATE_WIPE = robot.getConfiguration().createState("Wipe");
		this.STATE_HOOVE = robot.getConfiguration().createState("Hoove");
						
		this.supportedStates.add(this.STATE_WIPE);
		this.supportedStates.add(this.STATE_HOOVE);		
	}

	@Override
	protected void addHardwareComponents() {
		this.d.addDemandPair(ComponentTypes.WIPER, 1);
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
				
			//wipe area
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
			result.addState(STATE_WIPE, this.robot.getConfiguration().getWc().iteration);
		}	
		return result;
	}

	@Override
	public void initialiseBehaviour() {
		//do nothing before first start		
	}

}
