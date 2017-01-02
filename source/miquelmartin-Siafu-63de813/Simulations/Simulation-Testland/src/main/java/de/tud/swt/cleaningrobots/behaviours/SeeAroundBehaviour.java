package de.tud.swt.cleaningrobots.behaviours;

import java.util.ArrayList;
import java.util.Collection;
import de.tud.swt.cleaningrobots.Behaviour;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.hardware.ComponentTypes;
import de.tud.swt.cleaningrobots.hardware.LookAroundSensor;
import de.tud.swt.cleaningrobots.model.Field;
import de.tud.swt.cleaningrobots.model.State;

/**
 * Old one.
 * Behavior that activate the laser scanner if the robot is at the destination and scan the place.
 * 
 * @author Christopher Werner
 *
 */
public class SeeAroundBehaviour extends Behaviour {

	private int visionRadius;
	
	private State STATE_BLOCKED;
	private State STATE_FREE;
		
	public SeeAroundBehaviour(RobotCore robot) {
		super(robot);
		
		LookAroundSensor las = (LookAroundSensor) this.d.getHardwareComponent(ComponentTypes.LOOKAROUNDSENSOR);
		this.visionRadius = las.getMeasurementRange();
	}
	
	@Override
	protected void addSupportedStates() {
		//create and add the states
		this.STATE_BLOCKED = robot.getConfiguration().createState("Blocked");
		this.STATE_FREE = robot.getConfiguration().createState("Free");
						
		this.supportedStates.add(this.STATE_BLOCKED);
		this.supportedStates.add(this.STATE_FREE);		
	}

	@Override
	protected void addHardwareComponents() {
		this.d.addDemandPair(ComponentTypes.LOOKAROUNDSENSOR, 1);
	}

	@Override
	public boolean action() throws Exception {
		
		//start all hardware components
		this.d.switchAllOn();
		
		//add the new field to the world of the robot
		try {
			this.robot.getWorld().addFields(getData());
		} catch (Exception e) {
			throw e;
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
		result = new Field(col, row, !positionIsAtWall, this.robot.getConfiguration().getWc().iteration);
		if(positionIsAtWall)
		{
			result.addState(STATE_BLOCKED, this.robot.getConfiguration().getWc().iteration);
		}
		else
		{
			result.addState(STATE_FREE, this.robot.getConfiguration().getWc().iteration);
		}		
		
		return result;
	}

	@Override
	public void initialiseBehaviour() {
		//do nothing before first start		
	}
}
