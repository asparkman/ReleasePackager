package com.iinteractive.packager.beans;

import java.io.File;

public class Source {
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
	public static void validateSourceDirectory(String sourceDirectory) throws Exception {
		File validator = new File(sourceDirectory);
		if(!validator.exists()) {
			throw new Exception("The source directory provided does not exist.");
		}
	}
}
