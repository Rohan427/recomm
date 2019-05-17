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
 *
 * @author Paul G. Allen <pgallen@gmail.com>
 */
@Service
public class BrandAccessSvcImpl extends HibernateSvc implements IBrandAccessSvc, Serializable
{
    private static final long serialVersionUID = 43L;
    private final static Logger log = LogManager.getLogger (BrandAccessSvcImpl.class.getName());
    private HashedObjectWrapper hashtable = null;
    
    public BrandAccessSvcImpl()
    {
        
    }
    
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
		
		//validate input
		if (object != null && object instanceof Manufacturer)
		{
			Manufacturer brand = (Manufacturer)object;
            hashtable.getHashtable().put (brand.getIdBrand(), brand);
			result = true;
		}
		// else do nothing
		
		return result;
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
    public IDomainObject find (IDomainObject object)
    {
        Transaction transaction;
        Manufacturer brand = (Manufacturer)object;
        
        if (session == null)
        {
            loadService();
        }
                
        transaction = session.beginTransaction();
        object = (Manufacturer)session.get (Manufacturer.class, brand.getIdBrand());
        transaction.commit();
		
		return object;
    }

    @Override
    public HashedObjectWrapper getHashtable()
    {
        return hashtable;
    }

    @Override
    public boolean persist (HashedObjectWrapper object)
    {
        boolean result = true;
        Transaction transaction;
        Manufacturer newBrand;
        Iterator iterator;
        Hashtable<Integer, IDomainObject> brandTable;
        Collection<Manufacturer> brands = new ArrayList<>();
        
		if (object != null)//validate
		{
			this.hashtable = object;
            
            if (session == null)
            {
                loadService();
            }
        
            transaction = session.beginTransaction();
            
            brandTable = hashtable.getHashtable();
            Set<Integer> keys = brandTable.keySet();
            
            keys.forEach ((key) ->
            {
                Manufacturer brand = (Manufacturer)brandTable.get (key);                                
                brands.add (brand);
            });
			
            iterator = brands.iterator();
            
            while (iterator.hasNext() && result)
            {
                newBrand = (Manufacturer)iterator.next();
                session.persist (newBrand);
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
        Manufacturer newBrand;
        Iterator iterator;
        Hashtable<Integer, IDomainObject> brandTable;
        Collection<Manufacturer> brands = new ArrayList<>();
        
		if (object != null)//validate
		{
			this.hashtable = object;
            
            if (session == null)
            {
                loadService();
            }
        
            transaction = session.beginTransaction();
            
            brandTable = hashtable.getHashtable();
            Set<Integer> keys = brandTable.keySet();
            
            keys.forEach ((key) ->
            {
                Manufacturer brand = (Manufacturer)brandTable.get (key);                                
                brands.add (brand);
            });
			
            iterator = brands.iterator();
            
            while (iterator.hasNext() && result)
            {
                newBrand = (Manufacturer)iterator.next();
                session.save (newBrand);
            }
            
            transaction.commit();
		}
		// else do nothing
		
		return result;
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
    public boolean updateBrand (Manufacturer brand, boolean isUpdate)
    {
        boolean result;
		
		if (isUpdate)
		{
			// TODO: complete update code later
			result = true;
		}
        else if (result = addObjectToHashtable (brand))
        {
            result = merge (hashtable);
        }
		
		return result;
    }
    
    public boolean merge (HashedObjectWrapper object)
    {
        boolean result = true;
        Transaction transaction;
        Manufacturer newBrand;
        Iterator iterator;
        Hashtable<Integer, IDomainObject> brandTable;
        Collection<Manufacturer> brands = new ArrayList<>();
        
		if (object != null)//validate
		{
			this.hashtable = object;
            
            if (session == null)
            {
                loadService();
            }
        
            transaction = session.beginTransaction();
            
            brandTable = hashtable.getHashtable();
            Set<Integer> keys = brandTable.keySet();
            
            keys.forEach ((key) ->
            {
                Manufacturer brand = (Manufacturer)brandTable.get (key);                                
                brands.add (brand);
            });
			
            iterator = brands.iterator();
            
            while (iterator.hasNext() && result)
            {
                newBrand = (Manufacturer)iterator.next();
                session.merge (newBrand);
            }
            
            transaction.commit();
		}
		// else do nothing
		
		return result;
    }
}
