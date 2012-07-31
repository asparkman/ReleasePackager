package com.iinteractive.packager.processes;

import java.io.File;

import junit.framework.Assert;
import junit.framework.TestCase

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.iinteractive.packager.exceptions.FolderCreationFailure
import com.iinteractive.packager.processes.FolderCreationProcess;

public class FolderCreationProcessTests extends TestCase {

	public void setUp() {
		File baseDir = new File("JUnitTestFolders");
		if(baseDir.isDirectory()) {
			File[] files = baseDir.listFiles();
			for(int i = 0; i < files.length; i++) {
				DeleteDirectory(files[i]);
			}
		}
		
		assert new File("JUnitTestFolders\\testTwoD1").mkdirs();
		assert new File("JUnitTestFolders\\testThreeD1").mkdirs();
		assert new File("JUnitTestFolders\\testThreeD1\\testThreeD2").mkdirs();
		assert new File("JUnitTestFolders\\testFiveD1").mkdirs();
	}
	
	public void tearDown() {
		File baseDir = new File("JUnitTestFolders");
		if(baseDir.isDirectory()) {
			File[] files = baseDir.listFiles();
			for(int i = 0; i < files.length; i++) {
				DeleteDirectory(files[i]);
			}
		}
	}
	
	public void DeleteDirectory(File file) {
		File[] files = file.listFiles();
		for(int i = 0; i < files.length; i++) {
			if(files[i].isDirectory()) {
				DeleteDirectory(files[i]);
			}
			files[i].delete();
		}
		file.delete();
	}
	
	/**
	 * exists() / !exists() / !exists()
	 */
	public void testOneCreateFolder() {
		File file = new File("JUnitTestFolders\\testOneD1\\testOneD2\\file.txt");
		boolean pass = true;
		
		pass = pass && !file.exists();
		pass = pass && !file.getParentFile().exists();
		pass = pass && !file.getParentFile().getParentFile().exists();
		pass = pass && file.getParentFile().getParentFile().getParentFile().exists();
		
		FolderCreationProcess.CreateFolder(file);
		
		pass = pass && !file.exists();
		pass = pass && file.getParentFile().exists();
		pass = pass && file.getParentFile().getParentFile().exists();
		pass = pass && file.getParentFile().getParentFile().getParentFile().exists();
		
		Assert.assertTrue(pass);
	}
	
	/**
	 * exists() / exists() / !exists()
	 */
	public void testTwoCreateFolder() {
		File file = new File("JUnitTestFolders\\testTwoD1\\testTwoD2\\file.txt");
		boolean pass = true;
		
		pass = pass && !file.exists();
		pass = pass && !file.getParentFile().exists();
		pass = pass && file.getParentFile().getParentFile().exists();
		pass = pass && file.getParentFile().getParentFile().getParentFile().exists();
		
		FolderCreationProcess.CreateFolder(file);
		
		pass = pass && !file.exists();
		pass = pass && file.getParentFile().exists();
		pass = pass && file.getParentFile().getParentFile().exists();
		pass = pass && file.getParentFile().getParentFile().getParentFile().exists();
	
		Assert.assertTrue(pass);
	}
	
	/**
	 * exists() / exists() / exists()
	 */
	public void testThreeCreateFolder() {
		File file = new File("JUnitTestFolders\\testThreeD1\\testThreeD2\\file.txt");
		boolean pass = true;
		
		pass = pass && !file.exists();
		pass = pass && file.getParentFile().exists();
		pass = pass && file.getParentFile().getParentFile().exists();
		pass = pass && file.getParentFile().getParentFile().getParentFile().exists();
		
		FolderCreationProcess.CreateFolder(file);
		
		pass = pass && !file.exists();
		pass = pass && file.getParentFile().exists();
		pass = pass && file.getParentFile().getParentFile().exists();
		pass = pass && file.getParentFile().getParentFile().getParentFile().exists();
	
		Assert.assertTrue(pass);
	}
	
	/**
	 * exists() / !exists()
	 */
	public void testFourCreateFolder() {
		File file = new File("JUnitTestFolders\\testFourD1\\file.txt");
		boolean pass = true;
		
		pass = pass && !file.exists();
		pass = pass && !file.getParentFile().exists();
		pass = pass && file.getParentFile().getParentFile().exists();
		
		FolderCreationProcess.CreateFolder(file);
		
		pass = pass && !file.exists();
		pass = pass && file.getParentFile().exists();
		pass = pass && file.getParentFile().getParentFile().exists();
	
		Assert.assertTrue(pass);
	}
	/**
	 * exists() / exists()
	 */
	public void testFiveCreateFolder() {
		File file = new File("JUnitTestFolders\\testFiveD1\\file.txt");
		boolean pass = true;
		
		pass = pass && !file.exists();
		pass = pass && file.getParentFile().exists();
		pass = pass && file.getParentFile().getParentFile().exists();
		
		FolderCreationProcess.CreateFolder(file);
		
		pass = pass && !file.exists();
		pass = pass && file.getParentFile().exists();
		pass = pass && file.getParentFile().getParentFile().exists();
	
		Assert.assertTrue(pass);
	}
	
	public void testFolderCreationFailure() {
		File file = new File("JUnitTestFolders\\testFolderCreationFailure\\?*\\file.txt");
		try {
			FolderCreationProcess.CreateFolder(file);
			assert false
		} catch(FolderCreationFailure ex) {
			assert true
		}
	}

}
