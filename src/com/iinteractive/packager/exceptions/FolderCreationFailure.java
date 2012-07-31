package com.iinteractive.packager.exceptions;

import java.io.File;

public class FolderCreationFailure extends Exception {
	private static final long serialVersionUID = -4916625325014411218L;
	private static final String ERROR_MESSAGE = "Failed to create folder with absolute path: ";
	
	public FolderCreationFailure() {
		super();
	}
	public FolderCreationFailure(File dir, Throwable cause) {
		super(ERROR_MESSAGE + dir.getAbsolutePath(), cause);
	}
	public FolderCreationFailure(File dir) {
		super(ERROR_MESSAGE + dir.getAbsolutePath());
	}
	public FolderCreationFailure(Throwable cause) {
		super(cause);
	}
}
