package com.iinteractive.packager.exceptions;

import com.iinteractive.packager.beans.FileCopy;

public class FileCopyFailure extends Exception {
	private static final long serialVersionUID = 6152397452515403813L;
	private static final String EXCEPTION_MESSAGE = "File copy operation failed for: ";
	
	public FileCopyFailure(FileCopy copy, Throwable cause) {
		super(EXCEPTION_MESSAGE + copy.toString(), cause);
	}

	public FileCopyFailure(FileCopy copy) {
		super(EXCEPTION_MESSAGE + copy.toString());
	}

}
