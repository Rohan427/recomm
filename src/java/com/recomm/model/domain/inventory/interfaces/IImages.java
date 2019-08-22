/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recomm.model.domain.inventory.interfaces;

import com.recomm.model.domain.inventory.entity.Items;
import java.util.Collection;
import java.util.Date;
import com.recomm.model.domain.interfaces.IDomainObject;

/**
 *
 * @author pgallen
 */
public abstract class IImages implements IDomainObject
{

    /**
     *
     */
    public IImages ()
    {
    }

    /**
     *
     * @return
     */
    public abstract Date getCreation();

    /**
     *
     * @param creation
     */
    public abstract void setCreation (Date creation);

    /**
     *
     * @return
     */
    public abstract Integer getIdImages();

    /**
     *
     * @param idImages
     */
    public abstract void setIdImages (Integer idImages);

    /**
     *
     * @return
     */
    public abstract String getImagePath();

    /**
     *
     * @param imagePath
     */
    public abstract void setImagePath (String imagePath);

    /**
     *
     * @return
     */
    public abstract Collection<Items> getItemsCollection();

    /**
     *
     * @param itemsCollection
     */
    public abstract void setItemsCollection (Collection<Items> itemsCollection);

    /**
     *
     * @return
     */
    public abstract Integer getUsersidUsers();

    /**
     *
     * @param usersidUsers
     */
    public abstract void setUsersidUsers (Integer usersidUsers);

    /**
     *
     * @return
     */
    public abstract boolean validate ();
}
