package de.tud.swt.cleaningrobots.behaviours;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import de.tud.swt.cleaningrobots.Behaviour;
import de.tud.swt.cleaningrobots.Demand;
import de.tud.swt.cleaningrobots.FollowerRole;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.RobotRole;
import de.tud.swt.cleaningrobots.hardware.Components;
import de.tud.swt.cleaningrobots.hardware.HardwareComponent;
import de.tud.swt.cleaningrobots.hardware.Hoover;
import de.tud.swt.cleaningrobots.merge.MasterFieldMerge;
import de.tud.swt.cleaningrobots.model.Field;
import de.tud.swt.cleaningrobots.model.Position;
import de.tud.swt.cleaningrobots.model.State;

/**
 * Behavior that activate the hoover if the robot is at the destination and hoove the place.
 * Send the data directly to the master.
 * 
 * @author Christopher Werner
 *
 */
public class MasterHooveAroundBehaviour extends Behaviour {
	
	private RobotCore master;
	
	private Hoover hoove;
	private MasterFieldMerge mfm;
	
	private State STATE_HOOVE;
	private State STATE_FREE;
	
	private boolean firststart;
	
	public MasterHooveAroundBehaviour(RobotCore robot) {
		super(robot);
		
		//create and add the states
		this.STATE_HOOVE = robot.configuration.createState("Hoove");
		this.STATE_FREE = robot.configuration.createState("Free");
		
		this.mfm = new MasterFieldMerge(this.robot.configuration);
		this.firststart = true;
		
		supportedStates.add(STATE_HOOVE);
		supportedStates.add(STATE_FREE);
		
		//add the hardware components and proof there correctness
		Map<Components, Integer> hardware = new EnumMap<Components, Integer> (Components.class);
		hardware.put(Components.HOOVER, 1);
		
		d = new Demand(hardware, robot);
		hardwarecorrect = d.isCorrect();
				
		for (HardwareComponent robothc : d.getHcs()) {
			if (robothc.getComponents() == Components.HOOVER)
			{
				hoove = (Hoover) robothc;
			}
		}		
	}

	@Override
	public boolean action() throws Exception {
		
		if (firststart) {
			//get the master object
			for (RobotRole rr : robot.getRoles()) {
				if (rr instanceof FollowerRole) {
					master = ((FollowerRole) rr).master.getRobotCore();
				}
			}
			firststart = false;
		}
		
		if (robot.getDestinationContainer().isAtDestination() && !robot.getDestinationContainer().isAtLoadDestination()) {
			//start all hardware components
			for (HardwareComponent hard : d.getHcs())
			{
				hard.switchOn();
			}
			
			//hoove area
			//add the new field to the world of the master
			try {
				List<Field> fields = getData();
				//send Field to Robot and ask for new destination and Path
				mfm.sendFieldsAndMerge(robot.getName(), fields, master, "Hoove");
			} catch (Exception e) {
				throw e;
			}
			
		} else {
			//switch off the hardware components if not needed
			for (HardwareComponent hard : d.getHcs())
			{
				hard.switchOff();
			}
		}
		if (robot.getDestinationContainer().isAtDestination())
		{
			robot.setNewInformation(true);
		}
		return false;
	}
	
	private List<Field> getData() {
		
		List<Field> data = new ArrayList<Field>();
		int visionRadius = hoove.getRadius();
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
		//could only hoove position he knows about
		if (master.getWorld().isPassable(p))
		{
			result = new Field(x, y, true, this.robot.configuration.wc.iteration);
			result.addState(STATE_HOOVE, this.robot.configuration.wc.iteration);
		}	
		return result;		
	}
}
