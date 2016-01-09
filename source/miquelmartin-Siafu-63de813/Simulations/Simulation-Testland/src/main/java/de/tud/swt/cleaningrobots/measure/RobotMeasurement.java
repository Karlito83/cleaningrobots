package de.tud.swt.cleaningrobots.measure;

import java.util.LinkedList;
import java.util.List;

import de.tud.evaluation.EvaluationConstants;

/**
 * Measurement information about an robot for one complete test case.
 * 
 * @author Christopher Werner
 *
 */
public class RobotMeasurement {
	
	public int memory;	
	public String name;	
	public long benchmarkTime;
	
	public double completeEnergie;
	public int completeTicks;
	public double completeTime;
	
	public List<Double> energieProTick;
	public List<Double> timeProTick;
	
	public RobotMeasurement (String name) {
		this.name = name;
		energieProTick = new LinkedList<Double>();
		timeProTick = new LinkedList<Double>();
	}
	
	public void setName (String name) {
		this.name = name;
	}
	
	public RobotMeasurement fromJson(String json) {
        return EvaluationConstants.gson.fromJson(json, RobotMeasurement.class);
    }

	public String toJson() {
        return EvaluationConstants.gson.toJson(this);
    }	
}
