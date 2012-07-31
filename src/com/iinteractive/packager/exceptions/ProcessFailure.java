package com.iinteractive.packager.exceptions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class ProcessFailure extends Exception {
	private static final long serialVersionUID = 994007138092703125L;
	
	private Collection<FileCopyFailure> fileCopyFailures;

	public Collection<FileCopyFailure> getFileCopyFailures() {
		return fileCopyFailures;
	}

	public ProcessFailure() {
		super();
	}

	public ProcessFailure(String message, Throwable cause) {
		super(message, cause);
	}

	public ProcessFailure(String message) {
		super(message);
	}

	public ProcessFailure(Throwable cause) {
		super(cause);
	}
	
	public ProcessFailure(String message, Collection<FileCopyFailure> fileCopyFailures) {
		super(message);
	}
	
	public ProcessFailure(Collection<FileCopyFailure> causes) {
		super();
	}
	
	public Collection<ProcessFailure> generateExceptions() {
		ArrayList<ProcessFailure> exceptions = null;
		if(fileCopyFailures == null || fileCopyFailures.isEmpty()) {
			exceptions = new ArrayList<ProcessFailure>(1);
			exceptions.add(this);
		} else {
			exceptions = new ArrayList<ProcessFailure>(fileCopyFailures.size());
			Iterator<FileCopyFailure> iterator = fileCopyFailures.iterator();
			while(iterator.hasNext()) {
				if(this.getMessage() != null)
					exceptions.add(new ProcessFailure(this.getMessage(), iterator.next()));
				else
					exceptions.add(new ProcessFailure(iterator.next()));
			}
		}
		
		return exceptions;
	}
}
