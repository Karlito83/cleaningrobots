package de.tud.swt.cleaningrobots.merge;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import cleaningrobots.CleaningrobotsFactory;
import cleaningrobots.WorldPart;
import de.tud.evaluation.ExchangeMeasurement;
import de.tud.evaluation.WorkingConfiguration;
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

public class MergeAll {
	
	private ExchangeMeasurement em; 
	private WorkingConfiguration configuration;
	
	public MergeAll (WorkingConfiguration configuration) {
		this.configuration = configuration;
	}
	
	
	public void newInformationMeasure (String name) {
		em = new ExchangeMeasurement(name, "New Info", configuration.iteration);
		em.addKnowledgeIntegerNumber(1);
		em.addKnowledgeStringNumber(1);
		em.addKnowledgeStringByteNumber(name.getBytes().length);
		configuration.exchange.add(em);
	}
	
	public void importAllModel(EObject model, RobotCore importcore, ImportExportConfiguration config) {
		if (model instanceof cleaningrobots.Robot) {
			cleaningrobots.Robot robot = (cleaningrobots.Robot) model;
			
			//Namen Information die immer mitgesendet wird
			em = new ExchangeMeasurement(robot.getName(), importcore.getName(), configuration.iteration);
			em.addKnowledgeStringNumber(1);
			em.addKnowledgeStringByteNumber(robot.getName().getBytes().length);
			//für alle noch measurement erweitern
			if (config.knowledge)
			{
				//alls robotknowledges und eigene Daten durchlaufen und einfügen
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
						//Füge die neuen Information vom Robot ein
						List<State> knowns = new LinkedList<State>();
						for (cleaningrobots.State s : robot.getKnownStates()) {
							State st = ((State)importcore.configuration.as).createState(s.getName());
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
						State st = ((State)importcore.configuration.as).createState(s.getName());
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
				//World und Fields import
				cleaningrobots.WorldPart rootWorldPart = robot.getWorld();
				importFieldsFromWorldModel(rootWorldPart, importcore);
			}
			configuration.exchange.add(em);
		}
	}
	
	private void importRobotKnowledge (RobotKnowledge importRk, cleaningrobots.Robot exportR, RobotCore robot) {
		//Füge die neuen Information vom Robot ein
		importRk.setLastArrange(configuration.iteration);
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
				mrm.followers.addAll(m.getFollowerNames());
				newOnes.add(mrm);				
			} else {
				cleaningrobots.FollowerRole f = (cleaningrobots.FollowerRole) r;
				em.addKnowledgeStringNumber(1);
				em.addKnowledgeStringByteNumber(f.getMasterName().getBytes().length);
				//add role to importKnowledge
				FollowerRoleModel frm = new FollowerRoleModel();
				frm.master = f.getMasterName();
				newOnes.add(frm);
			}
		}
		importRk.setRoles(newOnes);
		//add states to RobotKnowledge
		List<State> knowns = new LinkedList<State>();
		for (cleaningrobots.State s : exportR.getKnownStates()) {
			State st = ((State)robot.configuration.as).createState(s.getName());
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
			//anderer Robot hat ihn als letztes gesehen also aktualisiere deine Daten
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
			//add Roles To RobotKnowledge
			List<RoleModel> newOnes = new ArrayList<RoleModel>();
			for (cleaningrobots.Role r : exportRk.getRoles()) {
				if (r instanceof cleaningrobots.MasterRole) {
					cleaningrobots.MasterRole m = (cleaningrobots.MasterRole) r;
					for (String s : m.getFollowerNames())
						em.addKnowledgeStringByteNumber(s.getBytes().length);
					em.addKnowledgeStringNumber(m.getFollowerNames().size());
					//add role to importKnowledge
					MasterRoleModel mrm = new MasterRoleModel();
					mrm.followers.addAll(m.getFollowerNames());
					newOnes.add(mrm);
				} else {
					cleaningrobots.FollowerRole f = (cleaningrobots.FollowerRole) r;
					em.addKnowledgeStringNumber(1);
					em.addKnowledgeStringByteNumber(f.getMasterName().getBytes().length);
					//add role to importKnowledge
					FollowerRoleModel frm = new FollowerRoleModel();
					frm.master = f.getMasterName();
					newOnes.add(frm);
				}
			}
			importRk.setRoles(newOnes);
			//add States to RobotKnowledge
			List<State> knowns = new LinkedList<State>();
			for (cleaningrobots.State s : exportRk.getKnowStates()) {
				State st = ((State)robot.configuration.as).createState(s.getName());
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
			//x und yDim
			em.addWorldIntegerNumber(2);
			cleaningrobots.State blockedState = CleaningrobotsFactory.eINSTANCE.createState();
			//Search blockstate for isPassable value of field 
			blockedState.setName("Blocked");
			
			for (cleaningrobots.State worldState : worldPart.getWorldStates()) {
				State state = ((State)importcore.configuration.as).createState(worldState.getName());
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
				Field f = new Field(modelField.getPos().getXpos(), modelField.getPos().getYpos(), !isBlocked, configuration.iteration);
				for (cleaningrobots.State modelState : modelField.getStates()) {
					State state = ((State)importcore.configuration.as).createState(modelState.getName());
					f.addState(state, configuration.iteration);
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
