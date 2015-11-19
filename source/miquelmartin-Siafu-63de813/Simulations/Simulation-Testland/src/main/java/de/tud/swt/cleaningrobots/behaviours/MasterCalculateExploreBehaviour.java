package de.tud.swt.cleaningrobots.behaviours;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.tud.swt.cleaningrobots.Behaviour;
import de.tud.swt.cleaningrobots.Demand;
import de.tud.swt.cleaningrobots.MasterRole;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.RobotRole;
import de.tud.swt.cleaningrobots.hardware.Components;
import de.tud.swt.cleaningrobots.hardware.HardwareComponent;
import de.tud.swt.cleaningrobots.merge.MasterFieldMerge;
import de.tud.swt.cleaningrobots.model.Position;
import de.tud.swt.cleaningrobots.model.State;
import de.tud.swt.cleaningrobots.util.RobotDestinationCalculation;

public class MasterCalculateExploreBehaviour extends Behaviour{

	private MasterRole mr;
	
	private State STATE_BLOCKED;
	private State STATE_FREE;	
	private State WORLDSTATE_DISCOVERED;	
		
	private boolean firstStart;
	private int calculationAway;
	private MasterFieldMerge mfm;
	
	private Map<String, RobotDestinationCalculation> information;
	
	private boolean relative;
	
	public MasterCalculateExploreBehaviour(RobotCore robot, MasterRole mr, boolean relative) {
		super(robot);
		
		this.STATE_BLOCKED = ((State)robot.configuration.as).createState("Blocked");
		this.STATE_FREE = ((State)robot.configuration.as).createState("Free");
		this.WORLDSTATE_DISCOVERED = ((State)robot.configuration.as).createState("Discovered");
		
		this.mr = mr;
		this.firstStart = true;
		this.relative = relative;
		this.mfm = new MasterFieldMerge(this.robot.configuration);
		this.information = new HashMap<String, RobotDestinationCalculation>();
		
		Map<Components, Integer> hardware = new EnumMap<Components, Integer> (Components.class);
		hardware.put(Components.WLAN, 1);
		
		supportedStates.add(STATE_BLOCKED);
		supportedStates.add(STATE_FREE);
		
		d = new Demand(hardware, robot);
		hardwarecorrect = d.isCorrect();
	}

	@Override
	public boolean action() {
		
		//Schalte alle Hardwarecomponenten an wenn sie nicht schon laufen
		for (HardwareComponent hard : d.getHcs())
		{
			if (!hard.isActive())
			{
				hard.changeActive();
			}
		}
		
		if (firstStart)
		{
			double maxAway = 0;
			//Suche Explore Robots in der nähe einmal ausführen
			List<RobotRole> follower = this.mr.getFollowers();
				
			for (RobotRole rr : follower) {
				RobotCore core = rr.getRobotCore();
				if (core.hasHardwareComponent(Components.WLAN) && core.hasHardwareComponent(Components.LOOKAROUNDSENSOR))
				{
					//add Robot to Map
					RobotDestinationCalculation rdc = new RobotDestinationCalculation(core.getName());
					rdc.actualPosition = core.getPosition();
					information.put(core.getName(), rdc);
					
					double away = Math.sqrt(core.getAccu().getMaxFieldGoes(core.getMinEnergie()));
					
					if (maxAway < away)
						maxAway = away;
				}
			}
			
			calculationAway = (int) maxAway;
			//System.out.println("Information: " + information.keySet() + " Away: " + calculationAway + " maxAway: " + maxAway);
			
			firstStart = false;
		}
		
		//Suche Explore Robots in der nähe einmal ausführen
		List<RobotCore> allRobots = this.robot.getICommunicationAdapter().getAllRobots();
		allRobots.remove(this.robot);
						
		for (RobotDestinationCalculation rdc : information.values()) {
			//alle NeedNew auf false setzen
			rdc.needNew = false;
			//new und old dest tauschen
			if (rdc.newDest != null)
			{
				//setze newDest zurück auf null und erneuere oldDest
				rdc.oldDest = rdc.newDest;
				rdc.newDest = null;
			}
		}
				
		//suche Roboter beim laden die noch keine neueDest haben und setze Variable				
		boolean newOneFind = false;
				
		for (RobotCore oneRobot : allRobots) {
			//laufe Values durch und suche gleichen Roboter
			for (RobotDestinationCalculation rdc : information.values()) {
				if (oneRobot.getName().equals(rdc.getName())) 
				{
					if (oneRobot.hasNewInformation() && oneRobot.getDestinationContainer().isAtLoadDestination())
					{
						newOneFind = true;
						rdc.needNew = true;
						oneRobot.setNewInformation(false);
					}
				}
			}			
		}
		
		//wenn neue gefunden dann bestimmt neue destination und setze diese
		if (newOneFind) {
			Map<String, RobotDestinationCalculation> result = this.robot.getWorld().getNextUnknownFields(information, calculationAway);
			
			if (result != null) {			
				information = result; 
				
				//neue Informationen noch raussenden
				for (RobotCore oneRobot : allRobots) {
					for (RobotDestinationCalculation rdc : information.values()) {
						if (rdc.getName().equals(oneRobot.getName()) && rdc.needNew)
						{
							mfm.sendDestPath(robot.getName(), oneRobot, robot.getWorld().getPath(rdc.newDest), rdc.newDest);
							rdc.actualPosition = rdc.newDest;
						}
					}
				}
			} else {
				for (RobotDestinationCalculation rdc : information.values()) {
					if (rdc.needNew)
						rdc.finish = true;
				}
			}
		}
		
		for (RobotCore oneRobot : allRobots) {
			//laufe Values durch und suche gleichen Roboter
			for (RobotDestinationCalculation rdc : information.values()) {
				if (oneRobot.getName().equals(rdc.getName())) 
				{
					if (oneRobot.hasNewInformation())
					{						
						Position nextUnknownPosition; 
						
						//look if you need relative algorithm or not
						if (relative)	
							nextUnknownPosition = this.robot.getWorld().getNextUnknownRelativeFieldPosition(rdc.actualPosition, rdc.oldDest); 
						else
							nextUnknownPosition = this.robot.getWorld().getNextUnknownFieldPosition(rdc.actualPosition);
						
						if(nextUnknownPosition != null){								
							//wenn accu vorhanden dann muss ladestatus geprüft werden Prüfe,
							//ob ziel vorher erreicht wird oder ob accu beim fahren leer wird
							if (oneRobot.getAccu() != null)
							{
								//Entfernung Robot bis Ziel
								int sizeOne = robot.getWorld().getPathFromTo(rdc.actualPosition, nextUnknownPosition).size();
								//Entfernung Ziel bis Ladestation
								int sizeThree = robot.getWorld().getPathFromTo(nextUnknownPosition, robot.getPosition()).size();
								int size = sizeOne + sizeThree;
								size +=2;
								//Wenn akku bis zu Ziel nicht mehr 
								if (size * oneRobot.getActualEnergie() > oneRobot.getAccu().getRestKWh())
								{
									//Robot schafft Weg nicht also Fahre zurück zu Ladestation
									if (rdc.actualPosition.equals(robot.getPosition()))
									{
										System.out.println("Robot erreicht keine Unknownposition mehr obwohl diese noch existiert!");
										rdc.finish = true;
									} else {
										mfm.sendDestPath(robot.getName(), oneRobot, robot.getWorld().getPathFromTo(rdc.actualPosition, robot.getPosition()), robot.getPosition());
										rdc.actualPosition = robot.getPosition();
									}
								} else {
									//Robot schafft weg also fahre hin
									mfm.sendDestPath(robot.getName(), oneRobot, robot.getWorld().getPathFromTo(rdc.actualPosition, nextUnknownPosition), nextUnknownPosition);
									rdc.actualPosition = nextUnknownPosition;
								}
							} else {
								mfm.sendDestPath(robot.getName(), oneRobot, robot.getWorld().getPathFromTo(rdc.actualPosition, nextUnknownPosition), nextUnknownPosition);
								rdc.actualPosition = nextUnknownPosition;
							}
						}
						else 
						{
							this.robot.getWorld().addWorldState(WORLDSTATE_DISCOVERED);
							if(!rdc.actualPosition.equals(robot.getPosition()))
							{
								//Ist an Ladestation angekommen muss geladen werden
								mfm.sendDestPath(robot.getName(), oneRobot, robot.getWorld().getPathFromTo(rdc.actualPosition, robot.getPosition()), robot.getPosition());
								rdc.actualPosition = robot.getPosition();
							} else {
								rdc.finish = true;
							}
						}						
						//calculiere die Position neu für den neuen
						oneRobot.setNewInformation(false);
					}
				}
			}			
		}				
		return false;
	}
	
	public boolean isFinishDisscovering () {
		for (RobotDestinationCalculation rdc : information.values()) {
			if (!rdc.finish)
				return false;
		}
		if (this.robot.getWorld().containsWorldState(WORLDSTATE_DISCOVERED))
		{
			for (RobotCore core : this.robot.getICommunicationAdapter().getAllRobots())
				core.getWorld().addWorldState(WORLDSTATE_DISCOVERED);
			return true;
		} else {
			System.out.println("Roboter brauchen größeren Accu können Welt nicht mehr erkunden!");
			try {
				Thread.sleep(40000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
}
