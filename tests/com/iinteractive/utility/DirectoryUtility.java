package com.iinteractive.utility;

import java.io.File;
import java.io.IOException;

public class DirectoryUtility implements IDirectoryUtility {
	static {
		File absolutePathRetrievalFile = new File("temp");
		absolutePathRetrievalFile.getParentFile();
		RootDir = absolutePathRetrievalFile.getAbsolutePath();
	}
	private static String RootDir;
	
	public DirectoryUtility(String prefix) {
		this.prefix = normalize(prefix);
	}

	private String prefix;
	@Override
	public String getPrefix() {
		return this.prefix;
	}
	@Override
	public void setPrefix(String prefix) throws Exception {
		if(prefix.length() == 0 || (prefix.length() == 1 && (prefix.startsWith("/") || prefix.startsWith("\\")))) {
			throw new Exception("Unacceptable prefix.");
		}
		this.prefix = normalize(prefix);
	}
	
	private String generatePath(String name) {
		return RootDir + prefix + normalize(name);
	}
	private String normalize(String name) {
		if(name.startsWith("/") || name.startsWith("\\")) {
			return name;
		} else {
			return "\\" + name;
		}
	}

	@Override
	public File[] constructDirs(String[] folders) {
		File[] dirs = new File[folders.length];
		for(int i = 0; i < folders.length; i++) {
			dirs[i] = constructDir(folders[i]);
		}
		return dirs;
	}

	@Override
	public File[] constructFiles(String[] files) throws IOException {
		File[] dirs = new File[files.length];
		for(int i = 0; i < files.length; i++) {
			dirs[i] = constructFile(files[i]);
		}
		return dirs;
	}

	@Override
	public File constructDir(String folder) {
		File dir = new File(generatePath(folder));
		File cursor = dir;
		while(cursor != null && !cursor.mkdir()) {
			cursor = cursor.getParentFile();
		}
		return dir;
	}

	@Override
	public File constructFile(String file) throws IOException {
		File fileObj = new File(generatePath(file));
		if(!fileObj.exists()) {
			File cursor = fileObj.getParentFile();
			while(cursor != null && !cursor.mkdir()) {
				cursor = cursor.getParentFile();
			}
			fileObj.createNewFile();
		}
		return fileObj;
	}

	@Override
	public File[] deleteDirs(String[] dirs) {
		File[] files = new File[dirs.length];
		for(int i = 0; i < dirs.length; i++) {
			files[i] = new File(generatePath(dirs[i]));
			deleteDir(files[i]);
		}
		return files;
	}

	@Override
	public void cleanUp() {
		File prefixDir = new File(RootDir + prefix);
		deleteDir(prefixDir);
	}

	private void deleteDir(File dir) {
		File[] files = dir.listFiles();
		for(int i = 0; i < files.length; i++) {
			if(files[i].isDirectory()) {
				deleteDir(files[i]);
			}
			files[i].delete();
		}
		dir.delete();
	}

}
