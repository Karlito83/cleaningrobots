package de.tud.swt.cleaningrobots.merge;

import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.emf.ecore.EObject;

import cleaningrobots.CleaningrobotsFactory;
import cleaningrobots.WorldPart;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.RobotKnowledge;
import de.tud.swt.cleaningrobots.measure.ExchangeMeasurement;
import de.tud.swt.cleaningrobots.model.Field;
import de.tud.swt.cleaningrobots.model.Position;
import de.tud.swt.cleaningrobots.model.State;
import de.tud.swt.cleaningrobots.util.EMFUtils;
import de.tud.swt.cleaningrobots.util.ImportExportConfiguration;
import de.tud.swt.cleaningrobots.util.Variables;

public class MergeAll {
	
	private ExchangeMeasurement em; 
	
	private final Logger logger = LogManager.getRootLogger();
	
	/*public void mergeAll () {
		
	}*/
	
	public void newInformationMeasure (String name) {
		em = new ExchangeMeasurement(name, "New Info", Variables.iteration);
		em.addIntegerNumber(1);
		em.addStringNumber(1);
		em.addStringByteNumber(name.getBytes().length);
		Variables.exchange.add(em);
	}
	
	public void importAllModel(EObject model, RobotCore importcore, ImportExportConfiguration config) {
		if (model instanceof cleaningrobots.Robot) {
			logger.trace("importing model " + model);
			cleaningrobots.Robot robot = (cleaningrobots.Robot) model;
			
			em = new ExchangeMeasurement(robot.getName(), importcore.getName(), Variables.iteration);
			em.addStringNumber(1);
			em.addStringByteNumber(robot.getName().getBytes().length);
			//für alle noch measurement erweitern
			if (config.knowledge)
			{
				//alls robotknowledges und eigene Daten durchlaufen und einfügen
				for (cleaningrobots.RobotKnowledge exportRk : robot.getRobotKnowledge()) {
					boolean isIn = false;
					for (RobotKnowledge importRk : importcore.getKnowledge()) {
						if (exportRk.getName().equals(importRk.getName())) {
							isIn = true;
							importRobotKnowledge(importRk, exportRk);							
						}
					}
					if (!isIn) {
						RobotKnowledge rk = new RobotKnowledge(exportRk.getName());
						importcore.getKnowledge().add(rk);
						importRobotKnowledge(rk, exportRk);
					}
				}
				boolean isIn = false;
				for (RobotKnowledge importRk : importcore.getKnowledge()) {
					if (importRk.getName().equals(robot.getName())) {
						isIn = true;			
						importRobotKnowledge(importRk, robot);
					}
				}
				if (!isIn) {
					RobotKnowledge rk = new RobotKnowledge(robot.getName());
					importcore.getKnowledge().add(rk);
					importRobotKnowledge(rk, robot);
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
							State st = State.createState(s.getName());
							knowns.add(st);
							em.addStringByteNumberKnowledge(s.getName().getBytes().length);
							em.addStringNumberKnowledge(1);
						}
						hisRk.setKnownStates(knowns);						
					}
				}
				if (!isIn) {
					RobotKnowledge rk = new RobotKnowledge(robot.getName());
					List<State> knowns = new LinkedList<State>();
					for (cleaningrobots.State s : robot.getKnownStates()) {
						State st = State.createState(s.getName());
						knowns.add(st);
						em.addStringByteNumberKnowledge(s.getName().getBytes().length);
						em.addStringNumberKnowledge(1);
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
			Variables.exchange.add(em);
		} else {
			logger.warn("unknown model " + model);
		}
	}
	
	private void importRobotKnowledge (RobotKnowledge importRk, cleaningrobots.Robot exportR) {
		//Füge die neuen Information vom Robot ein
		em.addStringByteNumberKnowledge(exportR.getName().getBytes().length);
		em.addStringNumberKnowledge(1);
		importRk.setLastArrange(Variables.iteration);
		em.addIntegerNumberKnowledge(1);
		if (exportR.getDestination() != null) {
			Position dest = new Position(exportR.getDestination().getXpos(), exportR.getDestination().getYpos());
			importRk.setLastDestination(dest);
			em.addIntegerNumberKnowledge(2);
		}
		importRk.setComponents(exportR.getComponents());
		for (String s : exportR.getComponents()) {
			em.addStringByteNumberKnowledge(s.getBytes().length);
		}
		em.addStringNumberKnowledge(exportR.getComponents().size());
		importRk.setRoles(exportR.getRoles());
		for (cleaningrobots.Role r : exportR.getRoles()) {
			if (r instanceof cleaningrobots.MasterRole) {
				cleaningrobots.MasterRole m = (cleaningrobots.MasterRole) r;
				for (String s : m.getFollowerNames())
					em.addStringByteNumberKnowledge(s.getBytes().length);
				em.addStringNumberKnowledge(m.getFollowerNames().size());
			} else {
				cleaningrobots.FollowerRole f = (cleaningrobots.FollowerRole) r;
				em.addStringNumberKnowledge(1);
				em.addStringByteNumberKnowledge(f.getMasterName().getBytes().length);
			}
		}
		List<State> knowns = new LinkedList<State>();
		for (cleaningrobots.State s : exportR.getKnownStates()) {
			State st = State.createState(s.getName());
			knowns.add(st);
			em.addStringByteNumberKnowledge(s.getName().getBytes().length);
			em.addStringNumberKnowledge(1);
		}
		importRk.setKnownStates(knowns);	
	}
	
	private void importRobotKnowledge (RobotKnowledge importRk, cleaningrobots.RobotKnowledge exportRk) {		
		if (importRk.getLastArrange() < exportRk.getLastArrange()) {
			em.addStringByteNumberKnowledge(exportRk.getName().getBytes().length);
			em.addStringNumberKnowledge(1);
			//anderer Robot hat ihn als letztes gesehen also aktualisiere deine Daten
			importRk.setLastArrange(exportRk.getLastArrange());
			em.addIntegerNumberKnowledge(1);
			if (exportRk.getDestination() != null) {
				Position dest = new Position(exportRk.getDestination().getXpos(), exportRk.getDestination().getYpos());
				importRk.setLastDestination(dest);			
				em.addIntegerNumberKnowledge(2);
			}
			importRk.setComponents(exportRk.getComponents());
			for (String s : exportRk.getComponents()) {
				em.addStringByteNumberKnowledge(s.getBytes().length);
			}
			em.addStringNumberKnowledge(exportRk.getComponents().size());
			importRk.setRoles(exportRk.getRoles());
			for (cleaningrobots.Role r : exportRk.getRoles()) {
				if (r instanceof cleaningrobots.MasterRole) {
					cleaningrobots.MasterRole m = (cleaningrobots.MasterRole) r;
					for (String s : m.getFollowerNames())
						em.addStringByteNumberKnowledge(s.getBytes().length);
					em.addStringNumberKnowledge(m.getFollowerNames().size());
				} else {
					cleaningrobots.FollowerRole f = (cleaningrobots.FollowerRole) r;
					em.addStringNumberKnowledge(1);
					em.addStringByteNumberKnowledge(f.getMasterName().getBytes().length);
				}
			}
			List<State> knowns = new LinkedList<State>();
			for (cleaningrobots.State s : exportRk.getKnowStates()) {
				State st = State.createState(s.getName());
				knowns.add(st);
				em.addStringByteNumberKnowledge(s.getName().getBytes().length);
				em.addStringNumberKnowledge(1);
			}
			importRk.setKnownStates(knowns);
		}
	}
	
	private void importFieldsFromWorldModel(cleaningrobots.WorldPart worldPart, RobotCore importcore) {
		// Maybe an arrayList is better here?
		if (worldPart instanceof cleaningrobots.Map) {
			//x und yDim
			em.addIntegerNumber(2);
			cleaningrobots.State blockedState = CleaningrobotsFactory.eINSTANCE.createState();
			//cleaningrobots.State freeState = CleaningrobotsFactory.eINSTANCE.createState();
			//Search blockstate for isPassable value of field 
			blockedState.setName("Blocked");
			
			for (cleaningrobots.State worldState : worldPart.getWorldStates()) {
				State state = State.createState(worldState.getName());
				importcore.getWorld().addWorldState(state);
				em.addStringByteNumber(worldState.getName().getBytes().length);
				em.addStringNumber(1);
			}
			
			//freeState.setName("Free");
			for (cleaningrobots.Field modelField : ((cleaningrobots.Map) worldPart)
					.getFields()) {
				//blockstate because of its passable
				boolean isBlocked = EMFUtils.listContains(modelField.getStates(), blockedState);
				//boolean isFree = EMFUtils.listContains(modelField.getStates(), freeState);
				//test about the supported states of the new robot
				//core is the new robot
				/*boolean passable;
				if (isBlocked) {
					passable = false;
				}
				if (isFree) {
					passable = true;
				}*/
				Field f = new Field(modelField.getPos().getXpos(), modelField.getPos().getYpos(), !isBlocked);
				for (cleaningrobots.State modelState : modelField.getStates()) {
					State state = State.createState(modelState.getName());
					f.addState(state);
					em.addStringByteNumber(modelState.getName().getBytes().length);
					em.addStringNumber(1);
				}
				em.addIntegerNumber(2);
				importcore.getWorld().addField(f);
			}
		}
		if (worldPart instanceof cleaningrobots.World) {
			for (WorldPart innerWorldPart : ((cleaningrobots.World) worldPart)
					.getChildren()) {
				importFieldsFromWorldModel(innerWorldPart, importcore);
			}
		}
	}

	
	
}
