package de.tud.swt.cleaningrobots.merge;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import cleaningrobots.CleaningrobotsFactory;
import cleaningrobots.WorldPart;
import de.tud.evaluation.ExchangeMeasurement;
import de.tud.swt.cleaningrobots.Configuration;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.RobotKnowledge;
import de.tud.swt.cleaningrobots.model.Field;
import de.tud.swt.cleaningrobots.model.FollowerRoleModel;
import de.tud.swt.cleaningrobots.model.MasterRoleModel;
import de.tud.swt.cleaningrobots.model.Position;
import de.tud.swt.cleaningrobots.model.RoleModel;
import de.tud.swt.cleaningrobots.model.State;
import de.tud.swt.cleaningrobots.util.EMFUtils;
import de.tud.swt.cleaningrobots.util.ImportExportConfiguration;

/**
 * Merge of models between two robots with the EMF Model.
 * 
 * @author Christopher Werner
 *
 */
public class MergeAll {
	
	private ExchangeMeasurement em; 
	private Configuration configuration;
	
	public MergeAll (Configuration configuration) {
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
	 * Import the model to the robot.
	 * @param model
	 * @param importcore
	 * @param config
	 */
	public void importAllModel(EObject model, RobotCore importcore, ImportExportConfiguration config) {
		if (model instanceof cleaningrobots.Robot) {
			cleaningrobots.Robot robot = (cleaningrobots.Robot) model;
			
			//name information which always send
			em = new ExchangeMeasurement(robot.getName(), importcore.getName(), configuration.wc.iteration);
			em.addKnowledgeStringNumber(1);
			em.addKnowledgeStringByteNumber(robot.getName().getBytes().length);
			//add robot knowledge
			if (config.knowledge)
			{
				//run over all robot knowledge and own knowledge and insert information
				for (cleaningrobots.RobotKnowledge exportRk : robot.getRobotKnowledge()) {
					boolean isIn = false;
					for (RobotKnowledge importRk : importcore.getKnowledge()) {
						if (exportRk.getName().equals(importRk.getName())) {
							isIn = true;
							importRobotKnowledge(importRk, exportRk, importcore);							
						}
					}
					if (!isIn) {
						RobotKnowledge rk = new RobotKnowledge(exportRk.getName());
						importcore.getKnowledge().add(rk);
						importRobotKnowledge(rk, exportRk, importcore);
					}
				}
				boolean isIn = false;
				for (RobotKnowledge importRk : importcore.getKnowledge()) {
					if (importRk.getName().equals(robot.getName())) {
						isIn = true;			
						importRobotKnowledge(importRk, robot, importcore);
					}
				}
				if (!isIn) {
					RobotKnowledge rk = new RobotKnowledge(robot.getName());
					importcore.getKnowledge().add(rk);
					importRobotKnowledge(rk, robot, importcore);
				}
			}
			if (config.knownstates && !config.knowledge)
			{
				boolean isIn = false;
				for (RobotKnowledge hisRk : importcore.getKnowledge()) {					
					if (hisRk.getName().equals(robot.getName())) {
						isIn = true;
						//insert the new information from the robot
						List<State> knowns = new LinkedList<State>();
						for (cleaningrobots.State s : robot.getKnownStates()) {
							State st = importcore.configuration.createState(s.getName());
							knowns.add(st);
							em.addStatesStringByteNumber(s.getName().getBytes().length);
							em.addStatesStringNumber(1);
						}
						hisRk.setKnownStates(knowns);						
					}
				}
				if (!isIn) {
					RobotKnowledge rk = new RobotKnowledge(robot.getName());
					List<State> knowns = new LinkedList<State>();
					for (cleaningrobots.State s : robot.getKnownStates()) {
						State st = importcore.configuration.createState(s.getName());
						knowns.add(st);
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
				cleaningrobots.WorldPart rootWorldPart = robot.getWorld();
				importFieldsFromWorldModel(rootWorldPart, importcore);
			}
			configuration.wc.exchange.add(em);
		}
	}
	
	private void importRobotKnowledge (RobotKnowledge importRk, cleaningrobots.Robot exportR, RobotCore robot) {
		//insert the new information from the robot
		importRk.setLastArrange(configuration.wc.iteration);
		em.addKnowledgeIntegerNumber(1);
		if (exportR.getDestination() != null) {
			Position dest = new Position(exportR.getDestination().getXpos(), exportR.getDestination().getYpos());
			importRk.setLastDestination(dest);
			em.addKnowledgeIntegerNumber(2);
		}
		importRk.setComponents(exportR.getComponents());
		for (String s : exportR.getComponents()) {
			em.addKnowledgeStringByteNumber(s.getBytes().length);
		}
		em.addKnowledgeStringNumber(exportR.getComponents().size());
		//add Roles to RobotKnowledge
		List<RoleModel> newOnes = new ArrayList<RoleModel>();
		for (cleaningrobots.Role r : exportR.getRoles()) {
			if (r instanceof cleaningrobots.MasterRole) {
				cleaningrobots.MasterRole m = (cleaningrobots.MasterRole) r;
				for (String s : m.getFollowerNames())
					em.addKnowledgeStringByteNumber(s.getBytes().length);
				em.addKnowledgeStringNumber(m.getFollowerNames().size());
				//add role to importKnowledge
				MasterRoleModel mrm = new MasterRoleModel();
				mrm.getFollowers().addAll(m.getFollowerNames());
				newOnes.add(mrm);				
			} else {
				cleaningrobots.FollowerRole f = (cleaningrobots.FollowerRole) r;
				em.addKnowledgeStringNumber(1);
				em.addKnowledgeStringByteNumber(f.getMasterName().getBytes().length);
				//add role to importKnowledge
				FollowerRoleModel frm = new FollowerRoleModel();
				frm.setMaster(f.getMasterName());
				newOnes.add(frm);
			}
		}
		importRk.setRoles(newOnes);
		//add states to RobotKnowledge
		List<State> knowns = new LinkedList<State>();
		for (cleaningrobots.State s : exportR.getKnownStates()) {
			State st = robot.configuration.createState(s.getName());
			knowns.add(st);
			em.addStatesStringByteNumber(s.getName().getBytes().length);
			em.addStatesStringNumber(1);
		}
		importRk.setKnownStates(knowns);	
	}
	
	private void importRobotKnowledge (RobotKnowledge importRk, cleaningrobots.RobotKnowledge exportRk, RobotCore robot) {		
		if (importRk.getLastArrange() < exportRk.getLastArrange()) {
			em.addKnowledgeStringByteNumber(exportRk.getName().getBytes().length);
			em.addKnowledgeStringNumber(1);
			//other robot has seen him last so actuate the knowledge
			//TODO: proof if this must in or not
			//importRk.setLastArrange(exportRk.getLastArrange());
			em.addKnowledgeIntegerNumber(1);
			if (exportRk.getDestination() != null) {
				Position dest = new Position(exportRk.getDestination().getXpos(), exportRk.getDestination().getYpos());
				importRk.setLastDestination(dest);			
				em.addKnowledgeIntegerNumber(2);
			}
			importRk.setComponents(exportRk.getComponents());
			for (String s : exportRk.getComponents()) {
				em.addKnowledgeStringByteNumber(s.getBytes().length);
			}
			em.addKnowledgeStringNumber(exportRk.getComponents().size());
			//add Roles to RobotKnowledge
			List<RoleModel> newOnes = new ArrayList<RoleModel>();
			for (cleaningrobots.Role r : exportRk.getRoles()) {
				if (r instanceof cleaningrobots.MasterRole) {
					cleaningrobots.MasterRole m = (cleaningrobots.MasterRole) r;
					for (String s : m.getFollowerNames())
						em.addKnowledgeStringByteNumber(s.getBytes().length);
					em.addKnowledgeStringNumber(m.getFollowerNames().size());
					//add role to importKnowledge
					MasterRoleModel mrm = new MasterRoleModel();
					mrm.getFollowers().addAll(m.getFollowerNames());
					newOnes.add(mrm);
				} else {
					cleaningrobots.FollowerRole f = (cleaningrobots.FollowerRole) r;
					em.addKnowledgeStringNumber(1);
					em.addKnowledgeStringByteNumber(f.getMasterName().getBytes().length);
					//add role to importKnowledge
					FollowerRoleModel frm = new FollowerRoleModel();
					frm.setMaster(f.getMasterName());
					newOnes.add(frm);
				}
			}
			importRk.setRoles(newOnes);
			//add States to RobotKnowledge
			List<State> knowns = new LinkedList<State>();
			for (cleaningrobots.State s : exportRk.getKnowStates()) {
				State st = robot.configuration.createState(s.getName());
				knowns.add(st);
				em.addKnowledgeStringByteNumber(s.getName().getBytes().length);
				em.addKnowledgeStringNumber(1);
			}
			importRk.setKnownStates(knowns);
		}
	}
	
	private void importFieldsFromWorldModel(cleaningrobots.WorldPart worldPart, RobotCore importcore) {
		// Maybe an arrayList is better here?
		if (worldPart instanceof cleaningrobots.Map) {
			//x and yDim
			em.addWorldIntegerNumber(2);
			cleaningrobots.State blockedState = CleaningrobotsFactory.eINSTANCE.createState();
			//Search blockstate for isPassable value of field 
			blockedState.setName("Blocked");
			
			for (cleaningrobots.State worldState : worldPart.getWorldStates()) {
				State state = importcore.configuration.createState(worldState.getName());
				importcore.getWorld().addWorldState(state);
				em.addWorldStatesStringByteNumber(worldState.getName().getBytes().length);
				em.addWorldStatesStringNumber(1);
			}
			
			for (cleaningrobots.Field modelField : ((cleaningrobots.Map) worldPart)
					.getFields()) {
				//blockstate because of its passable
				boolean isBlocked = EMFUtils.listContains(modelField.getStates(), blockedState);
				//test about the supported states of the new robot
				//core is the new robot
				Field f = new Field(modelField.getPos().getXpos(), modelField.getPos().getYpos(), !isBlocked, configuration.wc.iteration);
				for (cleaningrobots.State modelState : modelField.getStates()) {
					State state = importcore.configuration.createState(modelState.getName());
					f.addState(state, configuration.wc.iteration);
					em.addWorldStringByteNumber(modelState.getName().getBytes().length);
					em.addWorldStringNumber(1);
				}
				em.addWorldPositionCount(1);
				importcore.getWorld().addField(f);
			}
			importcore.getWorld().resetNewInformationCounter();
		}
		if (worldPart instanceof cleaningrobots.World) {
			for (WorldPart innerWorldPart : ((cleaningrobots.World) worldPart)
					.getChildren()) {
				importFieldsFromWorldModel(innerWorldPart, importcore);
			}
		}
	}	
}
