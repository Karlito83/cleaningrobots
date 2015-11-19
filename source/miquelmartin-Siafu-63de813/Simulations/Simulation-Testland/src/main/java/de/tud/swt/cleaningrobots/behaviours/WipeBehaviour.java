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

	private State STATE_HOOVE;
	private State STATE_WIPE;
	
	private State WORLDSTATE_WIPED;
	private State WORLDSTATE_HOOVED;
	
	private boolean finishWiping;
	private boolean relative;

	public WipeBehaviour(RobotCore robot, boolean relative) {
		super(robot);
		
		this.STATE_HOOVE = ((State)robot.configuration.as).createState("Hoove");
		this.STATE_WIPE = ((State)robot.configuration.as).createState("Wipe");		
		this.WORLDSTATE_WIPED = ((State)robot.configuration.as).createState("Wiped");
		this.WORLDSTATE_HOOVED = ((State)robot.configuration.as).createState("Hooved");
		
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
		if(robot.getDestinationContainer().isAtDestination()){
			
			//if you find more than the value of new field drive back to load station and give information to master
			if (this.robot.configuration.new_field_count > 0 && this.robot.getWorld().getNewInformationCounter() > this.robot.configuration.new_field_count) {
				robot.getDestinationContainer().setDestinationLoadStation();
				this.robot.getWorld().resetNewInformationCounter();
				return false;
			}
			
			Position nextNotWipePosition;
			//Look if you must use relative or non relative algorithm 
			if (relative)
				nextNotWipePosition = this.robot.getWorld().getNextPassableRelativePositionByStateWithoutState(this.robot.getDestinationContainer().getLastLoadDestination(), STATE_HOOVE, STATE_WIPE);
			else
				nextNotWipePosition = this.robot.getWorld().getNextPassablePositionByStateWithoutState(STATE_HOOVE, STATE_WIPE);
						
			if(nextNotWipePosition != null){
				robot.getDestinationContainer().setDestination(nextNotWipePosition);
				
				//wenn accu vorhanden dann muss ladestatus geprüft werden Prüfe,
				//ob ziel vorher erreicht wird oder ob accu beim fahren leer wird
				if (robot.getAccu() != null)
				{
					if (robot.isLoading)
						return false;
					
					//Entfernung Robot bis Ziel
					int sizeOne = robot.getDestinationContainer().getPathFromTo(robot.getPosition(), nextNotWipePosition).size();
					//Entfernung Robot bis Ladestation
					//int sizeTwo = robot.getPath(robot.getPosition(), robot.loadStationPosition).size();
					//Entfernung Ziel bis Ladestation
					int sizeThree = robot.getDestinationContainer().getPathFromTo(nextNotWipePosition, robot.getDestinationContainer().getLoadStationPosition()).size();
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
							System.out.println("Robot erreicht keine Hooveposition mehr obwohl diese noch existiert!");
							finishWiping = true;
							return true;
						}
					}
				}
			} else {
				//no more wipe position found
				if (this.robot.getWorld().containsWorldState(WORLDSTATE_HOOVED))
				{
					this.robot.getWorld().addWorldState(WORLDSTATE_WIPED);
					//finish back to load station
					if(!robot.getPosition().equals(robot.getDestinationContainer().getLoadStationPosition()))
					{
						//must drie back to loadstation
						robot.getDestinationContainer().setDestinationLoadStation();
					} else {
						//is at loadstation
						finishWiping = true;
						return true;
					}
				} else {
					//not finish wait for new data, drive back to master
					robot.getDestinationContainer().setDestinationLoadStation();
				}
			}
		}
		return false;		
	}

}
