/*
 * Copyright 2018 Random Logic Consulting and Paul G. Allen
 * All rights reserved.
 */
package com.recomm.model.domain.inventory.service.interfaces;

import com.recomm.model.domain.inventory.entity.Images;
import java.util.Collection;
import com.recomm.model.service.interfaces.Ipersist;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * mage Access Service interface
 * 
 * @author Paul G. Allen <pgallen@gmail.com>
 */
public interface IImageAccessSvc extends Ipersist
{		
    /**
     *
     * @param image
     * @param isUpdate
     * @return
     */
    public boolean updateImage (Images image, boolean isUpdate);
	
    /**
     *
     * @param idImage
     * @return
     */
    public Images readImage (Integer idImage);
    
    /**
     *
     * @return
     */
    Collection<Images> readImages();
	
    /**
     *
     * @param image
     * @return
     */
    public boolean removeImage (Images image);
}