package com.iinteractive.packager.beans;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;

import com.iinteractive.packager.beans.Merge;
import com.iinteractive.packager.beans.Package;

public class MergeTests {
	
	/*
	 * (exists, dne)
	 * (exists, exists)
	 * (dne, dne)
	 */
	@Test
	public void testValidateDestinationPackage() {
		boolean pass = true;
		
		Package p1 = new Package("C:\\JUnitMergeTestFolder\\exists");
		try {
			Merge.validateDestinationPackage(p1);
		} catch (Exception e) {
			pass = false;
		}
		
		Package p2 = new Package("C:\\JUnitMergeTestFolder\\dne");
		try {
			Merge.validateDestinationPackage(p2);
			pass = false;
		} catch (Exception e) {
		}
		
		Package p3 = new Package("C:\\dne");
		try {
			Merge.validateDestinationPackage(p3);
			pass = false;
		} catch (Exception e) {
		}
	}

	@Test
	public void testValidateSourcePackage() {
		boolean pass = true;
		
		Package p1 = new Package("C:\\JUnitMergeTestFolder\\exists");
		try {
			Merge.validateSourcePackage(p1);
		} catch (Exception e) {
			pass = false;
		}
		
		Package p2 = new Package("C:\\JUnitMergeTestFolder\\dne");
		try {
			Merge.validateSourcePackage(p2);
			pass = false;
		} catch (Exception e) {
		}
		
		Package p3 = new Package("C:\\dne");
		try {
			Merge.validateSourcePackage(p3);
			pass = false;
		} catch (Exception e) {
		}
		
		Assert.assertTrue(pass);
	}

}
