package de.nec.nle.siafu.testland;

import java.awt.Color;
import java.util.Random;

import de.nec.nle.siafu.model.BinaryOverlay;

public class DirtOverlay extends BinaryOverlay {

	private final static float CONST_DIRT_PERCENTAGE = 0.1f;
	private final static int CONST_DIRT_THRESHOLD = (Color.BLACK.getRGB() - Color.WHITE
			.getRGB()) / 2;

	public DirtOverlay(int width, int height) {
		super("Dörrd", generateDirtMap(width, height), CONST_DIRT_THRESHOLD);
	}

	private static int[][] generateDirtMap(int width, int height) {
		int[][] dirtMap = new int[height][width];

		Random r = new Random();

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				dirtMap[y][x] = r.nextFloat() <= CONST_DIRT_PERCENTAGE ? Color.BLACK
						.getRGB() : Color.WHITE.getRGB();
			}
		}

		return dirtMap;
	}
}
