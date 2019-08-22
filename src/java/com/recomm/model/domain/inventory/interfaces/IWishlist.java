/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recomm.model.domain.inventory.interfaces;

import com.recomm.model.domain.interfaces.IDomainObject;
import java.util.Date;
import com.recomm.model.domain.users.interfaces.ICustomer;

/**
 *
 * @author pgallen
 */
public abstract class IWishlist implements IDomainObject
{

    /**
     *
     * @return
     */
    public abstract Date getCreation ();

    /**
     *
     * @param creation
     */
    public abstract void setCreation (Date creation);

    /**
     *
     * @return
     */
    public abstract ICustomer getCustomer ();

    /**
     *
     * @param customer
     */
    public abstract void setCustomer (ICustomer customer);

    /**
     *
     * @return
     */
    public abstract int getItemsidItems ();

    /**
     *
     * @param itemsidItems
     */
    public abstract void setItemsidItems (int itemsidItems);

    /**
     *
     * @return
     */
    public abstract IWishlistPK getWishlistPK ();

    /**
     *
     * @param wishlistPK
     */
    public abstract void setWishlistPK (IWishlistPK wishlistPK);

    /**
     *
     * @return
     */
    public abstract boolean validate ();
}
