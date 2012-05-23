package com.iinteractive.packager.processes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import com.iinteractive.packager.beans.Merge;

public class MergeProcess implements IProcess {
	public MergeProcess(String operationFile) {
		super();
		this.operationFile = operationFile;
		merge = new Merge();
	}

	private Merge merge;
	private String operationFile;
	
	public Merge getMerge() {
		return merge;
	}
	public void setMerge(Merge merge) {
		this.merge = merge;
	}
	public String getOperationFile() {
		return operationFile;
	}
	public void setOperationFile(String operationFile) {
		this.operationFile = operationFile;
	}
	
	@Override
	public void process() throws Exception {
		Scanner reader = new Scanner(new BufferedReader(new FileReader(operationFile)));

		if(reader.hasNextLine()) {
			merge.setSourcePackage(new com.iinteractive.packager.beans.Package(reader.nextLine()));
		}
		if(reader.hasNextLine()) {
			merge.setDestinationPackage(new com.iinteractive.packager.beans.Package(reader.nextLine()));
		}
	
		ArrayList<File> srcFileList = new ArrayList<File>();
		getFileList(srcFileList);
		ArrayList<File> destFileList = new ArrayList<File>();
		ArrayList<File> modifiedSrcFileList = new ArrayList<File>();
		for(int i = 0; i < srcFileList.size(); i++) {
			File file = new File(merge.getDestinationPackage().getFilesSubDirectory() 
					+ srcFileList.get(i).getAbsolutePath().substring(merge.getSourcePackage().getFilesSubDirectory().length())
				);
			
			if(srcFileList.get(i).isDirectory()) {
				file.mkdir();
			} else {
				destFileList.add(file);
				modifiedSrcFileList.add(srcFileList.get(i));
			}
			
			FolderCreationProcess.CreateFolder(file);
		}
		
		String script = FileCopyScriptCreationProcess.GenerateScript(modifiedSrcFileList, destFileList);
		
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
	
	private void getFileList(ArrayList<File> fileList) {
		File subDirectory = new File(merge.getSourcePackage().getFilesSubDirectory());
		File[] files = subDirectory.listFiles();
		recursiveGetFileList(files, fileList);
	}
	
	private void recursiveGetFileList(File[] files, ArrayList<File> fileList) {
		for(int i = 0; i < files.length; i++) {
			if(files[i].isDirectory() && files[i].listFiles().length != 0) {
				recursiveGetFileList(files[i].listFiles(), fileList);
			} else {
				fileList.add(files[i]);
			}
		}
	}
}