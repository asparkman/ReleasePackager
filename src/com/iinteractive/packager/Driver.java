package com.iinteractive.packager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.apache.log4j.Logger;

import com.iinteractive.commandline.CommandLinePropertyFactory;
import com.iinteractive.commandline.beans.CommandLineProperty;
import com.iinteractive.packager.beans.Operation;
import com.iinteractive.packager.exceptions.ProcessFailure;
import com.iinteractive.packager.exceptions.ProcessInitFailure;
import com.iinteractive.packager.processes.IProcess;
import com.iinteractive.packager.processes.ProcessFactory;

public class Driver {
	public static Logger logger = Logger.getLogger(Driver.class.getName());
	
	public static final String DUPLICATE_CMD_LINE_ARG = "Duplicate command line argument found: ";
	public static final String CMD_LINE_INTERPRETATION_ERROR = "Failed to interpret command line arguments.";

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
		} catch (ProcessFailure e) {
			Collection<ProcessFailure> failures = e.generateExceptions();
			Iterator<ProcessFailure> iterator = failures.iterator();
			while(iterator.hasNext()) {
				ProcessFailure failure = iterator.next();
				logger.error(failure);
			}
		} catch(ProcessInitFailure e) {
			logger.error(e);
		}
		
		
	}
	
	public static ArrayList<CommandLineProperty> GetProperties(String[] args) throws ProcessInitFailure {
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
					throw new ProcessInitFailure(DUPLICATE_CMD_LINE_ARG + properties.get(i).toString());
				}
			}
		}
		
		if(propertiesRetrieved) {
			return properties;
		} else {
			throw new ProcessInitFailure(CMD_LINE_INTERPRETATION_ERROR);
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
