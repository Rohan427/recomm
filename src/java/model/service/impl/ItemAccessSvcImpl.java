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
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
import model.service.dao.HibernateSvc;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
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
    private final static Logger log = LogManager.getLogger (ItemAccessSvcImpl.class.getName());
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
		
		if (isUpdate)
		{
			// TODO: complete update code later
			result = true;
		}
        else if (result = addObjectToHashtable (item))
        {
            result = merge (hashtable);
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
	
    /**
     *
     * @param object
     * @return
     */
    @Override
	public boolean addObjectToHashtable (IDomainObject object)
	{
		boolean result = false;
        Hashtable<Integer, IDomainObject> localTable;
		
		//Instantiate a new table if none exists
		if (hashtable == null)
		{
			hashtable = new HashedObjectWrapper();
		}// else do nothing
		
		//validate input
		if (object != null && object instanceof Items)
		{
			Items item = (Items)object;
            localTable = hashtable.getHashtable();
			localTable.put (item.getIdItems(), item);
            hashtable.setHashtable (localTable);
			result = true;
		}// else do nothing
		
		return result;
	}
	
    /**
     *
     * @return
     */
    @Override
	public HashedObjectWrapper getHashtable()
	{
		return hashtable;
	}
	
    /**
     *
     * @param hashtable
     * @return
     */
    @Override
	public boolean persist (HashedObjectWrapper object)
	{
		boolean result = true;
        Transaction transaction;
        Items newItem;
        Iterator iterator;
        Hashtable<Integer, IDomainObject> itemTable;
        Collection<Items> items = new ArrayList<>();
        
		if (object != null)//validate
		{
			this.hashtable = object;
            
            if (session == null)
            {
                loadService();
            }
        
            transaction = session.beginTransaction();            
            itemTable = hashtable.getHashtable();
            Set<Integer> keys = itemTable.keySet();
            
            keys.forEach ((key) ->
            {
                Items item = (Items)itemTable.get (key);                                
                items.add (item);
            });
			
            iterator = items.iterator();
            
            while (iterator.hasNext() && result)
            {
                newItem = (Items)iterator.next();
                session.persist (newItem);
            }
            
            transaction.commit();
		}
		// else do nothing
		
		return result;
	}
    
    public boolean save (HashedObjectWrapper object)
    {
        boolean result = true;
        Transaction transaction;
        Items newItem;
        Iterator iterator;
        Hashtable<Integer, IDomainObject> itemTable;
        Collection<Items> items = new ArrayList<>();
        
		if (object != null)//validate
		{
			this.hashtable = object;
            
            if (session == null)
            {
                loadService();
            }
        
            transaction = session.beginTransaction();
            
            itemTable = hashtable.getHashtable();
            Set<Integer> keys = itemTable.keySet();
            
            keys.forEach ((key) ->
            {
                Items item = (Items)itemTable.get (key);                                
                items.add (item);
            });
			
            iterator = items.iterator();
            
            while (iterator.hasNext() && result)
            {
                newItem = (Items)iterator.next();
                session.save (newItem);
            }
            
            transaction.commit();
		}
		// else do nothing
		
		return result;
    }
	
    /**
     *
     * @param object
     * @return
     */
    @Override
	public IDomainObject find (IDomainObject object)
	{
		Transaction transaction;
        Items item = (Items)object;
        
        if (session == null)
        {
            loadService();
        }
                
        transaction = session.beginTransaction();
        object = (Items)session.get (Items.class, item.getIdItems());
        transaction.commit();
        		
		return object;
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
    
    public boolean merge (HashedObjectWrapper object)
    {
        boolean result = true;
        Transaction transaction;
        Items newItem;
        Iterator iterator;
        Hashtable<Integer, IDomainObject> itemTable;
        Collection<Items> items = new ArrayList<>();
        
		if (object != null)//validate
		{
			this.hashtable = object;
            
            if (session == null)
            {
                loadService();
            }
        
            transaction = session.beginTransaction();            
            itemTable = hashtable.getHashtable();
            Set<Integer> keys = itemTable.keySet();
            
            keys.forEach ((key) ->
            {
                Items item = (Items)itemTable.get (key);                                
                items.add (item);
            });
			
            iterator = items.iterator();
            
            while (iterator.hasNext() && result)
            {
                newItem = (Items)iterator.next();
                session.merge (newItem);
            }
            
            transaction.commit();
		}
		// else do nothing
		
		return result;
    }
}