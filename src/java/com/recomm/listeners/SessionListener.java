package com.recomm.listeners;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import com.recomm.business.logging.beans.ErrorBean;
import com.recomm.model.domain.users.entity.Users;

/**
 * Web application lifecycle listener.
 *
 * @author Paul.Allen
 */
public class SessionListener implements ServletContextListener,
                                        HttpSessionAttributeListener,
                                        HttpSessionListener
{
    /**
     * The servlet context with which we are associated.
     */
    private ServletContext context = null;
    
/**
     * Record the fact that a servlet context attribute was added.
     *
     * @param event The session attribute event
     */
    @Override
    public void attributeAdded (HttpSessionBindingEvent event)
    {
        log ("attributeAdded('" + event.getSession().getId() + "', '" +
             event.getName() + "', '" + event.getValue() + "')");
    }


    /**
     * Record the fact that a servlet context attribute was removed.
     *
     * @param event The session attribute event
     */
    @Override
    public void attributeRemoved (HttpSessionBindingEvent event)
    {
        log ("attributeRemoved('" + event.getSession().getId() + "', '" +
             event.getName() + "', '" + event.getValue() + "')");
    }


    /**
     * Record the fact that a servlet context attribute was replaced.
     *
     * @param event The session attribute event
     */
    @Override
    public void attributeReplaced (HttpSessionBindingEvent event)
    {
        log ("attributeReplaced('" + event.getSession().getId() + "', '" +
             event.getName() + "', '" + event.getValue() + "')");
    }


    /**
     * Record the fact that this web application has been destroyed.
     *
     * @param event The servlet context event
     */
    @Override
    public void contextDestroyed (ServletContextEvent event)
    {
        log ("contextDestroyed()");
        this.context = null;
    }


    /**
     * Record the fact that this web application has been initialized.
     *
     * @param event The servlet context event
     */
    @Override
    public void contextInitialized (ServletContextEvent event)
    {
        this.context = event.getServletContext();
        log ("contextInitialized()");
    }


    /**
     * Initialize and record the fact that a session has been created.
     *
     * @param event The session event
     */
    @Override
    public void sessionCreated (HttpSessionEvent event)
    {
        boolean result = true;

        try
        {
            Context initialContext = new InitialContext();
            Context env = (Context) initialContext.lookup ("java:comp/env");

            if (env == null)
            {
                log ("Could not create env");
                result = false;
            }

            if (result)
            {
                if (result)
                {
                    ErrorBean errorMsg = new ErrorBean();
                    event.getSession().setAttribute ("errorMsg", errorMsg);
                    
                    Users user = new Users();
                    event.getSession().setAttribute ("userBean", user);
                    event.getSession().setMaxInactiveInterval (300);
                    log ("sessionCreated ('" + event.getSession().getId() + "')");
                }
                else
                {
                    log ("sessionCreated() FAILED!");
                }
            }
        }
        catch (NamingException ex)
        {
            log (ex.getMessage(), ex);
        }
    }


    /**
     * Record the fact that a session has been destroyed.
     *
     * @param event The session event
     */
    @Override
    public void sessionDestroyed (HttpSessionEvent event)
    {
        event.getSession().removeAttribute ("errorMsg");
        event.getSession().removeAttribute ("userBean");

        log ("sessionDestroyed ('" + event.getSession().getId() + "')");
    }


    // -------------------------------------------------------- Private Methods


    /**
     * Log a message to the servlet context application log.
     *
     * @param message Message to be logged
     */
    private void log (String message)
    {
        if (context != null)
        {
            context.log ("SessionListener: " + message);
        }
        else
        {
            System.out.println ("SessionListener: " + message);
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
            context.log ("SessionListener: " + message, throwable);
        }
        else
        {
            System.out.println ("SessionListener: " + message);
            throwable.printStackTrace (System.out);
        }
    }
}
