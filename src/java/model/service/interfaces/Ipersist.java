/*
 * Copyright 2018, Random Logic Consulting and Paul G. ALlen.
 * All Rights Reserved.
 */
package model.service.interfaces;

import java.util.Collection;
import model.service.dao.HashedObjectWrapper;
import model.domain.interfaces.IDomainObject;
import model.domain.interfaces.ISearchParms;

/**
 * Persistence interface
 *
 * @author Paul G. Allen <pgallen@gmail.com>
 */
public interface Ipersist extends IService
{
    /**
     *
     * @param hashtable
     * @return
     */
    boolean persist (Collection<?> object);

    /**
     *
     * @param object
     * @return
     */
    public Collection<?> find (IDomainObject object);

    public boolean delete (String type);

    boolean save (Collection<?> object);

    boolean merge (Collection<?> object);

    Collection<?> search (ISearchParms searchParms);
}
