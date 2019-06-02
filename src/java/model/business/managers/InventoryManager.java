/*
 * Copyright 2018, Random Logic Consulting and Paul G. ALlen. All Rights Reserved.
 */
package model.business.managers;

import java.io.Serializable;
import model.domain.inventory.Manufacturer;
import java.util.Collection;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import model.domain.inventory.Images;
import model.domain.inventory.Items;
import model.domain.inventory.Prices;
import model.service.interfaces.IBrandAccessSvc;
import model.service.interfaces.IImageAccessSvc;
import model.service.interfaces.IItemAccessSvc;
import model.service.interfaces.IPriceAccessSvc;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import model.business.interfaces.IInventoryManager;
import model.domain.interfaces.IDomainObject;
import model.domain.interfaces.IUsers;
import model.domain.users.Users;
import model.service.interfaces.IUserAccessSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Paul G. Allen <pgallen@gmail.com>
 */
@Component
@ManagedBean
@SessionScoped
public class InventoryManager implements IInventoryManager, Serializable
{
	/** The manager. */
    private final static Logger log = LogManager.getLogger (InventoryManager.class.getName());

    @Autowired
    private IPriceAccessSvc priceSvc;

    @Autowired
    private IBrandAccessSvc brandSvc;

    @Autowired
    private IImageAccessSvc imageSvc;

    @Autowired
    private IUserAccessSvc userSvc;

    @Autowired
    private IItemAccessSvc itemSvc;

	/**
	 * Instantiates a new inventory manager.
	 */
	public InventoryManager() {}

    @Override
    public void setPriceSvc (IPriceAccessSvc priceSvc)
    {
        this.priceSvc = priceSvc;
    }

    @Override
    public void setBrandSvc (IBrandAccessSvc brandSvc)
    {
        this.brandSvc = brandSvc;
    }

    @Override
    public void setImageSvc (IImageAccessSvc imageSvc)
    {
        this.imageSvc = imageSvc;
    }

    @Override
    public void setUserSvc (IUserAccessSvc userSvc)
    {
        this.userSvc = userSvc;
    }

    @Override
    public void setItemSvc (IItemAccessSvc itemSvc)
    {
        this.itemSvc = itemSvc;
    }

    @Override
    public synchronized Collection<?> find (String type, IDomainObject Object)
    {
        Collection<?> objectList = null;

        switch (type)
		{
			case "item":
				objectList = itemSvc.find (Object);
				break;

			case "price":
				objectList = priceSvc.find (Object);
				break;

			case "brand":
				objectList = brandSvc.find (Object);
				break;

			case "image":
				objectList = imageSvc.find (Object);
				break;

            case "user":
				objectList = userSvc.find (Object);
				break;

			default:
				break;
		}


        return objectList;
    }

    /**
     *
     * @param type
     * @return
     */
    @Override
	public synchronized Collection<?> readAll (String type)
	{
		Collection<?> result = null;

		switch (type)
		{
			case "item":
				result = readAllItems();
				break;

			case "price":
				result = readAllPrices();
				break;

			case "brand":
				result = readAllBrands();
				break;

			case "image":
				result = readAllImages();
				break;

            case "user":
				result = readAllUsers();
				break;

			default:
				break;
		}

		return result;
	}

	private synchronized Collection<Prices> readAllPrices()
	{
		Collection<Prices> prices = null;

        prices = priceSvc.readPrices();
		return prices;
	}

	private synchronized Collection<Manufacturer> readAllBrands()
	{
        Collection<Manufacturer> brands = null;

        brands = brandSvc.readBrands();
		return brands;
	}

	private synchronized Collection<Images> readAllImages()
	{
		Collection<Images> images = null;

        images = imageSvc.readImages();
		return images;
	}

    private synchronized Collection<Users> readAllUsers()
	{
        Collection<Users> users = null;

        users = userSvc.readUsers();
		return users;
	}

	private synchronized Collection<Items> readAllItems()
	{
        Collection<Items> items = null;

        items = itemSvc.readItems();
		return items;
	}

    /**
     *
     * @param type
     * @param min
     * @param max
     * @return
     */
    @Override
	public synchronized Collection<?> readRange (String type, Integer min, Integer max)
	{
		switch (type)
		{
			case "item":
				break;

			case "price":
				break;

			case "brand":
				break;

			case "image":
				break;

            case "user":
                break;

			default:
				break;
		}

		return null;
	}

    /**
     *
     * @param type
     * @return
     */
    @Override
	public synchronized Collection<?> readNext (String type)
	{
		switch (type)
		{
			case "item":
				break;

			case "price":
				break;

			case "brand":
				break;

			case "image":
				break;

            case "user":
                break;

			default:
				break;
		}

		return null;
	}

    /**
     * Deletes the DAO containing the objects given by <b>type</b>. Removes the
     * physical representation of the object records.
     *
     * @param type The DAO type to delete
     * @return true if DAO deleted, false if not
     */
    @Override
    public synchronized boolean deleteAll (String type)
    {
        boolean result = false;

        switch (type)
		{
			case "item":
				result = deleteItems ("items.db");
				break;

			case "price":
				result = deletePrices ("prices.db");
				break;

			case "brand":
				result = deleteBrands ("brand.db");
				break;

			case "image":
				result = deleteImages ("images.db");
				break;

            case "user":
				result = deleteUsers ("users.db");
				break;

			default:
				break;
		}

        return result;
    }

    private synchronized boolean deleteImages (String type)
    {
        boolean result = false;

        result = imageSvc.delete (type);
		return result;
    }

    private synchronized boolean deleteUsers (String type)
    {
        boolean result = false;

        result = imageSvc.delete (type);
		return result;
    }

    private synchronized boolean deleteItems (String type)
    {
        boolean result = false;

        result = itemSvc.delete (type);
		return result;
    }

    private synchronized boolean deletePrices (String type)
    {
        boolean result = false;

        result = userSvc.delete (type);
		return result;
    }

    private synchronized boolean deleteBrands (String type)
    {
        boolean result = false;

        result = brandSvc.delete (type);
		return result;
    }


	//Items methods

    /**
     *
     * @param item
     * @param command
     * @return
     */
    @Override
	public synchronized boolean update (Items item, String command)
	{
        boolean result = false;


        switch (command)
        {
            case "create":
                result = itemSvc.updateItem (item, false);
                break;

            case "update":
                break;

            case "delete":
                break;

            default:
                break;
        }

        return result;
	}

    /**
     *
     * @param item
     * @return
     */
    @Override
    public synchronized Items read (Items item)
	{
        item = itemSvc.readItem (item.getIdItems());
		return item;
	}


	//Brands methods

    /**
     *
     * @param brand
     * @param command
     * @return
     */
    @Override
	public synchronized boolean update (Manufacturer brand, String command)
	{
		boolean result = false;

        switch (command)
        {
            case "create":
                result = brandSvc.updateBrand (brand, false);
                break;

            case "update":
                break;

            case "delete":
                break;

            default:
                break;
        }

		return result;
	}

    /**
     *
     * @param brand
     * @return
     */
    @Override
    public synchronized Manufacturer read (Manufacturer brand)
	{
        brand = brandSvc.readBrand (brand.getIdBrand());
		return brand;
	}


	//Prices methods

    /**
     *
     * @param price
     * @param command
     * @return
     */
    @Override
	public synchronized boolean update (Prices price, String command)
	{
		boolean result = false;

        switch (command)
        {
            case "create":
                result = priceSvc.updatePrice (price, false);
                break;

            case "update":
                result = priceSvc.updatePrice (price, true);
                break;

            case "delete":
                break;

            default:
                break;
        }

		return result;
	}

    /**
     *
     * @param price
     * @return
     */
    @Override
    public synchronized Prices read (Prices price)
	{
        price = priceSvc.readPrice (price.getIdPrices());
		return price;
	}


	// Images methods

    /**
     *
     * @param image
     * @param command
     * @return
     */
    @Override
	public synchronized boolean update (Images image, String command)
	{
		boolean result = false;

        switch (command)
        {
            case "create":
                result = imageSvc.updateImage (image, false);
                break;

            case "update":
                break;

            case "delete":
                break;

            default:
                break;
        }

		return result;
	}

    /**
     *
     * @param image
     * @return
     */
    @Override
    public synchronized Images read (Images image)
	{
        image = imageSvc.readImage (image.getIdImages());
		return image;
	}

   // Users methods

    /**
     *
     * @param user
     * @param command
     * @return
     */
    @Override
	public synchronized boolean update (IUsers user, String command)
	{
		boolean result = false;

        switch (command)
        {
            case "create":
                result = userSvc.updateUser (user, false);
                break;

            case "update":
                break;

            case "delete":
                break;

            default:
                break;
        }

		return result;
	}

    /**
     *
     * @param user
     * @return
     */
    @Override
    public synchronized Users read (Users user)
	{
        user = userSvc.readUser (user.getIdUsers());

		return user;
	}

    public boolean validate()
    {
        return true;
    }
}
