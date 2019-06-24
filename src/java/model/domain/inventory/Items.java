/*
 * Copyright (c) 2018 Random Logic Consulting and Paul G. Allen.
 * All Rights Reserved.
 */
package model.domain.inventory;

import model.domain.interfaces.IBrand;
import model.domain.interfaces.IItems;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.Proxy;

/**
 *
 * @author Paul G. Allen <pgallen@gmail.com>
 */
@Entity
@Proxy(lazy=false)
@Table
(
    catalog = "recomm", schema = "", uniqueConstraints =
    {
        @UniqueConstraint (columnNames = {"PartNo"}),
        @UniqueConstraint (columnNames = {"PartName"}),
        @UniqueConstraint (columnNames = {"idItems"})
    }
)
////@NamedQueries (
////{
////    @NamedQuery (name = "Items.findAll", query = "SELECT i FROM Items i")
////    , @NamedQuery (name = "Items.findByIdItems", query = "SELECT i FROM Items i WHERE i.idItems = :idItems")
////    , @NamedQuery (name = "Items.findByPartNo", query = "SELECT i FROM Items i WHERE i.partNo = :partNo")
////    , @NamedQuery (name = "Items.findByPartName", query = "SELECT i FROM Items i WHERE i.partName = :partName")
////    , @NamedQuery (name = "Items.findByUpc", query = "SELECT i FROM Items i WHERE i.upc = :upc")
////    , @NamedQuery (name = "Items.findByQty", query = "SELECT i FROM Items i WHERE i.qty = :qty")
////    , @NamedQuery (name = "Items.findByInventory", query = "SELECT i FROM Items i WHERE i.inventory = :inventory")
////    , @NamedQuery (name = "Items.findByMap", query = "SELECT i FROM Items i WHERE i.map = :map")
////    , @NamedQuery (name = "Items.findByNet", query = "SELECT i FROM Items i WHERE i.net = :net")
////    , @NamedQuery (name = "Items.findByPl", query = "SELECT i FROM Items i WHERE i.pl = :pl")
////    , @NamedQuery (name = "Items.findByDiscount", query = "SELECT i FROM Items i WHERE i.discount = :discount")
////    , @NamedQuery (name = "Items.findByHazCode", query = "SELECT i FROM Items i WHERE i.hazCode = :hazCode")
////    , @NamedQuery (name = "Items.findByMult", query = "SELECT i FROM Items i WHERE i.mult = :mult")
////    , @NamedQuery (name = "Items.findByQd", query = "SELECT i FROM Items i WHERE i.qd = :qd")
////    , @NamedQuery (name = "Items.findByEta", query = "SELECT i FROM Items i WHERE i.eta = :eta")
////    , @NamedQuery (name = "Items.findByShipCode", query = "SELECT i FROM Items i WHERE i.shipCode = :shipCode")
////    , @NamedQuery (name = "Items.findByStat", query = "SELECT i FROM Items i WHERE i.stat = :stat")
////    , @NamedQuery (name = "Items.findByRank", query = "SELECT i FROM Items i WHERE i.rank = :rank")
////    , @NamedQuery (name = "Items.findByDist", query = "SELECT i FROM Items i WHERE i.dist = :dist")
////    , @NamedQuery (name = "Items.findByDefaultImage", query = "SELECT i FROM Items i WHERE i.defaultImage = :defaultImage")
////    , @NamedQuery (name = "Items.findByImageOrder", query = "SELECT i FROM Items i WHERE i.imageOrder = :imageOrder")
////    , @NamedQuery (name = "Items.findByUsersidUsers", query = "SELECT i FROM Items i WHERE i.usersidUsers = :usersidUsers")
////    , @NamedQuery (name = "Items.findByCreation", query = "SELECT i FROM Items i WHERE i.creation = :creation")
////})
public class Items extends IItems implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Basic (optional = false)
    @Column (nullable = false)
    private Integer idItems;

    @Basic (optional = false)
    @Column (nullable = false, length = 45)
    private String partNo;

    @Basic (optional = false)
    @Column (nullable = false, length = 100)
    private String partName;

    @Lob
    @Column (length = 2147483647)
    private String description;

    @Column (length = 45)
    private String upc;

    @Basic (optional = false)
    @Column (nullable = false)
    private Integer qty;

    @Basic (optional = false)
    @Column (nullable = false)
    private Integer inventory = 0;

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic (optional = false)
    @Column (nullable = false, precision = 9, scale = 2)
    private BigDecimal map = new BigDecimal (0);

    @Basic (optional = false)
    @Column (nullable = false, precision = 9, scale = 2)
    private BigDecimal net = new BigDecimal (0);

    @Basic (optional = false)
    @Column (nullable = false, length = 10)
    private String pl = "";

    @Basic (optional = false)
    @Column (nullable = false)
    private int discount = 0;

    @Column (length = 45)
    private String hazCode;

    @Basic (optional = false)
    @Column (nullable = false)
    private int mult = 0;

    @Column (length = 45)
    private String qd;

    @Temporal (TemporalType.TIMESTAMP)
    private Date eta;

    @Column (length = 45)
    private String shipCode;

    @Column (length = 45)
    private String stat;

    @Column (length = 45)
    private String rank;

    @Basic (optional = false)
    @Column (nullable = false, length = 45)
    private String dist = "";

    @Basic (optional = false)
    @Column (nullable = false, length = 100)
    private String defaultImage = "";

    @Column (length = 45)
    private String imageOrder;

    @Column (name = "Users_idUsers")
    private Integer usersidUsers;

    @Basic (optional = false)
    @Column (nullable = false)
    @Temporal (TemporalType.TIMESTAMP)
    private Date creation;

    @JoinColumn (name = "Manufacturer_idManufacturer", referencedColumnName = "idManufacturer")
    @ManyToOne (fetch = FetchType.EAGER)
    private Manufacturer brand;

    @JoinColumn (name = "Retail", referencedColumnName = "idPrices", nullable = false)
    @ManyToOne (fetch = FetchType.EAGER, optional = false)
    private Prices retail;

    @JoinColumn (name = "images_idImages", referencedColumnName = "idImages", nullable = false)
    @ManyToOne (fetch = FetchType.EAGER, optional = false)
    private Images image;

    public Items()
    {
    }

    public Items (Integer idItems)
    {
        this.idItems = idItems;
    }

    public Items (Integer idItems,
                  String partNo,
                  String name,
                  String description,
                  String upc,
                  int qty,
                  int inventory,
                  BigDecimal map,
                  BigDecimal net,
                  String pl,
                  int discount,
                  int mult,
                  Date eta,
                  String hazCode,
                  Date creation
                 )
    {
        this.idItems = idItems;
        this.partNo = partNo;
        this.partName = name;
        this.description = description;
        this.upc = upc;
        this.qty = qty;
        this.inventory = inventory;
        this.map = map;
        this.net = net;
        this.pl = pl;
        this.discount = discount;
        this.mult = mult;
        this.eta = eta;
        this.hazCode = hazCode;
        this.creation = creation;
    }

    @Override
    public Integer getIdItems()
    {
        return idItems;
    }

    @Override
    public void setIdItems (Integer idItems)
    {
        this.idItems = idItems;
    }

    @Override
    public String getPartNo()
    {
        return partNo;
    }

    @Override
    public void setPartNo (String partNo)
    {
        this.partNo = partNo;
    }

    @Override
    public String getPartName()
    {
        return partName;
    }

    @Override
    public void setPartName (String partName)
    {
        this.partName = partName;
    }

    @Override
    public String getDescription()
    {
        return description;
    }

    @Override
    public void setDescription (String description)
    {
        this.description = description;
    }

    @Override
    public String getUpc()
    {
        return upc;
    }

    @Override
    public void setUpc (String upc)
    {
        this.upc = upc;
    }

    @Override
    public int getQty()
    {
        return qty;
    }

    @Override
    public void setQty (int qty)
    {
        this.qty = qty;
    }

    @Override
    public int getInventory()
    {
        return inventory;
    }

    public void setInventory (int inventory)
    {
        this.inventory = inventory;
    }

    @Override
    public BigDecimal getMap()
    {
        return map;
    }

    @Override
    public void setMap (BigDecimal map)
    {
        this.map = map;
    }

    @Override
    public BigDecimal getNet()
    {
        return net;
    }

    @Override
    public void setNet (BigDecimal net)
    {
        this.net = net;
    }

    @Override
    public String getPl()
    {
        return pl;
    }

    @Override
    public void setPl (String pl)
    {
        this.pl = pl;
    }

    @Override
    public int getDiscount()
    {
        return discount;
    }

    @Override
    public void setDiscount (int discount)
    {
        this.discount = discount;
    }

    @Override
    public String getHazCode()
    {
        return hazCode;
    }

    @Override
    public void setHazCode (String hazCode)
    {
        this.hazCode = hazCode;
    }

    @Override
    public int getMult()
    {
        return mult;
    }

    @Override
    public void setMult (int mult)
    {
        this.mult = mult;
    }

    @Override
    public String getQd()
    {
        return qd;
    }

    @Override
    public void setQd (String qd)
    {
        this.qd = qd;
    }

    @Override
    public Date getEta()
    {
        return eta;
    }

    @Override
    public void setEta (Date eta)
    {
        this.eta = eta;
    }

    @Override
    public String getShipCode()
    {
        return shipCode;
    }

    @Override
    public void setShipCode (String shipCode)
    {
        this.shipCode = shipCode;
    }

    @Override
    public String getStat()
    {
        return stat;
    }

    @Override
    public void setStat (String stat)
    {
        this.stat = stat;
    }

    @Override
    public String getRank()
    {
        return rank;
    }

    @Override
    public void setRank (String rank)
    {
        this.rank = rank;
    }

    @Override
    public String getDist()
    {
        return dist;
    }

    @Override
    public void setDist (String dist)
    {
        this.dist = dist;
    }

    @Override
    public String getDefaultImage()
    {
        return defaultImage;
    }

    @Override
    public void setDefaultImage (String defaultImage)
    {
        this.defaultImage = defaultImage;
    }

    @Override
    public String getImageOrder()
    {
        return imageOrder;
    }

    @Override
    public void setImageOrder (String imageOrder)
    {
        this.imageOrder = imageOrder;
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

    @Override
    public Manufacturer getBrand()
    {
        return brand;
    }

    @Override
    public void setBrand (IBrand brand)
    {
        this.brand = (Manufacturer)brand;
    }

    @Override
    public Prices getRetail()
    {
        return retail;
    }

    @Override
    public void setRetail (Prices retail)
    {
        this.retail = retail;
    }

    @Override
    public Images getImage()
    {
        return image;
    }

    @Override
    public void setImage (Images image)
    {
        this.image = image;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean validate()
    {
    	if ((this.idItems == null)
            || (this.partNo == null)
            || (this.partName == null)
            || (this.description == null)
            || (this.upc == null)
            || (this.qty == null)
            || (this.inventory == null)
// TODO: Implement when test complete            || (this.retail == null)
            || (this.map == null)
            || (this.net == null)
            || (this.creation == null)
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
    public int hashCode ()
    {
        int hash = 0;
        hash += (idItems != null ? idItems.hashCode () : 0);
        return hash;
    }

    @Override
    public boolean equals (Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if ( ! (object instanceof Items))
        {
            return false;
        }

        Items other = (Items)object;

        if ((this.idItems == null && other.idItems != null) || (this.idItems != null && ! this.idItems.equals (other.idItems)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString ()
    {
        return "com.recomm.model.domain.inventory.Items[ idItems=" + idItems + " ]";
    }

}
