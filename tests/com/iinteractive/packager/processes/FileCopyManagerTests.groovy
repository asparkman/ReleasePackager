package com.iinteractive.packager.processes

import com.iinteractive.packager.beans.FileCopy
import junit.framework.TestCase;
import junit.framework.TestSuite

class FileCopyManagerTests extends TestCase {
	private static final String SRC_FILE_NAME_PREFIX = "src_";
	private static final String DEST_FILE_NAME_PREFIX = "dest_";
	private static final String FILE_NAME_SUFFIX = ".txt";
	private static final String SRC_FOLDER_NAME = "source";
	private static final String DEST_FOLDER_NAME = "destination";
	
	private int numFiles;
	private int fileLengths;
	private Random random;
	
	public FileCopyManagerTests(String name, int numFiles, int fileLengths, long seed) {
		super(name);
		this.numFiles = numFiles;
		this.fileLengths = fileLengths;
		random = new Random(seed);
	}


	public void setUp() {
		File srcDir = new File(SRC_FOLDER_NAME); 
		File destDir = new File(DEST_FOLDER_NAME);
		if(srcDir.exists()) {
			if(srcDir.isDirectory())
				assert srcDir.deleteDir();
			else
				assert srcDir.delete();
		}
		assert srcDir.mkdir();
		if(destDir.exists()) {
			if(destDir.isDirectory())
				assert destDir.deleteDir();
			else
				assert destDir.delete();
		}
		assert destDir.mkdir();
		
		for(int i = 0; i < numFiles; i++) {
			File srcFile = new File(getSrcFileName(i));
			srcFile.setBytes(generateFileContents());
		}
	}
	
	public String getSrcFileName(int i) {
		return SRC_FOLDER_NAME + "\\" + SRC_FILE_NAME_PREFIX + i + FILE_NAME_SUFFIX;
	}
	
	public String getDestFileName(int i) {
		return DEST_FOLDER_NAME + "\\" + DEST_FILE_NAME_PREFIX + i + FILE_NAME_SUFFIX;
	}
	
	public byte[] generateFileContents() {
		byte[] bytes = new byte[fileLengths];
		
		random.nextBytes(bytes);
		
		return bytes;
	}
	
	public void runTest() {
		testBulkCopy();
	}
	
	public void testBulkCopy() {
		ArrayList<FileCopy> fileCopies = new ArrayList<FileCopy>(numFiles);
		for(int i = 0; i < numFiles; i++)
			fileCopies.add(new FileCopy(new File(getSrcFileName(i)), new File(getDestFileName(i))));
			
		long elapsedTime = System.currentTimeMillis(); 	
		FileCopyManager.copy(fileCopies);
		elapsedTime = System.currentTimeMillis() - elapsedTime;
		System.out.println("Copy time: " + elapsedTime + " ms");
		
		for(int i = 0; i < fileCopies.size(); i++) {
			File src = fileCopies.get(i).getSrc();
			File dest = fileCopies.get(i).getDest();
			
			byte[] srcBytes = src.getBytes();
			byte[] destBytes = dest.getBytes();
			
			assert srcBytes.length == destBytes.length
			assert destBytes.length == fileLengths
			
			for(int j = 0; j < srcBytes.length; j++)
				assert srcBytes[j] == destBytes[j];
		}
	}
	
	public void tearDown() {
		File srcDir = new File(SRC_FOLDER_NAME); 
		File destDir = new File(DEST_FOLDER_NAME);
		if(srcDir.exists()) {
			if(srcDir.isDirectory())
				assert srcDir.deleteDir();
			else
				assert srcDir.delete();
		}
		if(destDir.exists()) {
			if(destDir.isDirectory())
				assert destDir.deleteDir();
			else
				assert destDir.delete();
		}
	}
	
	public static junit.framework.Test suite() {
		TestSuite suite = new TestSuite();
		
		int tc = 0;
		
		suite.addTest(new FileCopyManagerTests(tc.toString(), 1, FileCopier.BUFFER_SIZE * 0 + 0, tc++));
		suite.addTest(new FileCopyManagerTests(tc.toString(), 1, FileCopier.BUFFER_SIZE * 0 + 1, tc++));
		suite.addTest(new FileCopyManagerTests(tc.toString(), 1, FileCopier.BUFFER_SIZE * 1 + 0, tc++));
		suite.addTest(new FileCopyManagerTests(tc.toString(), 1, FileCopier.BUFFER_SIZE * 1 + 1, tc++));
		suite.addTest(new FileCopyManagerTests(tc.toString(), 1, FileCopier.BUFFER_SIZE * 2 + 0, tc++));
		suite.addTest(new FileCopyManagerTests(tc.toString(), 1, FileCopier.BUFFER_SIZE * 2 + 1, tc++));
		suite.addTest(new FileCopyManagerTests(tc.toString(), 1, FileCopier.BUFFER_SIZE * 100 + 1, tc++));
		
		int default_file_size = FileCopier.BUFFER_SIZE * 20;
		
		suite.addTest(new FileCopyManagerTests(tc.toString(), FileCopyManager.NUM_THREADS * 0 + 1, default_file_size, tc++));
		suite.addTest(new FileCopyManagerTests(tc.toString(), FileCopyManager.NUM_THREADS * 1 + 0, default_file_size, tc++));
		suite.addTest(new FileCopyManagerTests(tc.toString(), FileCopyManager.NUM_THREADS * 1 + 1, default_file_size, tc++));
		suite.addTest(new FileCopyManagerTests(tc.toString(), FileCopyManager.NUM_THREADS * 2 + 0, default_file_size, tc++));
		suite.addTest(new FileCopyManagerTests(tc.toString(), FileCopyManager.NUM_THREADS * 2 + 1, default_file_size, tc++));
		
		suite.addTest(new FileCopyManagerTests(tc.toString(), FileCopyManager.NUM_THREADS * 100, default_file_size, tc++));
		suite.addTest(new FileCopyManagerTests(tc.toString(), FileCopyManager.NUM_THREADS, FileCopier.BUFFER_SIZE * 2000, tc++))
		
		return suite;
	}
}
