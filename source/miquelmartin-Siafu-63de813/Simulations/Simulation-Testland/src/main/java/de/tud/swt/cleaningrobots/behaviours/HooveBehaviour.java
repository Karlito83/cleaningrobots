package de.tud.swt.cleaningrobots.behaviours;

import java.util.EnumMap;
import java.util.Map;

import de.tud.swt.cleaningrobots.Behaviour;
import de.tud.swt.cleaningrobots.Demand;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.hardware.Components;
import de.tud.swt.cleaningrobots.model.Position;
import de.tud.swt.cleaningrobots.model.State;

public class HooveBehaviour extends Behaviour {
	
	private final State STATE_HOOVE = State.createState("Hoove");
	private final State STATE_FREE = State.createState("Free");
	
	private final State WORLDSTATE_DISCOVERED = State.createState("Discovered");
	private final State WORLDSTATE_HOOVED = State.createState("Hooved");
	
	private boolean finishHooving;

	public HooveBehaviour(RobotCore robot) {
		super(robot);
		
		supportedStates.add(STATE_HOOVE);
		supportedStates.add(STATE_FREE);
		
		Map<Components, Integer> hardware = new EnumMap<Components, Integer> (Components.class);
		//same as in the DiscoverBehaviour
		
		d = new Demand(hardware, robot);
		hardwarecorrect = d.isCorrect();
		
		finishHooving = false;
	}	
	
	public boolean isFinishHoove () {
		return finishHooving;
	}

	@Override
	public boolean action() throws Exception {
											
		//Prüfe ob Hardwarecorrect oder entferne es vorher schon wieder
		logger.trace("Entered HooveBehaviour.action().");
		
		if(getRobot().getDestinationContainer().isAtDestination()){
			//System.out.println("Hoove at Destination");
			//long startTime = System.nanoTime();
			Position nextNotHoovePosition = this.getRobot().getWorld().getNextPassablePositionWithoutState(STATE_HOOVE); 
			//long endTime = System.nanoTime();
			//System.out.println("NextNotHoovePosition: " + (endTime - startTime));
			//System.out.println("NextNotHoovePosition: " + nextNotHoovePosition);			
			if(nextNotHoovePosition != null){
				getRobot().getDestinationContainer().setDestination(nextNotHoovePosition);
				
				//wenn accu vorhanden dann muss ladestatus geprüft werden Prüfe,
				//ob ziel vorher erreicht wird oder ob accu beim fahren leer wird
				if (getRobot().getAccu() != null)
				{
					if (getRobot().isLoading)
						return false;
					
					//Entfernung Robot bis Ziel
					int sizeOne = getRobot().getDestinationContainer().getPathFromTo(getRobot().getPosition(), nextNotHoovePosition).size();
					//Entfernung Robot bis Ladestation
					//int sizeTwo = getRobot().getPath(getRobot().getPosition(), getRobot().loadStationPosition).size();
					//Entfernung Ziel bis Ladestation
					int sizeThree = getRobot().getDestinationContainer().getPathFromTo(nextNotHoovePosition, getRobot().getDestinationContainer().getLoadStationPosition()).size();
					int size = sizeOne + sizeThree;
					size +=2;
					//Wenn akku bis zu Ziel nicht mehr 
					if (size * getRobot().getActualEnergie() > getRobot().getAccu().getRestKWh())
					{
						//Robot schafft Weg nicht also Fahre zurück zu Ladestation
						getRobot().getDestinationContainer().setDestinationLoadStation();
						//lohnt sich für Robot nicht mehr rauszufahren
						if (getRobot().getDestinationContainer().getLoadStationPosition().equals(getRobot().getPosition()))
						{
							System.out.println("Robot erreicht keine Hooveposition mehr obwohl diese noch existiert!");
							finishHooving = true;
							return true;
						}
					}
				}				
				logger.info("Executed DiscoverBehaviour.action().");
			} else {
				//no more hoove position found
				//need no blocked field and proof if the hole world is discovered
				boolean discovered = this.getRobot().getWorld().containsWorldState(WORLDSTATE_DISCOVERED);
				if (discovered)
				{
					this.getRobot().getWorld().addWorldState(WORLDSTATE_HOOVED);
					//finish back to load station
					if(!getRobot().getDestinationContainer().getDestination().equals(getRobot().getDestinationContainer().getLoadStationPosition()))
					{
						//Ist an Ladestation angekommen muss geladen werden
						getRobot().getDestinationContainer().setDestinationLoadStation();
					} else {
						//is at loadstation
						finishHooving = true;
						return true;
					}
				} else {
					getRobot().getDestinationContainer().setDestinationLoadStation();
					return false;
				}
			}
		}
		
		logger.trace("Ended HooveBehaviour.action().");
		
		return false;		
	}

}
