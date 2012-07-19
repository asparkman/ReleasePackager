package com.iinteractive.packager.processes;

import static org.junit.Assert.*;

import java.awt.event.ItemEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

import junit.framework.Assert;
import junit.framework.TestSuite

import junit.framework.TestCase;

import com.iinteractive.packager.processes.PackageProcess;

public class PackageProcessTests extends TestCase {
	
	public static final String SRC_DIR_NAME = "src_dir";
	public static final String DEST_DIR_NAME = "dest_dir";
	public static final String DNE_SRC_DIR_NAME = "dne_src_dir";
	public static final String DNE_DEST_DIR_NAME = "dne_dest_dir";
	public static final String OP_FILE_NAME = "opFile.txt";
	
	public File SRC_DIR = new File(SRC_DIR_NAME);
	public File DEST_DIR = new File(DEST_DIR_NAME);
	public File DNE_SRC_DIR = new File(DNE_SRC_DIR_NAME);
	public File DNE_DEST_DIR = new File(DNE_DEST_DIR_NAME);
	public File OP_FILE = new File(OP_FILE_NAME);
	
	public final String OP_FILE_CONTENTS_EMPTY = "";
	public final String OP_FILE_CONTENTS_NORMAL_HEADER = SRC_DIR_NAME + "\n" + DEST_DIR_NAME + "\n";
	public final String OP_FILE_CONTENTS_SRC_DNE_HEADER = DNE_SRC_DIR_NAME + "\n" + DEST_DIR_NAME;
	public final String OP_FILE_CONTENTS_DEST_DNE_HEADER = SRC_DIR_NAME + "\n" + DNE_DEST_DIR_NAME;
	
	def srcDirName;
	def destDirName;
	
	def srcFiles;
	def opFileSrcFiles;
	
	def test;
	
	public void runTest() {
		
		Closure closure = test.clone()
		
		closure.delegate = this;
		
		closure.call()
	}
	
	
	public PackageProcessTests(String name, srcDirName, destDirName, srcFiles, opFileSrcFiles, test) {
		super(name);
		this.srcDirName = srcDirName
		this.destDirName = destDirName
		this.srcFiles = srcFiles
		this.opFileSrcFiles = opFileSrcFiles
		this.test = test;
	}

	public void setUp() {
		if(SRC_DIR.exists())
			assert SRC_DIR.deleteDir()
		assert SRC_DIR.mkdir()
		if(DEST_DIR.exists())
			assert DEST_DIR.deleteDir()
		assert DEST_DIR.mkdir()
		
		if(DNE_SRC_DIR.exists()) 
			DNE_SRC_DIR.deleteDir()
		if(DNE_DEST_DIR.exists())
			DNE_DEST_DIR.deleteDir()
		if(OP_FILE.exists()) {
			if(OP_FILE.isDirectory())
				assert OP_FILE.deleteDir()
			else {
				assert OP_FILE.exists()
				assert OP_FILE.delete()
			}
		}
	}
	
	public void tearDown() {
		if(SRC_DIR.exists())
			assert SRC_DIR.deleteDir()
		if(DEST_DIR.exists())
			assert DEST_DIR.deleteDir()
		
		if(DNE_SRC_DIR.exists())
			DNE_SRC_DIR.deleteDir()
		if(DNE_DEST_DIR.exists())
			DNE_DEST_DIR.deleteDir()
		if(OP_FILE.exists()) {
			if(OP_FILE.isDirectory())
				assert OP_FILE.deleteDir()
			else {
				assert OP_FILE.exists()
				assert OP_FILE.delete()
			}
		}
	}
	
	public void initializeSrcDir() {
		srcFiles.each {
				File file = new File(srcDirName + it)
				assert !file.exists()
				if(it.indexOf(".") != -1) {
					file.createNewFile()
				} else
					assert file.mkdirs()
			}
	}
	
	public void testSourceDir() {
		initializeSrcDir()
		String opFileContents = OP_FILE_CONTENTS_NORMAL_HEADER;
		opFileSrcFiles.each {
				opFileContents += it + "\n"
			}
		PrintWriter writer = OP_FILE.newPrintWriter()
		writer.write(opFileContents)
		writer.close()
		
		PackageProcess process = new PackageProcess(OP_FILE_NAME);
		try {
			process.process()
			assert true
		} catch(Exception ex) {
			fail()
		}
	}
	
	public void testEmptyOpFile() {
		exceptionTestTemplate(OP_FILE_CONTENTS_EMPTY);
	}
	
	public void testNonExistantSrcDir() {
		exceptionTestTemplate(OP_FILE_CONTENTS_SRC_DNE_HEADER);
	}
	
	public void testNonExistantDestDir() {
		exceptionTestTemplate(OP_FILE_CONTENTS_DEST_DNE_HEADER);
	}
	
	public void exceptionTestTemplate(String opFileContents) {
		PrintWriter writer = OP_FILE.newPrintWriter()
		writer.write(opFileContents)
		writer.close()
		
		PackageProcess process = new PackageProcess(OP_FILE_NAME);
		try {
			process.process()
			fail()
		} catch(Exception ex) {
			assert true
		}
	}
	
	public static junit.framework.Test suite() { 
		TestSuite suite = new TestSuite()
		
		suite.addTest(new PackageProcessTests("0", SRC_DIR_NAME, DEST_DIR_NAME, [], [], {testSourceDir()}))
		suite.addTest(new PackageProcessTests("1", SRC_DIR_NAME, DEST_DIR_NAME, ["\\file.txt"], ["\\file.txt"], {testSourceDir()}))
		suite.addTest(new PackageProcessTests("2", SRC_DIR_NAME, DEST_DIR_NAME, ["\\folder"], ["\\folder"], {testSourceDir()}))
		suite.addTest(new PackageProcessTests("3", SRC_DIR_NAME, DEST_DIR_NAME, ["\\folder", "\\folder\\file.txt"], ["\\folder"], {testSourceDir()}))
		suite.addTest(new PackageProcessTests("4", SRC_DIR_NAME, DEST_DIR_NAME, ["\\folder", "\\folder\\file.txt"], ["\\folder\\file.txt"], {testSourceDir()}))
		suite.addTest(new PackageProcessTests("5", SRC_DIR_NAME, DEST_DIR_NAME, ["\\folder", "\\folder\\folder2"], ["\\folder\\folder2"], {testSourceDir()}))
		suite.addTest(new PackageProcessTests("6", SRC_DIR_NAME, DEST_DIR_NAME, ["\\file.txt", "\\file2.txt"], ["\\file.txt", "\\file2.txt"], {testSourceDir()}))
		
		suite.addTest(new PackageProcessTests("7", DNE_SRC_DIR_NAME, DEST_DIR_NAME, [], [], {testEmptyOpFile()}))
		
		suite.addTest(new PackageProcessTests("8", DNE_SRC_DIR_NAME, DEST_DIR_NAME, [], [], {testNonExistantSrcDir()}))
		suite.addTest(new PackageProcessTests("9", SRC_DIR_NAME, DNE_DEST_DIR_NAME, [], [], {testNonExistantDestDir()}))
		
		return suite
	}
}
