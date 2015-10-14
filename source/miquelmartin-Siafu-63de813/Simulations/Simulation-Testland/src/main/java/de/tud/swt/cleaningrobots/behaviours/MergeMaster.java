package de.tud.swt.cleaningrobots.behaviours;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;

import de.tud.swt.cleaningrobots.Behaviour;
import de.tud.swt.cleaningrobots.Demand;
import de.tud.swt.cleaningrobots.MasterRole;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.RobotKnowledge;
import de.tud.swt.cleaningrobots.RobotRole;
import de.tud.swt.cleaningrobots.hardware.Components;
import de.tud.swt.cleaningrobots.hardware.HardwareComponent;
import de.tud.swt.cleaningrobots.hardware.Wlan;
import de.tud.swt.cleaningrobots.merge.MergeAll;
import de.tud.swt.cleaningrobots.util.ImportExportConfiguration;

public class MergeMaster extends Behaviour {
	
	private MasterRole mr;
	private MergeAll ma;
	private Wlan wlan;	
	
	private List<RobotRole> lastChange;
	
	public MergeMaster(RobotCore robot, MasterRole mr) {
		super(robot);
		
		this.mr = mr;
		this.lastChange = new ArrayList<RobotRole>();
		this.ma = new MergeAll();
		
		Map<Components, Integer> hardware = new EnumMap<Components, Integer> (Components.class);
		hardware.put(Components.WLAN, 1);
		
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
		long startTime = System.nanoTime();
		logger.trace("Merge Master Follower");
		
		List<RobotCore> nearRobots = this.getRobot().getICommunicationProvider().getNearRobots(wlan.getVisionRadius());
		nearRobots.remove(this.getRobot());
		
		//if no nearRobots end this behavior
		if (nearRobots.isEmpty())
			return false;
						
		//create the information maps for new robots with new information
		Map<RobotRole, ImportExportConfiguration> nearsNewInformation = new HashMap<RobotRole, ImportExportConfiguration>();
		Map<RobotRole, ImportExportConfiguration> nearsNoNewInformation = new HashMap<RobotRole, ImportExportConfiguration>();
		for (RobotCore nearRobot : nearRobots) {
			//darf nur mi Robotern in der nähe Kommunizieren wenn diese Wirklich die gleiche HardwareComponente habe und diese aktiv ist
			if (nearRobot.hasActiveHardwareComponent(wlan.getComponents())) {
				//darf auch nur das modell von Robotern einfügen die follower dieses Knotens sind
				List<RobotRole> frr = mr.getFollowers();
				//gehe alle Follower dieses Robots durch und prüfe ob einer der hier ist
				for (RobotRole rr : frr)
				{
					if (rr.getRobotCore().equals(nearRobot))
					{
						if (rr.hasNewInformation())
						{
							//set new information to false
							rr.setNewInformation(false);
							
							ma.newInformationMeasure(rr.getRobotCore().getName());
							//Robot say that he has new Information
							//make the config file for export and import
							ImportExportConfiguration config = new ImportExportConfiguration();
							config.world = true;
							config.knownstates = true;
							config.knowledge = true;																		
							//search timestamp of last meeting
							for (RobotKnowledge rk : getRobot().getKnowledge()) {
								if (rk.getName().equals(nearRobot.getName())) {
									config.iteration = rk.getLastArrange();
								}											
							}
								
							//export and Import the Models
							EObject model = nearRobot.exportModel(config);
							ma.importAllModel(model, this.getRobot(), config);
							
							//change the config for later export and import
							for (RobotKnowledge rk : getRobot().getKnowledge()) {
								if (rk.getName().equals(nearRobot.getName())) {
									config.knownStates = rk.getKnownStates();
								}											
							}
							nearsNewInformation.put(rr, config);
						} else {
							ImportExportConfiguration config = new ImportExportConfiguration();
							config.world = true;
							config.knownstates = true;
							config.knowledge = true;
							for (RobotKnowledge rk : getRobot().getKnowledge()) {
								if (rk.getName().equals(nearRobot.getName())) {
									config.iteration = rk.getLastArrange();
									config.knownStates = rk.getKnownStates();
								}											
							}
							nearsNoNewInformation.put(rr, config);								
						}
						break;
					}
				}				
			}
		}
		
		if (nearsNewInformation.isEmpty() && nearsNoNewInformation.isEmpty())
			return false;
		
		boolean newInfoForFollower = false;
		
		//schaue ob der selbe Roboter auch ein Follower ist und neue Informationen hat
		//wenn ja leere lastChange um anderen Robotern neue Daten zu geben
		for (RobotRole rr : mr.getFollowers()) {
			if (rr.getRobotCore().equals(mr.getRobotCore()) && rr.hasNewInformation()) {
				rr.setNewInformation(false);
				newInfoForFollower = true;
			}
		}
		
		//füge gesammeltes Model den nahen Robotern hinzu
		if (!nearsNewInformation.isEmpty())
		{
			mr.setNewInformation(true);
			System.out.println("LastChange: " + lastChange + " NewInfo: " + nearsNewInformation + " NoNewInfo: " + nearsNoNewInformation);
			if (nearsNewInformation.size() == 1)
			{
				//muss model nur importieren wenn noch nicht in lastchange liste war oder er nicht der einzige mit neuen informationen ist
				for (RobotRole rr : nearsNewInformation.keySet()) {
					//fr schon vorher enthalten
					if(!lastChange.contains(rr) || newInfoForFollower)
					{
						EObject model = this.getRobot().exportModel(nearsNewInformation.get(rr));
						ma.importAllModel(model, rr.getRobotCore(), nearsNewInformation.get(rr));
						rr.getRobotCore().getWorld().resetNewInformationCounter();
					}
				}
			} else {
				for (RobotRole rr : nearsNewInformation.keySet()) {
					//importiere allen nahen Robotern das neue Modell
					EObject model = this.getRobot().exportModel(nearsNewInformation.get(rr));
					ma.importAllModel(model, rr.getRobotCore(), nearsNewInformation.get(rr));
					rr.getRobotCore().getWorld().resetNewInformationCounter();
				}
			}
			for (RobotRole rr : nearsNoNewInformation.keySet()) {
				//importiere allen nahen Robotern das neue Modell
				EObject model = this.getRobot().exportModel(nearsNoNewInformation.get(rr));
				ma.importAllModel(model, rr.getRobotCore(), nearsNoNewInformation.get(rr));
				rr.getRobotCore().getWorld().resetNewInformationCounter();
			}	
			lastChange.clear();
			lastChange.addAll(nearsNewInformation.keySet());
			lastChange.addAll(nearsNoNewInformation.keySet());
			System.out.println("LastChange: " + lastChange);
		} else {
			//nearsNoNew kann hier nicht leer sein
			if (newInfoForFollower)
			{
				for (RobotRole rr : nearsNoNewInformation.keySet()) {
					//importiere allen nahen Robotern das neue Modell
					EObject model = this.getRobot().exportModel(nearsNoNewInformation.get(rr));
					ma.importAllModel(model, rr.getRobotCore(), nearsNoNewInformation.get(rr));
					rr.getRobotCore().getWorld().resetNewInformationCounter();
					lastChange.clear();
					lastChange.addAll(nearsNoNewInformation.keySet());
				}
				System.out.println("LastChange: " + lastChange);
			}
			//TODO:
			//fall wen roboter ankommt keine neue information hat aber für ihn neue informationen da sind
		}
		
		
		long endTime = System.nanoTime();
		logger.info("Importing the data from " + nearRobots.size() + " other agents took " + (endTime - startTime) + " ns.");
		
		logger.trace("exit getNearRobotsAndImportModel");
		return false;
	}	
}
