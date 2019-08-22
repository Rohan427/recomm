/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recomm.model.domain.inventory.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import com.recomm.model.domain.inventory.interfaces.ICart;
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
    name = "cart", catalog = "recomm", schema = "", uniqueConstraints =
    {
        @UniqueConstraint
        (
            columnNames =
            {
                "idCart"
            }
        )
    }
)
public class Cart extends ICart implements Serializable
{
    private static final long serialVersionUID = 25L;

    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Basic (optional = false)
    @Column (name="idCart", nullable = false)
    @Id
    private Integer idCart;

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

    public Cart()
    {
    }

    public Cart (Integer idCart)
    {
        this.idCart = idCart;
    }

    public Cart (Integer idCart,
                 int qty,
                 Date creation,
                 Integer customerIdCustomer,
                 Integer itemsidItems
                )
    {
        this.idCart = idCart;
        this.qty = qty;
        this.creation = creation;
        this.customerIdCustomer = customerIdCustomer;
        this.itemsIdItems = itemsidItems;
    }

    public Cart (Integer idCart, Integer customerIdCustomer)
    {
        this.idCart = idCart;
        this.customerIdCustomer = customerIdCustomer;
    }

    @Override
    public Integer getIdCart()
    {
        return this.idCart;
    }

    @Override
    public void setIdCart (Integer idCart)
    {
        this.idCart = idCart;
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

        hash += (idCart != null ? idCart.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals (Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cart))
        {
            return false;
        }

        Cart other = (Cart) object;

        if ((this.idCart == null && other.idCart != null) || (this.idCart != null && this.idCart != other.idCart))
        {
            return false;
        }

        return true;
    }

    @Override
    public String toString()
    {
        return "Database.Cart[ idCart=" + idCart + " ]";
    }
}
