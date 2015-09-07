package de.tud.swt.cleaningrobots.behaviours;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.tud.swt.cleaningrobots.Behaviour;
import de.tud.swt.cleaningrobots.Demand;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.hardware.Components;
import de.tud.swt.cleaningrobots.hardware.HardwareComponent;
import de.tud.swt.cleaningrobots.hardware.Wlan;
import de.tud.swt.cleaningrobots.model.RobotDestinationCalculation;

public class MasterDestinationExplore extends Behaviour {

	private Wlan wlan;
	private boolean firstStart;
	private int calculationAway;
	
	private Map<String, RobotDestinationCalculation> information;
	
	//private int numberExploreRobots;
	
	public MasterDestinationExplore(RobotCore robot) {
		super(robot);
		
		Map<Components, Integer> hardware = new EnumMap<Components, Integer> (Components.class);
		hardware.put(Components.WLAN, 1);
		
		information = new HashMap<String, RobotDestinationCalculation>();
		
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
		
		firstStart = true;
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
		
		if (firstStart)
		{
			double maxAway = 0;
			//Suche Explore Robots in der nähe einmal ausführen
			List<RobotCore> nearRobots = this.getRobot().getICommunicationProvider().getNearRobots(wlan.getVisionRadius());
			nearRobots.remove(this.getRobot());
			
			//int counter = 0;
			
			for (RobotCore nearRobot : nearRobots) {
				if (nearRobot.hasHardwareComponent(Components.WLAN) && nearRobot.hasHardwareComponent(Components.LOOKAROUNDSENSOR))
				{
					//counter++;
					//add Robot to Map
					information.put(nearRobot.getName(), new RobotDestinationCalculation(nearRobot.getName()));
					//double away = Math.sqrt(nearRobot.getAccu().getMaxFieldGoes(nearRobot.getMaxEnergie() - nearRobot.getMinEnergie()));
					double away = Math.sqrt(nearRobot.getAccu().getMaxFieldGoes(nearRobot.getMinEnergie()));
					//double away2 = Math.sqrt(nearRobot.getAccu().getMaxFieldGoes(nearRobot.getMaxEnergie()));
					//System.out.println("Away: " + away + " " + away1 + " " + away2);
					if (maxAway < away)
						maxAway = away;
				}
			}
			
			calculationAway = (int) maxAway;
			System.out.println("Information: " + information.keySet() + " Away: " + calculationAway + " maxAway: " + maxAway);
			
			//TODO: Prüfe ob der Robot auch Follower dieses Masters ist
			//numberExploreRobots = counter;
			firstStart = false;
		}
				
		//Suche Explore Robots in der nähe einmal ausführen
		List<RobotCore> nearRobots = this.getRobot().getICommunicationProvider().getNearRobots(wlan.getVisionRadius());
		nearRobots.remove(this.getRobot());
				
		for (RobotDestinationCalculation rdc : information.values()) {
			//alle NeedNew auf false setzen und new und old dest tauschen wenn nicht mehr in Reichweite
			rdc.needNew = false;
			if (rdc.newDest != null)
			{
				boolean change = true;
				//schaue ob noch in nearRobots
				for (RobotCore nearRobot : nearRobots) 
				{
					if (nearRobot.getName().equals(rdc.getName()))
					{
						//Roboter noch in Reichweite also nicht umsetzen
						change = false;						
					}
				}
				if (change)
				{
					//setze newDest zurück auf null und erneuere oldDest
					rdc.oldDest = rdc.newDest;
					rdc.newDest = null;
				}
			}
		}
		
		//if no nearRobots end this behavior
		if (nearRobots.isEmpty())
			return false;

		//such Roboter in Reichweite die noch keine neueDest haben und setze Variable
		//System.out.println("NearRobots: " + nearRobots.size());
		//List<FollowerRole> nearsNewInformation = new ArrayList<FollowerRole>();
		//List<FollowerRole> nearsNoNewInformation = new ArrayList<FollowerRole>();
		
		boolean newOneFind = false;
		
		//System.out.println("Robots in Tausch reichweite: " + nearRobots.size());
		for (RobotCore nearRobot : nearRobots) {
			//darf nur mi Robotern in der nähe Kommunizieren wenn diese Wirklich die gleiche HardwareComponente habe und diese aktiv ist
			if (nearRobot.hasActiveHardwareComponent(wlan.getComponents()))// && nearRobot.hasHardwareComponent(Components.LOOKAROUNDSENSOR)) 
			{
				//laufe Values durch und suche gleichen Roboter
				for (RobotDestinationCalculation rdc : information.values()) {
					if (nearRobot.getName().equals(rdc.getName())) 
					{
						if (rdc.newDest == null)
						{
							newOneFind = true;
							rdc.needNew = true;
						}
					}
				}
			}
		}
		
		//vielleicht beachten ob altes ziel noch unknown felder runterrum hat?
		
		if (!newOneFind)
			return false;
		
		information = this.getRobot().getWorld().getNextUnknownFields(information, calculationAway); 
		
		//TODO: warte Funktion und auslesen der neu berechneten Werte
		
		//neue Informationen noch raussenden
		for (RobotCore nearRobot : nearRobots) {
			for (RobotDestinationCalculation rdc : information.values()) {
				if (rdc.getName().equals(nearRobot.getName()) && rdc.needNew)
				{
					//TODO: Datenübertragung messen
					nearRobot.getDestinationContainer().setMasterDestination(rdc.newDest);
				}
			}
		}
		return false;
	}

}
