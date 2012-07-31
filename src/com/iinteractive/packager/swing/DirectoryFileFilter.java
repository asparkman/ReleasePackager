package com.iinteractive.packager.swing;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class DirectoryFileFilter extends FileFilter {

	@Override
	public boolean accept(File f) {
		return f.isDirectory();
	}

	@Override
	public String getDescription() {
		return "This file filters out any non-folders.";
	}

}
