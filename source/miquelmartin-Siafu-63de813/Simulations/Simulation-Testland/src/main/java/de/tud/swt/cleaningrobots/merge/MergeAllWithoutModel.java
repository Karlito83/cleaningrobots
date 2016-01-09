package de.tud.swt.cleaningrobots.merge;

import java.util.LinkedList;
import java.util.List;

import de.tud.evaluation.ExchangeMeasurement;
import de.tud.swt.cleaningrobots.Configuration;
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

/**
 * Merge of models between two robots with the direct world information.
 * 
 * @author Christopher Werner
 *
 */
public class MergeAllWithoutModel {

	private ExchangeMeasurement em; 
	private Configuration configuration;
	
	public MergeAllWithoutModel (Configuration configuration) {
		this.configuration = configuration;
	}
		
	/**
	 * Measurement if robot comes with new information.
	 * @param name
	 */
	public void newInformationMeasure (String name) {
		em = new ExchangeMeasurement(name, "New Info", configuration.wc.iteration);
		em.addKnowledgeIntegerNumber(1);
		em.addKnowledgeStringNumber(1);
		em.addKnowledgeStringByteNumber(name.getBytes().length);
		configuration.wc.exchange.add(em);
	}
	
	/**
	 * Import and Export the Model without using the EMF Model. It only need the RobotCore of each Robot and its World.
	 * @param exportcore RobotCore for export
	 * @param importcore RobotCore for import
	 * @param config
	 */
	public void importAllWithoutModel (RobotCore exportcore, RobotCore importcore, ImportExportConfiguration config) {
		//name information which always send
		em = new ExchangeMeasurement(exportcore.getName(), importcore.getName(), configuration.wc.iteration);
		em.addKnowledgeStringNumber(1);
		em.addKnowledgeStringByteNumber(exportcore.getName().getBytes().length);
		//add robot knowledge
		if (config.knowledge)
		{
			//run over all robot knowledge and own knowledge and insert information
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
					//insert the new information from the robot
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
			//World and Field import
			importFieldsFromWorld(exportcore, importcore, config);
		}
		configuration.wc.exchange.add(em);
	}
	
	private void importRobotKnowledgeWithoutModel (RobotKnowledge importRk, RobotCore exportR) {
		//insert the new information from the robot
		//add lastArrange
		importRk.setLastArrange(configuration.wc.iteration);
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
			//other robot has seen him last so actuate the knowledge
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
		//x and yDim
		em.addWorldIntegerNumber(2);
		
		//add worldstates
		for (State worldState : world.getWorldStates()) {
			importcore.getWorld().addWorldState(worldState);
			em.addWorldStatesStringByteNumber(worldState.getName().getBytes().length);
			em.addWorldStatesStringNumber(1);
		}
		
		//measurement
		//ExchangeMeasurement em2 = new ExchangeMeasurement(em.getName1(), (em.getName2() + "K"), em.getIteration());
		//ExchangeMeasurement em3 = new ExchangeMeasurement(em.getName1(), (em.getName2() + "S"), em.getIteration());
		//em2.addMeasurement(em);
		//em3.addMeasurement(em);		
		//ImportExportConfiguration config3 = config.getStateConfiguration();
		//ImportExportConfiguration config2 = new ImportExportConfiguration();
			
		//add fields
		for (Field modelField : world.getFields()) {
			//measurement
			/*Field newField = modelField.exportWithoutModel(config2, configuration.iteration);
			for (State modelState : modelField.getStates()) {
				em.addWorldStringByteNumber(modelState.getName().getBytes().length);
				em.addWorldStringNumber(1);
			}
			em.addWorldPositionCount(1);
			
			Field testField = modelField.exportWithoutModel(config3, configuration.iteration);
			if (testField != null) {
				for (State modelState : testField.getStates()) {
					em3.addWorldStringByteNumber(modelState.getName().getBytes().length);
					em3.addWorldStringNumber(1);
				}
				em3.addWorldPositionCount(1);
			}*/
			//real code
			Field newField = modelField.exportWithoutModel(config, configuration.wc.iteration);
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
		
		//measurement
		//configuration.exchange.add(em2);
		//configuration.exchange.add(em3);
	}
}
