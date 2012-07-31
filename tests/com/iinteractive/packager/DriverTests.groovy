package com.iinteractive.packager;

import static org.junit.Assert.*;

import java.util.ArrayList;

import junit.framework.Assert;
import junit.framework.TestCase;
import junit.framework.TestSuite

import org.junit.Test;

import com.iinteractive.commandline.CommandLinePropertyFactoryTests;
import com.iinteractive.commandline.beans.CommandLineProperty;
import com.iinteractive.packager.Driver;
import com.iinteractive.packager.beans.Operation;
import com.iinteractive.packager.exceptions.ProcessInitFailure

public class DriverTests extends TestCase {
	public String[] input;
	public ArrayList<CommandLineProperty> expectedOutput;
	public boolean exceptionExpected;

	
	
	public DriverTests(String name, input, expectedOutput, boolean exceptionExpected) {
		super(name);
		this.input = (String[]) input;
		this.expectedOutput = (ArrayList<CommandLineProperty>) expectedOutput;
		this.exceptionExpected = exceptionExpected;
	}
	
	public void setUp() {
		
	}
	
	public void tearDown() {
		
	}

	public void runTest() {
		testGetProperties();
	}
	
	public void testGetProperties() {
		CommandLinePropertyFactoryTests.clearFactoryTypes();
		ArrayList<CommandLineProperty> actualOutput = null;
		try {
			actualOutput = Driver.GetProperties(input);
			assert !exceptionExpected
		} catch(ProcessInitFailure ex) {
			ex.printStackTrace()
			assert exceptionExpected;
		}
		if(!exceptionExpected)
			compare(expectedOutput, actualOutput)
	}

	private void compare(ArrayList<CommandLineProperty> expectedOutput, ArrayList<CommandLineProperty> actualOutput) {
		if(expectedOutput.size() !=  actualOutput.size())
			assert false
		else if(expectedOutput.size() == 0) {
			assert true
		} else {
			boolean found = false;
			for(int i = 0; i < expectedOutput.size(); i++) {
				for(int j = 0; j < actualOutput.size(); j++) {
					if(expectedOutput.get(i).equals(actualOutput.get(j))) {
						found = true;
						expectedOutput.remove(i);
						actualOutput.remove(j);
						compare(expectedOutput, actualOutput);
					}
				}
			}
			assert found
		}
	}
	
	public static junit.framework.Test suite() {
		TestSuite suite = new TestSuite()
		
		suite.addTest(new DriverTests("Normal 0", [], new ArrayList<CommandLineProperty>(), false))
		suite.addTest(new DriverTests("Normal 1", ["-DOperationFile=opFile.txt"], [new CommandLineProperty(Operation.OPERATION_FILE_TYPE, "-DOperationFile=opFile.txt")], false))
		suite.addTest(new DriverTests("Normal 2", ["-DOperation=Package"], [new CommandLineProperty(Operation.OPERATION_TYPE, "-DOperation=Package")], false))
		suite.addTest(new DriverTests("Normal 3", ["-DOperationFile=opFile.txt", "-DOperation=Package"], [new CommandLineProperty(Operation.OPERATION_FILE_TYPE, "-DOperationFile=opFile.txt"), new CommandLineProperty(Operation.OPERATION_TYPE, "-DOperation=Package")], false))
		
		suite.addTest(new DriverTests("Exception 0", ["-DOperationFile=opFile.txt", "ABC=Package"], [], true))
		suite.addTest(new DriverTests("Exception 1", ["-DOperationFile=opFile.txt", "-DOperation=Package", "-DOperation=Package"], [], true))
		suite.addTest(new DriverTests("Exception 2", ["-DOperationFile=opFile.txt", "-DOperation=Package", "ABC=Package"], [], true))
		
		return suite
	}
}
