package com.iinteractive.tests.packager.beans;

import junit.framework.Assert;

import org.junit.Test;
import com.iinteractive.packager.beans.Package;

public class PackageTests {

	@Test
	public void testPackage() {
		Package test1 = new Package("C:\\JUnitTestFolders");
		boolean pass = test1.getFilesSubDirectory().equals("C:\\JUnitTestFolders\\Release-Files")
				&& test1.getPackageDirectory().equals("C:\\JUnitTestFolders");
		Assert.assertTrue(pass);
	}

	@Test
	public void testSetPackageDirectory() {
		Package test1 = new Package("C:\\JUnitTestFolders");
		test1.setPackageDirectory("C:\\JUnitTestFolders2");
		boolean pass = test1.getFilesSubDirectory().equals("C:\\JUnitTestFolders2\\Release-Files") 
				&& test1.getPackageDirectory().equals("C:\\JUnitTestFolders2");
		Assert.assertTrue(pass);
	}

	/*
	 * Tests folder that does exist, and one that doesn't. 
	 */
	@Test
	public void testValidatePackageDirectory() {
		boolean pass = true;
		try {
			Package.validatePackageDirectory("C:\\JUnitTestFolders2");
			pass = false;
		} catch (Exception e) {
		}
		
		try {
			Package.validatePackageDirectory("C:\\JUnitTestFolders");
		} catch (Exception e) {
			pass = false;
		}
		Assert.assertTrue(pass);
	}

}
