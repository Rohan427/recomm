/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recomm.model.domain.interfaces;

import java.util.Date;

/**
 *
 * @author Paul G. Allen <pgallen@gmail.com>
 */
public abstract class IWishlist implements IDomainObject
{
    public abstract Date getCreation();

    public abstract Integer getCustomerIdCustomer();

    public abstract Integer getIdWishlist();

    public abstract int getItemsidItems();

    public abstract int getQty();

    public abstract void setCreation (Date creation);

    public abstract void setCustomerIdCustomer (Integer customerIdCustomer);

    public abstract void setIdWishlist (Integer idWishlist);

    public abstract void setItemsidItems (int itemsidItems);

    public abstract void setQty (int qty);
}
