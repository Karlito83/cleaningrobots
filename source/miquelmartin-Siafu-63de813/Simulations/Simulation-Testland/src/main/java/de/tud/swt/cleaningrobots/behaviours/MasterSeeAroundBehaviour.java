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
import de.tud.swt.cleaningrobots.hardware.LookAroundSensor;
import de.tud.swt.cleaningrobots.merge.MasterFieldMerge;
import de.tud.swt.cleaningrobots.model.Field;
import de.tud.swt.cleaningrobots.model.State;

public class MasterSeeAroundBehaviour extends Behaviour {

	private RobotCore master;
	
	private LookAroundSensor las;
	private MasterFieldMerge mfm;
	
	private State STATE_BLOCKED;
	private State STATE_FREE;
	
	private boolean firststart;
	
	public MasterSeeAroundBehaviour(RobotCore robot) {
		super(robot);
		
		this.STATE_BLOCKED = ((State)robot.configuration.as).createState("Blocked");
		this.STATE_FREE = ((State)robot.configuration.as).createState("Free");
		
		this.mfm = new MasterFieldMerge(this.robot.configuration);
		this.firststart = true;
		
		supportedStates.add(STATE_BLOCKED);
		supportedStates.add(STATE_FREE);
		
		Map<Components, Integer> hardware = new EnumMap<Components, Integer> (Components.class);
		hardware.put(Components.LOOKAROUNDSENSOR, 1);
		
		d = new Demand(hardware, robot);
		hardwarecorrect = d.isCorrect();
				
		for (HardwareComponent robothc : d.getHcs()) {
			if (robothc.getComponents() == Components.LOOKAROUNDSENSOR)
			{
				las = (LookAroundSensor) robothc;
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
				mfm.sendFieldsAndMerge(robot.getName(), fields, master, "Explore");
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
		int visionRadius = las.getRadius();
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
		
		//Offset mit Agenten position vereinigen
		int row =  robot.getPosition().getY() + yOffset;
		int col =  robot.getPosition().getX() + xOffset;
		
		//prüfe ob es eine Wand ist
		boolean positionIsAtWall = robot.getICommunicationAdapter().isWall(row, col);
		
		//neues Feld anlegen
		result = new Field(col, row, !positionIsAtWall, this.robot.configuration.iteration);
		//wenn Wand ist dann status dazu anlegen ansonsten freien Status geben
		if(positionIsAtWall)
		{
			result.addState(STATE_BLOCKED, this.robot.configuration.iteration);
		}
		else
		{
			result.addState(STATE_FREE, this.robot.configuration.iteration);
		}
		
		
		return result;
	}
}
