package de.tud.swt.cleaningrobots.measure;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import de.tud.evaluation.EvaluationMeasurement;
import de.tud.evaluation.ExchangeMeasurement;

/**
 * Create CSV documents from every test case with the important information.
 * 
 * @author Christopher Werner
 *
 */
public class CSVCreation {

	public static void main(String[] args) {
		
		System.out.println("Start");		
		CSVCreation e = new CSVCreation();
		String path = "F:\\Masterarbeit\\Ergebnisse\\";
		e.mapZero(path);
		//e.mapOneAll(path);
		//e.mapThree(path);		
		System.out.println("Ready");		
	}	
	
	public void mapOneAll (String path) {
		BooleanEvaluation b = new BooleanEvaluation();
		b.setConfigurationV0();
		mapOne(path,0,0,0,"D", b);
		System.out.println("Run1");
		/*b = new BooleanEvaluation();
		b.setConfigurationV1();
		mapOne(path,0,1,0,"D", b);
		System.out.println("Run2");
		b = new BooleanEvaluation();
		b.setConfigurationV2();
		mapOne(path,0,2,0,"D", b);
		System.out.println("Run3");
		mapOne(path,0,2,1000,"D", b);
		System.out.println("Run4");
		mapOne(path,0,2,2000,"D", b);
		System.out.println("Run5");
		mapOne(path,0,2,3000,"D", b);
		System.out.println("Run6");
		mapOne(path,0,2,4000,"D", b);
		System.out.println("Run7");
		mapOne(path,0,2,5000,"D", b);
		System.out.println("Run8");
		b = new BooleanEvaluation();
		b.setConfigurationV3V4();
		mapOne(path,0,3,0,"D", b);
		System.out.println("Run9");
		mapOne(path,0,3,1000,"D", b);
		System.out.println("Run10");
		mapOne(path,0,3,2000,"D", b);
		System.out.println("Run11");
		mapOne(path,0,3,3000,"D", b);
		System.out.println("Run12");
		mapOne(path,0,3,4000,"D", b);
		System.out.println("Run13");
		mapOne(path,0,3,5000,"D", b);
		System.out.println("Run14");
		mapOne(path,0,4,0,"D", b);
		System.out.println("Run15");
		mapOne(path,0,4,1000,"D", b);
		System.out.println("Run16");
		mapOne(path,0,4,2000,"D", b);
		System.out.println("Run17");
		mapOne(path,0,4,3000,"D", b);
		System.out.println("Run18");
		mapOne(path,0,4,4000,"D", b);
		System.out.println("Run19");
		mapOne(path,0,4,5000,"D", b);
		System.out.println("Run20");*/	
	}
	
	public void mapThree (String path) {
		BooleanEvaluation b = new BooleanEvaluation();
		b.setConfigurationV0();
		versionZero(path,2,0,0,"D", b);
		System.out.println("Run1");
		b = new BooleanEvaluation();
		b.setConfigurationV1();
		versionZero(path,2,1,0,"D", b);
		System.out.println("Run2");
		b = new BooleanEvaluation();
		b.setConfigurationV2();
		versionZero(path,2,2,0,"D", b);
		System.out.println("Run3");
		versionZero(path,2,2,1000,"D", b);
		System.out.println("Run4");
		versionZero(path,2,2,2000,"D", b);
		System.out.println("Run5");
		versionZero(path,2,2,3000,"D", b);
		System.out.println("Run6");
		versionZero(path,2,2,4000,"D", b);
		System.out.println("Run7");
		versionZero(path,2,2,5000,"D", b);
		System.out.println("Run8");
		/*b = new BooleanEvaluation();
		b.setConfigurationV3V4();
		versionZero(path,2,3,0,"D", b);
		System.out.println("Run9");
		versionZero(path,2,3,1000,"D", b);
		System.out.println("Run10");
		versionZero(path,2,3,2000,"D", b);
		System.out.println("Run11");
		versionZero(path,2,3,3000,"D", b);
		System.out.println("Run12");
		versionZero(path,2,3,4000,"D", b);
		System.out.println("Run13");
		versionZero(path,2,3,5000,"D", b);
		System.out.println("Run14");
		versionZero(path,2,4,0,"D", b);
		System.out.println("Run15");
		versionZero(path,2,4,1000,"D", b);
		System.out.println("Run16");
		versionZero(path,2,4,2000,"D", b);
		System.out.println("Run17");
		versionZero(path,2,4,3000,"D", b);
		System.out.println("Run18");
		versionZero(path,2,4,4000,"D", b);
		System.out.println("Run19");
		versionZero(path,2,4,5000,"D", b);
		System.out.println("Run20");*/	
	}
	
	public void mapZero (String path) {
		BooleanEvaluation b = new BooleanEvaluation();
		/*b.setConfigurationV0();
		versionZero(path,0,0,0,"D", b);
		System.out.println("Run1");
		b = new BooleanEvaluation();
		b.setConfigurationV1();
		versionZero(path,0,1,0,"D", b);
		System.out.println("Run2");
		b = new BooleanEvaluation();
		b.setConfigurationV2();
		versionZero(path,0,2,0,"D", b);
		System.out.println("Run3");
		versionZero(path,0,2,1000,"D", b);
		System.out.println("Run4");
		versionZero(path,0,2,2000,"D", b);
		System.out.println("Run5");
		versionZero(path,0,2,3000,"D", b);
		System.out.println("Run6");
		versionZero(path,0,2,4000,"D", b);
		System.out.println("Run7");
		versionZero(path,0,2,5000,"D", b);
		System.out.println("Run8");
		b = new BooleanEvaluation();*/
		b.setConfigurationV3V4();
		versionZero(path,2,3,0,"D", b);
		System.out.println("Run9");
		/*versionZero(path,0,3,1000,"D", b);
		System.out.println("Run10");
		versionZero(path,0,3,2000,"D", b);
		System.out.println("Run11");
		versionZero(path,0,3,3000,"D", b);
		System.out.println("Run12");
		versionZero(path,0,3,4000,"D", b);
		System.out.println("Run13");
		versionZero(path,0,3,5000,"D", b);
		System.out.println("Run14");*/
		versionZero(path,2,4,0,"D", b);
		System.out.println("Run15");
		/*versionZero(path,0,4,1000,"D", b);
		System.out.println("Run16");
		versionZero(path,0,4,2000,"D", b);
		System.out.println("Run17");
		versionZero(path,0,4,3000,"D", b);
		System.out.println("Run18");
		versionZero(path,0,4,4000,"D", b);
		System.out.println("Run19");
		versionZero(path,0,4,5000,"D", b);
		System.out.println("Run20");*/
		/*
		b = new BooleanEvaluation();
		b.setConfigurationD1();
		versionDataExchange(path, 1,"D1", b);
		System.out.println("RunD1");
		b = new BooleanEvaluation();
		b.setConfigurationD2();
		versionDataExchange(path, 2,"D2", b);
		System.out.println("RunD2");*/	
	}
	
	public double getCompleteByteNumber (List<EvaluationMeasurement> measure, String name, int counter) {
		for (EvaluationMeasurement em : measure) {
			if (em.getName2().equals(name)) {
				if (counter == 0)
					return em.getCompleteByteNumber();
				if (counter == 1)
					return em.getCompleteNumberExchange();
				if (counter == 2)
					return em.getKnowledgeNumberExchange();
				if (counter == 3)
					return em.getWorldNumberExchange();
				if (counter == 4)
					return em.getNumber();
			}
		}
		return 0;
	}
	
	public double getCompleteByteNumberFinal (List<EvaluationMeasurement> measure, String name, int robbiNumber, int counter) {
		for (EvaluationMeasurement em : measure) {
			if (em.getName2().equals(name) && em.getName1().equals("Robbi_" + robbiNumber)) {
				if (counter == 0)
					return em.getCompleteByteNumber();
				if (counter == 1)
					return em.getTimeStampCompleteNumberExchange();
				if (counter == 2)
					return em.getKnowledgeNumberExchange();
				if (counter == 3)
					return em.getWorldNumberExchange();
				if (counter == 4)
					return em.getCompleteNumberExchange();
			}
		}
		return 0;
	}
	
	public List<EvaluationMeasurement> mergeEvaluationMeasurement (List<ExchangeMeasurement> measure) {
		List<EvaluationMeasurement> result = new ArrayList<EvaluationMeasurement>();
		EvaluationMeasurement complete = new EvaluationMeasurement("Master", "Complete", 0);
		EvaluationMeasurement explore = new EvaluationMeasurement("Master", "ExploreInformationNewDest", 0);
		EvaluationMeasurement wipe = new EvaluationMeasurement("Master", "WipeInformationNewDest", 0);
		EvaluationMeasurement hoove = new EvaluationMeasurement("Master", "HooveInformationNewDest", 0);
		EvaluationMeasurement sendNew = new EvaluationMeasurement("Master", "SendNewDest", 0);
		EvaluationMeasurement sendNull = new EvaluationMeasurement("Master", "SendNULLDest", 0);
		EvaluationMeasurement other = new EvaluationMeasurement("Master", "Other", 0);
		
		EvaluationMeasurement infoNew = new EvaluationMeasurement("Master", "New Info", 0);
		boolean isExplore = false, isHoove = false, isWipe = false, isNew = false, isNull = false, isInfoNew = false, isOther = false;
		for (ExchangeMeasurement em : measure) {
			if (em.getName2().equals("ExploreInformationNewDest")) {
				complete.addMeasurement(em);
				complete.setNumber(complete.getNumber() + 1);
				explore.addMeasurement(em);
				explore.setNumber(explore.getNumber() + 1);
				isExplore = true;
			} else if (em.getName2().equals("HooveInformationNewDest")) {
				complete.addMeasurement(em);
				complete.setNumber(complete.getNumber() + 1);
				hoove.addMeasurement(em);
				hoove.setNumber(hoove.getNumber() + 1);
				isHoove = true;
			} else if (em.getName2().equals("WipeInformationNewDest")) {
				complete.addMeasurement(em);
				complete.setNumber(complete.getNumber() + 1);
				wipe.addMeasurement(em);
				wipe.setNumber(wipe.getNumber() + 1);
				isWipe = true;
			} else if (em.getName2().equals("SendNULLDest")) {
				complete.addMeasurement(em);
				complete.setNumber(complete.getNumber() + 1);
				sendNull.addMeasurement(em);
				sendNull.setNumber(sendNull.getNumber() + 1);
				isNull = true;
			} else if (em.getName2().equals("SendNewDest")) {
				complete.addMeasurement(em);
				complete.setNumber(complete.getNumber() + 1);
				sendNew.addMeasurement(em);
				sendNew.setNumber(sendNew.getNumber() + 1);
				isNew = true;
			} else if (em.getName2().equals("New Info")) {
				complete.addMeasurement(em);
				complete.setNumber(complete.getNumber() + 1);
				infoNew.addMeasurement(em);
				infoNew.setNumber(infoNew.getNumber() + 1);
				isInfoNew = true;
			} else if (em.getName2().equals("FinalModel")) {
				EvaluationMeasurement finalModel = new EvaluationMeasurement(em.getName1(), em.getName2(), em.getIteration());
				finalModel.addMeasurement(em);
				result.add(finalModel);
			} else {
				complete.addMeasurement(em);
				complete.setNumber(complete.getNumber() + 1);
				other.addMeasurement(em);
				other.setNumber(other.getNumber() + 1);
				isOther = true;
			}
		}
		if (isExplore || isHoove || isWipe || isNew || isNull || isInfoNew || isOther)
			result.add(complete);
		if (isOther)
			result.add(other);
		if (isInfoNew)
			result.add(infoNew);
		if (isExplore)
			result.add(explore);
		if (isHoove)
			result.add(hoove);
		if (isWipe)
			result.add(wipe);
		if (isNew)
			result.add(sendNew);
		if (isNull)
			result.add(sendNull);
		
		return result;
	}
	
	public void versionZero (String path, int mapValue, int configValue, int newFieldValue, String zusatz, BooleanEvaluation booleanConfig) {
		//Listen Mit MinMaxAve werten zum einfügen der gefundenen Werte
		List<MinMaxAveLong> benchmarkTimeList = new LinkedList<MinMaxAveLong>();
		List<MinMaxAveInt> completeTicksList = new LinkedList<MinMaxAveInt>();
		List<MinMaxAveDouble> completeTimeList = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> completeEnergieList = new LinkedList<MinMaxAveDouble>();		
		//0
		List<MinMaxAveDouble> finalMasterList0 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> finalHooveList0 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> finalExploreList0 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> finalWipeList0 = new LinkedList<MinMaxAveDouble>();		
		List<MinMaxAveDouble> exploreList0 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> hooveList0 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> wipeList0 = new LinkedList<MinMaxAveDouble>();		
		List<MinMaxAveDouble> completeExchangeList0 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> otherList0 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> sendNewList0 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> sendNullList0 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> infoNewList0 = new LinkedList<MinMaxAveDouble>();
		//1
		List<MinMaxAveDouble> finalMasterList1 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> finalHooveList1 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> finalExploreList1 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> finalWipeList1 = new LinkedList<MinMaxAveDouble>();		
		List<MinMaxAveDouble> exploreList1 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> hooveList1 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> wipeList1 = new LinkedList<MinMaxAveDouble>();		
		List<MinMaxAveDouble> completeExchangeList1 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> otherList1 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> sendNewList1 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> sendNullList1 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> infoNewList1 = new LinkedList<MinMaxAveDouble>();
		//2
		List<MinMaxAveDouble> finalMasterList2 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> finalHooveList2 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> finalExploreList2 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> finalWipeList2 = new LinkedList<MinMaxAveDouble>();		
		List<MinMaxAveDouble> exploreList2 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> hooveList2 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> wipeList2 = new LinkedList<MinMaxAveDouble>();		
		List<MinMaxAveDouble> completeExchangeList2 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> otherList2 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> sendNewList2 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> sendNullList2 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> infoNewList2 = new LinkedList<MinMaxAveDouble>();
		//3
		List<MinMaxAveDouble> finalMasterList3 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> finalHooveList3 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> finalExploreList3 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> finalWipeList3 = new LinkedList<MinMaxAveDouble>();		
		List<MinMaxAveDouble> exploreList3 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> hooveList3 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> wipeList3 = new LinkedList<MinMaxAveDouble>();		
		List<MinMaxAveDouble> completeExchangeList3 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> otherList3 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> sendNewList3 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> sendNullList3 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> infoNewList3 = new LinkedList<MinMaxAveDouble>();
		//4
		List<MinMaxAveDouble> finalMasterList4 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> finalHooveList4 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> finalExploreList4 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> finalWipeList4 = new LinkedList<MinMaxAveDouble>();		
		List<MinMaxAveDouble> exploreList4 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> hooveList4 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> wipeList4 = new LinkedList<MinMaxAveDouble>();		
		List<MinMaxAveDouble> completeExchangeList4 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> otherList4 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> sendNewList4 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> sendNullList4 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> infoNewList4 = new LinkedList<MinMaxAveDouble>();
		
		//add for exploreRobots
		for (int anzExploreRobots = 1; anzExploreRobots <= 10; anzExploreRobots++) {
			
			int numberRobots = anzExploreRobots;
			//roboter information
			MinMaxAveLong mmaBench = new MinMaxAveLong(numberRobots);
			MinMaxAveInt mmaTicks = new MinMaxAveInt(numberRobots);
			MinMaxAveDouble mmaTime = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaEnergie = new MinMaxAveDouble(numberRobots);
			
			//0 data exchange information
			MinMaxAveDouble mmaComplete0 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaOther0 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaSendNew0 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaSendNull0 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaInfoNew0 = new MinMaxAveDouble(numberRobots);			
			MinMaxAveDouble mmaExplore0 = new MinMaxAveDouble(numberRobots);	
			MinMaxAveDouble mmaHoove0 = new MinMaxAveDouble(numberRobots);	
			MinMaxAveDouble mmaWipe0 = new MinMaxAveDouble(numberRobots);			
			MinMaxAveDouble mmaFinalMaster0 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalExplore0 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalHoove0 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalWipe0 = new MinMaxAveDouble(numberRobots);
			//1
			MinMaxAveDouble mmaComplete1 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaOther1 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaSendNew1 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaSendNull1 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaInfoNew1 = new MinMaxAveDouble(numberRobots);			
			MinMaxAveDouble mmaExplore1 = new MinMaxAveDouble(numberRobots);	
			MinMaxAveDouble mmaHoove1 = new MinMaxAveDouble(numberRobots);	
			MinMaxAveDouble mmaWipe1 = new MinMaxAveDouble(numberRobots);			
			MinMaxAveDouble mmaFinalMaster1 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalExplore1 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalHoove1 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalWipe1 = new MinMaxAveDouble(numberRobots);
			//2
			MinMaxAveDouble mmaComplete2 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaOther2 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaSendNew2 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaSendNull2 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaInfoNew2 = new MinMaxAveDouble(numberRobots);			
			MinMaxAveDouble mmaExplore2 = new MinMaxAveDouble(numberRobots);	
			MinMaxAveDouble mmaHoove2 = new MinMaxAveDouble(numberRobots);	
			MinMaxAveDouble mmaWipe2 = new MinMaxAveDouble(numberRobots);			
			MinMaxAveDouble mmaFinalMaster2 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalExplore2 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalHoove2 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalWipe2 = new MinMaxAveDouble(numberRobots);
			//3
			MinMaxAveDouble mmaComplete3 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaOther3 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaSendNew3 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaSendNull3 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaInfoNew3 = new MinMaxAveDouble(numberRobots);			
			MinMaxAveDouble mmaExplore3 = new MinMaxAveDouble(numberRobots);	
			MinMaxAveDouble mmaHoove3 = new MinMaxAveDouble(numberRobots);	
			MinMaxAveDouble mmaWipe3 = new MinMaxAveDouble(numberRobots);			
			MinMaxAveDouble mmaFinalMaster3 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalExplore3 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalHoove3 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalWipe3 = new MinMaxAveDouble(numberRobots);
			//4
			MinMaxAveDouble mmaComplete4 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaOther4 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaSendNew4 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaSendNull4 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaInfoNew4 = new MinMaxAveDouble(numberRobots);			
			MinMaxAveDouble mmaExplore4 = new MinMaxAveDouble(numberRobots);	
			MinMaxAveDouble mmaHoove4 = new MinMaxAveDouble(numberRobots);	
			MinMaxAveDouble mmaWipe4 = new MinMaxAveDouble(numberRobots);			
			MinMaxAveDouble mmaFinalMaster4 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalExplore4 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalHoove4 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalWipe4 = new MinMaxAveDouble(numberRobots);
			
			for (int runs = 1; runs < 6; runs++) {
				//Exchange measurement
				FileWorker fex = new FileWorker(path + "M" + mapValue + "_V" + configValue + "\\M" 
						+ mapValue + "_V" + configValue + "_CE" + anzExploreRobots + "_CH0_CW0_B" 
						+ newFieldValue + "_D" + runs + "_exchange.txt");
				
				List<String> input = fex.readFile();	
				List<ExchangeMeasurement> inEvals = new ArrayList<ExchangeMeasurement>();				
				for (String line : input) {
					ExchangeMeasurement em = new ExchangeMeasurement("", "", 0);
					em = em.fromJson(line);
					inEvals.add(em);
				}
				List<EvaluationMeasurement> measures = mergeEvaluationMeasurement(inEvals);
				
				//robbi one information
				FileWorker rob1 = new FileWorker(path + "M" + mapValue + "_V" + configValue + "\\M" 
						+ mapValue + "_V" + configValue + "_CE" + anzExploreRobots + "_CH0_CW0_B" 
						+ newFieldValue + "_D" + runs + "_Robbi_1.txt");				
				String firstLine = rob1.readFile().get(0);//.readNextLine();
				RobotMeasurement robM = new RobotMeasurement("");
				robM = robM.fromJson(firstLine);
				
				long benchmarkTime = robM.benchmarkTime;
				int completeTicks = robM.completeTicks;
				
				double completeTime = robM.completeTime;				
				double completeEnergie = robM.completeEnergie;
				
				for (int i = 2; i <= numberRobots + 1; i++) {
					FileWorker rob = new FileWorker(path + "M" + mapValue + "_V" + configValue + "\\M" 
							+ mapValue + "_V" + configValue + "_CE" + anzExploreRobots + "_CH0_CW0_B" 
							+ newFieldValue + "_D" + runs + "_Robbi_" + i + ".txt");
					RobotMeasurement rM = new RobotMeasurement("");
					rM = rM.fromJson(rob.readNextLine());
					completeTime += rM.completeTime;
					completeEnergie += rM.completeEnergie;
				}
				
				mmaBench.values.add(benchmarkTime);
				mmaTicks.values.add(completeTicks);
				mmaTime.values.add(completeTime);
				mmaEnergie.values.add(completeEnergie);		
				//addBaytNumbers 
				//0
				mmaFinalMaster0.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 1, 0));
				mmaFinalExplore0.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 2, 0));
				mmaFinalHoove0.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 12, 0));
				mmaFinalWipe0.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 22, 0));				
				mmaExplore0.values.add(getCompleteByteNumber(measures, "ExploreInformationNewDest", 0));
				mmaHoove0.values.add(getCompleteByteNumber(measures, "HooveInformationNewDest", 0));
				mmaWipe0.values.add(getCompleteByteNumber(measures, "WipeInformationNewDest", 0));				
				mmaSendNew0.values.add(getCompleteByteNumber(measures, "SendNewDest", 0));
				mmaSendNull0.values.add(getCompleteByteNumber(measures, "SendNULLDest", 0));
				mmaInfoNew0.values.add(getCompleteByteNumber(measures, "New Info", 0));				
				mmaComplete0.values.add(getCompleteByteNumber(measures, "Complete", 0));
				mmaOther0.values.add(getCompleteByteNumber(measures, "Other", 0));
				//1			
				mmaFinalMaster1.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 1, 1));
				mmaFinalExplore1.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 2, 1));
				mmaFinalHoove1.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 12, 1));
				mmaFinalWipe1.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 22, 1));				
				mmaExplore1.values.add(getCompleteByteNumber(measures, "ExploreInformationNewDest", 1));
				mmaHoove1.values.add(getCompleteByteNumber(measures, "HooveInformationNewDest", 1));
				mmaWipe1.values.add(getCompleteByteNumber(measures, "WipeInformationNewDest", 1));				
				mmaSendNew1.values.add(getCompleteByteNumber(measures, "SendNewDest", 1));
				mmaSendNull1.values.add(getCompleteByteNumber(measures, "SendNULLDest", 1));
				mmaInfoNew1.values.add(getCompleteByteNumber(measures, "New Info", 1));				
				mmaComplete1.values.add(getCompleteByteNumber(measures, "Complete", 1));
				mmaOther1.values.add(getCompleteByteNumber(measures, "Other", 1));
				//2
				mmaFinalMaster2.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 1, 2));
				mmaFinalExplore2.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 2, 2));
				mmaFinalHoove2.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 12, 2));
				mmaFinalWipe2.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 22, 2));				
				mmaExplore2.values.add(getCompleteByteNumber(measures, "ExploreInformationNewDest", 2));
				mmaHoove2.values.add(getCompleteByteNumber(measures, "HooveInformationNewDest", 2));
				mmaWipe2.values.add(getCompleteByteNumber(measures, "WipeInformationNewDest", 2));				
				mmaSendNew2.values.add(getCompleteByteNumber(measures, "SendNewDest", 2));
				mmaSendNull2.values.add(getCompleteByteNumber(measures, "SendNULLDest", 2));
				mmaInfoNew2.values.add(getCompleteByteNumber(measures, "New Info", 2));				
				mmaComplete2.values.add(getCompleteByteNumber(measures, "Complete", 2));
				mmaOther2.values.add(getCompleteByteNumber(measures, "Other", 2));
				//3
				mmaFinalMaster3.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 1, 3));
				mmaFinalExplore3.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 2, 3));
				mmaFinalHoove3.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 12, 3));
				mmaFinalWipe3.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 22, 3));				
				mmaExplore3.values.add(getCompleteByteNumber(measures, "ExploreInformationNewDest", 3));
				mmaHoove3.values.add(getCompleteByteNumber(measures, "HooveInformationNewDest", 3));
				mmaWipe3.values.add(getCompleteByteNumber(measures, "WipeInformationNewDest", 3));				
				mmaSendNew3.values.add(getCompleteByteNumber(measures, "SendNewDest", 3));
				mmaSendNull3.values.add(getCompleteByteNumber(measures, "SendNULLDest", 3));
				mmaInfoNew3.values.add(getCompleteByteNumber(measures, "New Info", 3));				
				mmaComplete3.values.add(getCompleteByteNumber(measures, "Complete", 3));
				mmaOther3.values.add(getCompleteByteNumber(measures, "Other", 3));
				//4
				mmaFinalMaster4.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 1, 4));
				mmaFinalExplore4.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 2, 4));
				mmaFinalHoove4.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 12, 4));
				mmaFinalWipe4.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 22, 4));				
				mmaExplore4.values.add(getCompleteByteNumber(measures, "ExploreInformationNewDest", 4));
				mmaHoove4.values.add(getCompleteByteNumber(measures, "HooveInformationNewDest", 4));
				mmaWipe4.values.add(getCompleteByteNumber(measures, "WipeInformationNewDest", 4));				
				mmaSendNew4.values.add(getCompleteByteNumber(measures, "SendNewDest", 4));
				mmaSendNull4.values.add(getCompleteByteNumber(measures, "SendNULLDest", 4));
				mmaInfoNew4.values.add(getCompleteByteNumber(measures, "New Info", 4));				
				mmaComplete4.values.add(getCompleteByteNumber(measures, "Complete", 4));
				mmaOther4.values.add(getCompleteByteNumber(measures, "Other", 4));
			}
			
			benchmarkTimeList.add(mmaBench);
			completeEnergieList.add(mmaEnergie);
			completeTicksList.add(mmaTicks);
			completeTimeList.add(mmaTime);
			
			//Exchange Informationen adden 0
			finalMasterList0.add(mmaFinalMaster0);
			finalHooveList0.add(mmaFinalHoove0);
			finalExploreList0.add(mmaFinalExplore0);
			finalWipeList0.add(mmaFinalWipe0);			
			exploreList0.add(mmaExplore0);
			hooveList0.add(mmaHoove0);
			wipeList0.add(mmaWipe0);
			completeExchangeList0.add(mmaComplete0);
			sendNewList0.add(mmaSendNew0);
			sendNullList0.add(mmaSendNull0);
			infoNewList0.add(mmaInfoNew0);
			otherList0.add(mmaOther0);
			//1
			finalMasterList1.add(mmaFinalMaster1);
			finalHooveList1.add(mmaFinalHoove1);
			finalExploreList1.add(mmaFinalExplore1);
			finalWipeList1.add(mmaFinalWipe1);			
			exploreList1.add(mmaExplore1);
			hooveList1.add(mmaHoove1);
			wipeList1.add(mmaWipe1);
			completeExchangeList1.add(mmaComplete1);
			sendNewList1.add(mmaSendNew1);
			sendNullList1.add(mmaSendNull1);
			infoNewList1.add(mmaInfoNew1);
			otherList1.add(mmaOther1);
			//2
			finalMasterList2.add(mmaFinalMaster2);
			finalHooveList2.add(mmaFinalHoove2);
			finalExploreList2.add(mmaFinalExplore2);
			finalWipeList2.add(mmaFinalWipe2);			
			exploreList2.add(mmaExplore2);
			hooveList2.add(mmaHoove2);
			wipeList2.add(mmaWipe2);
			completeExchangeList2.add(mmaComplete2);
			sendNewList2.add(mmaSendNew2);
			sendNullList2.add(mmaSendNull2);
			infoNewList2.add(mmaInfoNew2);
			otherList2.add(mmaOther2);
			//3
			finalMasterList3.add(mmaFinalMaster3);
			finalHooveList3.add(mmaFinalHoove3);
			finalExploreList3.add(mmaFinalExplore3);
			finalWipeList3.add(mmaFinalWipe3);			
			exploreList3.add(mmaExplore3);
			hooveList3.add(mmaHoove3);
			wipeList3.add(mmaWipe3);
			completeExchangeList3.add(mmaComplete3);
			sendNewList3.add(mmaSendNew3);
			sendNullList3.add(mmaSendNull3);
			infoNewList3.add(mmaInfoNew3);
			otherList3.add(mmaOther3);
			//4
			finalMasterList4.add(mmaFinalMaster4);
			finalHooveList4.add(mmaFinalHoove4);
			finalExploreList4.add(mmaFinalExplore4);
			finalWipeList4.add(mmaFinalWipe4);			
			exploreList4.add(mmaExplore4);
			hooveList4.add(mmaHoove4);
			wipeList4.add(mmaWipe4);
			completeExchangeList4.add(mmaComplete4);
			sendNewList4.add(mmaSendNew4);
			sendNullList4.add(mmaSendNull4);
			infoNewList4.add(mmaInfoNew4);
			otherList4.add(mmaOther4);
		}
		
		//add for hooveRobots
		for (int anzHooveRobots = 1; anzHooveRobots <= 10; anzHooveRobots++) {
			int numberRobots = 10 + anzHooveRobots;
			//roboter information
			MinMaxAveLong mmaBench = new MinMaxAveLong(numberRobots);
			MinMaxAveInt mmaTicks = new MinMaxAveInt(numberRobots);
			MinMaxAveDouble mmaTime = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaEnergie = new MinMaxAveDouble(numberRobots);
			//data exchange information
			//0 data exchange information
			MinMaxAveDouble mmaComplete0 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaOther0 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaSendNew0 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaSendNull0 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaInfoNew0 = new MinMaxAveDouble(numberRobots);			
			MinMaxAveDouble mmaExplore0 = new MinMaxAveDouble(numberRobots);	
			MinMaxAveDouble mmaHoove0 = new MinMaxAveDouble(numberRobots);	
			MinMaxAveDouble mmaWipe0 = new MinMaxAveDouble(numberRobots);			
			MinMaxAveDouble mmaFinalMaster0 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalExplore0 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalHoove0 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalWipe0 = new MinMaxAveDouble(numberRobots);
			//1
			MinMaxAveDouble mmaComplete1 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaOther1 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaSendNew1 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaSendNull1 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaInfoNew1 = new MinMaxAveDouble(numberRobots);			
			MinMaxAveDouble mmaExplore1 = new MinMaxAveDouble(numberRobots);	
			MinMaxAveDouble mmaHoove1 = new MinMaxAveDouble(numberRobots);	
			MinMaxAveDouble mmaWipe1 = new MinMaxAveDouble(numberRobots);			
			MinMaxAveDouble mmaFinalMaster1 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalExplore1 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalHoove1 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalWipe1 = new MinMaxAveDouble(numberRobots);
			//2
			MinMaxAveDouble mmaComplete2 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaOther2 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaSendNew2 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaSendNull2 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaInfoNew2 = new MinMaxAveDouble(numberRobots);			
			MinMaxAveDouble mmaExplore2 = new MinMaxAveDouble(numberRobots);	
			MinMaxAveDouble mmaHoove2 = new MinMaxAveDouble(numberRobots);	
			MinMaxAveDouble mmaWipe2 = new MinMaxAveDouble(numberRobots);			
			MinMaxAveDouble mmaFinalMaster2 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalExplore2 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalHoove2 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalWipe2 = new MinMaxAveDouble(numberRobots);
			//3
			MinMaxAveDouble mmaComplete3 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaOther3 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaSendNew3 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaSendNull3 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaInfoNew3 = new MinMaxAveDouble(numberRobots);			
			MinMaxAveDouble mmaExplore3 = new MinMaxAveDouble(numberRobots);	
			MinMaxAveDouble mmaHoove3 = new MinMaxAveDouble(numberRobots);	
			MinMaxAveDouble mmaWipe3 = new MinMaxAveDouble(numberRobots);			
			MinMaxAveDouble mmaFinalMaster3 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalExplore3 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalHoove3 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalWipe3 = new MinMaxAveDouble(numberRobots);
			//4
			MinMaxAveDouble mmaComplete4 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaOther4 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaSendNew4 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaSendNull4 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaInfoNew4 = new MinMaxAveDouble(numberRobots);			
			MinMaxAveDouble mmaExplore4 = new MinMaxAveDouble(numberRobots);	
			MinMaxAveDouble mmaHoove4 = new MinMaxAveDouble(numberRobots);	
			MinMaxAveDouble mmaWipe4 = new MinMaxAveDouble(numberRobots);			
			MinMaxAveDouble mmaFinalMaster4 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalExplore4 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalHoove4 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalWipe4 = new MinMaxAveDouble(numberRobots);
			
			for (int runs = 1; runs < 6; runs++) {
				//Exchange measurement
				FileWorker fex = new FileWorker(path + "M" + mapValue + "_V" + configValue + "\\M" 
						+ mapValue + "_V" + configValue + "_CE10_CH" + anzHooveRobots + "_CW0_B" 
						+ newFieldValue + "_D" + runs + "_exchange.txt");
				
				List<String> input = fex.readFile();
				
				if (input == null || input.isEmpty())
					continue;
				
				List<ExchangeMeasurement> inEvals = new ArrayList<ExchangeMeasurement>();				
				for (String line : input) {
					ExchangeMeasurement em = new ExchangeMeasurement("", "", 0);
					em = em.fromJson(line);
					inEvals.add(em);
				}
				List<EvaluationMeasurement> measures = mergeEvaluationMeasurement(inEvals);
				
				//robbi one information
				FileWorker rob1 = new FileWorker(path + "M" + mapValue + "_V" + configValue + "\\M" 
					+ mapValue + "_V" + configValue + "_CE10_CH" + anzHooveRobots + "_CW0_B" 
					+ newFieldValue + "_D" + runs + "_Robbi_1.txt");				
				String firstLine = rob1.readFile().get(0);//.readNextLine();
				RobotMeasurement robM = new RobotMeasurement("");
				robM = robM.fromJson(firstLine);
				
				long benchmarkTime = robM.benchmarkTime;
				int completeTicks = robM.completeTicks;
				
				double completeTime = robM.completeTime;				
				double completeEnergie = robM.completeEnergie;
				
				for (int i = 2; i <= numberRobots + 1; i++) {
					FileWorker rob = new FileWorker(path + "M" + mapValue + "_V" + configValue + "\\M" 
							+ mapValue + "_V" + configValue + "_CE10_CH" + anzHooveRobots + "_CW0_B" 
							+ newFieldValue + "_D" + runs + "_Robbi_" + i + ".txt");
					RobotMeasurement rM = new RobotMeasurement("");
					rM = rM.fromJson(rob.readNextLine());
					completeTime += rM.completeTime;
					completeEnergie += rM.completeEnergie;
				}
				
				mmaBench.values.add(benchmarkTime);
				mmaTicks.values.add(completeTicks);
				mmaTime.values.add(completeTime);
				mmaEnergie.values.add(completeEnergie);		
				//addBaytNumbers				
				//0
				mmaFinalMaster0.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 1, 0));
				mmaFinalExplore0.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 2, 0));
				mmaFinalHoove0.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 12, 0));
				mmaFinalWipe0.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 22, 0));				
				mmaExplore0.values.add(getCompleteByteNumber(measures, "ExploreInformationNewDest", 0));
				mmaHoove0.values.add(getCompleteByteNumber(measures, "HooveInformationNewDest", 0));
				mmaWipe0.values.add(getCompleteByteNumber(measures, "WipeInformationNewDest", 0));				
				mmaSendNew0.values.add(getCompleteByteNumber(measures, "SendNewDest", 0));
				mmaSendNull0.values.add(getCompleteByteNumber(measures, "SendNULLDest", 0));
				mmaInfoNew0.values.add(getCompleteByteNumber(measures, "New Info", 0));				
				mmaComplete0.values.add(getCompleteByteNumber(measures, "Complete", 0));
				mmaOther0.values.add(getCompleteByteNumber(measures, "Other", 0));
				//1			
				mmaFinalMaster1.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 1, 1));
				mmaFinalExplore1.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 2, 1));
				mmaFinalHoove1.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 12, 1));
				mmaFinalWipe1.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 22, 1));				
				mmaExplore1.values.add(getCompleteByteNumber(measures, "ExploreInformationNewDest", 1));
				mmaHoove1.values.add(getCompleteByteNumber(measures, "HooveInformationNewDest", 1));
				mmaWipe1.values.add(getCompleteByteNumber(measures, "WipeInformationNewDest", 1));				
				mmaSendNew1.values.add(getCompleteByteNumber(measures, "SendNewDest", 1));
				mmaSendNull1.values.add(getCompleteByteNumber(measures, "SendNULLDest", 1));
				mmaInfoNew1.values.add(getCompleteByteNumber(measures, "New Info", 1));				
				mmaComplete1.values.add(getCompleteByteNumber(measures, "Complete", 1));
				mmaOther1.values.add(getCompleteByteNumber(measures, "Other", 1));
				//2
				mmaFinalMaster2.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 1, 2));
				mmaFinalExplore2.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 2, 2));
				mmaFinalHoove2.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 12, 2));
				mmaFinalWipe2.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 22, 2));				
				mmaExplore2.values.add(getCompleteByteNumber(measures, "ExploreInformationNewDest", 2));
				mmaHoove2.values.add(getCompleteByteNumber(measures, "HooveInformationNewDest", 2));
				mmaWipe2.values.add(getCompleteByteNumber(measures, "WipeInformationNewDest", 2));				
				mmaSendNew2.values.add(getCompleteByteNumber(measures, "SendNewDest", 2));
				mmaSendNull2.values.add(getCompleteByteNumber(measures, "SendNULLDest", 2));
				mmaInfoNew2.values.add(getCompleteByteNumber(measures, "New Info", 2));				
				mmaComplete2.values.add(getCompleteByteNumber(measures, "Complete", 2));
				mmaOther2.values.add(getCompleteByteNumber(measures, "Other", 2));
				//3
				mmaFinalMaster3.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 1, 3));
				mmaFinalExplore3.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 2, 3));
				mmaFinalHoove3.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 12, 3));
				mmaFinalWipe3.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 22, 3));				
				mmaExplore3.values.add(getCompleteByteNumber(measures, "ExploreInformationNewDest", 3));
				mmaHoove3.values.add(getCompleteByteNumber(measures, "HooveInformationNewDest", 3));
				mmaWipe3.values.add(getCompleteByteNumber(measures, "WipeInformationNewDest", 3));				
				mmaSendNew3.values.add(getCompleteByteNumber(measures, "SendNewDest", 3));
				mmaSendNull3.values.add(getCompleteByteNumber(measures, "SendNULLDest", 3));
				mmaInfoNew3.values.add(getCompleteByteNumber(measures, "New Info", 3));				
				mmaComplete3.values.add(getCompleteByteNumber(measures, "Complete", 3));
				mmaOther3.values.add(getCompleteByteNumber(measures, "Other", 3));
				//4
				mmaFinalMaster4.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 1, 4));
				mmaFinalExplore4.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 2, 4));
				mmaFinalHoove4.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 12, 4));
				mmaFinalWipe4.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 22, 4));				
				mmaExplore4.values.add(getCompleteByteNumber(measures, "ExploreInformationNewDest", 4));
				mmaHoove4.values.add(getCompleteByteNumber(measures, "HooveInformationNewDest", 4));
				mmaWipe4.values.add(getCompleteByteNumber(measures, "WipeInformationNewDest", 4));				
				mmaSendNew4.values.add(getCompleteByteNumber(measures, "SendNewDest", 4));
				mmaSendNull4.values.add(getCompleteByteNumber(measures, "SendNULLDest", 4));
				mmaInfoNew4.values.add(getCompleteByteNumber(measures, "New Info", 4));				
				mmaComplete4.values.add(getCompleteByteNumber(measures, "Complete", 4));
				mmaOther4.values.add(getCompleteByteNumber(measures, "Other", 4));
			}
					
			benchmarkTimeList.add(mmaBench);
			completeEnergieList.add(mmaEnergie);
			completeTicksList.add(mmaTicks);
			completeTimeList.add(mmaTime);
			
			//Exchange Informationen adden 0
			finalMasterList0.add(mmaFinalMaster0);
			finalHooveList0.add(mmaFinalHoove0);
			finalExploreList0.add(mmaFinalExplore0);
			finalWipeList0.add(mmaFinalWipe0);			
			exploreList0.add(mmaExplore0);
			hooveList0.add(mmaHoove0);
			wipeList0.add(mmaWipe0);
			completeExchangeList0.add(mmaComplete0);
			sendNewList0.add(mmaSendNew0);
			sendNullList0.add(mmaSendNull0);
			infoNewList0.add(mmaInfoNew0);
			otherList0.add(mmaOther0);
			//1
			finalMasterList1.add(mmaFinalMaster1);
			finalHooveList1.add(mmaFinalHoove1);
			finalExploreList1.add(mmaFinalExplore1);
			finalWipeList1.add(mmaFinalWipe1);			
			exploreList1.add(mmaExplore1);
			hooveList1.add(mmaHoove1);
			wipeList1.add(mmaWipe1);
			completeExchangeList1.add(mmaComplete1);
			sendNewList1.add(mmaSendNew1);
			sendNullList1.add(mmaSendNull1);
			infoNewList1.add(mmaInfoNew1);
			otherList1.add(mmaOther1);
			//2
			finalMasterList2.add(mmaFinalMaster2);
			finalHooveList2.add(mmaFinalHoove2);
			finalExploreList2.add(mmaFinalExplore2);
			finalWipeList2.add(mmaFinalWipe2);			
			exploreList2.add(mmaExplore2);
			hooveList2.add(mmaHoove2);
			wipeList2.add(mmaWipe2);
			completeExchangeList2.add(mmaComplete2);
			sendNewList2.add(mmaSendNew2);
			sendNullList2.add(mmaSendNull2);
			infoNewList2.add(mmaInfoNew2);
			otherList2.add(mmaOther2);
			//3
			finalMasterList3.add(mmaFinalMaster3);
			finalHooveList3.add(mmaFinalHoove3);
			finalExploreList3.add(mmaFinalExplore3);
			finalWipeList3.add(mmaFinalWipe3);			
			exploreList3.add(mmaExplore3);
			hooveList3.add(mmaHoove3);
			wipeList3.add(mmaWipe3);
			completeExchangeList3.add(mmaComplete3);
			sendNewList3.add(mmaSendNew3);
			sendNullList3.add(mmaSendNull3);
			infoNewList3.add(mmaInfoNew3);
			otherList3.add(mmaOther3);
			//4
			finalMasterList4.add(mmaFinalMaster4);
			finalHooveList4.add(mmaFinalHoove4);
			finalExploreList4.add(mmaFinalExplore4);
			finalWipeList4.add(mmaFinalWipe4);			
			exploreList4.add(mmaExplore4);
			hooveList4.add(mmaHoove4);
			wipeList4.add(mmaWipe4);
			completeExchangeList4.add(mmaComplete4);
			sendNewList4.add(mmaSendNew4);
			sendNullList4.add(mmaSendNull4);
			infoNewList4.add(mmaInfoNew4);
			otherList4.add(mmaOther4);
		}
				
		//add for wipeRobots
		for (int anzWipeRobots = 1; anzWipeRobots <= 10; anzWipeRobots++) {
			int numberRobots = 20 + anzWipeRobots;
			//roboter information
			MinMaxAveLong mmaBench = new MinMaxAveLong(numberRobots);
			MinMaxAveInt mmaTicks = new MinMaxAveInt(numberRobots);
			MinMaxAveDouble mmaTime = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaEnergie = new MinMaxAveDouble(numberRobots);
			//data exchange information
			//0 data exchange information
			MinMaxAveDouble mmaComplete0 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaOther0 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaSendNew0 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaSendNull0 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaInfoNew0 = new MinMaxAveDouble(numberRobots);			
			MinMaxAveDouble mmaExplore0 = new MinMaxAveDouble(numberRobots);	
			MinMaxAveDouble mmaHoove0 = new MinMaxAveDouble(numberRobots);	
			MinMaxAveDouble mmaWipe0 = new MinMaxAveDouble(numberRobots);			
			MinMaxAveDouble mmaFinalMaster0 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalExplore0 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalHoove0 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalWipe0 = new MinMaxAveDouble(numberRobots);
			//1
			MinMaxAveDouble mmaComplete1 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaOther1 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaSendNew1 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaSendNull1 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaInfoNew1 = new MinMaxAveDouble(numberRobots);			
			MinMaxAveDouble mmaExplore1 = new MinMaxAveDouble(numberRobots);	
			MinMaxAveDouble mmaHoove1 = new MinMaxAveDouble(numberRobots);	
			MinMaxAveDouble mmaWipe1 = new MinMaxAveDouble(numberRobots);			
			MinMaxAveDouble mmaFinalMaster1 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalExplore1 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalHoove1 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalWipe1 = new MinMaxAveDouble(numberRobots);
			//2
			MinMaxAveDouble mmaComplete2 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaOther2 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaSendNew2 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaSendNull2 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaInfoNew2 = new MinMaxAveDouble(numberRobots);			
			MinMaxAveDouble mmaExplore2 = new MinMaxAveDouble(numberRobots);	
			MinMaxAveDouble mmaHoove2 = new MinMaxAveDouble(numberRobots);	
			MinMaxAveDouble mmaWipe2 = new MinMaxAveDouble(numberRobots);			
			MinMaxAveDouble mmaFinalMaster2 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalExplore2 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalHoove2 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalWipe2 = new MinMaxAveDouble(numberRobots);
			//3
			MinMaxAveDouble mmaComplete3 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaOther3 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaSendNew3 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaSendNull3 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaInfoNew3 = new MinMaxAveDouble(numberRobots);			
			MinMaxAveDouble mmaExplore3 = new MinMaxAveDouble(numberRobots);	
			MinMaxAveDouble mmaHoove3 = new MinMaxAveDouble(numberRobots);	
			MinMaxAveDouble mmaWipe3 = new MinMaxAveDouble(numberRobots);			
			MinMaxAveDouble mmaFinalMaster3 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalExplore3 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalHoove3 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalWipe3 = new MinMaxAveDouble(numberRobots);
			//4
			MinMaxAveDouble mmaComplete4 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaOther4 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaSendNew4 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaSendNull4 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaInfoNew4 = new MinMaxAveDouble(numberRobots);			
			MinMaxAveDouble mmaExplore4 = new MinMaxAveDouble(numberRobots);	
			MinMaxAveDouble mmaHoove4 = new MinMaxAveDouble(numberRobots);	
			MinMaxAveDouble mmaWipe4 = new MinMaxAveDouble(numberRobots);			
			MinMaxAveDouble mmaFinalMaster4 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalExplore4 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalHoove4 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalWipe4 = new MinMaxAveDouble(numberRobots);
			
			for (int runs = 1; runs < 6; runs++) {
				//Exchange measurement
				FileWorker fex = new FileWorker(path + "M" + mapValue + "_V" + configValue + "\\M" 
						+ mapValue + "_V" + configValue + "_CE10_CH10_CW" + anzWipeRobots + "_B" 
						+ newFieldValue + "_D" + runs + "_exchange.txt");
				
				List<String> input = fex.readFile();
				
				if (input == null || input.isEmpty())
					continue;
				
				List<ExchangeMeasurement> inEvals = new ArrayList<ExchangeMeasurement>();				
				for (String line : input) {
					ExchangeMeasurement em = new ExchangeMeasurement("", "", 0);
					em = em.fromJson(line);
					inEvals.add(em);
				}
				List<EvaluationMeasurement> measures = mergeEvaluationMeasurement(inEvals);
				
				//robbi one information
				FileWorker rob1 = new FileWorker(path + "M" + mapValue + "_V" + configValue + "\\M" 
					+ mapValue + "_V" + configValue + "_CE10_CH10_CW" + anzWipeRobots + "_B" 
					+ newFieldValue + "_D" + runs + "_Robbi_1.txt");				
				String firstLine = rob1.readFile().get(0);//.readNextLine();
				RobotMeasurement robM = new RobotMeasurement("");
				robM = robM.fromJson(firstLine);
				
				long benchmarkTime = robM.benchmarkTime;
				int completeTicks = robM.completeTicks;
				
				double completeTime = robM.completeTime;				
				double completeEnergie = robM.completeEnergie;
				
				for (int i = 2; i <= numberRobots + 1; i++) {
					FileWorker rob = new FileWorker(path + "M" + mapValue + "_V" + configValue + "\\M" 
							+ mapValue + "_V" + configValue + "_CE10_CH10_CW" + anzWipeRobots + "_B" 
							+ newFieldValue + "_D" + runs + "_Robbi_" + i + ".txt");
					RobotMeasurement rM = new RobotMeasurement("");
					rM = rM.fromJson(rob.readNextLine());
					completeTime += rM.completeTime;
					completeEnergie += rM.completeEnergie;
				}
				
				mmaBench.values.add(benchmarkTime);
				mmaTicks.values.add(completeTicks);
				mmaTime.values.add(completeTime);
				mmaEnergie.values.add(completeEnergie);		
				//addBaytNumbers				
				//0
				mmaFinalMaster0.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 1, 0));
				mmaFinalExplore0.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 2, 0));
				mmaFinalHoove0.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 12, 0));
				mmaFinalWipe0.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 22, 0));				
				mmaExplore0.values.add(getCompleteByteNumber(measures, "ExploreInformationNewDest", 0));
				mmaHoove0.values.add(getCompleteByteNumber(measures, "HooveInformationNewDest", 0));
				mmaWipe0.values.add(getCompleteByteNumber(measures, "WipeInformationNewDest", 0));				
				mmaSendNew0.values.add(getCompleteByteNumber(measures, "SendNewDest", 0));
				mmaSendNull0.values.add(getCompleteByteNumber(measures, "SendNULLDest", 0));
				mmaInfoNew0.values.add(getCompleteByteNumber(measures, "New Info", 0));				
				mmaComplete0.values.add(getCompleteByteNumber(measures, "Complete", 0));
				mmaOther0.values.add(getCompleteByteNumber(measures, "Other", 0));
				//1			
				mmaFinalMaster1.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 1, 1));
				mmaFinalExplore1.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 2, 1));
				mmaFinalHoove1.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 12, 1));
				mmaFinalWipe1.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 22, 1));				
				mmaExplore1.values.add(getCompleteByteNumber(measures, "ExploreInformationNewDest", 1));
				mmaHoove1.values.add(getCompleteByteNumber(measures, "HooveInformationNewDest", 1));
				mmaWipe1.values.add(getCompleteByteNumber(measures, "WipeInformationNewDest", 1));				
				mmaSendNew1.values.add(getCompleteByteNumber(measures, "SendNewDest", 1));
				mmaSendNull1.values.add(getCompleteByteNumber(measures, "SendNULLDest", 1));
				mmaInfoNew1.values.add(getCompleteByteNumber(measures, "New Info", 1));				
				mmaComplete1.values.add(getCompleteByteNumber(measures, "Complete", 1));
				mmaOther1.values.add(getCompleteByteNumber(measures, "Other", 1));
				//2
				mmaFinalMaster2.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 1, 2));
				mmaFinalExplore2.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 2, 2));
				mmaFinalHoove2.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 12, 2));
				mmaFinalWipe2.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 22, 2));				
				mmaExplore2.values.add(getCompleteByteNumber(measures, "ExploreInformationNewDest", 2));
				mmaHoove2.values.add(getCompleteByteNumber(measures, "HooveInformationNewDest", 2));
				mmaWipe2.values.add(getCompleteByteNumber(measures, "WipeInformationNewDest", 2));				
				mmaSendNew2.values.add(getCompleteByteNumber(measures, "SendNewDest", 2));
				mmaSendNull2.values.add(getCompleteByteNumber(measures, "SendNULLDest", 2));
				mmaInfoNew2.values.add(getCompleteByteNumber(measures, "New Info", 2));				
				mmaComplete2.values.add(getCompleteByteNumber(measures, "Complete", 2));
				mmaOther2.values.add(getCompleteByteNumber(measures, "Other", 2));
				//3
				mmaFinalMaster3.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 1, 3));
				mmaFinalExplore3.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 2, 3));
				mmaFinalHoove3.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 12, 3));
				mmaFinalWipe3.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 22, 3));				
				mmaExplore3.values.add(getCompleteByteNumber(measures, "ExploreInformationNewDest", 3));
				mmaHoove3.values.add(getCompleteByteNumber(measures, "HooveInformationNewDest", 3));
				mmaWipe3.values.add(getCompleteByteNumber(measures, "WipeInformationNewDest", 3));				
				mmaSendNew3.values.add(getCompleteByteNumber(measures, "SendNewDest", 3));
				mmaSendNull3.values.add(getCompleteByteNumber(measures, "SendNULLDest", 3));
				mmaInfoNew3.values.add(getCompleteByteNumber(measures, "New Info", 3));				
				mmaComplete3.values.add(getCompleteByteNumber(measures, "Complete", 3));
				mmaOther3.values.add(getCompleteByteNumber(measures, "Other", 3));
				//4
				mmaFinalMaster4.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 1, 4));
				mmaFinalExplore4.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 2, 4));
				mmaFinalHoove4.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 12, 4));
				mmaFinalWipe4.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 22, 4));				
				mmaExplore4.values.add(getCompleteByteNumber(measures, "ExploreInformationNewDest", 4));
				mmaHoove4.values.add(getCompleteByteNumber(measures, "HooveInformationNewDest", 4));
				mmaWipe4.values.add(getCompleteByteNumber(measures, "WipeInformationNewDest", 4));				
				mmaSendNew4.values.add(getCompleteByteNumber(measures, "SendNewDest", 4));
				mmaSendNull4.values.add(getCompleteByteNumber(measures, "SendNULLDest", 4));
				mmaInfoNew4.values.add(getCompleteByteNumber(measures, "New Info", 4));				
				mmaComplete4.values.add(getCompleteByteNumber(measures, "Complete", 4));
				mmaOther4.values.add(getCompleteByteNumber(measures, "Other", 4));
			}
					
			benchmarkTimeList.add(mmaBench);
			completeEnergieList.add(mmaEnergie);
			completeTicksList.add(mmaTicks);
			completeTimeList.add(mmaTime);
			
			//Exchange Informationen adden 0
			finalMasterList0.add(mmaFinalMaster0);
			finalHooveList0.add(mmaFinalHoove0);
			finalExploreList0.add(mmaFinalExplore0);
			finalWipeList0.add(mmaFinalWipe0);			
			exploreList0.add(mmaExplore0);
			hooveList0.add(mmaHoove0);
			wipeList0.add(mmaWipe0);
			completeExchangeList0.add(mmaComplete0);
			sendNewList0.add(mmaSendNew0);
			sendNullList0.add(mmaSendNull0);
			infoNewList0.add(mmaInfoNew0);
			otherList0.add(mmaOther0);
			//1
			finalMasterList1.add(mmaFinalMaster1);
			finalHooveList1.add(mmaFinalHoove1);
			finalExploreList1.add(mmaFinalExplore1);
			finalWipeList1.add(mmaFinalWipe1);			
			exploreList1.add(mmaExplore1);
			hooveList1.add(mmaHoove1);
			wipeList1.add(mmaWipe1);
			completeExchangeList1.add(mmaComplete1);
			sendNewList1.add(mmaSendNew1);
			sendNullList1.add(mmaSendNull1);
			infoNewList1.add(mmaInfoNew1);
			otherList1.add(mmaOther1);
			//2
			finalMasterList2.add(mmaFinalMaster2);
			finalHooveList2.add(mmaFinalHoove2);
			finalExploreList2.add(mmaFinalExplore2);
			finalWipeList2.add(mmaFinalWipe2);			
			exploreList2.add(mmaExplore2);
			hooveList2.add(mmaHoove2);
			wipeList2.add(mmaWipe2);
			completeExchangeList2.add(mmaComplete2);
			sendNewList2.add(mmaSendNew2);
			sendNullList2.add(mmaSendNull2);
			infoNewList2.add(mmaInfoNew2);
			otherList2.add(mmaOther2);
			//3
			finalMasterList3.add(mmaFinalMaster3);
			finalHooveList3.add(mmaFinalHoove3);
			finalExploreList3.add(mmaFinalExplore3);
			finalWipeList3.add(mmaFinalWipe3);			
			exploreList3.add(mmaExplore3);
			hooveList3.add(mmaHoove3);
			wipeList3.add(mmaWipe3);
			completeExchangeList3.add(mmaComplete3);
			sendNewList3.add(mmaSendNew3);
			sendNullList3.add(mmaSendNull3);
			infoNewList3.add(mmaInfoNew3);
			otherList3.add(mmaOther3);
			//4
			finalMasterList4.add(mmaFinalMaster4);
			finalHooveList4.add(mmaFinalHoove4);
			finalExploreList4.add(mmaFinalExplore4);
			finalWipeList4.add(mmaFinalWipe4);			
			exploreList4.add(mmaExplore4);
			hooveList4.add(mmaHoove4);
			wipeList4.add(mmaWipe4);
			completeExchangeList4.add(mmaComplete4);
			sendNewList4.add(mmaSendNew4);
			sendNullList4.add(mmaSendNull4);
			infoNewList4.add(mmaInfoNew4);
			otherList4.add(mmaOther4);
		}
		
		FileWorker fw = new FileWorker("M" + mapValue + "_V" + configValue + "_B" + newFieldValue + zusatz + ".csv");
		String numberS = ";";		
		List<String> ergebnis = new ArrayList<String>();
		int anzahlRobots = 30;
		for (int anzRobots = 1; anzRobots <= anzahlRobots; anzRobots++) {
			numberS = numberS + anzRobots + ";";
		}
		
		ergebnis.add("M" + mapValue + "_V" + configValue + "_B" + newFieldValue);
		ergebnis.add(numberS);
		ergebnis.addAll(getMinAvgMaxLong(benchmarkTimeList, anzahlRobots, "Benchmarkzeit"));
		ergebnis.addAll(getMinAvgMaxInt(completeTicksList, anzahlRobots, "Iterationen"));
		ergebnis.addAll(getMinAvgMaxDouble(completeTimeList, anzahlRobots, "Rechenzeit"));
		ergebnis.addAll(getMinAvgMaxDouble(completeEnergieList, anzahlRobots, "Energieverbrauch"));
		ergebnis.add("Messungen in Byte");
		if (booleanConfig.exploreExchange)
			ergebnis.addAll(getMinAvgMaxDouble(exploreList0, anzahlRobots, "Zielaustausch Erkunden"));
		if (booleanConfig.hooveExchange)
			ergebnis.addAll(getMinAvgMaxDouble(hooveList0, anzahlRobots, "Zielaustausch Saugen"));
		if (booleanConfig.wipeExchange)
			ergebnis.addAll(getMinAvgMaxDouble(wipeList0, anzahlRobots, "Zielaustausch Wischen"));		
		if (booleanConfig.kompletterAustausch)
			ergebnis.addAll(getMinAvgMaxDouble(completeExchangeList0, anzahlRobots, "Kompletter Austausch"));
		if (booleanConfig.newInformation)
			ergebnis.addAll(getMinAvgMaxDouble(infoNewList0, anzahlRobots, "Neue Informationen"));
		if (booleanConfig.other)
			ergebnis.addAll(getMinAvgMaxDouble(otherList0, anzahlRobots, "Andereraustausch"));
		if (booleanConfig.sendNewInformation)
			ergebnis.addAll(getMinAvgMaxDouble(sendNewList0, anzahlRobots, "Neue Informationen Senden"));
		if (booleanConfig.sendNull)
			ergebnis.addAll(getMinAvgMaxDouble(sendNullList0, anzahlRobots, "Sende Null Ziel"));
		ergebnis.add("Messungen in Anzahl komplett");
		if (booleanConfig.exploreExchange)
			ergebnis.addAll(getMinAvgMaxDouble(exploreList1, anzahlRobots, "Zielaustausch Erkunden"));
		if (booleanConfig.hooveExchange)
			ergebnis.addAll(getMinAvgMaxDouble(hooveList1, anzahlRobots, "Zielaustausch Saugen"));
		if (booleanConfig.wipeExchange)
			ergebnis.addAll(getMinAvgMaxDouble(wipeList1, anzahlRobots, "Zielaustausch Wischen"));		
		if (booleanConfig.kompletterAustausch)
			ergebnis.addAll(getMinAvgMaxDouble(completeExchangeList1, anzahlRobots, "Kompletter Austausch"));
		if (booleanConfig.newInformation)
			ergebnis.addAll(getMinAvgMaxDouble(infoNewList1, anzahlRobots, "Neue Informationen"));
		if (booleanConfig.other)
			ergebnis.addAll(getMinAvgMaxDouble(otherList1, anzahlRobots, "Andereraustausch"));
		if (booleanConfig.sendNewInformation)
			ergebnis.addAll(getMinAvgMaxDouble(sendNewList1, anzahlRobots, "Neue Informationen Senden"));
		if (booleanConfig.sendNull)
			ergebnis.addAll(getMinAvgMaxDouble(sendNullList1, anzahlRobots, "Sende Null Ziel"));
		ergebnis.add("Messungen in Anzahl sonstiges Wissen");
		if (booleanConfig.exploreExchange)
			ergebnis.addAll(getMinAvgMaxDouble(exploreList2, anzahlRobots, "Zielaustausch Erkunden"));
		if (booleanConfig.hooveExchange)
			ergebnis.addAll(getMinAvgMaxDouble(hooveList2, anzahlRobots, "Zielaustausch Saugen"));
		if (booleanConfig.wipeExchange)
			ergebnis.addAll(getMinAvgMaxDouble(wipeList2, anzahlRobots, "Zielaustausch Wischen"));		
		if (booleanConfig.kompletterAustausch)
			ergebnis.addAll(getMinAvgMaxDouble(completeExchangeList2, anzahlRobots, "Kompletter Austausch"));
		if (booleanConfig.newInformation)
			ergebnis.addAll(getMinAvgMaxDouble(infoNewList2, anzahlRobots, "Neue Informationen"));
		if (booleanConfig.other)
			ergebnis.addAll(getMinAvgMaxDouble(otherList2, anzahlRobots, "Andereraustausch"));
		if (booleanConfig.sendNewInformation)
			ergebnis.addAll(getMinAvgMaxDouble(sendNewList2, anzahlRobots, "Neue Informationen Senden"));
		if (booleanConfig.sendNull)
			ergebnis.addAll(getMinAvgMaxDouble(sendNullList2, anzahlRobots, "Sende Null Ziel"));
		ergebnis.add("Messungen in Anzahl Weltwissen");
		if (booleanConfig.exploreExchange)
			ergebnis.addAll(getMinAvgMaxDouble(exploreList3, anzahlRobots, "Zielaustausch Erkunden"));
		if (booleanConfig.hooveExchange)
			ergebnis.addAll(getMinAvgMaxDouble(hooveList3, anzahlRobots, "Zielaustausch Saugen"));
		if (booleanConfig.wipeExchange)
			ergebnis.addAll(getMinAvgMaxDouble(wipeList3, anzahlRobots, "Zielaustausch Wischen"));		
		if (booleanConfig.kompletterAustausch)
			ergebnis.addAll(getMinAvgMaxDouble(completeExchangeList3, anzahlRobots, "Kompletter Austausch"));
		if (booleanConfig.newInformation)
			ergebnis.addAll(getMinAvgMaxDouble(infoNewList3, anzahlRobots, "Neue Informationen"));
		if (booleanConfig.other)
			ergebnis.addAll(getMinAvgMaxDouble(otherList3, anzahlRobots, "Andereraustausch"));
		if (booleanConfig.sendNewInformation)
			ergebnis.addAll(getMinAvgMaxDouble(sendNewList3, anzahlRobots, "Neue Informationen Senden"));
		if (booleanConfig.sendNull)
			ergebnis.addAll(getMinAvgMaxDouble(sendNullList3, anzahlRobots, "Sende Null Ziel"));
		ergebnis.add("Messungen in Anzahl Austausche");
		if (booleanConfig.exploreExchange)
			ergebnis.addAll(getMinAvgMaxDouble(exploreList4, anzahlRobots, "Zielaustausch Erkunden"));
		if (booleanConfig.hooveExchange)
			ergebnis.addAll(getMinAvgMaxDouble(hooveList4, anzahlRobots, "Zielaustausch Saugen"));
		if (booleanConfig.wipeExchange)
			ergebnis.addAll(getMinAvgMaxDouble(wipeList4, anzahlRobots, "Zielaustausch Wischen"));		
		if (booleanConfig.kompletterAustausch)
			ergebnis.addAll(getMinAvgMaxDouble(completeExchangeList4, anzahlRobots, "Kompletter Austausch"));
		if (booleanConfig.newInformation)
			ergebnis.addAll(getMinAvgMaxDouble(infoNewList4, anzahlRobots, "Neue Informationen"));
		if (booleanConfig.other)
			ergebnis.addAll(getMinAvgMaxDouble(otherList4, anzahlRobots, "Andereraustausch"));
		if (booleanConfig.sendNewInformation)
			ergebnis.addAll(getMinAvgMaxDouble(sendNewList4, anzahlRobots, "Neue Informationen Senden"));
		if (booleanConfig.sendNull)
			ergebnis.addAll(getMinAvgMaxDouble(sendNullList4, anzahlRobots, "Sende Null Ziel"));
		//Modeloutput
		ergebnis.add("Modell in komplett Byte");
		if (booleanConfig.endmodellMaster)
			ergebnis.addAll(getMinAvgMaxDouble(finalMasterList0, anzahlRobots, "Endmodelmaster"));
		if (booleanConfig.endmodellExplore)
			ergebnis.addAll(getMinAvgMaxDouble(finalExploreList0, anzahlRobots, "Endmodelerkunder"));
		if (booleanConfig.endmodellHoove)
			ergebnis.addAll(getMinAvgMaxDouble(finalHooveList0, anzahlRobots, "Endmodelsauger"));
		if (booleanConfig.endModellWipe)
			ergebnis.addAll(getMinAvgMaxDouble(finalWipeList0, anzahlRobots, "Endmodelwischer"));
		ergebnis.add("Modell in komplett Number mit Zeitstempel");
		if (booleanConfig.endmodellMaster)
			ergebnis.addAll(getMinAvgMaxDouble(finalMasterList1, anzahlRobots, "Endmodelmaster"));
		if (booleanConfig.endmodellExplore)
			ergebnis.addAll(getMinAvgMaxDouble(finalExploreList1, anzahlRobots, "Endmodelerkunder"));
		if (booleanConfig.endmodellHoove)
			ergebnis.addAll(getMinAvgMaxDouble(finalHooveList1, anzahlRobots, "Endmodelsauger"));
		if (booleanConfig.endModellWipe)
			ergebnis.addAll(getMinAvgMaxDouble(finalWipeList1, anzahlRobots, "Endmodelwischer"));
		ergebnis.add("Modell in Anzahl sonstiges Wissen");
		if (booleanConfig.endmodellMaster)
			ergebnis.addAll(getMinAvgMaxDouble(finalMasterList2, anzahlRobots, "Endmodelmaster"));
		if (booleanConfig.endmodellExplore)
			ergebnis.addAll(getMinAvgMaxDouble(finalExploreList2, anzahlRobots, "Endmodelerkunder"));
		if (booleanConfig.endmodellHoove)
			ergebnis.addAll(getMinAvgMaxDouble(finalHooveList2, anzahlRobots, "Endmodelsauger"));
		if (booleanConfig.endModellWipe)
			ergebnis.addAll(getMinAvgMaxDouble(finalWipeList2, anzahlRobots, "Endmodelwischer"));
		ergebnis.add("Modell in Anzahl Weltwissen");
		if (booleanConfig.endmodellMaster)
			ergebnis.addAll(getMinAvgMaxDouble(finalMasterList3, anzahlRobots, "Endmodelmaster"));
		if (booleanConfig.endmodellExplore)
			ergebnis.addAll(getMinAvgMaxDouble(finalExploreList3, anzahlRobots, "Endmodelerkunder"));
		if (booleanConfig.endmodellHoove)
			ergebnis.addAll(getMinAvgMaxDouble(finalHooveList3, anzahlRobots, "Endmodelsauger"));
		if (booleanConfig.endModellWipe)
			ergebnis.addAll(getMinAvgMaxDouble(finalWipeList3, anzahlRobots, "Endmodelwischer"));
		ergebnis.add("Modell in Anzahl komplett");
		if (booleanConfig.endmodellMaster)
			ergebnis.addAll(getMinAvgMaxDouble(finalMasterList4, anzahlRobots, "Endmodelmaster"));
		if (booleanConfig.endmodellExplore)
			ergebnis.addAll(getMinAvgMaxDouble(finalExploreList4, anzahlRobots, "Endmodelerkunder"));
		if (booleanConfig.endmodellHoove)
			ergebnis.addAll(getMinAvgMaxDouble(finalHooveList4, anzahlRobots, "Endmodelsauger"));
		if (booleanConfig.endModellWipe)
			ergebnis.addAll(getMinAvgMaxDouble(finalWipeList4, anzahlRobots, "Endmodelwischer"));
		
		for (String s: ergebnis) {
			fw.addLineToFile(s.replace('.', ','));
		}			
	}
	
	//output csv for excel		
	public void outputCsv (List<Double> liste, String name) {
		FileWorker fw = new FileWorker("V0.csv");
		String svalue = "";
		String skey = "";
		if (!liste.isEmpty()) {
			svalue = "" + liste.get(0);
			skey = "" + 0;
		}
		for (int i = 1; i < liste.size(); i++) {
			svalue = svalue + ";" + liste.get(i);
			skey = skey + ";" + i;
		}
		fw.addLineToFile(skey);
		fw.addLineToFile(svalue);
	}
	
	public void versionDataExchange (String path, int exchangeValue, String zusatz, BooleanEvaluation booleanConfig) {
		//Listen Mit MinMaxAve werten zum einfügen der gefundenen Werte
		List<MinMaxAveLong> benchmarkTimeList = new LinkedList<MinMaxAveLong>();
		List<MinMaxAveInt> completeTicksList = new LinkedList<MinMaxAveInt>();
		List<MinMaxAveDouble> completeTimeList = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> completeEnergieList = new LinkedList<MinMaxAveDouble>();
		//Exchange
		List<MinMaxAveDouble> completeExchangeList0 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> completeKExchangeList0 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> completeSExchangeList0 = new LinkedList<MinMaxAveDouble>();	
		List<MinMaxAveDouble> infoNewList0 = new LinkedList<MinMaxAveDouble>();		
		List<MinMaxAveDouble> finalMasterList0 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> finalHooveList0 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> finalExploreList0 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> finalWipeList0 = new LinkedList<MinMaxAveDouble>();
		//1
		List<MinMaxAveDouble> completeExchangeList1 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> completeKExchangeList1 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> completeSExchangeList1 = new LinkedList<MinMaxAveDouble>();	
		List<MinMaxAveDouble> infoNewList1 = new LinkedList<MinMaxAveDouble>();		
		List<MinMaxAveDouble> finalMasterList1 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> finalHooveList1 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> finalExploreList1 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> finalWipeList1 = new LinkedList<MinMaxAveDouble>();
		//2
		List<MinMaxAveDouble> completeExchangeList2 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> completeKExchangeList2 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> completeSExchangeList2 = new LinkedList<MinMaxAveDouble>();	
		List<MinMaxAveDouble> infoNewList2 = new LinkedList<MinMaxAveDouble>();		
		List<MinMaxAveDouble> finalMasterList2 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> finalHooveList2 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> finalExploreList2 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> finalWipeList2 = new LinkedList<MinMaxAveDouble>();
		//3
		List<MinMaxAveDouble> completeExchangeList3 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> completeKExchangeList3 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> completeSExchangeList3 = new LinkedList<MinMaxAveDouble>();	
		List<MinMaxAveDouble> infoNewList3 = new LinkedList<MinMaxAveDouble>();		
		List<MinMaxAveDouble> finalMasterList3 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> finalHooveList3 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> finalExploreList3 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> finalWipeList3 = new LinkedList<MinMaxAveDouble>();
		//4
		List<MinMaxAveDouble> completeExchangeList4 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> completeKExchangeList4 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> completeSExchangeList4 = new LinkedList<MinMaxAveDouble>();	
		List<MinMaxAveDouble> infoNewList4 = new LinkedList<MinMaxAveDouble>();		
		List<MinMaxAveDouble> finalMasterList4 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> finalHooveList4 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> finalExploreList4 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> finalWipeList4 = new LinkedList<MinMaxAveDouble>();
		
		//add for exploreRobots
		for (int anzExploreRobots = 1; anzExploreRobots <= 10; anzExploreRobots++) {
			int numberRobots = anzExploreRobots;
			//roboter information
			MinMaxAveLong mmaBench = new MinMaxAveLong(numberRobots);
			MinMaxAveInt mmaTicks = new MinMaxAveInt(numberRobots);
			MinMaxAveDouble mmaTime = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaEnergie = new MinMaxAveDouble(numberRobots);
			
			//data exchange information
			MinMaxAveDouble mmaComplete0 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaCompleteK0 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaCompleteS0 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaInfoNew0 = new MinMaxAveDouble(numberRobots);						
			MinMaxAveDouble mmaFinalMaster0 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalExplore0 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalHoove0 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalWipe0 = new MinMaxAveDouble(numberRobots);	
			//1
			MinMaxAveDouble mmaComplete1 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaCompleteK1 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaCompleteS1 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaInfoNew1 = new MinMaxAveDouble(numberRobots);						
			MinMaxAveDouble mmaFinalMaster1 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalExplore1 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalHoove1 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalWipe1 = new MinMaxAveDouble(numberRobots);	
			//2
			MinMaxAveDouble mmaComplete2 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaCompleteK2 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaCompleteS2 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaInfoNew2 = new MinMaxAveDouble(numberRobots);						
			MinMaxAveDouble mmaFinalMaster2 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalExplore2 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalHoove2 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalWipe2 = new MinMaxAveDouble(numberRobots);	
			//3
			MinMaxAveDouble mmaComplete3 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaCompleteK3 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaCompleteS3 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaInfoNew3 = new MinMaxAveDouble(numberRobots);						
			MinMaxAveDouble mmaFinalMaster3 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalExplore3 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalHoove3 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalWipe3 = new MinMaxAveDouble(numberRobots);	
			//4
			MinMaxAveDouble mmaComplete4 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaCompleteK4 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaCompleteS4 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaInfoNew4 = new MinMaxAveDouble(numberRobots);						
			MinMaxAveDouble mmaFinalMaster4 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalExplore4 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalHoove4 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalWipe4 = new MinMaxAveDouble(numberRobots);	
			
			for (int runs = 1; runs < 6; runs++) {
				//Exchange measurement
				FileWorker fex = new FileWorker(path + "D" + exchangeValue + "\\M0_V2_CE" + anzExploreRobots + "_CH0_CW0_B0_D" + runs + "_exchange.txt");
				
				List<String> input = fex.readFile();	
				List<ExchangeMeasurement> inEvals = new ArrayList<ExchangeMeasurement>();				
				for (String line : input) {
					ExchangeMeasurement em = new ExchangeMeasurement("", "", 0);
					em = em.fromJson(line);
					inEvals.add(em);
				}
				List<EvaluationMeasurement> measures = mergeDataEvaluationMeasurement(inEvals);
				
				//robbi one information
				FileWorker rob1 = new FileWorker(path + "D" + exchangeValue + "\\M0_V2_CE" + anzExploreRobots + "_CH0_CW0_B0_D" + runs + "_Robbi_1.txt");				
				String firstLine = rob1.readFile().get(0);//.readNextLine();
				RobotMeasurement robM = new RobotMeasurement("");
				robM = robM.fromJson(firstLine);
				
				long benchmarkTime = robM.benchmarkTime;
				int completeTicks = robM.completeTicks;
				
				double completeTime = robM.completeTime;				
				double completeEnergie = robM.completeEnergie;
				
				for (int i = 2; i <= numberRobots + 1; i++) {
					FileWorker rob = new FileWorker(path + "D" + exchangeValue + "\\M0_V2_CE" + anzExploreRobots + "_CH0_CW0_B0_D" + runs + "_Robbi_" + i + ".txt");
					RobotMeasurement rM = new RobotMeasurement("");
					rM = rM.fromJson(rob.readNextLine());
					completeTime += rM.completeTime;
					completeEnergie += rM.completeEnergie;
				}
				
				mmaBench.values.add(benchmarkTime);
				mmaTicks.values.add(completeTicks);
				mmaTime.values.add(completeTime);
				mmaEnergie.values.add(completeEnergie);		
				//addBaytNumbers				
				mmaFinalMaster0.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 1, 0));
				mmaFinalExplore0.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 2, 0));
				mmaFinalHoove0.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 12, 0));
				mmaFinalWipe0.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 22, 0));				
				mmaInfoNew0.values.add(getCompleteByteNumber(measures, "New Info", 0));				
				mmaComplete0.values.add(getCompleteByteNumber(measures, "Complete", 0));
				mmaCompleteS0.values.add(getCompleteByteNumber(measures, "CompleteS", 0));
				mmaCompleteK0.values.add(getCompleteByteNumber(measures, "CompleteK", 0));
				//1
				mmaFinalMaster1.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 1, 1));
				mmaFinalExplore1.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 2, 1));
				mmaFinalHoove1.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 12, 1));
				mmaFinalWipe1.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 22, 1));				
				mmaInfoNew1.values.add(getCompleteByteNumber(measures, "New Info", 1));				
				mmaComplete1.values.add(getCompleteByteNumber(measures, "Complete", 1));
				mmaCompleteS1.values.add(getCompleteByteNumber(measures, "CompleteS", 1));
				mmaCompleteK1.values.add(getCompleteByteNumber(measures, "CompleteK", 1));
				//2
				mmaFinalMaster2.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 1, 2));
				mmaFinalExplore2.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 2, 2));
				mmaFinalHoove2.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 12, 2));
				mmaFinalWipe2.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 22, 2));				
				mmaInfoNew2.values.add(getCompleteByteNumber(measures, "New Info", 2));				
				mmaComplete2.values.add(getCompleteByteNumber(measures, "Complete", 2));
				mmaCompleteS2.values.add(getCompleteByteNumber(measures, "CompleteS", 2));
				mmaCompleteK2.values.add(getCompleteByteNumber(measures, "CompleteK", 2));
				//3
				mmaFinalMaster3.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 1, 3));
				mmaFinalExplore3.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 2, 3));
				mmaFinalHoove3.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 12, 3));
				mmaFinalWipe3.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 22, 3));				
				mmaInfoNew3.values.add(getCompleteByteNumber(measures, "New Info", 3));				
				mmaComplete3.values.add(getCompleteByteNumber(measures, "Complete", 3));
				mmaCompleteS3.values.add(getCompleteByteNumber(measures, "CompleteS", 3));
				mmaCompleteK3.values.add(getCompleteByteNumber(measures, "CompleteK", 3));
				//4
				mmaFinalMaster4.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 1, 4));
				mmaFinalExplore4.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 2, 4));
				mmaFinalHoove4.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 12, 4));
				mmaFinalWipe4.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 22, 4));				
				mmaInfoNew4.values.add(getCompleteByteNumber(measures, "New Info", 4));				
				mmaComplete4.values.add(getCompleteByteNumber(measures, "Complete", 4));
				mmaCompleteS4.values.add(getCompleteByteNumber(measures, "CompleteS", 4));
				mmaCompleteK4.values.add(getCompleteByteNumber(measures, "CompleteK", 4));
				
			}
			
			benchmarkTimeList.add(mmaBench);
			completeEnergieList.add(mmaEnergie);
			completeTicksList.add(mmaTicks);
			completeTimeList.add(mmaTime);
			
			//Exchange Informationen adden
			finalMasterList0.add(mmaFinalMaster0);
			finalHooveList0.add(mmaFinalHoove0);
			finalExploreList0.add(mmaFinalExplore0);
			finalWipeList0.add(mmaFinalWipe0);			
			completeExchangeList0.add(mmaComplete0);
			completeKExchangeList0.add(mmaCompleteK0);
			completeSExchangeList0.add(mmaCompleteS0);
			infoNewList0.add(mmaInfoNew0);
			//1
			finalMasterList1.add(mmaFinalMaster1);
			finalHooveList1.add(mmaFinalHoove1);
			finalExploreList1.add(mmaFinalExplore1);
			finalWipeList1.add(mmaFinalWipe1);			
			completeExchangeList1.add(mmaComplete1);
			completeKExchangeList1.add(mmaCompleteK1);
			completeSExchangeList1.add(mmaCompleteS1);
			infoNewList1.add(mmaInfoNew1);
			//2
			finalMasterList2.add(mmaFinalMaster2);
			finalHooveList2.add(mmaFinalHoove2);
			finalExploreList2.add(mmaFinalExplore2);
			finalWipeList2.add(mmaFinalWipe2);			
			completeExchangeList2.add(mmaComplete2);
			completeKExchangeList2.add(mmaCompleteK2);
			completeSExchangeList2.add(mmaCompleteS2);
			infoNewList2.add(mmaInfoNew2);
			//3
			finalMasterList3.add(mmaFinalMaster3);
			finalHooveList3.add(mmaFinalHoove3);
			finalExploreList3.add(mmaFinalExplore3);
			finalWipeList3.add(mmaFinalWipe3);			
			completeExchangeList3.add(mmaComplete3);
			completeKExchangeList3.add(mmaCompleteK3);
			completeSExchangeList3.add(mmaCompleteS3);
			infoNewList3.add(mmaInfoNew3);
			//4
			finalMasterList4.add(mmaFinalMaster4);
			finalHooveList4.add(mmaFinalHoove4);
			finalExploreList4.add(mmaFinalExplore4);
			finalWipeList4.add(mmaFinalWipe4);			
			completeExchangeList4.add(mmaComplete4);
			completeKExchangeList4.add(mmaCompleteK4);
			completeSExchangeList4.add(mmaCompleteS4);
			infoNewList4.add(mmaInfoNew4);
		}
		
		//add for hooveRobots
		for (int anzHooveRobots = 1; anzHooveRobots <= 10; anzHooveRobots++) {
			int numberRobots = 10 + anzHooveRobots;
			//roboter information
			MinMaxAveLong mmaBench = new MinMaxAveLong(numberRobots);
			MinMaxAveInt mmaTicks = new MinMaxAveInt(numberRobots);
			MinMaxAveDouble mmaTime = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaEnergie = new MinMaxAveDouble(numberRobots);
			//data exchange information
			//data exchange information
			MinMaxAveDouble mmaComplete0 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaCompleteK0 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaCompleteS0 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaInfoNew0 = new MinMaxAveDouble(numberRobots);						
			MinMaxAveDouble mmaFinalMaster0 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalExplore0 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalHoove0 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalWipe0 = new MinMaxAveDouble(numberRobots);	
			//1
			MinMaxAveDouble mmaComplete1 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaCompleteK1 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaCompleteS1 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaInfoNew1 = new MinMaxAveDouble(numberRobots);						
			MinMaxAveDouble mmaFinalMaster1 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalExplore1 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalHoove1 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalWipe1 = new MinMaxAveDouble(numberRobots);	
			//2
			MinMaxAveDouble mmaComplete2 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaCompleteK2 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaCompleteS2 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaInfoNew2 = new MinMaxAveDouble(numberRobots);						
			MinMaxAveDouble mmaFinalMaster2 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalExplore2 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalHoove2 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalWipe2 = new MinMaxAveDouble(numberRobots);	
			//3
			MinMaxAveDouble mmaComplete3 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaCompleteK3 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaCompleteS3 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaInfoNew3 = new MinMaxAveDouble(numberRobots);						
			MinMaxAveDouble mmaFinalMaster3 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalExplore3 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalHoove3 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalWipe3 = new MinMaxAveDouble(numberRobots);	
			//4
			MinMaxAveDouble mmaComplete4 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaCompleteK4 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaCompleteS4 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaInfoNew4 = new MinMaxAveDouble(numberRobots);						
			MinMaxAveDouble mmaFinalMaster4 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalExplore4 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalHoove4 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalWipe4 = new MinMaxAveDouble(numberRobots);
			
			for (int runs = 1; runs < 6; runs++) {
				//Exchange measurement
				FileWorker fex = new FileWorker(path + "D" + exchangeValue + "\\M0_V2_CE10_CH" + anzHooveRobots + "_CW0_B0_D" + runs + "_exchange.txt");
				
				List<String> input = fex.readFile();	
				List<ExchangeMeasurement> inEvals = new ArrayList<ExchangeMeasurement>();				
				for (String line : input) {
					ExchangeMeasurement em = new ExchangeMeasurement("", "", 0);
					em = em.fromJson(line);
					inEvals.add(em);
				}
				List<EvaluationMeasurement> measures = mergeDataEvaluationMeasurement(inEvals);
				
				//robbi one information
				FileWorker rob1 = new FileWorker(path + "D" + exchangeValue + "\\M0_V2_CE10_CH" + anzHooveRobots + "_CW0_B0_D" + runs + "_Robbi_1.txt");				
				String firstLine = rob1.readFile().get(0);//.readNextLine();
				RobotMeasurement robM = new RobotMeasurement("");
				robM = robM.fromJson(firstLine);
				
				long benchmarkTime = robM.benchmarkTime;
				int completeTicks = robM.completeTicks;
				
				double completeTime = robM.completeTime;				
				double completeEnergie = robM.completeEnergie;
				
				for (int i = 2; i <= numberRobots + 1; i++) {
					FileWorker rob = new FileWorker(path + "D" + exchangeValue + "\\M0_V2_CE10_CH" + anzHooveRobots + "_CW0_B0_D" + runs + "_Robbi_" + i + ".txt");
					RobotMeasurement rM = new RobotMeasurement("");
					rM = rM.fromJson(rob.readNextLine());
					completeTime += rM.completeTime;
					completeEnergie += rM.completeEnergie;
				}
				
				mmaBench.values.add(benchmarkTime);
				mmaTicks.values.add(completeTicks);
				mmaTime.values.add(completeTime);
				mmaEnergie.values.add(completeEnergie);		
				//addBaytNumbers				
				mmaFinalMaster0.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 1, 0));
				mmaFinalExplore0.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 2, 0));
				mmaFinalHoove0.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 12, 0));
				mmaFinalWipe0.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 22, 0));				
				mmaInfoNew0.values.add(getCompleteByteNumber(measures, "New Info", 0));				
				mmaComplete0.values.add(getCompleteByteNumber(measures, "Complete", 0));
				mmaCompleteS0.values.add(getCompleteByteNumber(measures, "CompleteS", 0));
				mmaCompleteK0.values.add(getCompleteByteNumber(measures, "CompleteK", 0));
				//1
				mmaFinalMaster1.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 1, 1));
				mmaFinalExplore1.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 2, 1));
				mmaFinalHoove1.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 12, 1));
				mmaFinalWipe1.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 22, 1));				
				mmaInfoNew1.values.add(getCompleteByteNumber(measures, "New Info", 1));				
				mmaComplete1.values.add(getCompleteByteNumber(measures, "Complete", 1));
				mmaCompleteS1.values.add(getCompleteByteNumber(measures, "CompleteS", 1));
				mmaCompleteK1.values.add(getCompleteByteNumber(measures, "CompleteK", 1));
				//2
				mmaFinalMaster2.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 1, 2));
				mmaFinalExplore2.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 2, 2));
				mmaFinalHoove2.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 12, 2));
				mmaFinalWipe2.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 22, 2));				
				mmaInfoNew2.values.add(getCompleteByteNumber(measures, "New Info", 2));				
				mmaComplete2.values.add(getCompleteByteNumber(measures, "Complete", 2));
				mmaCompleteS2.values.add(getCompleteByteNumber(measures, "CompleteS", 2));
				mmaCompleteK2.values.add(getCompleteByteNumber(measures, "CompleteK", 2));
				//3
				mmaFinalMaster3.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 1, 3));
				mmaFinalExplore3.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 2, 3));
				mmaFinalHoove3.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 12, 3));
				mmaFinalWipe3.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 22, 3));				
				mmaInfoNew3.values.add(getCompleteByteNumber(measures, "New Info", 3));				
				mmaComplete3.values.add(getCompleteByteNumber(measures, "Complete", 3));
				mmaCompleteS3.values.add(getCompleteByteNumber(measures, "CompleteS", 3));
				mmaCompleteK3.values.add(getCompleteByteNumber(measures, "CompleteK", 3));
				//4
				mmaFinalMaster4.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 1, 4));
				mmaFinalExplore4.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 2, 4));
				mmaFinalHoove4.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 12, 4));
				mmaFinalWipe4.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 22, 4));				
				mmaInfoNew4.values.add(getCompleteByteNumber(measures, "New Info", 4));				
				mmaComplete4.values.add(getCompleteByteNumber(measures, "Complete", 4));
				mmaCompleteS4.values.add(getCompleteByteNumber(measures, "CompleteS", 4));
				mmaCompleteK4.values.add(getCompleteByteNumber(measures, "CompleteK", 4));
			}
					
			benchmarkTimeList.add(mmaBench);
			completeEnergieList.add(mmaEnergie);
			completeTicksList.add(mmaTicks);
			completeTimeList.add(mmaTime);
			
			//Exchange Informationen adden
			finalMasterList0.add(mmaFinalMaster0);
			finalHooveList0.add(mmaFinalHoove0);
			finalExploreList0.add(mmaFinalExplore0);
			finalWipeList0.add(mmaFinalWipe0);			
			completeExchangeList0.add(mmaComplete0);
			completeKExchangeList0.add(mmaCompleteK0);
			completeSExchangeList0.add(mmaCompleteS0);
			infoNewList0.add(mmaInfoNew0);
			//1
			finalMasterList1.add(mmaFinalMaster1);
			finalHooveList1.add(mmaFinalHoove1);
			finalExploreList1.add(mmaFinalExplore1);
			finalWipeList1.add(mmaFinalWipe1);			
			completeExchangeList1.add(mmaComplete1);
			completeKExchangeList1.add(mmaCompleteK1);
			completeSExchangeList1.add(mmaCompleteS1);
			infoNewList1.add(mmaInfoNew1);
			//2
			finalMasterList2.add(mmaFinalMaster2);
			finalHooveList2.add(mmaFinalHoove2);
			finalExploreList2.add(mmaFinalExplore2);
			finalWipeList2.add(mmaFinalWipe2);			
			completeExchangeList2.add(mmaComplete2);
			completeKExchangeList2.add(mmaCompleteK2);
			completeSExchangeList2.add(mmaCompleteS2);
			infoNewList2.add(mmaInfoNew2);
			//3
			finalMasterList3.add(mmaFinalMaster3);
			finalHooveList3.add(mmaFinalHoove3);
			finalExploreList3.add(mmaFinalExplore3);
			finalWipeList3.add(mmaFinalWipe3);			
			completeExchangeList3.add(mmaComplete3);
			completeKExchangeList3.add(mmaCompleteK3);
			completeSExchangeList3.add(mmaCompleteS3);
			infoNewList3.add(mmaInfoNew3);
			//4
			finalMasterList4.add(mmaFinalMaster4);
			finalHooveList4.add(mmaFinalHoove4);
			finalExploreList4.add(mmaFinalExplore4);
			finalWipeList4.add(mmaFinalWipe4);			
			completeExchangeList4.add(mmaComplete4);
			completeKExchangeList4.add(mmaCompleteK4);
			completeSExchangeList4.add(mmaCompleteS4);
			infoNewList4.add(mmaInfoNew4);
		}
				
		//add for wipeRobots
		for (int anzWipeRobots = 1; anzWipeRobots <= 10; anzWipeRobots++) {
			int numberRobots = 20 + anzWipeRobots;
			//roboter information
			MinMaxAveLong mmaBench = new MinMaxAveLong(numberRobots);
			MinMaxAveInt mmaTicks = new MinMaxAveInt(numberRobots);
			MinMaxAveDouble mmaTime = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaEnergie = new MinMaxAveDouble(numberRobots);
			//data exchange information
			//data exchange information
			MinMaxAveDouble mmaComplete0 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaCompleteK0 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaCompleteS0 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaInfoNew0 = new MinMaxAveDouble(numberRobots);						
			MinMaxAveDouble mmaFinalMaster0 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalExplore0 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalHoove0 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalWipe0 = new MinMaxAveDouble(numberRobots);	
			//1
			MinMaxAveDouble mmaComplete1 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaCompleteK1 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaCompleteS1 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaInfoNew1 = new MinMaxAveDouble(numberRobots);						
			MinMaxAveDouble mmaFinalMaster1 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalExplore1 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalHoove1 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalWipe1 = new MinMaxAveDouble(numberRobots);	
			//2
			MinMaxAveDouble mmaComplete2 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaCompleteK2 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaCompleteS2 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaInfoNew2 = new MinMaxAveDouble(numberRobots);						
			MinMaxAveDouble mmaFinalMaster2 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalExplore2 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalHoove2 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalWipe2 = new MinMaxAveDouble(numberRobots);	
			//3
			MinMaxAveDouble mmaComplete3 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaCompleteK3 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaCompleteS3 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaInfoNew3 = new MinMaxAveDouble(numberRobots);						
			MinMaxAveDouble mmaFinalMaster3 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalExplore3 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalHoove3 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalWipe3 = new MinMaxAveDouble(numberRobots);	
			//4
			MinMaxAveDouble mmaComplete4 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaCompleteK4 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaCompleteS4 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaInfoNew4 = new MinMaxAveDouble(numberRobots);						
			MinMaxAveDouble mmaFinalMaster4 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalExplore4 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalHoove4 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalWipe4 = new MinMaxAveDouble(numberRobots);	
			
			for (int runs = 1; runs < 6; runs++) {
				//Exchange measurement
				FileWorker fex = new FileWorker(path + "D" + exchangeValue + "\\M0_V2_CE10_CH10_CW" + anzWipeRobots + "_B0_D" + runs + "_exchange.txt");
				
				List<String> input = fex.readFile();	
				List<ExchangeMeasurement> inEvals = new ArrayList<ExchangeMeasurement>();				
				for (String line : input) {
					ExchangeMeasurement em = new ExchangeMeasurement("", "", 0);
					em = em.fromJson(line);
					inEvals.add(em);
				}
				List<EvaluationMeasurement> measures = mergeDataEvaluationMeasurement(inEvals);
				
				//robbi one information
				FileWorker rob1 = new FileWorker(path + "D" + exchangeValue + "\\M0_V2_CE10_CH10_CW" + anzWipeRobots + "_B0_D" + runs + "_Robbi_1.txt");				
				String firstLine = rob1.readFile().get(0);//.readNextLine();
				RobotMeasurement robM = new RobotMeasurement("");
				robM = robM.fromJson(firstLine);
				
				long benchmarkTime = robM.benchmarkTime;
				int completeTicks = robM.completeTicks;
				
				double completeTime = robM.completeTime;				
				double completeEnergie = robM.completeEnergie;
				
				for (int i = 2; i <= numberRobots + 1; i++) {
					FileWorker rob = new FileWorker(path + "D" + exchangeValue + "\\M0_V2_CE10_CH10_CW" + anzWipeRobots + "_B0_D" + runs + "_Robbi_" + i + ".txt");
					RobotMeasurement rM = new RobotMeasurement("");
					rM = rM.fromJson(rob.readNextLine());
					completeTime += rM.completeTime;
					completeEnergie += rM.completeEnergie;
				}
				
				mmaBench.values.add(benchmarkTime);
				mmaTicks.values.add(completeTicks);
				mmaTime.values.add(completeTime);
				mmaEnergie.values.add(completeEnergie);		
				//addBaytNumbers				
				mmaFinalMaster0.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 1, 0));
				mmaFinalExplore0.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 2, 0));
				mmaFinalHoove0.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 12, 0));
				mmaFinalWipe0.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 22, 0));				
				mmaInfoNew0.values.add(getCompleteByteNumber(measures, "New Info", 0));				
				mmaComplete0.values.add(getCompleteByteNumber(measures, "Complete", 0));
				mmaCompleteS0.values.add(getCompleteByteNumber(measures, "CompleteS", 0));
				mmaCompleteK0.values.add(getCompleteByteNumber(measures, "CompleteK", 0));
				//1
				mmaFinalMaster1.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 1, 1));
				mmaFinalExplore1.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 2, 1));
				mmaFinalHoove1.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 12, 1));
				mmaFinalWipe1.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 22, 1));				
				mmaInfoNew1.values.add(getCompleteByteNumber(measures, "New Info", 1));				
				mmaComplete1.values.add(getCompleteByteNumber(measures, "Complete", 1));
				mmaCompleteS1.values.add(getCompleteByteNumber(measures, "CompleteS", 1));
				mmaCompleteK1.values.add(getCompleteByteNumber(measures, "CompleteK", 1));
				//2
				mmaFinalMaster2.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 1, 2));
				mmaFinalExplore2.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 2, 2));
				mmaFinalHoove2.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 12, 2));
				mmaFinalWipe2.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 22, 2));				
				mmaInfoNew2.values.add(getCompleteByteNumber(measures, "New Info", 2));				
				mmaComplete2.values.add(getCompleteByteNumber(measures, "Complete", 2));
				mmaCompleteS2.values.add(getCompleteByteNumber(measures, "CompleteS", 2));
				mmaCompleteK2.values.add(getCompleteByteNumber(measures, "CompleteK", 2));
				//3
				mmaFinalMaster3.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 1, 3));
				mmaFinalExplore3.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 2, 3));
				mmaFinalHoove3.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 12, 3));
				mmaFinalWipe3.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 22, 3));				
				mmaInfoNew3.values.add(getCompleteByteNumber(measures, "New Info", 3));				
				mmaComplete3.values.add(getCompleteByteNumber(measures, "Complete", 3));
				mmaCompleteS3.values.add(getCompleteByteNumber(measures, "CompleteS", 3));
				mmaCompleteK3.values.add(getCompleteByteNumber(measures, "CompleteK", 3));
				//4
				mmaFinalMaster4.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 1, 4));
				mmaFinalExplore4.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 2, 4));
				mmaFinalHoove4.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 12, 4));
				mmaFinalWipe4.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 22, 4));				
				mmaInfoNew4.values.add(getCompleteByteNumber(measures, "New Info", 4));				
				mmaComplete4.values.add(getCompleteByteNumber(measures, "Complete", 4));
				mmaCompleteS4.values.add(getCompleteByteNumber(measures, "CompleteS", 4));
				mmaCompleteK4.values.add(getCompleteByteNumber(measures, "CompleteK", 4));
			}
					
			benchmarkTimeList.add(mmaBench);
			completeEnergieList.add(mmaEnergie);
			completeTicksList.add(mmaTicks);
			completeTimeList.add(mmaTime);
			
			//Exchange Informationen adden
			finalMasterList0.add(mmaFinalMaster0);
			finalHooveList0.add(mmaFinalHoove0);
			finalExploreList0.add(mmaFinalExplore0);
			finalWipeList0.add(mmaFinalWipe0);			
			completeExchangeList0.add(mmaComplete0);
			completeKExchangeList0.add(mmaCompleteK0);
			completeSExchangeList0.add(mmaCompleteS0);
			infoNewList0.add(mmaInfoNew0);
			//1
			finalMasterList1.add(mmaFinalMaster1);
			finalHooveList1.add(mmaFinalHoove1);
			finalExploreList1.add(mmaFinalExplore1);
			finalWipeList1.add(mmaFinalWipe1);			
			completeExchangeList1.add(mmaComplete1);
			completeKExchangeList1.add(mmaCompleteK1);
			completeSExchangeList1.add(mmaCompleteS1);
			infoNewList1.add(mmaInfoNew1);
			//2
			finalMasterList2.add(mmaFinalMaster2);
			finalHooveList2.add(mmaFinalHoove2);
			finalExploreList2.add(mmaFinalExplore2);
			finalWipeList2.add(mmaFinalWipe2);			
			completeExchangeList2.add(mmaComplete2);
			completeKExchangeList2.add(mmaCompleteK2);
			completeSExchangeList2.add(mmaCompleteS2);
			infoNewList2.add(mmaInfoNew2);
			//3
			finalMasterList3.add(mmaFinalMaster3);
			finalHooveList3.add(mmaFinalHoove3);
			finalExploreList3.add(mmaFinalExplore3);
			finalWipeList3.add(mmaFinalWipe3);			
			completeExchangeList3.add(mmaComplete3);
			completeKExchangeList3.add(mmaCompleteK3);
			completeSExchangeList3.add(mmaCompleteS3);
			infoNewList3.add(mmaInfoNew3);
			//4
			finalMasterList4.add(mmaFinalMaster4);
			finalHooveList4.add(mmaFinalHoove4);
			finalExploreList4.add(mmaFinalExplore4);
			finalWipeList4.add(mmaFinalWipe4);			
			completeExchangeList4.add(mmaComplete4);
			completeKExchangeList4.add(mmaCompleteK4);
			completeSExchangeList4.add(mmaCompleteS4);
			infoNewList4.add(mmaInfoNew4);
		}
		
		FileWorker fw = new FileWorker("D" + exchangeValue + "_M0_V2_" + zusatz + ".csv");
		String numberS = ";"; 
		
		List<String> ergebnis = new ArrayList<String>();
		int anzahlRobots = 30;
		for (int anzRobots = 1; anzRobots <= anzahlRobots; anzRobots++) {
			numberS = numberS + anzRobots + ";";
		}
		
		ergebnis.add("D" + exchangeValue + "_M0_V2");
		ergebnis.add(numberS);
		ergebnis.addAll(getMinAvgMaxLong(benchmarkTimeList, anzahlRobots, "Benchmarkzeit"));
		ergebnis.addAll(getMinAvgMaxInt(completeTicksList, anzahlRobots, "Iterationen"));
		ergebnis.addAll(getMinAvgMaxDouble(completeTimeList, anzahlRobots, "Rechenzeit"));
		ergebnis.addAll(getMinAvgMaxDouble(completeEnergieList, anzahlRobots, "Energieverbrauch"));
		ergebnis.add("Messungen in Byte");
		if (booleanConfig.completeK)
			ergebnis.addAll(getMinAvgMaxDouble(completeKExchangeList0, anzahlRobots, "Kompletter Austausch"));
		if (booleanConfig.completeS)
			ergebnis.addAll(getMinAvgMaxDouble(completeSExchangeList0, anzahlRobots, "State Austausch"));
		if (booleanConfig.kompletterAustausch)
			ergebnis.addAll(getMinAvgMaxDouble(completeExchangeList0, anzahlRobots, "Zeit/State Austausch"));
		if (booleanConfig.newInformation)
			ergebnis.addAll(getMinAvgMaxDouble(infoNewList0, anzahlRobots, "Neue Informationen"));
		ergebnis.add("Messungen in Anzahl komplett");
		if (booleanConfig.completeK)
			ergebnis.addAll(getMinAvgMaxDouble(completeKExchangeList1, anzahlRobots, "Kompletter Austausch"));
		if (booleanConfig.completeS)
			ergebnis.addAll(getMinAvgMaxDouble(completeSExchangeList1, anzahlRobots, "State Austausch"));
		if (booleanConfig.kompletterAustausch)
			ergebnis.addAll(getMinAvgMaxDouble(completeExchangeList1, anzahlRobots, "Zeit/State Austausch"));
		if (booleanConfig.newInformation)
			ergebnis.addAll(getMinAvgMaxDouble(infoNewList1, anzahlRobots, "Neue Informationen"));
		ergebnis.add("Messungen in Anzahl sonstiges Wissen");
		if (booleanConfig.completeK)
			ergebnis.addAll(getMinAvgMaxDouble(completeKExchangeList2, anzahlRobots, "Kompletter Austausch"));
		if (booleanConfig.completeS)
			ergebnis.addAll(getMinAvgMaxDouble(completeSExchangeList2, anzahlRobots, "State Austausch"));
		if (booleanConfig.kompletterAustausch)
			ergebnis.addAll(getMinAvgMaxDouble(completeExchangeList2, anzahlRobots, "Zeit/State Austausch"));
		if (booleanConfig.newInformation)
			ergebnis.addAll(getMinAvgMaxDouble(infoNewList2, anzahlRobots, "Neue Informationen"));
		ergebnis.add("Messungen in Anzahl Weltwissen");
		if (booleanConfig.completeK)
			ergebnis.addAll(getMinAvgMaxDouble(completeKExchangeList3, anzahlRobots, "Kompletter Austausch"));
		if (booleanConfig.completeS)
			ergebnis.addAll(getMinAvgMaxDouble(completeSExchangeList3, anzahlRobots, "State Austausch"));
		if (booleanConfig.kompletterAustausch)
			ergebnis.addAll(getMinAvgMaxDouble(completeExchangeList3, anzahlRobots, "Zeit/State Austausch"));
		if (booleanConfig.newInformation)
			ergebnis.addAll(getMinAvgMaxDouble(infoNewList3, anzahlRobots, "Neue Informationen"));
		ergebnis.add("Messungen in Anzahl Austausche");
		if (booleanConfig.completeK)
			ergebnis.addAll(getMinAvgMaxDouble(completeKExchangeList4, anzahlRobots, "Kompletter Austausch"));
		if (booleanConfig.completeS)
			ergebnis.addAll(getMinAvgMaxDouble(completeSExchangeList4, anzahlRobots, "State Austausch"));
		if (booleanConfig.kompletterAustausch)
			ergebnis.addAll(getMinAvgMaxDouble(completeExchangeList4, anzahlRobots, "Zeit/State Austausch"));
		if (booleanConfig.newInformation)
			ergebnis.addAll(getMinAvgMaxDouble(infoNewList4, anzahlRobots, "Neue Informationen"));
		ergebnis.add("Modell in komplett Byte");
		if (booleanConfig.endmodellMaster)
			ergebnis.addAll(getMinAvgMaxDouble(finalMasterList0, anzahlRobots, "Endmodelmaster"));
		if (booleanConfig.endmodellExplore)
			ergebnis.addAll(getMinAvgMaxDouble(finalExploreList0, anzahlRobots, "Endmodelerkunder"));
		if (booleanConfig.endmodellHoove)
			ergebnis.addAll(getMinAvgMaxDouble(finalHooveList0, anzahlRobots, "Endmodelsauger"));
		if (booleanConfig.endModellWipe)
			ergebnis.addAll(getMinAvgMaxDouble(finalWipeList0, anzahlRobots, "Endmodelwischer"));
		ergebnis.add("Modell in komplett Number mit Zeitstempel");
		if (booleanConfig.endmodellMaster)
			ergebnis.addAll(getMinAvgMaxDouble(finalMasterList1, anzahlRobots, "Endmodelmaster"));
		if (booleanConfig.endmodellExplore)
			ergebnis.addAll(getMinAvgMaxDouble(finalExploreList1, anzahlRobots, "Endmodelerkunder"));
		if (booleanConfig.endmodellHoove)
			ergebnis.addAll(getMinAvgMaxDouble(finalHooveList1, anzahlRobots, "Endmodelsauger"));
		if (booleanConfig.endModellWipe)
			ergebnis.addAll(getMinAvgMaxDouble(finalWipeList1, anzahlRobots, "Endmodelwischer"));
		ergebnis.add("Modell in Anzahl sonstiges Wissen");
		if (booleanConfig.endmodellMaster)
			ergebnis.addAll(getMinAvgMaxDouble(finalMasterList2, anzahlRobots, "Endmodelmaster"));
		if (booleanConfig.endmodellExplore)
			ergebnis.addAll(getMinAvgMaxDouble(finalExploreList2, anzahlRobots, "Endmodelerkunder"));
		if (booleanConfig.endmodellHoove)
			ergebnis.addAll(getMinAvgMaxDouble(finalHooveList2, anzahlRobots, "Endmodelsauger"));
		if (booleanConfig.endModellWipe)
			ergebnis.addAll(getMinAvgMaxDouble(finalWipeList2, anzahlRobots, "Endmodelwischer"));
		ergebnis.add("Modell in Anzahl Weltwissen");
		if (booleanConfig.endmodellMaster)
			ergebnis.addAll(getMinAvgMaxDouble(finalMasterList3, anzahlRobots, "Endmodelmaster"));
		if (booleanConfig.endmodellExplore)
			ergebnis.addAll(getMinAvgMaxDouble(finalExploreList3, anzahlRobots, "Endmodelerkunder"));
		if (booleanConfig.endmodellHoove)
			ergebnis.addAll(getMinAvgMaxDouble(finalHooveList3, anzahlRobots, "Endmodelsauger"));
		if (booleanConfig.endModellWipe)
			ergebnis.addAll(getMinAvgMaxDouble(finalWipeList3, anzahlRobots, "Endmodelwischer"));
		ergebnis.add("Modell in Anzahl komplett");
		if (booleanConfig.endmodellMaster)
			ergebnis.addAll(getMinAvgMaxDouble(finalMasterList4, anzahlRobots, "Endmodelmaster"));
		if (booleanConfig.endmodellExplore)
			ergebnis.addAll(getMinAvgMaxDouble(finalExploreList4, anzahlRobots, "Endmodelerkunder"));
		if (booleanConfig.endmodellHoove)
			ergebnis.addAll(getMinAvgMaxDouble(finalHooveList4, anzahlRobots, "Endmodelsauger"));
		if (booleanConfig.endModellWipe)
			ergebnis.addAll(getMinAvgMaxDouble(finalWipeList4, anzahlRobots, "Endmodelwischer"));
		
		for (String s: ergebnis) {
			fw.addLineToFile(s.replace('.', ','));
		}		
	}
	
	public List<EvaluationMeasurement> mergeDataEvaluationMeasurement (List<ExchangeMeasurement> measure) {
		List<EvaluationMeasurement> result = new ArrayList<EvaluationMeasurement>();
		EvaluationMeasurement complete = new EvaluationMeasurement("Master", "Complete", 0);
		EvaluationMeasurement completeK = new EvaluationMeasurement("Master", "CompleteK", 0);
		EvaluationMeasurement completeS = new EvaluationMeasurement("Master", "CompleteS", 0);		
		EvaluationMeasurement infoNew = new EvaluationMeasurement("Master", "New Info", 0);
		
		boolean isComplete = false, isCompleteK = false, isCompleteS = false, isNewInfo = false;
		for (ExchangeMeasurement em : measure) {
			if (em.getName2().endsWith("S")) {
				completeS.addMeasurement(em);
				completeS.setNumber(complete.getNumber() + 1);
				isCompleteS = true;
			} else if (em.getName2().endsWith("K")) {
				completeK.addMeasurement(em);
				completeK.setNumber(complete.getNumber() + 1);
				isCompleteK = true;
			} else if (em.getName2().equals("New Info")) {
				infoNew.addMeasurement(em);
				infoNew.setNumber(infoNew.getNumber() + 1);
				isNewInfo = true;
			} else if (em.getName2().equals("FinalModel")) {
				EvaluationMeasurement finalModel = new EvaluationMeasurement(em.getName1(), em.getName2(), em.getIteration());
				finalModel.addMeasurement(em);
				result.add(finalModel);
			} else {
				complete.addMeasurement(em);
				complete.setNumber(complete.getNumber() + 1);
				isComplete = true;
			}
		}
		if (isCompleteK)
			result.add(completeK);
		if (isComplete)
			result.add(complete);
		if (isCompleteS)
			result.add(completeS);
		if (isNewInfo)
			result.add(infoNew);
		
		return result;
	}
	
	public List<String> getMinAvgMaxDouble(List<MinMaxAveDouble> liste, int anzahl, String start) {
		String min = start + " Min;";	
		String max = start + " Max;";
		String avg = start + " Avg;";
		for (int anzRobots = 1; anzRobots <= anzahl; anzRobots++) {
			for (MinMaxAveDouble i : liste) {
				if (i.numberRobots == anzRobots) {
					max = max + i.getMaxValue() + ";";
					min = min + i.getMinValue() + ";";
					avg = avg + i.getAveValue() + ";";
				}
			}
		}
		List<String> result = new ArrayList<String>();
		result.add(max);
		result.add(avg);
		result.add(min);
		return result;
	}
	
	public List<String> getMinAvgMaxInt(List<MinMaxAveInt> liste, int anzahl, String start) {
		String min = start + " Min;";	
		String max = start + " Max;";
		String avg = start + " Avg;";
		for (int anzRobots = 1; anzRobots <= anzahl; anzRobots++) {
			for (MinMaxAveInt i : liste) {
				if (i.numberRobots == anzRobots) {
					max = max + i.getMaxValue() + ";";
					min = min + i.getMinValue() + ";";
					avg = avg + i.getAveValue() + ";";
				}
			}
		}
		List<String> result = new ArrayList<String>();
		result.add(max);
		result.add(avg);
		result.add(min);
		return result;
	}
	
	public List<String> getMinAvgMaxLong(List<MinMaxAveLong> liste, int anzahl, String start) {
		String min = start + " Min;";	
		String max = start + " Max;";
		String avg = start + " Avg;";
		for (int anzRobots = 1; anzRobots <= anzahl; anzRobots++) {
			for (MinMaxAveLong i : liste) {
				if (i.numberRobots == anzRobots) {
					max = max + i.getMaxValue() + ";";
					min = min + i.getMinValue() + ";";
					avg = avg + i.getAveValue() + ";";
				}
			}
		}
		List<String> result = new ArrayList<String>();
		result.add(max);
		result.add(avg);
		result.add(min);
		return result;
	}
	
	
	public void mapOne (String path, int mapValue, int configValue, int newFieldValue, String zusatz, BooleanEvaluation booleanConfig) {
		mapValue = 1;
		//Listen Mit MinMaxAve werten zum einfügen der gefundenen Werte
		List<MinMaxAveLong> benchmarkTimeList = new LinkedList<MinMaxAveLong>();
		List<MinMaxAveInt> completeTicksList = new LinkedList<MinMaxAveInt>();
		List<MinMaxAveDouble> completeTimeList = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> completeEnergieList = new LinkedList<MinMaxAveDouble>();		
		//0
		List<MinMaxAveDouble> finalMasterList0 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> finalHooveList0 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> finalExploreList0 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> finalWipeList0 = new LinkedList<MinMaxAveDouble>();		
		List<MinMaxAveDouble> exploreList0 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> hooveList0 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> wipeList0 = new LinkedList<MinMaxAveDouble>();		
		List<MinMaxAveDouble> completeExchangeList0 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> otherList0 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> sendNewList0 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> sendNullList0 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> infoNewList0 = new LinkedList<MinMaxAveDouble>();
		//1
		List<MinMaxAveDouble> finalMasterList1 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> finalHooveList1 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> finalExploreList1 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> finalWipeList1 = new LinkedList<MinMaxAveDouble>();		
		List<MinMaxAveDouble> exploreList1 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> hooveList1 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> wipeList1 = new LinkedList<MinMaxAveDouble>();		
		List<MinMaxAveDouble> completeExchangeList1 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> otherList1 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> sendNewList1 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> sendNullList1 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> infoNewList1 = new LinkedList<MinMaxAveDouble>();
		//2
		List<MinMaxAveDouble> finalMasterList2 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> finalHooveList2 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> finalExploreList2 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> finalWipeList2 = new LinkedList<MinMaxAveDouble>();		
		List<MinMaxAveDouble> exploreList2 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> hooveList2 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> wipeList2 = new LinkedList<MinMaxAveDouble>();		
		List<MinMaxAveDouble> completeExchangeList2 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> otherList2 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> sendNewList2 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> sendNullList2 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> infoNewList2 = new LinkedList<MinMaxAveDouble>();
		//3
		List<MinMaxAveDouble> finalMasterList3 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> finalHooveList3 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> finalExploreList3 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> finalWipeList3 = new LinkedList<MinMaxAveDouble>();		
		List<MinMaxAveDouble> exploreList3 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> hooveList3 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> wipeList3 = new LinkedList<MinMaxAveDouble>();		
		List<MinMaxAveDouble> completeExchangeList3 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> otherList3 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> sendNewList3 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> sendNullList3 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> infoNewList3 = new LinkedList<MinMaxAveDouble>();
		//4
		List<MinMaxAveDouble> finalMasterList4 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> finalHooveList4 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> finalExploreList4 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> finalWipeList4 = new LinkedList<MinMaxAveDouble>();		
		List<MinMaxAveDouble> exploreList4 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> hooveList4 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> wipeList4 = new LinkedList<MinMaxAveDouble>();		
		List<MinMaxAveDouble> completeExchangeList4 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> otherList4 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> sendNewList4 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> sendNullList4 = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> infoNewList4 = new LinkedList<MinMaxAveDouble>();
		
		//add for exploreRobots
		for (int anzExploreRobots = 1; anzExploreRobots <= 10; anzExploreRobots++) {
			
			int numberRobots = anzExploreRobots;
			//roboter information
			MinMaxAveLong mmaBench = new MinMaxAveLong(numberRobots);
			MinMaxAveInt mmaTicks = new MinMaxAveInt(numberRobots);
			MinMaxAveDouble mmaTime = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaEnergie = new MinMaxAveDouble(numberRobots);
			
			//0 data exchange information
			MinMaxAveDouble mmaComplete0 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaOther0 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaSendNew0 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaSendNull0 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaInfoNew0 = new MinMaxAveDouble(numberRobots);			
			MinMaxAveDouble mmaExplore0 = new MinMaxAveDouble(numberRobots);	
			MinMaxAveDouble mmaHoove0 = new MinMaxAveDouble(numberRobots);	
			MinMaxAveDouble mmaWipe0 = new MinMaxAveDouble(numberRobots);			
			MinMaxAveDouble mmaFinalMaster0 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalExplore0 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalHoove0 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalWipe0 = new MinMaxAveDouble(numberRobots);
			//1
			MinMaxAveDouble mmaComplete1 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaOther1 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaSendNew1 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaSendNull1 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaInfoNew1 = new MinMaxAveDouble(numberRobots);			
			MinMaxAveDouble mmaExplore1 = new MinMaxAveDouble(numberRobots);	
			MinMaxAveDouble mmaHoove1 = new MinMaxAveDouble(numberRobots);	
			MinMaxAveDouble mmaWipe1 = new MinMaxAveDouble(numberRobots);			
			MinMaxAveDouble mmaFinalMaster1 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalExplore1 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalHoove1 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalWipe1 = new MinMaxAveDouble(numberRobots);
			//2
			MinMaxAveDouble mmaComplete2 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaOther2 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaSendNew2 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaSendNull2 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaInfoNew2 = new MinMaxAveDouble(numberRobots);			
			MinMaxAveDouble mmaExplore2 = new MinMaxAveDouble(numberRobots);	
			MinMaxAveDouble mmaHoove2 = new MinMaxAveDouble(numberRobots);	
			MinMaxAveDouble mmaWipe2 = new MinMaxAveDouble(numberRobots);			
			MinMaxAveDouble mmaFinalMaster2 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalExplore2 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalHoove2 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalWipe2 = new MinMaxAveDouble(numberRobots);
			//3
			MinMaxAveDouble mmaComplete3 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaOther3 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaSendNew3 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaSendNull3 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaInfoNew3 = new MinMaxAveDouble(numberRobots);			
			MinMaxAveDouble mmaExplore3 = new MinMaxAveDouble(numberRobots);	
			MinMaxAveDouble mmaHoove3 = new MinMaxAveDouble(numberRobots);	
			MinMaxAveDouble mmaWipe3 = new MinMaxAveDouble(numberRobots);			
			MinMaxAveDouble mmaFinalMaster3 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalExplore3 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalHoove3 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalWipe3 = new MinMaxAveDouble(numberRobots);
			//4
			MinMaxAveDouble mmaComplete4 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaOther4 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaSendNew4 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaSendNull4 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaInfoNew4 = new MinMaxAveDouble(numberRobots);			
			MinMaxAveDouble mmaExplore4 = new MinMaxAveDouble(numberRobots);	
			MinMaxAveDouble mmaHoove4 = new MinMaxAveDouble(numberRobots);	
			MinMaxAveDouble mmaWipe4 = new MinMaxAveDouble(numberRobots);			
			MinMaxAveDouble mmaFinalMaster4 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalExplore4 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalHoove4 = new MinMaxAveDouble(numberRobots);
			MinMaxAveDouble mmaFinalWipe4 = new MinMaxAveDouble(numberRobots);
			
			for (int runs = 1; runs < 6; runs++) {
				//Exchange measurement
				FileWorker fex = new FileWorker(path + "M" + mapValue + "\\M" 
						+ mapValue + "_V" + configValue + "_CE" + anzExploreRobots + "_CH0_CW0_B" 
						+ newFieldValue + "_D" + runs + "_exchange.txt");
				
				List<String> input = fex.readFile();	
				List<ExchangeMeasurement> inEvals = new ArrayList<ExchangeMeasurement>();				
				for (String line : input) {
					ExchangeMeasurement em = new ExchangeMeasurement("", "", 0);
					em = em.fromJson(line);
					inEvals.add(em);
				}
				List<EvaluationMeasurement> measures = mergeEvaluationMeasurement(inEvals);
				
				//robbi one information
				FileWorker rob1 = new FileWorker(path + "M" + mapValue + "\\M" 
						+ mapValue + "_V" + configValue + "_CE" + anzExploreRobots + "_CH0_CW0_B" 
						+ newFieldValue + "_D" + runs + "_Robbi_1.txt");				
				String firstLine = rob1.readFile().get(0);//.readNextLine();
				RobotMeasurement robM = new RobotMeasurement("");
				robM = robM.fromJson(firstLine);
				
				long benchmarkTime = robM.benchmarkTime;
				int completeTicks = robM.completeTicks;
				
				double completeTime = robM.completeTime;				
				double completeEnergie = robM.completeEnergie;
				
				for (int i = 2; i <= numberRobots + 1; i++) {
					FileWorker rob = new FileWorker(path + "M" + mapValue + "\\M" 
							+ mapValue + "_V" + configValue + "_CE" + anzExploreRobots + "_CH0_CW0_B" 
							+ newFieldValue + "_D" + runs + "_Robbi_" + i + ".txt");
					RobotMeasurement rM = new RobotMeasurement("");
					rM = rM.fromJson(rob.readNextLine());
					completeTime += rM.completeTime;
					completeEnergie += rM.completeEnergie;
				}
				
				mmaBench.values.add(benchmarkTime);
				mmaTicks.values.add(completeTicks);
				mmaTime.values.add(completeTime);
				mmaEnergie.values.add(completeEnergie);		
				//addBaytNumbers 
				//0
				mmaFinalMaster0.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 1, 0));
				mmaFinalExplore0.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 2, 0));
				mmaFinalHoove0.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 12, 0));
				mmaFinalWipe0.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 22, 0));				
				mmaExplore0.values.add(getCompleteByteNumber(measures, "ExploreInformationNewDest", 0));
				mmaHoove0.values.add(getCompleteByteNumber(measures, "HooveInformationNewDest", 0));
				mmaWipe0.values.add(getCompleteByteNumber(measures, "WipeInformationNewDest", 0));				
				mmaSendNew0.values.add(getCompleteByteNumber(measures, "SendNewDest", 0));
				mmaSendNull0.values.add(getCompleteByteNumber(measures, "SendNULLDest", 0));
				mmaInfoNew0.values.add(getCompleteByteNumber(measures, "New Info", 0));				
				mmaComplete0.values.add(getCompleteByteNumber(measures, "Complete", 0));
				mmaOther0.values.add(getCompleteByteNumber(measures, "Other", 0));
				//1			
				mmaFinalMaster1.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 1, 1));
				mmaFinalExplore1.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 2, 1));
				mmaFinalHoove1.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 12, 1));
				mmaFinalWipe1.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 22, 1));				
				mmaExplore1.values.add(getCompleteByteNumber(measures, "ExploreInformationNewDest", 1));
				mmaHoove1.values.add(getCompleteByteNumber(measures, "HooveInformationNewDest", 1));
				mmaWipe1.values.add(getCompleteByteNumber(measures, "WipeInformationNewDest", 1));				
				mmaSendNew1.values.add(getCompleteByteNumber(measures, "SendNewDest", 1));
				mmaSendNull1.values.add(getCompleteByteNumber(measures, "SendNULLDest", 1));
				mmaInfoNew1.values.add(getCompleteByteNumber(measures, "New Info", 1));				
				mmaComplete1.values.add(getCompleteByteNumber(measures, "Complete", 1));
				mmaOther1.values.add(getCompleteByteNumber(measures, "Other", 1));
				//2
				mmaFinalMaster2.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 1, 2));
				mmaFinalExplore2.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 2, 2));
				mmaFinalHoove2.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 12, 2));
				mmaFinalWipe2.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 22, 2));				
				mmaExplore2.values.add(getCompleteByteNumber(measures, "ExploreInformationNewDest", 2));
				mmaHoove2.values.add(getCompleteByteNumber(measures, "HooveInformationNewDest", 2));
				mmaWipe2.values.add(getCompleteByteNumber(measures, "WipeInformationNewDest", 2));				
				mmaSendNew2.values.add(getCompleteByteNumber(measures, "SendNewDest", 2));
				mmaSendNull2.values.add(getCompleteByteNumber(measures, "SendNULLDest", 2));
				mmaInfoNew2.values.add(getCompleteByteNumber(measures, "New Info", 2));				
				mmaComplete2.values.add(getCompleteByteNumber(measures, "Complete", 2));
				mmaOther2.values.add(getCompleteByteNumber(measures, "Other", 2));
				//3
				mmaFinalMaster3.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 1, 3));
				mmaFinalExplore3.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 2, 3));
				mmaFinalHoove3.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 12, 3));
				mmaFinalWipe3.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 22, 3));				
				mmaExplore3.values.add(getCompleteByteNumber(measures, "ExploreInformationNewDest", 3));
				mmaHoove3.values.add(getCompleteByteNumber(measures, "HooveInformationNewDest", 3));
				mmaWipe3.values.add(getCompleteByteNumber(measures, "WipeInformationNewDest", 3));				
				mmaSendNew3.values.add(getCompleteByteNumber(measures, "SendNewDest", 3));
				mmaSendNull3.values.add(getCompleteByteNumber(measures, "SendNULLDest", 3));
				mmaInfoNew3.values.add(getCompleteByteNumber(measures, "New Info", 3));				
				mmaComplete3.values.add(getCompleteByteNumber(measures, "Complete", 3));
				mmaOther3.values.add(getCompleteByteNumber(measures, "Other", 3));
				//4
				mmaFinalMaster4.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 1, 4));
				mmaFinalExplore4.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 2, 4));
				mmaFinalHoove4.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 12, 4));
				mmaFinalWipe4.values.add(getCompleteByteNumberFinal(measures, "FinalModel", 22, 4));				
				mmaExplore4.values.add(getCompleteByteNumber(measures, "ExploreInformationNewDest", 4));
				mmaHoove4.values.add(getCompleteByteNumber(measures, "HooveInformationNewDest", 4));
				mmaWipe4.values.add(getCompleteByteNumber(measures, "WipeInformationNewDest", 4));				
				mmaSendNew4.values.add(getCompleteByteNumber(measures, "SendNewDest", 4));
				mmaSendNull4.values.add(getCompleteByteNumber(measures, "SendNULLDest", 4));
				mmaInfoNew4.values.add(getCompleteByteNumber(measures, "New Info", 4));				
				mmaComplete4.values.add(getCompleteByteNumber(measures, "Complete", 4));
				mmaOther4.values.add(getCompleteByteNumber(measures, "Other", 4));
			}
			
			benchmarkTimeList.add(mmaBench);
			completeEnergieList.add(mmaEnergie);
			completeTicksList.add(mmaTicks);
			completeTimeList.add(mmaTime);
			
			//Exchange Informationen adden 0
			finalMasterList0.add(mmaFinalMaster0);
			finalHooveList0.add(mmaFinalHoove0);
			finalExploreList0.add(mmaFinalExplore0);
			finalWipeList0.add(mmaFinalWipe0);			
			exploreList0.add(mmaExplore0);
			hooveList0.add(mmaHoove0);
			wipeList0.add(mmaWipe0);
			completeExchangeList0.add(mmaComplete0);
			sendNewList0.add(mmaSendNew0);
			sendNullList0.add(mmaSendNull0);
			infoNewList0.add(mmaInfoNew0);
			otherList0.add(mmaOther0);
			//1
			finalMasterList1.add(mmaFinalMaster1);
			finalHooveList1.add(mmaFinalHoove1);
			finalExploreList1.add(mmaFinalExplore1);
			finalWipeList1.add(mmaFinalWipe1);			
			exploreList1.add(mmaExplore1);
			hooveList1.add(mmaHoove1);
			wipeList1.add(mmaWipe1);
			completeExchangeList1.add(mmaComplete1);
			sendNewList1.add(mmaSendNew1);
			sendNullList1.add(mmaSendNull1);
			infoNewList1.add(mmaInfoNew1);
			otherList1.add(mmaOther1);
			//2
			finalMasterList2.add(mmaFinalMaster2);
			finalHooveList2.add(mmaFinalHoove2);
			finalExploreList2.add(mmaFinalExplore2);
			finalWipeList2.add(mmaFinalWipe2);			
			exploreList2.add(mmaExplore2);
			hooveList2.add(mmaHoove2);
			wipeList2.add(mmaWipe2);
			completeExchangeList2.add(mmaComplete2);
			sendNewList2.add(mmaSendNew2);
			sendNullList2.add(mmaSendNull2);
			infoNewList2.add(mmaInfoNew2);
			otherList2.add(mmaOther2);
			//3
			finalMasterList3.add(mmaFinalMaster3);
			finalHooveList3.add(mmaFinalHoove3);
			finalExploreList3.add(mmaFinalExplore3);
			finalWipeList3.add(mmaFinalWipe3);			
			exploreList3.add(mmaExplore3);
			hooveList3.add(mmaHoove3);
			wipeList3.add(mmaWipe3);
			completeExchangeList3.add(mmaComplete3);
			sendNewList3.add(mmaSendNew3);
			sendNullList3.add(mmaSendNull3);
			infoNewList3.add(mmaInfoNew3);
			otherList3.add(mmaOther3);
			//4
			finalMasterList4.add(mmaFinalMaster4);
			finalHooveList4.add(mmaFinalHoove4);
			finalExploreList4.add(mmaFinalExplore4);
			finalWipeList4.add(mmaFinalWipe4);			
			exploreList4.add(mmaExplore4);
			hooveList4.add(mmaHoove4);
			wipeList4.add(mmaWipe4);
			completeExchangeList4.add(mmaComplete4);
			sendNewList4.add(mmaSendNew4);
			sendNullList4.add(mmaSendNull4);
			infoNewList4.add(mmaInfoNew4);
			otherList4.add(mmaOther4);
		}		
		
		FileWorker fw = new FileWorker("M" + mapValue + "_V" + configValue + "_B" + newFieldValue + zusatz + ".csv");
		String numberS = ";";		
		List<String> ergebnis = new ArrayList<String>();
		int anzahlRobots = 30;
		for (int anzRobots = 1; anzRobots <= anzahlRobots; anzRobots++) {
			numberS = numberS + anzRobots + ";";
		}
		
		ergebnis.add("M" + mapValue + "_V" + configValue + "_B" + newFieldValue);
		ergebnis.add(numberS);
		ergebnis.addAll(getMinAvgMaxLong(benchmarkTimeList, anzahlRobots, "Benchmarkzeit"));
		ergebnis.addAll(getMinAvgMaxInt(completeTicksList, anzahlRobots, "Iterationen"));
		ergebnis.addAll(getMinAvgMaxDouble(completeTimeList, anzahlRobots, "Rechenzeit"));
		ergebnis.addAll(getMinAvgMaxDouble(completeEnergieList, anzahlRobots, "Energieverbrauch"));
		ergebnis.add("Messungen in Byte");
		if (booleanConfig.exploreExchange)
			ergebnis.addAll(getMinAvgMaxDouble(exploreList0, anzahlRobots, "Zielaustausch Erkunden"));
		/*if (booleanConfig.hooveExchange)
			ergebnis.addAll(getMinAvgMaxDouble(hooveList0, anzahlRobots, "Zielaustausch Saugen"));
		if (booleanConfig.wipeExchange)
			ergebnis.addAll(getMinAvgMaxDouble(wipeList0, anzahlRobots, "Zielaustausch Wischen"));*/		
		if (booleanConfig.kompletterAustausch)
			ergebnis.addAll(getMinAvgMaxDouble(completeExchangeList0, anzahlRobots, "Kompletter Austausch"));
		if (booleanConfig.newInformation)
			ergebnis.addAll(getMinAvgMaxDouble(infoNewList0, anzahlRobots, "Neue Informationen"));
		if (booleanConfig.other)
			ergebnis.addAll(getMinAvgMaxDouble(otherList0, anzahlRobots, "Andereraustausch"));
		if (booleanConfig.sendNewInformation)
			ergebnis.addAll(getMinAvgMaxDouble(sendNewList0, anzahlRobots, "Neue Informationen Senden"));
		if (booleanConfig.sendNull)
			ergebnis.addAll(getMinAvgMaxDouble(sendNullList0, anzahlRobots, "Sende Null Ziel"));
		ergebnis.add("Messungen in Anzahl komplett");
		if (booleanConfig.exploreExchange)
			ergebnis.addAll(getMinAvgMaxDouble(exploreList1, anzahlRobots, "Zielaustausch Erkunden"));
		/*if (booleanConfig.hooveExchange)
			ergebnis.addAll(getMinAvgMaxDouble(hooveList1, anzahlRobots, "Zielaustausch Saugen"));
		if (booleanConfig.wipeExchange)
			ergebnis.addAll(getMinAvgMaxDouble(wipeList1, anzahlRobots, "Zielaustausch Wischen"));*/		
		if (booleanConfig.kompletterAustausch)
			ergebnis.addAll(getMinAvgMaxDouble(completeExchangeList1, anzahlRobots, "Kompletter Austausch"));
		if (booleanConfig.newInformation)
			ergebnis.addAll(getMinAvgMaxDouble(infoNewList1, anzahlRobots, "Neue Informationen"));
		if (booleanConfig.other)
			ergebnis.addAll(getMinAvgMaxDouble(otherList1, anzahlRobots, "Andereraustausch"));
		if (booleanConfig.sendNewInformation)
			ergebnis.addAll(getMinAvgMaxDouble(sendNewList1, anzahlRobots, "Neue Informationen Senden"));
		if (booleanConfig.sendNull)
			ergebnis.addAll(getMinAvgMaxDouble(sendNullList1, anzahlRobots, "Sende Null Ziel"));
		ergebnis.add("Messungen in Anzahl sonstiges Wissen");
		if (booleanConfig.exploreExchange)
			ergebnis.addAll(getMinAvgMaxDouble(exploreList2, anzahlRobots, "Zielaustausch Erkunden"));
		/*if (booleanConfig.hooveExchange)
			ergebnis.addAll(getMinAvgMaxDouble(hooveList2, anzahlRobots, "Zielaustausch Saugen"));
		if (booleanConfig.wipeExchange)
			ergebnis.addAll(getMinAvgMaxDouble(wipeList2, anzahlRobots, "Zielaustausch Wischen"));	*/	
		if (booleanConfig.kompletterAustausch)
			ergebnis.addAll(getMinAvgMaxDouble(completeExchangeList2, anzahlRobots, "Kompletter Austausch"));
		if (booleanConfig.newInformation)
			ergebnis.addAll(getMinAvgMaxDouble(infoNewList2, anzahlRobots, "Neue Informationen"));
		if (booleanConfig.other)
			ergebnis.addAll(getMinAvgMaxDouble(otherList2, anzahlRobots, "Andereraustausch"));
		if (booleanConfig.sendNewInformation)
			ergebnis.addAll(getMinAvgMaxDouble(sendNewList2, anzahlRobots, "Neue Informationen Senden"));
		if (booleanConfig.sendNull)
			ergebnis.addAll(getMinAvgMaxDouble(sendNullList2, anzahlRobots, "Sende Null Ziel"));
		ergebnis.add("Messungen in Anzahl Weltwissen");
		if (booleanConfig.exploreExchange)
			ergebnis.addAll(getMinAvgMaxDouble(exploreList3, anzahlRobots, "Zielaustausch Erkunden"));
		/*if (booleanConfig.hooveExchange)
			ergebnis.addAll(getMinAvgMaxDouble(hooveList3, anzahlRobots, "Zielaustausch Saugen"));
		if (booleanConfig.wipeExchange)
			ergebnis.addAll(getMinAvgMaxDouble(wipeList3, anzahlRobots, "Zielaustausch Wischen"));	*/	
		if (booleanConfig.kompletterAustausch)
			ergebnis.addAll(getMinAvgMaxDouble(completeExchangeList3, anzahlRobots, "Kompletter Austausch"));
		if (booleanConfig.newInformation)
			ergebnis.addAll(getMinAvgMaxDouble(infoNewList3, anzahlRobots, "Neue Informationen"));
		if (booleanConfig.other)
			ergebnis.addAll(getMinAvgMaxDouble(otherList3, anzahlRobots, "Andereraustausch"));
		if (booleanConfig.sendNewInformation)
			ergebnis.addAll(getMinAvgMaxDouble(sendNewList3, anzahlRobots, "Neue Informationen Senden"));
		if (booleanConfig.sendNull)
			ergebnis.addAll(getMinAvgMaxDouble(sendNullList3, anzahlRobots, "Sende Null Ziel"));
		ergebnis.add("Messungen in Anzahl Austausche");
		if (booleanConfig.exploreExchange)
			ergebnis.addAll(getMinAvgMaxDouble(exploreList4, anzahlRobots, "Zielaustausch Erkunden"));
		/*if (booleanConfig.hooveExchange)
			ergebnis.addAll(getMinAvgMaxDouble(hooveList4, anzahlRobots, "Zielaustausch Saugen"));
		if (booleanConfig.wipeExchange)
			ergebnis.addAll(getMinAvgMaxDouble(wipeList4, anzahlRobots, "Zielaustausch Wischen"));	*/	
		if (booleanConfig.kompletterAustausch)
			ergebnis.addAll(getMinAvgMaxDouble(completeExchangeList4, anzahlRobots, "Kompletter Austausch"));
		if (booleanConfig.newInformation)
			ergebnis.addAll(getMinAvgMaxDouble(infoNewList4, anzahlRobots, "Neue Informationen"));
		if (booleanConfig.other)
			ergebnis.addAll(getMinAvgMaxDouble(otherList4, anzahlRobots, "Andereraustausch"));
		if (booleanConfig.sendNewInformation)
			ergebnis.addAll(getMinAvgMaxDouble(sendNewList4, anzahlRobots, "Neue Informationen Senden"));
		if (booleanConfig.sendNull)
			ergebnis.addAll(getMinAvgMaxDouble(sendNullList4, anzahlRobots, "Sende Null Ziel"));
		//Modeloutput
		ergebnis.add("Modell in komplett Byte");
		if (booleanConfig.endmodellMaster)
			ergebnis.addAll(getMinAvgMaxDouble(finalMasterList0, anzahlRobots, "Endmodelmaster"));
		if (booleanConfig.endmodellExplore)
			ergebnis.addAll(getMinAvgMaxDouble(finalExploreList0, anzahlRobots, "Endmodelerkunder"));
		/*if (booleanConfig.endmodellHoove)
			ergebnis.addAll(getMinAvgMaxDouble(finalHooveList0, anzahlRobots, "Endmodelsauger"));
		if (booleanConfig.endModellWipe)
			ergebnis.addAll(getMinAvgMaxDouble(finalWipeList0, anzahlRobots, "Endmodelwischer"));*/
		ergebnis.add("Modell in komplett Number mit Zeitstempel");
		if (booleanConfig.endmodellMaster)
			ergebnis.addAll(getMinAvgMaxDouble(finalMasterList1, anzahlRobots, "Endmodelmaster"));
		if (booleanConfig.endmodellExplore)
			ergebnis.addAll(getMinAvgMaxDouble(finalExploreList1, anzahlRobots, "Endmodelerkunder"));
		/*if (booleanConfig.endmodellHoove)
			ergebnis.addAll(getMinAvgMaxDouble(finalHooveList1, anzahlRobots, "Endmodelsauger"));
		if (booleanConfig.endModellWipe)
			ergebnis.addAll(getMinAvgMaxDouble(finalWipeList1, anzahlRobots, "Endmodelwischer"));*/
		ergebnis.add("Modell in Anzahl sonstiges Wissen");
		if (booleanConfig.endmodellMaster)
			ergebnis.addAll(getMinAvgMaxDouble(finalMasterList2, anzahlRobots, "Endmodelmaster"));
		if (booleanConfig.endmodellExplore)
			ergebnis.addAll(getMinAvgMaxDouble(finalExploreList2, anzahlRobots, "Endmodelerkunder"));
		/*if (booleanConfig.endmodellHoove)
			ergebnis.addAll(getMinAvgMaxDouble(finalHooveList2, anzahlRobots, "Endmodelsauger"));
		if (booleanConfig.endModellWipe)
			ergebnis.addAll(getMinAvgMaxDouble(finalWipeList2, anzahlRobots, "Endmodelwischer"));*/
		ergebnis.add("Modell in Anzahl Weltwissen");
		if (booleanConfig.endmodellMaster)
			ergebnis.addAll(getMinAvgMaxDouble(finalMasterList3, anzahlRobots, "Endmodelmaster"));
		if (booleanConfig.endmodellExplore)
			ergebnis.addAll(getMinAvgMaxDouble(finalExploreList3, anzahlRobots, "Endmodelerkunder"));
		/*if (booleanConfig.endmodellHoove)
			ergebnis.addAll(getMinAvgMaxDouble(finalHooveList3, anzahlRobots, "Endmodelsauger"));
		if (booleanConfig.endModellWipe)
			ergebnis.addAll(getMinAvgMaxDouble(finalWipeList3, anzahlRobots, "Endmodelwischer"));*/
		ergebnis.add("Modell in Anzahl komplett");
		if (booleanConfig.endmodellMaster)
			ergebnis.addAll(getMinAvgMaxDouble(finalMasterList4, anzahlRobots, "Endmodelmaster"));
		if (booleanConfig.endmodellExplore)
			ergebnis.addAll(getMinAvgMaxDouble(finalExploreList4, anzahlRobots, "Endmodelerkunder"));
		/*if (booleanConfig.endmodellHoove)
			ergebnis.addAll(getMinAvgMaxDouble(finalHooveList4, anzahlRobots, "Endmodelsauger"));
		if (booleanConfig.endModellWipe)
			ergebnis.addAll(getMinAvgMaxDouble(finalWipeList4, anzahlRobots, "Endmodelwischer"));*/
		
		for (String s: ergebnis) {
			fw.addLineToFile(s.replace('.', ','));
		}			
	}
}
