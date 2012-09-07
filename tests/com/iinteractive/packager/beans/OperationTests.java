package com.iinteractive.packager.beans;

import java.util.ArrayList;

import junit.framework.Assert;
import junit.framework.TestCase;

import com.iinteractive.commandline.beans.*;
import com.iinteractive.packager.beans.Operation;
import com.iinteractive.packager.exceptions.ProcessInitFailure;

public class OperationTests extends TestCase {

	/*
	 * Test Inputs:
	 *	{
	 *		null,
	 *		0,
	 *		1,
	 *		2,
	 *		3
	 *	} 
	 */
	public void testOperation() {
		CommandLinePropertyType type1 = Operation.OPERATION_FILE_TYPE;
		CommandLinePropertyType type2 = Operation.OPERATION_TYPE;
		CommandLinePropertyType type3 = new CommandLinePropertyType("A","B","C");
		
		CommandLineProperty prop1 = null;
		CommandLineProperty prop2 = null;
		CommandLineProperty prop3 = null;
		
		try {
			prop1 = new CommandLineProperty(type1, "-DOperationFile=");
			prop2 = new CommandLineProperty(type2, "-DOperation=");
			prop3 = new CommandLineProperty(type3, "ABC");
		} catch (ProcessInitFailure e1) {
			fail("Initialization of CommandLineProperty object failed.");
		}
		
		ArrayList<CommandLineProperty> inputNull = null;
		ArrayList<CommandLineProperty> input0 = new ArrayList<CommandLineProperty>();
		ArrayList<CommandLineProperty> input1 = new ArrayList<CommandLineProperty>();
		ArrayList<CommandLineProperty> input2a = new ArrayList<CommandLineProperty>();
		ArrayList<CommandLineProperty> input2b = new ArrayList<CommandLineProperty>();
		ArrayList<CommandLineProperty> input3 = new ArrayList<CommandLineProperty>();
	
		input1.add(prop1);
		
		input2a.add(prop1);
		input2a.add(prop2);
		
		input2b.add(prop1);
		input2b.add(prop3);
		
		input3.add(prop1);
		input3.add(prop2);
		input3.add(prop3);
		
		@SuppressWarnings("unused")
		Operation testNull = null;
		@SuppressWarnings("unused")
		Operation test0 = null;
		@SuppressWarnings("unused")
		Operation test1 = null;
		@SuppressWarnings("unused")
		Operation test2a = null;
		@SuppressWarnings("unused")
		Operation test2b = null;
		@SuppressWarnings("unused")
		Operation test3 = null;
		
		boolean pass = true;
		try {
			testNull = new Operation(inputNull);
			pass = false;
		} catch (NullPointerException e) {
		} catch (ProcessInitFailure e) {
			pass = false;
		}
		
		try {
			test0 = new Operation(input0);
			pass = false;
		} catch (NullPointerException e) {
			pass = false;
		} catch (ProcessInitFailure e) {
		}
		
		try {
			test1 = new Operation(input1);
		} catch (NullPointerException e) {
			pass = false;
		} catch (ProcessInitFailure e) {
			pass = false;
		}
		
		try {
			test2a = new Operation(input2a);
		} catch (NullPointerException e) {
			pass = false;
		} catch (ProcessInitFailure e) {
			pass = false;
		}
		
		try {
			test2b = new Operation(input2b);
			pass = false;
		} catch (NullPointerException e) {
			pass = false;
		} catch (ProcessInitFailure e) {
		}
		
		try {
			test3 = new Operation(input3);
			pass = false;
		} catch (NullPointerException e) {
			pass = false;
		} catch (ProcessInitFailure e) {
		}
		
		Assert.assertTrue(pass);
	}

}
