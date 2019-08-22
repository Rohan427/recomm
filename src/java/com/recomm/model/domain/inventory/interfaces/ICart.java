/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recomm.model.domain.inventory.interfaces;

import java.util.Date;
import com.recomm.model.domain.interfaces.IDomainObject;

/**
 *
 * @author pgallen
 */
public abstract class ICart implements IDomainObject
{

    /**
     *
     */
    public ICart ()
    {
    }

    public abstract void setIdCart (Integer idCart);

    public abstract Integer getIdCart();

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
    public abstract int getQty ();

    /**
     *
     * @param qty
     */
    public abstract void setQty (int qty);

    public abstract void setCustomerIdCustomer (Integer customerIdCustomer);

    public abstract Integer getCustomerIdCustomer();
}
