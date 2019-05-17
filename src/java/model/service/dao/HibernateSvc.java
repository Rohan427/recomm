/*
 * Copyright (c) 2018 Random Logic Consulting and Paul G. Allen.
 * All Rights Reserved.
 */
package model.service.dao;

import model.service.interfaces.IDAOService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
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
    private final static Logger log = LogManager.getLogger (HibernateSvc.class.getName());
        
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
        try
        {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            if (factory == null)
            {
                factory = new Configuration().configure().buildSessionFactory();
            }
            
            if (session == null)
            {
                session = factory.openSession();
            }
        }
        catch (HibernateException ex)
        {
            // Log the exception. 
            log.error ("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError (ex);
        }
    }
    
    @Override
    public SessionFactory getFactory()
    {
        return factory;
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
            session.close();
        }
        catch (HibernateException e)
        {
            
        }
        finally
        {
            super.finalize();
        }
    }
}
