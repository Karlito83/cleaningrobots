package de.tud.swt.cleaningrobots.merge;

import java.util.LinkedList;
import java.util.List;

import de.tud.swt.cleaningrobots.Configuration;
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
import de.tud.swt.cleaningrobots.roles.FollowerRole;
import de.tud.swt.cleaningrobots.roles.MasterRole;
import de.tud.swt.cleaningrobots.util.ImportExportConfiguration;

public class WorldMerge extends AgentMerge {

	public WorldMerge(Configuration configuration) {
		super(configuration);
	}
	
	/**
	 * Import and Export the Model without using the EMF Model. It only need the RobotCore of each Robot and its World.
	 * @param from RobotCore for export
	 * @param to RobotCore for import
	 * @param config
	 */
	@Override
	protected void action(RobotCore from, RobotCore to,
			ImportExportConfiguration config) {
		//name information which always send
		em.addKnowledgeStringNumber(1);
		em.addKnowledgeStringByteNumber(from.getName().getBytes().length);
		//add robot knowledge
		if (config.knowledge)
		{
			//run over all robot knowledge and own knowledge and insert information
			for (RobotKnowledge exportRk : from.getKnowledge()) {
				boolean isIn = false;
				for (RobotKnowledge importRk : to.getKnowledge()) {
					if (exportRk.getName().equals(importRk.getName())) {
						isIn = true;
						importRobotKnowledgeWithoutModel(importRk, exportRk);							
					}
				}
				if (!isIn) {
					RobotKnowledge importRk = new RobotKnowledge(exportRk.getName());
					to.getKnowledge().add(importRk);
					importRobotKnowledgeWithoutModel(importRk, exportRk);
				}
			}
			boolean isIn = false;
			for (RobotKnowledge importRk : to.getKnowledge()) {
				if (importRk.getName().equals(from.getName())) {
					isIn = true;			
					importRobotKnowledgeWithoutModel(importRk, from);
				}
			}
			if (!isIn) {
				RobotKnowledge importRk = new RobotKnowledge(from.getName());
				to.getKnowledge().add(importRk);
				importRobotKnowledgeWithoutModel(importRk, from);
			}
		}
		if (config.knownstates && !config.knowledge)
		{
			boolean isIn = false;
			for (RobotKnowledge hisRk : to.getKnowledge()) {					
				if (hisRk.getName().equals(from.getName())) {
					isIn = true;
					//insert the new information from the robot
					List<State> knowns = new LinkedList<State>();
					for (State s : from.getSupportedStates()) {
						knowns.add(s);
						em.addStatesStringByteNumber(s.getName().getBytes().length);
						em.addStatesStringNumber(1);
					}
					hisRk.setKnownStates(knowns);						
				}
			}
			if (!isIn) {
				RobotKnowledge rk = new RobotKnowledge(from.getName());
				List<State> knowns = new LinkedList<State>();
				for (State s : from.getSupportedStates()) {
					knowns.add(s);
					em.addStatesStringByteNumber(s.getName().getBytes().length);
					em.addStatesStringNumber(1);
				}
				rk.setKnownStates(knowns);
				to.getKnowledge().add(rk);
			}
		}
		if (config.world)
		{
			//World and Field import
			importFieldsFromWorld(from, to, config);
		}
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
				mrm.setRoleName(r.getClass().getName());
				for (RobotRole s : m.getFollowers()) {
					em.addKnowledgeStringByteNumber(s.getRobotCore().getName().getBytes().length);
					mrm.getFollowers().add(s.getRobotCore().getName());
				}
				em.addKnowledgeStringNumber(m.getFollowers().size() + 1);
				em.addKnowledgeStringByteNumber(r.getClass().getName().getBytes().length);
				//add MasterRoleModel
				roles.add(mrm);
			} else if (r instanceof FollowerRole) {
				FollowerRole f = (FollowerRole) r;
				em.addKnowledgeStringNumber(2);
				em.addKnowledgeStringByteNumber(f.getMaster().getRobotCore().getName().getBytes().length);
				em.addKnowledgeStringByteNumber(r.getClass().getName().getBytes().length);
				//add FollowerRoleModel
				FollowerRoleModel frm = new FollowerRoleModel();
				frm.setMaster(f.getMaster().getRobotCore().getName());
				frm.setRoleName(r.getClass().getName());
				roles.add(frm);
			} else {
				RoleModel rm = new RoleModel();
				em.addKnowledgeStringNumber(1);
				em.addKnowledgeStringByteNumber(r.getClass().getName().getBytes().length);
				rm.setRoleName(r.getClass().getName());
				roles.add(rm);
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
					for (String s : m.getFollowers())
						em.addKnowledgeStringByteNumber(s.getBytes().length);
					em.addKnowledgeStringNumber(m.getFollowers().size());
				} else if (r instanceof FollowerRoleModel) {
					FollowerRoleModel f = (FollowerRoleModel) r;
					em.addKnowledgeStringNumber(1);
					em.addKnowledgeStringByteNumber(f.getMaster().getBytes().length);
				}
				em.addKnowledgeStringNumber(1);
				em.addKnowledgeStringByteNumber(r.getRoleName().getBytes().length);
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
					for (String s : m.getFollowers())
						em.addKnowledgeStringByteNumber(s.getBytes().length);
					em.addKnowledgeStringNumber(m.getFollowers().size());
				} else if (r instanceof FollowerRoleModel) {
					FollowerRoleModel f = (FollowerRoleModel) r;
					em.addKnowledgeStringNumber(1);
					em.addKnowledgeStringByteNumber(f.getMaster().getBytes().length);
				}
				em.addKnowledgeStringNumber(1);
				em.addKnowledgeStringByteNumber(r.getRoleName().getBytes().length);
			}
			for (State s : exportRk.getKnownStates()) {
				em.addKnowledgeStringByteNumber(s.getName().getBytes().length);
				em.addKnowledgeStringNumber(1);
			}
		}
	}
	
	private void importFieldsFromWorld(RobotCore from, RobotCore to, ImportExportConfiguration config) {
		World world = from.getWorld();
		//x and yDim
		em.addWorldIntegerNumber(2);
		
		//add worldstates
		for (State worldState : world.getWorldStates()) {
			to.getWorld().addWorldState(worldState);
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
			to.getWorld().addField(newField);
		}
		to.getWorld().resetNewInformationCounter();
		
		//measurement
		//configuration.exchange.add(em2);
		//configuration.exchange.add(em3);
	}

}
