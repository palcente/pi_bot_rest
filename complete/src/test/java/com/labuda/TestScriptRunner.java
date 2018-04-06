package com.labuda;

import org.junit.Test;

import java.io.*;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * Created by mateu on 05/04/2018.
 */


public class TestScriptRunner {

    @Test
    public void testScriptRunner(){
		System.out.println(execute(new String[]{"ipconfig", "/all"},1));
    }

    private String execute(String[] command, long timeout) {

    	StringBuilder sb = new StringBuilder();

		ProcessBuilder pb = new ProcessBuilder(command);
		pb.redirectErrorStream(true);

		Process process = null;
		try {
			process = pb.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try (BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));){
			String line;
			while ((line = in.readLine()) != null) {
				sb.append(line).append(System.lineSeparator());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(process!=null && process.exitValue()!=0)
			throw new RuntimeException("Script failed");

		return sb.toString();
	}
        private static String readOutput(InputStream is) {

        		BufferedReader br = null;
           		StringBuilder sb = new StringBuilder();

        		String line;
        		try {

        			br = new BufferedReader(new InputStreamReader(is));
        			while ((line = br.readLine()) != null) {
        				sb.append(line);
        			}

        		} catch (IOException e) {
        			e.printStackTrace();
        		} finally {
        			if (br != null) {
        				try {
        					br.close();
        				} catch (IOException e) {
        					e.printStackTrace();
        				}                             
        			}
        		}
        		return sb.toString();

        	}

    
}
