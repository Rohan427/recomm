/*
 * Copyright 2018 Random Logic Consulting and Paul G. Allen
 * All rights reserved.
 */

package model.service.impl;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.domain.inventory.Manufacturer;
import model.service.dao.HashedObjectWrapper;
import model.domain.interfaces.IDomainObject;
import java.security.SecureRandom;
import java.util.Date;
import utility.RandomString;

public class BrandAccessSvcImplTest
{
	private BrandAccessSvcImpl brandSvc;
	private Manufacturer brand;
	private HashedObjectWrapper hashtable;
	
	@BeforeAll
	public static void setUpBeforeClass() throws Exception 
	{
		System.out.println ("\n\nTesting: com.recomm.model.service.impl.BrandAccessSvcImplTest:");
	}

	@BeforeEach
	public void setUp() throws Exception 
	{
		brandSvc = new BrandAccessSvcImpl();
        
        String easy = RandomString.digits + "ACEFGHJKLMNPQRUVWXYabcdefhijkprstuvwx";
        RandomString name = new RandomString (20, new SecureRandom(), easy);
        
		brand = new Manufacturer (1, 
                            name.nextString(), 
                            name.nextString(),
                            //TODO: Remove deprecated method
                            new Date ("12/1/2018"), // TODO: replace deprecated
                            1
                           );
	}

	@Test
	public void testUpdateBrand() 
	{
		System.out.println ("\nStarting testUpdateBrand:");
		
		assertTrue (brandSvc.updateBrand (brand, true), "brandSvc.updateBrand is VALID FAILED");
		
		System.out.println ("\tbrandSvc.updateBrand is VALID PASSED");
	}

	@Test
	public void testReadBrand() 
	{
		System.out.println ("\nStarting testReadBrand:");
		brand = null;
		brand = brandSvc.readBrand (1);
		
		assertTrue ((brand != null) && (brand.getIdBrand() == 1), "brandSvc.testReadBrand is VALID FAILED");
		
		System.out.println ("\tbrandSvc.testReadBrand is VALID PASSED");
	}

	@Test
	public void testRemoveBrand() 
	{
		System.out.println ("\nStarting testRemoveBrand:");
		
		assertTrue (brandSvc.removeBrand (brand), "brandSvc.testRemoveBrand is VALID FAILED");
		
		System.out.println ("\tbrandSvc.testRemoveBrand is VALID PASSED");
	}
	
	@Test
	public void testAddObjectToHashtable() 
	{
		System.out.println ("\nStarting testAddObjectToHashtable:");
		
		assertTrue (brandSvc.addObjectToHashtable (brand), "brandSvc.testAddObjectToHashtable is VALID FAILED");
		
		System.out.println ("\tbrandSvc.testAddObjectToHashtable is VALID PASSED");
	}

	@Test
	public void testGetHashtable() 
	{
		System.out.println ("\nStarting testGetHashtable:");
		
		assertTrue (brandSvc.addObjectToHashtable (brand), "brandSvc.testAddObjectToHashtable is VALID FAILED");
		assertTrue (brandSvc.getHashtable () instanceof HashedObjectWrapper, "brandSvc.testGetHashtable is VALID FAILED");
		
		System.out.println ("\tbrandSvc.testGetHashtable is VALID PASSED");
	}

	@Test
	public void testMerge() 
	{
		System.out.println ("\nStarting testMerge:");
		
        // TODO: Why does this fail every time
////		assertAll ("persist",
////					() -> assertTrue (brandSvc.addObjectToHashtable (brand), "brandSvc.testAddObjectToHashtable is VALID FAILED"),
////					() -> assertTrue ((hashtable = brandSvc.getHashtable ()) instanceof HashedObjectWrapper, "brandSvc.testGetHashtable is VALID FAILED"),
////					() -> assertTrue (brandSvc.merge (hashtable), "brandSvc.testMerge is VALID FAILED")
////				  );
        
        assertTrue (brandSvc.addObjectToHashtable (brand), "brandSvc.testAddObjectToHashtable is VALID FAILED");
		assertTrue ((hashtable = brandSvc.getHashtable ()) instanceof HashedObjectWrapper, "brandSvc.testGetHashtable is VALID FAILED");
		assertTrue (brandSvc.merge (hashtable), "brandSvc.testMerge is VALID FAILED");
		
		System.out.println ("\tbrandSvc.testMerge is VALID PASSED");
	}
    
    @Test
	public void testSave() 
	{
		System.out.println ("\nStarting testSave:");
        
        assertTrue (brandSvc.addObjectToHashtable (brand), "brandSvc.testAddObjectToHashtable is VALID FAILED");
		assertTrue ((hashtable = brandSvc.getHashtable ()) instanceof HashedObjectWrapper, "brandSvc.testGetHashtable is VALID FAILED");
		assertTrue (brandSvc.save (hashtable), "brandSvc.testSave is VALID FAILED");
		
		System.out.println ("\tbrandSvc.testSave is VALID PASSED");
	}

	@Test
	public void testFind() 
	{		
		System.out.println ("\nStarting testFind:");
		
		assertTrue (brandSvc.find (brand) instanceof IDomainObject
				    && brandSvc.find (brand) instanceof Manufacturer, 
				    "brandSvc.testFind is VALID FAILED"
				   );
		
		System.out.println ("\tbrandSvc.testFind is VALID PASSED");
	}
}
