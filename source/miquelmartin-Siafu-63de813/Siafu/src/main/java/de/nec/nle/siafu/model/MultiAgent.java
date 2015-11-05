package de.nec.nle.siafu.model;

public class MultiAgent {

	protected MultiWorld siafuWorld;	
	protected String name;
	
	protected int col;
	protected int row;
	
	public MultiAgent(int col, int row, MultiWorld world) {
		this.col = col;
		this.row = row;
		this.siafuWorld = world;
	}
	
	public int getCol() {
		return col;
	}
	
	public int getRow() {
		return row;
	}
	
	public void setRow(int row) {
		this.row = row;
	}
	
	public void setCol(int col) {
		this.col = col;
	}
}
