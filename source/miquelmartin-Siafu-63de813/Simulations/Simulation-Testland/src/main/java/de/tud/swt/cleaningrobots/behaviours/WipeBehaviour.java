package de.tud.swt.cleaningrobots.behaviours;

import java.util.EnumMap;
import java.util.Map;

import de.tud.swt.cleaningrobots.Behaviour;
import de.tud.swt.cleaningrobots.Demand;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.hardware.Components;
import de.tud.swt.cleaningrobots.model.Position;
import de.tud.swt.cleaningrobots.model.State;

public class WipeBehaviour extends Behaviour{

	private final State STATE_HOOVE = State.createState("Hoove");
	private final State STATE_WIPE = State.createState("Wipe");
	
	private boolean finishWiping;

	public WipeBehaviour(RobotCore robot) {
		super(robot);
		
		Map<Components, Integer> hardware = new EnumMap<Components, Integer> (Components.class);
		//same as in DiscoverBehaviour

		d = new Demand(hardware, robot);
		hardwarecorrect = d.isCorrect();
		
		finishWiping = false;
	}	
	
	public boolean isFinishWipe () {
		return finishWiping;
	}

	@Override
	public boolean action() throws Exception {
											
		//Prüfe ob Hardwarecorrect oder entferne es vorher schon wieder
		logger.trace("Entered HooveBehaviour.action().");
		
		if(getRobot().isAtDestination()){
			
			Position nextNotWipePosition = this.getRobot().getWorld().getNextFieldByStateWithoutState(STATE_HOOVE, STATE_WIPE);
						
			if(nextNotWipePosition != null){
				getRobot().setDestination(nextNotWipePosition);
				
				//wenn accu vorhanden dann muss ladestatus geprüft werden Prüfe,
				//ob ziel vorher erreicht wird oder ob accu beim fahren leer wird
				if (getRobot().getAccu() != null)
				{
					if (getRobot().isLoading)
						return false;
					
					//Entfernung Robot bis Ziel
					int sizeOne = getRobot().getPath(getRobot().getPosition(), nextNotWipePosition).size();
					//Entfernung Robot bis Ladestation
					//int sizeTwo = getRobot().getPath(getRobot().getPosition(), getRobot().loadStationPosition).size();
					//Entfernung Ziel bis Ladestation
					int sizeThree = getRobot().getPath(nextNotWipePosition, getRobot().loadStationPosition).size();
					int size = sizeOne + sizeThree;
					size +=2;
					//Wenn akku bis zu Ziel nicht mehr 
					if (size * getRobot().getActualEnergie() > getRobot().getAccu().getRestKWh())
					{
						//Robot schafft Weg nicht also Fahre zurück zu Ladestation
						getRobot().setDestinationLoadStation();
						//
						if (getRobot().loadStationPosition.equals(getRobot().getPosition()))
						{
							System.out.println("Robot erreicht keine Hooveposition mehr obwohl diese noch existiert!");
							finishWiping = true;
							return true;
						}
					}
				}
				logger.info("Executed DiscoverBehaviour.action().");
			} else {
				//no more wipe position found
				Position nextNotHoovePosition = this.getRobot().getWorld().getNextFieldByState(STATE_HOOVE); 
				Position nextUnknownPosition = this.getRobot().getWorld().getNextUnknownFieldPosition();
				if (nextUnknownPosition == null && nextNotHoovePosition == null)
				{
					//finish back to load station
					if(!getRobot().getDestination().equals(getRobot().loadStationPosition))
					{
						//Ist an Ladestation angekommen muss geladen werden
						getRobot().setDestinationLoadStation();
					} else {
						//is at loadstation
						finishWiping = true;
						return true;
					}
				} else {
					//not finish wait for new data, drive back to master
					getRobot().setDestinationLoadStation();
					return false;
				}
			}
		}
		
		logger.trace("Ended HooveBehaviour.action().");
		
		return false;		
	}

}
