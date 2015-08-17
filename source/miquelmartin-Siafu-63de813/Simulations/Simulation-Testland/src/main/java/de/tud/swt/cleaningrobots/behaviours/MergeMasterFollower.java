package de.tud.swt.cleaningrobots.behaviours;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.emf.ecore.EObject;

import cleaningrobots.CleaningrobotsFactory;
import cleaningrobots.WorldPart;
import de.tud.swt.cleaningrobots.Behaviour;
import de.tud.swt.cleaningrobots.Demand;
import de.tud.swt.cleaningrobots.EMFUtils;
import de.tud.swt.cleaningrobots.FollowerRole;
import de.tud.swt.cleaningrobots.MasterRole;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.RobotRole;
import de.tud.swt.cleaningrobots.hardware.Components;
import de.tud.swt.cleaningrobots.hardware.HardwareComponent;
import de.tud.swt.cleaningrobots.hardware.Wlan;
import de.tud.swt.cleaningrobots.model.Field;
import de.tud.swt.cleaningrobots.model.State;

public class MergeMasterFollower extends Behaviour {

	private final Logger logger = LogManager.getRootLogger();
	
	private Wlan wlan;
	
	private final State STATE_BLOCKED = State.createState("Blocked");
	private final State STATE_FREE = State.createState("Free");		
	
	private List<FollowerRole> lastChange;
	
	public MergeMasterFollower(RobotCore robot) {
		super(robot);
		
		lastChange = new ArrayList<FollowerRole>();
		
		Map<Components, Integer> hardware = new EnumMap<Components, Integer> (Components.class);
		hardware.put(Components.WLAN, 1);
		
		supportedStates.add(STATE_BLOCKED);
		supportedStates.add(STATE_FREE);
		
		d = new Demand(hardware, robot);
		hardwarecorrect = d.isCorrect();
		
		//Vision Radius aus Wlan Hardwarecomponente ziehen
		for (HardwareComponent hard : d.getHcs())
		{
			if (hard.getComponents() == Components.WLAN)
			{
				wlan = (Wlan)hard;
			}
		}
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
		
		//Tausche komplettes modell von ronny
		long endTime, startTime = System.nanoTime();
		logger.trace("Merge Master Follower");
		
		List<RobotCore> nearRobots = this.getRobot().getICommunicationProvider().getNearRobots(wlan.getVisionRadius());
		nearRobots.remove(this.getRobot());
		
		//if no nearRobots end this behavior
		if (nearRobots.isEmpty())
			return false;

		System.out.println("NearRobots: " + nearRobots.size());
		List<FollowerRole> nearsNewInformation = new ArrayList<FollowerRole>();
		List<FollowerRole> nearsNoNewInformation = new ArrayList<FollowerRole>();
		//System.out.println("Robots in Tausch reichweite: " + nearRobots.size());
		for (RobotCore nearRobot : nearRobots) {
			//darf nur mi Robotern in der nähe Kommunizieren wenn diese Wirklich die gleiche HardwareComponente habe und diese aktiv ist
			if (nearRobot.hasActiveHardwareComponent(wlan.getComponents())) {
				//darf auch nur das modell von Robotern einfügen die follower dieses Knotens sind
				List<RobotRole> lrr = getRobot().getRoles();
				for (RobotRole rr : lrr)
				{
					if (rr instanceof MasterRole)
					{
						List<FollowerRole> frr = ((MasterRole)rr).followers;
						//gehe alle Follower dieses Robots durch und prüfe ob einer der hier ist
						for (FollowerRole fr : frr)
						{
							if (fr.getRobotCore().equals(nearRobot))
							{
								if (fr.hasNewInformation())
								{
									System.out.println("Follower with new Information");
									//Roboter in der nähe
									fr.setNewInformation(false);
									nearsNewInformation.add(fr);
									//wenn fr schon in vorheriger Liste dann muss er keine Informationen mehr erhalten
									if (lastChange.contains(fr))
										fr.setNewInformation(true);
									
									//Tausche Model aus
									//Füge modell von Nahem Robot mir hinzu
									EObject model = nearRobot.exportModel();
									importModel(model, this.getRobot());
								} else {
									//System.out.println("Follower without new Information");
									nearsNoNewInformation.add(fr);									
								}
								break;
							}
						}						
					}
				}				
			}
		}
		
		//füge gesammeltes Model den nahen Robotern hinzu
		if (nearsNewInformation.size() >= 1)
		{
			System.out.println("LastChange: " + lastChange + " NewInfo: " + nearsNewInformation + " NoNewInfo: " + nearsNoNewInformation);
			lastChange.clear();
			EObject model = this.getRobot().exportModel();
			if (nearsNewInformation.size() == 1)
			{
				//muss model nur importieren wenn noch nicht in lastchange liste war oder er nicht der einzige mit neuen informationen ist
				for (FollowerRole fr : nearsNewInformation) {
					//fr schon vorher enthalten
					if(!fr.hasNewInformation())
						importModel(model, fr.getRobotCore());
					lastChange.add(fr);
					fr.setNewInformation(false);
				}
			} else {
				for (FollowerRole fr : nearsNewInformation) {
					//importiere allen nahen Robotern das neue Modell
					importModel(model, fr.getRobotCore());
					lastChange.add(fr);
					fr.setNewInformation(false);
				}
			}
			for (FollowerRole fr : nearsNoNewInformation) {
				//importiere allen nahen Robotern das neue Modell
				importModel(model, fr.getRobotCore());
				lastChange.add(fr);
			}			
			System.out.println("LastChange: " + lastChange);
		}
		
		
		endTime = System.nanoTime();
		logger.info("Importing the data from " + nearRobots.size() + " other agents took " + (endTime - startTime) + " ns.");
		
		logger.trace("exit getNearRobotsAndImportModel");
		return false;
	}
	
	private void importFieldsFromWorldModel(cleaningrobots.WorldPart worldPart, RobotCore core) {
		// Maybe an arrayList is better here?
		if (worldPart instanceof cleaningrobots.Map) {
			cleaningrobots.State blockedState = CleaningrobotsFactory.eINSTANCE
					.createState();
			blockedState.setName("Blocked");
			for (cleaningrobots.Field modelField : ((cleaningrobots.Map) worldPart)
					.getFields()) {
				boolean isBlocked = EMFUtils.listContains(modelField
						.getStates(), blockedState);
				Field f = new Field(modelField.getXpos(), modelField.getYpos(), !isBlocked);
				for (cleaningrobots.State modelState : modelField.getStates()) {
					State state = State.createState(modelState.getName());
					f.addState(state);
				}
				core.getWorld().addField(f);
			}
		}
		if (worldPart instanceof cleaningrobots.World) {
			for (WorldPart innerWorldPart : ((cleaningrobots.World) worldPart)
					.getChildren()) {
				importFieldsFromWorldModel(innerWorldPart, core);
			}
		}
	}

	private void importModel(EObject model, RobotCore core) {
		if (model instanceof cleaningrobots.Robot) {
			logger.trace("importing model " + model);
			cleaningrobots.Robot robot = (cleaningrobots.Robot) model;
			cleaningrobots.WorldPart rootWorldPart = robot.getWorld();
			importFieldsFromWorldModel(rootWorldPart, core);
		} else {
			logger.warn("unknown model " + model);
		}
	}
}
