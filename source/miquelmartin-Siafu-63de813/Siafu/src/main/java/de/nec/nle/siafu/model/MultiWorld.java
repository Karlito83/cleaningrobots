package de.nec.nle.siafu.model;

import java.io.InputStream;
import java.util.List;

import org.eclipse.swt.graphics.ImageData;

import de.nec.nle.siafu.behaviormodels.BaseAgentModelMulti;
import de.tud.evaluation.WorkingConfiguration;

public class MultiWorld {
	
	/** The white color. Used to identify walls. */
	private int COLOR_WHITE = 0xFFFFFF;
	
	/**
	 * The data that defines this simulation, including maps, sprites and
	 * behavioral classes.
	 */
	private SimulationData simData;
	
	/**
	 * The world's height.
	 */
	private int height;

	/**
	 * The worlds width.
	 */
	private int width;
	
	/**
	 * The matrix of points that defines where an agent can walk or not.
	 */
	private boolean[][] walls;
	
	private List<MultiAgent> people;
	
	private WorkingConfiguration configuration;
	
	private BaseAgentModelMulti modell;
	
	/**
	 * Instantiate the world in which the simulation will run.
	 * 
	 * @param simulation
	 *            the simulation object which is running this world.
	 * @param simData
	 *            the simulation data (maps, sprites, classes) for this
	 *            simulation.
	 */
	public MultiWorld(SimulationData simData, WorkingConfiguration configuration) {
		this.configuration = configuration;
		this.simData = simData;
		simData.getConfigFile();
		
		buildWalls();
		createPeople();
	}
	
	/**
	 * Create the people to simulate by asking the AgentModel to do so.
	 * 
	 */
	private void createPeople() {
		try {
			modell = (BaseAgentModelMulti) simData.getMultiAgentModelClass()
					.getConstructor(new Class[] { this.getClass() , configuration.getClass() })
					.newInstance(new Object[] { this , configuration });
		} catch (Exception e) {
			throw new RuntimeException("Can't instantiate the agent model", e);
		}

		people = modell.createAgents();
	}
	
	public List<MultiAgent> getPeople() {
		return people;
	}
	
	public void wander() {
		modell.doIteration(people);
	}
	
	public BaseAgentModelMulti getAgentModel() {
		return modell;
	}
	
	/**
	 * Generate a matrix with the world's walls, out of the image file provided
	 * in the simulation data.
	 * 
	 */
	private void buildWalls() {
		InputStream wallsIS = simData.getWallsFile();
		ImageData img = new ImageData(wallsIS);
		height = img.height;
		width = img.width;

		walls = new boolean[height][width];

		for (int i = 0; i < height; i++) {
			int[] colors = new int[width];
			img.getPixels(0, i, width, colors, 0);

			for (int j = 0; j < width; j++) {
				walls[i][j] = (colors[j] == COLOR_WHITE);
			}
		}
	}
	
	/**
	 * Find out if the given position corresponds to a wall, or is actually
	 * walkable by agents.
	 * 
	 * @param pos
	 *            the position to check
	 * @return true if the position is on a wall, false otherwise
	 */
	public boolean isAWall(int row, int col) {
		return walls[row][col];
	}

}
