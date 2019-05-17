package model.service.impl;

import java.io.Serializable;

import model.domain.inventory.Images;
import model.service.dao.HashedObjectWrapper;
import model.service.interfaces.IImageAccessSvc;
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
 * Image access service
 * 
 * @author Paul G. Allen <pgallen@gmail.com>
 */
@Service
public class ImageAccessSvcImpl extends HibernateSvc implements IImageAccessSvc, Serializable
{
    private static final long serialVersionUID = 44L;
    private final static Logger log = LogManager.getLogger (ImageAccessSvcImpl.class.getName());
	private HashedObjectWrapper hashtable;
    
    /**
     *
     * @return
     */
    @Override
    public Collection<Images> readImages()
    {
        Collection<Images> images = new ArrayList<Images>();
        Transaction transaction;
        Query query;
        Iterator<Images> iterator;
        
        if (session == null)
        {
            loadService();
        }
        // else do nothing
               
        transaction = session.beginTransaction();        
        query = session.createQuery ("from Images");
        iterator = (Iterator<Images>)query.iterate();
        
        while (iterator.hasNext())
        {
            images.add (iterator.next());
        }

        transaction.commit();
        
        return images;
    }
		
    /**
     *
     * @param image
     * @param isUpdate
     * @return
     */
    @Override
	public boolean updateImage (Images image, boolean isUpdate) 
	{
		boolean result;
		
		if (isUpdate)
		{
			// TODO: complete update code later
			result = true;
		}
        else if (result = addObjectToHashtable (image))
        {
            result = merge (hashtable);
        }
		
		return result;
	}

    /**
     *
     * @param idImage
     * @return
     */
    @Override
	public Images readImage (Integer idImage)
	{
		return (Images)find (new Images (idImage));
	}

    /**
     *
     * @param brand
     * @return
     */
    @Override
	public boolean removeImage (Images brand)
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
		
		//validate input
		if (object != null && object instanceof Images)
		{
			Images image = (Images)object;
			hashtable.getHashtable().put (image.getIdImages(), image);
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
	
    @Override
	public boolean persist (HashedObjectWrapper object)
	{
		boolean result = true;
        Transaction transaction;
        Images newImage;
        Iterator iterator;
        Hashtable<Integer, IDomainObject> brandTable;
        Collection<Images> brands = new ArrayList<>();
        
		if (object != null)//validate
		{
			this.hashtable = object;
            
            if (session == null)
            {
                loadService();
            }
            // else do nothing
        
            transaction = session.beginTransaction();
            
            brandTable = hashtable.getHashtable();
            Set<Integer> keys = brandTable.keySet();
            
            keys.forEach ((key) ->
            {
                Images brand = (Images)brandTable.get (key);                                
                brands.add (brand);
            });
			
            iterator = brands.iterator();
            
            while (iterator.hasNext() && result)
            {
                newImage = (Images)iterator.next();
                session.persist (newImage);
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
        Images newImage;
        Iterator iterator;
        Hashtable<Integer, IDomainObject> imageTable;
        Collection<Images> images = new ArrayList<>();
        
		if (object != null)//validate
		{
			this.hashtable = object;
            
            if (session == null)
            {
                loadService();
            }
            // else do nothing
        
            transaction = session.beginTransaction();
            
            imageTable = hashtable.getHashtable();
            Set<Integer> keys = imageTable.keySet();
            
            keys.forEach ((key) ->
            {
                Images image = (Images)imageTable.get (key);                                
                images.add (image);
            });
			
            iterator = images.iterator();
            
            while (iterator.hasNext() && result)
            {
                newImage = (Images)iterator.next();
                session.save (newImage);
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
        Images image = (Images)object;
        
        if (session == null)
        {
            loadService();
        }
        // else do nothing
               
        transaction = session.beginTransaction();
        object = (Images)session.get (Images.class, image.getIdImages());
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
        // else do nothing
        
        transaction = session.beginTransaction();
        session.createSQLQuery ("SET FOREIGN_KEY_CHECKS = 0").executeUpdate();
        session.createSQLQuery ("TRUNCATE Images").executeUpdate();
        session.createSQLQuery ("SET FOREIGN_KEY_CHECKS = 1").executeUpdate();
        
        transaction.commit();
        result = true;
        
        return result;
    }
    
    public boolean merge (HashedObjectWrapper object)
    {
        boolean result = true;
        Transaction transaction;
        Images newImage;
        Iterator iterator;
        Hashtable<Integer, IDomainObject> imageTable;
        Collection<Images> brands = new ArrayList<>();
        
		if (object != null)//validate
		{
			this.hashtable = object;
            
            if (session == null)
            {
                loadService();
            }
            // else do nothing
        
            transaction = session.beginTransaction();
            
            imageTable = hashtable.getHashtable();
            Set<Integer> keys = imageTable.keySet();
            
            keys.forEach ((key) ->
            {
                Images brand = (Images)imageTable.get (key);                                
                brands.add (brand);
            });
			
            iterator = brands.iterator();
            
            while (iterator.hasNext() && result)
            {
                newImage = (Images)iterator.next();
                session.merge (newImage);
            }
            
            transaction.commit();
		}
		// else do nothing
		
		return result;
    }
}