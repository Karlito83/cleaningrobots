package de.tud.swt.cleaningrobots.merge;

import java.util.List;

import de.tud.evaluation.ExchangeMeasurement;
import de.tud.swt.cleaningrobots.Configuration;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.model.Field;
import de.tud.swt.cleaningrobots.model.Position;
import de.tud.swt.cleaningrobots.model.State;

/**
 * Methods communicate and to measure the communication between two robots.
 * 
 * @author Christopher Werner
 * 
 */
public class MasterFieldMerge {

	private Configuration configuration;
	
	public MasterFieldMerge (Configuration configuration) {
		this.configuration = configuration;
	}
	
	/**
	 * Only Methods that saves the data exchange if destination is set to null.
	 * @param name
	 */
	public void sendNullDestination (String name) {
		//measurement
		ExchangeMeasurement em = new ExchangeMeasurement(name, "SendNULLDest", configuration.wc.iteration);
		em.addKnowledgeStringNumber(1);
		em.addKnowledgeStringByteNumber(name.getBytes().length);
		em.addWorldIntegerNumber(1);
		configuration.wc.exchange.add(em);
	}
	
	/**
	 * Only Methods that saves the data exchange if a new destination is calculated.
	 * @param name
	 */
	public void sendDestination (String name) {
		//measurement
		ExchangeMeasurement em = new ExchangeMeasurement(name, "SendNewDest", configuration.wc.iteration);
		em.addKnowledgeStringNumber(1);
		em.addKnowledgeStringByteNumber(name.getBytes().length);
		em.addWorldPositionCount(1);
		configuration.wc.exchange.add(em);
	}
	
	/**
	 * The Follower send the new Field information to the Master and saves the measurement.
	 * @param name
	 * @param fields
	 * @param master
	 * @param doing
	 */
	public void sendFieldsAndMerge (String name, List<Field> fields, RobotCore master, String doing) {
		master.getWorld().addFields(fields);
		//measurement
		ExchangeMeasurement em = new ExchangeMeasurement(name, (doing + "InformationNewDest"), configuration.wc.iteration);
		em.addWorldPositionCount(fields.size());
		em.addKnowledgeStringNumber(1);
		em.addKnowledgeStringByteNumber(name.getBytes().length);		
		for (Field field : fields) {
			//state informations of the fields
			for (State state : field.getStates()) {
				em.addWorldStringByteNumber(state.getName().getBytes().length);
				em.addWorldStringNumber(1);
			}
		}
		em.addAccuDoubleNumber(2);
		configuration.wc.exchange.add(em);
	} 
	
	/**
	 * Send the new Destination to the Follower and measure the informations.
	 * @param name
	 * @param follower
	 * @param path
	 * @param destination
	 */
	public void sendDestPath (String name, RobotCore follower, List<Position> path, Position destination) {
		follower.getDestinationContainer().setDestinationAndPath(path, destination);
		//measurement
		ExchangeMeasurement em = new ExchangeMeasurement(name, follower.getName(), configuration.wc.iteration);
		em.addKnowledgeStringNumber(1);
		em.addKnowledgeStringByteNumber(name.getBytes().length);
		em.addWorldPositionCount(path.size());
		configuration.wc.exchange.add(em);
	}
}
