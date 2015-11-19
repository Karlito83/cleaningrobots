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
import de.tud.swt.cleaningrobots.hardware.Wiper;
import de.tud.swt.cleaningrobots.merge.MasterFieldMerge;
import de.tud.swt.cleaningrobots.model.Field;
import de.tud.swt.cleaningrobots.model.Position;
import de.tud.swt.cleaningrobots.model.State;

public class MasterWipeAroundBehaviour extends Behaviour {

	private RobotCore master;
	
	private Wiper wipe;
	private MasterFieldMerge mfm;
	
	private State STATE_HOOVE;
	private State STATE_WIPE;
	
	private boolean firststart;
	
	public MasterWipeAroundBehaviour(RobotCore robot) {
		super(robot);
		
		this.STATE_HOOVE = ((State)robot.configuration.as).createState("Hoove");
		this.STATE_WIPE = ((State)robot.configuration.as).createState("Wipe");
		
		this.mfm = new MasterFieldMerge(this.robot.configuration);
		this.firststart = true;
		
		supportedStates.add(STATE_HOOVE);
		supportedStates.add(STATE_WIPE);
		
		Map<Components, Integer> hardware = new EnumMap<Components, Integer> (Components.class);
		hardware.put(Components.WIPER, 1);
		
		d = new Demand(hardware, robot);
		hardwarecorrect = d.isCorrect();
				
		for (HardwareComponent robothc : d.getHcs()) {
			if (robothc.getComponents() == Components.WIPER)
			{
				wipe = (Wiper) robothc;
			}
		}		
	}

	@Override
	public boolean action() throws Exception {
		
		if (firststart) {
			//gehe davon aus das Master im Wlan sichtbereich ist
			for (RobotRole rr : robot.getRoles()) {
				if (rr instanceof FollowerRole) {
					master = ((FollowerRole) rr).master.getRobotCore();
				}
			}
			firststart = false;
		}
		
		//Wenn Roboter an Ziel dann machen ann Scanne umgebung und machen wieder aus
		if (robot.getDestinationContainer().isAtDestination() && !robot.getDestinationContainer().isAtLoadDestination()) {
			//Schalte alle Hardwarecomponenten an wenn sie nicht schon laufen
			for (HardwareComponent hard : d.getHcs())
			{
				if (!hard.isActive())
				{
					hard.changeActive();
				}
			}
			
			//scanne umgebung
			//der Welt des Roboters die neuen Felder hinzufügen
			try {
				List<Field> fields = getData();
				//send Field to Robot and ask für new destination and Path
				mfm.sendFieldsAndMerge(robot.getName(), fields, master, "Wipe");
			} catch (Exception e) {
				throw e;
			}
			
		} else {
			//kommt erst im nächsten Schritt damit die Energie beachtet wird
			//Schalte alle Hardwarecomponenten aus
			for (HardwareComponent hard : d.getHcs())
			{
				if (hard.isActive())
				{
					hard.changeActive();
				}
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
		int visionRadius = wipe.getRadius();
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
		if (master.getWorld().hasState(p, STATE_HOOVE))
		{
			result = new Field(x, y, true, this.robot.configuration.iteration);
			result.addState(STATE_WIPE, this.robot.configuration.iteration);
		}	
		return result;		
	}
}
