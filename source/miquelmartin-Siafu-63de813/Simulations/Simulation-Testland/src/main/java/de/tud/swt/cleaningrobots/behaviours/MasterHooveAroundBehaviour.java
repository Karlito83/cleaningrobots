package de.tud.swt.cleaningrobots.behaviours;

import java.util.ArrayList;
import java.util.List;

import de.tud.swt.cleaningrobots.Behaviour;
import de.tud.swt.cleaningrobots.AgentCore;
import de.tud.swt.cleaningrobots.AgentRole;
import de.tud.swt.cleaningrobots.hardware.ComponentTypes;
import de.tud.swt.cleaningrobots.hardware.Hoover;
import de.tud.swt.cleaningrobots.merge.FieldMerge;
import de.tud.swt.cleaningrobots.model.Field;
import de.tud.swt.cleaningrobots.model.Position;
import de.tud.swt.cleaningrobots.model.State;
import de.tud.swt.cleaningrobots.roles.FollowerRole;
import de.tud.swt.cleaningrobots.util.FieldMergeInformation;

/**
 * Behavior that activate the hoover if the robot is at the destination and hoove the place.
 * Send the data directly to the master.
 * 
 * @author Christopher Werner
 *
 */
public class MasterHooveAroundBehaviour extends Behaviour {
	
	private AgentCore master;
	
	private int visionRadius;
	private FieldMerge merge;
	
	private State STATE_HOOVE;
	private State STATE_FREE;
		
	public MasterHooveAroundBehaviour(AgentRole role) {
		super(role);

		this.merge = new FieldMerge(this.agentCore.getConfiguration());
		this.master = ((FollowerRole)role).getMaster().getRobotCore();
		
		Hoover las = (Hoover) this.demand.getHardwareComponent(ComponentTypes.HOOVER);
		this.visionRadius = las.getMeasurementRange();	
	}
	
	@Override
	protected void addSupportedStates() {
		//create and add the states
		this.STATE_HOOVE = agentCore.getConfiguration().createState("Hoove");
		this.STATE_FREE = agentCore.getConfiguration().createState("Free");
						
		this.supportedStates.add(this.STATE_HOOVE);
		this.supportedStates.add(this.STATE_FREE);		
	}

	@Override
	protected void addHardwareComponents() {
		this.demand.addDemandPair(ComponentTypes.HOOVER, 1);
	}

	@Override
	public boolean action() throws Exception {
				
		if (agentCore.getDestinationContainer().isAtDestination() && !agentCore.getDestinationContainer().isAtLoadDestination()) {
			//start all hardware components
			this.demand.switchAllOn();
			
			//hoove area
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
				Field f = getField(xOffset, yOffset);
				if (f != null)
					data.add(f);
			}
		}
				
		return data;
	}	
	
	private Field getField(int xOffset, int yOffset)
	{
		Field result = null;
		
		//Merge offset with Robot Position
		int y =  agentCore.getPosition().getY() + yOffset;
		int x =  agentCore.getPosition().getX() + xOffset;
		
		Position p = new Position(x, y);
		//could only hoove position he knows about
		if (master.getWorld().isPassable(p))
		{
			result = new Field(x, y, true, this.agentCore.getConfiguration().getWc().iteration);
			result.addState(STATE_HOOVE, this.agentCore.getConfiguration().getWc().iteration);
		}	
		return result;		
	}

	@Override
	public void initialiseBehaviour() {
		//do nothing before first start				
	}
}
