package de.tud.swt.cleaningrobots.merge;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import cleaningrobots.CleaningrobotsFactory;
import cleaningrobots.WorldPart;
import de.tud.swt.cleaningrobots.Configuration;
import de.tud.swt.cleaningrobots.AgentCore;
import de.tud.swt.cleaningrobots.AgentKnowledge;
import de.tud.swt.cleaningrobots.model.Field;
import de.tud.swt.cleaningrobots.model.FollowerRoleModel;
import de.tud.swt.cleaningrobots.model.MasterRoleModel;
import de.tud.swt.cleaningrobots.model.Position;
import de.tud.swt.cleaningrobots.model.RoleModel;
import de.tud.swt.cleaningrobots.model.State;
import de.tud.swt.cleaningrobots.util.EMFUtils;
import de.tud.swt.cleaningrobots.util.ImportExportConfiguration;

/**
 * Import and Export the Model and use intern the EMF Model. It only need the RobotCore of each Robot and its ImportExportConfiguration.
 * 
 * @author Christopher Werner
 *
 */
public class WorldEcoreModelMerge extends Merge {

	public WorldEcoreModelMerge(Configuration configuration) {
		super(configuration);
	}
	
	/**
	 * Import and Export the Model and use intern the EMF Model. It only need the RobotCore of each Robot and its ImportExportConfiguration.
	 * @param from (AgentCore where the data comes from)
	 * @param to (AgentCore where the data goes to)
	 * @param object (Data or information about what should be send)
	 */
	@Override
	protected void action(AgentCore from, AgentCore to, Object object) {
		ImportExportConfiguration config = (ImportExportConfiguration) object;
		EObject model = from.exportModel(config);
		if (model instanceof cleaningrobots.Robot) {
			cleaningrobots.Robot robot = (cleaningrobots.Robot) model;
			
			//name information which always send
			em.addKnowledgeStringNumber(1);
			em.addKnowledgeStringByteNumber(robot.getName().getBytes().length);
			//add robot knowledge
			if (config.knowledge)
			{
				//run over all robot knowledge and own knowledge and insert information
				for (cleaningrobots.RobotKnowledge exportRk : robot.getRobotKnowledge()) {
					boolean isIn = false;
					for (AgentKnowledge importRk : to.getKnowledge()) {
						if (exportRk.getName().equals(importRk.getName())) {
							isIn = true;
							importRobotKnowledge(importRk, exportRk, to);							
						}
					}
					if (!isIn) {
						AgentKnowledge rk = new AgentKnowledge(exportRk.getName());
						to.getKnowledge().add(rk);
						importRobotKnowledge(rk, exportRk, to);
					}
				}
				boolean isIn = false;
				for (AgentKnowledge importRk : to.getKnowledge()) {
					if (importRk.getName().equals(robot.getName())) {
						isIn = true;			
						importRobotKnowledge(importRk, robot, to);
					}
				}
				if (!isIn) {
					AgentKnowledge rk = new AgentKnowledge(robot.getName());
					to.getKnowledge().add(rk);
					importRobotKnowledge(rk, robot, to);
				}
			}
			if (config.knownstates && !config.knowledge)
			{
				boolean isIn = false;
				for (AgentKnowledge hisRk : to.getKnowledge()) {					
					if (hisRk.getName().equals(robot.getName())) {
						isIn = true;
						//insert the new information from the robot
						List<State> knowns = new LinkedList<State>();
						for (cleaningrobots.State s : robot.getKnownStates()) {
							State st = to.getConfiguration().createState(s.getName());
							knowns.add(st);
							em.addStatesStringByteNumber(s.getName().getBytes().length);
							em.addStatesStringNumber(1);
						}
						hisRk.setKnownStates(knowns);						
					}
				}
				if (!isIn) {
					AgentKnowledge rk = new AgentKnowledge(robot.getName());
					List<State> knowns = new LinkedList<State>();
					for (cleaningrobots.State s : robot.getKnownStates()) {
						State st = to.getConfiguration().createState(s.getName());
						knowns.add(st);
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
				cleaningrobots.WorldPart rootWorldPart = robot.getWorld();
				importFieldsFromWorldModel(rootWorldPart, to);
			}
		}
	}
	
	private void importRobotKnowledge (AgentKnowledge importRk, cleaningrobots.Robot exportR, AgentCore robot) {
		//insert the new information from the robot
		importRk.setLastArrange(configuration.getWc().iteration);
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
				MasterRoleModel mrm = new MasterRoleModel("MasterRole");
				mrm.getFollowers().addAll(m.getFollowerNames());
				newOnes.add(mrm);				
			} else {
				cleaningrobots.FollowerRole f = (cleaningrobots.FollowerRole) r;
				em.addKnowledgeStringNumber(1);
				em.addKnowledgeStringByteNumber(f.getMasterName().getBytes().length);
				//add role to importKnowledge
				FollowerRoleModel frm = new FollowerRoleModel("FollowerRole");
				frm.setMaster(f.getMasterName());
				newOnes.add(frm);
			}
		}
		importRk.setRoles(newOnes);
		//add states to RobotKnowledge
		List<State> knowns = new LinkedList<State>();
		for (cleaningrobots.State s : exportR.getKnownStates()) {
			State st = robot.getConfiguration().createState(s.getName());
			knowns.add(st);
			em.addStatesStringByteNumber(s.getName().getBytes().length);
			em.addStatesStringNumber(1);
		}
		importRk.setKnownStates(knowns);	
	}
	
	private void importRobotKnowledge (AgentKnowledge importRk, cleaningrobots.RobotKnowledge exportRk, AgentCore robot) {		
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
					MasterRoleModel mrm = new MasterRoleModel("MasterRole");
					mrm.getFollowers().addAll(m.getFollowerNames());
					newOnes.add(mrm);
				} else {
					cleaningrobots.FollowerRole f = (cleaningrobots.FollowerRole) r;
					em.addKnowledgeStringNumber(1);
					em.addKnowledgeStringByteNumber(f.getMasterName().getBytes().length);
					//add role to importKnowledge
					FollowerRoleModel frm = new FollowerRoleModel("FollowerRole");
					frm.setMaster(f.getMasterName());
					newOnes.add(frm);
				}
			}
			importRk.setRoles(newOnes);
			//add States to RobotKnowledge
			List<State> knowns = new LinkedList<State>();
			for (cleaningrobots.State s : exportRk.getKnowStates()) {
				State st = robot.getConfiguration().createState(s.getName());
				knowns.add(st);
				em.addKnowledgeStringByteNumber(s.getName().getBytes().length);
				em.addKnowledgeStringNumber(1);
			}
			importRk.setKnownStates(knowns);
		}
	}
	
	private void importFieldsFromWorldModel(cleaningrobots.WorldPart worldPart, AgentCore importcore) {
		// Maybe an arrayList is better here?
		if (worldPart instanceof cleaningrobots.Map) {
			//x and yDim
			em.addWorldIntegerNumber(2);
			cleaningrobots.State blockedState = CleaningrobotsFactory.eINSTANCE.createState();
			//Search blockstate for isPassable value of field 
			blockedState.setName("Blocked");
			
			for (cleaningrobots.State worldState : worldPart.getWorldStates()) {
				State state = importcore.getConfiguration().createState(worldState.getName());
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
				Field f = new Field(modelField.getPos().getXpos(), modelField.getPos().getYpos(), !isBlocked, configuration.getWc().iteration);
				for (cleaningrobots.State modelState : modelField.getStates()) {
					State state = importcore.getConfiguration().createState(modelState.getName());
					f.addState(state, configuration.getWc().iteration);
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
