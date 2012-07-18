package com.iinteractive.packager.processes;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Scanner;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.iinteractive.packager.processes.MergeProcess;
import com.iinteractive.packager.beans.Package;

public class MergeProcessTests {

	@Before
	public void init() throws Exception {
		
	}

	@After
	public void cleanUp() {
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
	
	private void moveCopyScript(int i) {
		File script = new File("script.bat");
		
		if(script.exists()) {
			try {
				Scanner reader = new Scanner(new BufferedReader(new FileReader(script)));
				File writeFile = new File("\\var\\scripts\\merge\\script" + i + ".bat");
				
				PrintWriter writer = new PrintWriter(writeFile);
				while(reader.hasNextLine()) {
					writer.println(reader.nextLine());
				}
				writer.close();
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	private String getOpFile(int i) {
		return "C:\\Users\\Alex Sparkman\\Documents\\Job\\Workspace\\ReleasePackager\\TestFolders\\MergeProcessTestFolders\\OpFiles\\mergeR1File" + i;
	}
	
	@Test
	public void testGetFileList() throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		File file1 = new File("C:\\Users\\Alex Sparkman\\Documents\\Job\\Workspace\\ReleasePackager\\TestFolders\\GetFileListTestFolders\\OpFiles\\opFile1");
		File file2 = new File("C:\\Users\\Alex Sparkman\\Documents\\Job\\Workspace\\ReleasePackager\\TestFolders\\GetFileListTestFolders\\OpFiles\\opFile2");

		String releaseFilesFolder = "C:\\Users\\Alex Sparkman\\Documents\\Job\\Workspace\\ReleasePackager\\TestFolders\\GetFileListTestFolders\\SourceFolders\\srcFolder1\\Release-Files";
		ArrayList<File> expOutput1 = new ArrayList<File>();
		expOutput1.add(new File(releaseFilesFolder + "\\empty"));
		expOutput1.add(new File(releaseFilesFolder + "\\file\\f1.txt"));
		expOutput1.add(new File(releaseFilesFolder + "\\fileAndFolder\\empty"));
		expOutput1.add(new File(releaseFilesFolder + "\\fileAndFolder\\file\\f1.txt"));
		expOutput1.add(new File(releaseFilesFolder + "\\fileAndFolder\\f1.txt"));
		expOutput1.add(new File(releaseFilesFolder + "\\folder\\empty"));
		expOutput1.add(new File(releaseFilesFolder + "\\folder\\file\\f1.txt"));
		
		boolean valid = true;
		MergeProcess process = null;
		
		process = new MergeProcess(file1.getAbsolutePath());
		process.getMerge().setSourcePackage(new Package("C:\\Users\\Alex Sparkman\\Documents\\Job\\Workspace\\ReleasePackager\\TestFolders\\GetFileListTestFolders\\SourceFolders\\srcFolder1"));
		Method method = process.getClass().getDeclaredMethod("getFileList", new ArrayList<File>().getClass());
		method.setAccessible(true);
		ArrayList<File> fileList = new ArrayList<File>();
		method.invoke(process, fileList);
			
		if(expOutput1.size() != fileList.size()) {
			valid = false;
		}
		
		for(int i = 0; i < expOutput1.size(); i++) {
			boolean found = false;
			for(int j = 0; j < fileList.size(); j++) {
				if(fileList.get(j).getAbsolutePath().equals(expOutput1.get(i).getAbsolutePath())) {
					found = true;
					break;
				}
			}
			if(!found) {
				valid = false;
				break;
			}
		}
		
		Assert.assertTrue(valid);
		
		process = new MergeProcess(file2.getAbsolutePath());
		process.getMerge().setSourcePackage(new Package("C:\\Users\\Alex Sparkman\\Documents\\Job\\Workspace\\ReleasePackager\\TestFolders\\GetFileListTestFolders\\SourceFolders\\srcFolder2"));
		method = process.getClass().getDeclaredMethod("getFileList", new ArrayList<File>().getClass());
		method.setAccessible(true);
		ArrayList<File> fileList2 = new ArrayList<File>();
		method.invoke(process, fileList2);
		
		if(fileList2.isEmpty())
			Assert.assertTrue(true);
		else
			Assert.assertTrue(false);
	}
	
	@Test
	public void testProcess() {
		MergeProcess process = null;
		for(int i = 1; i <= 8; i++) {
			process = new MergeProcess(getOpFile(i));
			try {
				process.process();
				moveCopyScript(i);
			} catch (Exception e) {
				Assert.assertTrue(false);
			}
		}
		
		Assert.assertTrue(true);
	}
}
