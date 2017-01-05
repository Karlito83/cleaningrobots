package de.tud.evaluation;

import com.google.gson.annotations.SerializedName;

/**
 * Modification of the exchange measurement to reduce mistakes.
 * 
 * @author Christopher Werner
 *
 */
public class EvaluationMeasurement {

	@SerializedName("Number")
	public double number;
	@SerializedName("AgentName1")
	public String robotName1;
	@SerializedName("AgentName2")
	public String robotName2;
	@SerializedName("Iteration")
	public double iteration;
		
	//knowledge Information
	@SerializedName("KnowledgeIntegerNumber")
	public double knowledgeIntegerNumber;
	@SerializedName("KnowledgeStringNumber")
	public double knowledgeStringNumber;
	@SerializedName("KnowledgeStringByteNumber")
	public double knowledgeStringByteNumber;
		
	//states
	@SerializedName("StateStringNumber")
	public double statesStringNumber;
	@SerializedName("StateStringByteNumber")
	public double statesStringByteNumber;
		
	//world
	@SerializedName("WorldPositionNumber")
	public double worldPositionCount;
	@SerializedName("WorldIntegerNumber")
	public double worldIntegerNumber;
	@SerializedName("WorldStringNumber")
	public double worldStringNumber;
	@SerializedName("WorldStringByteNumber")
	public double worldStringByteNumber;
		
	@SerializedName("WStateStringNumber")
	public double worldStatesStringNumber;
	@SerializedName("WStateStringByteNumber")
	public double worldStatesStringByteNumber;
		
	//Accu values
	@SerializedName("AccuDoubleNumber")
	public double accuDoubleNumber;
		
	public EvaluationMeasurement (String name1, String name2, double iteration) {
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
	public void setNumber (double value) {
		this.number = value;
	}
		
	public double getNumber () {
		return this.number;
	}
		
	public String getName1 () {
		return this.robotName1;
	}
		
	public String getName2 () {
		return this.robotName2;
	}
		
	public double getIteration () {
		return this.iteration;
	}
		
	//knowledge Information
	public double getKnowledgeIntegerNumber () {
		return this.knowledgeIntegerNumber;
	}
		
	public double getKnowledgeStringNumber () {
		return this.knowledgeStringNumber;
	}
		
	public double getKnowledgeStringByteNumber () {
		return this.knowledgeStringByteNumber;
	}
		
	//knowledge add methods
	public void addKnowledgeIntegerNumber (double value) {
		knowledgeIntegerNumber += value;
	}
		
	public void addKnowledgeStringNumber (double value) {
		knowledgeStringNumber += value;
	}
		
	public void addKnowledgeStringByteNumber (double value) {
		knowledgeStringByteNumber += value;
	}
		
	//knownState information	
	public double getStatesStringNumber () {
		return this.statesStringNumber;
	}
		
	public double getStatesStringByteNumber () {
		return this.statesStringByteNumber;
	}
		
	//knownState add methods
	public void addStatesStringNumber (double value) {
		statesStringNumber += value;
	}
		
	public void addStatesStringByteNumber (double value) {
		statesStringByteNumber += value;
	}
		
	//world information
	public double getWorldPositionCount () {
		return this.worldPositionCount;
	}
		
	public double getWorldIntegerNumber () {
		return this.worldIntegerNumber;
	}
	
	public double getWorldStringNumber () {
		return this.worldStringNumber;
	}
	
	public double getWorldStringByteNumber () {
		return this.worldStringByteNumber;
	}
	
	public double getWorldStatesStringNumber () {
		return this.worldStatesStringNumber;
	}
	
	public double getWorldStatesStringByteNumber () {
		return this.worldStatesStringByteNumber;
	}
	
	//world add methods
	public void addWorldPositionCount (double value) {
		this.worldPositionCount += value;
	}
	
	public void addWorldIntegerNumber (double value) {
		this.worldIntegerNumber += value;
	}
	public void addWorldStringNumber (double value) {
		this.worldStringNumber += value;
	}
	
	public void addWorldStringByteNumber (double value) {
		this.worldStringByteNumber += value;
	}
	
	public void addWorldStatesStringNumber (double value) {
		this.worldStatesStringNumber += value;
	}
	
	public void addWorldStatesStringByteNumber (double value) {
		this.worldStatesStringByteNumber += value;
	}
	
	//Accu get and add methods
	public double getAccuDoubleNumber () {
		return this.accuDoubleNumber;
	}
	
	public void addAccuDoubleNumber (double value) {
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
	
	public double getCompleteByteNumber () {
		double result = knowledgeStringByteNumber + statesStringByteNumber + worldStringByteNumber + worldStatesStringByteNumber;
		result += (knowledgeIntegerNumber + worldIntegerNumber + worldPositionCount*2) * 4;
		result += accuDoubleNumber * 8;
		return result;
	}
	
	public double getWorldNumberExchange () {
		return worldPositionCount*2 + worldIntegerNumber + worldStringNumber + worldStatesStringNumber;
	}
	
	public double getKnowledgeNumberExchange () {
		return knowledgeStringNumber + knowledgeIntegerNumber + accuDoubleNumber + statesStringNumber;
	}
		
	public double getTimeStampCompleteNumberExchange () {
		double result = worldPositionCount*3 + worldIntegerNumber + worldStringNumber*2 + worldStatesStringNumber;
		result += knowledgeStringNumber + knowledgeIntegerNumber;
		result += statesStringNumber;
		result += accuDoubleNumber;
		return result;
	}
		
	public double getCompleteNumberExchange () {
		double result = worldPositionCount*2 + worldIntegerNumber + worldStringNumber + worldStatesStringNumber;
		result += knowledgeStringNumber + knowledgeIntegerNumber;
		result += statesStringNumber;
		result += accuDoubleNumber;
		return result;
	}
}
