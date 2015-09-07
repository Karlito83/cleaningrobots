package de.tud.swt.cleaningrobots.behaviours;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.nec.nle.siafu.model.Agent;
import de.nec.nle.siafu.model.Position;
import de.nec.nle.siafu.model.World;
import de.tud.swt.cleaningrobots.Behaviour;
import de.tud.swt.cleaningrobots.Demand;
import de.tud.swt.cleaningrobots.FollowerRole;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.RobotRole;
import de.tud.swt.cleaningrobots.hardware.Components;
import de.tud.swt.cleaningrobots.hardware.HardwareComponent;
import de.tud.swt.cleaningrobots.hardware.LookAroundSensor;
import de.tud.swt.cleaningrobots.model.Field;
import de.tud.swt.cleaningrobots.model.State;

public class SeeAroundAtDestinationBehaviour extends Behaviour {

	private Agent agent;
	private World siafuWorld;
	
	private int visionRadius = 0;
	
	private final State STATE_BLOCKED = State.createState("Blocked");
	private final State STATE_FREE = State.createState("Free");
	
	private final Logger logger = LogManager.getRootLogger(); 
	
	public SeeAroundAtDestinationBehaviour(RobotCore robot) {
		super(robot);
		
		this.agent = robot.getINavigationController().getAgent();
		this.siafuWorld = robot.getINavigationController().getSiafuWorld();		
		
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
		
		//Wenn Roboter an Ziel dann machen ann Scanne umgebung und machen wieder aus
		if (getRobot().getDestinationContainer().isAtDestination() && !getRobot().getDestinationContainer().isAtLoadDestination()) {
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
				if (rr instanceof FollowerRole)
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
				data.add(getField(xOffset, yOffset));
			}
		}
				
		return data;
	}	
	
	private Field getField(int xOffset, int yOffset)
	{
		Field result = null;
		
		//Offset mit Agenten position vereinigen
		int row =  agent.getPos().getRow() + yOffset;
		int column =  agent.getPos().getCol() + xOffset;
		
		//prüfe ob es eine Wand ist
		boolean positionIsAtWall = siafuWorld.isAWall(new Position(row, column));
		
		//neues Feld anlegen
		result = new Field(column, row, !positionIsAtWall);
		//wenn Wand ist dann status dazu anlegen ansonsten freien Status geben
		if(positionIsAtWall)
		{
			logger.debug("Blocked field: " + this);
			result.addState(STATE_BLOCKED);
		}
		else
		{
			result.addState(STATE_FREE);
		}
		
		
		return result;
	}
}
