package com.iinteractive.packager.processes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.iinteractive.packager.beans.FileCopy;

public class FileCopyManager {
	public static final int NUM_THREADS = 10;
	
	private static ArrayList<Thread> threads = new ArrayList<Thread>(NUM_THREADS);
	private static ConcurrentLinkedQueue<FileCopy> fileCopyQueue = new ConcurrentLinkedQueue<FileCopy>();
	
	public static ConcurrentLinkedQueue<FileCopy> getFileCopyQueue() {
		return fileCopyQueue;
	}
	
	public static synchronized void copy(Collection<FileCopy> fileCopyOperations) {
		fileCopyQueue.addAll(fileCopyOperations);
		for(int i = 0; i < NUM_THREADS; i++) {
			
			threads.add(new Thread(new FileCopier()));
			threads.get(i).start();
		}
		
		for(int i = 0; i < threads.size(); i++) {
			Thread thread = threads.get(i);
			while(thread.isAlive());
		}
		
		threads.clear();
	}
}
