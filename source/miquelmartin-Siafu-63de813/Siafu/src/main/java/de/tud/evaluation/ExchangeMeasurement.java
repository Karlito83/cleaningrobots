package de.tud.evaluation;

import com.google.gson.annotations.SerializedName;

/**
 * Information about one data exchange between two robots.
 * 
 * @author Christopher Werner
 *
 */
public class ExchangeMeasurement {
	
	@SerializedName("Number")
	public int number;
	@SerializedName("AgentName1")
	public String robotName1;
	@SerializedName("AgentName2")
	public String robotName2;
	@SerializedName("Iteration")
	public int iteration;
		
	//knowledge Information
	@SerializedName("KnowledgeIntegerNumber")
	public int knowledgeIntegerNumber;
	@SerializedName("KnowledgeStringNumber")
	public int knowledgeStringNumber;
	@SerializedName("KnowledgeStringByteNumber")
	public int knowledgeStringByteNumber;
		
	//states
	@SerializedName("StateStringNumber")
	public int statesStringNumber;
	@SerializedName("StateStringByteNumber")
	public int statesStringByteNumber;
		
	//world
	@SerializedName("WorldPositionNumber")
	public int worldPositionCount;
	@SerializedName("WorldIntegerNumber")
	public int worldIntegerNumber;
	@SerializedName("WorldStringNumber")
	public int worldStringNumber;
	@SerializedName("WorldStringByteNumber")
	public int worldStringByteNumber;
		
	@SerializedName("WStateStringNumber")
	public int worldStatesStringNumber;
	@SerializedName("WStateStringByteNumber")
	public int worldStatesStringByteNumber;
		
	//Accu values
	@SerializedName("AccuDoubleNumber")
	public int accuDoubleNumber;
	
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
		
		//Accu
		this.accuDoubleNumber = 0;
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
	
	//Accu get and add methods
	public int getAccuDoubleNumber () {
		return this.accuDoubleNumber;
	}
	
	public void addAccuDoubleNumber (int value) {
		this.accuDoubleNumber += value;
	}
	
	public void addMeasurement (ExchangeMeasurement em) {
		this.addAccuDoubleNumber(em.getAccuDoubleNumber());
		this.addKnowledgeIntegerNumber(em.getKnowledgeIntegerNumber());
		this.addKnowledgeStringByteNumber(em.getKnowledgeStringByteNumber());
		this.addKnowledgeStringNumber(em.getKnowledgeStringNumber());
		this.addStatesStringByteNumber(em.getStatesStringByteNumber());
		this.addStatesStringNumber(em.getStatesStringNumber());
		this.addWorldIntegerNumber(em.getWorldIntegerNumber());
		this.addWorldPositionCount(em.getWorldPositionCount());
		this.addWorldStatesStringByteNumber(em.getWorldStatesStringByteNumber());
		this.addWorldStatesStringNumber(em.getWorldStatesStringNumber());
		this.addWorldStringByteNumber(em.getWorldStringByteNumber());
		this.addWorldStringNumber(em.getWorldStringNumber());
	}
	
	public int getCompleteByteNumber () {
		int result = knowledgeStringByteNumber + statesStringByteNumber + worldStringByteNumber + worldStatesStringByteNumber;
		result += (knowledgeIntegerNumber + worldIntegerNumber + worldPositionCount*2) * 4;
		result += accuDoubleNumber * 8;
		return result;
	}
	
	public int getWorldNumberExchange () {
		return worldPositionCount*2 + worldIntegerNumber + worldStringNumber + worldStatesStringNumber;
	}
	
	public int getKnowledgeNumberExchange () {
		return knowledgeStringNumber + knowledgeIntegerNumber + accuDoubleNumber + statesStringNumber;
	}
	
	public int getTimeStampCompleteNumberExchange () {
		int result = worldPositionCount/2 * 3 + worldIntegerNumber + worldStringNumber*2 + worldStatesStringNumber;
		result += knowledgeStringNumber + knowledgeIntegerNumber;
		result += statesStringNumber;
		result += accuDoubleNumber;
		return result;
	}
	
	public int getCompleteNumberExchange () {
		int result = worldPositionCount*2 + worldIntegerNumber + worldStringNumber + worldStatesStringNumber;
		result += knowledgeStringNumber + knowledgeIntegerNumber;
		result += statesStringNumber;
		result += accuDoubleNumber;
		return result;
	}
	
	public ExchangeMeasurement fromJson(String json) {
        return EvaluationConstants.gson.fromJson(json, ExchangeMeasurement.class);
    }

	public String toJson() {
        return EvaluationConstants.gson.toJson(this);
    }	
}
