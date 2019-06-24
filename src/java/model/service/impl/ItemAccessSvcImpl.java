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
import java.util.List;
import model.business.error.Logger;
import model.domain.interfaces.ISearchParms;
import model.domain.inventory.ItemSearchParams;
import model.service.dao.HibernateSvc;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
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

    @Override
    public Collection<Items> readItems()
    {
        Collection<Items> items = new ArrayList<Items>();
        Transaction transaction;
        Query query;
        Iterator<Items> iterator;

        if (invsession == null)
        {
            loadService ("inv");
        }

        transaction = invsession.beginTransaction();
        query = invsession.createQuery ("from Items");
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
            initInvSession();

            if (invsession != null)
            {
                try
                {
                    transaction = invsession.beginTransaction();
                    iterator = items.iterator();

                    while (iterator.hasNext() && result)
                    {
                        newItem = (Items)iterator.next();
                        invsession.save (newItem);
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

        if (invsession == null)
        {
            loadService ("inv");
        }

        object = (Items)invsession.get (Items.class, item.getIdItems());

		return items;
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
        invsession.createSQLQuery ("TRUNCATE Items").executeUpdate();
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
        Items newItem;
        Iterator iterator;
        Collection<Items> items;

		if (object != null)//validate
		{
            items = (Collection<Items>) object;
            initInvSession();

            if (invsession != null)
            {
                try
                {
                    transaction = invsession.beginTransaction();
                    iterator = items.iterator();

                    while (iterator.hasNext() && result)
                    {
                        newItem = (Items)iterator.next();
                        invsession.persist (newItem);
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
            initInvSession();

            if (invsession != null)
            {
                try
                {
                    transaction = invsession.beginTransaction();
                    iterator = items.iterator();

                    while (iterator.hasNext() && result)
                    {
                        newItem = (Items)iterator.next();
                        invsession.merge (newItem);
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

    @Override
    public Collection<?> search (ISearchParms searchParms)
    {
        Items item = new Items();
        Collection<Items> itemList = new ArrayList<Items>();
        NativeQuery result;
        List<Object[]> resultSet;
        ItemSearchParams params = (ItemSearchParams)searchParms;

        initInvSession();

        if (invsession!= null)
        {
            item.setDescription (params.getDesc());
            item.setPartName (params.getName());
            item.setUpc (params.getUpc());
            item.setPartNo (params.getPartNo());
            /**
             * SELECT *
             *      FROM [dbo].[logs]
             *      WHERE customerId LIKE '%289362%'
             *      AND errorMsg LIKE '%Successful%'
             *      AND logDate >= '2019-04-23 00:00:00'
             *      AND logDate <= '2019-04-23 23:59:59.999'
             */
            String query = "SELECT * FROM Items" +
                           " WHERE PartNo LIKE '%" + item.getPartNo() + "%'" +
                           " AND PartName LIKE '%" + item.getPartName() + "%'" +
                           " AND UPC LIKE '%" + item.getUpc() + "%'" +
                           " AND Description LIKE '%" + item.getDescription()  + "%'";
            try
            {
                result = invsession.createSQLQuery (query);
                resultSet = (List<Object[]>)result.list();

                if (!resultSet.isEmpty())
                {
                    for (Object[] element : resultSet)
                    {
                        try
                        {
                            item.setIdItems (Integer.parseInt (element[0].toString()));
                        }
                        catch (Exception e)
                        {

                        }

                        item.setPartNo (element[1].toString());
                        item.setPartName (element[2].toString());
                        item.setDescription (element[3].toString());
                        item.setUpc (element[4].toString());

                        try
                        {
                            item.setQty (Integer.parseInt (element[5].toString()));
                        }
                        catch (Exception e)
                        {
                            item.setQty (0);
                        }

                        try
                        {
                            item.setInventory (Integer.parseInt (element[6].toString()));
                        }
                        catch (Exception e)
                        {
                            item.setInventory (0);
                        }

                        item.setDefaultImage (element[21].toString());

                        itemList.add (item);
                    }
                }
                // else do nothing
            }
            catch (Exception e)
            {
                itemList = new ArrayList<Items>();
                Logger.log (LogsAccessSvcImpl.class, e);
            }
        }
        else
        {
            Logger.log (ItemAccessSvcImpl.class, "Items.search failed with a NULL session.");
        }

        return itemList;
    }
}