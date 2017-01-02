package de.tud.swt.cleaningrobots.behaviours;

import java.util.ArrayList;
import java.util.List;

import de.tud.swt.cleaningrobots.Behaviour;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.RobotRole;
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
public class MasterSeeAroundBehaviour extends Behaviour {

	private RobotCore master;
	
	private int visionRadius;
	private FieldMerge merge;
	
	private State STATE_BLOCKED;
	private State STATE_FREE;
		
	public MasterSeeAroundBehaviour(RobotCore robot) {
		super(robot);
		
		this.merge = new FieldMerge(this.robot.getConfiguration());
						
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
				
		if (robot.getDestinationContainer().isAtDestination() && !robot.getDestinationContainer().isAtLoadDestination()) {
			//start all hardware components
			this.d.switchAllOn();
			
			//scan area
			//add the new field to the world of the master
			try {
				List<Field> fields = getData();
				//send Field to Robot and ask for new destination and Path
				FieldMergeInformation fmi = new FieldMergeInformation(fields);
				merge.run(robot, master, fmi);
			} catch (Exception e) {
				throw e;
			}
			
		} else {
			//switch off the hardware components if not needed
			this.d.switchAllOff();
		}
		if (robot.getDestinationContainer().isAtDestination())
		{
			robot.setNewInformation(true);
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
		int row =  robot.getPosition().getY() + yOffset;
		int col =  robot.getPosition().getX() + xOffset;
		
		//proof if it is a wall
		boolean positionIsAtWall = robot.getICommunicationAdapter().isWall(row, col);
		
		//create new field
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
		//get the master object
		for (RobotRole rr : robot.getRoles()) {
			if (rr instanceof FollowerRole) {
				master = ((FollowerRole) rr).getMaster().getRobotCore();
			}
		}		
	}
}
