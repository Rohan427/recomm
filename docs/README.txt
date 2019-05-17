Registration and login is now based upon the database. An empty DB will have no valid user. 
Session will begin with a Guest account.


Week 6 Changes:

Added application filters as follows. See the updated web.xml file for filter declarations and mappings.

 - Added "filters" package
   
   Note that these will probably be replaced at a later time with Spring Interceptors.

   - Added Security filter (filters.Security.java)

     This filter currently does nothing. It will handle some of the security processing for the
     application. It is the first filter in the filter chan

   - Added Tracking filter (filters.Tracking.java)

     This filter logs the client IP and the requested URL to the server log. If debugging is
     enabled for the filter, it also prints it to System.out. This is the second filter in the
     chain.

   - Added Compression filter (filters.Compression.java)

     This filter compresses the response using GZIP if the client browser supports compression.
     It also compresses the main CSS file. Note that if any other filters try to do final
     processing AFTER this filter runs, an exception will occur. The respone can not be changed
     after this filter is run. This filter is mapped to all current JSF files (not their templates).
     This is the final filter in the chain.



Week 5 Changes:

 - Added Spring 4.01 support integrated with JSF.

   - Updated associated libraries (Spring and JSF) to support 4.01 vs older 3.1.

   - Removed model.service.factory package. No longer needed with Spring IoC.

   - Added faces-config.xml for initializing Spring EL resolver.

   - Removed context parameters for domain services from web.xml. These are supported through Spring.

   - Updated model.business.managers.InventoryManager to use Spring IoC annotations.

   - Added model.business.managers.beans.LoginBean to replace Authenticate. Authenticate is now
     model.business.managers.AthenticateManager and operates as a Spring service for LoginBean.

   - All domain service implementations (model.domain.serice.impl.*) are Spring services injected
     into InventoryManager.

   - LoginBean and InventoryManager are both managed beans with Session scope and managed by Spring
     and JSF.

   - Changed model.service.dao.HibernateSvc to be extended by the service implementation classes
     so that it properly initializes when instantiated during the service class instantiations.

   - Modified all test cases to reflect all of the above changes. (Note: Mock Servlet session and 
     context not yet implemented for tests that requre them.)

   - Updated login.xhtml and its templates to use #{loginBean.*} vs previous #{authenticate.*}.
     Also updated corresponding result pages and templates as well as logout.xhtml.


Week 4 changes

 - Removed JSP and added JSF.

 - Registration and login now works with the database.

 - Added JUnit/Jupiter tests. Need to configure mock servlet configuration for tests.

 - Added stub pages for Inventory management.

 - Removed Authenticate servlet and added methods to a managed bean. Faces servlet now controls views. 

 - Added Hibernate support for Customer database (user registration/login support). 
   (user root, password root)

 - Added SQL creation script for databases.

 - Added service callsses for domain objects.

 - Added domain objects for basic Inventory and User support.

 - Added context parameters for loading domain service implementation classes through service factory.

 - Added service factory for loading domain service implementation classes.


Week 3 changes

 - Added standard error page created dynamically using an error object and with an error handling servlet.
   Error object is stored in the session for continued use during the session as needed.

 - All pages are now JSP's.

 - Login menu changes to Logout when a user is logged in.

 - Added Session and Context listeners. Session listener creates the session object and logs it when 
   the session is created. Context listener does not yet implement what it is intended to in later 
   versions.

 - Added a check for a valid session object. The session timeout is short - 30 sec. - for testing.

 - User session is tracked through a separate userBean object stored at session scope (aside from the
   server tracked timeout interval. this bean will be used later for further session security (once a
   database is implemented).





























