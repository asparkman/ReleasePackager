package com.iinteractive.packager.beans;

import java.io.File;

public class Package {
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
	
	public static void validatePackageDirectory(String packageDirectory) throws Exception {
		File validator = new File(packageDirectory);
		if(!validator.exists()) {
			throw new Exception("The package directory provided does not exist.");
		}
	}
}
