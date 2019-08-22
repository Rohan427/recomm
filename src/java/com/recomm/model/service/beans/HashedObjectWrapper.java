/*
 * Copyright 2018, Random Logic Consulting and Paul G. ALlen. All Rights Reserved.
 */
package com.recomm.model.service.beans;

import java.util.Hashtable;

import com.recomm.model.service.interfaces.IHashedObject;
import com.recomm.model.domain.interfaces.IDomainObject;

/**
 * Object used to wrap generic Hashtable objects so they can be converted to
 * more specific types.
 * 
 * @author Paul G. Allen <pgallen@gmail.com>
 */
public class HashedObjectWrapper implements IHashedObject
{
	private Hashtable<Integer, IDomainObject> hashtable = null;

    /**
     *
     */
    public HashedObjectWrapper()
	{
		hashtable = new Hashtable<>();
	}

    /**
     *
     * @param hashtable
     */
    public HashedObjectWrapper (Hashtable<Integer, IDomainObject> hashtable)
	{
		this.hashtable = hashtable;
	}

    /**
     *
     * @param table
     */
    public void setHashtable (Hashtable<Integer, IDomainObject> table)
	{
		hashtable = table;
	}

    /**
     *
     * @return
     */
    public Hashtable<Integer, IDomainObject> getHashtable()
	{
		return hashtable;
	}
}