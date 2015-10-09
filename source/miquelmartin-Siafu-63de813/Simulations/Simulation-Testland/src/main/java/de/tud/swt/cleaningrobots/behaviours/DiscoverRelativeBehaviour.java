package de.tud.swt.cleaningrobots.behaviours;

import java.util.EnumMap;
import java.util.Map;

import de.tud.swt.cleaningrobots.Behaviour;
import de.tud.swt.cleaningrobots.Demand;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.hardware.Components;
import de.tud.swt.cleaningrobots.model.Position;
import de.tud.swt.cleaningrobots.model.State;

public class DiscoverRelativeBehaviour extends Behaviour {
	
	private final State STATE_BLOCKED = State.createState("Blocked");
	private final State STATE_FREE = State.createState("Free");
	
	private final State WORLDSTATE_DISCOVERED = State.createState("Discovered");

	private boolean noMoreDiscovering;
	
	public DiscoverRelativeBehaviour(RobotCore robot) {
		super(robot);
		
		supportedStates.add(STATE_BLOCKED);
		supportedStates.add(STATE_FREE);
		
		Map<Components, Integer> hardware = new EnumMap<Components, Integer> (Components.class);
		//dont add motor because of you online search destination you don't move
		//hardware.put(Components.MOTOR, 1);
		
		d = new Demand(hardware, robot);
		hardwarecorrect = d.isCorrect();
		
		noMoreDiscovering = false;
	}	

	public boolean isFinishDiscovering () {
		return noMoreDiscovering;
	}
	
	@Override
	public boolean action() throws Exception {
		
		//Prüfe ob Hardwarecorrect oder entferne es vorher schon wieder
		logger.trace("Entered DiscoverRelativeBehaviour.action().");
		
		if(getRobot().getDestinationContainer().isAtDestination()) {
			
			Position nextUnknownPosition = this.getRobot().getWorld().getNextUnknownRelativeFieldPosition(this.getRobot().getDestinationContainer().getLastLoadDestination()); 
						
			if(nextUnknownPosition != null){
				getRobot().getDestinationContainer().setDestination(nextUnknownPosition);
				
				//wenn accu vorhanden dann muss ladestatus geprüft werden Prüfe,
				//ob ziel vorher erreicht wird oder ob accu beim fahren leer wird
				if (getRobot().getAccu() != null)
				{
					if (getRobot().isLoading)
						return false;
					
					//Entfernung Robot bis Ziel
					int sizeOne = getRobot().getDestinationContainer().getPathFromTo(getRobot().getPosition(), nextUnknownPosition).size();
					//Entfernung Robot bis Ladestation
					//int sizeTwo = getRobot().getPath(getRobot().getPosition(), getRobot().loadStationPosition).size();
					//Entfernung Ziel bis Ladestation
					int sizeThree = getRobot().getDestinationContainer().getPathFromTo(nextUnknownPosition, getRobot().getDestinationContainer().getLoadStationPosition()).size();
					int size = sizeOne + sizeThree;
					size +=2;
					//Wenn akku bis zu Ziel nicht mehr 
					if (size * getRobot().getActualEnergie() > getRobot().getAccu().getRestKWh())
					{
						//Robot schafft Weg nicht also Fahre zurück zu Ladestation
						getRobot().getDestinationContainer().setDestinationLoadStation();
						//
						if (getRobot().getDestinationContainer().getLoadStationPosition().equals(getRobot().getPosition()))
						{
							System.out.println("Robot erreicht keine Unknownposition mehr obwohl diese noch existiert!");
							noMoreDiscovering = true;
							return true;
						}
					}
				}
				logger.info("Executed DiscoverBehaviour.action().");
			}
			else 
			{
				this.getRobot().getWorld().addWorldState(WORLDSTATE_DISCOVERED);
				if(!getRobot().getPosition().equals(getRobot().getDestinationContainer().getLoadStationPosition()))
				{
					//Ist an Ladestation angekommen muss geladen werden
					getRobot().getDestinationContainer().setDestinationLoadStation();
				} else {
					//gibt true zurück wenn alles geschafft ist in der Behaviour
					//Keine Unknown Positon mehr und schon an Ladestation
					noMoreDiscovering = true;
					return true;
				}
			}
		}
		
		logger.trace("Ended DiscoverBehaviour.action().");
		
		return false;		
	}

}
