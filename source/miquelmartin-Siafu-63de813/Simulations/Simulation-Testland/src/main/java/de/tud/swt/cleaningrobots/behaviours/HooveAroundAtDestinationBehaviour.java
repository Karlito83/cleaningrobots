package de.tud.swt.cleaningrobots.behaviours;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.tud.swt.cleaningrobots.Behaviour;
import de.tud.swt.cleaningrobots.Demand;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.RobotRole;
import de.tud.swt.cleaningrobots.hardware.Components;
import de.tud.swt.cleaningrobots.hardware.HardwareComponent;
import de.tud.swt.cleaningrobots.hardware.Hoover;
import de.tud.swt.cleaningrobots.model.Field;
import de.tud.swt.cleaningrobots.model.Position;
import de.tud.swt.cleaningrobots.model.State;

public class HooveAroundAtDestinationBehaviour extends Behaviour {
	
	private int visionRadius = 0;
	
	private final State STATE_HOOVE = State.createState("Hoove");
	private final State STATE_FREE = State.createState("Free");
	
	private final Logger logger = LogManager.getRootLogger(); 
	
	public HooveAroundAtDestinationBehaviour(RobotCore robot) {
		super(robot);
				
		supportedStates.add(STATE_HOOVE);
		supportedStates.add(STATE_FREE);
		
		Map<Components, Integer> hardware = new EnumMap<Components, Integer> (Components.class);
		hardware.put(Components.HOOVER, 1);
		
		d = new Demand(hardware, robot);
		hardwarecorrect = d.isCorrect();
				
		for (HardwareComponent robothc : d.getHcs()) {
			if (robothc.getComponents() == Components.HOOVER)
			{
				Hoover las = (Hoover) robothc;
				visionRadius = las.getRadius();
			}
		}
	}

	@Override
	public boolean action() throws Exception {
		
		//Wenn Roboter an Ziel dann machen ann Scanne umgebung und machen wieder aus
		if (getRobot().getDestinationContainer().isAtDestination() && getRobot().getDestinationContainer().isDestinationSet() 
				&& !getRobot().getDestinationContainer().isAtLoadDestination()) {
			//Schalte alle Hardwarecomponenten an wenn sie nicht schon laufen
			for (HardwareComponent hard : d.getHcs())
			{
				if (!hard.isActive())
				{
					hard.changeActive();
				}
			}
			
			//Activate Flage that he has new information
			for (RobotRole rr : getRobot().getRoles()) {
				rr.setNewInformation(true);
			}
					
			//scanne umgebung
			//der Welt des Roboters die neuen Felder hinzufügen
			try {
				this.getRobot().getWorld().addFields(getData());
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
		//System.out.println("Data: " + data);		
		return data;
	}	
	
	//give new hooved field back
	private Field getField(int xOffset, int yOffset)
	{		
		Field result = null;
		
		//Offset mit Agenten position vereinigen
		int y =  getRobot().getPosition().getY() + yOffset;
		int x =  getRobot().getPosition().getX() + xOffset;
		
		Position p = new Position(x, y);
		//could only hoove position he knows about
		if (getRobot().getWorld().hasState(p, STATE_FREE))
		{
			logger.debug("Hoove field: " + x + ", " + y);
			result = getRobot().getWorld().getField(p);
			result.addState(STATE_HOOVE);
		}	
		return result;
	}

}
