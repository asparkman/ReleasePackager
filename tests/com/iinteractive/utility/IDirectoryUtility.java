package com.iinteractive.utility;

import java.io.File;
import java.io.IOException;

public interface IDirectoryUtility {
	public String getPrefix();
	public void setPrefix(String prefix) throws Exception;
	
	public File[] constructDirs(String[] folders);
	public File[] constructFiles(String[] files) throws IOException;
	public File constructDir(String folders);
	public File constructFile(String files) throws IOException;
	public File[] deleteDirs(String[] dirs);
	public void cleanUp();
}
