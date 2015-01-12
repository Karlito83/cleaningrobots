package de.tud.swt.cleaningrobots;

public class DiscoverBehaviour extends Behaviour {

	public DiscoverBehaviour(Robot robot) {
		super(robot);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean action() throws Exception {
		boolean result = false;
		
		Position nextUnknownPosition = this.getRobot().getWorld().getNextUnknownPosition(); 
		if(nextUnknownPosition != null){
			getRobot().setDestination(nextUnknownPosition);
			result = true;
		}
		return result;		
	}

}
