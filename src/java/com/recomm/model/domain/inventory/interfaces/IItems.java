/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recomm.model.domain.inventory.interfaces;

import com.recomm.model.domain.inventory.interfaces.IBrand;
import com.recomm.model.domain.inventory.entity.Images;
import com.recomm.model.domain.inventory.entity.Prices;
import java.math.BigDecimal;
import java.util.Date;
import com.recomm.model.domain.interfaces.IDomainObject;

/**
 *
 * @author pgallen
 */
public abstract class IItems implements IDomainObject
{

    /**
     *
     */
    public IItems()
    {
    }

    /**
     *
     * @return
     */
    public abstract Date getCreation();

    /**
     *
     * @param creation
     */
    public abstract void setCreation (Date creation);

    /**
     *
     * @return
     */
    public abstract String getDefaultImage();

    /**
     *
     * @param defaultImage
     */
    public abstract void setDefaultImage (String defaultImage);

    /**
     *
     * @return
     */
    public abstract String getDescription();

    /**
     *
     * @param description
     */
    public abstract void setDescription (String description);

    /**
     *
     * @return
     */
    public abstract int getDiscount();

    /**
     *
     * @param discount
     */
    public abstract void setDiscount (int discount);

    /**
     *
     * @return
     */
    public abstract String getDist();

    /**
     *
     * @param dist
     */
    public abstract void setDist (String dist);

    /**
     *
     * @return
     */
    public abstract Date getEta();

    /**
     *
     * @param eta
     */
    public abstract void setEta (Date eta);

    /**
     *
     * @return
     */
    public abstract String getHazCode();

    /**
     *
     * @param hazCode
     */
    public abstract void setHazCode (String hazCode);

    /**
     *
     * @return
     */
    public abstract Integer getIdItems();

    /**
     *
     * @param idItems
     */
    public abstract void setIdItems (Integer idItems);

    /**
     *
     * @return
     */
    public abstract String getImageOrder();

    /**
     *
     * @param imageOrder
     */
    public abstract void setImageOrder (String imageOrder);

    /**
     *
     * @return
     */
    public abstract Images getImage();

    /**
     *
     * @param imagesCollection
     */
    public abstract void setImage (Images image);

    /**
     *
     * @return
     */
    public abstract int getInventory();

    /**
     *
     * @param inventory
     */
    public abstract void setInventory (int inventory);

    /**
     *
     * @return
     */
    public abstract IBrand getBrand();

    /**
     *
     * @param brand
     */
    public abstract void setBrand (IBrand brand);

    /**
     *
     * @return
     */
    public abstract BigDecimal getMap();

    /**
     *
     * @param map
     */
    public abstract void setMap (BigDecimal map);

    /**
     *
     * @return
     */
    public abstract int getMult();

    /**
     *
     * @param mult
     */
    public abstract void setMult (int mult);

    /**
     *
     * @return
     */
    public abstract BigDecimal getNet();

    /**
     *
     * @param net
     */
    public abstract void setNet (BigDecimal net);

    /**
     *
     * @return
     */
    public abstract String getPartName();

    /**
     *
     * @param partName
     */
    public abstract void setPartName (String partName);

    /**
     *
     * @return
     */
    public abstract String getPartNo();

    /**
     *
     * @param partNo
     */
    public abstract void setPartNo (String partNo);

    /**
     *
     * @return
     */
    public abstract String getPl();

    /**
     *
     * @param pl
     */
    public abstract void setPl (String pl);

    /**
     *
     * @return
     */
    public abstract String getQd();

    /**
     *
     * @param qd
     */
    public abstract void setQd (String qd);

    /**
     *
     * @return
     */
    public abstract int getQty();

    /**
     *
     * @param qty
     */
    public abstract void setQty (int qty);

    /**
     *
     * @return
     */
    public abstract String getRank();

    /**
     *
     * @param rank
     */
    public abstract void setRank (String rank);

    /**
     *
     * @return
     */
    public abstract Prices getRetail();

    /**
     *
     * @param retail
     */
    public abstract void setRetail (Prices retail);

    /**
     *
     * @return
     */
    public abstract String getShipCode();

    /**
     *
     * @param shipCode
     */
    public abstract void setShipCode (String shipCode);

    /**
     *
     * @return
     */
    public abstract String getStat();

    /**
     *
     * @param stat
     */
    public abstract void setStat (String stat);

    /**
     *
     * @return
     */
    public abstract String getUpc();

    /**
     *
     * @param upc
     */
    public abstract void setUpc (String upc);

    /**
     *
     * @return
     */
    public abstract Integer getUsersidUsers();

    /**
     *
     * @param usersidUsers
     */
    public abstract void setUsersidUsers (Integer usersidUsers);

    /**
     *
     * @return
     */
    public abstract boolean validate();
}
