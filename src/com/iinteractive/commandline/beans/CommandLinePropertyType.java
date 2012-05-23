package com.iinteractive.commandline.beans;

public class CommandLinePropertyType {
	public CommandLinePropertyType(String prefix, String argumentName,
			String suffix) {
		super();
		this.argumentName = argumentName;
		this.prefix = prefix;
		this.suffix = suffix;
	}

	private String argumentName;
	private String prefix;
	private String suffix;
	
	public String getArgumentName() {
		return argumentName;
	}

	public void setArgumentName(String argumentName) {
		this.argumentName = argumentName;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	
	/**
	 * Takes a string of the form: prefix + argumentName + suffix + value.  It
	 * @param value
	 * @return Whether the prefix, argumentName, and suffix match.
	 */
	public boolean isOfType(String commandLineArgument) {
		return commandLineArgument.startsWith(prefix + argumentName + suffix);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return new CommandLinePropertyType(argumentName, prefix, suffix);
	}

	@Override
	public boolean equals(Object obj) {
		CommandLinePropertyType type = (CommandLinePropertyType) obj;
		
		if(!type.getPrefix().equals(this.getPrefix())) {
			return false;
		} else if(!type.getSuffix().equals(this.getSuffix())) {
			return false;
		} else if(!type.getArgumentName().equals(this.getArgumentName())) {
			return false;
		}
		
		return true;
	}
}
