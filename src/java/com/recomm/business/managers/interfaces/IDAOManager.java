/*
 * Copyright 2018, Random Logic Consulting and Paul G. ALlen. All Rights Reserved.
 */
package com.recomm.business.managers.interfaces;

import java.util.Collection;

/**
 *
 * @author Paul G. Allen <pgallen@gmail.com>
 */
public interface IDAOManager 
{
    /**
     * Reads all objects from the data store
     * 
     * @param type Name of the domain objects to read
     * @return A Collection of the objects read
     */
    Collection<?> readAll (String type);
	
    /**
     * Reads a range of object from the data store
     * 
     * @param type Name of the domain objects to read
     * @param min Index of first object to read
     * @param max Index of last object to read
     * @return A Collection of the objects read
     */
    Collection<?> readRange (String type, Integer min, Integer max);
	
    /**
     * Read the next object from the data store
     * 
     * @param type Name of the domain objects to read
     * @return The object read as a single entry in a Collection
     */
    Collection<?> readNext (String type);
    
    /**
     * Deletes the DAO containing the objects given by <b>type</b>. Removes the
     * physical representation of the object records.
     * 
     * @param type The DAO type to delete
     * @return true if DAO deleted, false if not
     */
    boolean deleteAll (String type);
}

