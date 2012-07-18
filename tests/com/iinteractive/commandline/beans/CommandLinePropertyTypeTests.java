package com.iinteractive.commandline.beans;

import org.junit.Test;
import junit.framework.Assert;
import com.iinteractive.commandline.beans.CommandLinePropertyType;

public class CommandLinePropertyTypeTests {

	/**
	 * Tests Type: "ABC" Value: "ABC"
	 */
	@Test
	public void testIsOfTypeABCEmpty() {
		Assert.assertTrue(new CommandLinePropertyType("A", "B", "C").isOfType("ABC"));
	}
	/**
	 * Tests Type: "ABC" Value: "ABC1"
	 */
	@Test
	public void testIsOfTypeABCOne() {
		Assert.assertTrue(new CommandLinePropertyType("A", "B", "C").isOfType("ABC1"));
	}
	/**
	 * Tests Type: "ABC" Value: "ABC12"
	 */
	@Test
	public void testIsOfTypeABCTwo() {
		Assert.assertTrue(new CommandLinePropertyType("A", "B", "C").isOfType("ABC12"));
	}
	/**
	 * Tests Type: "ABC" Value: "AB"
	 */
	@Test
	public void testIsOfTypeABEmpty() {
		Assert.assertTrue(!new CommandLinePropertyType("A", "B", "C").isOfType("AB"));
	}
	/**
	 * Tests Type: "ABC" Value: "BC"
	 */
	@Test
	public void testIsOfTypeBCEmpty() {
		Assert.assertTrue(!new CommandLinePropertyType("A", "B", "C").isOfType("BC"));
	}
	/**
	 * Tests Type: "ABC" Value: "AC"
	 */
	@Test
	public void testIsOfTypeACEmpty() {
		Assert.assertTrue(!new CommandLinePropertyType("A", "B", "C").isOfType("AC"));
	}

	/**
	 * Tests:
	 * 		true: 
	 * 			Type1: "ABC" Type2: "ABC"
	 * 		false:
	 * 			Type1: "ABC" Type2: "DBC"
	 * 			Type1: "ABC" Type2: "ADC"
	 * 			Type1: "ABC" Type2: "ABD"
	 * 			Type1: "DBC" Type2: "ABC"
	 * 			Type1: "ADC" Type2: "ABC"
	 * 			Type1: "ABD" Type2: "ABC"
	 */
	@Test
	public void testEquals() {
		CommandLinePropertyType ABC = new CommandLinePropertyType("A", "B", "C");
		CommandLinePropertyType DBC = new CommandLinePropertyType("D", "B", "C");
		CommandLinePropertyType ADC = new CommandLinePropertyType("A", "D", "C");
		CommandLinePropertyType ABD = new CommandLinePropertyType("A", "B", "D");
		
		boolean pass = true;
		
		pass = pass && ABC.equals(ABC);
		
		pass = pass && !ABC.equals(DBC);
		pass = pass && !ABC.equals(ADC);
		pass = pass && !ABC.equals(ABD);
		pass = pass && !DBC.equals(ABC);
		pass = pass && !ADC.equals(ABC);
		pass = pass && !ABD.equals(ABC);	
		
		Assert.assertTrue(pass);
	}
}
