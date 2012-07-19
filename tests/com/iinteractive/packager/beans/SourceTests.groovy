package com.iinteractive.packager.beans;

import java.io.File;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.Before;

import com.iinteractive.packager.beans.Source;

public class SourceTests {
	public static final String RELEASES_SUBDIR = "\\Release-Files";
	
	public static final String EXISTS_DIR_NAME = "exists";
	public static final String DNE_DIR_NAME = "dne";
	
	public static final File EXISTS_DIR = new File(EXISTS_DIR_NAME);
	public static final File DNE_DIR = new File(DNE_DIR_NAME);
	
	@Before
	void setUp() {
		if(!EXISTS_DIR.exists())
			assert EXISTS_DIR.mkdir()
			
		if(DNE_DIR.exists())
			assert DNE_DIR.deleteDir()
	}
	
	@Test
	public void testValidateSourceDirectory_Case_DNE() {
		try {
			Source.validateSourceDirectory(DNE_DIR_NAME);
			Assert.fail();
		} catch (Exception e) {
			assert true;
		}
	}
	
	@Test
	public void testValidateSourceDirectory_Case_Exists() {
		try {
			Source.validateSourceDirectory(EXISTS_DIR_NAME);
			assert true;
		} catch (Exception e) {
			Assert.fail();
		}
	}
}
