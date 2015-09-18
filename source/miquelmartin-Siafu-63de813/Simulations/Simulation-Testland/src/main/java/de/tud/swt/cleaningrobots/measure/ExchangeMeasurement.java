package de.tud.swt.cleaningrobots.measure;

import com.google.gson.Gson;

public class ExchangeMeasurement {
	
	private static final Gson gson = new Gson();

	private int number;
	private String robotName1;
	private String robotName2;
	private int iteration;
	
	//knowledge Information
	private int knowledgeIntegerNumber;
	private int knowledgeStringNumber;
	private int knowledgeStringByteNumber;
	
	//states
	private int statesStringNumber;
	private int statesStringByteNumber;
	
	//world
	private int worldPositionCount;
	private int worldIntegerNumber;
	private int worldStringNumber;
	private int worldStringByteNumber;
	
	private int worldStatesStringNumber;
	private int worldStatesStringByteNumber;
	
	public ExchangeMeasurement (String name1, String name2, int iteration) {
		this.robotName1 = name1;
		this.robotName2 = name2;
		this.iteration = iteration;
		this.number = 0;
		
		//knowledge
		this.knowledgeIntegerNumber = 0;
		this.knowledgeStringNumber = 0;
		this.knowledgeStringByteNumber = 0;
		
		//states
		this.statesStringNumber = 0;
		this.statesStringByteNumber = 0;
		
		//world
		this.worldPositionCount = 0;
		this.worldIntegerNumber = 0;
		this.worldStringNumber = 0;
		this.worldStringByteNumber = 0;
		
		this.worldStatesStringNumber = 0;
		this.worldStatesStringByteNumber = 0;
	}
	
	//basic information
	public void setNumber (int value) {
		this.number = value;
	}
	
	public int getNumber () {
		return this.number;
	}
	
	public String getName1 () {
		return this.robotName1;
	}
	
	public String getName2 () {
		return this.robotName2;
	}
	
	public int getIteration () {
		return this.iteration;
	}
	
	//knowledge Information
	public int getKnowledgeIntegerNumber () {
		return this.knowledgeIntegerNumber;
	}
	
	public int getKnowledgeStringNumber () {
		return this.knowledgeStringNumber;
	}
	
	public int getKnowledgeStringByteNumber () {
		return this.knowledgeStringByteNumber;
	}
	
	//knowledge add methods
	public void addKnowledgeIntegerNumber (int value) {
		knowledgeIntegerNumber += value;
	}
	
	public void addKnowledgeStringNumber (int value) {
		knowledgeStringNumber += value;
	}
	
	public void addKnowledgeStringByteNumber (int value) {
		knowledgeStringByteNumber += value;
	}
	
	//knownState information	
	public int getStatesStringNumber () {
		return this.statesStringNumber;
	}
	
	public int getStatesStringByteNumber () {
		return this.statesStringByteNumber;
	}
	
	//knownState add methods
	public void addStatesStringNumber (int value) {
		statesStringNumber += value;
	}
	
	public void addStatesStringByteNumber (int value) {
		statesStringByteNumber += value;
	}
	
	//world information
	public int getWorldPositionCount () {
		return this.worldPositionCount;
	}
	
	public int getWorldIntegerNumber () {
		return this.worldIntegerNumber;
	}
	public int getWorldStringNumber () {
		return this.worldStringNumber;
	}
	
	public int getWorldStringByteNumber () {
		return this.worldStringByteNumber;
	}
	
	public int getWorldStatesStringNumber () {
		return this.worldStatesStringNumber;
	}
	
	public int getWorldStatesStringByteNumber () {
		return this.worldStatesStringByteNumber;
	}
	
	//world add methods
	public void addWorldPositionCount (int value) {
		this.worldPositionCount += value;
	}
	
	public void addWorldIntegerNumber (int value) {
		this.worldIntegerNumber += value;
	}
	public void addWorldStringNumber (int value) {
		this.worldStringNumber += value;
	}
	
	public void addWorldStringByteNumber (int value) {
		this.worldStringByteNumber += value;
	}
	
	public void addWorldStatesStringNumber (int value) {
		this.worldStatesStringNumber += value;
	}
	
	public void addWorldStatesStringByteNumber (int value) {
		this.worldStatesStringByteNumber += value;
	}
	
	public ExchangeMeasurement fromJson(String json) {
        return gson.fromJson(json, ExchangeMeasurement.class);
    }

	public String toJson() {
        return gson.toJson(this);
    }	
}
