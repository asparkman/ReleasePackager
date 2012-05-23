package com.iinteractive.packager.beans;

import java.util.ArrayList;

import com.iinteractive.commandline.beans.CommandLineProperty;
import com.iinteractive.commandline.beans.CommandLinePropertyType;

public class Operation {
	public Operation(ArrayList<CommandLineProperty> properties) throws Exception {
		if(properties.size() != 2) {
			throw new Exception("Received an unexpected number of properties.");
		}
		//TODO: Validate that both CommandLineProperties are to be picked up.
		for(int i = 0; i < properties.size(); i++) {
			if(properties.get(i).getType().equals(OPERATION_TYPE)) {
				operation = properties.get(i);
			} else if(properties.get(i).getType().equals(OPERATION_FILE_TYPE)) {
				operationFile = properties.get(i);
			} else {
				throw new Exception("Unknown command line property");
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
