package de.tud.swt.cleaningrobots.behaviours;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import de.tud.swt.cleaningrobots.Behaviour;
import de.tud.swt.cleaningrobots.Demand;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.hardware.Components;
import de.tud.swt.cleaningrobots.hardware.HardwareComponent;
import de.tud.swt.cleaningrobots.hardware.LoadStation;

public class LoadBehaviour extends Behaviour {

	private LoadStation loadStation;
	
	public LoadBehaviour(RobotCore robot) {
		super(robot);

		Map<Components, Integer> hardware = new EnumMap<Components, Integer> (Components.class);
		hardware.put(Components.LOADSTATION, 1);
		
		d = new Demand(hardware, robot);
		hardwarecorrect = d.isCorrect();
		
		//Vision Radius aus Wlan Hardwarecomponente ziehen
		for (HardwareComponent hard : d.getHcs())
		{
			if (hard.getComponents() == Components.LOADSTATION)
			{
				loadStation = (LoadStation)hard;
			}
		}
	}

	@Override
	public boolean action() throws Exception {

		List<RobotCore> nearRobots = this.robot.getICommunicationAdapter().getNearRobots(loadStation.getLoadRadius());
		nearRobots.remove(this.robot);
		for (RobotCore nearRobot : nearRobots) {
			if (nearRobot.getAccu() != null)
			{
				if (nearRobot.isLoading)
					nearRobot.getAccu().load(loadStation.getLoadValue());
				//wenn der akku weniger als halbleer ist soll er laden
				if (!nearRobot.isLoading && nearRobot.getAccu().getActualKWh() < nearRobot.getAccu().getMaxKWh()/2)
					nearRobot.isLoading = true;
				//wenn der akku voll ist soll er nicht mehr laden
				if (nearRobot.getAccu().isFull()) {
					System.out.println("Accu Full");
					nearRobot.isLoading = false;
				}
			}
		}
		
		return false;
	}

}
