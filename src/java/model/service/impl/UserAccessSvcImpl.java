/*
 * Copyright (c) 2018 Random Logic Consulting and Paul G. Allen.
 * All Rights Reserved.
 */
package model.service.impl;

import model.domain.interfaces.IDomainObject;
import model.domain.users.Users;
import model.service.dao.HashedObjectWrapper;
import model.service.interfaces.IUserAccessSvc;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
import model.domain.interfaces.IUsers;
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
public class UserAccessSvcImpl extends HibernateSvc implements IUserAccessSvc, Serializable
{
    private static final long serialVersionUID = 43L;
    private final static Logger log = LogManager.getLogger (UserAccessSvcImpl.class.getName());
    private HashedObjectWrapper hashtable = null;
    
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
		if (object != null && object instanceof Users)
		{
			Users user = (Users)object;
            hashtable.getHashtable().put (user.getIdUsers(), user);
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
        session.createSQLQuery ("TRUNCATE Users").executeUpdate();
        session.createSQLQuery ("SET FOREIGN_KEY_CHECKS = 1").executeUpdate();
        
        transaction.commit();
        result = true;
        
        return result;
    }

    @Override
    public IDomainObject find (IDomainObject object)
    {
        Transaction transaction;
        Users user = (Users)object;
        
        if (session == null)
        {
            loadService();
        }
                
        transaction = session.beginTransaction();
        object = (Users)session.get (Users.class, user.getIdUsers());
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
        Users newUser;
        Iterator iterator;
        Hashtable<Integer, IDomainObject> usersTable;
        Collection<Users> users = new ArrayList<>();
        
		if (object != null)//validate
		{
			this.hashtable = object;
            
            if (session == null)
            {
                loadService();
            }
        
            transaction = session.beginTransaction();
            
            usersTable = hashtable.getHashtable();
            Set<Integer> keys = usersTable.keySet();
            
            keys.forEach ((key) ->
            {
                Users user = (Users)usersTable.get (key);                                
                users.add (user);
            });
			
            iterator = users.iterator();
            
            while (iterator.hasNext() && result)
            {
                newUser = (Users)iterator.next();
                session.persist (newUser);
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
        Users newUser;
        Iterator iterator;
        Hashtable<Integer, IDomainObject> usersTable;
        Collection<Users> users = new ArrayList<>();
        
		if (object != null)//validate
		{
			this.hashtable = object;
            
            if (session == null)
            {
                loadService();
            }
        
            transaction = session.beginTransaction();
            
            usersTable = hashtable.getHashtable();
            Set<Integer> keys = usersTable.keySet();
            
            keys.forEach ((key) ->
            {
                Users user = (Users)usersTable.get (key);                                
                users.add (user);
            });
			
            iterator = users.iterator();
            
            while (iterator.hasNext() && result)
            {
                newUser = (Users)iterator.next();
                session.save (newUser);
            }
            
            transaction.commit();
		}
		// else do nothing
		
		return result;
    }

    @Override
    public Users readUser (Integer idusers)
    {
        return (Users)find (new Users (idusers));
    }

    @Override
    public Collection<Users> readUsers()
    {
        Collection<Users> users = new ArrayList<Users>();
        Transaction transaction;
        Query query;
        Iterator<Users> iterator;
        
        if (session == null)
        {
            loadService();
        }
               
        transaction = session.beginTransaction();        
        query = session.createQuery ("from Users");
        iterator = (Iterator<Users>)query.iterate();
        
        while (iterator.hasNext())
        {
            users.add (iterator.next());
        }

        transaction.commit();
        
        return users;
    }

    @Override
    public boolean removeUser (IUsers user)
    {
        return true;
    }

    @Override
    public boolean updateUser (IUsers user, boolean isUpdate)
    {
        boolean result;
		
		if (isUpdate)
		{
			// TODO: complete update code later
			result = true;
		}
        else if (result = addObjectToHashtable (user))
        {
            result = merge (hashtable);
        }
		
		return result;
    }
    
    public boolean merge (HashedObjectWrapper object)
    {
        boolean result = true;
        Transaction transaction;
        Users newUser;
        Iterator iterator;
        Hashtable<Integer, IDomainObject> usersTable;
        Collection<Users> users = new ArrayList<>();
        
		if (object != null)//validate
		{
			this.hashtable = object;
            
            if (session == null)
            {
                loadService();
            }
        
            transaction = session.beginTransaction();            
            usersTable = hashtable.getHashtable();
            Set<Integer> keys = usersTable.keySet();
            
            keys.forEach ((key) ->
            {
                Users user = (Users)usersTable.get (key);
                users.add (user);
            });
			
            iterator = users.iterator();
            
            while (iterator.hasNext() && result)
            {
                newUser = (Users)iterator.next();
                session.merge (newUser);
            }
            
            transaction.commit();
		}
		// else do nothing
		
		return result;
    }
}
