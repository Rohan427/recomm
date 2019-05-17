/*
 * Copyright 2018, Random Logic Consulting and Paul G. ALlen.
 * All Rights Reserved.
 */
package model.service.interfaces;

import model.domain.inventory.Manufacturer;
import java.util.Collection;

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
    boolean updateBrand (Manufacturer brand, boolean isUpdate);
	
    /**
     *
     * @param idBrand
     * @return
     */
    Manufacturer readBrand (Integer idBrand);
    
    /**
     *
     * @return
     */
    Collection<Manufacturer> readBrands();
	
    /**
     *
     * @param brand
     * @return
     */
    boolean removeBrand (Manufacturer brand);
}