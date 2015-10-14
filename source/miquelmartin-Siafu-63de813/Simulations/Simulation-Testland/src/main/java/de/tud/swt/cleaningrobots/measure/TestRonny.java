package de.tud.swt.cleaningrobots.measure;

import de.tud.swt.cleaningrobots.model.Position;

public class TestRonny {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
		System.out.println("Two: " + (endTime - startTime));
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

}
