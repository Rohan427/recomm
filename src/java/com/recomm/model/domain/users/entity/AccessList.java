/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recomm.model.domain.users.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author pgallen
 */
@Entity
@Table (name = "AccessList")
public class AccessList implements Serializable
{
    private static final long serialVersionUID = 8L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer idAccessList;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    private String type;

    @Basic(optional = false)
    @NotNull
    private int level;

    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date creation;

    @JoinTable(name = "Users_has_AccessList", joinColumns = {
        @JoinColumn(name = "AccessList_idAccessList", referencedColumnName = "idAccessList")}, inverseJoinColumns = {
        @JoinColumn(name = "Users_idUsers", referencedColumnName = "idUsers")})
    @ManyToMany
    private Collection<Users> usersCollection;

    public AccessList()
    {
    }

    public AccessList(Integer idAccessList)
    {
        this.idAccessList = idAccessList;
    }

    public AccessList (Integer idAccessList, String type, int level, Date creation)
    {
        this.idAccessList = idAccessList;
        this.type = type;
        this.level = level;
        this.creation = creation;
    }

    public Integer getIdAccessList()
    {
        return idAccessList;
    }

    public void setIdAccessList (Integer idAccessList)
    {
        this.idAccessList = idAccessList;
    }

    public String getType()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    public int getLevel()
    {
        return level;
    }

    public void setLevel (int level)
    {
        this.level = level;
    }

    public Date getCreation()
    {
        return creation;
    }

    public void setCreation (Date creation)
    {
        this.creation = creation;
    }

    @XmlTransient
    public Collection<Users> getUsersCollection()
    {
        return usersCollection;
    }

    public void setUsersCollection (Collection<Users> usersCollection)
    {
        this.usersCollection = usersCollection;
    }

    @Override
    public int hashCode()
    {
        int hash = 0;

        hash += (idAccessList != null ? idAccessList.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals (Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AccessList))
        {
            return false;
        }

        AccessList other = (AccessList) object;

        if ((this.idAccessList == null && other.idAccessList != null) || (this.idAccessList != null && !this.idAccessList.equals (other.idAccessList)))
        {
            return false;
        }

        return true;
    }

    @Override
    public String toString()
    {
        return "Database.AccessList[ idAccessList=" + idAccessList + " ]";
    }

}
