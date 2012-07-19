package com.iinteractive.packager.processes;

import com.iinteractive.packager.beans.Operation;

public class ProcessFactory {
	public enum ProcessDefinition {
		PACKAGE
	}
	
	public static IProcess RetrieveProcess(Operation operation) throws Exception {
		ProcessDefinition[] definitions = ProcessDefinition.values();
		for(ProcessDefinition definition : definitions) {
			if(definition.name().equalsIgnoreCase(operation.getOperation().getValue())) {
				switch(definition) {
					case PACKAGE:
						return new PackageProcess(operation.getOperationFile().getValue());
					default:
						throw new Exception("Process not defined.");
				}
			}
		}
		throw new Exception("Process not defined.");
	}
}
