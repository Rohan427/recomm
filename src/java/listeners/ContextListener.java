package listeners;

import model.business.config.Config;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author Paul.Allen
 */
public class ContextListener implements ServletContextAttributeListener, ServletContextListener
{
    /**
     * The servlet context with which we are associated.
     */
    private ServletContext context = null;
    private Config cfg = null;
    
    /**
     * Record the fact that a servlet context attribute was added.
     *
     * @param event The servlet context attribute event
     */
    @Override
    public void attributeAdded (ServletContextAttributeEvent event)
    {
        log ("attributeAdded ('" + event.getName() + "', '" +
             event.getValue() + "')");
    }

    /**
     * Record the fact that a servlet context attribute was removed.
     *
     * @param event The servlet context attribute event
     */
    @Override
    public void attributeRemoved (ServletContextAttributeEvent event)
    {
        log ("attributeRemoved ('" + event.getName() + "', '" +
             event.getValue() + "')");
    }

    /**
     * Record the fact that a servlet context attribute was replaced.
     *
     * @param event The servlet context attribute event
     */
    @Override
    public void attributeReplaced (ServletContextAttributeEvent event)
    {
        log ("attributeReplaced ('" + event.getName() + "', '" +
             event.getValue() + "')");
    }

    @Override
    public void contextInitialized (ServletContextEvent event)
    {
        context = event.getServletContext();
        cfg = new Config (context);
////        manager = ReCommManager.getInstance();
        context.setAttribute ("cfg", cfg);
////        context.setAttribute ("manager", manager);
        log ("contextInitialized()");
    }

    @Override
    public void contextDestroyed (ServletContextEvent sce)
    {
    }
    
    /**
     * Log a message to the servlet context application log.
     *
     * @param message Message to be logged
     */
    private void log (String message)
    {
        if (context != null)
        {
            context.log ("ContextListener: " + message);
        }
        else
        {
            System.out.println ("ContextListener: " + message);
        }

    }

    /**
     * Log a message and associated exception to the servlet context
     * application log.
     *
     * @param message Message to be logged
     * @param throwable Exception to be logged
     */
    private void log (String message, Throwable throwable)
    {
        if (context != null)
        {
            context.log ("ContextListener: " + message, throwable);
        }
        else
        {
            System.out.println ("ContextListener: " + message);
            throwable.printStackTrace (System.out);
        }
    }
}
