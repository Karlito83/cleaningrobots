package de.tud.swt.cleaningrobots.merge;

import de.tud.swt.cleaningrobots.Configuration;
import de.tud.swt.cleaningrobots.AgentCore;
import de.tud.swt.cleaningrobots.model.Field;
import de.tud.swt.cleaningrobots.model.State;
import de.tud.swt.cleaningrobots.util.FieldMergeInformation;

/**
 * The Follower send the new Field information to the Master and saves the measurement.
 * 
 * @author Christopher Werner
 *
 */
public class FieldMerge extends Merge {

	public FieldMerge(Configuration configuration) {
		super(configuration);
	}
	
	@Override
	protected void action(AgentCore from, AgentCore to, Object object) {
		
		FieldMergeInformation fieldInformation = (FieldMergeInformation) object;
		to.getWorld().addFields(fieldInformation.getFields());
		//measurement
		em.addWorldPositionCount(fieldInformation.getFields().size());
		em.addKnowledgeStringNumber(1);
		em.addKnowledgeStringByteNumber(from.getName().getBytes().length);		
		for (Field field : fieldInformation.getFields()) {
			//state informations of the fields
			for (State state : field.getStates()) {
				em.addWorldStringByteNumber(state.getName().getBytes().length);
				em.addWorldStringNumber(1);
			}
		}
		em.addAccuDoubleNumber(2);
	} 
}
