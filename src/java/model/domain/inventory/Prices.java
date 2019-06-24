/*
 * Copyright (c) 2018 Random Logic Consulting and Paul G. Allen.
 * All Rights Reserved.
 */
package model.domain.inventory;

import model.domain.interfaces.IPrices;
import java.io.Serializable;
import java.math.BigDecimal;
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
@Proxy (lazy=false)
@Table (catalog = "recomm", schema = "", uniqueConstraints =
        {
            @UniqueConstraint (columnNames =
            {
                "idPrices"
            })
})
@XmlRootElement
@NamedQueries (
{
    @NamedQuery (name = "Prices.findAll", query = "SELECT p FROM Prices p"),
    @NamedQuery (name = "Prices.findByIdPrices", query = "SELECT p FROM Prices p WHERE p.idPrices = :idPrices"),
    @NamedQuery (name = "Prices.findByPrice", query = "SELECT p FROM Prices p WHERE p.price = :price"),
    @NamedQuery (name = "Prices.findByCreation", query = "SELECT p FROM Prices p WHERE p.creation = :creation")
})
public class Prices extends IPrices implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Basic (optional = false)
    @Column (nullable = false)
    private Integer idPrices;

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic (optional = false)
    @Column (nullable = false, precision = 9, scale = 2)
    private BigDecimal price;

    @Basic (optional = false)
    @Column (nullable = false)
    @Temporal (TemporalType.TIMESTAMP)
    private Date creation;

    @OneToMany (fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "retail")
    private Collection<Items> itemsCollection;

    public Prices ()
    {
    }

    public Prices (Integer idPrices)
    {
        this.idPrices = idPrices;
    }

    public Prices (Integer idPrices, BigDecimal price, Date creation)
    {
        this.idPrices = idPrices;
        this.price = price;
        this.creation = creation;
    }

    public Integer getIdPrices ()
    {
        return idPrices;
    }

    public void setIdPrices (Integer idPrices)
    {
        this.idPrices = idPrices;
    }

    public BigDecimal getPrice ()
    {
        return price;
    }

    public void setPrice (BigDecimal price)
    {
        this.price = price;
    }

    public Date getCreation ()
    {
        return creation;
    }

    public void setCreation (Date creation)
    {
        this.creation = creation;
    }

    @XmlTransient
    public Collection<Items> getItemsCollection ()
    {
        return itemsCollection;
    }

    public void setItemsCollection (Collection<Items> itemsCollection)
    {
        this.itemsCollection = itemsCollection;
    }

    @Override
    public int hashCode ()
    {
        int hash = 0;
        hash += (idPrices != null ? idPrices.hashCode () : 0);
        return hash;
    }

    @Override
    public boolean equals (Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if ( ! (object instanceof Prices))
        {
            return false;
        }

        Prices other = (Prices)object;

        if ((this.idPrices == null && other.idPrices != null) || (this.idPrices != null && ! this.idPrices.equals (other.idPrices)))
        {
            return false;
        }

        return true;
    }

    @Override
    public String toString()
    {
        return "com.recomm.model.domain.inventory.Prices[ idPrices=" + idPrices + " ]";
    }
}
