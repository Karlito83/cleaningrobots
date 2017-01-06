package de.tud.swt.cleaningrobots.behaviours;

import java.util.ArrayList;
import java.util.List;

import de.tud.swt.cleaningrobots.Behaviour;
import de.tud.swt.cleaningrobots.AgentCore;
import de.tud.swt.cleaningrobots.AgentRole;
import de.tud.swt.cleaningrobots.hardware.ComponentTypes;
import de.tud.swt.cleaningrobots.hardware.Wiper;
import de.tud.swt.cleaningrobots.merge.FieldMerge;
import de.tud.swt.cleaningrobots.model.Field;
import de.tud.swt.cleaningrobots.model.Position;
import de.tud.swt.cleaningrobots.model.State;
import de.tud.swt.cleaningrobots.roles.FollowerRole;
import de.tud.swt.cleaningrobots.util.FieldMergeInformation;

/**
 * Behavior that activate the wiper if the robot is at the destination and wipe the place.
 * Send the data directly to the master.
 * 
 * @author Christopher Werner
 *
 */
public class MasterWipeAroundBehaviour extends Behaviour {

	private AgentCore master;
	
	private int visionRadius;
	private FieldMerge merge;
	
	private State STATE_HOOVE;
	private State STATE_WIPE;
		
	public MasterWipeAroundBehaviour(AgentRole role) {
		super(role);
		
		this.merge = new FieldMerge(this.agentCore.getConfiguration());
		this.master = ((FollowerRole)role).getMaster().getRobotCore();
						
		Wiper las = (Wiper) this.demand.getHardwareComponent(ComponentTypes.WIPER);
		this.visionRadius = las.getMeasurementRange();		
	}
	
	@Override
	protected void addSupportedStates() {
		//create and add the states
		this.STATE_HOOVE = agentCore.getConfiguration().createState("Hoove");
		this.STATE_WIPE = agentCore.getConfiguration().createState("Wipe");
								
		this.supportedStates.add(this.STATE_HOOVE);
		this.supportedStates.add(this.STATE_WIPE);		
	}

	@Override
	protected void addHardwareComponents() {
		this.demand.addDemandPair(ComponentTypes.WIPER, 1);
	}

	@Override
	public boolean action() throws Exception {
		
		if (agentCore.getDestinationContainer().isAtDestination() && !agentCore.getDestinationContainer().isAtLoadDestination()) {
			//start all hardware components
			this.demand.switchAllOn();
			
			//wipe area
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
		//could only wipe position he knows about
		if (master.getWorld().hasState(p, STATE_HOOVE))
		{
			result = new Field(x, y, true, this.agentCore.getConfiguration().getWc().iteration);
			result.addState(STATE_WIPE, this.agentCore.getConfiguration().getWc().iteration);
		}	
		return result;		
	}

	@Override
	public void initialiseBehaviour() {
		//do nothing before first start			
	}
}
