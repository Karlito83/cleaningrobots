package de.tud.swt.cleaningrobots.measure;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import de.tud.evaluation.ExchangeMeasurement;
import de.tud.evaluation.WorkingConfiguration;

/**
 * Creating of the exported JSON files from the measurement information of a test case.
 * 
 * @author Christopher Werner
 *
 */
public class ExportFiles {

	public boolean addLineToFile(String s, String path)
	{
		try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File(path) , true));
    		bw.write(s);
    		bw.newLine();
    		bw.close();
    		return true;
        }
		catch (IOException e) 
		{
            return false;
        }
	}
	
	public boolean addConfigurationToFile(WorkingConfiguration wc, String path) {
		try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File(path) , true));
            int tester = 0;
			for (ExchangeMeasurement em : wc.exchange) {
				tester++;
				em.setNumber(tester);
	    		bw.write(em.toJson());
	    		bw.newLine();
			}
    		bw.close();
    		return true;
        }
		catch (IOException e) 
		{
            return false;
        }
	}
	
	public boolean addMatrixToFile(int[][] matrix, String path) {
		try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File(path) , true));
            String line = "";
            for (int i = 0; i < matrix[0].length; i++)
            {
            	line = "";
            	for (int j = 0; j < matrix.length; j++)
            	{
            		line = line + matrix[j][i] + ",";
            	}
            	bw.write(line);
	    		bw.newLine();
            }
    		bw.close();
    		return true;
        }
		catch (IOException e) 
		{
            return false;
        }
	}
}
