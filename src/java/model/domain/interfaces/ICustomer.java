/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.domain.interfaces;

import java.util.Collection;
import java.util.Date;

/**
 *
 * @author pgallen
 */
public abstract class ICustomer implements IDomainObject
{

    /**
     *
     */
    public ICustomer ()
    {
    }

    /**
     *
     * @return
     */
    public abstract IAddress getAddress ();

    /**
     *
     * @param address
     */
    public abstract void setAddress (IAddress address);

    /**
     *
     * @return
     */
    public abstract Collection<ICart> getCartCollection ();

    /**
     *
     * @param cartCollection
     */
    public abstract void setCartCollection (Collection<ICart> cartCollection);
    
    /**
     *
     * @return
     */
    public abstract Date getCreated ();

    /**
     *
     * @param created
     */
    public abstract void setCreated (Date created);

    /**
     *
     * @return
     */
    public abstract String getEmail ();

    /**
     *
     * @param email
     */
    public abstract void setEmail (String email);

    /**
     *
     * @return
     */
    public abstract String getFirstName ();

    /**
     *
     * @param firstName
     */
    public abstract void setFirstName (String firstName);

    /**
     *
     * @return
     */
    public abstract Integer getIdCustomer ();

    /**
     *
     * @param idCustomer
     */
    public abstract void setIdCustomer (Integer idCustomer);

    /**
     *
     * @return
     */
    public abstract String getLName ();

    /**
     *
     * @param lName
     */
    public abstract void setLName (String lName);

    /**
     *
     * @return
     */
    public abstract String getMi ();

    /**
     *
     * @param mi
     */
    public abstract void setMi (String mi);
    
    /**
     *
     * @return
     */
    public abstract IUsers getUsersidUsers ();

    /**
     *
     * @param usersidUsers
     */
    public abstract void setUsersidUsers (IUsers usersidUsers);

    /**
     *
     * @return
     */
    public abstract Collection<IWishlist> getWishlistCollection ();

    /**
     *
     * @param wishlistCollection
     */
    public abstract void setWishlistCollection (Collection<IWishlist> wishlistCollection);
    
    /**
     *
     * @return
     */
    public abstract boolean validate ();
}
