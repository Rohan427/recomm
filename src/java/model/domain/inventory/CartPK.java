/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.domain.inventory;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import model.domain.interfaces.ICartPK;

/**
 *
 * @author pgallen
 */
@Embeddable
public class CartPK extends ICartPK implements Serializable
{
    @Basic(optional = false)
    private int idCart;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Customer_idCustomer")
    private int customeridCustomer;

    public CartPK()
    {
    }

    public CartPK (int idCart, int customeridCustomer)
    {
        this.idCart = idCart;
        this.customeridCustomer = customeridCustomer;
    }

    @Override
    public int getIdCart()
    {
        return idCart;
    }

    @Override
    public void setIdCart (int idCart)
    {
        this.idCart = idCart;
    }

    @Override
    public int getCustomeridCustomer()
    {
        return customeridCustomer;
    }

    @Override
    public void setCustomeridCustomer (int customeridCustomer)
    {
        this.customeridCustomer = customeridCustomer;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (int) idCart;
        hash += (int) customeridCustomer;
        return hash;
    }

    @Override
    public boolean equals (Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CartPK))
        {
            return false;
        }

        CartPK other = (CartPK) object;

        if (this.idCart != other.idCart)
        {
            return false;
        }

        if (this.customeridCustomer != other.customeridCustomer)
        {
            return false;
        }

        return true;
    }

    @Override
    public String toString()
    {
        return "Database.CartPK[ idCart=" + idCart + ", customeridCustomer=" + customeridCustomer + " ]";
    }

}
