/*
 * Copyright 2018 Random Logic Consulting and Paul G. Allen
 * All rights reserved.
 */

package com.recomm.model.domain.inventory.service.impl;

import java.io.Serializable;

import com.recomm.model.domain.inventory.entity.Items;
import com.recomm.model.domain.inventory.service.interfaces.IItemAccessSvc;
import com.recomm.model.domain.interfaces.IDomainObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import com.recomm.business.logging.Logger;
import com.recomm.model.domain.interfaces.ISearchParms;
import com.recomm.model.domain.inventory.util.ItemSearchParams;
import com.recomm.model.service.impl.HibernateSvc;
import com.recomm.model.domain.application.service.impl.LogsAccessSvcImpl;
import com.recomm.model.domain.inventory.interfaces.IItems;
import com.recomm.model.domain.users.service.impl.UserAccessSvcImpl;
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
        Transaction transaction = null;
        Query query;
        Iterator<Items> iterator;

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
                close();
            }

            try
            {
                query = invsession.createQuery ("from Items");
                iterator = (Iterator<Items>)query.iterate();

                while (iterator.hasNext())
                {
                    items.add (iterator.next());
                }

                transaction.commit();
            }
            catch (Exception e)
            {
                items = new ArrayList<Items>();

                if (transaction != null)
                {
                    try
                    {
                        Logger.log (UserAccessSvcImpl.class, e);
                        transaction.rollback();
                        close();
                    }
                    catch (Exception ex)
                    {
                        Logger.log (ItemAccessSvcImpl.class, "Rollback() failed");
                        close();
                    }
                }
            }
        }
        else
        {
            Logger.log (ItemAccessSvcImpl.class, "Items.readItems failed with a NULL session.");
            close();
        }

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
		boolean result = true;
        Collection<IItems> items = new ArrayList<IItems>();
        Collection<IItems> newList = new ArrayList<IItems>();

        if (isUpdate)
        {
            // TODO: complete update code later
            result = true;
        }
        else
        {
            items.add ((IItems)item);
            newList = (Collection<IItems>)merge (items);
        }

        if (newList.isEmpty())
        {
            result = false;
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
		Items item = new Items (idItem);
        NativeQuery result;
        List<Object[]> resultSet;

        initInvSession();

        if (invsession!= null)
        {
            String query = "SELECT * FROM Items WHERE idItems = '" + item.getIdItems() + "'";

            try
            {
                result = invsession.createSQLQuery (query);
                resultSet = (List<Object[]>)result.list();

                if (!resultSet.isEmpty())
                {
                    Object[] element = resultSet.get(0);
                    item = new Items();

                    try
                    {
                        item.setIdItems (Integer.parseInt (element[0].toString()));

                        item.setPartNo (element[1].toString());
                        item.setPartName (element[2].toString());
                        item.setDescription (element[3].toString());
                        item.setUpc (element[4].toString());

                        try
                        {
                            item.setQty (Integer.parseInt (element[5].toString()));
                        }
                        catch (NumberFormatException e)
                        {
                            item.setQty (0);
                        }

                        try
                        {
                            item.setInventory (Integer.parseInt (element[6].toString()));
                        }
                        catch (NumberFormatException e)
                        {
                            item.setInventory (0);
                        }

                        item.setDefaultImage (element[21].toString());
                    }
                    catch (NumberFormatException e)
                    {
                        close();
                    }
                }
                // else do nothing
            }
            catch (Exception e)
            {
                Logger.log (LogsAccessSvcImpl.class, e);
                close();
            }
        }
        else
        {
            Logger.log (ItemAccessSvcImpl.class, "Items.readItem failed with a NULL session.");
            close();
        }

        return item;
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
                }
                catch (IllegalStateException e)
                {

                }
                catch (Exception e)
                {
                    Logger.log (ItemAccessSvcImpl.class, "Transaction failed");
                    close();
                }

                try
                {
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
                            close();
                        }
                        catch (Exception ex)
                        {
                            Logger.log (ItemAccessSvcImpl.class, "Rollback() failed");
                            close();
                        }
                    }
                }
            }
            else
            {
                Logger.log (ItemAccessSvcImpl.class, "ItemAccessSvcImpl.save failed with a NULL session.");
                result = false;
                close();
            }
		}
        else
        {
            Logger.log (ItemAccessSvcImpl.class, "ItemAccessSvcImpl.persist failed with a NULL object.");
            result = false;
            close();
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
        Collection<Items> itemList = new ArrayList<Items>();
        NativeQuery result;
        List<Object[]> resultSet;

        initInvSession();

        if (invsession!= null)
        {
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
                        item = new Items();

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
                            close();
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
                close();
            }
        }
        else
        {
            Logger.log (ItemAccessSvcImpl.class, "Items.search failed with a NULL session.");
            close();
        }

        return itemList;
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
                            close();
                        }
                        catch (Exception ex)
                        {
                            Logger.log (ItemAccessSvcImpl.class, "Rollback() failed");
                            close();
                        }
                    }
                }
            }
            else
            {
                Logger.log (ItemAccessSvcImpl.class, "ItemAccessSvcImpl.persist failed with a NULL session.");
                close();
            }
		}
        else
        {
            Logger.log (ItemAccessSvcImpl.class, "ItemAccessSvcImpl.persist failed with a NULL object.");
            result = false;
            close();
        }

		return result;
    }

    @Override
    public Collection<?> merge (Collection<?> object)
    {
        Transaction transaction = null;
        IItems newItem;
        Iterator iterator;
        Collection<IItems> items;
        Collection<IItems> newList = new ArrayList<IItems>();

        if (object != null)//validate
        {
            items = (Collection<IItems>) object;
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
                    close();
                }

                try
                {
                    iterator = items.iterator();

                    do
                    {
                        newItem = (IItems)iterator.next();
                        newItem = (IItems)invsession.merge (newItem);
                        newList.add (newItem);
                    } while (iterator.hasNext() && newItem != null);

                    transaction.commit();
                }
                catch (Exception e)
                {
                    Logger.log (ItemAccessSvcImpl.class, e);
                    newList = new ArrayList<IItems>();
                    close();
                }
            }
            else
            {
                Logger.log (ItemAccessSvcImpl.class, "ItemAccessSvcImpl.merge failed with a NULL session.");
                newList = new ArrayList<IItems>();
                close();
            }
        }
        else
        {
            Logger.log (ItemAccessSvcImpl.class, "ItemAccessSvcImpl.merge failed with a NULL object.");
            newList = new ArrayList<IItems>();
            close();
        }

        return newList;
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

            String query = "SELECT * FROM Items" +
                           " WHERE PartNo LIKE '" + item.getPartNo() + "'" +
                           " AND PartName LIKE '" + item.getPartName() + "'" +
                           " AND UPC LIKE '" + item.getUpc() + "'" +
                           " AND Description LIKE '" + item.getDescription()  + "'";
            try
            {
                result = invsession.createSQLQuery (query);
                resultSet = (List<Object[]>)result.list();

                if (!resultSet.isEmpty())
                {
                    for (Object[] element : resultSet)
                    {
                        item = new Items();

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
                            close();
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
                close();
            }
        }
        else
        {
            Logger.log (ItemAccessSvcImpl.class, "Items.search failed with a NULL session.");
            close();
        }

        return itemList;
    }

    @Override
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