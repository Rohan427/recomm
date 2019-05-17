/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.domain.interfaces;

import java.util.Date;

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

    /**
     *
     * @return
     */
    public abstract ICartPK getCartPK ();

    /**
     *
     * @param cartPK
     */
    public abstract void setCartPK (ICartPK cartPK);

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
    public abstract int getQty ();

    /**
     *
     * @param qty
     */
    public abstract void setQty (int qty);

}
