package de.tud.swt.cleaningrobots.merge;

import java.util.List;

import de.tud.swt.cleaningrobots.Configuration;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.model.Field;
import de.tud.swt.cleaningrobots.model.State;

public class FieldMerge extends Merge {

	public FieldMerge(Configuration configuration) {
		super(configuration);
	}

	/**
	 * The Follower send the new Field information to the Master and saves the measurement.
	 * @param name
	 * @param fields
	 * @param master
	 * @param doing
	 */
	public void run (String name, List<Field> fields, RobotCore master, String doing) {
		master.getWorld().addFields(fields);
		//measurement
		this.preRun(name, (doing + "InformationNewDest"));
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
		this.postRun();
	} 
}
