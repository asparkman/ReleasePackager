package com.iinteractive.utility;

import com.iinteractive.commandline.*;
import com.iinteractive.commandline.beans.*;
import com.iinteractive.packager.*;
import com.iinteractive.packager.beans.*;
import com.iinteractive.packager.processes.*;

import junit.framework.TestSuite;

public class AllTests {
	public static junit.framework.Test suite() {
		TestSuite suite = new TestSuite();
		
		suite.addTestSuite(CommandLinePropertyFactoryTests.class);
		
		suite.addTestSuite(CommandLinePropertyTests.class);
		suite.addTestSuite(CommandLinePropertyTypeTests.class);
		
		suite.addTest(DriverTests.suite());
		suite.addTestSuite(Log4jTests.class);
		
		suite.addTestSuite(OperationTests.class);
		suite.addTestSuite(PackageTests.class);
		suite.addTestSuite(SourceTests.class);
		
		suite.addTest(FileCopyManagerTests.suite());
		suite.addTestSuite(FolderCreationProcessTests.class);
		suite.addTest(PackageProcessTests.suite());
		suite.addTestSuite(ProcessFactoryTests.class);
		
		return suite;
	}
}
