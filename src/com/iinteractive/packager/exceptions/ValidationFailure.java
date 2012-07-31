package com.iinteractive.packager.exceptions;

public class ValidationFailure extends Exception {
	private static final long serialVersionUID = -8920195551132230084L;

	public ValidationFailure() {
		super();
	}

	public ValidationFailure(String message, Throwable cause) {
		super(message, cause);
	}

	public ValidationFailure(String message) {
		super(message);
	}

	public ValidationFailure(Throwable cause) {
		super(cause);
	}

}
