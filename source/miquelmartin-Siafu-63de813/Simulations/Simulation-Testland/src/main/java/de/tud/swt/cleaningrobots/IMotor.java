package de.tud.swt.cleaningrobots;

public interface IMotor {
	public enum Direction {
		NORTH, NORTH_EAST, EAST, SOUTH_EAST, SOUTH, SOUTH_WEST, WEST, NORTH_WEST;
		/*
		 * NORTH (0), NORTH_EAST (1), EAST (2), SOUTH_EAST (3), SOUTH (4),
		 * SOUTH_WEST (5), WEST (6), NORTH_WEST (7);
		 */
	}
}
