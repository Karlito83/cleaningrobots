package de.tud.swt.cleaningrobots.behaviours;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.emf.ecore.EObject;

import cleaningrobots.CleaningrobotsFactory;
import cleaningrobots.WorldPart;
import de.tud.swt.cleaningrobots.Behaviour;
import de.tud.swt.cleaningrobots.Demand;
import de.tud.swt.cleaningrobots.EMFUtils;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.hardware.Components;
import de.tud.swt.cleaningrobots.hardware.HardwareComponent;
import de.tud.swt.cleaningrobots.hardware.Wlan;
import de.tud.swt.cleaningrobots.model.Field;
import de.tud.swt.cleaningrobots.model.State;

public class MergeAllRonny extends Behaviour {

	private final Logger logger = LogManager.getRootLogger();
	
	private Wlan wlan;
	
	public MergeAllRonny(RobotCore robot) {
		super(robot);
		
		Map<Components, Integer> hardware = new EnumMap<Components, Integer> (Components.class);
		hardware.put(Components.WLAN, 1);
		
		d = new Demand(hardware, robot);
		hardwarecorrect = d.isCorrect();
		
		//Vision Radius aus Wlan Hardwarecomponente ziehen
		for (HardwareComponent hard : d.getHcs())
		{
			if (hard.getComponents() == Components.WLAN)
			{
				wlan = (Wlan)hard;
			}
		}
	}

	@Override
	public boolean action() {
		
		//Schalte alle Hardwarecomponenten an wenn sie nicht schon laufen
		for (HardwareComponent hard : d.getHcs())
		{
			if (!hard.isActive())
			{
				hard.changeActive();
			}
		}
		
		//Tausche komplettes modell von ronny
		long endTime, startTime = System.nanoTime();
		logger.trace("enter getNearRobotsAndImportModel");
		
		List<RobotCore> nearRobots = this.getRobot().getICommunicationProvider().getNearRobots(wlan.getVisionRadius());
		nearRobots.remove(this.getRobot());
		for (RobotCore nearRobot : nearRobots) {
			//darf nur mi Robotern in der nähe Kommunizieren wenn diese Wirklich die gleiche HardwareComponente habe und diese aktiv ist
			if (nearRobot.hasActiveHardwareComponent(wlan.getComponents())) {
				EObject model = nearRobot.exportModel();
				importModel(model);
			}
		}
		
		endTime = System.nanoTime();
		logger.info("Importing the data from " + nearRobots.size() + " other agents took " + (endTime - startTime) + " ns.");
		
		logger.trace("exit getNearRobotsAndImportModel");
		return false;
	}
	
	private void importFieldsFromWorldModel(cleaningrobots.WorldPart worldPart) {
		// Maybe an arrayList is better here?
		if (worldPart instanceof cleaningrobots.Map) {
			cleaningrobots.State blockedState = CleaningrobotsFactory.eINSTANCE
					.createState();
			blockedState.setName("Blocked");
			for (cleaningrobots.Field modelField : ((cleaningrobots.Map) worldPart)
					.getFields()) {
				boolean isBlocked = EMFUtils.listContains(modelField
						.getStates(), blockedState);
				Field f = new Field(modelField.getXpos(), modelField.getYpos(), !isBlocked);
				for (cleaningrobots.State modelState : modelField.getStates()) {
					State state = State.createState(modelState.getName());
					f.addState(state);
				}
				this.getRobot().getWorld().addField(f);
			}
		}
		if (worldPart instanceof cleaningrobots.World) {
			for (WorldPart innerWorldPart : ((cleaningrobots.World) worldPart)
					.getChildren()) {
				importFieldsFromWorldModel(innerWorldPart);
			}
		}
	}

	private void importModel(EObject model) {
		if (model instanceof cleaningrobots.Robot) {
			logger.trace("importing model " + model);
			cleaningrobots.Robot robot = (cleaningrobots.Robot) model;
			cleaningrobots.WorldPart rootWorldPart = robot.getWorld();
			importFieldsFromWorldModel(rootWorldPart);
		} else {
			logger.warn("unknown model " + model);
		}
	}
}
