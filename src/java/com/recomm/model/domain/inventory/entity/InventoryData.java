/*
 * Copyright (c) 2018 Random Logic Consulting and Paul G. Allen
 * All Rights Reserved
 */
package com.recomm.model.domain.inventory.entity;

import com.recomm.model.domain.inventory.entity.Images;
import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author Paul G. Allen <pgallen@gmail.com>
 */
public class InventoryData implements Serializable
{
    private static final long serialVersionUID = 12345L;
    private Collection<Manufacturer> brands;
    private Collection<Images> images;
    private Collection<Items> items;
    private Items item;
    private Prices price;
    private Manufacturer brand;
    private Images image;
    private String request;
    
    public InventoryData()
    {
        
    }
    
    /**
     * Initialize the controller, preparing it to send a request to the server.
     * 
     * @param item      An Items entity
     * @param price     A Price entity
     * @param brand     A Manufacturer entity
     * @param image     An Images entity
     * @param request   The request being made
     *      Request List:
     *          GetBrandFromItem
     *          GetPriceFromItem
     *          GetImageFromItem
     *          ReadBrands
     *          ReadImages
     *          CreateItem
     *          DeleteAllTables
     *          CreatePrice
     *          CreateBrand
     *          CreateImage
     */
    public InventoryData (Items item,
                          Prices price,
                          Manufacturer brand,
                          Images image,
                          String request
                         )
    {
        
    }
    
    public void setRequest (String request)
    {
        this.request = request;
    }
    
    public String getRequest()
    {
        return request;
    }
    
    public void setItem (Items item)
    {
        this.item = item;
    }
    
    public Items getItem()
    {
        return item;
    }
    
    public void setPrice (Prices price)
    {
        this.price = price;
    }
    
    public Prices getPrice()
    {
        return price;
    }
    
    public void setBrand (Manufacturer brand)
    {
        this.brand = brand;
    }
    
    public Manufacturer getBrand()
    {
        return brand;
    }
    
    public void setImage (Images image)
    {
        this.image = image;
    }
    
    public Images getImage()
    {
        return image;
    }
    
    public Collection<Manufacturer> getBrands()
    {
        return brands;
    }
    
    public void setBrands (Collection<Manufacturer> brands)
    {
        this.brands = brands;
    }
    
    public Collection<Images> getImages()
    {
        return images;
    }
    
    public void setImages (Collection<Images> images)
    {
        this.images = images;
    }
    
    public Collection<Items> getItems()
    {
        return items;
    }
    
    public void setItems (Collection<Items> items)
    {
        this.items = items;
    }
}
