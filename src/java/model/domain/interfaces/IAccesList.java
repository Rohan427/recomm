/*
 * Copyright 2018, Random Logic Consulting and Paul G. ALlen. All Rights Reserved.
 */
package model.domain.interfaces;

import java.util.Collection;
import java.util.Date;

/**
 * AccessList abstract class
 * 
 * @author Paul G. Allen <pgallen@gmail.com>
 */
public abstract class IAccesList implements IDomainObject
{
    /**
     * Default constructor
     */
    public IAccesList()
    {
    }

    /**
     *
     * @return Creation Date
     */
    public abstract Date getCreation();

    /**
     *
     * @param creation The Creation Date
     */
    public abstract void setCreation (Date creation);

    /**
     *
     * @return AccessList index
     */
    public abstract Integer getIdAccessList();

    /**
     *
     * @param idAccessList AccessList index
     */
    public abstract void setIdAccessList (Integer idAccessList);

    /**
     *
     * @return Access level
     */
    public abstract int getLevel();

    /**
     *
     * @param level Access level
     */
    public abstract void setLevel (int level);

    /**
     *
     * @return Access level type (name)
     */
    public abstract String getType();

    /**
     *
     * @param type Access level type (name)
     */
    public abstract void setType (String type);

    /**
     *
     * @return List of Users with this access level
     */
    public abstract Collection<IUsers> getUsersCollection();

    /**
     *
     * @param usersCollection List of Users with this access level
     */
    public abstract void setUsersCollection (Collection<IUsers> usersCollection);

    /**
     *
     * @return true is this object is valid, otherwise false
     */
    public abstract boolean validate();
}
