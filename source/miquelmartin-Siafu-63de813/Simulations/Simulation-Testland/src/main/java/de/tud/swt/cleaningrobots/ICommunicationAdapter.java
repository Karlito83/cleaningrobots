package de.tud.swt.cleaningrobots;

import java.util.List;

import de.tud.swt.cleaningrobots.model.Position;

public interface ICommunicationAdapter {

	public void setPosition(Position position);
	public boolean isWall(int row, int col);
	public Position getPosition();
	public List<RobotCore> getNearRobots(int visionRadius);
	public List<RobotCore> getAllRobots();
}
