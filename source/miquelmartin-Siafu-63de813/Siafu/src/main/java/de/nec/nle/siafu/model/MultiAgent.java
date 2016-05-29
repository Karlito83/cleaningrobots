package de.nec.nle.siafu.model;

public class MultiAgent {

	protected MultiWorld siafuWorld;	
	private String name;
	
	private int col;
	private int row;
	
	public MultiAgent(int col, int row, MultiWorld world) {
		this("", col ,row , world);
	}
	
	public MultiAgent(String name, int col, int row, MultiWorld world) {
		this.name = name;
		this.col = col;
		this.row = row;
		this.siafuWorld = world;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getCol() {
		return this.col;
	}
	
	public int getRow() {
		return this.row;
	}
	
	protected void setRow(int row) {
		this.row = row;
	}
	
	protected void setCol(int col) {
		this.col = col;
	}
}
