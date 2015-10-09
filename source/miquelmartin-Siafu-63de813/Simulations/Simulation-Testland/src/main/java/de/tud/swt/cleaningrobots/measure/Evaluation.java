package de.tud.swt.cleaningrobots.measure;

import java.util.ArrayList;
import java.util.List;

public class Evaluation {

	public static void main(String[] args) {
		System.out.println("Start");
		
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
		
		System.out.println("Ready");
		
		/*for (int i = 1; i < 5; i++) {
			FileWorker f = new FileWorker("Robbi_" + i + ".txt");
			RobotMeasurement rm = new RobotMeasurement("");
			List<String> robi = f.readFile();
			rm = rm.fromJson(robi.get(0));
		}*/	
		
	}

}
