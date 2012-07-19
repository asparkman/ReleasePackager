package com.iinteractive.packager.processes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.NoSuchElementException;

import com.iinteractive.packager.beans.FileCopy;

public class FileCopier implements Runnable {
	public static final int BUFFER_SIZE = 1024;
	
	public FileCopy copy;
	
	

	@Override
	public void run() {
		while(retrieveFileCopy()) {
			FileInputStream input = null;
			FileOutputStream output = null;
			try {
				input = new FileInputStream(copy.getSrc());
				output = new FileOutputStream(copy.getDest());
				
				int lastNumBytesRead = 0;
				byte[] bytes = new byte[BUFFER_SIZE];
				while((lastNumBytesRead = input.read(bytes)) != -1) {
					output.write(bytes, 0, lastNumBytesRead);
				}
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if(input != null) {
					try {
						input.close();
					} catch (IOException e1) {
						// Fail silently.
					}
				}
				if(output != null) {
					try {
						output.close();
					} catch (IOException e) {
						// Fail silently.
					}
				}
			}
		}
	}
	
	public boolean retrieveFileCopy() {
		try {
			copy = FileCopyManager.getFileCopyQueue().remove();
		} catch(NoSuchElementException ex) {
			return false;
		}
		
		return copy != null;
	}

}
