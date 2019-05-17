/**
 * 
 */
package model.domain.inventory;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * @author pgallen
 *
 */
public class ItemsTest 
{
	Items i1 = new Items (1,
		 	              "partNo",
		 	              "name",
		 	              "description",
		 	               "upc",
		 	              2,
		 	              3,
			              BigDecimal.valueOf(10.5),
			              BigDecimal.valueOf(9.5),
			              "pl",
			              6,
			              5,
			              new Date ("10/12/2014"),
			              "hazCode",
			              new Date ("10/12/2014")
			             );
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	public static void setUpBeforeClass() throws Exception
	{
		System.out.println ("\n\nTesting: com.recomm.model.domain.inventory.Items:");
	}

	/**
	 * Test method for {@link com.recomm.model.domain.inventory.Items#validate()}.
	 */
	@Test
	public void testValidate()
	{		
		System.out.println ("\nStarting testValidate:");
		
		assertTrue (i1.validate(), "i1 is VALID FAILED");
		System.out.println ("\ti1 is VALID PASSED");
	}
	
	@Test
	public void testNotValid()
	{
		i1.setIdItems(null);
		
		System.out.println ("\nStarting testNotValid:");
		
		assertFalse (i1.validate(), "i1 is NOT valid FAILED");
		System.out.println ("\ti1 is NOT valid PASSED");
	}

	/**
	 * Test method for {@link com.recomm.model.domain.inventory.Items#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsObject()
	{
		System.out.println ("\nStarting testEqualsObject:");
		
		Items c1 = new Items (1);
		Items c2 = new Items (1);
		
		assertTrue (c1.equals (c2), "test c1 == c2 FAILED");
		System.out.println ("\ttest c1 == c2 PASSED");
	}
	
	@Test
	public void testNotEqualsObject()
	{
		System.out.println ("\nStarting testNotEqualsObject:");
		
		Items c1 = new Items (1);
		Items c2 = new Items (2);
		
		assertFalse (c1.equals (c2), "test c1 != c2 FAILED");
		System.out.println ("\ttest c1 != c2 PASSED");
	}
}
