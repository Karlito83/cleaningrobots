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
				for (cleaningrobots.RobotKnowledge rk : robot.getRobotKnowledge()) {
					for (RobotKnowledge hisRk : importcore.getKnowledge()) {
						if (rk.getName().equals(hisRk.getName())) {
							//Vergleiche beide RobotKnowledges miteinander
							if (hisRk.getLastArrange() < rk.getLastArrange()) {
								//anderer Robot hat ihn als letztes gesehen also aktualisiere deine Daten
								hisRk.setLastArrange(rk.getLastArrange());
								em.addIntegerNumber(1);
								Position dest = new Position(rk.getDestination().getXpos(), rk.getDestination().getYpos());
								hisRk.setLastDestination(dest);
								em.addIntegerNumber(2);
								hisRk.setComponents(rk.getComponents());
								for (String s : rk.getComponents()) {
									em.addStringByteNumber(s.getBytes().length);
								}
								em.addStringNumber(rk.getComponents().size());
								hisRk.setRoles(rk.getRoles());
								for (cleaningrobots.Role r : rk.getRoles()) {
									if (r instanceof cleaningrobots.MasterRole) {
										cleaningrobots.MasterRole m = (cleaningrobots.MasterRole) r;
										for (String s : m.getFollowerNames())
											em.addStringByteNumber(s.getBytes().length);
										em.addStringNumber(m.getFollowerNames().size());
									} else {
										cleaningrobots.FollowerRole f = (cleaningrobots.FollowerRole) r;
										em.addStringNumber(1);
										em.addStringByteNumber(f.getMasterName().getBytes().length);
									}
								}
								List<State> knowns = new LinkedList<State>();
								for (cleaningrobots.State s : rk.getKnowStates()) {
									State st = State.createState(s.getName());
									knowns.add(st);
									em.addStringByteNumber(s.getName().getBytes().length);
									em.addStringNumber(1);
								}
								hisRk.setKnownStates(knowns);
							}
						}
						if (hisRk.getName().equals(robot.getName())) {
							//Füge die neuen Information vom Robot ein
							hisRk.setLastArrange(Variables.iteration);
							em.addIntegerNumber(1);
							Position dest = new Position(robot.getDestination().getXpos(), robot.getDestination().getYpos());
							hisRk.setLastDestination(dest);
							em.addIntegerNumber(2);
							hisRk.setComponents(robot.getComponents());
							for (String s : rk.getComponents()) {
								em.addStringByteNumber(s.getBytes().length);
							}
							em.addStringNumber(rk.getComponents().size());
							hisRk.setRoles(robot.getRoles());
							for (cleaningrobots.Role r : rk.getRoles()) {
								if (r instanceof cleaningrobots.MasterRole) {
									cleaningrobots.MasterRole m = (cleaningrobots.MasterRole) r;
									for (String s : m.getFollowerNames())
										em.addStringByteNumber(s.getBytes().length);
									em.addStringNumber(m.getFollowerNames().size());
								} else {
									cleaningrobots.FollowerRole f = (cleaningrobots.FollowerRole) r;
									em.addStringNumber(1);
									em.addStringByteNumber(f.getMasterName().getBytes().length);
								}
							}
							List<State> knowns = new LinkedList<State>();
							for (cleaningrobots.State s : robot.getKnownStates()) {
								State st = State.createState(s.getName());
								knowns.add(st);
								em.addStringByteNumber(s.getName().getBytes().length);
								em.addStringNumber(1);
							}
							hisRk.setKnownStates(knowns);						
						}
					}
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
	
	private void importFieldsFromWorldModel(cleaningrobots.WorldPart worldPart, RobotCore importcore) {
		// Maybe an arrayList is better here?
		if (worldPart instanceof cleaningrobots.Map) {
			//x und yDim
			em.addIntegerNumber(2);
			cleaningrobots.State blockedState = CleaningrobotsFactory.eINSTANCE
					.createState();
			//Search blockstate for isPassable value of field 
			blockedState.setName("Blocked");
			for (cleaningrobots.Field modelField : ((cleaningrobots.Map) worldPart)
					.getFields()) {
				//blockstate because of its passable
				boolean isBlocked = EMFUtils.listContains(modelField
						.getStates(), blockedState);
				//test about the supported states of the new robot
				//core is the new robot
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
