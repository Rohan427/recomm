package model.domain.users;

import com.recomm.model.domain.users.entity.Customer;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CustomerTest 
{
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	public static void setUpBeforeClass() throws Exception
	{
		System.out.println ("\n\nTesting: com.recomm.model.domain.users.Customer:");
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testValidate()
	{
		//TODO: Fix deprecated Date method call
		Customer c1 = new Customer (1, 
					                "firstName", 
					                "lName", 
					                "address1", 
					                "address2", 
					                "city", 
					                "state", 
					                "country", 
					                "zipCode",
					                new Date ("10/12/2014")
					               );
		
		System.out.println ("\nStarting testValidate:");
		
		assertTrue (c1.validate(), "c1 is VALID FAILED");
		System.out.println ("\tc1 is VALID PASSED");
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testNotValid()
	{
		//TODO: Fix deprecated Date method call
		Customer c1 = new Customer (null, 
					                "firstName", 
					                "lName", 
					                "address1", 
					                "address2", 
					                "city", 
					                "state", 
					                "country", 
					                "zipCode",
					                new Date ("10/12/2014")
					               );
		
		System.out.println ("\nStarting testNotValid:");
		
		assertFalse (c1.validate(), "c1 is NOT valid FAILED");
		System.out.println ("\tc1 is NOT valid PASSED");
	}

	@Test
	public void testEqualsObject()
	{
		System.out.println ("\nStarting testEqualsObject:");
		
		Customer c1 = new Customer (1);
		Customer c2 = new Customer (1);
		
		assertTrue (c1.equals (c2), "test c1 == c2 FAILED");
		System.out.println ("\ttest c1 == c2 PASSED");
	}
	
	@Test
	public void testNotEqualsObject()
	{
		System.out.println ("\nStarting testNotEqualsObject:");
		
		Customer c1 = new Customer (1);
		Customer c2 = new Customer (2);
		
		assertFalse (c1.equals (c2), "test c1 != c2 FAILED");
		System.out.println ("\ttest c1 != c2 PASSED");
	}
}
