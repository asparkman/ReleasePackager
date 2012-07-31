package com.iinteractive.packager.processes;

import com.iinteractive.packager.exceptions.ProcessFailure;

public interface IProcess {
	public void process() throws ProcessFailure;
}
