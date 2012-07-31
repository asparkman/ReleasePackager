package com.iinteractive.packager.exceptions;

public class ProcessInitFailure extends Exception {
	private static final long serialVersionUID = -1892534784983343630L;

	public ProcessInitFailure(String message, Throwable cause) {
		super(message, cause);
	}

	public ProcessInitFailure(String message) {
		super(message);
	}
	
	
}
