package de.tud.swt.cleaningrobots.measure;

import com.google.gson.Gson;

public class ExchangeMeasurement {
	
	private static final Gson gson = new Gson();

	private int number;
	private String robotName1;
	private String robotName2;
	private int iteration;
	
	private int integerNumber;
	private int stringNumber;
	private int stringByteNumber;
	
	public ExchangeMeasurement (String name1, String name2, int iteration) {
		this.robotName1 = name1;
		this.robotName2 = name2;
		this.iteration = iteration;
		this.number = 0;
		this.integerNumber = 0;
		this.stringByteNumber = 0;
		this.stringNumber = 0;
	}
	
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
	
	public int getIntegerNumber () {
		return this.integerNumber;
	}
	
	public int getStringNumber () {
		return this.stringNumber;
	}
	
	public int getStringByteNumber () {
		return this.stringByteNumber;
	}
	
	public void addIntegerNumber (int value) {
		integerNumber += value;
	}
	
	public void addStringNumber (int value) {
		stringNumber += value;
	}
	
	public void addStringByteNumber (int value) {
		stringByteNumber += value;
	}
	
	public ExchangeMeasurement fromJson(String json) {
        return gson.fromJson(json, ExchangeMeasurement.class);
    }

	public String toJson() {
        return gson.toJson(this);
    }	
}
