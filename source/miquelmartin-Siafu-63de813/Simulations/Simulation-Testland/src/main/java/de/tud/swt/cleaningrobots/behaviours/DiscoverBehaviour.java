package de.tud.swt.cleaningrobots.behaviours;

import java.util.EnumMap;
import java.util.Map;

import de.tud.swt.cleaningrobots.Behaviour;
import de.tud.swt.cleaningrobots.Demand;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.hardware.Components;
import de.tud.swt.cleaningrobots.model.Position;
import de.tud.swt.cleaningrobots.model.State;

/**
 * Search next unknown position and drive throw, if the Accu is to low drive to loadstation
 * and if there is no more unknown position drive back to loadstation again
 *  
 * @author ChrissiMobil
 *
 */
public class DiscoverBehaviour extends Behaviour {
	
	private final State STATE_BLOCKED = State.createState("Blocked");
	private final State STATE_FREE = State.createState("Free");
	
	private final State WORLDSTATE_DISCOVERED = State.createState("Discovered");

	private boolean noMoreDiscovering;	
	private boolean relative;
	
	public DiscoverBehaviour(RobotCore robot, boolean relative) {
		super(robot);
		
		this.relative = relative;
		this.noMoreDiscovering = false;
		
		supportedStates.add(STATE_BLOCKED);
		supportedStates.add(STATE_FREE);
		
		Map<Components, Integer> hardware = new EnumMap<Components, Integer> (Components.class);
		//do not add motor because of you only search destination you don't move
		//hardware.put(Components.MOTOR, 1);
		
		d = new Demand(hardware, robot);
		hardwarecorrect = d.isCorrect();		
	}	

	public boolean isFinishDiscovering () {
		return noMoreDiscovering;
	}
	
	@Override
	public boolean action() throws Exception {
		
		//Prüfe ob Hardwarecorrect oder entferne es vorher schon wieder		
		if(robot.getDestinationContainer().isAtDestination()) {
			
			//if you find more than the value of new field drive back to load station and give information to master
			if (this.robot.configuration.new_field_count > 0 && this.robot.getWorld().getNewInformationCounter() > this.robot.configuration.new_field_count) {
				robot.getDestinationContainer().setDestinationLoadStation();
				this.robot.getWorld().resetNewInformationCounter();
				return false;
			}
			
			Position nextUnknownPosition;
			//Look if you must use relative or non relative algorithm 
			if (relative)
				nextUnknownPosition = this.robot.getWorld().getNextUnknownRelativeFieldPosition(this.robot.getDestinationContainer().getLastLoadDestination()); 
			else
				nextUnknownPosition = this.robot.getWorld().getNextUnknownFieldPosition(); 
			
			if(nextUnknownPosition != null){
				robot.getDestinationContainer().setDestination(nextUnknownPosition);
				
				//wenn accu vorhanden dann muss ladestatus geprüft werden Prüfe,
				//ob ziel vorher erreicht wird oder ob accu beim fahren leer wird
				if (robot.getAccu() != null)
				{
					if (robot.isLoading)
						return false;
					
					//Entfernung Robot bis Ziel
					int sizeOne = robot.getDestinationContainer().getPathFromTo(robot.getPosition(), nextUnknownPosition).size();
					//Entfernung Robot bis Ladestation
					//int sizeTwo = robot.getPath(robot.getPosition(), robot.loadStationPosition).size();
					//Entfernung Ziel bis Ladestation
					int sizeThree = robot.getDestinationContainer().getPathFromTo(nextUnknownPosition, robot.getDestinationContainer().getLoadStationPosition()).size();
					int size = sizeOne + sizeThree;
					size +=2;
					//Wenn akku bis zu Ziel nicht mehr 
					if (size * robot.getActualEnergie() > robot.getAccu().getRestKWh())
					{
						//Robot schafft Weg nicht also Fahre zurück zu Ladestation
						robot.getDestinationContainer().setDestinationLoadStation();
						//
						if (robot.getDestinationContainer().getLoadStationPosition().equals(robot.getPosition()))
						{
							System.out.println("Robot erreicht keine Unknownposition mehr obwohl diese noch existiert!");
							noMoreDiscovering = true;
							return true;
						}
					}
				}
			}
			else 
			{
				this.robot.getWorld().addWorldState(WORLDSTATE_DISCOVERED);
				if(!robot.getPosition().equals(robot.getDestinationContainer().getLoadStationPosition()))
				{
					//Ist an Ladestation angekommen muss geladen werden
					robot.getDestinationContainer().setDestinationLoadStation();
				} else {
					//gibt true zurück wenn alles geschafft ist in der Behaviour
					//Keine Unknown Positon mehr und schon an Ladestation
					noMoreDiscovering = true;
					return true;
				}
			}
		}		
		return false;		
	}
}
