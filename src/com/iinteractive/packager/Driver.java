package com.iinteractive.packager;

import java.util.ArrayList;

import com.iinteractive.commandline.CommandLinePropertyFactory;
import com.iinteractive.commandline.beans.CommandLineProperty;
import com.iinteractive.packager.beans.Operation;
import com.iinteractive.packager.processes.IProcess;
import com.iinteractive.packager.processes.ProcessFactory;

public class Driver {

	/**
	 * Wrapper for the EntryPoint function.
	 * @param args
	 */
	public static void main(String[] args) {
		EntryPoint(args);
	}
	
	/**
	 * This function interprets command line arguments.  It assumes that all
	 * strings in the argument list that are surrounded by quotes will be a 
	 * separate element in the array without the quotes.  Additionally, it 
	 * assumes that if quotes are contained within a string they will be 
	 * escaped.
	 * @param args
	 */
	public static void EntryPoint(String[] args) {
		try {
			ArrayList<CommandLineProperty> properties = GetProperties(args);
			Operation operation = new Operation(properties);
			IProcess process = ProcessFactory.RetrieveProcess(operation);
			process.process();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static ArrayList<CommandLineProperty> GetProperties(String[] args) throws Exception {
		AssignCommandLinePropertyTypes();
		
		ArrayList<CommandLineProperty> properties = new ArrayList<CommandLineProperty>();
		boolean propertiesRetrieved = true;
		for(int i = 0; i < args.length; i++) {
			try {
				properties.add(CommandLinePropertyFactory.InstantiateProperty(args[i]));
			} catch (Exception e) {
				propertiesRetrieved = false;
				e.printStackTrace();
			}
		}
		
		for(int i = 0; i < properties.size(); i++) {
			for(int j = i + 1; j < properties.size(); j++) {
				if(properties.get(i).equals(properties.get(j))) {
					throw new Exception("Duplicate command line argument found: " + properties.get(i).toString());
				}
			}
		}
		
		if(propertiesRetrieved) {
			return properties;
		} else {
			throw new Exception("Failed to interpret command line arguments.");
		}
	}
	
	/**
	 * Defines two property types:
	 * 1. Operation - The operation to perform, either merge or package.
	 * 2. OperationFile - The file that contains the information to perform 
	 * the operation.
	 */
	private static void AssignCommandLinePropertyTypes() {
		CommandLinePropertyFactory.AddCommandLinePropertyType(
				Operation.OPERATION_TYPE
			);
		CommandLinePropertyFactory.AddCommandLinePropertyType(
				Operation.OPERATION_FILE_TYPE
			);
	}
}
