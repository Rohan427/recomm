/*
 * Copyright (c) 2018 Random Logic Consulting and Paul G. Allen.
 * All Rights Reserved.
 */
package model.service.impl;

import model.domain.interfaces.IDomainObject;
import model.domain.inventory.Manufacturer;
import model.service.dao.HashedObjectWrapper;
import model.service.interfaces.IBrandAccessSvc;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import model.business.error.Logger;
import model.service.dao.HibernateSvc;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

/**
 *
 * @author Paul G. Allen <pgallen@gmail.com>
 */
@Service
public class BrandAccessSvcImpl extends HibernateSvc implements IBrandAccessSvc, Serializable
{
    private static final long serialVersionUID = 43L;
    private HashedObjectWrapper hashtable = null;

    public BrandAccessSvcImpl()
    {

    }

    @Override
    public boolean delete (String type)
    {
        boolean result = false;
        Transaction transaction;

        if (session == null)
        {
            loadService();
        }

        transaction = session.beginTransaction();
        session.createSQLQuery ("SET FOREIGN_KEY_CHECKS = 0").executeUpdate();
        session.createSQLQuery ("TRUNCATE Manufacturer").executeUpdate();
        session.createSQLQuery ("SET FOREIGN_KEY_CHECKS = 1").executeUpdate();

        transaction.commit();
        result = true;

        return result;
    }

    @Override
    public Collection<Manufacturer> find (IDomainObject object)
    {
        Transaction transaction;
        Manufacturer brand = (Manufacturer)object;
        Iterator userItr;
        Object[] userObject;
        Collection<Manufacturer> mfgs = new ArrayList<Manufacturer>();

        if (session == null)
        {
            loadService();
        }

        transaction = session.beginTransaction();
        object = (Manufacturer)session.get (Manufacturer.class, brand.getIdBrand());
        transaction.commit();

		return mfgs;
    }

    @Override
    public Manufacturer readBrand (Integer idBrand)
    {
        return (Manufacturer)find (new Manufacturer (idBrand));
    }

    @Override
    public Collection<Manufacturer> readBrands()
    {
        Collection<Manufacturer> brands = new ArrayList<Manufacturer>();
        Transaction transaction;
        Query query;
        Iterator<Manufacturer> iterator;

        if (session == null)
        {
            loadService();
        }

        transaction = session.beginTransaction();
        query = session.createQuery ("from Manufacturer");
        iterator = (Iterator<Manufacturer>)query.iterate();

        while (iterator.hasNext())
        {
            brands.add (iterator.next());
        }

        transaction.commit();

        return brands;
    }

    @Override
    public boolean removeBrand (Manufacturer brand)
    {
        return true;
    }

    @Override
    public boolean updateBrand (Manufacturer mfg, boolean isUpdate)
	{
		boolean result;
        Collection<Manufacturer> mfgs = new ArrayList<Manufacturer>();

        if (isUpdate)
        {
            // TODO: complete update code later
            result = true;
        }
        else
        {
            mfgs.add ((Manufacturer)mfg);
            result = merge (mfgs);
        }

        return result;
    }

    @Override
    public boolean persist (Collection<?> object)
	{
		boolean result = true;
        Transaction transaction = null;
        Manufacturer newMfg;
        Iterator iterator;
        Collection<Manufacturer> mfgs;

		if (object != null)//validate
		{
            mfgs = (Collection<Manufacturer>) object;
            initSession();

            if (session != null)
            {
                try
                {
                    transaction = session.beginTransaction();
                    iterator = mfgs.iterator();

                    while (iterator.hasNext() && result)
                    {
                        newMfg = (Manufacturer)iterator.next();
                        session.persist (newMfg);
                    }

                    transaction.commit();
                }
                catch (Exception e)
                {
                    result = false;

                    if (transaction != null)
                    {
                        try
                        {
                            Logger.log (BrandAccessSvcImpl.class, e);
                            transaction.rollback();
                        }
                        catch (Exception ex)
                        {
                            Logger.log (BrandAccessSvcImpl.class, "Rollback() failed");
                        }
                    }
                }
            }
            else
            {
                Logger.log (BrandAccessSvcImpl.class, "BrandAccessSvcImpl.persist failed with a NULL session.");
            }
		}
        else
        {
            Logger.log (BrandAccessSvcImpl.class, "BrandAccessSvcImpl.persist failed with a NULL object.");
            result = false;
        }

		return result;
    }

    @Override
    public boolean save (Collection<?> object)
    {
        boolean result = true;
        Transaction transaction = null;
        Manufacturer newMfg;
        Iterator iterator;
        Collection<Manufacturer> mfgs;

		if (object != null)//validate
		{
            mfgs = (Collection<Manufacturer>) object;
            initSession();

            if (session != null)
            {
                try
                {
                    transaction = session.beginTransaction();
                    iterator = mfgs.iterator();

                    while (iterator.hasNext() && result)
                    {
                        newMfg = (Manufacturer)iterator.next();
                        session.save (newMfg);
                    }

                    transaction.commit();
                }
                catch (Exception e)
                {
                    result = false;

                    if (transaction != null)
                    {
                        try
                        {
                            Logger.log (BrandAccessSvcImpl.class, e);
                            transaction.rollback();
                        }
                        catch (Exception ex)
                        {
                            Logger.log (BrandAccessSvcImpl.class, "Rollback() failed");
                        }
                    }
                }
            }
            else
            {
                Logger.log (BrandAccessSvcImpl.class, "BrandAccessSvcImpl.save failed with a NULL session.");
                result = false;
            }
		}
        else
        {
            Logger.log (BrandAccessSvcImpl.class, "BrandAccessSvcImpl.persist failed with a NULL object.");
            result = false;
        }

		return result;
    }

    @Override
    public boolean merge (Collection<?> object)
    {
        boolean result = true;
        Transaction transaction;
        Manufacturer newMfg;
        Iterator iterator;
        Collection<Manufacturer> mfgs;

        if (object != null)//validate
        {
            mfgs = (Collection<Manufacturer>) object;
            initSession();

            if (session != null)
            {
                try
                {
                    transaction = session.beginTransaction();
                    iterator = mfgs.iterator();

                    while (iterator.hasNext() && result)
                    {
                        newMfg = (Manufacturer)iterator.next();
                        session.merge (newMfg);
                    }

                    transaction.commit();
                }
                catch (Exception e)
                {
                    Logger.log (BrandAccessSvcImpl.class, e);
                }
            }
            else
            {
                Logger.log (BrandAccessSvcImpl.class, "BrandAccessSvcImpl.merge failed with a NULL session.");
                result = false;
            }
        }
        else
        {
            Logger.log (BrandAccessSvcImpl.class, "BrandAccessSvcImpl.merge failed with a NULL object.");
            result = false;
        }

        return result;
    }
}
