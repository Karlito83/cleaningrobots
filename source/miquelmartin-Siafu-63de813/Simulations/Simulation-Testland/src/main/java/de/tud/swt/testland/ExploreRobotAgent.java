package de.tud.swt.testland;

import de.nec.nle.siafu.model.Position;
import de.nec.nle.siafu.model.World;
import de.tud.swt.cleaningrobots.AgentNavigationAdapter;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.goals.ExploreDumpGoal;
import de.tud.swt.cleaningrobots.goals.ExploreLoadGoal;
import de.tud.swt.cleaningrobots.goals.MasterGoal;
import de.tud.swt.cleaningrobots.goals.WlanLoadMasterMergeGoal;
import de.tud.swt.cleaningrobots.hardware.Accu;
import de.tud.swt.cleaningrobots.hardware.LookAroundSensor;
import de.tud.swt.cleaningrobots.hardware.Motor;
import de.tud.swt.cleaningrobots.hardware.Wlan;

public class ExploreRobotAgent extends RobotAgent {

	public ExploreRobotAgent(Position start, String image, World world) {
		super(start, image, world);

		siafuWorld = world;
		cleaningRobot = new RobotCore(this, new AgentNavigationAdapter(this, siafuWorld), this, new Accu(0, 400)); //new Accu(0, 1000)
		
		cleaningRobot.addHardwareComponent(new Wlan());
		cleaningRobot.addHardwareComponent(new Motor());
		cleaningRobot.addHardwareComponent(new LookAroundSensor());
		
		cleaningRobot.isLoadStation = false;
		cleaningRobot.calculateMaxMinEnergieConsumption();
		
		ExploreLoadGoal elg = new ExploreLoadGoal(cleaningRobot);
		ExploreDumpGoal edg = new ExploreDumpGoal(cleaningRobot);
		WlanLoadMasterMergeGoal wlmmg = new WlanLoadMasterMergeGoal(cleaningRobot);
		
		MasterGoal mg = new MasterGoal(cleaningRobot);
		mg.subGoals.add(elg);
		mg.subGoals.add(wlmmg);
		mg.subGoals.add(edg);
		
		if (mg.isHardwareCorrect())
		{
			cleaningRobot.getGoals().add(mg);
		}

		/*SeeAroundBehaviour s = new SeeAroundBehaviour(cleaningRobot);
		System.out.println("Correct SeeAround: " + s.isHardwarecorrect());
		if (s.isHardwarecorrect())
			cleaningRobot.addBehaviour(s);
		
		DiscoverBehaviour d = new DiscoverBehaviour(cleaningRobot);
		System.out.println("Correct Discover: " + d.isHardwarecorrect());
		if (d.isHardwarecorrect())
			cleaningRobot.addBehaviour(d);
		
		MoveBehaviour m = new MoveBehaviour(cleaningRobot);
		System.out.println("Correct Move: " + m.isHardwarecorrect());
		if (m.isHardwarecorrect())
			cleaningRobot.addBehaviour(m);
				
		LoadWlanActivateBehaviour lab = new LoadWlanActivateBehaviour(cleaningRobot);
		System.out.println("Correct WlanActive: " + lab.isHardwarecorrect());
		if (lab.isHardwarecorrect())
			cleaningRobot.addBehaviour(lab);
		
		//MergeAllRonny a = new MergeAllRonny(cleaningRobot);
		System.out.println("Correct Merge: " + a.isHardwarecorrect());
		if (a.isHardwarecorrect())
			cleaningRobot.addBehaviour(a);
		
		DumpModelBehaviour b = new DumpModelBehaviour(cleaningRobot);
		System.out.println("Correct Dump: " + b.isHardwarecorrect());
		if (b.isHardwarecorrect())
			cleaningRobot.addBehaviour(b);*/
		
	}

}
