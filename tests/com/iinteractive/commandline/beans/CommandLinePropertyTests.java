package com.iinteractive.commandline.beans;

import junit.framework.Assert;

import org.junit.Test;

import com.iinteractive.commandline.beans.CommandLineProperty;
import com.iinteractive.commandline.beans.CommandLinePropertyType;

public class CommandLinePropertyTests {
	/**
	 * Tests:
	 * 		Type: "ABC", Argument: "ABC", Output: ""
	 * 		Type: "ABC", Argument: "ABC1", Output: "1"
	 * 		Type: "ABC", Argument: "ABC12", Output: "12"
	 * 		Type: "ABC", Argument: "ABCABC", Output: "ABC"
	 * 		Type: "ABC", Argument: "DBC1", Output: Exception
	 * 		Type: "ABC", Argument: "ADC1", Output: Exception
	 * 		Type: "ABC", Argument: "ABD1", Output: Exception
	 */
	@Test
	public void testSetValue() {
		CommandLinePropertyType type = new CommandLinePropertyType("A", "B", "C");
		try {
			String tests[] = 
				{
					new CommandLineProperty(type, "ABC").getValue(),
					new CommandLineProperty(type, "ABC1").getValue(),
					new CommandLineProperty(type, "ABC12").getValue(),
					new CommandLineProperty(type, "ABCABC").getValue(),
					"",
					"",
					""
				};
			try {
				new CommandLineProperty(type, "DBC1").getValue();
			} catch(Exception ex) {
				tests[4] = "true";
			}
			try {
				new CommandLineProperty(type, "ADC1").getValue();
			} catch(Exception ex) {
				tests[5] = "true";
			}
			try {
				new CommandLineProperty(type, "ABD1").getValue();
			} catch(Exception ex) {
				tests[6] = "true";
			}
			boolean pass = true;
			
			pass = pass && tests[0].equals("");
			pass = pass && tests[0].equals("");
			pass = pass && tests[1].equals("1");
			pass = pass && tests[2].equals("12");
			pass = pass && tests[3].equals("ABC");
			pass = pass && tests[4].equals("true");
			pass = pass && tests[5].equals("true");
			pass = pass && tests[6].equals("true");
			
			Assert.assertTrue(pass);
		} catch(Exception ex) {
		}
		
	}

}
