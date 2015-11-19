package de.tud.evaluation;

import java.util.LinkedList;
import java.util.List;

public class WorkingConfiguration {

	public AbstractState as;
	public int number_explore_agents;
	public int number_wipe_agents;
	public int number_hoove_agents;
	public int new_field_count; //0-5000
	public int map = 0; //0...Rechteck 1...Labyrinth 2...FakKomplett 3...Fak	
	public int run;	
	public int config; //0-4
	public int iteration;
	public List<ExchangeMeasurement> exchange; 
	
	public WorkingConfiguration (int explore, int hoove, int wipe, int run, int configuration, int newField, int map) {
		this.iteration = 0;
		this.exchange = new LinkedList<ExchangeMeasurement>();
		
		this.number_explore_agents = explore;
		this.number_hoove_agents = hoove;
		this.number_wipe_agents = wipe;
		this.run = run;
		this.config = configuration;
		this.new_field_count = newField;
		this.map = map;
	}
	
	public String toString () {
		return map + "_V" + config + "_CE" + number_explore_agents + "_CH" + number_hoove_agents +
				"_CW" + number_wipe_agents + "_B" + new_field_count + "_D" + run;		
	}
}
