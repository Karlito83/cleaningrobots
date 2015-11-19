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
	
	private State STATE_HOOVE;
	private State STATE_FREE;	
	private State WORLDSTATE_DISCOVERED;
	private State WORLDSTATE_HOOVED;
	
	private boolean finishHooving;
	private boolean relative;

	public HooveBehaviour(RobotCore robot, boolean relative) {
		super(robot);
		
		this.STATE_HOOVE = ((State)robot.configuration.as).createState("Hoove");
		this.STATE_FREE = ((State)robot.configuration.as).createState("Free");		
		this.WORLDSTATE_DISCOVERED = ((State)robot.configuration.as).createState("Discovered");
		this.WORLDSTATE_HOOVED = ((State)robot.configuration.as).createState("Hooved");
		
		this.relative = relative;
		this.finishHooving = false;
		
		supportedStates.add(STATE_HOOVE);
		supportedStates.add(STATE_FREE);
		
		Map<Components, Integer> hardware = new EnumMap<Components, Integer> (Components.class);
		//same as in the DiscoverBehaviour		
		d = new Demand(hardware, robot);
		hardwarecorrect = d.isCorrect();		
	}	
	
	public boolean isFinishHoove () {
		return finishHooving;
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
			
			Position nextNotHoovePosition;
			//Look if you must use relative or non relative algorithm 
			if (relative)
				nextNotHoovePosition = this.robot.getWorld().getNextPassablePositionRelativeWithoutState(this.robot.getDestinationContainer().getLastLoadDestination(), STATE_HOOVE);
			else
				nextNotHoovePosition = this.robot.getWorld().getNextPassablePositionWithoutState(STATE_HOOVE); 
			
			if(nextNotHoovePosition != null){
				robot.getDestinationContainer().setDestination(nextNotHoovePosition);
				
				//wenn accu vorhanden dann muss ladestatus geprüft werden Prüfe,
				//ob ziel vorher erreicht wird oder ob accu beim fahren leer wird
				if (robot.getAccu() != null)
				{
					if (robot.isLoading)
						return false;
					
					//Entfernung Robot bis Ziel
					int sizeOne = robot.getDestinationContainer().getPathFromTo(robot.getPosition(), nextNotHoovePosition).size();
					//Entfernung Robot bis Ladestation
					//int sizeTwo = robot.getPath(robot.getPosition(), robot.loadStationPosition).size();
					//Entfernung Ziel bis Ladestation
					int sizeThree = robot.getDestinationContainer().getPathFromTo(nextNotHoovePosition, robot.getDestinationContainer().getLoadStationPosition()).size();
					int size = sizeOne + sizeThree;
					size +=2;
					//Wenn akku bis zu Ziel nicht mehr 
					if (size * robot.getActualEnergie() > robot.getAccu().getRestKWh())
					{
						//Robot schafft Weg nicht also Fahre zurück zu Ladestation
						robot.getDestinationContainer().setDestinationLoadStation();
						//lohnt sich für Robot nicht mehr rauszufahren
						if (robot.getDestinationContainer().getLoadStationPosition().equals(robot.getPosition()))
						{
							System.out.println("Robot erreicht keine Hooveposition mehr obwohl diese noch existiert!");
							finishHooving = true;
							return true;
						}
					}
				}				
			} else {
				//no more hoove position found
				//need no blocked field and proof if the hole world is discovered
				if (this.robot.getWorld().containsWorldState(WORLDSTATE_DISCOVERED))
				{
					this.robot.getWorld().addWorldState(WORLDSTATE_HOOVED);
					//finish back to load station
					if(!robot.getPosition().equals(robot.getDestinationContainer().getLoadStationPosition()))
					{
						//must drive to load station for end
						robot.getDestinationContainer().setDestinationLoadStation();
					} else {
						//is at loadstation
						finishHooving = true;
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
