/*
 * Copyright 2018 Random Logic Consulting and Paul G. Allen
 * All rights reserved.
 */

package model.service.impl;

import java.io.Serializable;

import model.domain.inventory.Items;
import model.service.dao.HashedObjectWrapper;
import model.service.interfaces.IItemAccessSvc;
import model.domain.interfaces.IDomainObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import model.business.error.Logger;
import model.service.dao.HibernateSvc;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

/**
 * Item access service
 *
 * @author Paul G. Allen <pgallen@gmail.com>
 */
@Service
public class ItemAccessSvcImpl extends HibernateSvc implements IItemAccessSvc, Serializable
{
    private static final long serialVersionUID = 41L;
	private HashedObjectWrapper hashtable;

    @Override
    public Collection<Items> readItems()
    {
        Collection<Items> items = new ArrayList<Items>();
        Transaction transaction;
        Query query;
        Iterator<Items> iterator;

        if (session == null)
        {
            loadService();
        }

        transaction = session.beginTransaction();
        query = session.createQuery ("from Items");
        iterator = (Iterator<Items>)query.iterate();

        while (iterator.hasNext())
        {
            items.add (iterator.next());
        }

        transaction.commit();

        return items;
    }

        /**
     *
     * @param item
     * @param isUpdate
     * @return
     */
    @Override
	public boolean updateItem (Items item, boolean isUpdate)
	{
		boolean result;
        Collection<Items> items = new ArrayList<Items>();

        if (isUpdate)
        {
            // TODO: complete update code later
            result = true;
        }
        else
        {
            items.add ((Items)item);
            result = merge (items);
        }

        return result;
	}

    /**
     *
     * @param idItem
     * @return
     */
    @Override
	public Items readItem (Integer idItem)
	{
		return (Items)find (new Items (idItem));
	}

    /**
     *
     * @param item
     * @return
     */
    @Override
	public boolean removeItem (Items item)
	{
		// TODO Auto-generated method stub
		return true;
	}

    @Override
    public boolean save (Collection<?> object)
    {
        boolean result = true;
        Transaction transaction = null;
        Items newItem;
        Iterator iterator;
        Collection<Items> items;

		if (object != null)//validate
		{
            items = (Collection<Items>) object;
            initSession();

            if (session != null)
            {
                try
                {
                    transaction = session.beginTransaction();
                    iterator = items.iterator();

                    while (iterator.hasNext() && result)
                    {
                        newItem = (Items)iterator.next();
                        session.save (newItem);
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
                            Logger.log (ItemAccessSvcImpl.class, "Rollback() failed");
                        }
                    }
                }
            }
            else
            {
                Logger.log (ItemAccessSvcImpl.class, "ItemAccessSvcImpl.save failed with a NULL session.");
                result = false;
            }
		}
        else
        {
            Logger.log (ItemAccessSvcImpl.class, "ItemAccessSvcImpl.persist failed with a NULL object.");
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
	public Collection<Items> find (IDomainObject object)
	{
        Items item = (Items)object;
        Iterator userItr;
        Object[] userObject;
        Collection<Items> items = new ArrayList<Items>();

        if (session == null)
        {
            loadService();
        }

        object = (Items)session.get (Items.class, item.getIdItems());

		return items;
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
        session.createSQLQuery ("TRUNCATE Items").executeUpdate();
        session.createSQLQuery ("SET FOREIGN_KEY_CHECKS = 1").executeUpdate();
        transaction.commit();
        result = true;

        return result;
    }

    @Override
    public boolean persist (Collection<?> object)
	{
		boolean result = true;
        Transaction transaction = null;
        Items newItem;
        Iterator iterator;
        Collection<Items> items;

		if (object != null)//validate
		{
            items = (Collection<Items>) object;
            initSession();

            if (session != null)
            {
                try
                {
                    transaction = session.beginTransaction();
                    iterator = items.iterator();

                    while (iterator.hasNext() && result)
                    {
                        newItem = (Items)iterator.next();
                        session.persist (newItem);
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
                            Logger.log (ItemAccessSvcImpl.class, e);
                            transaction.rollback();
                        }
                        catch (Exception ex)
                        {
                            Logger.log (ItemAccessSvcImpl.class, "Rollback() failed");
                        }
                    }
                }
            }
            else
            {
                Logger.log (ItemAccessSvcImpl.class, "ItemAccessSvcImpl.persist failed with a NULL session.");
            }
		}
        else
        {
            Logger.log (ItemAccessSvcImpl.class, "ItemAccessSvcImpl.persist failed with a NULL object.");
            result = false;
        }

		return result;
    }

    @Override
    public boolean merge (Collection<?> object)
    {
        boolean result = true;
        Transaction transaction;
        Items newItem;
        Iterator iterator;
        Collection<Items> items;

        if (object != null)//validate
        {
            items = (Collection<Items>) object;
            initSession();

            if (session != null)
            {
                try
                {
                    transaction = session.beginTransaction();
                    iterator = items.iterator();

                    while (iterator.hasNext() && result)
                    {
                        newItem = (Items)iterator.next();
                        session.merge (newItem);
                    }

                    transaction.commit();
                }
                catch (Exception e)
                {
                    Logger.log (ItemAccessSvcImpl.class, e);
                }
            }
            else
            {
                Logger.log (ItemAccessSvcImpl.class, "ItemAccessSvcImpl.merge failed with a NULL session.");
                result = false;
            }
        }
        else
        {
            Logger.log (ItemAccessSvcImpl.class, "ItemAccessSvcImpl.merge failed with a NULL object.");
            result = false;
        }

        return result;
    }
}