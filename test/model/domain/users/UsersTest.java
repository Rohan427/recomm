package model.domain.users;

import com.recomm.model.domain.users.entity.Users;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class UsersTest 
{
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	public static void setUpBeforeClass() throws Exception
	{
		System.out.println ("\n\nTesting: com.recomm.model.domain.users.Users:");
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testValidate()
	{
		//TODO: Fix deprecated Date method call
		Users c1 = new Users (1, 
			                  "firstName", 
			                  "lName",
			                  "userName",
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
		Users c1 = new Users (null, 
				               "firstName", 
				               "lName", 
				               "userName",
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
		
		Users c1 = new Users (1);
		Users c2 = new Users (1);
		
		assertTrue (c1.equals (c2), "test c1 == c2 FAILED");
		System.out.println ("\ttest c1 == c2 PASSED");
	}
	
	@Test
	public void testNotEqualsObject()
	{
		System.out.println ("\nStarting testNotEqualsObject:");
		
		Users c1 = new Users (1);
		Users c2 = new Users (2);
		
		assertFalse (c1.equals (c2), "test c1 != c2 FAILED");
		System.out.println ("\ttest c1 != c2 PASSED");
	}
}
