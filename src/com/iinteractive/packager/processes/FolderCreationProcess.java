package com.iinteractive.packager.processes;

import java.io.File;

/**
 * This class is meant to facilitate the creation of a file's parent folders. 
 * This class has no access modifier as it is meant to serve solely as a 
 * helper class for the MergeProcess, and PackageProcess classes.
 * 
 * @author Alex Sparkman
 *
 */
public class FolderCreationProcess {
	/**
	 * This function creates the file.
	 * @param file
	 * @return Whether the file was able to be created.
	 */
	public static boolean CreateFolder(File file) {
		if(!file.exists()) {
			File parent = new File(file.getParent());
			return parent.mkdirs();
		} else {
			return true;
		}
	}
}
