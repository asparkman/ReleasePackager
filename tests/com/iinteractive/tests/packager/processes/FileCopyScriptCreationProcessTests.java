package com.iinteractive.tests.packager.processes;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

import com.iinteractive.packager.processes.FileCopyScriptCreationProcess;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Methods are named with the following convention:
 * <fileList1 size><fileList2 size>GenerateScript
 * @author Alex Sparkman
 *
 */
public class FileCopyScriptCreationProcessTests {

	private ArrayList<File> fileList1;
	private ArrayList<File> fileList2;
	
	@Before public void createArrays() {
		fileList1 = new ArrayList<File>();
		fileList2 = new ArrayList<File>();
	}
	
	@Test
	public void emptyEmptyGenerateScript() {
		try {
			String val = FileCopyScriptCreationProcess.GenerateScript(fileList1, fileList2);
			
			Assert.assertTrue(val.equals("pause"));
		} catch (Exception e) {
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void oneEmptyGenerateScript() {
		try {
			fileList1.add(new File("file.txt"));
			FileCopyScriptCreationProcess.GenerateScript(fileList1, fileList2);
			Assert.assertTrue(false);
		} catch (Exception e) {
			Assert.assertTrue(true);
		}
	}
	
	@Test
	public void emptyOneGenerateScript() {
		try {
			fileList2.add(new File("file.txt"));
			FileCopyScriptCreationProcess.GenerateScript(fileList1, fileList2);
			Assert.assertTrue(false);
		} catch (Exception e) {
			Assert.assertTrue(true);
		}
	}
	
	@Test
	public void oneOneGenerateScript() {
		try {
			File file1 = new File("file1.txt");
			File file2 = new File("file2.txt");
			fileList1.add(file1);
			fileList2.add(file2);
			String val = FileCopyScriptCreationProcess.GenerateScript(fileList1, fileList2);
			Assert.assertTrue(("COPY \"" + file1.getAbsolutePath() + "\" \"" + file2.getAbsolutePath() + "\";\npause").equals(val));
		} catch (Exception e) {
			Assert.assertTrue(false);
		}
	}

	@Test
	public void twoTwoGenerateScript() {
		try {
			File file1 = new File("file1.txt");
			File file2 = new File("file2.txt");
			File file3 = new File("file3.txt");
			File file4 = new File("file4.txt");
			fileList1.add(file1);
			fileList2.add(file2);
			fileList1.add(file3);
			fileList2.add(file4);
			String val = FileCopyScriptCreationProcess.GenerateScript(fileList1, fileList2);
			String script = ("COPY \"" + file1.getAbsolutePath() + "\" \"" + file2.getAbsolutePath() + "\";\n")
				+ ("COPY \"" + file3.getAbsolutePath() + "\" \"" + file4.getAbsolutePath() + "\";\npause");
			Assert.assertTrue(script.equals(val));
		} catch (Exception e) {
			Assert.assertTrue(false);
		}
	}
}
