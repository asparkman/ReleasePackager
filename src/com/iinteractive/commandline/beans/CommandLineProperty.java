package com.iinteractive.commandline.beans;

import com.iinteractive.packager.exceptions.ProcessInitFailure;

/**
 * The purpose of this class is to house functionality associated with the 
 * reading of command line arguments.
 * @author Alex Sparkman
 *
 */
public class CommandLineProperty {
	public static final String CMD_LINE_MATCH_FAILURE = "The value does not match the correct CommandLinePropertyType.";
	
	public CommandLineProperty(CommandLinePropertyType type, String value) throws ProcessInitFailure {
		super();
		this.type = type;
		setValue(value);
	}

	private CommandLinePropertyType type;
	
	/**
	 * The value found of the command line property.
	 */
	private String value;

	public CommandLinePropertyType getType() {
		return type;
	}
	public void setType(CommandLinePropertyType type) {
		this.type = type;
	}
	
	public String getValue() {
		return value;
	}
	
	/**
	 * Takes a string of the form: prefix + argumentName + suffix + value.  It
	 * sets the value property equal to what is found.
	 * @param value
	 * @throws ProcessInitFailure 
	 * @throws Exception 
	 */
	public void setValue(String value) throws ProcessInitFailure {
		if(!value.startsWith(type.getPrefix() + type.getArgumentName() + type.getSuffix())) {
			throw new ProcessInitFailure(CMD_LINE_MATCH_FAILURE);
		}
		int prefixLength = type.getPrefix().length() + type.getArgumentName().length() + type.getSuffix().length();
		this.value = value.substring(prefixLength);
	}
	@Override
	public boolean equals(Object obj) {
		CommandLineProperty prop = (CommandLineProperty) obj;
		
		if(!prop.getType().equals(this.getType()))
			return false;
		else if(!prop.getValue().equals(this.getValue()))
			return false;
		else
			return true;
	}
	
	
}
