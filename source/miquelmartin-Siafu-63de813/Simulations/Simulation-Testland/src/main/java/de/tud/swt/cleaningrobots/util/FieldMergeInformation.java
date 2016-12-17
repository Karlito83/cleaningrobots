package de.tud.swt.cleaningrobots.util;

import java.util.List;

import de.tud.swt.cleaningrobots.model.Field;

public class FieldMergeInformation {
	
	/**
	 * List of new Field from Follower;
	 */
	private List<Field> fields;
	
	public FieldMergeInformation (List<Field> fields) {
		this.fields = fields;
	}
	
	public List<Field> getFields () {
		return this.fields;
	}
}
