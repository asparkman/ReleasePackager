package com.iinteractive.packager.processes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import com.iinteractive.packager.beans.Package;
import com.iinteractive.packager.beans.Source;

public class PackageProcess implements IProcess {
	public PackageProcess(String operationFile) {
		super();
		this.operationFile = operationFile;
		changedFiles = new ArrayList<String>();
	}
	
	private Package pack;
	private Source source;
	private String operationFile;
	private ArrayList<String> changedFiles;
	
	public Package getPackage() {
		return pack;
	}
	public void setPackage(Package pack) {
		this.pack = pack;
	}
	public Source getSource() {
		return source;
	}
	public void setSource(Source source) {
		this.source = source;
	}
	public String getOperationFile() {
		return operationFile;
	}
	public void setOperationFile(String operationFile) {
		this.operationFile = operationFile;
	}
	public ArrayList<String> getChangedFiles() {
		return changedFiles;
	}
	public void setChangedFiles(ArrayList<String> changedFiles) {
		this.changedFiles = changedFiles;
	}
	
	@Override
	public void process() throws Exception {
		Scanner reader = new Scanner(new BufferedReader(new FileReader(operationFile)));
		
		if(reader.hasNextLine()) {
			source = new Source(reader.nextLine());
		}
		if(reader.hasNextLine()) {
			pack = new Package(reader.nextLine());
		}
		
		while(reader.hasNextLine()) {
			changedFiles.add(reader.nextLine());
		}
		
		ArrayList<File> srcFiles = new ArrayList<File>();
		ArrayList<File> destFiles = new ArrayList<File>();
		for(int i = 0; i < changedFiles.size(); i++) {
			File srcFile = new File(source.getSourceDirectory() + changedFiles.get(i));
			File destFile = new File(pack.getFilesSubDirectory() + changedFiles.get(i));
			FolderCreationProcess.CreateFolder(destFile);
			if(srcFile.isDirectory()) {
				destFile.mkdir();
			} else {
				srcFiles.add(srcFile);
				destFiles.add(destFile);
			}
		}
		
		String script = FileCopyScriptCreationProcess.GenerateScript(srcFiles, destFiles);
		
		if(script.length() != 0) {
			PrintWriter writer = new PrintWriter(new File("script.bat"));
			writer.print(script);
			writer.close();
			System.out.println("Files need to be copied in order for the release package to be complete.  This can be done by running the following script:");
			System.out.println(new File("script.bat").getAbsolutePath());
		} else {
			System.out.println("No files were found that needed copying.  No script.bat was generated.");
		}
	}
}
