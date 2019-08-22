/*
 * Copyright (c) 2018 Random Logic Consulting and Paul G. Allen.
 * All Rights Reserved.
 */
package com.recomm.model.service.impl;

import com.recomm.business.logging.Logger;
import com.recomm.model.service.interfaces.IDAOService;
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
    protected static SessionFactory userfactory = null;
    protected static Session usersession = null;
    protected static SessionFactory invfactory = null;
    protected static Session invsession = null;
    private static boolean failed = false;
////    private final static Logger log = LogManager.getLogger (HibernateDccSvc.class.getName());

    static
    {
        configure ("both");
    }

    @Override
    public void loadService (String session)
    {
        HibernateSvc.configure (session);
    }

    private static void configure (String session)
    {
        // Create the SessionFactory from standard (hibernate.cfg.xml)
        // config file.
        if (userfactory == null)
        {
            buildSessionFactory();
        }
        // else do nothing

        if (session.equals ("user") || session.equals ("both"))
        {
            if (userfactory != null)
            {
                if (usersession != null)
                {
                    try
                    {
                        usersession.close();
                    }
                    catch (HibernateException ex)
                    {
                        failed = true;
                        Logger.log (HibernateSvc.class, ex);
                    }
                }
                // else do nothing

                if (!failed)
                {
                    try
                    {
                        usersession = userfactory.openSession();
                        usersession.setHibernateFlushMode (FlushMode.ALWAYS);
                    }
                    catch (HibernateException ex)
                    {
                        // Log the exception.
                        Logger.log (HibernateSvc.class, ex, "SEVERE: Initial  User Session creation failed: ");
                        throw new ExceptionInInitializerError (ex);
                    }
                }
            }
            else
            {
                Logger.log (HibernateSvc.class, "SEVERE: Initial Hibernate configuration failed: ");
                throw new ExceptionInInitializerError (new Exception ("Failed to create Hibernate User Factory"));
            }
        }

        if (session.equals ("inv") || session.equals ("both"))
        {
            if (!failed)
            {
                if (invfactory != null)
                {
                    if (invsession != null)
                    {
                        try
                        {
                            invsession.close();
                        }
                        catch (HibernateException ex)
                        {
                            failed = true;
                            Logger.log (HibernateSvc.class, ex);
                        }
                    }

                    try
                    {
                        invsession = invfactory.openSession();
                        invsession.setHibernateFlushMode (FlushMode.ALWAYS);
                    }
                    catch (HibernateException ex)
                    {
                        // Log the exception.
                        Logger.log (HibernateSvc.class, ex, "SEVERE: Initial Inv Session creation failed: ");
                        throw new ExceptionInInitializerError (ex);
                    }
                }
            }
            else
            {
                Logger.log (HibernateSvc.class, "SEVERE: Initial Hibernate configuration failed: ");
                throw new ExceptionInInitializerError (new Exception ("Failed to create Hibernate Inv Factory"));
            }
        }
    }

    private static void buildSessionFactory()
    {
        try
        {
            // Create the SessionFactory from hibernate.cfg.xml
            userfactory = new Configuration().configure ("hibernate.cfg.xml").buildSessionFactory();
            invfactory = new Configuration().configure ("recomm.hibernate.cfg.xml").buildSessionFactory();
        }
        catch (HibernateException ex)
        {
            // Make sure you log the exception, as it might be swallowed
            Logger.log (HibernateSvc.class, ex, "SEVERE: Initial SessionFactory creation failed: ");
        }
    }

    @Override
    public SessionFactory getUserFactory()
    {
        return userfactory;
    }

    @Override
    public SessionFactory getInvFactory()
    {
        return invfactory;
    }

    @Override
    public void initUserSession()
    {
        if (usersession == null)
        {
            loadService ("user");
        }
        else
        {
            if (!usersession.isOpen())
            {
                loadService ("user");
            }
            // else do nothing
        }

        if (usersession == null)
        {
            Logger.log (HibernateSvc.class, "Failed to open a User session.");
        }
        // else do nothing
    }

    @Override
    public void initInvSession()
    {
        if (invsession == null)
        {
            loadService ("inv");
        }
        else
        {
            if (!invsession.isOpen())
            {
                loadService ("inv");
            }
            // else do nothing
        }

        if (invsession == null)
        {
            Logger.log (HibernateSvc.class, "Failed to open a Inv session.");
        }
        // else do nothing
    }

    @Override
    public Session getUserSession()
    {
        return usersession;
    }

    @Override
    public Session getInvSession()
    {
        return invsession;
    }

    @Override
    protected void finalize() throws Throwable
    {
        try
        {
            usersession.flush();
            usersession.close();
        }
        catch (Exception e)
        {

        }
        finally
        {
            try
            {
                invsession.flush();
                invsession.close();
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
}
