/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.domain.inventory;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import model.domain.interfaces.ICart;
import model.domain.interfaces.ICartPK;
import model.domain.interfaces.ICustomer;
import model.domain.users.Customer;

/**
 *
 * @author pgallen
 */
@Entity
@Table (name = "Cart")
@XmlRootElement
@NamedQueries
({
    @NamedQuery(name = "Cart.findAll", query = "SELECT c FROM Cart c"),
    @NamedQuery(name = "Cart.findByIdCart", query = "SELECT c FROM Cart c WHERE c.cartPK.idCart = :idCart"),
    @NamedQuery(name = "Cart.findByQty", query = "SELECT c FROM Cart c WHERE c.qty = :qty"),
    @NamedQuery(name = "Cart.findByCreation", query = "SELECT c FROM Cart c WHERE c.creation = :creation"),
    @NamedQuery(name = "Cart.findByCustomeridCustomer", query = "SELECT c FROM Cart c WHERE c.cartPK.customeridCustomer = :customeridCustomer"),
    @NamedQuery(name = "Cart.findByItemsidItems", query = "SELECT c FROM Cart c WHERE c.itemsidItems = :itemsidItems"),
    @NamedQuery(name = "Cart.findByCustAndItem", query = "SELECT c FROM Cart c WHERE c.itemsidItems = :itemsidItems AND c.cartPK.customeridCustomer = :customeridCustomer")
})
public class Cart extends ICart implements Serializable
{
    private static final long serialVersionUID = 25L;
    @EmbeddedId
    protected CartPK cartPK;

    @Basic(optional = false)
    @NotNull
    private int qty;

    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date creation;

    @JoinColumn(name = "Customer_idCustomer", referencedColumnName = "idCustomer", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private ICustomer customer;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Items_idItems")
    private int itemsidItems;

    public Cart()
    {
    }

    public Cart (CartPK cartPK)
    {
        this.cartPK = cartPK;
    }

    public Cart (CartPK cartPK,
                 int qty,
                 Date creation,
                 ICustomer customer,
                 int itemsidItems
                )
    {
        this.cartPK = cartPK;
        this.qty = qty;
        this.creation = creation;
        this.customer = customer;
        this.itemsidItems = itemsidItems;
    }

    public Cart (int idCart, int customeridCustomer)
    {
        this.cartPK = new CartPK (idCart, customeridCustomer);
    }

    @Override
    public ICartPK getCartPK()
    {
        return cartPK;
    }

    @Override
    public void setCartPK (ICartPK cartPK)
    {
        this.cartPK = (CartPK)cartPK;
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
    public ICustomer getCustomer()
    {
        return customer;
    }

    @Override
    public void setCustomer (ICustomer customer)
    {
        this.customer = customer;
    }

    @Override
    public int getItemsidItems()
    {
        return itemsidItems;
    }

    @Override
    public void setItemsidItems (int itemsidItems)
    {
        this.itemsidItems = itemsidItems;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (cartPK != null ? cartPK.hashCode() : 0);
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

        if ((this.cartPK == null && other.cartPK != null) || (this.cartPK != null && !this.cartPK.equals (other.cartPK)))
        {
            return false;
        }

        return true;
    }

    @Override
    public String toString()
    {
        return "Database.Cart[ cartPK=" + cartPK + " ]";
    }
}
