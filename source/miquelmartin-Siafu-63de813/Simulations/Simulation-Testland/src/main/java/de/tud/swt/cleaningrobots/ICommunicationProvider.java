package de.tud.swt.cleaningrobots;

import java.util.List;

public interface ICommunicationProvider {

	List<RobotCore> getNearRobots(int visionRadius);
	List<RobotCore> getAllRobots();

}
