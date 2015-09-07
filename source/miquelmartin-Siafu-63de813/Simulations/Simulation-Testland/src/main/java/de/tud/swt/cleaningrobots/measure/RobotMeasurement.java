package de.tud.swt.cleaningrobots.measure;

import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;

public class RobotMeasurement {
	
	private static final Gson gson = new Gson();
	
	public int memory;
	
	public String name;
	
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
	
	public RobotMeasurement fromJson(String json) {
        return gson.fromJson(json, RobotMeasurement.class);
    }

	public String toJson() {
        return gson.toJson(this);
    }	
}
