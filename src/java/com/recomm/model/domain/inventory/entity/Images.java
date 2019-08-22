/*
 * Copyright (c) 2018 Random Logic Consulting and Paul G. Allen.
 * All Rights Reserved.
 */
package com.recomm.model.domain.inventory.entity;

import com.recomm.model.domain.inventory.interfaces.IImages;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
                    "idImages"
                })
            }
       )
@XmlRootElement
@NamedQueries (
{
    @NamedQuery (name = "Images.findAll", query = "SELECT i FROM Images i"),
    @NamedQuery (name = "Images.findByIdImages", query = "SELECT i FROM Images i WHERE i.idImages = :idImages"),
    @NamedQuery (name = "Images.findByImagePath", query = "SELECT i FROM Images i WHERE i.imagePath = :imagePath"),
    @NamedQuery (name = "Images.findByUsersidUsers", query = "SELECT i FROM Images i WHERE i.usersidUsers = :usersidUsers"),
    @NamedQuery (name = "Images.findByCreation", query = "SELECT i FROM Images i WHERE i.creation = :creation")
})
public class Images extends IImages implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Basic (optional = false)
    @Column (nullable = false)
    private Integer idImages;

    @Basic (optional = false)
    @Column (nullable = false, length = 255)
    private String imagePath;

    @Column (name = "Users_idUsers")
    private Integer usersidUsers;

    @Basic (optional = false)
    @Column (nullable = false)
    @Temporal (TemporalType.TIMESTAMP)
    private Date creation;

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "image")
    private Collection<Items> itemsCollection;

    public Images()
    {
    }

    public Images (Integer idImages)
    {
        this.idImages = idImages;
    }

    public Images (Integer idImages,
                   String imagePath,
                   Integer usersidUsers,
                   Date creation
                  )
    {
        this.idImages = idImages;
        this.imagePath = imagePath;
        this.usersidUsers = usersidUsers;
        this.creation = creation;
    }

    @Override
    public Integer getIdImages()
    {
        return idImages;
    }

    @Override
    public void setIdImages (Integer idImages)
    {
        this.idImages = idImages;
    }

    @Override
    public String getImagePath()
    {
        return imagePath;
    }

    @Override
    public void setImagePath (String imagePath)
    {
        this.imagePath = imagePath;
    }

    @Override
    public Integer getUsersidUsers()
    {
        return usersidUsers;
    }

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
    public int hashCode()
    {
        int hash = 0;
        hash += (idImages != null ? idImages.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals (Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if ( ! (object instanceof Images))
        {
            return false;
        }

        Images other = (Images)object;

        if ((this.idImages == null && other.idImages != null) || (this.idImages != null && ! this.idImages.equals (other.idImages)))
        {
            return false;
        }

        return true;
    }

    @Override
    public String toString()
    {
        return imagePath;
    }

}
