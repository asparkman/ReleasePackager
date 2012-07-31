package com.iinteractive.packager

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import java.util.regex.Pattern
import java.util.regex.Matcher

import junit.framework.TestCase
import org.apache.log4j.Appender
import org.apache.log4j.Logger
import org.apache.log4j.PropertyConfigurator

class Log4jTests extends TestCase {
	static {
		PropertyConfigurator.configure("log4j.properties");
	}
	
	Logger logger = Logger.getRootLogger();
	private static Pattern debugMessage = Pattern.compile("^[0-9][0-9 ]{3} \\[.+?\\] root \\- .+?\n", Pattern.DOTALL)
	
	public void testLog4j() {
		logger.debug("Testing");
	}
}
