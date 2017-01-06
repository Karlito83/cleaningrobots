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
 * Behavior that activate the laser scanner if the robot is at the destination and scan the place.
 * 
 * @author Christopher Werner
 *
 */
public class DiscoverAroundAtDestinationBehaviour extends Behaviour {

	private int visionRadius;
	
	private State STATE_BLOCKED;
	private State STATE_FREE;
	
	public DiscoverAroundAtDestinationBehaviour(AgentRole role) {
		super(role);
				
		LookAroundSensor las = (LookAroundSensor) demand.getHardwareComponent(ComponentTypes.LOOKAROUNDSENSOR);
		this.visionRadius = las.getMeasurementRange();
	}
	
	@Override
	protected void addSupportedStates() {
		//create and add the states
		this.STATE_BLOCKED = agentCore.getConfiguration().createState("Blocked");
		this.STATE_FREE = agentCore.getConfiguration().createState("Free");
				
		this.supportedStates.add(STATE_BLOCKED);
		this.supportedStates.add(STATE_FREE);		
	}

	@Override
	protected void addHardwareComponents() {
		this.demand.addDemandPair(ComponentTypes.LOOKAROUNDSENSOR, 1);		
	}

	@Override
	public boolean action() throws Exception {
		
		if (agentCore.getDestinationContainer().isAtDestination() && agentCore.getDestinationContainer().isDestinationSet()
				&& !agentCore.getDestinationContainer().isAtLoadDestination()) {
			//start all hardware components
			this.demand.switchAllOn();
			
			//Activate flag that he has new information
			agentRole.setNewInformation(true);
			
			//scan area
			//add the new field to the world of the robot
			try {
				this.agentCore.getWorld().addFields(getData());
			} catch (Exception e) {
				throw e;
			}
			
		} else {
			//switch off the hardware components if not needed
			this.demand.switchAllOff();
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
