package com.iinteractive.packager.beans;

import java.io.File;

import com.iinteractive.packager.exceptions.ValidationFailure;

public class Package {
	public static final String PACKAGE_DIR_DNE = "The package directory provided does not exist. ";
	
	public Package(String packageDirectory) {
		super();
		setPackageDirectory(packageDirectory);
	}

	private String packageDirectory;
	private String filesSubDirectory;
	
	public String getPackageDirectory() {
		return packageDirectory;
	}
	public void setPackageDirectory(String packageDirectory) {
		this.packageDirectory = packageDirectory;
		setFilesSubDirectory(packageDirectory + "\\Release-Files");
	}
	
	public String getFilesSubDirectory() {
		return filesSubDirectory;
	}
	private void setFilesSubDirectory(String filesSubDirectory) {
		this.filesSubDirectory = filesSubDirectory;
	}
	
	public static void validatePackageDirectory(String packageDirectory) throws ValidationFailure {
		File validator = new File(packageDirectory);
		if(!validator.exists()) {
			throw new ValidationFailure(PACKAGE_DIR_DNE + validator.getAbsolutePath());
		}
	}
}
