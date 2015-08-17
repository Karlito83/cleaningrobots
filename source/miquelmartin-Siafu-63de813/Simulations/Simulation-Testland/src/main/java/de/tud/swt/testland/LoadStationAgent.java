package de.tud.swt.testland;

import de.nec.nle.siafu.model.Position;
import de.nec.nle.siafu.model.World;
import de.tud.swt.cleaningrobots.AgentNavigationAdapter;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.goals.ExploreDumpGoal;
import de.tud.swt.cleaningrobots.goals.LoadRobotGoal;
import de.tud.swt.cleaningrobots.goals.MasterGoal;
import de.tud.swt.cleaningrobots.goals.MergeMasterFollowerGoal;
import de.tud.swt.cleaningrobots.hardware.LoadStation;
import de.tud.swt.cleaningrobots.hardware.Wlan;

public class LoadStationAgent extends RobotAgent {

	public LoadStationAgent(Position start, String image, World world) {
		super(start, image, world);

		siafuWorld = world;
		cleaningRobot = new RobotCore(this, new AgentNavigationAdapter(this, siafuWorld), this, null);
		
		cleaningRobot.addHardwareComponent(new LoadStation());
		cleaningRobot.addHardwareComponent(new Wlan());
		
		//Wlan hinzufügen und MergeMasterFollower / DumpBehaviour
		cleaningRobot.isLoadStation = true;
		
		MergeMasterFollowerGoal mmfg = new MergeMasterFollowerGoal(cleaningRobot);
		LoadRobotGoal lrg = new LoadRobotGoal(cleaningRobot);
		ExploreDumpGoal edg = new ExploreDumpGoal(cleaningRobot);
		
		MasterGoal mg = new MasterGoal(cleaningRobot);
		mg.subGoals.add(mmfg);
		mg.subGoals.add(lrg);
		mg.subGoals.add(edg);
		
		if (mg.isHardwareCorrect())
		{
			cleaningRobot.getGoals().add(mg);
		}
		
		
		/*
		MergeMasterFollower mm = new MergeMasterFollower(cleaningRobot);
		System.out.println("Correct Load: " + mm.isHardwarecorrect());
		if (mm.isHardwarecorrect())
			cleaningRobot.addBehaviour(mm);
		
		LoadBehaviour l = new LoadBehaviour(cleaningRobot);
		System.out.println("Correct Load: " + l.isHardwarecorrect());
		if (l.isHardwarecorrect())
			cleaningRobot.addBehaviour(l);	
		
		DumpModelBehaviour b = new DumpModelBehaviour(cleaningRobot);
		System.out.println("Correct Dump: " + b.isHardwarecorrect());
		if (b.isHardwarecorrect())
			cleaningRobot.addBehaviour(b);*/
		
	}

}
