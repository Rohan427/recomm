/*
 * Copyright 2018, Random Logic Consulting and Paul G. ALlen.
 * All Rights Reserved.
 */
package model.service.interfaces;

import model.service.dao.HashedObjectWrapper;
import model.domain.interfaces.IDomainObject;
import org.hibernate.Session;

/**
 * Persistence interface
 * 
 * @author Paul G. Allen <pgallen@gmail.com>
 */
public interface Ipersist extends IService
{
    /**
     *
     * @param object
     * @return
     */
    boolean addObjectToHashtable (IDomainObject object);
	
    /**
     *
     * @return
     */
    HashedObjectWrapper getHashtable();
	
    /**
     *
     * @param hashtable
     * @return
     */
    boolean persist (HashedObjectWrapper hashtable);
	
    /**
     *
     * @param object
     * @return
     */
    public IDomainObject find (IDomainObject object);
    
    public boolean delete (String type);
}
