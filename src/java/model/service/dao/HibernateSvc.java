/*
 * Copyright (c) 2018 Random Logic Consulting and Paul G. Allen.
 * All Rights Reserved.
 */
package model.service.dao;

import model.business.error.Logger;
import model.service.interfaces.IDAOService;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Paul G. Allen <pgallen@gmail.com>
 */
public abstract class HibernateSvc implements IDAOService
{
    protected static SessionFactory factory = null;
    protected static Session session = null;
////    private final static Logger log = LogManager.getLogger (HibernateDccSvc.class.getName());

    static
    {
        configure();
    }

    @Override
    public void loadService()
    {
        HibernateSvc.configure();
    }

    private static void configure()
    {
        // Create the SessionFactory from standard (hibernate.cfg.xml)
        // config file.
        if (factory == null)
        {
            factory = buildSessionFactory();
        }
        // else do nothing

        if (factory != null)
        {
            if (session != null)
            {
                try
                {
                    session.close();
                }
                catch (HibernateException ex)
                {
                    Logger.log (HibernateSvc.class, ex);
                }
            }
            // else do nothing

            try
            {
                session = factory.openSession();
                session.setHibernateFlushMode (FlushMode.ALWAYS);
            }
            catch (HibernateException ex)
            {
                // Log the exception.
                Logger.log (HibernateSvc.class, ex, "SEVERE: Initial Session creation failed: ");
                throw new ExceptionInInitializerError (ex);
            }
        }
        else
        {
            //TODO: Handle the null factory condition
        }
    }

    private static SessionFactory buildSessionFactory()
    {
        SessionFactory lFactory = null;

        try
        {
            // Create the SessionFactory from hibernate.cfg.xml
            lFactory = new Configuration().configure ("hibernate.cfg.xml").buildSessionFactory();
        }
        catch (HibernateException ex)
        {
            // Make sure you log the exception, as it might be swallowed
            Logger.log (HibernateSvc.class, ex, "SEVERE: Initial SessionFactory creation failed: ");
        }

        return lFactory;
    }

    @Override
    public SessionFactory getFactory()
    {
        return factory;
    }

    @Override
    public void initSession()
    {
        if (session == null)
        {
            loadService();
        }
        else
        {
            if (!session.isOpen())
            {
                loadService();
            }
            // else do nothing
        }

        if (session == null)
        {
            Logger.log (HibernateSvc.class, "Failed to open a session.");
        }
        // else do nothing
    }

    @Override
    public Session getSession()
    {
        return session;
    }

    @Override
    protected void finalize() throws Throwable
    {
        try
        {
            session.flush();
            session.close();
        }
        catch (Exception e)
        {

        }
        finally
        {
            super.finalize();
        }
    }
}
