package de.tud.swt.cleaningrobots.behaviours;

import java.util.EnumMap;
import java.util.Map;

import de.tud.evaluation.EvaluationConstants;
import de.tud.swt.cleaningrobots.Behaviour;
import de.tud.swt.cleaningrobots.Demand;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.hardware.Components;
import de.tud.swt.cleaningrobots.model.Position;
import de.tud.swt.cleaningrobots.model.State;

public class WipeBehaviour extends Behaviour{

	private final State STATE_HOOVE = State.createState("Hoove");
	private final State STATE_WIPE = State.createState("Wipe");
	
	private final State WORLDSTATE_WIPED = State.createState("Wiped");
	private final State WORLDSTATE_HOOVED = State.createState("Hooved");
	
	private boolean finishWiping;
	private boolean relative;

	public WipeBehaviour(RobotCore robot, boolean relative) {
		super(robot);
		
		this.relative = relative;
		this.finishWiping = false;
		
		Map<Components, Integer> hardware = new EnumMap<Components, Integer> (Components.class);
		//same as in DiscoverBehaviour
		d = new Demand(hardware, robot);
		hardwarecorrect = d.isCorrect();		
	}	
	
	public boolean isFinishWipe () {
		return finishWiping;
	}

	@Override
	public boolean action() throws Exception {
											
		//Prüfe ob Hardwarecorrect oder entferne es vorher schon wieder
		if(getRobot().getDestinationContainer().isAtDestination()){
			
			//if you find more than the value of new field drive back to load station and give information to master
			if (EvaluationConstants.NEW_FIELD_COUNT > 0 && this.getRobot().getWorld().getNewInformationCounter() > EvaluationConstants.NEW_FIELD_COUNT) {
				getRobot().getDestinationContainer().setDestinationLoadStation();
				this.getRobot().getWorld().resetNewInformationCounter();
				return false;
			}
			
			Position nextNotWipePosition;
			//Look if you must use relative or non relative algorithm 
			if (relative)
				nextNotWipePosition = this.getRobot().getWorld().getNextPassableRelativePositionByStateWithoutState(this.getRobot().getDestinationContainer().getLastLoadDestination(), STATE_HOOVE, STATE_WIPE);
			else
				nextNotWipePosition = this.getRobot().getWorld().getNextPassablePositionByStateWithoutState(STATE_HOOVE, STATE_WIPE);
						
			if(nextNotWipePosition != null){
				getRobot().getDestinationContainer().setDestination(nextNotWipePosition);
				
				//wenn accu vorhanden dann muss ladestatus geprüft werden Prüfe,
				//ob ziel vorher erreicht wird oder ob accu beim fahren leer wird
				if (getRobot().getAccu() != null)
				{
					if (getRobot().isLoading)
						return false;
					
					//Entfernung Robot bis Ziel
					int sizeOne = getRobot().getDestinationContainer().getPathFromTo(getRobot().getPosition(), nextNotWipePosition).size();
					//Entfernung Robot bis Ladestation
					//int sizeTwo = getRobot().getPath(getRobot().getPosition(), getRobot().loadStationPosition).size();
					//Entfernung Ziel bis Ladestation
					int sizeThree = getRobot().getDestinationContainer().getPathFromTo(nextNotWipePosition, getRobot().getDestinationContainer().getLoadStationPosition()).size();
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
							System.out.println("Robot erreicht keine Hooveposition mehr obwohl diese noch existiert!");
							finishWiping = true;
							return true;
						}
					}
				}
			} else {
				//no more wipe position found
				if (this.getRobot().getWorld().containsWorldState(WORLDSTATE_HOOVED))
				{
					this.getRobot().getWorld().addWorldState(WORLDSTATE_WIPED);
					//finish back to load station
					if(!getRobot().getPosition().equals(getRobot().getDestinationContainer().getLoadStationPosition()))
					{
						//must drie back to loadstation
						getRobot().getDestinationContainer().setDestinationLoadStation();
					} else {
						//is at loadstation
						finishWiping = true;
						return true;
					}
				} else {
					//not finish wait for new data, drive back to master
					getRobot().getDestinationContainer().setDestinationLoadStation();
				}
			}
		}
		return false;		
	}

}
