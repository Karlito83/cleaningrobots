package de.tud.swt.cleaningrobots.merge;

import java.util.List;

import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.measure.ExchangeMeasurement;
import de.tud.swt.cleaningrobots.model.Field;
import de.tud.swt.cleaningrobots.model.Position;
import de.tud.swt.cleaningrobots.model.State;
import de.tud.swt.cleaningrobots.util.Variables;

public class MasterFieldMerge {

	private ExchangeMeasurement em; 
	
	/**
	 * Only Methods that saves the data exchange if destination is set to null.
	 * @param name
	 */
	public void sendNullDestination (String name) {
		//measurement
		em = new ExchangeMeasurement(name, "SendNULLDest", Variables.iteration);
		em.addKnowledgeStringNumber(1);
		em.addKnowledgeStringByteNumber(name.getBytes().length);
		em.addWorldIntegerNumber(1);
	}
	
	/**
	 * Only Methods that saves the data exchange if a new destination is calculated.
	 * @param name
	 */
	public void sendDestination (String name) {
		//measurement
		em = new ExchangeMeasurement(name, "SendNewDest", Variables.iteration);
		em.addKnowledgeStringNumber(1);
		em.addKnowledgeStringByteNumber(name.getBytes().length);
		em.addWorldPositionCount(1);
	}
	
	public void sendExploreFieldsAndMerge (String name, List<Field> fields, RobotCore master) {
		//System.out.println("Fields: " + fields + " Master: " + master);
		master.getWorld().addFields(fields);
		//measurement
		em = new ExchangeMeasurement(name, "ExploreInformationNewDest", Variables.iteration);
		em.addKnowledgeStringNumber(1);
		em.addKnowledgeStringByteNumber(name.getBytes().length);		
		for (Field field : fields) {
			em.addWorldPositionCount(1);
			//blockstate because of its passable
			for (State state : field.getStates()) {
				em.addWorldStringByteNumber(state.getName().getBytes().length);
				em.addWorldStringNumber(1);
			}
		}
		em.addAccuDoubleNumber(2);
		Variables.exchange.add(em);
	}
	
	public void sendHooveFieldsAndMerge (String name, List<Field> fields, RobotCore master) {
		//System.out.println("Fields: " + fields + " Master: " + master);
		master.getWorld().addFields(fields);
		//measurement
		em = new ExchangeMeasurement(name, "HooveInformationNewDest", Variables.iteration);
		em.addKnowledgeIntegerNumber(fields.size());
		em.addKnowledgeStringNumber(1);
		em.addKnowledgeStringByteNumber(name.getBytes().length);		
		for (Field field : fields) {
			em.addWorldPositionCount(1);
			//blockstate because of its passable
			for (State state : field.getStates()) {
				em.addWorldStringByteNumber(state.getName().getBytes().length);
				em.addWorldStringNumber(1);
			}
		}
		em.addAccuDoubleNumber(2);
		Variables.exchange.add(em);
	} 
	
	public void sendWipeFieldsAndMerge (String name, List<Field> fields, RobotCore master) {
		//System.out.println("Fields: " + fields + " Master: " + master);
		master.getWorld().addFields(fields);
		//measurement
		em = new ExchangeMeasurement(name, "WipeInformationNewDest", Variables.iteration);
		em.addKnowledgeIntegerNumber(fields.size());
		em.addKnowledgeStringNumber(1);
		em.addKnowledgeStringByteNumber(name.getBytes().length);		
		for (Field field : fields) {
			em.addWorldPositionCount(1);
			//blockstate because of its passable
			for (State state : field.getStates()) {
				em.addWorldStringByteNumber(state.getName().getBytes().length);
				em.addWorldStringNumber(1);
			}
		}
		em.addAccuDoubleNumber(2);
		Variables.exchange.add(em);
	} 
	
	public void sendDestPath (String name, RobotCore follower, List<Position> path, Position destination) {
		//System.out.println(name + " Dest: " + destination + " Path: " + path);
		follower.getDestinationContainer().setDestinationAndPath(path, destination);
		//measurement
		em = new ExchangeMeasurement(name, follower.getName(), Variables.iteration);
		em.addKnowledgeStringNumber(1);
		em.addKnowledgeStringByteNumber(name.getBytes().length);
		int posCount = path.size(); 
		em.addWorldPositionCount(posCount);
		Variables.exchange.add(em);
	}
}
