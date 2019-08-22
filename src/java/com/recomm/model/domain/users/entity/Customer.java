/*
 * Copyright 2018, Random Logic Consulting and Paul G. ALlen. All Rights Reserved.
 */
package com.recomm.model.domain.users.entity;

import com.recomm.model.domain.users.interfaces.IAddress;
import com.recomm.model.domain.inventory.interfaces.ICart;
import com.recomm.model.domain.users.interfaces.ICustomer;
import com.recomm.model.domain.users.interfaces.IUsers;
import com.recomm.model.domain.inventory.interfaces.IWishlist;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Embedded;
import org.hibernate.annotations.Proxy;

/**
 * Customer domain object class
 *
 * @author Paul G. Allen <pgallen@gmail.com>
 */
@Entity
@Proxy (lazy=false)
@Table (name = "customer", catalog = "customer", schema = "", uniqueConstraints =
        {
            @UniqueConstraint (columnNames =
            {
                "idCustomer"
            })
})
@XmlRootElement
public class Customer extends ICustomer implements Serializable
{
    private static final long serialVersionUID = 23L;

    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Basic (optional = false)
    @Column (name="idCustomer", nullable = false)
    @Id
    private Integer idCustomer = 0;

    @Basic (optional = false)
    private String firstName;

    @Basic
    private String mi;

    @Basic (optional = false)
    private String lName;

    @Embedded
    private Address address;

    @Basic (optional = false)
    private String email;

    @Basic (optional = false)
    @Temporal (TemporalType.TIMESTAMP)
    private Date created;

    @Transient
    private Users usersidUsers;

    @Transient
    private Collection<IWishlist> wishlistCollection;

    @Transient
    private Collection<ICart> cartCollection;

    @Transient
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
