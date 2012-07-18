package com.iinteractive.packager.processes;

import java.io.File;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.iinteractive.packager.processes.FolderCreationProcess;

public class FolderCreationProcessTests {

	@Before
	public void cleanUp() {
		File baseDir = new File("C:\\JUnitTestFolders");
		File[] files = baseDir.listFiles();
		for(int i = 0; i < files.length; i++) {
			DeleteDirectory(files[i]);
		}
		
		new File("C:\\JUnitTestFolders\\testTwoD1").mkdir();
		new File("C:\\JUnitTestFolders\\testThreeD1").mkdir();
		new File("C:\\JUnitTestFolders\\testThreeD1\\testThreeD2").mkdir();
		new File("C:\\JUnitTestFolders\\testFiveD1").mkdir();
	}
	
	@After
	public void cleanUp2() {
		File baseDir = new File("C:\\JUnitTestFolders");
		File[] files = baseDir.listFiles();
		for(int i = 0; i < files.length; i++) {
			DeleteDirectory(files[i]);
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
	@Test
	public void testOneCreateFolder() {
		File file = new File("C:\\JUnitTestFolders\\testOneD1\\testOneD2\\file.txt");
		boolean pass = true;
		
		pass = pass && !file.exists();
		pass = pass && !file.getParentFile().exists();
		pass = pass && !file.getParentFile().getParentFile().exists();
		pass = pass && file.getParentFile().getParentFile().getParentFile().exists();
		
		pass = pass && FolderCreationProcess.CreateFolder(file);
		
		pass = pass && !file.exists();
		pass = pass && file.getParentFile().exists();
		pass = pass && file.getParentFile().getParentFile().exists();
		pass = pass && file.getParentFile().getParentFile().getParentFile().exists();
		
		Assert.assertTrue(pass);
	}
	
	/**
	 * exists() / exists() / !exists()
	 */
	@Test
	public void testTwoCreateFolder() {
		File file = new File("C:\\JUnitTestFolders\\testTwoD1\\testTwoD2\\file.txt");
		boolean pass = true;
		
		pass = pass && !file.exists();
		pass = pass && !file.getParentFile().exists();
		pass = pass && file.getParentFile().getParentFile().exists();
		pass = pass && file.getParentFile().getParentFile().getParentFile().exists();
		
		pass = pass && FolderCreationProcess.CreateFolder(file);
		
		pass = pass && !file.exists();
		pass = pass && file.getParentFile().exists();
		pass = pass && file.getParentFile().getParentFile().exists();
		pass = pass && file.getParentFile().getParentFile().getParentFile().exists();
	
		Assert.assertTrue(pass);
	}
	
	/**
	 * exists() / exists() / exists()
	 */
	@Test
	public void testThreeCreateFolder() {
		File file = new File("C:\\JUnitTestFolders\\testThreeD1\\testThreeD2\\file.txt");
		boolean pass = true;
		
		pass = pass && !file.exists();
		pass = pass && file.getParentFile().exists();
		pass = pass && file.getParentFile().getParentFile().exists();
		pass = pass && file.getParentFile().getParentFile().getParentFile().exists();
		
		pass = pass && !FolderCreationProcess.CreateFolder(file);
		
		pass = pass && !file.exists();
		pass = pass && file.getParentFile().exists();
		pass = pass && file.getParentFile().getParentFile().exists();
		pass = pass && file.getParentFile().getParentFile().getParentFile().exists();
	
		Assert.assertTrue(pass);
	}
	
	/**
	 * exists() / !exists()
	 */
	@Test
	public void testFourCreateFolder() {
		File file = new File("C:\\JUnitTestFolders\\testFourD1\\file.txt");
		boolean pass = true;
		
		pass = pass && !file.exists();
		pass = pass && !file.getParentFile().exists();
		pass = pass && file.getParentFile().getParentFile().exists();
		
		pass = pass && FolderCreationProcess.CreateFolder(file);
		
		pass = pass && !file.exists();
		pass = pass && file.getParentFile().exists();
		pass = pass && file.getParentFile().getParentFile().exists();
	
		Assert.assertTrue(pass);
	}
	/**
	 * exists() / exists()
	 */
	@Test
	public void testFiveCreateFolder() {
		File file = new File("C:\\JUnitTestFolders\\testFiveD1\\file.txt");
		boolean pass = true;
		
		pass = pass && !file.exists();
		pass = pass && file.getParentFile().exists();
		pass = pass && file.getParentFile().getParentFile().exists();
		
		pass = pass && !FolderCreationProcess.CreateFolder(file);
		
		pass = pass && !file.exists();
		pass = pass && file.getParentFile().exists();
		pass = pass && file.getParentFile().getParentFile().exists();
	
		Assert.assertTrue(pass);
	}

}
