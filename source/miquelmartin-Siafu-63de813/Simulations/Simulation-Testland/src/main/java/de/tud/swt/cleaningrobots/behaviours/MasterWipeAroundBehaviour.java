package de.tud.swt.cleaningrobots.behaviours;

import java.util.ArrayList;
import java.util.List;
import de.tud.swt.cleaningrobots.Behaviour;
import de.tud.swt.cleaningrobots.FollowerRole;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.RobotRole;
import de.tud.swt.cleaningrobots.hardware.Components;
import de.tud.swt.cleaningrobots.hardware.Wiper;
import de.tud.swt.cleaningrobots.merge.MasterFieldMerge;
import de.tud.swt.cleaningrobots.model.Field;
import de.tud.swt.cleaningrobots.model.Position;
import de.tud.swt.cleaningrobots.model.State;

/**
 * Behavior that activate the wiper if the robot is at the destination and wipe the place.
 * Send the data directly to the master.
 * 
 * @author Christopher Werner
 *
 */
public class MasterWipeAroundBehaviour extends Behaviour {

	private RobotCore master;
	
	private int visionRadius;
	private MasterFieldMerge mfm;
	
	private State STATE_HOOVE;
	private State STATE_WIPE;
	
	private boolean firststart;
	
	public MasterWipeAroundBehaviour(RobotCore robot) {
		super(robot);
		
		this.mfm = new MasterFieldMerge(this.robot.configuration);
		this.firststart = true;
						
		Wiper las = (Wiper) this.d.getHardwareComponent(Components.WIPER);
		this.visionRadius = las.getRadius();		
	}
	
	@Override
	protected void addSupportedStates() {
		//create and add the states
		this.STATE_HOOVE = robot.configuration.createState("Hoove");
		this.STATE_WIPE = robot.configuration.createState("Wipe");
								
		this.supportedStates.add(this.STATE_HOOVE);
		this.supportedStates.add(this.STATE_WIPE);		
	}

	@Override
	protected void addHardwareComponents() {
		this.d.addDemandPair(Components.WIPER, 1);
	}

	@Override
	public boolean action() throws Exception {
		
		if (firststart) {
			//get the master object
			for (RobotRole rr : robot.getRoles()) {
				if (rr instanceof FollowerRole) {
					master = ((FollowerRole) rr).getMaster().getRobotCore();
				}
			}
			firststart = false;
		}
		
		if (robot.getDestinationContainer().isAtDestination() && !robot.getDestinationContainer().isAtLoadDestination()) {
			//start all hardware components
			this.d.switchAllOn();
			
			//wipe area
			//add the new field to the world of the master
			try {
				List<Field> fields = getData();
				//send Field to Robot and ask for new destination and Path
				mfm.sendFieldsAndMerge(robot.getName(), fields, master, "Wipe");
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
		int y =  robot.getPosition().getY() + yOffset;
		int x =  robot.getPosition().getX() + xOffset;
		
		Position p = new Position(x, y);
		//could only wipe position he knows about
		if (master.getWorld().hasState(p, STATE_HOOVE))
		{
			result = new Field(x, y, true, this.robot.configuration.wc.iteration);
			result.addState(STATE_WIPE, this.robot.configuration.wc.iteration);
		}	
		return result;		
	}
}
