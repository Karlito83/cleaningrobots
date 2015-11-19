package de.tud.swt.cleaningrobots.merge;

import java.util.LinkedList;
import java.util.List;

import de.tud.evaluation.ExchangeMeasurement;
import de.tud.evaluation.WorkingConfiguration;
import de.tud.swt.cleaningrobots.FollowerRole;
import de.tud.swt.cleaningrobots.MasterRole;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.RobotKnowledge;
import de.tud.swt.cleaningrobots.RobotRole;
import de.tud.swt.cleaningrobots.hardware.HardwareComponent;
import de.tud.swt.cleaningrobots.model.Field;
import de.tud.swt.cleaningrobots.model.FollowerRoleModel;
import de.tud.swt.cleaningrobots.model.MasterRoleModel;
import de.tud.swt.cleaningrobots.model.RoleModel;
import de.tud.swt.cleaningrobots.model.State;
import de.tud.swt.cleaningrobots.model.World;
import de.tud.swt.cleaningrobots.util.ImportExportConfiguration;

public class MergeAllWithoutModel {

	private ExchangeMeasurement em; 
	private WorkingConfiguration configuration;
	
	public MergeAllWithoutModel (WorkingConfiguration configuration) {
		this.configuration = configuration;
	}
		
	public void newInformationMeasure (String name) {
		em = new ExchangeMeasurement(name, "New Info", configuration.iteration);
		em.addKnowledgeIntegerNumber(1);
		em.addKnowledgeStringNumber(1);
		em.addKnowledgeStringByteNumber(name.getBytes().length);
		configuration.exchange.add(em);
	}
	
	/**
	 * Import and Export the Model without using the EMF Model. It only need the RobotCore of each Robot and its World.
	 * @param exportcore RobotCore for export
	 * @param importcore RobotCore for import
	 * @param config
	 */
	public void importAllWithoutModel (RobotCore exportcore, RobotCore importcore, ImportExportConfiguration config) {
		//Namen Information die immer mitgesendet wird
		em = new ExchangeMeasurement(exportcore.getName(), importcore.getName(), configuration.iteration);
		em.addKnowledgeStringNumber(1);
		em.addKnowledgeStringByteNumber(exportcore.getName().getBytes().length);
		//für alle noch measurement erweitern
		if (config.knowledge)
		{
			//alls robotknowledges und eigene Daten durchlaufen und einfügen
			for (RobotKnowledge exportRk : exportcore.getKnowledge()) {
				boolean isIn = false;
				for (RobotKnowledge importRk : importcore.getKnowledge()) {
					if (exportRk.getName().equals(importRk.getName())) {
						isIn = true;
						importRobotKnowledgeWithoutModel(importRk, exportRk);							
					}
				}
				if (!isIn) {
					RobotKnowledge importRk = new RobotKnowledge(exportRk.getName());
					importcore.getKnowledge().add(importRk);
					importRobotKnowledgeWithoutModel(importRk, exportRk);
				}
			}
			boolean isIn = false;
			for (RobotKnowledge importRk : importcore.getKnowledge()) {
				if (importRk.getName().equals(exportcore.getName())) {
					isIn = true;			
					importRobotKnowledgeWithoutModel(importRk, exportcore);
				}
			}
			if (!isIn) {
				RobotKnowledge importRk = new RobotKnowledge(exportcore.getName());
				importcore.getKnowledge().add(importRk);
				importRobotKnowledgeWithoutModel(importRk, exportcore);
			}
		}
		if (config.knownstates && !config.knowledge)
		{
			boolean isIn = false;
			for (RobotKnowledge hisRk : importcore.getKnowledge()) {					
				if (hisRk.getName().equals(exportcore.getName())) {
					isIn = true;
					//Füge die neuen Information vom Robot ein
					List<State> knowns = new LinkedList<State>();
					for (State s : exportcore.getSupportedStates()) {
						knowns.add(s);
						em.addStatesStringByteNumber(s.getName().getBytes().length);
						em.addStatesStringNumber(1);
					}
					hisRk.setKnownStates(knowns);						
				}
			}
			if (!isIn) {
				RobotKnowledge rk = new RobotKnowledge(exportcore.getName());
				List<State> knowns = new LinkedList<State>();
				for (State s : exportcore.getSupportedStates()) {
					knowns.add(s);
					em.addStatesStringByteNumber(s.getName().getBytes().length);
					em.addStatesStringNumber(1);
				}
				rk.setKnownStates(knowns);
				importcore.getKnowledge().add(rk);
			}
		}
		if (config.world)
		{
			//World und Fields import
			importFieldsFromWorld(exportcore, importcore, config);
		}
		configuration.exchange.add(em);
	}
	
	private void importRobotKnowledgeWithoutModel (RobotKnowledge importRk, RobotCore exportR) {
		//Füge die neuen Information vom Robot ein
		//add lastArrange
		importRk.setLastArrange(configuration.iteration);
		em.addKnowledgeIntegerNumber(1);
		//add destination
		if (exportR.getDestinationContainer().getDestination() != null) {
			importRk.setLastDestination(exportR.getDestinationContainer().getDestination());
			em.addKnowledgeIntegerNumber(2);
		}
		//add components
		List<String> components = new LinkedList<String>();
		for (HardwareComponent s : exportR.getHardwarecomponents()) {
			em.addKnowledgeStringByteNumber(s.getName().getBytes().length);
			components.add(s.getName());
		}
		em.addKnowledgeStringNumber(components.size());
		importRk.setComponents(components);
		//add roles
		List<RoleModel> roles = new LinkedList<RoleModel>();
		for (RobotRole r : exportR.getRoles()) {
			if (r instanceof MasterRole) {
				MasterRole m = (MasterRole) r;
				MasterRoleModel mrm = new MasterRoleModel();
				for (RobotRole s : m.getFollowers()) {
					em.addKnowledgeStringByteNumber(s.getRobotCore().getName().getBytes().length);
					mrm.followers.add(s.getRobotCore().getName());
				}
				em.addKnowledgeStringNumber(m.getFollowers().size());
				//add MasterRoleModel
				roles.add(mrm);
			} else {
				FollowerRole f = (FollowerRole) r;
				em.addKnowledgeStringNumber(1);
				em.addKnowledgeStringByteNumber(f.master.getRobotCore().getName().getBytes().length);
				//add FollowerRoleModel
				FollowerRoleModel frm = new FollowerRoleModel();
				frm.master = f.master.getRobotCore().getName();
				roles.add(frm);
			}
		}
		importRk.setRoles(roles);
		//add states
		List<State> knowns = new LinkedList<State>();
		knowns.addAll(exportR.getSupportedStates());
		for (State s : exportR.getSupportedStates()) {
			em.addStatesStringByteNumber(s.getName().getBytes().length);
			em.addStatesStringNumber(1);
		}
		importRk.setKnownStates(knowns);	
	}
	
	private void importRobotKnowledgeWithoutModel (RobotKnowledge importRk, RobotKnowledge exportRk) {		
		if (importRk.getLastArrange() < exportRk.getLastArrange()) {
			em.addKnowledgeStringByteNumber(exportRk.getName().getBytes().length);
			em.addKnowledgeStringNumber(1);
			//anderer Robot hat ihn als letztes gesehen also aktualisiere deine Daten
			//TODO: proof if this must in or not
			//importRk.setLastArrange(exportRk.getLastArrange());
			em.addKnowledgeIntegerNumber(1);
			//add destination
			if (exportRk.getLastDestination() != null) {
				importRk.setLastDestination(exportRk.getLastDestination());			
				em.addKnowledgeIntegerNumber(2);
			}
			//add components
			for (String s : exportRk.getComponents()) {
				em.addKnowledgeStringByteNumber(s.getBytes().length);
			}
			em.addKnowledgeStringNumber(exportRk.getComponents().size());
			importRk.setComponents(exportRk.getComponents());
			//add roles
			for (RoleModel r : exportRk.getRoles()) {
				if (r instanceof MasterRoleModel) {
					MasterRoleModel m = (MasterRoleModel) r;
					for (String s : m.followers)
						em.addKnowledgeStringByteNumber(s.getBytes().length);
					em.addKnowledgeStringNumber(m.followers.size());
				} else {
					FollowerRoleModel f = (FollowerRoleModel) r;
					em.addKnowledgeStringNumber(1);
					em.addKnowledgeStringByteNumber(f.master.getBytes().length);
				}
			}
			importRk.setRoles(exportRk.getRoles());
			//add states
			List<State> knowns = new LinkedList<State>();
			knowns.addAll(exportRk.getKnownStates());
			for (State s : exportRk.getKnownStates()) {
				em.addKnowledgeStringByteNumber(s.getName().getBytes().length);
				em.addKnowledgeStringNumber(1);
			}
			importRk.setKnownStates(knowns);
		}
		else 
		{
			em.addKnowledgeStringByteNumber(exportRk.getName().getBytes().length);
			em.addKnowledgeStringNumber(1);
			em.addKnowledgeIntegerNumber(1);
			if (exportRk.getLastDestination() != null) {
				em.addKnowledgeIntegerNumber(2);
			}
			for (String s : exportRk.getComponents()) {
				em.addKnowledgeStringByteNumber(s.getBytes().length);
			}
			em.addKnowledgeStringNumber(exportRk.getComponents().size());
			for (RoleModel r : exportRk.getRoles()) {
				if (r instanceof MasterRoleModel) {
					MasterRoleModel m = (MasterRoleModel) r;
					for (String s : m.followers)
						em.addKnowledgeStringByteNumber(s.getBytes().length);
					em.addKnowledgeStringNumber(m.followers.size());
				} else {
					FollowerRoleModel f = (FollowerRoleModel) r;
					em.addKnowledgeStringNumber(1);
					em.addKnowledgeStringByteNumber(f.master.getBytes().length);
				}
			}
			for (State s : exportRk.getKnownStates()) {
				em.addKnowledgeStringByteNumber(s.getName().getBytes().length);
				em.addKnowledgeStringNumber(1);
			}
		}
	}
	
	private void importFieldsFromWorld(RobotCore exportcore, RobotCore importcore, ImportExportConfiguration config) {
		World world = exportcore.getWorld();
		//x und yDim
		em.addWorldIntegerNumber(2);
		
		//add worldstates
		for (State worldState : world.getWorldStates()) {
			importcore.getWorld().addWorldState(worldState);
			em.addWorldStatesStringByteNumber(worldState.getName().getBytes().length);
			em.addWorldStatesStringNumber(1);
		}
			
		//add fields
		for (Field modelField : world.getFields()) {
			Field newField = modelField.exportWithoutModel(config, configuration.iteration);
			if (newField == null)
				continue;
			
			for (State modelState : newField.getStates()) {
				em.addWorldStringByteNumber(modelState.getName().getBytes().length);
				em.addWorldStringNumber(1);
			}
			em.addWorldPositionCount(1);
			importcore.getWorld().addField(newField);
		}
		importcore.getWorld().resetNewInformationCounter();
	}
}
