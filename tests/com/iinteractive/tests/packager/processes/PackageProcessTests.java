package com.iinteractive.tests.packager.processes;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;

import com.iinteractive.packager.processes.PackageProcess;

public class PackageProcessTests {
	
	@Before
	public void init() {
		File packageFolder = new File("C:\\Users\\Alex Sparkman\\Documents\\Job\\Workspace\\ReleasePackager\\TestFolders\\PackageProcessTestFolders\\PackageFolder");
		packageFolder.mkdir();
		File releaseFilesFolder = new File("C:\\Users\\Alex Sparkman\\Documents\\Job\\Workspace\\ReleasePackager\\TestFolders\\PackageProcessTestFolders\\PackageFolder\\Release-Files");
		releaseFilesFolder.mkdir();
	}
	
	@After
	public void cleanUp() throws Exception {
		File script = new File("script.bat");
		
		if(script.exists()) {
			if(script.delete()) {
				throw new Exception("Unable to delete script file.");
			}
		}
		/*File packageFolder = new File("C:\\Users\\Alex Sparkman\\Documents\\Job\\Workspace\\ReleasePackager\\TestFolders\\PackageProcessTestFolders\\PackageFolder");
		File[] files = packageFolder.listFiles();
		for(int i = 0; i < files.length; i++) {
			DeleteDirectory(files[i]);
		}*/
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

	@Test
	public void testProcess() {
		String opFile = null;
		PackageProcess process = null;
		
		for(int i = 1; i <= 10; i++) {
			opFile = getOpFileName("opFile" + i);
			process = new PackageProcess(opFile);
			try {
				process.process();
				moveCopyScript(i);
				Assert.assertTrue(true);
			} catch (Exception e) {
				fail("");
			}
		}
	}
	
	private void moveCopyScript(int i) {
		File script = new File("script.bat");
		
		if(script.exists()) {
			try {
				Scanner reader = new Scanner(new BufferedReader(new FileReader(script)));
				File writeFile = new File("\\var\\scripts\\script" + i + ".bat");
				
				PrintWriter writer = new PrintWriter(writeFile);
				while(reader.hasNextLine()) {
					writer.println(reader.nextLine());
				}
				writer.close();
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private String getOpFileName(String fileName) {
		return "C:\\Users\\Alex Sparkman\\Documents\\Job\\Workspace\\ReleasePackager\\TestFolders\\PackageProcessTestFolders\\" + fileName;
	}

}
