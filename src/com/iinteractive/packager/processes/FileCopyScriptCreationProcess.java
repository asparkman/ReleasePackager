package com.iinteractive.packager.processes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * This class is meant to facilitate the transfer of a file from a source to
 * a destination.  This class has no access modifier as it is meant to serve
 * solely as a helper class for the MergeProcess, and PackageProcess classes.
 * 
 * The way it facilitates the transfer is to create a script file that can be
 * executed separately from the execution of this program.
 * 
 * @author Alex Sparkman
 *
 */
public class FileCopyScriptCreationProcess {
	/**
	 * This function creates the command line function that will transfer the
	 * source file to the given destination.
	 * @throws FileNotFoundException 
	 */
	private static String GenerateLine(File source, File destination) {
		return "COPY \"" + source.getAbsolutePath() + "\" \"" + destination.getAbsolutePath() + "\";\n";
	}
	
	/**
	 * This function generates the script to be executed separately from the 
	 * program.
	 * @param sources The source files to be transferred to the destinations.
	 * @param destinations The destination files that the source files are to 
	 * be transferred to.
	 * @return The script text.
	 * @throws Exception If the sources size does not match up with the 
	 * destinations size then an exception will be thrown.
	 */
	public static String GenerateScript(ArrayList<File> sources, ArrayList<File> destinations) throws Exception {
		if(sources.size() != destinations.size()) {
			throw new Exception("The number of source files is different from the number of destination files.");
		}
		
		int numberOfFiles = sources.size();
		String script = "";
		for(int i = 0; i < numberOfFiles; i++) {
			script += GenerateLine(sources.get(i), destinations.get(i));
		}
		script += "pause";
		
		return script;
	}
}
