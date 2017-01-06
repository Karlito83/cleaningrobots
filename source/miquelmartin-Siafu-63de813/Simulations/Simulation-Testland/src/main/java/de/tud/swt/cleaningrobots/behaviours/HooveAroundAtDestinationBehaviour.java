package de.tud.swt.cleaningrobots.behaviours;

import java.util.ArrayList;
import java.util.Collection;

import de.tud.swt.cleaningrobots.Behaviour;
import de.tud.swt.cleaningrobots.AgentRole;
import de.tud.swt.cleaningrobots.hardware.ComponentTypes;
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
		
	public HooveAroundAtDestinationBehaviour(AgentRole role) {
		super(role);
		
		Hoover las = (Hoover) demand.getHardwareComponent(ComponentTypes.HOOVER);
		this.visionRadius = las.getMeasurementRange();
	}
	
	@Override
	protected void addSupportedStates ()
	{
		//create and add the states
		this.STATE_HOOVE = agentCore.getConfiguration().createState("Hoove");
		this.STATE_FREE = agentCore.getConfiguration().createState("Free");
						
		this.supportedStates.add(this.STATE_HOOVE);
		this.supportedStates.add(this.STATE_FREE);
	}
	
	@Override
	protected void addHardwareComponents ()
	{
		this.demand.addDemandPair(ComponentTypes.HOOVER, 1);
	}

	@Override
	public boolean action() throws Exception {
		
		if (agentCore.getDestinationContainer().isAtDestination() && agentCore.getDestinationContainer().isDestinationSet() 
				&& !agentCore.getDestinationContainer().isAtLoadDestination()) {
			//start all hardware components
			this.demand.switchAllOn();
			
			//Activate flag that he has new information
			agentRole.setNewInformation(true);
					
			//hoove area
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
		int y =  agentCore.getPosition().getY() + yOffset;
		int x =  agentCore.getPosition().getX() + xOffset;
		
		Position p = new Position(x, y);
		//could only hoove positions he knows about
		if (agentCore.getWorld().hasState(p, STATE_FREE))
		{
			result = agentCore.getWorld().getField(p);
			result.addState(STATE_HOOVE, this.agentCore.getConfiguration().getWc().iteration);
		}	
		return result;
	}

	@Override
	public void initialiseBehaviour() {
		//do nothing before first start		
	}

}
