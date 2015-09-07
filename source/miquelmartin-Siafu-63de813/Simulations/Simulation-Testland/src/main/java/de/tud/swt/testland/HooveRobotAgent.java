package de.tud.swt.testland;

import de.nec.nle.siafu.model.Position;
import de.nec.nle.siafu.model.World;
import de.tud.swt.cleaningrobots.AgentNavigationAdapter;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.goals.ExploreDumpGoal;
import de.tud.swt.cleaningrobots.goals.HooveLoadGoal;
import de.tud.swt.cleaningrobots.goals.MasterGoal;
import de.tud.swt.cleaningrobots.goals.WlanActivateMasterPositionGoal;
import de.tud.swt.cleaningrobots.hardware.Accu;
import de.tud.swt.cleaningrobots.hardware.Hoover;
import de.tud.swt.cleaningrobots.hardware.Motor;
import de.tud.swt.cleaningrobots.hardware.Rechner;
import de.tud.swt.cleaningrobots.hardware.Wlan;

public class HooveRobotAgent extends RobotAgent {

	public HooveRobotAgent(Position start, String image, World world) {
		super(start, image, world);

		siafuWorld = world;
		cleaningRobot = new RobotCore(this, new AgentNavigationAdapter(this, siafuWorld), this, new Accu(0, 500)); //new Accu(0, 1000)
		
		cleaningRobot.addHardwareComponent(new Rechner());
		cleaningRobot.addHardwareComponent(new Wlan());
		cleaningRobot.addHardwareComponent(new Motor());
		cleaningRobot.addHardwareComponent(new Hoover());
		
		System.out.println("LoadStation: " + cleaningRobot.isLoadStation());
		
		HooveLoadGoal hlg = new HooveLoadGoal(cleaningRobot);
		ExploreDumpGoal edg = new ExploreDumpGoal(cleaningRobot);
		WlanActivateMasterPositionGoal wlmmg = new WlanActivateMasterPositionGoal(cleaningRobot);
		
		MasterGoal mg = new MasterGoal(cleaningRobot);
		mg.subGoals.add(hlg);
		mg.subGoals.add(wlmmg);
		mg.subGoals.add(edg);
		
		if (mg.isHardwareCorrect())
		{
			cleaningRobot.getGoals().add(mg);
		}
	}

}
