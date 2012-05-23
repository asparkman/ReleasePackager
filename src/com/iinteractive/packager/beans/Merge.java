package com.iinteractive.packager.beans;

import java.io.File;

public class Merge {
	private Package destinationPackage;
	private Package sourcePackage;
	
	public Package getDestinationPackage() {
		return destinationPackage;
	}
	public void setDestinationPackage(Package destinationPackage) {
		this.destinationPackage = destinationPackage;
	}
	public Package getSourcePackage() {
		return sourcePackage;
	}
	public void setSourcePackage(Package sourcePackage) {
		this.sourcePackage = sourcePackage;
	}
	
	public static void validateDestinationPackage(Package destinationPackage) throws Exception {
		File validator = new File(destinationPackage.getPackageDirectory());
		if(!validator.exists()) {
			throw new Exception("The specified destination package does not exist.");
		} else {
			validator = new File(destinationPackage.getFilesSubDirectory());
			if(!validator.exists()) {
				throw new Exception("The specified destination package does not have a files subdirectory.");
			}
		}
	}
	
	public static void validateSourcePackage(Package sourcePackage) throws Exception {
		File validator = new File(sourcePackage.getPackageDirectory());
		if(!validator.exists()) {
			throw new Exception("The specified source package does not exist.");
		} else {
			validator = new File(sourcePackage.getFilesSubDirectory());
			if(!validator.exists()) {
				throw new Exception("The specified destination package does not have a files subdirectory.");
			}
		}
	}
}
