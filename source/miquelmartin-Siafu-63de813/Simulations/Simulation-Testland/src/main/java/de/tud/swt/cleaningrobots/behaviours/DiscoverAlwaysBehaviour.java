package de.tud.swt.cleaningrobots.behaviours;

import java.util.ArrayList;
import java.util.Collection;

import de.tud.swt.cleaningrobots.Behaviour;
import de.tud.swt.cleaningrobots.AgentRole;
import de.tud.swt.cleaningrobots.hardware.ComponentTypes;
import de.tud.swt.cleaningrobots.hardware.LookAroundSensor;
import de.tud.swt.cleaningrobots.model.Field;
import de.tud.swt.cleaningrobots.model.State;

/**
 * Old one.
 * Behavior that activate always the laser scanner and always scan the place.
 * 
 * @author Christopher Werner
 *
 */
public class DiscoverAlwaysBehaviour extends Behaviour {

	private int visionRadius;
	
	private State STATE_BLOCKED;
	private State STATE_FREE;
		
	public DiscoverAlwaysBehaviour(AgentRole role) {
		super(role);
		
		LookAroundSensor las = (LookAroundSensor) this.demand.getHardwareComponent(ComponentTypes.LOOKAROUNDSENSOR);
		this.visionRadius = las.getMeasurementRange();
	}
	
	@Override
	protected void addSupportedStates() {
		//create and add the states
		this.STATE_BLOCKED = agentCore.getConfiguration().createState("Blocked");
		this.STATE_FREE = agentCore.getConfiguration().createState("Free");
						
		this.supportedStates.add(this.STATE_BLOCKED);
		this.supportedStates.add(this.STATE_FREE);		
	}

	@Override
	protected void addHardwareComponents() {
		this.demand.addDemandPair(ComponentTypes.LOOKAROUNDSENSOR, 1);
	}

	@Override
	public boolean action() throws Exception {
		
		//start all hardware components
		this.demand.switchAllOn();
		
		//add the new field to the world of the robot
		try {
			this.agentCore.getWorld().addFields(getData());
		} catch (Exception e) {
			throw e;
		}		
		return false;
	}
	
	private Collection<Field> getData() {
		
		Collection<Field> data = new ArrayList<Field>();
		
		for (int xOffset=-visionRadius; xOffset<=visionRadius; xOffset++)
		{
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
		int row =  agentCore.getPosition().getY() + yOffset;
		int col =  agentCore.getPosition().getX() + xOffset;
		
		//proof if it is a wall
		boolean positionIsAtWall = agentCore.getICommunicationAdapter().isWall(row, col);
		
		//add new field
		result = new Field(col, row, !positionIsAtWall, this.agentCore.getConfiguration().getWc().iteration);
		if(positionIsAtWall)
		{
			result.addState(STATE_BLOCKED, this.agentCore.getConfiguration().getWc().iteration);
		}
		else
		{
			result.addState(STATE_FREE, this.agentCore.getConfiguration().getWc().iteration);
		}		
		
		return result;
	}

	@Override
	public void initialiseBehaviour() {
		//do nothing before first start		
	}
}
