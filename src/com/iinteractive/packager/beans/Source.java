package com.iinteractive.packager.beans;

import java.io.File;

import com.iinteractive.packager.exceptions.ValidationFailure;

public class Source {
	public static final String SOURCE_DIR_DNE = "The source directory provided does not exist. ";
	public Source(String sourceDirectory) {
		super();
		this.sourceDirectory = sourceDirectory;
	}
	private String sourceDirectory;
	
	public String getSourceDirectory() {
		return sourceDirectory;
	}
	public void setSourceDirectory(String sourceDirectory) {
		this.sourceDirectory = sourceDirectory;
	}
	public static void validateSourceDirectory(String sourceDirectory) throws ValidationFailure {
		File validator = new File(sourceDirectory);
		if(!validator.exists()) {
			throw new ValidationFailure(SOURCE_DIR_DNE + validator.getAbsolutePath());
		}
	}
}
