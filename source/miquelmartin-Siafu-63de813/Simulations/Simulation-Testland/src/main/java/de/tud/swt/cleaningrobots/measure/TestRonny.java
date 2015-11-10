package de.tud.swt.cleaningrobots.measure;

import de.tud.swt.cleaningrobots.model.Position;

public class TestRonny {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestRonny tr = new TestRonny();
		//tr.testNumberEvaluations2();
		tr.testNumber();
		/*
		Position x = new Position (27, 36);
		Position y = new Position (13, 59);
		TestRonny tr = new TestRonny();
		long startTime = System.nanoTime();
		for (int i = 0; i < 1000000; i++) {
			x = new Position (x.getX()+1, i);
			y = new Position (y.getX()+1, i);
			tr.getDistOne(x, y);
		}
		long endTime = System.nanoTime();
		System.out.println("One: " + (endTime - startTime));
		x = new Position (27, 36);
		y = new Position (13, 59);
		startTime = System.nanoTime();
		for (int i = 0; i < 1000000; i++) {
			x = new Position (x.getX()+1, i);
			y = new Position (y.getX()+1, i);
			tr.getDistTwo(x, y);
		}
		endTime = System.nanoTime();
		System.out.println("Two: " + (endTime - startTime));*/
	}
	
	public int getDistOne (Position x, Position y) {
		int deltaX = 0;
		int deltaY = 0;
		deltaX = x.getX() - y.getX();
		deltaY = x.getY() - y.getY();
		deltaX = deltaX < 0 ? -deltaX : deltaX;
		deltaY = deltaY < 0 ? -deltaY : deltaY;

		return deltaX < deltaY ? deltaY : deltaX;
	}
	
	public int getDistTwo (Position x, Position y) {
		return Math.max(Math.abs(x.getX() - y.getX()), Math.abs(x.getY() - y.getY()));
	}
	
	public void testNumberEvaluations2 () {
		int NUMBER_EXPLORE_AGENTS = 1;
		int NUMBER_HOOVE_AGENTS = 0;
		int NUMBER_WIPE_AGENTS = 0;
		int run = 0;
		int configuration = 0;//0 1 2 3 4
		int NEW_FIELD_COUNT = 0; //0 0 500 500 500
		String map = "R";
		for (int i = 0; i < 128000; i++) {
			//set Evaluation configuration			
			if (run == 3) {
				run = 1;
				if (NEW_FIELD_COUNT == 5000 || configuration < 2) {
					NEW_FIELD_COUNT = 0;
					if (NUMBER_WIPE_AGENTS == 0 || NUMBER_HOOVE_AGENTS == 0 || NUMBER_WIPE_AGENTS > NUMBER_HOOVE_AGENTS - 2) {
						NUMBER_WIPE_AGENTS = 0;
						if (NUMBER_HOOVE_AGENTS == 0 || NUMBER_HOOVE_AGENTS > NUMBER_EXPLORE_AGENTS - 2) {
							NUMBER_HOOVE_AGENTS = 0;
							if (NUMBER_EXPLORE_AGENTS == 10) {
								NUMBER_EXPLORE_AGENTS = 1;
								if (configuration == 0) {
									System.out.println("i: " + i);
									break;
								} else {
									configuration +=1;
								}
							} else {
								NUMBER_EXPLORE_AGENTS +=1;
							}
						} else {
							NUMBER_HOOVE_AGENTS +=1;
						}
					} else {
						NUMBER_WIPE_AGENTS +=1;
					}
				} else {
					NEW_FIELD_COUNT += 1000;
				}
			} else {
				run += 1;
			}	
			System.out.println(map + "_V" + configuration + "_CE" + NUMBER_EXPLORE_AGENTS + "_CH" + NUMBER_HOOVE_AGENTS +
						"_CW" + NUMBER_WIPE_AGENTS + "_B" + NEW_FIELD_COUNT + "_D" + run);
		}
	}
	
	public void testNumber () {
		boolean running = true;
		int NUMBER_EXPLORE_AGENTS = 1;
		int NUMBER_WIPE_AGENTS = 0;
		int NUMBER_HOOVE_AGENTS = 0;
		int NEW_FIELD_COUNT = 0;
		int run = 0;	
		int configuration = 0;
		int map = 0;
		
		int i = 0;
		
		while (running) {
			if (run == 5) {
				run = 1;
				if (NEW_FIELD_COUNT == 5000 || configuration < 3) {
					NEW_FIELD_COUNT = 0;
					if (NUMBER_WIPE_AGENTS == 0 || NUMBER_HOOVE_AGENTS == 0 || NUMBER_WIPE_AGENTS > NUMBER_HOOVE_AGENTS - 2) {
						NUMBER_WIPE_AGENTS = 0;
						if (NUMBER_HOOVE_AGENTS == 0 || NUMBER_HOOVE_AGENTS > NUMBER_EXPLORE_AGENTS - 2) {
							NUMBER_HOOVE_AGENTS = 0;
							if (NUMBER_EXPLORE_AGENTS == 10) {
								NUMBER_EXPLORE_AGENTS = 1;
								if (configuration == 4) {
									configuration = 0;
									if (map == 3) {
										running = false;
										break;
									} else {
										map+=1;
									}											
								} else {
									configuration +=1;
								}
							} else {
								NUMBER_EXPLORE_AGENTS +=1;
							}
						} else {
							NUMBER_HOOVE_AGENTS +=1;
						}
					} else {
						NUMBER_WIPE_AGENTS +=1;
					}
				} else {
					NEW_FIELD_COUNT += 1000;
				}
			} else {
				run += 1;
			}
			i++;
		}
		System.out.println("I: " + i);
	}

}
