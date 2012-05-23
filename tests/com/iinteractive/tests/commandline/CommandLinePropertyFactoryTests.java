package com.iinteractive.tests.commandline;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Test;

import com.iinteractive.commandline.CommandLinePropertyFactory;
import com.iinteractive.commandline.beans.CommandLineProperty;
import com.iinteractive.commandline.beans.CommandLinePropertyType;

public class CommandLinePropertyFactoryTests {

	@Test
	public void testAddCommandLinePropertyType() {
		
	}

	/*
	 * matches {
	 * 		0,
	 * 		1,
	 * 		2
	 * }
	 */
	@Test
	public void testInstantiateProperty() {
		boolean pass = true;
		
		CommandLinePropertyType type1 = new CommandLinePropertyType("A", "B", "C");
		CommandLinePropertyType type2 = new CommandLinePropertyType("A", "B", "C");
		
		String input0 = "";
		String input1and2 = "ABC";
		
		CommandLineProperty output0 = null;
		CommandLineProperty output1 = null;
		CommandLineProperty output2 = null;
		
		// Test0
		CommandLinePropertyFactory.AddCommandLinePropertyType(type1);
		try {
			CommandLinePropertyFactory.InstantiateProperty(input0);
		} catch (Exception e1) {
		}
		try {
			clearFactoryTypes();
		} catch (Throwable e) {
			pass = false;
			e.printStackTrace();
		}
		
		CommandLinePropertyFactory.AddCommandLinePropertyType(type1);
		try {
			CommandLinePropertyFactory.InstantiateProperty(input1and2);
		} catch (Exception e1) {
			pass = false;
		}
		try {
			clearFactoryTypes();
		} catch (Throwable e) {
			pass = false;
			e.printStackTrace();
		}
		
		CommandLinePropertyFactory.AddCommandLinePropertyType(type1);
		CommandLinePropertyFactory.AddCommandLinePropertyType(type2);
		try {
			CommandLinePropertyFactory.InstantiateProperty(input1and2);
			pass = false;
		} catch (Exception e1) {
		}
		try {
			clearFactoryTypes();
		} catch (Throwable e) {
			pass = false;
			e.printStackTrace();
		}
		
		Assert.assertTrue(pass);
	}
	
	public static void clearFactoryTypes() throws IllegalArgumentException, SecurityException, IllegalAccessException, NoSuchFieldException {
		Field field = CommandLinePropertyFactory.class.getDeclaredField("CommandLinePropertyTypes");
		field.setAccessible(true);
		ArrayList<CommandLinePropertyType> types = (ArrayList<CommandLinePropertyType>) field.get(null);
		types.clear();
	}

}
