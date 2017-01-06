package de.tud.swt.cleaningrobots.behaviours.merge;

import java.util.LinkedList;
import java.util.List;

import de.tud.swt.cleaningrobots.Behaviour;
import de.tud.swt.cleaningrobots.AgentCore;
import de.tud.swt.cleaningrobots.AgentRole;
import de.tud.swt.cleaningrobots.hardware.ComponentTypes;
import de.tud.swt.cleaningrobots.hardware.Wlan;
import de.tud.swt.cleaningrobots.merge.Merge;
import de.tud.swt.cleaningrobots.merge.WorldEcoreModelMerge;
import de.tud.swt.cleaningrobots.merge.WorldMerge;
import de.tud.swt.cleaningrobots.util.ImportExportConfiguration;
import de.tud.swt.cleaningrobots.util.NearAgentInformation;

/**
 * Behavior which realize the data exchange and integration between two robots.
 * Without any roles and without the EMF model.
 * 
 * @author Christopher Werner
 *
 */
public class MergeAllNearBehaviour extends Behaviour {
	
	private int visionRadius;
	private Merge ma;	
	private int maxCount;
	private List<NearAgentInformation> robotInformation;
	
	public MergeAllNearBehaviour (AgentRole role, boolean useModel) {
		super(role);
		
		if (useModel)
		{
			this.ma = new WorldEcoreModelMerge(this.agentCore.getConfiguration());
		}
		else
		{
			this.ma = new WorldMerge(this.agentCore.getConfiguration());
		}
		this.maxCount = 200;
		this.robotInformation = new LinkedList<NearAgentInformation>();
		
		Wlan wlan = (Wlan) this.demand.getHardwareComponent(ComponentTypes.WLAN);
		this.visionRadius = wlan.getMeasurementRange();			
	}
	
	@Override
	protected void addSupportedStates() {
		//no states needed...		
	}

	@Override
	protected void addHardwareComponents() {
		this.demand.addDemandPair(ComponentTypes.WLAN, 1);
	}

	@Override
	public boolean action() {
		
		//start all hardware components
		this.demand.switchAllOn();
				
		//increment all counters or reset them
		for (NearAgentInformation i: robotInformation) {
			if (i.getCounter() > -1) {
				i.addCounterOne();
				if (i.getCounter() > maxCount)
					i.resetCounter();
			}
		}		
		
		if (this.agentCore.isLoading())
			return false;
						
		List<AgentCore> nearRobots = this.agentCore.getICommunicationAdapter().getNearRobots(this.visionRadius);
		nearRobots.remove(this.agentCore);
		for (AgentCore nearRobot : nearRobots) {
			//could only communicate with near robots if they have active WLAN
			if (nearRobot.hasActiveHardwareComponent(ComponentTypes.WLAN)) {
				for (NearAgentInformation i: robotInformation) {
					if (i.getName().equals(nearRobot.getName())) {
						if (i.getCounter() == -1) {
							ImportExportConfiguration config = new ImportExportConfiguration();
							config.world = true;
							config.knownstates = true;
							config.knowledge = true;
							ma.run(nearRobot, this.agentCore, config);
							i.addCounterOne();
						}
					}
				}				
			}
		}
		return false;
	}

	@Override
	public void initialiseBehaviour() {
		//create robot information map
		List<AgentCore> nearRobots = this.agentCore.getICommunicationAdapter().getAllRobots();
		nearRobots.remove(this.agentCore);
		
		for (AgentCore core : nearRobots) {
			robotInformation.add(new NearAgentInformation(core.getName()));
		}		
	}
}
