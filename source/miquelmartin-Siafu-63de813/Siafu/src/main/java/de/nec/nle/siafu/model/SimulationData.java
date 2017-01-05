/*
 * Copyright NEC Europe Ltd. 2006-2007
 * 
 * This file is part of the context simulator called Siafu.
 * 
 * Siafu is free software; you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 * 
 * Siafu is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along
 * with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package de.nec.nle.siafu.model;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.eclipse.swt.graphics.ImageData;

import de.nec.nle.siafu.behaviormodels.BaseAgentModel;
import de.nec.nle.siafu.behaviormodels.BaseAgentModelMulti;
import de.nec.nle.siafu.behaviormodels.BaseContextModel;
import de.nec.nle.siafu.behaviormodels.BaseWorldModel;

/**
 * The subclasses of SimulationData encapsulate the simulation information,
 * including the agent, world and context models, the configuration file, the
 * image maps and the sprites. They provide an easy interface to retreive all
 * the information.
 * 
 * In order to get your SimulationData object, get an instance of the subclass
 * by passing the path string to the getInstance method. Currently, only a
 * folder or a jar file is supported. TODO:Document the jar/folder structure
 * 
 * @author Miquel Martin
 * 
 */
public abstract class SimulationData {

	/** Path to overlay files, which define context variables on the map. */
	public static final String OVERLAYS_PATH = "overlays";

	/**
	 * Path to place files, which indicate the positions where a place of a
	 * certain type should be placed.
	 */
	public static final String PLACES_PATH = "places";
	public static final String FACULTY_PLACES_PATH = "places/fakulty";

	/**
	 * Path to the walls image of the simulation data.
	 */
	public static final String WALLS_FILE = "map/walls.png";
	public static final String WALLS_FILE_LAB = "map/wallsLab.png";
	public static final String WALLS_FILE_FAKK = "map/wallsFakK.png";
	public static final String WALLS_FILE_FAK = "map/wallsFak.png";

	/**
	 * Path to the backgroundimage of the simulation data.
	 */
	public static final String BACKGROUND_FILE = "map/background.png";
	public static final String BACKGROUND_FILE_LAB = "map/backgroundLab.png";
	public static final String BACKGROUND_FILE_FAKK = "map/backgroundFakK.png";
	public static final String BACKGROUND_FILE_FAK = "map/backgroundFak.png";

	// FIXME: I'd like all the images to be png

	/**
	 * Path to the simulation configuration.
	 */
	public static final String CONFIG_FILE = "config.xml";

	/**
	 * The class loader used to include the simulation behavior classes into
	 * the classpath.
	 */
	protected URLClassLoader classLoader;

	/**
	 * The simulation configuration.
	 */
	private XMLConfiguration simulationConfig;

	/**
	 * Get an instance of the right subclass of SimulationData by taking the
	 * path to the simulation data as parameter.
	 * 
	 * @param pathString the absolute path to the simulation data, currently,
	 *            a jar file or a folder
	 * 
	 * @return a SimulationData instance of the right subclass
	 */
	public static SimulationData getInstance(final String pathString) {
		File givenPath = new File(pathString);
		if (!givenPath.exists()) {
			throw new RuntimeException("The simulation data at "
					+ pathString + " does not exist");
		}
		if (givenPath.isDirectory()) {
			return new DirectorySimulationData(givenPath);
		} else if (pathString.endsWith(".jar")) {
			return new JarSimulationData(givenPath);
		} else {
			throw new RuntimeException("Invalid simulation data ("
					+ pathString + "). Please provide a supported type "
					+ "(currently a folder or jar file");
		}
	}

	/**
	 * Instantiates a SimulationData object using the provided path.
	 * 
	 * @param givenPath the path to the data
	 */
	protected SimulationData(final File givenPath) {
		try {
			classLoader = new URLClassLoader(new URL[] {givenPath.toURI().toURL()});
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Get the AgentModel using the class loader set to the simulation data
	 * path.
	 * 
	 * @return the AgentModel for this simulation
	 */
	public Class<? extends BaseAgentModel> getAgentModelClass() {
		try {
			return Class.forName(simulationConfig
					.getString("models.agentmodel"), true, classLoader).asSubclass(BaseAgentModel.class);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Get the AgentModel using the class loader set to the simulation data
	 * path.
	 * 
	 * @return the AgentModel for this simulation
	 */
	public Class<? extends BaseAgentModelMulti> getMultiAgentModelClass() {
		try {
			return Class.forName(simulationConfig
					.getString("models.multiagentmodel"), true, classLoader).asSubclass(BaseAgentModelMulti.class);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


	/**
	 * Get the ContextModel using the class loader set to the simulation data
	 * path.
	 * 
	 * @return the ContextModel for this simulation
	 */
	public Class<? extends BaseContextModel> getContextModelClass() {
		try {
			return Class.forName(simulationConfig
					.getString("models.contextmodel"), true, classLoader).asSubclass(BaseContextModel.class);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


	/**
	 * Get the WorldModel using the class loader set to the simulation data
	 * path.
	 * 
	 * @return the WorldModel for this simulation
	 */
	public Class<? extends BaseWorldModel> getWorldModelClass() {
		try {
			return Class.forName(simulationConfig
					.getString("models.worldmodel"), true, classLoader).asSubclass(BaseWorldModel.class);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Get the image files from the Simulation Data which represent places.
	 * This files are in fact a white image with black dots wherever a place
	 * should be created.
	 * 
	 * @return a Map of with a name-file pair per place type
	 */
	public HashMap<String, InputStream> getPlaceFiles() {
		return getFilesByPath(PLACES_PATH);
	}
	
	public HashMap<String, InputStream> getFacultyPlaceFiles() {
		return getFilesByPath(FACULTY_PLACES_PATH);
	}

	/**
	 * Get the image files from the Simulation Data which represent overlays.
	 * An overlay is an image map with a value for each position from the map.
	 * 
	 * @return a Map of with a name-file pair per overlays
	 */
	public HashMap<String, InputStream> getOverlayFiles() {
		return getFilesByPath(OVERLAYS_PATH);
	}

	/**
	 * Get the sprites of the size given as a parameter.
	 * 
	 * @return a map with the filename-InputStream pair per sprite image
	 */
	public HashMap<String, InputStream> getSprites() {
		return getFilesByPath(simulationConfig.getString("spritepath"));
	}

	/**
	 * Get the names of the sprites available in the simulation.
	 * 
	 * @return a map with the filename-InputStream pair per sprite image
	 */
	public ArrayList<String> getSpriteNames() {
		return getFileNamesByPath(simulationConfig.getString("spritepath"));
	}

	/**
	 * Retrieve from the simulation data an InputStream for the image that
	 * represents the walkable areas (white) and the walls (black) in the
	 * simulated world.
	 * 
	 * @return the InputStream for the image file with the walls
	 */
	public InputStream getWallsFile() {
		return getFile(WALLS_FILE);
	}
	
	public InputStream getWallsLabFile() {
		return getFile(WALLS_FILE_LAB);
	}
	
	public InputStream getWallsFakFile() {
		return getFile(WALLS_FILE_FAK);
	}
	
	public InputStream getWallsFakKFile() {
		return getFile(WALLS_FILE_FAKK);
	}
	
	private int COLOR_WHITE = 0xFFFFFF;
	/*private boolean[][] wallReck;
	private boolean[][] wallLab;
	private boolean[][] wallFak;
	private boolean[][] wallFakK;*/
	private ImageData imgFakK;
	private ImageData imgR;
	private ImageData imgLab;
	private ImageData imgFak;
	
	public boolean[][] getWallsFakK () {
		int height = imgFakK.height;
		int width = imgFakK.width;

		boolean[][] walls = new boolean[height][width];

		for (int i = 0; i < height; i++) {
			int[] colors = new int[width];
			imgFakK.getPixels(0, i, width, colors, 0);

			for (int j = 0; j < width; j++) {
				walls[i][j] = (colors[j] == COLOR_WHITE);
			}
		}
		return walls;
	}
	
	public boolean[][] getWallsLab () {
		int height = imgLab.height;
		int width = imgLab.width;

		boolean[][] walls = new boolean[height][width];

		for (int i = 0; i < height; i++) {
			int[] colors = new int[width];
			imgLab.getPixels(0, i, width, colors, 0);

			for (int j = 0; j < width; j++) {
				walls[i][j] = (colors[j] == COLOR_WHITE);
			}
		}
		return walls;
	}
	
	public boolean[][] getWallsFak () {
		int height = imgFak.height;
		int width = imgFak.width;

		boolean[][] walls = new boolean[height][width];

		for (int i = 0; i < height; i++) {
			int[] colors = new int[width];
			imgFak.getPixels(0, i, width, colors, 0);

			for (int j = 0; j < width; j++) {
				walls[i][j] = (colors[j] == COLOR_WHITE);
			}
		}
		return walls;
	}
	
	public boolean[][] getWallsR () {
		int height = imgR.height;
		int width = imgR.width;

		boolean[][] walls = new boolean[height][width];

		for (int i = 0; i < height; i++) {
			int[] colors = new int[width];
			imgR.getPixels(0, i, width, colors, 0);

			for (int j = 0; j < width; j++) {
				walls[i][j] = (colors[j] == COLOR_WHITE);
			}
		}
		return walls;
	}
	
	public void createWallFiles () {
		InputStream wallsR = getWallsFile();
		imgR = new ImageData(wallsR);
		try {
			wallsR.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		InputStream wallsLab = getWallsLabFile();
		imgLab = new ImageData(wallsLab);
		try {
			wallsLab.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		InputStream wallsFak = getWallsFakKFile();
		imgFak = new ImageData(wallsFak);
		try {
			wallsFak.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		InputStream wallsFakK = getWallsFakFile();
		imgFakK = new ImageData(wallsFakK);
		try {
			wallsFakK.close();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	/**
	 * Retrieve from the simulation data an InputStream for the image that
	 * represents the background (white) and the walls (black) in the
	 * simulated world.
	 * 
	 * @return the InputStream for the image file with the background
	 */
	public InputStream getBackgroundFile() {
		return getFile(BACKGROUND_FILE);
	}
	
	public InputStream getBackgroundLabFile() {
		return getFile(BACKGROUND_FILE_LAB);
	}
	
	public InputStream getBackgroundFakFile() {
		return getFile(BACKGROUND_FILE_FAK);
	}
	
	public InputStream getBackgroundFakKFile() {
		return getFile(BACKGROUND_FILE_FAKK);
	}

	/**
	 * Get an InputSream linked to the simulation configuration file.
	 * 
	 * @return the inputstream with the configuration
	 */
	public XMLConfiguration getConfigFile() {
		if (simulationConfig == null) {
			simulationConfig = new XMLConfiguration();
			try {
				simulationConfig.load(getFile(CONFIG_FILE));
			} catch (ConfigurationException e) {
				throw new RuntimeException(
						"Error reading the simulation config file", e);
			}
		}
		return simulationConfig;
	}

	/**
	 * Retrieve a file from the SimulationData by its path.
	 * 
	 * @param path a String representing the path within the folder or jar
	 *            file
	 * @return the InputStream for that path
	 */
	protected abstract InputStream getFile(final String path);

	/**
	 * Gets the files from the simulation data associated to the given type.
	 * <p>
	 * For instance, calling <code>getFilesOfType(FileType.OVERLAY)</code>
	 * will return a Map with the names of the overlay, and the InputStreams
	 * to them.
	 * 
	 * @param path to the type of files to be retrieved
	 * @return a map with pairs of overlay names and their data
	 */
	protected abstract HashMap<String, InputStream> getFilesByPath(
			final String path);
	

	/**
	 * <p>Gets the names of the files from the simulation data associated to 
	 * the given type.</p>
	 * <p>
	 * For instance, calling <code>getFilesOfType(FileType.OVERLAY)</code>
	 * will return an ArrayList with the names of the overlays.
	 * </p>
	 * <p>
	 * This method is similar to getFilesByPath, but does not actually open the
	 * files. Use wisely to avoid having too many open files at a time.
	 * </p>
	 * @param path to the type of files to be retrieved
	 * @return an ArrayList with the file overlay names and their data
	 */
	protected abstract ArrayList<String> getFileNamesByPath(
			final String path);
}
