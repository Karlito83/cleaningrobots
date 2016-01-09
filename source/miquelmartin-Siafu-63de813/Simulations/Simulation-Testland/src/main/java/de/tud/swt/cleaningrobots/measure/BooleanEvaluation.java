package de.tud.swt.cleaningrobots.measure;

/**
 * What field in what configuration you have and need.
 * 
 * @author Christopher Werner
 *
 */
public class BooleanEvaluation {
	
	//modell
	public boolean endmodellMaster;
	public boolean endmodellExplore;
	public boolean endmodellHoove;
	public boolean endModellWipe;
	
	//V...
	public boolean kompletterAustausch;
	public boolean sendNull;
	public boolean newInformation;
	public boolean sendNewInformation;
	public boolean other;
	
	//V0
	public boolean exploreExchange;
	public boolean hooveExchange;
	public boolean wipeExchange;
	
	public boolean completeS;
	public boolean completeK;
	
	public BooleanEvaluation() {
		endmodellMaster = false;
		endmodellExplore = false;
		endmodellHoove = false;
		endModellWipe = false;
		
		kompletterAustausch = false;
		sendNull = false;
		newInformation = false;
		sendNewInformation = false;
		other = false;
		
		exploreExchange = false;
		hooveExchange = false;
		wipeExchange = false;
		
		completeS = false;
		completeK = false;
	}
	
	public void setConfigurationD1 () {
		endmodellMaster = true;
		endmodellExplore = true;
		endmodellHoove = true;
		endModellWipe = true;
		
		completeS = true;
		completeK = true;
		kompletterAustausch = true;
		newInformation = true;		
	}
	
	public void setConfigurationD2 () {
		endmodellMaster = true;
		endmodellExplore = true;
		endmodellHoove = true;
		endModellWipe = true;
		
		kompletterAustausch = true;
		newInformation = true;
	}
	
	public void setConfigurationV2 () {
		endmodellMaster = true;
		endmodellExplore = true;
		endmodellHoove = true;
		endModellWipe = true;
		
		kompletterAustausch = true;
		newInformation = true;
		other = true;
	}
	
	public void setConfigurationV3V4 () {
		endmodellMaster = true;
		endmodellExplore = true;
		endmodellHoove = true;
		endModellWipe = true;
		
		kompletterAustausch = true;
		sendNull = true;
		newInformation = true;
		sendNewInformation = true;
		other = true;
	}
	
	public void setConfigurationV0 () {
		endmodellMaster = true;
		endmodellExplore = true;
		endmodellHoove = true;
		endModellWipe = true;
		
		exploreExchange = true;
		hooveExchange = true;
		wipeExchange = true;
		
		kompletterAustausch = true;
		other = true;
	}
	
	public void setConfigurationV1 () {
		endmodellExplore = true;
		endmodellHoove = true;
		endModellWipe = true;
		
		kompletterAustausch = true;
	}
}
