package com.iinteractive.packager;

import static org.junit.Assert.*;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Test;

import com.iinteractive.commandline.CommandLinePropertyFactoryTests;
import com.iinteractive.commandline.beans.CommandLineProperty;
import com.iinteractive.packager.Driver;
import com.iinteractive.packager.beans.Operation;

public class DriverTests {

	@Test
	public void testEntryPoint() {
		fail("Need to test this via command line.");
	}

	@Test
	public void testGetProperties() throws Exception {
		boolean valid = true;
		
		CommandLinePropertyFactoryTests.clearFactoryTypes();
		
		ArrayList<CommandLineProperty> expOutput0 = new ArrayList<CommandLineProperty>();
		String[] input0 = {};
		valid = valid && compare(expOutput0, Driver.GetProperties(input0));
		
		CommandLinePropertyFactoryTests.clearFactoryTypes();
		
		ArrayList<CommandLineProperty> expOutput1a = new ArrayList<CommandLineProperty>();
		String[] input1a = {"-DOperationFile=C:\\Users\\Alex Sparkman\\Documents\\Job\\Workspace\\ReleasePackager\\TestFolders\\DriverTestFolders\\GetPropertiesTestFolders\\OpFiles\\OpFile1"};
		expOutput1a.add(new CommandLineProperty(Operation.OPERATION_FILE_TYPE, input1a[0]));		
		valid = valid && compare(expOutput1a, Driver.GetProperties(input1a));
		
		CommandLinePropertyFactoryTests.clearFactoryTypes();
		
		ArrayList<CommandLineProperty> expOutput1b = new ArrayList<CommandLineProperty>();
		String[] input1b = {"-DOperation=Merge"};
		expOutput1b.add(new CommandLineProperty(Operation.OPERATION_TYPE, input1b[0]));		
		valid = valid && compare(expOutput1b, Driver.GetProperties(input1b));

		CommandLinePropertyFactoryTests.clearFactoryTypes();
		
		ArrayList<CommandLineProperty> expOutput2a = new ArrayList<CommandLineProperty>();
		String[] input2a = {"-DOperationFile=C:\\Users\\Alex Sparkman\\Documents\\Job\\Workspace\\ReleasePackager\\TestFolders\\DriverTestFolders\\GetPropertiesTestFolders\\OpFiles\\OpFile1", "-DOperation=Merge"};
		expOutput2a.add(new CommandLineProperty(Operation.OPERATION_FILE_TYPE, input2a[0]));		
		expOutput2a.add(new CommandLineProperty(Operation.OPERATION_TYPE, input2a[1]));		
		valid = valid && compare(expOutput2a, Driver.GetProperties(input2a));
		
		CommandLinePropertyFactoryTests.clearFactoryTypes();
		
		ArrayList<CommandLineProperty> expOutput2b = new ArrayList<CommandLineProperty>();
		String[] input2b = {"-DOperationFile=C:\\Users\\Alex Sparkman\\Documents\\Job\\Workspace\\ReleasePackager\\TestFolders\\DriverTestFolders\\GetPropertiesTestFolders\\OpFiles\\OpFile1", "-DOperation=Package"};
		expOutput2b.add(new CommandLineProperty(Operation.OPERATION_FILE_TYPE, input2b[0]));		
		expOutput2b.add(new CommandLineProperty(Operation.OPERATION_TYPE, input2b[1]));	
		valid = valid && compare(expOutput2b, Driver.GetProperties(input2b));		
		
		CommandLinePropertyFactoryTests.clearFactoryTypes();
		
		// Exception expected.
		String[] input2c = {"-DOperationFile=C:\\Users\\Alex Sparkman\\Documents\\Job\\Workspace\\ReleasePackager\\TestFolders\\DriverTestFolders\\GetPropertiesTestFolders\\OpFiles\\OpFile1", "ABC=Merge"};
		try {
			Driver.GetProperties(input2c);
			valid = false;
		} catch(Exception e) {
		}
		
		CommandLinePropertyFactoryTests.clearFactoryTypes();
		
		// Exception expected
		String[] input3a = {"-DOperationFile=C:\\Users\\Alex Sparkman\\Documents\\Job\\Workspace\\ReleasePackager\\TestFolders\\DriverTestFolders\\GetPropertiesTestFolders\\OpFiles\\OpFile1", "-DOperation=Merge", "-DOperation=Merge"};
		try {
			Driver.GetProperties(input3a);
			valid = false;
		} catch(Exception e) {
		}

		CommandLinePropertyFactoryTests.clearFactoryTypes();
		
		// Exception expected
		String[] input3b = {"-DOperationFile=C:\\Users\\Alex Sparkman\\Documents\\Job\\Workspace\\ReleasePackager\\TestFolders\\DriverTestFolders\\GetPropertiesTestFolders\\OpFiles\\OpFile1", "-DOperation=Merge", "ABC=Merge"};
		try {
			Driver.GetProperties(input3b);
			valid = false;
		} catch(Exception e) {
		}
		
		Assert.assertTrue(valid);
	}

	private boolean compare(ArrayList<CommandLineProperty> expectedOutput, ArrayList<CommandLineProperty> actualOutput) {
		if(expectedOutput.size() !=  actualOutput.size())
			return false;
		else if(expectedOutput.size() == 0) {
			return true;
		} else {
			boolean found = false;
			for(int i = 0; i < expectedOutput.size(); i++) {
				for(int j = 0; j < actualOutput.size(); j++) {
					if(expectedOutput.get(i).equals(actualOutput.get(j))) {
						found = true;
						expectedOutput.remove(i);
						actualOutput.remove(j);
						return compare(expectedOutput, actualOutput);
					}
				}
			}
			return found;
		}
	}
}
