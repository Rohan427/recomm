/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recomm.model.domain.inventory.util;

import com.recomm.model.domain.interfaces.ISearchParms;

/**
 *
 * @author pgallen
 */

public class CartSearchParams implements ISearchParms
{
    private String id = "%%";
    private String idCustomer = "%%";
    private String idItem = "%%";
    private int limit = 25;
    private int lastIndex = 0;
    private String orderBy = "customer_idCustomer";
    private boolean asc = true;
    private String idUser = "%%";

    public CartSearchParams()
    {

    }

    public String getIdUser()
    {
        return this.idUser;
    }

    public void setIdUser (String idUser)
    {
        this.idUser = idUser;
    }

    public String getOrderBy()
    {
        return orderBy;
    }

    public void setOrderBy (String orderBy)
    {
        this.orderBy = orderBy;
    }

    public boolean isAsc()
    {
        return asc;
    }

    public void setAsc (boolean asc)
    {
        this.asc = asc;
    }

    public String getId()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getIdCustomer()
    {
        return idCustomer;
    }

    public void setIdCustomer (String idCustomer)
    {
        this.idCustomer = idCustomer;
    }

    public String getIdItem()
    {
        return idItem;
    }

    public void setIdItem (String idItem)
    {
        this.idItem = idItem;
    }

    public int getLimit()
    {
        return limit;
    }

    public void setLimit (int limit)
    {
        this.limit = limit;
    }

    public void setLastIndex (int lastIndex)
    {
        this.lastIndex = lastIndex;
    }

    public int getLastIndex()
    {
        return lastIndex;
    }

    @Override
    public boolean equals (Object object)
    {
        boolean result = false;
        CartSearchParams other = (CartSearchParams)object;

        if (other != null)
        {
            result = true;

            if (this.idCustomer == null ? other.getIdCustomer() != null : !this.idCustomer.equals (other.getIdCustomer()))
            {
                result = false;
            }

            if (this.idItem == null ? other.getIdItem() != null : !this.idItem.equals (other.getIdItem()))
            {
                result = false;
            }

            if (this.id == null ? other.getId() != null : !this.id.equals (other.getId()))
            {
                result = false;
            }
        }

        return result;
    }
}
