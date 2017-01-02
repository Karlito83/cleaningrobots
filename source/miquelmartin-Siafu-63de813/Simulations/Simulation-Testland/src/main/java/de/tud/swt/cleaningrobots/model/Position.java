package de.tud.swt.cleaningrobots.model;

import cleaningrobots.CleaningrobotsFactory;

/**
 * Position for the representation of a point in the world.
 * 
 * @author Christopher Werner
 *
 */
public class Position {
	
	private int x;
	private int y;
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;		
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}
	
	@Override
	public int hashCode() {
		return x*10000+y;
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		if(obj!=null && obj instanceof Position){
			Position tmp = (Position)obj;
			result = (tmp.getX()==x && tmp.getY()==y);
		}
		return result;
	}
	
	public cleaningrobots.Position exportModel() {
		cleaningrobots.Position modelPosition = null;
		
		modelPosition = CleaningrobotsFactory.eINSTANCE.createPosition();
		
		modelPosition.setXpos(x);
		modelPosition.setYpos(y);
		
		return modelPosition;
	}
}
