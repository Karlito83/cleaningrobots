package de.tud.swt.cleaningrobots.behaviours;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

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
import de.tud.swt.cleaningrobots.merge.MasterFieldMerge;
import de.tud.swt.cleaningrobots.model.Field;
import de.tud.swt.cleaningrobots.model.State;

public class MasterSeeAroundBehaviour extends Behaviour {

	private Agent agent;
	private World siafuWorld;
	
	private RobotCore master;
	
	private LookAroundSensor las;
	private MasterFieldMerge mfm;
	
	private final State STATE_BLOCKED = State.createState("Blocked");
	private final State STATE_FREE = State.createState("Free");
	
	private boolean firststart;
	
	public MasterSeeAroundBehaviour(RobotCore robot) {
		super(robot);
		
		this.agent = robot.getINavigationController().getAgent();
		this.siafuWorld = robot.getINavigationController().getSiafuWorld();	
		this.mfm = new MasterFieldMerge();
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
			for (RobotRole rr : getRobot().getRoles()) {
				if (rr instanceof FollowerRole) {
					master = ((FollowerRole) rr).master.getRobotCore();
				}
			}
			firststart = false;
		}
		
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
			
			//scanne umgebung
			//der Welt des Roboters die neuen Felder hinzufügen
			try {
				List<Field> fields = getData();
				//send Field to Robot and ask für new destination and Path
				mfm.sendFieldsAndMerge(getRobot().getName(), fields, master, "Explore");
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
		if (getRobot().getDestinationContainer().isAtDestination())
		{
			getRobot().setNewInformation(true);
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
		int row =  agent.getPos().getRow() + yOffset;
		int column =  agent.getPos().getCol() + xOffset;
		
		//prüfe ob es eine Wand ist
		boolean positionIsAtWall = siafuWorld.isAWall(new Position(row, column));
		
		//neues Feld anlegen
		result = new Field(column, row, !positionIsAtWall);
		//wenn Wand ist dann status dazu anlegen ansonsten freien Status geben
		if(positionIsAtWall)
		{
			result.addState(STATE_BLOCKED);
		}
		else
		{
			result.addState(STATE_FREE);
		}
		
		
		return result;
	}
}
