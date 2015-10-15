package de.tud.swt.cleaningrobots.behaviours;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;

import de.tud.swt.cleaningrobots.RobotKnowledge;
import de.tud.swt.cleaningrobots.Behaviour;
import de.tud.swt.cleaningrobots.Demand;
import de.tud.swt.cleaningrobots.MasterRole;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.RobotRole;
import de.tud.swt.cleaningrobots.hardware.Components;
import de.tud.swt.cleaningrobots.hardware.HardwareComponent;
import de.tud.swt.cleaningrobots.hardware.Wlan;
import de.tud.swt.cleaningrobots.merge.MergeAll;
import de.tud.swt.cleaningrobots.util.ImportExportConfiguration;

public class MergeMasterFollower extends Behaviour {
	
	private MergeAll ma;
	private Wlan wlan;
		
	private List<RobotRole> lastChange;
	
	public MergeMasterFollower(RobotCore robot) {
		super(robot);
		
		lastChange = new ArrayList<RobotRole>();
		ma = new MergeAll();
		
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
				
		List<RobotCore> nearRobots = this.getRobot().getICommunicationProvider().getNearRobots(wlan.getVisionRadius());
		nearRobots.remove(this.getRobot());
		
		//if no nearRobots end this behavior
		if (nearRobots.isEmpty())
			return false;
		
		Map<RobotRole, ImportExportConfiguration> nearsNewInformation = new HashMap<RobotRole, ImportExportConfiguration>();
		Map<RobotRole, ImportExportConfiguration> nearsNoNewInformation = new HashMap<RobotRole, ImportExportConfiguration>();
		for (RobotCore nearRobot : nearRobots) {
			//darf nur mi Robotern in der nähe Kommunizieren wenn diese Wirklich die gleiche HardwareComponente habe und diese aktiv ist
			if (nearRobot.hasActiveHardwareComponent(wlan.getComponents())) {
				//darf auch nur das modell von Robotern einfügen die follower dieses Knotens sind
				List<RobotRole> lrr = getRobot().getRoles();
				for (RobotRole rr : lrr)
				{
					if (rr instanceof MasterRole)
					{
						List<RobotRole> frr = ((MasterRole)rr).getFollowers();
						//gehe alle Follower dieses Robots durch und prüfe ob einer der hier ist
						for (RobotRole fr : frr)
						{
							if (fr.getRobotCore().equals(nearRobot))
							{
								if (fr.hasNewInformation())
								{
									//set new information to false
									fr.setNewInformation(false);
									
									ma.newInformationMeasure(fr.getRobotCore().getName());
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
											System.out.println(rk.getName() + " RK KnownStates: " + rk.getKnownStates());
											config.knownStates = rk.getKnownStates();
										}											
									}
									nearsNewInformation.put(fr, config);
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
									nearsNoNewInformation.put(fr, config);								
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
			//System.out.println("LastChange: " + lastChange + " NewInfo: " + nearsNewInformation + " NoNewInfo: " + nearsNoNewInformation);
			if (nearsNewInformation.size() == 1)
			{
				//muss model nur importieren wenn noch nicht in lastchange liste war oder er nicht der einzige mit neuen informationen ist
				for (RobotRole fr : nearsNewInformation.keySet()) {
					//fr schon vorher enthalten
					if(!lastChange.contains(fr))
					{
						EObject model = this.getRobot().exportModel(nearsNewInformation.get(fr));
						ma.importAllModel(model, fr.getRobotCore(), nearsNewInformation.get(fr));
					}
				}
			} else {
				for (RobotRole fr : nearsNewInformation.keySet()) {
					//importiere allen nahen Robotern das neue Modell
					EObject model = this.getRobot().exportModel(nearsNewInformation.get(fr));
					ma.importAllModel(model, fr.getRobotCore(), nearsNewInformation.get(fr));
				}
			}
			for (RobotRole fr : nearsNoNewInformation.keySet()) {
				//importiere allen nahen Robotern das neue Modell
				EObject model = this.getRobot().exportModel(nearsNoNewInformation.get(fr));
				ma.importAllModel(model, fr.getRobotCore(), nearsNoNewInformation.get(fr));
			}	
			lastChange.clear();
			lastChange.addAll(nearsNewInformation.keySet());
			lastChange.addAll(nearsNoNewInformation.keySet());
			System.out.println("LastChange: " + lastChange);
		}
		
		return false;
	}	
}
