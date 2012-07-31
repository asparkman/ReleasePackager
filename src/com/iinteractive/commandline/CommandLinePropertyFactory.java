package com.iinteractive.commandline;

import java.util.ArrayList;

import com.iinteractive.commandline.beans.CommandLineProperty;
import com.iinteractive.commandline.beans.CommandLinePropertyType;
import com.iinteractive.packager.exceptions.ProcessInitFailure;

/**
 * The intention of this class is to house the functionality of identifying 
 * and binding command line arguments to defined CommandLinePropertyTypes.
 * @author Alex Sparkman
 *
 */
public class CommandLinePropertyFactory {
	private static final String MATCH_FAILURE = "No match was found given the command line argument: ";
	private static final String MULTIPLE_MATCHES = "Multiple matches were found for the given command line argument: ";
	private static ArrayList<CommandLinePropertyType> CommandLinePropertyTypes = new ArrayList<CommandLinePropertyType>();

	/**
	 * Wrapper function for the CommandLinePropertyTypes static member.
	 * @param type
	 */
	public static void AddCommandLinePropertyType(CommandLinePropertyType type) {
		CommandLinePropertyTypes.add(type);
	}
	
	/**
	 * Searches through the list of known CommandLinePropertyTypes and 
	 * determines if a match exists.  If a match exists it returns an 
	 * instantiated object.
	 * @param arg
	 * @return
	 * @throws Exception If zero or multiple matches are found.
	 */
	public static CommandLineProperty InstantiateProperty(String arg) throws ProcessInitFailure {
		ArrayList<CommandLinePropertyType> matches = new ArrayList<CommandLinePropertyType>();
		for(CommandLinePropertyType type : CommandLinePropertyTypes) {
			if(type.isOfType(arg)) {
				matches.add(type);
			}
		}
		
		if(matches.size() == 0) {
			throw new ProcessInitFailure(MATCH_FAILURE + arg);
		} else if(matches.size() > 1) {
			throw new ProcessInitFailure(MULTIPLE_MATCHES + arg);
		} else {
			return new CommandLineProperty(matches.get(0), arg);
		}
	}

}
