package de.tud.swt.cleaningrobots.behaviours;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.Map;

import de.tud.swt.cleaningrobots.Behaviour;
import de.tud.swt.cleaningrobots.Demand;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.hardware.Components;
import de.tud.swt.cleaningrobots.hardware.HardwareComponent;
import de.tud.swt.cleaningrobots.hardware.LookAroundSensor;
import de.tud.swt.cleaningrobots.model.Field;
import de.tud.swt.cleaningrobots.model.State;

public class SeeAroundBehaviour extends Behaviour {

	private int visionRadius = 0;
	
	private final State STATE_BLOCKED = State.createState("Blocked");
	private final State STATE_FREE = State.createState("Free");
		
	public SeeAroundBehaviour(RobotCore robot) {
		super(robot);
				
		supportedStates.add(STATE_BLOCKED);
		supportedStates.add(STATE_FREE);
		
		Map<Components, Integer> hardware = new EnumMap<Components, Integer> (Components.class);
		hardware.put(Components.LOOKAROUNDSENSOR, 1);
		
		d = new Demand(hardware, robot);
		hardwarecorrect = d.isCorrect();
				
		for (HardwareComponent robothc : d.getHcs()) {
			if (robothc.getComponents() == Components.LOOKAROUNDSENSOR)
			{
				LookAroundSensor las = (LookAroundSensor) robothc;
				visionRadius = las.getRadius();
			}
		}
	}

	@Override
	public boolean action() throws Exception {
		
		//Schalte alle Hardwarecomponenten an wenn sie nicht schon laufen
		for (HardwareComponent hard : d.getHcs())
		{
			if (!hard.isActive())
			{
				hard.changeActive();
			}
		}
		
		//der Welt des Roboters die neuen Felder hinzufügen
		try {
			this.getRobot().getWorld().addFields(getData());
		} catch (Exception e) {
			throw e;
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
		
		//Offset mit Agenten position vereinigen
		int row =  getRobot().getINavigationController().getRow() + yOffset;
		int col =  getRobot().getINavigationController().getCol() + xOffset;
		
		//prüfe ob es eine Wand ist
		boolean positionIsAtWall = getRobot().getINavigationController().isWall(row, col);
		
		//neues Feld anlegen
		result = new Field(col, row, !positionIsAtWall, this.getRobot().configuration.iteration);
		//wenn Wand ist dann status dazu anlegen ansonsten freien Status geben
		if(positionIsAtWall)
		{
			result.addState(STATE_BLOCKED, this.getRobot().configuration.iteration);
		}
		else
		{
			result.addState(STATE_FREE, this.getRobot().configuration.iteration);
		}
		
		
		return result;
	}

}
