package com.iinteractive.packager.beans;

import java.io.File;

public class FileCopy {
	private File src;
	private File dest;
	
	public File getSrc() {
		return src;
	}

	public File getDest() {
		return dest;
	}

	public FileCopy(File src, File dest) {
		super();
		this.src = src;
		this.dest = dest;
	}

	@Override
	public String toString() {
		return "[src=" + src + ", dest=" + dest + "]";
	}	
}
