/*
 * Copyright (c) 2018 Random Logic Consulting and Paul G. Allen.
 * All Rights Reserved.
 */
package com.recomm.model.domain.inventory.service.impl;

import com.recomm.model.domain.interfaces.IDomainObject;
import com.recomm.model.domain.inventory.entity.Manufacturer;
import com.recomm.model.domain.inventory.service.interfaces.IBrandAccessSvc;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import com.recomm.business.logging.Logger;
import com.recomm.model.domain.interfaces.ISearchParms;
import com.recomm.model.domain.inventory.util.MfgSearchParams;
import com.recomm.model.service.impl.HibernateSvc;
import com.recomm.model.domain.application.service.impl.LogsAccessSvcImpl;
import com.recomm.model.domain.inventory.interfaces.IBrand;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Service;

/**
 *
 * @author Paul G. Allen <pgallen@gmail.com>
 */
@Service
public class BrandAccessSvcImpl extends HibernateSvc implements IBrandAccessSvc, Serializable
{
    private static final long serialVersionUID = 43L;

    @Override
    public Collection<IBrand> readBrands()
    {
        Collection<IBrand> brands = new ArrayList<IBrand>();
        Transaction transaction = null;
        Query query;
        Iterator<IBrand> iterator;

        initInvSession();

        if (invsession != null)
        {
            try
            {
                transaction = invsession.beginTransaction();
            }
            catch (IllegalStateException e)
            {

            }
            catch (Exception e)
            {
                Logger.log (BrandAccessSvcImpl.class, "Transaction failed");
                close();
            }

            try
            {
                query = invsession.createQuery ("from Manufacturer");
                iterator = (Iterator<IBrand>)query.iterate();

                while (iterator.hasNext())
                {
                    brands.add (iterator.next());
                }

                transaction.commit();
            }
            catch (Exception e)
            {
                if (transaction != null)
                {
                    try
                    {
                        Logger.log (BrandAccessSvcImpl.class, e);
                        transaction.rollback();
                        close();
                    }
                    catch (Exception ex)
                    {
                        Logger.log (BrandAccessSvcImpl.class, "Rollback() failed");
                        close();
                    }
                }
            }
        }
        else
        {
             Logger.log (BrandAccessSvcImpl.class, "BrandAccessSvcImpl.readBrands failed with a NULL session.");
             close();
        }

        return brands;
    }

    @Override
    public boolean updateBrand (IBrand mfg, boolean isUpdate)
	{
		boolean result = true ;
        Collection<IBrand> mfgs = new ArrayList<IBrand>();
        Collection<IBrand> newList = new ArrayList<IBrand>();

        if (isUpdate)
        {
            // TODO: complete update code later
            result = true;
        }
        else
        {
            mfgs.add ((IBrand)mfg);
            newList = (Collection<IBrand>)merge (mfgs);
        }

        return result;
    }

    @Override
    public IBrand readBrand (Integer idBrand)
    {
        return (IBrand)find (new Manufacturer (idBrand));
    }

    @Override
    public boolean removeBrand (IBrand brand)
    {
        return true;
    }

    @Override
    public boolean save (Collection<?> object)
    {
        boolean result = true;
        Transaction transaction = null;
        IBrand newMfg;
        Iterator iterator;
        Collection<IBrand> mfgs;

		if (object != null)//validate
		{
            mfgs = (Collection<IBrand>) object;
            initInvSession();

            if (invsession != null)
            {
                try
                {
                    transaction = invsession.beginTransaction();
                    iterator = mfgs.iterator();

                    while (iterator.hasNext() && result)
                    {
                        newMfg = (IBrand)iterator.next();
                        invsession.save (newMfg);
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
                            close();
                        }
                    }
                }
            }
            else
            {
                Logger.log (BrandAccessSvcImpl.class, "BrandAccessSvcImpl.save failed with a NULL session.");
                result = false;
                close();
            }
		}
        else
        {
            Logger.log (BrandAccessSvcImpl.class, "BrandAccessSvcImpl.persist failed with a NULL object.");
            result = false;
            close();
        }

		return result;
    }

    @Override
    public Collection<IBrand> find (IDomainObject object)
    {
        IBrand brand = (IBrand)object;
        Collection<IBrand> brandList = new ArrayList<IBrand>();
        NativeQuery result;
        List<Object[]> resultSet;

        initInvSession();

        if (invsession!= null)
        {
            String query = "SELECT * FROM Manufacturer" +
                           " WHERE Address1 LIKE '%" + brand.getAddress1() + "%'" +
                           " AND Address2 LIKE '%" + brand.getAddress2() + "%'" +
                           " AND MafgKey LIKE '%" + brand.getBrandKey() + "%'" +
                           " AND ManName LIKE '%" + brand.getBrandName()  + "%'" +
                           " AND City LIKE '%" + brand.getCity() + "%'" +
                           " AND State LIKE '%" + brand.getState()  + "%'";
            try
            {
                result = invsession.createSQLQuery (query);
                resultSet = (List<Object[]>)result.list();

                if (!resultSet.isEmpty())
                {
                    for (Object[] element : resultSet)
                    {
                        brand = new Manufacturer();

                        try
                        {
                            brand.setIdBrand (Integer.parseInt (element[0].toString()));
                        }
                        catch (Exception e)
                        {

                        }

                        brand.setBrandKey (element[1].toString());
                        brand.setBrandName (element[2].toString());
                        brand.setAddress1 (element[3].toString());
                        brand.setCity (element[4].toString());
                        brand.setState (element[5].toString());
                        brand.setCountry (element[6].toString());
                        brand.setZip (element[7].toString());
                        brand.setPhone (element[8].toString());

                        brandList.add (brand);
                    }
                }
                // else do nothing
            }
            catch (Exception e)
            {
                brandList = new ArrayList<IBrand>();
                Logger.log (LogsAccessSvcImpl.class, e);
                close();
            }
        }
        else
        {
            Logger.log (ItemAccessSvcImpl.class, "Manufacturer.search failed with a NULL session.");
            close();
        }

        return brandList;
    }

    @Override
    public boolean delete (String type)
    {
        boolean result = false;
        Transaction transaction;

        if (invsession == null)
        {
            loadService ("inv");
        }

        transaction = invsession.beginTransaction();
        invsession.createSQLQuery ("SET FOREIGN_KEY_CHECKS = 0").executeUpdate();
        invsession.createSQLQuery ("TRUNCATE Manufacturer").executeUpdate();
        invsession.createSQLQuery ("SET FOREIGN_KEY_CHECKS = 1").executeUpdate();
        transaction.commit();
        result = true;

        return result;
    }

    @Override
    public boolean persist (Collection<?> object)
	{
		boolean result = true;
        Transaction transaction = null;
        IBrand newMfg;
        Iterator iterator;
        Collection<IBrand> mfgs;

		if (object != null)//validate
		{
            mfgs = (Collection<IBrand>) object;
            initInvSession();

            if (invsession != null)
            {
                try
                {
                    transaction = invsession.beginTransaction();
                    iterator = mfgs.iterator();

                    while (iterator.hasNext() && result)
                    {
                        newMfg = (IBrand)iterator.next();
                        invsession.persist (newMfg);
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
                            close();
                        }
                        catch (Exception ex)
                        {
                            Logger.log (BrandAccessSvcImpl.class, "Rollback() failed");
                            close();
                        }
                    }
                }
            }
            else
            {
                Logger.log (BrandAccessSvcImpl.class, "BrandAccessSvcImpl.persist failed with a NULL session.");
                result = false;
                close();
            }
		}
        else
        {
            Logger.log (BrandAccessSvcImpl.class, "BrandAccessSvcImpl.persist failed with a NULL object.");
            result = false;
            close();
        }

		return result;
    }

    @Override
    public Collection<?> merge (Collection<?> object)
    {
        boolean result = true;
        Transaction transaction = null;
        IBrand newMfg;
        Iterator iterator;
        Collection<IBrand> mfgs;
        Collection<IBrand> newList = new ArrayList<IBrand>();

        if (object != null)//validate
        {
            mfgs = (Collection<IBrand>) object;
            initInvSession();

            if (invsession != null)
            {
                try
                {
                    transaction = invsession.beginTransaction();
                }
                catch (IllegalStateException e)
                {

                }
                catch (Exception e)
                {
                    Logger.log (BrandAccessSvcImpl.class, "Transaction failed");
                    close();
                }

                try
                {
                    iterator = mfgs.iterator();

                    while (iterator.hasNext() && result)
                    {
                        newMfg = (IBrand)iterator.next();
                        invsession.merge (newMfg);
                    }

                    transaction.commit();
                }
                catch (Exception e)
                {
                    Logger.log (BrandAccessSvcImpl.class, e);
                    newList = new ArrayList<IBrand>();
                    close();
                }
            }
            else
            {
                Logger.log (BrandAccessSvcImpl.class, "BrandAccessSvcImpl.merge failed with a NULL session.");
                newList = new ArrayList<IBrand>();
                close();
            }
        }
        else
        {
            Logger.log (BrandAccessSvcImpl.class, "BrandAccessSvcImpl.merge failed with a NULL object.");
            newList = new ArrayList<IBrand>();
            close();
        }

        return newList;
    }

    @Override
    public Collection<?> search (ISearchParms searchParms)
    {
        Manufacturer brand = new Manufacturer();
        Collection<Manufacturer> brandList = new ArrayList<Manufacturer>();
        NativeQuery result;
        List<Object[]> resultSet;
        MfgSearchParams params = (MfgSearchParams)searchParms;

        initInvSession();

        if (invsession!= null)
        {
            brand.setAddress1 (params.getAddress1());
            brand.setAddress2 (params.getAddress2());
            brand.setBrandKey (params.getMfgKey());
            brand.setBrandName (params.getManName());
            brand.setCity (params.getCity());
            brand.setState (params.getState());

            String query = "SELECT * FROM Manufacturer" +
                           " WHERE Address1 LIKE '" + brand.getAddress1() + "'" +
                           " AND Address2 LIKE '" + brand.getAddress2() + "'" +
                           " AND MafgKey LIKE '" + brand.getBrandKey() + "'" +
                           " AND ManName LIKE '" + brand.getBrandName()  + "'" +
                           " AND City LIKE '" + brand.getCity() + "'" +
                           " AND State LIKE '" + brand.getState()  + "'";
            try
            {
                result = invsession.createSQLQuery (query);
                resultSet = (List<Object[]>)result.list();

                if (!resultSet.isEmpty())
                {
                    for (Object[] element : resultSet)
                    {
                        brand = new Manufacturer();

                        try
                        {
                            brand.setIdBrand (Integer.parseInt (element[0].toString()));
                        }
                        catch (Exception e)
                        {

                        }

                        brand.setBrandKey (element[1].toString());
                        brand.setBrandName (element[2].toString());
                        brand.setAddress1 (element[3].toString());
                        brand.setCity (element[4].toString());
                        brand.setState (element[5].toString());
                        brand.setCountry (element[6].toString());
                        brand.setZip (element[7].toString());
                        brand.setPhone (element[8].toString());

                        brandList.add (brand);
                    }
                }
                // else do nothing
            }
            catch (Exception e)
            {
                brandList = new ArrayList<Manufacturer>();
                Logger.log (LogsAccessSvcImpl.class, e);
            }
        }
        else
        {
            Logger.log (ItemAccessSvcImpl.class, "Manufacturer.search failed with a NULL session.");
        }

        return brandList;
    }

    public void close()
    {
        if (invsession !=null)
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
                invsession = null;
            }
        }
    }
}
