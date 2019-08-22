/*
 * Copyright 2018 Random Logic Consulting and Paul G. Allen
 * All rights reserved.
 */
package com.recomm.model.domain.inventory.service.interfaces;

import com.recomm.model.domain.inventory.entity.Prices;
import java.util.Collection;
import com.recomm.model.service.interfaces.Ipersist;

/**
 * Price Access Service interface
 *
 * @author Paul G. Allen <pgallen@gmail.com>
 */
public interface IPriceAccessSvc extends Ipersist
{
    /**
     *
     * @return
     */
    public Collection<Prices> readPrices ();

    /**
     *
     * @param price
     * @param isUpdate
     * @return
     */
    public boolean updatePrice (Prices price, boolean isUpdate);

    /**
     *
     * @param idPrice
     * @return
     */
    public Prices readPrice (Integer idPrice);

    /**
     *
     * @param price
     * @return
     */
    public boolean removePrice (Prices price);
}