package model.business.managers;

import com.recomm.business.managers.impl.InventoryManager;
import com.recomm.business.managers.interfaces.IInventoryManager;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class InventoryManagerTest
{
	private static IInventoryManager invManager;

	@BeforeAll
	static void setUpBeforeClass() throws Exception 
	{
		System.out.println ("\n\nTesting: com.recomm.model.business.managers.InventoryManagerTest:");
        ApplicationContext context = new ClassPathXmlApplicationContext (new String[] {"applicationContext.xml"});
        invManager = (IInventoryManager)context.getBean ("inventoryMgr");
	}

	@BeforeEach
	void setUp() throws Exception 
	{
	}

	@Test
	void testReadAll()
	{
		System.out.println ("\nStarting testReadAll:");
		
		assertNotNull (invManager.readAll ("item"), "InventoryManager.readRange() is NOT NULL FAILED");
		
		System.out.println ("\tInventoryManager.readAll() is NOT NULL PASSED");
	}

	@Test
	void testReadRange() 
	{
		System.out.println ("\nStarting testReadRange:");
		
		assertNull (invManager.readRange ("item", 0, 10), "InventoryManager.readRange() is NULL FAILED");
		
		System.out.println ("\tInventoryManager.readRange() is NULL PASSED");
	}

	@Test
	void testReadNext() 
	{
		System.out.println ("\nStarting testReadNext:");
		
		assertNull (invManager.readNext ("item"), "InventoryManager.readNext() is NULL FAILED");
		
		System.out.println ("\tInventoryManager.readNext() is NULL PASSED");
	}

	@Test
	void testGetInstance() 
	{
		System.out.println ("\nStarting testGetInstance:");
		
		assertTrue (invManager instanceof InventoryManager, "InventoryManager.getInstance() is ReCommManager FAILED");
		
		System.out.println ("\tInventoryManager.getInstance() is VALID PASSED");
	}
////
////	@Test
////	void testReadItems() 
////	{
////		System.out.println ("Not yet implemented"); // TODO
////	}
////
////	@Test
////	void testReadBrand() 
////	{
////		System.out.println ("Not yet implemented"); // TODO
////	}
////
////	@Test
////	void testReadPrices() 
////	{
////		System.out.println ("Not yet implemented"); // TODO
////	}
////
////	@Test
////	void testReadImages() 
////	{
////		System.out.println ("Not yet implemented"); // TODO
////	}

}
