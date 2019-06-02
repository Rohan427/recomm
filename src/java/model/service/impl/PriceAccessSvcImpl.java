/*
 * Copyright 2018 Random Logic Consulting and Paul G. Allen
 * All rights reserved.
 */

package model.service.impl;

import java.io.Serializable;

import model.domain.inventory.Prices;
import model.service.dao.HashedObjectWrapper;
import model.service.interfaces.IPriceAccessSvc;
import java.util.ArrayList;
import java.util.Collection;
import model.domain.interfaces.IDomainObject;
import java.util.Iterator;
import model.business.error.Logger;
import model.service.dao.HibernateSvc;
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

        if (session == null)
        {
            loadService();
        }

        transaction = session.beginTransaction();
        query = session.createQuery ("from Prices");
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
		boolean result;
        Collection<Prices> prices = new ArrayList<Prices>();

        if (isUpdate)
        {
            // TODO: complete update code later
            result = true;
        }
        else
        {
            prices.add ((Prices)price);
            result = merge (prices);
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
            initSession();

            if (session != null)
            {
                try
                {
                    transaction = session.beginTransaction();
                    iterator = users.iterator();

                    while (iterator.hasNext() && result)
                    {
                        newPrice = (Prices)iterator.next();
                        session.save (newPrice);
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
            initSession();

            if (session != null)
            {
                try
                {
                    transaction = session.beginTransaction();
                    iterator = prices.iterator();

                    while (iterator.hasNext() && result)
                    {
                        newPrice = (Prices)iterator.next();
                        session.persist (newPrice);
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

        if (session == null)
        {
            loadService();
        }

        object = (Prices)session.get (Prices.class, price.getIdPrices());

		return prices;
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
        session.createSQLQuery ("TRUNCATE Prices").executeUpdate();
        session.createSQLQuery ("SET FOREIGN_KEY_CHECKS = 1").executeUpdate();

        transaction.commit();
        result = true;

        return result;
    }

    @Override
    public boolean merge (Collection<?> object)
    {
        boolean result = true;
        Transaction transaction;
        Prices newPrice;
        Iterator iterator;
        Collection<Prices> prices;

        if (object != null)//validate
        {
            prices = (Collection<Prices>) object;
            initSession();

            if (session != null)
            {
                try
                {
                    transaction = session.beginTransaction();
                    iterator = prices.iterator();

                    while (iterator.hasNext() && result)
                    {
                        newPrice = (Prices)iterator.next();
                        session.merge (newPrice);
                    }

                    transaction.commit();
                }
                catch (Exception e)
                {
                    Logger.log (PriceAccessSvcImpl.class, e);
                }
            }
            else
            {
                Logger.log (PriceAccessSvcImpl.class, "PriceAccessSvcImpl.merge failed with a NULL session.");
                result = false;
            }
        }
        else
        {
            Logger.log (PriceAccessSvcImpl.class, "PriceAccessSvcImpl.merge failed with a NULL object.");
            result = false;
        }

        return result;
    }
}