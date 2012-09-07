package com.iinteractive.packager.processes;

import java.util.ArrayList;

import junit.framework.Assert;
import junit.framework.TestCase;

import com.iinteractive.commandline.beans.CommandLineProperty;
import com.iinteractive.packager.beans.Operation;
import com.iinteractive.packager.exceptions.ProcessInitFailure;
import com.iinteractive.packager.processes.IProcess;
import com.iinteractive.packager.processes.ProcessFactory;

public class ProcessFactoryTests extends TestCase {

	public void testRetrieveProcess() throws Exception {
		ArrayList<CommandLineProperty> input1props = new ArrayList<CommandLineProperty>();
		ArrayList<CommandLineProperty> input2props = new ArrayList<CommandLineProperty>();
		ArrayList<CommandLineProperty> input3props = new ArrayList<CommandLineProperty>();

		input1props.add(new CommandLineProperty(Operation.OPERATION_FILE_TYPE, "-DOperationFile=testvalue"));
		
		input2props.add(new CommandLineProperty(Operation.OPERATION_TYPE, "-DOperation=package"));
		input2props.add(new CommandLineProperty(Operation.OPERATION_FILE_TYPE, "-DOperationFile=testvalue"));
		
		input3props.add(new CommandLineProperty(Operation.OPERATION_TYPE, "-DOperation=poop"));
		input3props.add(new CommandLineProperty(Operation.OPERATION_FILE_TYPE, "-DOperationFile=testvalue"));

		Operation input1Operation = new Operation(input1props);
		Operation input2Operation = new Operation(input2props);
		Operation input3Operation = new Operation(input3props);

		
		try {
			IProcess process = ProcessFactory.RetrieveProcess(input1Operation);
			assert process.getClass().getName().equals("com.iinteractive.packager.processes.PackageProcess");
		} catch (ProcessInitFailure e) {
			Assert.assertTrue(false);
		}
		
		try {
			IProcess process = ProcessFactory.RetrieveProcess(input2Operation);
			assert process.getClass().getName().equals("com.iinteractive.packager.processes.PackageProcess");
		} catch (ProcessInitFailure e) {
			Assert.assertTrue(false);
		}
		
		try {
			ProcessFactory.RetrieveProcess(input3Operation);
			Assert.assertTrue(false);
		} catch (ProcessInitFailure e) {
			assert e.getMessage() == ProcessFactory.PROCESS_NOT_DEFINED;
		}
		
	}

}
