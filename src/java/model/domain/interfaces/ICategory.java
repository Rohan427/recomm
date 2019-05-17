/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.domain.interfaces;

import java.util.Collection;
import java.util.Date;

/**
 *
 * @author pgallen
 */
public abstract class ICategory implements IDomainObject
{

    /**
     *
     */
    public ICategory ()
    {
    }

    /**
     *
     * @return
     */
    public abstract String getCatKey ();

    /**
     *
     * @param key
     */
    public abstract void setCatKey (String key);

    /**
     *
     * @return
     */
    public abstract String getCatName ();

    /**
     *
     * @param name
     */
    public abstract void setCatName (String name);

    /**
     *
     * @return
     */
    public abstract Date getCreation ();

    /**
     *
     * @param creation
     */
    public abstract void setCreation (Date creation);

    /**
     *
     * @return
     */
    public abstract String getDescription ();

    /**
     *
     * @param description
     */
    public abstract void setDescription (String description);

    /**
     *
     * @return
     */
    public abstract Integer getIdCategory ();

    /**
     *
     * @param idCategory
     */
    public abstract void setIdCategory (Integer idCategory);

    /**
     *
     * @return
     */
    public abstract Collection<IItems> getItemsCollection ();

    /**
     *
     * @param itemsCollection
     */
    public abstract void setItemsCollection (Collection<IItems> itemsCollection);

    /**
     *
     * @return
     */
    public abstract boolean validate ();
}
