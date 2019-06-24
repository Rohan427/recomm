/*
 * Copyright 2018, Random Logic Consulting and Paul G. ALlen. All Rights Reserved.
 */
package model.domain.users;

import model.domain.interfaces.IAddress;
import model.domain.interfaces.ICart;
import model.domain.interfaces.ICustomer;
import model.domain.interfaces.IUsers;
import model.domain.interfaces.IWishlist;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 * Customer domain object class
 *
 * @author Paul G. Allen <pgallen@gmail.com>
 */
public class Customer extends ICustomer implements Serializable
{
    private static final long serialVersionUID = 23L;

    private Integer idCustomer = 0;

    private String firstName;

    private String mi;

    private String lName;

    private Address address;

    private String email;

    private Date created;

    private Users usersidUsers;

    private Collection<IWishlist> wishlistCollection;

    private Collection<ICart> cartCollection;

    private int cachedHashCode;

    /**
     *
     */
    public Customer()
    {
    }

    /**
     *
     * @param idCustomer
     */
    public Customer (Integer idCustomer)
    {
        this.idCustomer = idCustomer;
    }

    /**
     *
     * @param firstName
     * @param lName
     * @param user
     */
    public Customer (String firstName,
                     String lName,
                     IUsers user
                    )
    {
        this.firstName = firstName;
        this.lName = lName;
        this.usersidUsers = (Users)user;
    }

    /**
     *
     * @param idCustomer
     * @param firstName
     * @param lName
     * @param address1
     * @param address2
     * @param city
     * @param state
     * @param country
     * @param zipCode
     * @param created
     */
    public Customer (Integer idCustomer,
                     String firstName,
                     String lName,
                     String address1,
                     String address2,
                     String city,
                     String state,
                     String country,
                     String zipCode,
                     Date created
                    )
    {
    	this.address = new Address (0,
					                address1,
					                address2,
					                city,
					                state,
					                country,
					                zipCode,
					                created
					               );
        this.idCustomer = idCustomer;
        this.firstName = firstName;
        this.lName = lName;
        this.created = created;
    }

    /**
     *
     * @return
     */
    @Override
    public Integer getIdCustomer()
    {
        return idCustomer;
    }

    /**
     *
     * @param idCustomer
     */
    @Override
    public void setIdCustomer (Integer idCustomer)
    {
        this.idCustomer = idCustomer;
    }

    /**
     *
     * @return
     */
    @Override
    public String getFirstName()
    {
        return firstName;
    }

    /**
     *
     * @param firstName
     */
    @Override
    public void setFirstName (String firstName)
    {
        this.firstName = firstName;
    }

    /**
     *
     * @return
     */
    @Override
    public String getMi()
    {
        return mi;
    }

    /**
     *
     * @param mi
     */
    @Override
    public void setMi (String mi)
    {
        this.mi = mi;
    }

    /**
     *
     * @return
     */
    @Override
    public String getLName()
    {
        return lName;
    }

    /**
     *
     * @param lName
     */
    @Override
    public void setLName (String lName)
    {
        this.lName = lName;
    }

    /**
     *
     * @return
     */
    @Override
    public Address getAddress()
    {
        return address;
    }

    /**
     *
     * @param address
     */
    @Override
    public void setAddress (IAddress address)
    {
        this.address = (Address)address;
    }

    /**
     *
     * @return
     */
    @Override
    public String getEmail()
    {
        return email;
    }

    /**
     *
     * @param email
     */
    @Override
    public void setEmail (String email)
    {
        this.email = email;
    }

    /**
     *
     * @return
     */
    @Override
    public Date getCreated()
    {
        return created;
    }

    /**
     *
     * @param created
     */
    @Override
    public void setCreated (Date created)
    {
        this.created = created;
    }

    /**
     *
     * @return
     */
    @Override
    public Users getUsersidUsers()
    {
        return usersidUsers;
    }

    /**
     *
     * @param usersidUsers
     */
    @Override
    public void setUsersidUsers (IUsers usersidUsers)
    {
        this.usersidUsers = (Users)usersidUsers;
    }

    /**
     *
     * @return
     */
    @Override
    public Collection<IWishlist> getWishlistCollection()
    {
        return wishlistCollection;
    }

    /**
     *
     * @param wishlistCollection
     */
    @Override
    public void setWishlistCollection (Collection<IWishlist> wishlistCollection)
    {
        this.wishlistCollection = wishlistCollection;
    }

    /**
     *
     * @return
     */
    @Override
    public Collection<ICart> getCartCollection()
    {
        return cartCollection;
    }

    /**
     *
     * @param cartCollection
     */
    @Override
    public void setCartCollection (Collection<ICart> cartCollection)
    {
        this.cartCollection = cartCollection;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean validate()
    {
    	if ((idCustomer == null)
    		|| (firstName == null)
            || (lName == null)
            || (address == null)
            || (created == null)
           )
    	{
    		return false;
    	}
    	else
    	{
    		return true;
    	}
    }

    @Override
    public int hashCode()
    {
    	int hc = cachedHashCode;

		if (hc == 0)
		{
			String varstr = idCustomer + firstName + mi + lName + address + email + created + usersidUsers;
			hc = varstr.hashCode();
		}

		return hc;
    }

    @Override
    public boolean equals (Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Customer))
        {
            return false;
        }

        Customer other = (Customer) object;

        if ((this.idCustomer == null && other.idCustomer != null) || (this.idCustomer != null && !this.idCustomer.equals (other.idCustomer)))
        {
            return false;
        }

        return true;
    }

    @Override
    public String toString()
    {
        return "com.recomm.model.domain.users[ idCustomer=" + idCustomer + " firstName=" + firstName + " lName=" + lName + "]";
    }
}
