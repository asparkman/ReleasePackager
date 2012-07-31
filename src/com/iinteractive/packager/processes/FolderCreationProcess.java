package com.iinteractive.packager.processes;

import java.io.File;

import com.iinteractive.packager.exceptions.FolderCreationFailure;

/**
 * This class is meant to facilitate the creation of a file's parent folders. 
 * This class has no access modifier as it is meant to serve solely as a 
 * helper class for the MergeProcess, and PackageProcess classes.
 * 
 * @author Alex Sparkman
 *
 */
class FolderCreationProcess {
	/**
	 * This function creates the folder.
	 * @param dir The folder to be created.
	 * @return Whether the file was able to be created.
	 * @throws Exception If the folder could not be created.
	 */
	public static void CreateFolder(File dir) throws FolderCreationFailure {
		File parent = new File(dir.getParent());
		if(!parent.exists()) {
			boolean mkdirs = parent.mkdirs();
			if(!mkdirs) {
				throw new FolderCreationFailure(parent);
			}
		}
	}
}
