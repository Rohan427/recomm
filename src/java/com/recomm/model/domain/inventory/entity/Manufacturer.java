/*
 * Copyright (c) 2018 Random Logic Consulting and Paul G. Allen.
 * All Rights Reserved.
 */
package com.recomm.model.domain.inventory.entity;

import com.recomm.model.domain.inventory.interfaces.IBrand;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.Proxy;

/**
 *
 * @author Paul G. Allen <pgallen@gmail.com>
 */
@Entity
@Proxy(lazy=false)
@Table (catalog = "recomm", schema = "", uniqueConstraints =
        {
            @UniqueConstraint (columnNames =
            {
                "MfgKey"
            })
            , @UniqueConstraint (columnNames =
            {
                "idManufacturer"
            })
})
@XmlRootElement
@NamedQueries (
{
    @NamedQuery (name = "Manufacturer.findAll", query = "SELECT m FROM Manufacturer m")
    , @NamedQuery (name = "Manufacturer.findByIdManufacturer", query = "SELECT m FROM Manufacturer m WHERE m.idBrand = :idManufacturer")
    , @NamedQuery (name = "Manufacturer.findByMfgKey", query = "SELECT m FROM Manufacturer m WHERE m.brandKey = :mfgKey")
    , @NamedQuery (name = "Manufacturer.findByManName", query = "SELECT m FROM Manufacturer m WHERE m.brandName = :manName")
    , @NamedQuery (name = "Manufacturer.findByAddress1", query = "SELECT m FROM Manufacturer m WHERE m.address1 = :address1")
    , @NamedQuery (name = "Manufacturer.findByAddress2", query = "SELECT m FROM Manufacturer m WHERE m.address2 = :address2")
    , @NamedQuery (name = "Manufacturer.findByCity", query = "SELECT m FROM Manufacturer m WHERE m.city = :city")
    , @NamedQuery (name = "Manufacturer.findByState", query = "SELECT m FROM Manufacturer m WHERE m.state = :state")
    , @NamedQuery (name = "Manufacturer.findByCountry", query = "SELECT m FROM Manufacturer m WHERE m.country = :country")
    , @NamedQuery (name = "Manufacturer.findByZip", query = "SELECT m FROM Manufacturer m WHERE m.zip = :zip")
    , @NamedQuery (name = "Manufacturer.findByPhone", query = "SELECT m FROM Manufacturer m WHERE m.phone = :phone")
    , @NamedQuery (name = "Manufacturer.findByUsersidUsers", query = "SELECT m FROM Manufacturer m WHERE m.usersidUsers = :usersidUsers")
    , @NamedQuery (name = "Manufacturer.findByCreation", query = "SELECT m FROM Manufacturer m WHERE m.creation = :creation")
})
public class Manufacturer extends IBrand implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Basic (optional = false)
    @Column (name="idManufacturer", nullable = false)
    private Integer idBrand;

    @Basic (optional = false)
    @Column (name="MfgKey", nullable = false, length = 45)
    private String brandKey;

    @Basic (optional = false)
    @Column (name="ManName", nullable = false, length = 45)
    private String brandName;

    @Column (length = 45)
    private String address1;

    @Column (length = 45)
    private String address2;

    @Column (length = 45)
    private String city;

    @Column (length = 45)
    private String state;

    @Column (length = 45)
    private String country;

    @Column (length = 45)
    private String zip;

    @Column (length = 45)
    private String phone;

    @Column (name = "Users_idUsers")
    private Integer usersidUsers;

    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creation;

    @OneToMany (fetch = FetchType.EAGER, mappedBy = "brand")
    private Collection<Items> itemsCollection;

    public Manufacturer()
    {
    }

    public Manufacturer (Integer idManufacturer)
    {
        this.idBrand = idManufacturer;
    }

    public Manufacturer (Integer idBrand,
                         String brandKey,
                         String name,
                         Date creation,
                         Integer users
                        )
    {
        this.idBrand = idBrand;
        this.brandKey = brandKey;
        this.brandName = name;
        this.creation = creation;
        this.usersidUsers = users;
    }

    @Override
    public Integer getIdBrand ()
    {
        return idBrand;
    }

    @Override
    public void setIdBrand (Integer idBrand)
    {
        this.idBrand = idBrand;
    }

    @Override
    public String getBrandKey ()
    {
        return brandKey;
    }

    @Override
    public void setBrandKey (String brandKey)
    {
        this.brandKey = brandKey;
    }

    @Override
    public String getBrandName ()
    {
        return brandName;
    }

    @Override
    public void setBrandName (String brandName)
    {
        this.brandName = brandName;
    }

    @Override
    public String getAddress1 ()
    {
        return address1;
    }

    @Override
    public void setAddress1 (String address1)
    {
        this.address1 = address1;
    }

    @Override
    public String getAddress2 ()
    {
        return address2;
    }

    @Override
    public void setAddress2 (String address2)
    {
        this.address2 = address2;
    }

    @Override
    public String getCity ()
    {
        return city;
    }

    @Override
    public void setCity (String city)
    {
        this.city = city;
    }

    @Override
    public String getState ()
    {
        return state;
    }

    @Override
    public void setState (String state)
    {
        this.state = state;
    }

    @Override
    public String getCountry ()
    {
        return country;
    }

    @Override
    public void setCountry (String country)
    {
        this.country = country;
    }

    @Override
    public String getZip ()
    {
        return zip;
    }

    @Override
    public void setZip (String zip)
    {
        this.zip = zip;
    }

    @Override
    public String getPhone ()
    {
        return phone;
    }

    @Override
    public void setPhone (String phone)
    {
        this.phone = phone;
    }

    @Override
    public Integer getUsersidUsers()
    {
        return usersidUsers;
    }

    @Override
    public void setUsersidUsers (Integer usersidUsers)
    {
        this.usersidUsers = usersidUsers;
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

    @XmlTransient
    @Override
    public Collection<Items> getItemsCollection()
    {
        return itemsCollection;
    }

    @Override
    public void setItemsCollection (Collection<Items> itemsCollection)
    {
        this.itemsCollection = itemsCollection;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean validate()
    {
    	return true;
    }

    @Override
    public int hashCode ()
    {
        int hash = 0;
        hash += (idBrand != null ? idBrand.hashCode () : 0);
        return hash;
    }

    @Override
    public boolean equals (Object object)
    {
        boolean result = true;

        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Manufacturer))
        {
            result = false;
        }
        else
        {
            Manufacturer other = (Manufacturer) object;

            if ((this.brandName == null && other.brandName != null) ||
                (this.brandName != null && !this.brandName.equalsIgnoreCase (other.brandName))
               )
            {
                result = false;
            }
            else if ((this.brandKey == null && other.brandKey != null) ||
                     (this.brandKey != null && !this.brandKey.equals (other.brandKey))
                    )
            {
                result = false;
            }
        }

        return result;
    }

    /**
     *
     * @param other
     * @return
     */
    @Override
    public boolean compareBrandKey (IBrand other)
    {
        boolean result = true;

        if (!this.brandKey.equals (other.getBrandKey()))
        {
            result = false;
        }

        return result;
    }

    @Override
    public String toString()
    {
        return "" + this.brandName;
    }
}
