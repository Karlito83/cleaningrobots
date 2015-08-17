package de.tud.swt.testland;

import de.nec.nle.siafu.model.Position;
import de.nec.nle.siafu.model.World;
import de.tud.swt.cleaningrobots.AgentNavigationAdapter;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.goals.ExploreDumpGoal;
import de.tud.swt.cleaningrobots.goals.MasterGoal;
import de.tud.swt.cleaningrobots.goals.WipeLoadGoal;
import de.tud.swt.cleaningrobots.goals.WlanActivateMasterPositionGoal;
import de.tud.swt.cleaningrobots.hardware.Accu;
import de.tud.swt.cleaningrobots.hardware.Motor;
import de.tud.swt.cleaningrobots.hardware.Wiper;
import de.tud.swt.cleaningrobots.hardware.Wlan;

public class WipeRobotAgent extends RobotAgent {

	public WipeRobotAgent(Position start, String image, World world) {
		super(start, image, world);

		siafuWorld = world;
		cleaningRobot = new RobotCore(this, new AgentNavigationAdapter(this, siafuWorld), this, new Accu(0, 500)); //new Accu(0, 1000)
		
		cleaningRobot.addHardwareComponent(new Wlan());
		cleaningRobot.addHardwareComponent(new Motor());
		cleaningRobot.addHardwareComponent(new Wiper());
		
		cleaningRobot.isLoadStation = false;
		cleaningRobot.calculateMaxMinEnergieConsumption();
		
		WipeLoadGoal wlg = new WipeLoadGoal(cleaningRobot);
		ExploreDumpGoal edg = new ExploreDumpGoal(cleaningRobot);
		WlanActivateMasterPositionGoal wlmmg = new WlanActivateMasterPositionGoal(cleaningRobot);
		
		MasterGoal mg = new MasterGoal(cleaningRobot);
		mg.subGoals.add(wlg);
		mg.subGoals.add(wlmmg);
		mg.subGoals.add(edg);
		
		if (mg.isHardwareCorrect())
		{
			cleaningRobot.getGoals().add(mg);
		}
	}

}
