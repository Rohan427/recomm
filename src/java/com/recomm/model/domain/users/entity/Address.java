/*
 * Copyright 2018, Random Logic Consulting and Paul G. ALlen. All Rights Reserved.
 */
package com.recomm.model.domain.users.entity;

import java.io.Serializable;
import java.util.Date;

import com.recomm.model.domain.users.interfaces.IAddress;
import javax.persistence.Basic;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

/**
 * Address domain object class
 *
 * @author Paul G. Allen <pgallen@gmail.com>
 */
@Embeddable
public class Address extends IAddress implements Serializable
{
    @Transient
    private Integer idAddress = 0;

    @Basic
    private String address1;

    @Basic
    private String address2;

    @Basic
    private String city;

    @Basic
    private String state;

    @Basic
    private String country;

    @Basic
    private String zipCode;

    @Basic
    private String phone1;

    @Basic
    private String phone2;

    @Basic
    private String phone3;

    @Transient
    private Date created;

    @Transient
    private int cachedHashCode;

    /**
     *
     */
    public Address()
    {
    }

    /**
     *
     * @param idAddress
     */
    public Address (Integer idAddress)
    {
        this.idAddress = idAddress;
    }

    /**
     *
     * @param idAddress
     * @param address1
     * @param address2
     * @param city
     * @param state
     * @param country
     * @param zipCode
     * @param created
     */
    public Address (Integer idAddress,
                    String address1,
                    String address2,
                    String city,
                    String state,
                    String country,
                    String zipCode,
                    Date created
                   )
    {
        this.idAddress = idAddress;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipCode = zipCode;
        this.created = created;
    }

    /* (non-Javadoc)
	 * @see com.recomm.model.domain.users.IAddress#getIdAddress()
	 */

    /**
     *
     * @return
     */

    @Override
	public Integer getIdAddress()
    {
        return idAddress;
    }

    /* (non-Javadoc)
	 * @see com.recomm.model.domain.users.IAddress#setIdAddress(java.lang.Integer)
	 */

    /**
     *
     * @param idAddress
     */

    @Override
	public void setIdAddress (Integer idAddress)
    {
        this.idAddress = idAddress;
    }

    /* (non-Javadoc)
	 * @see com.recomm.model.domain.users.IAddress#getAddress1()
	 */

    /**
     *
     * @return
     */

    @Override
	public String getAddress1()
    {
        return address1;
    }

    /* (non-Javadoc)
	 * @see com.recomm.model.domain.users.IAddress#setAddress1(java.lang.String)
	 */

    /**
     *
     * @param address1
     */

    @Override
	public void setAddress1 (String address1)
    {
        this.address1 = address1;
    }

    /* (non-Javadoc)
	 * @see com.recomm.model.domain.users.IAddress#getAddress2()
	 */

    /**
     *
     * @return
     */

    @Override
	public String getAddress2()
    {
        return address2;
    }

    /* (non-Javadoc)
	 * @see com.recomm.model.domain.users.IAddress#setAddress2(java.lang.String)
	 */

    /**
     *
     * @param address2
     */

    @Override
	public void setAddress2 (String address2)
    {
        this.address2 = address2;
    }

    /* (non-Javadoc)
	 * @see com.recomm.model.domain.users.IAddress#getCity()
	 */

    /**
     *
     * @return
     */

    @Override
	public String getCity()
    {
        return city;
    }

    /* (non-Javadoc)
	 * @see com.recomm.model.domain.users.IAddress#setCity(java.lang.String)
	 */

    /**
     *
     * @param city
     */

    @Override
	public void setCity (String city)
    {
        this.city = city;
    }

    /* (non-Javadoc)
	 * @see com.recomm.model.domain.users.IAddress#getState()
	 */

    /**
     *
     * @return
     */

    @Override
	public String getState()
    {
        return state;
    }

    /* (non-Javadoc)
	 * @see com.recomm.model.domain.users.IAddress#setState(java.lang.String)
	 */

    /**
     *
     * @param state
     */

    @Override
	public void setState (String state)
    {
        this.state = state;
    }

    /* (non-Javadoc)
	 * @see com.recomm.model.domain.users.IAddress#getCountry()
	 */

    /**
     *
     * @return
     */

    @Override
	public String getCountry()
    {
        return country;
    }

    /* (non-Javadoc)
	 * @see com.recomm.model.domain.users.IAddress#setCountry(java.lang.String)
	 */

    /**
     *
     * @param country
     */

    @Override
	public void setCountry (String country)
    {
        this.country = country;
    }

    /* (non-Javadoc)
	 * @see com.recomm.model.domain.users.IAddress#getZipCode()
	 */

    /**
     *
     * @return
     */

    @Override
	public String getZipCode()
    {
        return zipCode;
    }

    /* (non-Javadoc)
	 * @see com.recomm.model.domain.users.IAddress#setZipCode(java.lang.String)
	 */

    /**
     *
     * @param zipCode
     */

    @Override
	public void setZipCode (String zipCode)
    {
        this.zipCode = zipCode;
    }

    /* (non-Javadoc)
	 * @see com.recomm.model.domain.users.IAddress#getPhone1()
	 */

    /**
     *
     * @return
     */

    @Override
	public String getPhone1()
    {
        return phone1;
    }

    /* (non-Javadoc)
	 * @see com.recomm.model.domain.users.IAddress#setPhone1(java.lang.String)
	 */

    /**
     *
     * @param phone1
     */

    @Override
	public void setPhone1 (String phone1)
    {
        this.phone1 = phone1;
    }

    /* (non-Javadoc)
	 * @see com.recomm.model.domain.users.IAddress#getPhone2()
	 */

    /**
     *
     * @return
     */

    @Override
	public String getPhone2()
    {
        return phone2;
    }

    /* (non-Javadoc)
	 * @see com.recomm.model.domain.users.IAddress#setPhone2(java.lang.String)
	 */

    /**
     *
     * @param phone2
     */

    @Override
	public void setPhone2 (String phone2)
    {
        this.phone2 = phone2;
    }

    /* (non-Javadoc)
	 * @see com.recomm.model.domain.users.IAddress#getPhone3()
	 */

    /**
     *
     * @return
     */

    @Override
	public String getPhone3()
    {
        return phone3;
    }

    /* (non-Javadoc)
	 * @see com.recomm.model.domain.users.IAddress#setPhone3(java.lang.String)
	 */

    /**
     *
     * @param phone3
     */

    @Override
	public void setPhone3 (String phone3)
    {
        this.phone3 = phone3;
    }

    /* (non-Javadoc)
	 * @see com.recomm.model.domain.users.IAddress#getCreated()
	 */

    /**
     *
     * @return
     */

    @Override
	public Date getCreated()
    {
        return created;
    }

    /* (non-Javadoc)
	 * @see com.recomm.model.domain.users.IAddress#setCreated(java.util.Date)
	 */

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
    public boolean validate()
    {
    	if ((this.idAddress == null)
            || (this.address1 == null)
            || (this.city == null)
            || (this.state == null)
            || (this.country == null)
            || (this.zipCode == null)
            || (this.created == null)
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
			String varstr = idAddress + address1 + address2 + city + state + country + zipCode + phone1 + phone2 + phone3 + created;
			hc = varstr.hashCode();
		}

		return hc;
    }

    @Override
    public boolean equals (Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Address))
        {
            return false;
        }

        Address other = (Address) object;

        if ((this.idAddress == null && other.idAddress != null) || (this.idAddress != null && !this.idAddress.equals (other.idAddress)))
        {
            return false;
        }

        return true;
    }

    @Override
    public String toString()
    {
        return "com.recomm.model.domain.users.Address[ idAddress=" + idAddress + " ]";
    }
}
