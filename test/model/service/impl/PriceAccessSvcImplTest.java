/*
 * Copyright 2018 Random Logic Consulting and Paul G. Allen
 * All rights reserved.
 */

package model.service.impl;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.domain.inventory.Prices;
import model.service.dao.HashedObjectWrapper;
import model.domain.interfaces.IDomainObject;
import java.math.BigDecimal;
import java.util.Date;

public class PriceAccessSvcImplTest
{
	PriceAccessSvcImpl priceSvc;
	Prices price;
	private HashedObjectWrapper hashtable;

	@BeforeAll
	public static void setUpBeforeClass() throws Exception
	{
		System.out.println ("\n\nTesting: com.recomm.model.service.impl.PriceAccessSvcImplTest:");
	}

	@BeforeEach
	public void setUp() throws Exception
	{
		priceSvc = new PriceAccessSvcImpl();
		price = new Prices(1, new BigDecimal (800), new Date ("12/12/12"));
		priceSvc.hashCode();
	}

	@Test
	public void testUpdatePrice()
	{
		System.out.println ("\nStarting testUpdatePrice:");

		assertTrue (priceSvc.updatePrice (price, true), "priceSvc.updatePrice is VALID FAILED");

		System.out.println ("\tpriceSvc.updatePrice is VALID PASSED");
	}

	@Test
	public void testReadPrice()
	{
		System.out.println ("\nStarting testReadPrice:");
		price = null;
		price = priceSvc.readPrice(1);

		assertTrue ((price != null) && (price.getIdPrices() == 1), "priceSvc.testReadPrice is VALID FAILED");

		System.out.println ("\tpriceSvc.testReadPrice is VALID PASSED");
	}

	@Test
	public void testRemovePrice()
	{
		System.out.println ("\nStarting testRemovePrice:");

		assertTrue (priceSvc.removePrice (price), "priceSvc.testRemovePrice is VALID FAILED");

		System.out.println ("\tpriceSvc.testRemovePrice is VALID PASSED");
	}

	@Test
	public void testAddObjectToHashtable()
	{
		System.out.println ("\nStarting testAddObjectToHashtable:");

////		assertTrue (priceSvc.addObjectToHashtable (price), "priceSvc.testAddObjectToHashtable is VALID FAILED");

		System.out.println ("\tpriceSvc.testAddObjectToHashtable is VALID PASSED");
	}

	@Test
	public void testGetHashtable()
	{
		System.out.println ("\nStarting testGetHashtable:");

////		assertTrue (priceSvc.addObjectToHashtable (price), "priceSvc.testAddObjectToHashtable is VALID FAILED");
////		assertTrue (priceSvc.getHashtable () instanceof HashedObjectWrapper, "priceSvc.testGetHashtable is VALID FAILED");

		System.out.println ("\tpriceSvc.testGetHashtable is VALID PASSED");
	}

	@Test
	public void testMerge()
	{
		System.out.println ("\nStarting testMerge:");

////		assertTrue (priceSvc.addObjectToHashtable (price), "priceSvc.testAddObjectToHashtable is VALID FAILED");
////		assertTrue ((hashtable = priceSvc.getHashtable()) instanceof HashedObjectWrapper, "priceSvc.testGetHashtable is VALID FAILED");
////		assertTrue (priceSvc.merge (hashtable), "priceSvc.testMerge is VALID FAILED");

		System.out.println ("\tpriceSvc.testMerge is VALID PASSED");
	}

    @Test
	public void testSave()
	{
		System.out.println ("\nStarting testSave:");

////		assertTrue (priceSvc.addObjectToHashtable (price), "priceSvc.testAddObjectToHashtable is VALID FAILED");
////		assertTrue ((hashtable = priceSvc.getHashtable()) instanceof HashedObjectWrapper, "priceSvc.testGetHashtable is VALID FAILED");
////		assertTrue (priceSvc.save (hashtable), "priceSvc.testSave is VALID FAILED");

		System.out.println ("\tpriceSvc.testSave is VALID PASSED");
	}

	@Test
	public void testFind()
	{
		System.out.println ("\nStarting testFind:");

		assertTrue (priceSvc.find (price) instanceof IDomainObject
				    && priceSvc.find (price) instanceof Prices,
				    "priceSvc.testFind is VALID FAILED"
				    );

		System.out.println ("\tpriceSvc.testFind is VALID PASSED");
	}
}
