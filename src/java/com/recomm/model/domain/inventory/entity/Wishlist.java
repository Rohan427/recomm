/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recomm.model.domain.inventory.entity;

import com.recomm.model.domain.interfaces.IWishlist;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.Proxy;

/**
 *
 * @author pgallen
 */
@Entity
@Proxy (lazy=false)
@Table
(
    name = "wishlist", catalog = "recomm", schema = "", uniqueConstraints =
    {
        @UniqueConstraint
        (
            columnNames =
            {
                "idwishlist"
            }
        )
    }
)
public class Wishlist extends IWishlist implements Serializable
{
    private static final long serialVersionUID = 25L;

    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Basic (optional = false)
    @Column (name="idwishlist", nullable = false)
    @Id
    private Integer idWishlist;

    @Basic (optional = false)
    @NotNull
    private int qty;

    @Basic (optional = false)
    @NotNull
    @Temporal (TemporalType.TIMESTAMP)
    private Date creation;

    @Basic (optional = false)
    @NotNull
    @Column (name = "customer_idCustomer")
    private Integer customerIdCustomer;

    @Basic(optional = false)
    @NotNull
    @Column (name = "Items_idItems")
    private int itemsIdItems;

    public Wishlist()
    {
    }

    public Wishlist (Integer idWishlist)
    {
        this.idWishlist = idWishlist;
    }

    public Wishlist (Integer idWishlist,
                     int qty,
                     Date creation,
                     Integer customerIdCustomer,
                     Integer itemsidItems
                    )
    {
        this.idWishlist = idWishlist;
        this.qty = qty;
        this.creation = creation;
        this.customerIdCustomer = customerIdCustomer;
        this.itemsIdItems = itemsidItems;
    }

    public Wishlist (Integer idWishlist, Integer customerIdCustomer)
    {
        this.idWishlist = idWishlist;
        this.customerIdCustomer = customerIdCustomer;
    }

    @Override
    public Integer getIdWishlist()
    {
        return this.idWishlist;
    }

    @Override
    public void setIdWishlist (Integer idWishlist)
    {
        this.idWishlist = idWishlist;
    }

    @Override
    public int getQty()
    {
        return qty;
    }

    @Override
    public void setQty (int qty)
    {
        this.qty = qty;
    }

    @Override
    public Date getCreation()
    {
        return creation;
    }

    @Override
    public void setCreation (Date creation)
    {
        this.creation = creation;
    }

    @Override
    public Integer getCustomerIdCustomer()
    {
        return this.customerIdCustomer;
    }

    @Override
    public void setCustomerIdCustomer (Integer customerIdCustomer)
    {
        this.customerIdCustomer = customerIdCustomer;
    }

    @Override
    public int getItemsidItems()
    {
        return itemsIdItems;
    }

    @Override
    public void setItemsidItems (int itemsidItems)
    {
        this.itemsIdItems = itemsidItems;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;

        hash += (idWishlist != null ? idWishlist.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals (Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Wishlist))
        {
            return false;
        }

        Wishlist other = (Wishlist) object;

        if ((this.idWishlist == null && other.idWishlist != null) || (this.idWishlist != null && this.idWishlist != other.idWishlist))
        {
            return false;
        }

        return true;
    }

    @Override
    public String toString()
    {
        return "Database.Wishlist[ idWishlist=" + idWishlist + " ]";
    }
}
