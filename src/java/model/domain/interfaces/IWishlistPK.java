/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.domain.interfaces;

/**
 *
 * @author pgallen
 */
public abstract class IWishlistPK implements IDomainObject
{

    /**
     *
     */
    public IWishlistPK ()
    {
    }

    /**
     *
     * @return
     */
    public abstract int getCustomeridCustomer ();

    /**
     *
     * @param customeridCustomer
     */
    public abstract void setCustomeridCustomer (int customeridCustomer);

    /**
     *
     * @return
     */
    public abstract int getIdWishlist ();

    /**
     *
     * @param idWishlist
     */
    public abstract void setIdWishlist (int idWishlist);

    /**
     *
     * @return
     */
    public abstract boolean validate ();
}
