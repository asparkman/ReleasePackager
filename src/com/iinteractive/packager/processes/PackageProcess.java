package com.iinteractive.packager.processes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import com.iinteractive.packager.beans.FileCopy;
import com.iinteractive.packager.beans.Package;
import com.iinteractive.packager.beans.Source;
import com.iinteractive.packager.exceptions.FileCopyFailure;
import com.iinteractive.packager.exceptions.FolderCreationFailure;
import com.iinteractive.packager.exceptions.ProcessFailure;
import com.iinteractive.packager.exceptions.ValidationFailure;

public class PackageProcess implements IProcess {
	public static final String OPFILE_MISSING_CONTENT = "The operation file is missing content.";
	public static final String FILE_COPY_OPERATION_ERROR = "A set of files was unable to be copied. ";
	public static final String DIR_CREATION_ERROR = "Failed to create a folder. ";
	
	public PackageProcess(String operationFile) {
		super();
		this.operationFile = operationFile;
		changedFiles = new ArrayList<String>();
	}
	
	public PackageProcess(Readable readable) {
		super();
		this.readable = readable;
		changedFiles = new ArrayList<String>();
	}
	
	private Package pack;
	private Source source;
	private String operationFile;
	private Readable readable;
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
	public Readable getReadable() {
		return readable;
	}
	public void setReadable(Readable readable) {
		this.readable = readable;
	}
	public ArrayList<String> getChangedFiles() {
		return changedFiles;
	}
	public void setChangedFiles(ArrayList<String> changedFiles) {
		this.changedFiles = changedFiles;
	}
	
	@Override
	public void process() throws ProcessFailure {
		Scanner reader = null;
		try {
			if(readable == null)
				reader = new Scanner(new BufferedReader(new FileReader(operationFile)));
			else
				reader = new Scanner(readable);
		} catch(FileNotFoundException ex) {
			throw new ProcessFailure(ex);
		}
		
		if(reader.hasNextLine()) {
			String sourceDir = reader.nextLine();
			try {
				Source.validateSourceDirectory(sourceDir);
			} catch(ValidationFailure ex) {
				reader.close();
				throw new ProcessFailure(ex);
			}
			source = new Source(sourceDir);
		} else {
			reader.close();
			throw new ProcessFailure(OPFILE_MISSING_CONTENT);
		}
		
		if(reader.hasNextLine()) {
			String packDir = reader.nextLine();
			try {
				Package.validatePackageDirectory(packDir);
			} catch(ValidationFailure ex) {
				reader.close();
				throw new ProcessFailure(ex);
			}
			pack = new Package(packDir);
		} else {
			reader.close();
			throw new ProcessFailure(OPFILE_MISSING_CONTENT);
		}
		
		while(reader.hasNextLine()) {
			changedFiles.add(reader.nextLine());
		}
		
		reader.close();
		
		ArrayList<FileCopy> fileCopyOperations = new ArrayList<FileCopy>();
		for(int i = 0; i < changedFiles.size(); i++) {
			File srcFile = new File(source.getSourceDirectory() + changedFiles.get(i));
			File destFile = new File(pack.getFilesSubDirectory() + changedFiles.get(i));
			try {
				FolderCreationProcess.CreateFolder(destFile);
			} catch (FolderCreationFailure e) {
				throw new ProcessFailure(e);
			}
			if(srcFile.isDirectory()) {
				destFile.mkdir();
				if(!destFile.exists()) {
					throw new ProcessFailure(DIR_CREATION_ERROR + destFile.getAbsolutePath());
				}
			} else {
				fileCopyOperations.add(new FileCopy(srcFile, destFile));
			}
		}
		
		if(!FileCopyManager.copy(fileCopyOperations)) {
			Collection<FileCopyFailure> copyFailures = FileCopyManager.getFailures();
			throw new ProcessFailure(FILE_COPY_OPERATION_ERROR, copyFailures);
		}
	}
}
