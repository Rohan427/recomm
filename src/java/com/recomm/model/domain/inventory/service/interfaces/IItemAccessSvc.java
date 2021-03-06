/*
 * Copyright 2018 Random Logic Consulting and Paul G. Allen
 * All rights reserved.
 */
package com.recomm.model.domain.inventory.service.interfaces;

import com.recomm.model.domain.inventory.entity.Items;
import java.util.Collection;
import com.recomm.model.service.interfaces.Ipersist;
import org.hibernate.Session;

/**
 * Item Access Service interface
 * 
 * @author Paul G. Allen <pgallen@gmail.com>
 */
public interface IItemAccessSvc extends Ipersist
{
    /**
     *
     * @param item
     * @param isUpdate
     * @return
     */
    public boolean updateItem (Items item, boolean isUpdate);
    
    /**
     *
     * @return
     */
    public Collection<Items> readItems();
    
    /**
     *
     * @param idItem
     * @return
     */
    public Items readItem (Integer idItem);
	
    /**
     *
     * @param item
     * @return
     */
    public boolean removeItem (Items item);
}