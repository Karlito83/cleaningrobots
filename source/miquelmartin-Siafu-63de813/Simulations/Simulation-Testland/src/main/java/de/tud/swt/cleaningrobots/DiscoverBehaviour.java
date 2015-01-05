package de.tud.swt.cleaningrobots;

public class DiscoverBehaviour extends Behaviour {

	public DiscoverBehaviour(Robot robot) {
		super(robot);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean action() throws Exception {
		// TODO Auto-generated method stub
		this.getRobot().getWorld().getNextUnknownField();
		throw new Exception("Not yet implemented...");
	}

}
