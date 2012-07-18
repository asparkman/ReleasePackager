package com.iinteractive.packager.beans;

import junit.framework.Assert;

import org.junit.Test;

import com.iinteractive.packager.beans.Source;

public class SourceTests {

	@Test
	public void testValidateSourceDirectory() {
		boolean pass = true;
		try {
			Source.validateSourceDirectory("C:\\JUnitTestFolders2");
			pass = false;
		} catch (Exception e) {
		}
		
		try {
			Source.validateSourceDirectory("C:\\JUnitTestFolders");
		} catch (Exception e) {
			pass = false;
		}
		
		Assert.assertTrue(pass);
	}

}
