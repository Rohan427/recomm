/*
 * Copyright 2018 Random Logic Consulting and Paul G. Allen
 * All rights reserved.
 */

package com.recomm.model.domain.inventory.service.impl;

import java.io.Serializable;

import com.recomm.model.domain.inventory.entity.Prices;
import com.recomm.model.service.beans.HashedObjectWrapper;
import com.recomm.model.domain.inventory.service.interfaces.IPriceAccessSvc;
import java.util.ArrayList;
import java.util.Collection;
import com.recomm.model.domain.interfaces.IDomainObject;
import java.util.Iterator;
import com.recomm.business.logging.Logger;
import com.recomm.model.domain.interfaces.ISearchParms;
import com.recomm.model.domain.inventory.interfaces.IPrices;
import com.recomm.model.service.impl.HibernateSvc;
import com.recomm.model.domain.users.service.impl.UserAccessSvcImpl;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

/**
 * Price access service
 *
 * @author Paul G. Allen <pgallen@gmail.com>
 */
@Service
public class PriceAccessSvcImpl extends HibernateSvc implements IPriceAccessSvc, Serializable
{
    private static final long serialVersionUID = 45L;
	private HashedObjectWrapper hashtable;

    /**
     *
     * @return
     */
    @Override
    public Collection<Prices> readPrices()
    {
        Collection<Prices> prices = new ArrayList<Prices>();
        Transaction transaction;
        Query query;
        Iterator<Prices> iterator;

        if (invsession == null)
        {
            loadService ("inv");
        }

        transaction = invsession.beginTransaction();
        query = invsession.createQuery ("from Prices");
        iterator = (Iterator<Prices>)query.iterate();

        while (iterator.hasNext())
        {
            prices.add (iterator.next());
        }

        transaction.commit();

        return prices;
    }

    /**
     *
     * @param price
     * @param isUpdate
     * @return
     */
    @Override
	public boolean updatePrice (Prices price, boolean isUpdate)
	{
		boolean result = true;
        Collection<IPrices> prices = new ArrayList<IPrices>();
        Collection<IPrices> newList = new ArrayList<IPrices>();

        if (isUpdate)
        {
            // TODO: complete update code later
            result = true;
        }
        else
        {
            prices.add ((Prices)price);
            newList = (Collection<IPrices>)merge (prices);
        }

        if (newList.isEmpty())
        {
            result = false;
        }

        return result;
	}

    @Override
    public boolean save (Collection<?> object)
    {
        boolean result = true;
        Transaction transaction = null;
        Prices newPrice;
        Iterator iterator;
        Collection<Prices> users;

		if (object != null)//validate
		{
            users = (Collection<Prices>) object;
            initInvSession();

            if (invsession != null)
            {
                try
                {
                    transaction = invsession.beginTransaction();
                    iterator = users.iterator();

                    while (iterator.hasNext() && result)
                    {
                        newPrice = (Prices)iterator.next();
                        invsession.save (newPrice);
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
                            Logger.log (UserAccessSvcImpl.class, e);
                            transaction.rollback();
                        }
                        catch (Exception ex)
                        {
                            Logger.log (PriceAccessSvcImpl.class, "Rollback() failed");
                        }
                    }
                }
            }
            else
            {
                Logger.log (PriceAccessSvcImpl.class, "PriceAccessSvcImpl.save failed with a NULL session.");
                result = false;
            }
		}
        else
        {
            Logger.log (PriceAccessSvcImpl.class, "PriceAccessSvcImpl.persist failed with a NULL object.");
            result = false;
        }

		return result;
    }

    /**
     *
     * @param idPrice
     * @return
     */
    @Override
	public Prices readPrice (Integer idPrice)
	{
		return (Prices)find (new Prices (idPrice));
	}

    /**
     *
     * @param price
     * @return
     */
    @Override
	public boolean removePrice (Prices price)
	{
		// TODO Auto-generated method stub
		return true;
	}

    /**
     *
     * @param object
     * @return
     */
    @Override
	public boolean persist (Collection<?> object)
	{
		boolean result = true;
        Transaction transaction = null;
        Prices newPrice;
        Iterator iterator;
        Collection<Prices> prices;

		if (object != null)//validate
		{
            prices = (Collection<Prices>) object;
            initInvSession();

            if (invsession != null)
            {
                try
                {
                    transaction = invsession.beginTransaction();
                    iterator = prices.iterator();

                    while (iterator.hasNext() && result)
                    {
                        newPrice = (Prices)iterator.next();
                        invsession.persist (newPrice);
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
                            Logger.log (UserAccessSvcImpl.class, e);
                            transaction.rollback();
                        }
                        catch (Exception ex)
                        {
                            Logger.log (PriceAccessSvcImpl.class, "Rollback() failed");
                        }
                    }
                }
            }
            else
            {
                Logger.log (PriceAccessSvcImpl.class, "PriceAccessSvcImpl.persist failed with a NULL session.");
            }
		}
        else
        {
            Logger.log (PriceAccessSvcImpl.class, "PriceAccessSvcImpl.persist failed with a NULL object.");
            result = false;
        }

		return result;
	}

    /**
     *
     * @param object
     * @return
     */
    @Override
	public Collection<Prices> find (IDomainObject object)
	{
        Prices price = (Prices)object;
        Iterator userItr;
        Object[] userObject;
        Collection<Prices> prices = new ArrayList<Prices>();

        if (invsession == null)
        {
            loadService ("inv");
        }

        object = (Prices)invsession.get (Prices.class, price.getIdPrices());

		return prices;
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
        invsession.createSQLQuery ("TRUNCATE Prices").executeUpdate();
        invsession.createSQLQuery ("SET FOREIGN_KEY_CHECKS = 1").executeUpdate();

        transaction.commit();
        result = true;

        return result;
    }

    @Override
    public Collection<?> merge (Collection<?> object)
    {
        boolean result = true;
        Transaction transaction = null;
        IPrices newPrice;
        Iterator iterator;
        Collection<IPrices> prices;
        Collection<IPrices> newList = new ArrayList<IPrices>();

        if (object != null)//validate
        {
            prices = (Collection<IPrices>) object;
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
                    Logger.log (ItemAccessSvcImpl.class, "Transaction failed");
                    invsession.flush();
                    invsession.close();
                }

                try
                {
                    iterator = prices.iterator();

                    while (iterator.hasNext() && result)
                    {
                        newPrice = (IPrices)iterator.next();
                        newPrice = (IPrices)invsession.merge (newPrice);
                        newList.add (newPrice);
                    }

                    transaction.commit();
                }
                catch (Exception e)
                {
                    Logger.log (PriceAccessSvcImpl.class, e);
                    newList = new ArrayList<IPrices>();
                }
            }
            else
            {
                Logger.log (PriceAccessSvcImpl.class, "PriceAccessSvcImpl.merge failed with a NULL session.");
                newList = new ArrayList<IPrices>();
            }
        }
        else
        {
            Logger.log (PriceAccessSvcImpl.class, "PriceAccessSvcImpl.merge failed with a NULL object.");
        }

        return newList;
    }

    @Override
    public Collection<?> search(ISearchParms searchParms) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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