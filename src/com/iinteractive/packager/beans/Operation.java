package com.iinteractive.packager.beans;

import java.util.ArrayList;

import com.iinteractive.commandline.beans.CommandLineProperty;
import com.iinteractive.commandline.beans.CommandLinePropertyType;
import com.iinteractive.packager.exceptions.ProcessInitFailure;

public class Operation {
	public static String UNKNOWN_PROPERTY = "Unknown command line property";
	public static String UNEXPECTED_NUM_PROPERTIES = "Received an unexpected number of properties.";
	
	public Operation(ArrayList<CommandLineProperty> properties) throws ProcessInitFailure {
		if(properties.size() != 1 && properties.size() != 2) {
			throw new ProcessInitFailure(UNEXPECTED_NUM_PROPERTIES);
		}
		//TODO: Validate that both CommandLineProperties are to be picked up.
		for(int i = 0; i < properties.size(); i++) {
			if(properties.get(i).getType().equals(OPERATION_TYPE)) {
				operation = properties.get(i);
			} else if(properties.get(i).getType().equals(OPERATION_FILE_TYPE)) {
				operationFile = properties.get(i);
			} else {
				throw new ProcessInitFailure(UNKNOWN_PROPERTY);
			}
		}
	}
	
	public static CommandLinePropertyType OPERATION_TYPE = new CommandLinePropertyType("-D", "Operation", "=");
	public static CommandLinePropertyType OPERATION_FILE_TYPE = new CommandLinePropertyType("-D", "OperationFile", "=");
	private CommandLineProperty operation;
	private CommandLineProperty operationFile;
	
	public CommandLineProperty getOperation() {
		return operation;
	}
	public void setOperation(CommandLineProperty operation) {
		this.operation = operation;
	}
	public CommandLineProperty getOperationFile() {
		return operationFile;
	}
	public void setOperationFile(CommandLineProperty operationFile) {
		this.operationFile = operationFile;
	}
	
	
}
