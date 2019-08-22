/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recomm.model.domain.users.interfaces;

import com.recomm.model.domain.interfaces.IDomainObject;
import com.recomm.model.domain.inventory.interfaces.IItems;
import com.recomm.model.domain.inventory.interfaces.IImages;
import com.recomm.model.domain.inventory.interfaces.IBrand;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author pgallen
 */
public abstract class IUsers implements IDomainObject
{

    /**
     *
     */
    public IUsers ()
    {
    }

    public abstract String getA2();

    public abstract void setA2 (String a2);

    public abstract void setValid (boolean valid);

    /**
     *
     * @return
     */
    public abstract Integer getA();

    /**
     *
     * @param a
     */
    public abstract void setA (Integer a);

    /**
     *
     * @return
     */
    public abstract Collection<IAccesList> getAccessListCollection();

    /**
     *
     * @param accessListCollection
     */
    public abstract void setAccessListCollection (Collection<IAccesList> accessListCollection);

    /**
     *
     * @return
     */
    public abstract Integer getB();

    /**
     *
     * @param b
     */
    public abstract void setB (Integer b);

    /**
     *
     * @return
     */
    public abstract Integer getC();

    /**
     *
     * @param c
     */
    public abstract void setC (Integer c);

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
    public abstract Collection<ICustomer> getCustomerCollection();

    /**
     *
     * @param customerCollection
     */
    public abstract void setCustomerCollection (Collection<ICustomer> customerCollection);

    /**
     *
     * @return
     */
    public abstract Integer getD();

    /**
     *
     * @param d
     */
    public abstract void setD (Integer d);

    /**
     *
     * @return
     */
    public abstract String getFirst();

    /**
     *
     * @param first
     */
    public abstract void setFirst (String first);

    /**
     *
     * @return
     */
    public abstract Integer getIdUsers();

    /**
     *
     * @param idUsers
     */
    public abstract void setIdUsers (Integer idUsers);

    /**
     *
     * @return
     */
    public abstract Collection<IImages> getImagesCollection();

    /**
     *
     * @param imagesCollection
     */
    public abstract void setImagesCollection (Collection<IImages> imagesCollection);

    /**
     *
     * @return
     */
    public abstract Collection<IItems> getItemsCollection();

    /**
     *
     * @param itemsCollection
     */
    public abstract void setItemsCollection (Collection<IItems> itemsCollection);

    /**
     *
     * @return
     */
    public abstract String getLast();

    /**
     *
     * @param last
     */
    public abstract void setLast (String last);

    /**
     *
     * @return
     */
    public abstract Collection<IBrand> getManufacturerCollection();

    /**
     *
     * @param manufacturerCollection
     */
    public abstract void setManufacturerCollection (Collection<IBrand> manufacturerCollection);

    /**
     *
     */
    public abstract void inValidate();

    /**
     *
     * @return
     */
    public abstract boolean isPwReset();

    /**
     *
     * @param pwReset
     */
    public abstract void setPwReset (boolean pwReset);

    /**
     *
     * @return
     */
    public abstract String getSecretKey();

    /**
     *
     * @param secretKey
     */
    public abstract void setSecretKey (String secretKey);

    /**
     *
     * @return
     */
    public abstract String getUserName();

    /**
     *
     * @param userName
     */
    public abstract void setUserName (String userName);

    /**
     *
     * @return
     */
    public abstract boolean validate();

    public abstract boolean isValid();
}
