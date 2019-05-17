import org.junit.platform.suite.api.SelectClasses;
//import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith (JUnitPlatform.class)
@SelectClasses({
				model.business.managers.InventoryManagerTest.class,
			    model.domain.inventory.ItemsTest.class,
			    model.domain.users.AddressTest.class,
 			    model.domain.users.CustomerTest.class,
			    model.domain.users.UsersTest.class,
			    model.service.impl.ItemAccessSvcImplTest.class,
			    model.service.impl.ImageAccessSvcImplTest.class,
			    model.service.impl.BrandAccessSvcImplTest.class,
			    model.service.impl.PriceAccessSvcImplTest.class
			   }
			  )

// For use when JUnit 4 no longer needed to run Jupiier tests and we can run sets of packages
//@SelectPackages({"com.recomm.model.business.main",
//	   "com.recomm.model.domain.inventory",
//	   "com.recomm.model.domain.users",
//	   "com.recomm.model.domain.users",
//	   "com.recomm.model.domain.users",
//	   "com.recomm.model.service.factory",
//	   "com.recomm.model.service.impl",
//	   "com.recomm.model.service.impl",
//	   "com.recomm.model.service.impl",
//	   "com.recomm.model.service.impl",
//	   "com.recomm.model.service.dao"})
public class AllTests 
{

}