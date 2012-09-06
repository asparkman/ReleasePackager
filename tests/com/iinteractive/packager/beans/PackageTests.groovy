package com.iinteractive.packager.beans;

import junit.framework.Assert;
import junit.framework.TestCase

import org.junit.Test;
import org.junit.Before;
import com.iinteractive.packager.beans.Package;
import com.iinteractive.packager.exceptions.ValidationFailure

public class PackageTests extends TestCase {

	public static final String FOLDER_NAME_1 = "JUnitTestFolders";
	public static final File FOLDER_1 = new File(FOLDER_NAME_1);
	
	public static final String FOLDER_NAME_2 = "JUnitTestFolders2";
	public static final File FOLDER_2 = new File(FOLDER_NAME_2);
	
	public static final String RELEASES_SUBDIR = "\Release-Files";
	
	public void setUp() {
		if(!FOLDER_1.exists()) {
			assert FOLDER_1.mkdir();
		}
		
		if(FOLDER_2.exists()) {
			assert FOLDER_2.deleteDir();
		}
		
		assert FOLDER_1.exists();
		assert !FOLDER_2.exists();
	}
	
	public void testPackage() {
		Package test1 = new Package(FOLDER_NAME_1);
		boolean pass = test1.getFilesSubDirectory().equals(FOLDER_NAME_1 + RELEASES_SUBDIR) && test1.getPackageDirectory().equals(FOLDER_NAME_1);
		Assert.assertTrue(pass);
	}

	public void testSetPackageDirectory() {
		Package test1 = new Package(FOLDER_NAME_1);
		test1.setPackageDirectory(FOLDER_NAME_2);
		boolean pass = test1.getFilesSubDirectory().equals(FOLDER_NAME_2 + RELEASES_SUBDIR) && test1.getPackageDirectory().equals(FOLDER_NAME_2);
		Assert.assertTrue(pass);
	}

	/*
	 * Tests folder that does exist, and one that doesn't. 
	 */
	public void testValidatePackageDirectory_Case_Existant() {
		try {
			Package.validatePackageDirectory(FOLDER_NAME_1)
			assert true
		} catch (ValidationFailure e) {
			Assert.fail()
		}
	}
	
	public void testValidatePackageDirectory_Case_NonExistant() {
		try {
			Package.validatePackageDirectory(FOLDER_NAME_2)
			Assert.fail()
		} catch (ValidationFailure e) {
			assert true
		}
	}

}
