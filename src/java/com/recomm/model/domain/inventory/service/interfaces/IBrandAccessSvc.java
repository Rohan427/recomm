/*
 * Copyright 2018, Random Logic Consulting and Paul G. ALlen.
 * All Rights Reserved.
 */
package com.recomm.model.domain.inventory.service.interfaces;

import com.recomm.model.domain.inventory.entity.Manufacturer;
import com.recomm.model.domain.inventory.interfaces.IBrand;
import java.util.Collection;
import com.recomm.model.service.interfaces.Ipersist;

/**
 * Manufacturer Access Service interface
 *
 * @author Paul G. Allen <pgallen@gmail.com>
 */
public interface IBrandAccessSvc extends Ipersist
{

    /**
     *
     * @param brand
     * @param isUpdate
     * @return
     */
    boolean updateBrand (IBrand brand, boolean isUpdate);

    /**
     *
     * @param idBrand
     * @return
     */
    IBrand readBrand (Integer idBrand);

    /**
     *
     * @return
     */
    Collection<IBrand> readBrands();

    /**
     *
     * @param brand
     * @return
     */
    boolean removeBrand (IBrand brand);
}