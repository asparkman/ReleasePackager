package com.iinteractive.packager.processes;

import com.iinteractive.packager.beans.Operation;
import com.iinteractive.packager.exceptions.ProcessInitFailure;

public class ProcessFactory {
	public static final String PROCESS_NOT_DEFINED = "Process not defined.";
	public static final String OPERATION_FILE_NOT_DEFINED = "Operation file not defined.";
	
	public enum ProcessDefinition {
		PACKAGE
	}
	
	public static IProcess RetrieveProcess(Operation operation) throws ProcessInitFailure {
		ProcessDefinition[] definitions = ProcessDefinition.values();
		if(operation.getOperation() != null) {
			for(ProcessDefinition definition : definitions) {
				if(definition.name().equalsIgnoreCase(operation.getOperation().getValue())) {
					switch(definition) {
						case PACKAGE:
							return new PackageProcess(operation.getOperationFile().getValue());
						default:
							throw new ProcessInitFailure(PROCESS_NOT_DEFINED);
					}
				}
			}
			throw new ProcessInitFailure(PROCESS_NOT_DEFINED);
		} else {
			if(operation.getOperationFile() != null) {
				return new PackageProcess(operation.getOperationFile().getValue());
			} else {
				throw new ProcessInitFailure(OPERATION_FILE_NOT_DEFINED);
			}
		}
	}
}
