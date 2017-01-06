package de.tud.swt.cleaningrobots.behaviours;

import java.util.ArrayList;
import java.util.List;

import de.tud.swt.cleaningrobots.Behaviour;
import de.tud.swt.cleaningrobots.AgentCore;
import de.tud.swt.cleaningrobots.AgentRole;
import de.tud.swt.cleaningrobots.hardware.ComponentTypes;
import de.tud.swt.cleaningrobots.hardware.LookAroundSensor;
import de.tud.swt.cleaningrobots.merge.FieldMerge;
import de.tud.swt.cleaningrobots.model.Field;
import de.tud.swt.cleaningrobots.model.State;
import de.tud.swt.cleaningrobots.roles.FollowerRole;
import de.tud.swt.cleaningrobots.util.FieldMergeInformation;

/**
 * Behavior that activate the laser scanner if the robot is at the destination and scan the place.
 * Send the data directly to the master.
 * 
 * @author Christopher Werner
 *
 */
public class MasterDiscoverAroundBehaviour extends Behaviour {

	private AgentCore master;
	
	private int visionRadius;
	private FieldMerge merge;
	
	private State STATE_BLOCKED;
	private State STATE_FREE;
		
	public MasterDiscoverAroundBehaviour(AgentRole role) {
		super(role);
		
		this.merge = new FieldMerge(this.agentCore.getConfiguration());
		this.master = ((FollowerRole)role).getMaster().getRobotCore();
						
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
				
		if (agentCore.getDestinationContainer().isAtDestination() && !agentCore.getDestinationContainer().isAtLoadDestination()) {
			//start all hardware components
			this.demand.switchAllOn();
			
			//scan area
			//add the new field to the world of the master
			try {
				List<Field> fields = getData();
				//send Field to Robot and ask for new destination and Path
				FieldMergeInformation fmi = new FieldMergeInformation(fields);
				merge.run(agentCore, master, fmi);
			} catch (Exception e) {
				throw e;
			}
			
		} else {
			//switch off the hardware components if not needed
			this.demand.switchAllOff();
		}
		if (agentCore.getDestinationContainer().isAtDestination())
		{
			agentCore.setNewInformation(true);
		}
		return false;
	}
	
	private List<Field> getData() {
		
		List<Field> data = new ArrayList<Field>();
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
		
		//add offset to agent position
		int row =  agentCore.getPosition().getY() + yOffset;
		int col =  agentCore.getPosition().getX() + xOffset;
		
		//proof if it is a wall
		boolean positionIsAtWall = agentCore.getICommunicationAdapter().isWall(row, col);
		
		//create new field
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
