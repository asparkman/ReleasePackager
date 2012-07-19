package com.iinteractive.packager.processes;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Test;

import com.iinteractive.commandline.beans.CommandLineProperty;
import com.iinteractive.packager.beans.Operation;
import com.iinteractive.packager.beans.Source;
import com.iinteractive.packager.beans.Package;
import com.iinteractive.packager.processes.IProcess;
import com.iinteractive.packager.processes.ProcessFactory;

public class ProcessFactoryTests {

	@Test
	public void testRetrieveProcess() throws Exception {
		ArrayList<CommandLineProperty> input2props = new ArrayList<CommandLineProperty>();
		ArrayList<CommandLineProperty> input3props = new ArrayList<CommandLineProperty>();

		input2props.add(new CommandLineProperty(Operation.OPERATION_TYPE, "-DOperation=package"));
		input2props.add(new CommandLineProperty(Operation.OPERATION_FILE_TYPE, "-DOperationFile=testvalue"));
		
		input3props.add(new CommandLineProperty(Operation.OPERATION_TYPE, "-DOperation=poop"));
		input3props.add(new CommandLineProperty(Operation.OPERATION_FILE_TYPE, "-DOperationFile=testvalue"));

		Operation input2Operation = new Operation(input2props);
		Operation input3Operation = new Operation(input3props);
		
		try {
			IProcess process = ProcessFactory.RetrieveProcess(input2Operation);
			process.getClass().getName().equals("com.iinteractive.packager.processes.PackageProcess");
		} catch (Exception e) {
			Assert.assertTrue(false);
		}
		
		try {
			IProcess process = ProcessFactory.RetrieveProcess(input3Operation);
			Assert.assertTrue(false);
		} catch (Exception e) {
			Assert.assertTrue(true);
		}
		
	}

}
