package de.tud.swt.cleaningrobots.measure;

import de.nec.nle.siafu.control.Controller;
import de.nec.nle.siafu.model.World;
import de.tud.evaluation.EvaluationConstants;
import de.tud.swt.cleaningrobots.model.Position;

public class TestRonny {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestRonny tr = new TestRonny();
		tr.testNumberEvaluations2();
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
	
	public void testNumberEvaluations () {
		EvaluationConstants.NUMBER_EXPLORE_AGENTS = 1;
		EvaluationConstants.NUMBER_HOOVE_AGENTS = 0;
		EvaluationConstants.NUMBER_WIPE_AGENTS = 0;
		EvaluationConstants.run = 0;
		EvaluationConstants.configuration = 0;//0 1 2 3 4
		EvaluationConstants.NEW_FIELD_COUNT = 0; //0 0 500 500 500
		for (int i = 0; i < 128000; i++) {
			//set Evaluation configuration			
			if (EvaluationConstants.run == 3) {
				EvaluationConstants.run = 1;
				if (EvaluationConstants.NEW_FIELD_COUNT == 1000 || EvaluationConstants.configuration < 2) {
					EvaluationConstants.NEW_FIELD_COUNT = 0;
					if (EvaluationConstants.NUMBER_EXPLORE_AGENTS == 10) {
						EvaluationConstants.NUMBER_EXPLORE_AGENTS = 1;
						if (EvaluationConstants.NUMBER_WIPE_AGENTS == 10 || EvaluationConstants.NUMBER_HOOVE_AGENTS == 0) {
							EvaluationConstants.NUMBER_WIPE_AGENTS = 0;
							if (EvaluationConstants.NUMBER_HOOVE_AGENTS == 10) {
								EvaluationConstants.NUMBER_HOOVE_AGENTS = 0;
								if (EvaluationConstants.configuration == 4) {
									System.out.println("i: " + i);
									break;
								} else {
									EvaluationConstants.configuration +=1;
								}
							} else {
								EvaluationConstants.NUMBER_HOOVE_AGENTS +=1;
							}
						} else {
							EvaluationConstants.NUMBER_WIPE_AGENTS +=1;
						}
					} else {
						EvaluationConstants.NUMBER_EXPLORE_AGENTS +=1;
					}
				} else {
					EvaluationConstants.NEW_FIELD_COUNT += 100;
				}
			} else {
				EvaluationConstants.run += 1;
			}	
			System.out.println(EvaluationConstants.map + "_V" + EvaluationConstants.configuration + "_CE" + EvaluationConstants.NUMBER_EXPLORE_AGENTS + "_CH" + EvaluationConstants.NUMBER_HOOVE_AGENTS +
						"_CW" + EvaluationConstants.NUMBER_WIPE_AGENTS + "_B" + EvaluationConstants.NEW_FIELD_COUNT + "_D" + EvaluationConstants.run);
		}
	}
	
	public void testNumberEvaluations2 () {
		EvaluationConstants.NUMBER_EXPLORE_AGENTS = 1;
		EvaluationConstants.NUMBER_HOOVE_AGENTS = 0;
		EvaluationConstants.NUMBER_WIPE_AGENTS = 0;
		EvaluationConstants.run = 0;
		EvaluationConstants.configuration = 0;//0 1 2 3 4
		EvaluationConstants.NEW_FIELD_COUNT = 0; //0 0 500 500 500
		for (int i = 0; i < 128000; i++) {
			//set Evaluation configuration			
			if (EvaluationConstants.run == 3) {
				EvaluationConstants.run = 1;
				if (EvaluationConstants.NEW_FIELD_COUNT == 1000 || EvaluationConstants.configuration < 2) {
					EvaluationConstants.NEW_FIELD_COUNT = 0;
					if (EvaluationConstants.NUMBER_WIPE_AGENTS == 10 || EvaluationConstants.NUMBER_HOOVE_AGENTS == 0 || EvaluationConstants.NUMBER_WIPE_AGENTS > EvaluationConstants.NUMBER_HOOVE_AGENTS - 1) {
						EvaluationConstants.NUMBER_WIPE_AGENTS = 0;
						if (EvaluationConstants.NUMBER_HOOVE_AGENTS == 10 || EvaluationConstants.NUMBER_HOOVE_AGENTS > EvaluationConstants.NUMBER_EXPLORE_AGENTS - 1) {
							EvaluationConstants.NUMBER_HOOVE_AGENTS = 0;
							if (EvaluationConstants.NUMBER_EXPLORE_AGENTS == 10) {
								EvaluationConstants.NUMBER_EXPLORE_AGENTS = 1;
								if (EvaluationConstants.configuration == 4) {
									System.out.println("i: " + i);
									break;
								} else {
									EvaluationConstants.configuration +=1;
								}
							} else {
								EvaluationConstants.NUMBER_EXPLORE_AGENTS +=1;
							}
						} else {
							EvaluationConstants.NUMBER_HOOVE_AGENTS +=1;
						}
					} else {
						EvaluationConstants.NUMBER_WIPE_AGENTS +=1;
					}
				} else {
					EvaluationConstants.NEW_FIELD_COUNT += 100;
				}
			} else {
				EvaluationConstants.run += 1;
			}	
			System.out.println(EvaluationConstants.map + "_V" + EvaluationConstants.configuration + "_CE" + EvaluationConstants.NUMBER_EXPLORE_AGENTS + "_CH" + EvaluationConstants.NUMBER_HOOVE_AGENTS +
						"_CW" + EvaluationConstants.NUMBER_WIPE_AGENTS + "_B" + EvaluationConstants.NEW_FIELD_COUNT + "_D" + EvaluationConstants.run);
		}
	}

}
