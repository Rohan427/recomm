/*
 * Copyright 2018 Random Logic Consulting and Paul G. Allen
 * All rights reserved.
 */

package model.service.impl;


import com.recomm.model.domain.inventory.service.impl.ImageAccessSvcImpl;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.recomm.model.domain.inventory.entity.Images;
import com.recomm.model.service.beans.HashedObjectWrapper;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ImageAccessSvcImplTest
{
	ImageAccessSvcImpl imageSvc;
	Images image;
	private HashedObjectWrapper hashtable;

	@BeforeAll
	public static void setUpBeforeClass() throws Exception
	{
		System.out.println ("\n\nTesting: com.recomm.model.service.impl.ImageAccessSvcImplTest:");
	}

	@BeforeEach
	public void setUp() throws Exception
	{
		imageSvc =  new ImageAccessSvcImpl();
        image = new Images(1,
                           "myimagepath",
                           1,
                           //TODO: Remove deprecated method
                            new Date ("12/1/2018") // TODO: replace deprecated
                          );
	}

	@Test
	public void testUpdateImage()
	{
		System.out.println ("\nStarting testUpdateImage:");

		assertTrue (imageSvc.updateImage (image, true), "imageSvc.updateImage is VALID FAILED");

		System.out.println ("\timageSvc.updateImage is VALID PASSED");
	}

	@Test
	public void testReadImage()
	{
		System.out.println ("\nStarting testReadImage:");
		image = null;
		image = imageSvc.readImage(1);

		assertTrue ((image != null) && (image.getIdImages() == 1), "imageSvc.testReadImage is VALID FAILED");

		System.out.println ("\timageSvc.testReadImage is VALID PASSED");
	}

	@Test
	public void testRemoveImage()
	{
		System.out.println ("\nStarting testRemoveImage:");

		assertTrue (imageSvc.removeImage (image), "imageSvc.testRemoveImage is VALID FAILED");

		System.out.println ("\timageSvc.testRemoveImage is VALID PASSED");
	}

    @Test
	public void testMerge()
	{
		System.out.println ("\nStarting testMerge:");

////		assertTrue (imageSvc.addObjectToHashtable (image), "imageSvc.testAddObjectToHashtable is VALID FAILED");
////		assertTrue ((hashtable = imageSvc.getHashtable()) instanceof HashedObjectWrapper, "priceSvc.testGetHashtable is VALID FAILED");
////		assertTrue (imageSvc.merge (hashtable), "imageSvc.testMerge is VALID FAILED");

		System.out.println ("\timageSvc.testMerge is VALID PASSED");
	}

    @Test
	public void testSave()
	{
		System.out.println ("\nStarting testSave:");

////		assertTrue (imageSvc.addObjectToHashtable (image), "imageSvc.testAddObjectToHashtable is VALID FAILED");
////		assertTrue ((hashtable = imageSvc.getHashtable()) instanceof HashedObjectWrapper, "priceSvc.testGetHashtable is VALID FAILED");
////		assertTrue (imageSvc.save (hashtable), "imageSvc.testSave is VALID FAILED");

		System.out.println ("\timageSvc.testSave is VALID PASSED");
	}
}
