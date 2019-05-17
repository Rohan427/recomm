/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.domain.interfaces;

import model.domain.inventory.Items;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author pgallen
 */
public abstract class IBrand implements IDomainObject
{

    /**
     *
     */
    public IBrand()
    {
    }

    /**
     *
     * @param other
     * @return
     */
    public abstract boolean compareBrandKey (IBrand other);

    /**
     *
     * @return
     */
    public abstract String getAddress1();

    /**
     *
     * @param address1
     */
    public abstract void setAddress1 (String address1);

    /**
     *
     * @return
     */
    public abstract String getAddress2();

    /**
     *
     * @param address2
     */
    public abstract void setAddress2 (String address2);

    /**
     *
     * @return
     */
    public abstract String getCity();

    /**
     *
     * @param city
     */
    public abstract void setCity (String city);

    /**
     *
     * @return
     */
    public abstract String getCountry();

    /**
     *
     * @param country
     */
    public abstract void setCountry (String country);

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
    public abstract Integer getIdBrand();

    /**
     *
     * @param idBrand
     */
    public abstract void setIdBrand (Integer idBrand);

    /**
     *
     * @return
     */
    public abstract Collection<Items> getItemsCollection();

    /**
     *
     * @param itemsCollection
     */
    public abstract void setItemsCollection (Collection<Items> itemsCollection);

    /**
     *
     * @return
     */
    public abstract String getBrandName();

    /**
     *
     * @param name
     */
    public abstract void setBrandName (String name);

    /**
     *
     * @return
     */
    public abstract String getBrandKey();

    /**
     *
     * @param BrandKey
     */
    public abstract void setBrandKey (String BrandKey);

    /**
     *
     * @return
     */
    public abstract String getPhone();

    /**
     *
     * @param phone
     */
    public abstract void setPhone (String phone);

    /**
     *
     * @return
     */
    public abstract String getState();

    /**
     *
     * @param state
     */
    public abstract void setState (String state);

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
    public abstract String getZip();

    /**
     *
     * @param zip
     */
    public abstract void setZip (String zip);
    
    /**
     *
     * @return
     */
    public abstract boolean validate();
}
