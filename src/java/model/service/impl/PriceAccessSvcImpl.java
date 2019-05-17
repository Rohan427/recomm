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
import java.util.Hashtable;
import java.util.Set;
import model.domain.interfaces.IDomainObject;
import java.util.Iterator;
import model.service.dao.HibernateSvc;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
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
    private final static Logger log = LogManager.getLogger (PriceAccessSvcImpl.class.getName());

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
		
		if (isUpdate)
		{
			if (result = addObjectToHashtable (price))
            {
                result = merge (hashtable);
            }
			result = true;
		}
        else if (result = addObjectToHashtable (price))
        {
            result = save (hashtable);
        }
		
		return result;
	}
    
    public boolean save (HashedObjectWrapper object)
    {
        boolean result = true;
        Transaction transaction;
        Prices newPrice;
        Iterator iterator;
        Hashtable<Integer, IDomainObject> priceTable;
        Collection<Prices> prices = new ArrayList<>();
        
		if (object != null)//validate
		{
			this.hashtable = object;
            
            if (session == null)
            {
                loadService();
            }
        
            transaction = session.beginTransaction();
            
            priceTable = hashtable.getHashtable();
            Set<Integer> keys = priceTable.keySet();
            
            keys.forEach ((key) ->
            {
                Prices price = (Prices)priceTable.get (key);                                
                prices.add (price);
            });
			
            iterator = prices.iterator();
            
            while (iterator.hasNext() && result)
            {
                newPrice = (Prices)iterator.next();
                session.save (newPrice);
            }
            
            transaction.commit();
		}
		// else do nothing
		
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
	public boolean addObjectToHashtable (IDomainObject object)
	{
		boolean result = false;
		
		//Instantiate a new table if none exists
		if (hashtable == null)
		{
			hashtable = new HashedObjectWrapper();
		}
		// else do nothing
		
		if (object != null && object instanceof Prices)//validate input
		{
			Prices price = (Prices)object;
			hashtable.getHashtable().put (price.getIdPrices(), price);
			result = true;
		}
		// else do nothing
		
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
     * @param object
     * @return
     */
    @Override
	public boolean persist (HashedObjectWrapper object)
	{
		boolean result = true;
        Transaction transaction;
        Prices newPrice;
        Iterator iterator;
        Hashtable<Integer, IDomainObject> priceTable;
        Collection<Prices> prices = new ArrayList<>();
        
		if (object != null)//validate
		{
			this.hashtable = object;
            
            if (session == null)
            {
                loadService();
            }
        
            transaction = session.beginTransaction();
            
            priceTable = hashtable.getHashtable();
            Set<Integer> keys = priceTable.keySet();
            
            keys.forEach ((key) ->
            {
                Prices price = (Prices)priceTable.get (key);                                
                prices.add (price);
            });
			
            iterator = prices.iterator();
            
            while (iterator.hasNext() && result)
            {
                newPrice = (Prices)iterator.next();
                session.persist (newPrice);
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
        Prices price = (Prices)object;
        
        if (session == null)
        {
            loadService();
        }
                
        transaction = session.beginTransaction();
        object = (Prices)session.get (Prices.class, price.getIdPrices());
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
        session.createSQLQuery ("TRUNCATE Prices").executeUpdate();
        session.createSQLQuery ("SET FOREIGN_KEY_CHECKS = 1").executeUpdate();
        
        transaction.commit();
        result = true;
        
        return result;
    }
    
    public boolean merge (HashedObjectWrapper object)
    {
        boolean result = true;
        Transaction transaction;
        Prices newPrice;
        Iterator iterator;
        Hashtable<Integer, IDomainObject> priceTable;
        Collection<Prices> prices = new ArrayList<>();
        
		if (object != null)//validate
		{
			this.hashtable = object;
            
            if (session == null)
            {
                loadService();
            }
        
            transaction = session.beginTransaction();
            
            priceTable = hashtable.getHashtable();
            Set<Integer> keys = priceTable.keySet();
            
            keys.forEach ((key) ->
            {
                Prices price = (Prices)priceTable.get (key);                                
                prices.add (price);
            });
			
            iterator = prices.iterator();
            
            while (iterator.hasNext() && result)
            {
                newPrice = (Prices)iterator.next();
                session.merge (newPrice);
            }
            
            transaction.commit();
		}
		// else do nothing
		
		return result;
    }
}