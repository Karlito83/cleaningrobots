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
	
	public void sendFieldAndMerge (String name, List<Field> fields, RobotCore master) {
		//System.out.println("Fields: " + fields + " Master: " + master);
		master.getWorld().addFields(fields);
		//measurement
		em = new ExchangeMeasurement(name, "needNewDestPath", Variables.iteration);
		em.addKnowledgeStringNumber(1);
		em.addKnowledgeStringByteNumber(name.getBytes().length);		
		for (Field field : fields) {
			em.addWorldPositionCount(1);
			em.addWorldIntegerNumber(2);
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
		follower.getDestinationContainer().setDestinationAndPath(path, destination);
		//measurement
		em = new ExchangeMeasurement(name, follower.getName(), Variables.iteration);
		em.addKnowledgeStringNumber(1);
		em.addKnowledgeStringByteNumber(name.getBytes().length);
		int posCount = path.size(); 
		em.addWorldPositionCount(posCount);
		em.addWorldIntegerNumber(2*posCount);
		Variables.exchange.add(em);
	}
}
