package de.tud.swt.cleaningrobots.measure;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import de.tud.evaluation.ExchangeMeasurement;

public class Evaluation {

	public static void main(String[] args) {
		System.out.println("Start");
		
		Evaluation e = new Evaluation();
		e.versionZero();
		
		System.out.println("Ready");
		
	}
	
	public void oldEvaluation () {
		FileWorker fex = new FileWorker("exchange.txt");
		
		List<String> input = fex.readFile();
		
		List<ExchangeMeasurement> measures = new ArrayList<ExchangeMeasurement>();
		
		for (String line : input) {
			ExchangeMeasurement em = new ExchangeMeasurement("", "", 0);
			em = em.fromJson(line);
			measures.add(em);
		}
		
		ExchangeMeasurement result = new ExchangeMeasurement("Result", "", measures.size());
		ExchangeMeasurement resultTwo = new ExchangeMeasurement("NeedNew", "", measures.size());
		
		int counter = 0;
		
		for (ExchangeMeasurement em : measures) {
			if (em.getName2().equals("needNewDestPath")) {
				result.addMeasurement(em);
				counter++;
			} else				
				resultTwo.addMeasurement(em);
		}
		
		result.setNumber(counter);
		
		FileWorker fw = new FileWorker("measure.txt");
		String test = result.toJson();
		fw.addLineToFile(test);
		String test2 = resultTwo.toJson();
		fw.addLineToFile(test2);
		
		/*for (int i = 1; i < 5; i++) {
			FileWorker f = new FileWorker("Robbi_" + i + ".txt");
			RobotMeasurement rm = new RobotMeasurement("");
			List<String> robi = f.readFile();
			rm = rm.fromJson(robi.get(0));
		}*/	
	}
	
	public int getCompleteByteNumber (List<ExchangeMeasurement> measure, String name) {
		for (ExchangeMeasurement em : measure) {
			if (em.getName2().equals(name))
				return em.getCompleteByteNumber();
		}
		return 0;
	}
	
	public List<ExchangeMeasurement> mergeExchangeMeasurement (List<ExchangeMeasurement> measure) {
		List<ExchangeMeasurement> result = new ArrayList<ExchangeMeasurement>();
		ExchangeMeasurement complete = new ExchangeMeasurement("Master", "Complete", 0);
		ExchangeMeasurement explore = new ExchangeMeasurement("Master", "ExploreInformationNewDest", 0);
		ExchangeMeasurement wipe = new ExchangeMeasurement("Master", "WipeInformationNewDest", 0);
		ExchangeMeasurement hoove = new ExchangeMeasurement("Master", "HooveInformationNewDest", 0);
		ExchangeMeasurement sendNew = new ExchangeMeasurement("Master", "SendNewDest", 0);
		ExchangeMeasurement sendNull = new ExchangeMeasurement("Master", "SendNULLDest", 0);
		ExchangeMeasurement other = new ExchangeMeasurement("Master", "Other", 0);
		boolean isExplore = false, isHoove = false, isWipe = false, isNew = false, isNull = false;
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
			} else if (em.getName2().equals("FinalModel")) {
				result.add(em);
			} else {
				complete.addMeasurement(em);
				complete.setNumber(complete.getNumber() + 1);
				other.addMeasurement(em);
				other.setNumber(other.getNumber() + 1);
			}
		}
		result.add(other);
		result.add(complete);
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
	
	public void versionZero () {
		List<MinMaxAveLong> benchmarkTimeList = new LinkedList<MinMaxAveLong>();
		List<MinMaxAveInt> completeTicksList = new LinkedList<MinMaxAveInt>();
		List<MinMaxAveDouble> completeTimeList = new LinkedList<MinMaxAveDouble>();
		List<MinMaxAveDouble> completeEnergieList = new LinkedList<MinMaxAveDouble>();
		
		List<MinMaxAveInt> completeExchangeList = new LinkedList<MinMaxAveInt>();
		List<MinMaxAveInt> finalList = new LinkedList<MinMaxAveInt>();
		List<MinMaxAveInt> exploreList = new LinkedList<MinMaxAveInt>();
		List<MinMaxAveInt> otherList = new LinkedList<MinMaxAveInt>();
		for (int anzRobots = 1; anzRobots <= 10; anzRobots++) {
			List<ExchangeMeasurement> completeExchange = new ArrayList<ExchangeMeasurement>();
			//roboter information
			MinMaxAveLong mmaBench = new MinMaxAveLong(anzRobots);
			MinMaxAveInt mmaTicks = new MinMaxAveInt(anzRobots);
			MinMaxAveDouble mmaTime = new MinMaxAveDouble(anzRobots);
			MinMaxAveDouble mmaEnergie = new MinMaxAveDouble(anzRobots);
			//data exchange information
			MinMaxAveInt mmaExchange = new MinMaxAveInt(anzRobots);			
			MinMaxAveInt mmaFinal = new MinMaxAveInt(anzRobots);
			MinMaxAveInt mmaExplore = new MinMaxAveInt(anzRobots);
			MinMaxAveInt mmaOther = new MinMaxAveInt(anzRobots);
			for (int runs = 1; runs < 6; runs++) {
				//Exchange measurement
				FileWorker fex = new FileWorker("V0\\R_V0_CE" + anzRobots + "_CH0_CW0_B0_D" + runs + "_exchange.txt");
				
				List<String> input = fex.readFile();	
				List<ExchangeMeasurement> measures = new ArrayList<ExchangeMeasurement>();				
				for (String line : input) {
					ExchangeMeasurement em = new ExchangeMeasurement("", "", 0);
					em = em.fromJson(line);
					measures.add(em);
				}
				measures = mergeExchangeMeasurement(measures);
				completeExchange.addAll(measures);
				
				//robbi one information
				FileWorker rob1 = new FileWorker("V0\\R_V0_CE" + anzRobots + "_CH0_CW0_B0_D" + runs + "_Robbi_1.txt");				
				String firstLine = rob1.readFile().get(0);//.readNextLine();
				RobotMeasurement robM = new RobotMeasurement("");
				robM = robM.fromJson(firstLine);
				
				long benchmarkTime = robM.benchmarkTime;
				int completeTicks = robM.completeTicks;
				
				double completeTime = robM.completeTime;				
				double completeEnergie = robM.completeEnergie;
				
				for (int i = 2; i <= anzRobots + 1; i++) {
					FileWorker rob = new FileWorker("V0//R_V0_CE" + anzRobots + "_CH0_CW0_B0_D" + runs + "_Robbi_" + i + ".txt");
					RobotMeasurement rM = new RobotMeasurement("");
					rM = rM.fromJson(rob.readNextLine());
					completeTime += rM.completeTime;
					completeEnergie += rM.completeEnergie;
				}
				
				mmaBench.values.add(benchmarkTime);
				mmaTicks.values.add(completeTicks);
				mmaTime.values.add(completeTime);
				mmaEnergie.values.add(completeEnergie);		
				
				mmaExchange.values.add(getCompleteByteNumber(measures, "Complete"));
				mmaFinal.values.add(getCompleteByteNumber(measures, "FinalModel"));
				mmaExplore.values.add(getCompleteByteNumber(measures, "ExploreInformationNewDest"));
				mmaOther.values.add(getCompleteByteNumber(measures, "Other"));
			}
			
			benchmarkTimeList.add(mmaBench);
			completeEnergieList.add(mmaEnergie);
			completeTicksList.add(mmaTicks);
			completeTimeList.add(mmaTime);

			completeExchangeList.add(mmaExchange);
			finalList.add(mmaFinal);
			otherList.add(mmaOther);
			exploreList.add(mmaExplore);
		}
		
		FileWorker fw = new FileWorker("V0.csv");
		String numberS = ";"; 
		String benchSMax = "Benchmarkzeit Max;", energieSMax = "Energieverbrauch Max;", ticksSMax = "Iterationen Max;", timeSMax = "Rechenzeit Max;", 
				exchangeSMax = "Komplett Austausch Max;", finalSMax = "Endmodell Max;", otherSMax = "Andereraustausch Max;", exploreSMax = "Zielaustausch Max;"; 
		String benchSAve = "Benchmarkzeit Avg;", energieSAve = "Energieverbrauch Avg;", ticksSAve = "Iterationen Avg;", timeSAve = "Rechenzeit Avg;", 
				exchangeSAve = "Komplett Austausch Avg;", finalSAve = "Endmodell Avg;", otherSAve = "Andereraustausch Avg;", exploreSAve = "Zielaustausch Avg;"; 
		String benchSMin = "Benchmarkzeit Min;", energieSMin = "Energieverbrauch Min;", ticksSMin = "Iterationen Min;", timeSMin = "Rechenzeit Min;", 
				exchangeSMin = "Komplett Austausch Min;", finalSMin = "Endmodell Min;", otherSMin = "Andereraustausch Min;", exploreSMin = "Zielaustausch Min;"; 
		//String benchSMin = "", energieSMin = "", ticksSMin = "", timeSMin = "", exchangeSMin = "", finalSMin = "", otherSMin = "", exploreSMin = "";
		//String benchSAve = "", energieSAve = "", ticksSAve = "", timeSAve = "", exchangeSAve = "", finalSAve = "", otherSAve = "", exploreSAve = "";
		for (int anzRobots = 1; anzRobots <= 10; anzRobots++) {
			numberS = numberS + anzRobots + ";";
			for (MinMaxAveLong i : benchmarkTimeList) {
				if (i.numberRobots == anzRobots) {
					benchSMax = benchSMax + i.getMaxValue() + ";";
					benchSMin = benchSMin + i.getMinValue() + ";";
					benchSAve = benchSAve + i.getAveValue() + ";";
				}
			}
			
			for (MinMaxAveDouble i : completeTimeList) {
				if (i.numberRobots == anzRobots) {
					timeSMax = timeSMax + i.getMaxValue() + ";";
					timeSMin = timeSMin + i.getMinValue() + ";";
					timeSAve = timeSAve + i.getAveValue() + ";";
				}
			}
			
			for (MinMaxAveDouble i : completeEnergieList) {
				if (i.numberRobots == anzRobots) {
					energieSMax = energieSMax + i.getMaxValue() + ";";
					energieSMin = energieSMin + i.getMinValue() + ";";
					energieSAve = energieSAve + i.getAveValue() + ";";
				}
			}
			
			for (MinMaxAveInt i : completeTicksList) {
				if (i.numberRobots == anzRobots) {
					ticksSMax = ticksSMax + i.getMaxValue() + ";";
					ticksSMin = ticksSMin + i.getMinValue() + ";";
					ticksSAve = ticksSAve + i.getAveValue() + ";";
				}
			}
			
			for (MinMaxAveInt i : completeExchangeList) {
				if (i.numberRobots == anzRobots) {
					exchangeSMax = exchangeSMax + i.getMaxValue() + ";";
					exchangeSMin = exchangeSMin + i.getMinValue() + ";";
					exchangeSAve = exchangeSAve + i.getAveValue() + ";";
				}
			}
			
			for (MinMaxAveInt i : exploreList) {
				if (i.numberRobots == anzRobots) {
					exploreSMax = exploreSMax + i.getMaxValue() + ";";
					exploreSMin = exploreSMin + i.getMinValue() + ";";
					exploreSAve = exploreSAve + i.getAveValue() + ";";
				}
			}
			
			for (MinMaxAveInt i : otherList) {
				if (i.numberRobots == anzRobots) {
					otherSMax = otherSMax + i.getMaxValue() + ";";
					otherSMin = otherSMin + i.getMinValue() + ";";
					otherSAve = otherSAve + i.getAveValue() + ";";
				}
			}
			
			for (MinMaxAveInt i : finalList) {
				if (i.numberRobots == anzRobots) {
					finalSMax = finalSMax + i.getMaxValue() + ";";
					finalSMin = finalSMin + i.getMinValue() + ";";
					finalSAve = finalSAve + i.getAveValue() + ";";
				}
			}
		}
		
		fw.addLineToFile(numberS.replace('.', ','));
		fw.addLineToFile(benchSMax.replace('.', ','));
		fw.addLineToFile(benchSAve.replace('.', ','));
		fw.addLineToFile(benchSMin.replace('.', ','));
		fw.addLineToFile(ticksSMax.replace('.', ','));
		fw.addLineToFile(ticksSAve.replace('.', ','));
		fw.addLineToFile(ticksSMin.replace('.', ','));
		fw.addLineToFile(timeSMax.replace('.', ','));
		fw.addLineToFile(timeSAve.replace('.', ','));
		fw.addLineToFile(timeSMin.replace('.', ','));
		fw.addLineToFile(energieSMax.replace('.', ','));
		fw.addLineToFile(energieSAve.replace('.', ','));
		fw.addLineToFile(energieSMin.replace('.', ','));
		fw.addLineToFile(exchangeSMax.replace('.', ','));
		fw.addLineToFile(exchangeSAve.replace('.', ','));
		fw.addLineToFile(exchangeSMin.replace('.', ','));
		fw.addLineToFile(exploreSMax.replace('.', ','));
		fw.addLineToFile(exploreSAve.replace('.', ','));
		fw.addLineToFile(exploreSMin.replace('.', ','));
		fw.addLineToFile(otherSMax.replace('.', ','));
		fw.addLineToFile(otherSAve.replace('.', ','));
		fw.addLineToFile(otherSMin.replace('.', ','));
		fw.addLineToFile(finalSMax.replace('.', ','));
		fw.addLineToFile(finalSAve.replace('.', ','));
		fw.addLineToFile(finalSMin.replace('.', ','));
		
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

}
