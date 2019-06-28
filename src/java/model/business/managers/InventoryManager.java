/*
 * Copyright 2018, Random Logic Consulting and Paul G. ALlen. All Rights Reserved.
 */
package model.business.managers;

import java.io.Serializable;
import model.domain.inventory.Manufacturer;
import java.util.Collection;
import java.util.Iterator;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

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
import model.domain.interfaces.IBrand;
import model.domain.interfaces.IDomainObject;
import model.domain.interfaces.IImages;
import model.domain.interfaces.IItems;
import model.domain.interfaces.IUsers;
import model.domain.inventory.ItemSearchParams;
import model.domain.users.Users;
import model.service.interfaces.IUserAccessSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Paul G. Allen <pgallen@gmail.com>
 */
@Service
public class InventoryManager implements IInventoryManager, Serializable
{
	/** The manager. */
    private final static Logger log = LogManager.getLogger (InventoryManager.class.getName());

    private String partNo;

    private String desc;

    private String upc;

    private String pageLimit;

    @ManagedProperty ("#{param.sourcePage}")
    private String sourcePage = "catalog.xhtml";

    @ManagedProperty ("#{param.orderBy}")
    private String orderBy = "partNo";

    private HttpSession session;

    @ManagedProperty ("#{param.pageTitle}")
    private String pageTitle = "Catalog";

    private Collection<IItems> itemList;

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
	public InventoryManager()
    {
    }

    public void setSession()
    {
        String temp;
        FacesContext facesContext = FacesContext.getCurrentInstance();
        session = (HttpSession) facesContext.getExternalContext().getSession (false);

        temp = facesContext.getExternalContext().getRequestParameterMap().get ("sourcePage");

        if (temp != null)
        {
            sourcePage = temp;
        }

        temp = facesContext.getExternalContext().getRequestParameterMap().get ("orderBy");

        if (temp != null)
        {
            orderBy = temp;
        }

        temp = facesContext.getExternalContext().getRequestParameterMap().get ("pageTitle");

        if (temp != null)
        {
            pageTitle = temp;
        }
    }

    /**
     * @return the orderBy
     */
    @Override
    public String getOrderBy()
    {
        return orderBy;
    }

    /**
     * @param orderBy the orderBy to set
     */
    @Override
    public void setOrderBy (String orderBy)
    {
        this.orderBy = orderBy;
    }

    /**
     * @return the partNo
     */
    @Override
    public String getPartNo()
    {
        return partNo;
    }

    /**
     * @param partNo the partNo to set
     */
    @Override
    public void setPartNo (String partNo)
    {
        this.partNo = partNo;
    }

    /**
     * @return the desc
     */
    @Override
    public String getDesc()
    {
        return desc;
    }

    /**
     * @param desc the desc to set
     */
    @Override
    public void setDesc (String desc)
    {
        this.desc = desc;
    }

    /**
     * @return the upc
     */
    @Override
    public String getUpc()
    {
        return upc;
    }

    /**
     * @param upc the upc to set
     */
    public void setUpc (String upc)
    {
        this.upc = upc;
    }

    /**
     * @return the pageLimit
     */
    @Override
    public String getPageLimit()
    {
        return pageLimit;
    }

    /**
     * @param pageLimit the pageLimit to set
     */
    public void setPageLimit (String pageLimit)
    {
        this.pageLimit = pageLimit;
    }

    /**
     * @return the sourcePage
     */
    @Override
    public String getSourcePage()
    {
        return sourcePage;
    }

    /**
     * @param sourcePage the sourcePage to set
     */
    public void setSourcePage (String sourcePage)
    {
        this.sourcePage = sourcePage;
    }

    /**
     * @return the session
     */
    public HttpSession getSession()
    {
        return session;
    }

    /**
     * @param session the session to set
     */
    public void setSession (HttpSession session)
    {
        this.session = session;
    }

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
    public Collection<IItems> findProducts (String cat)
    {
        int lastIndex = 0;
        int limit = 25;
        ItemSearchParams itemParms = new ItemSearchParams();
        ItemSearchParams lastParms = null;
        Collection<Manufacturer> mfgList;

        setSession();
        mfgList = brandSvc.readBrands();

        if (sourcePage != null)
        {
            if (!sourcePage.isEmpty())
            {
                session.setAttribute ("pageLimit", null);
                session.setAttribute ("lastItemParms", null);
                session.setAttribute ("itemList", null);
                session.setAttribute ("mfgList", null);
            }
            //else do nothing
        }
        else
        {
            lastParms = (ItemSearchParams)session.getAttribute ("lastItemParms");
        }

        /**
         * Retrieve last index number
         * Get number to display on page as <number>
         * Get <number> aircraft from database starting from index
         * Save next index number
         * Save index
         * Display results
         */
        try
        {
            limit = Integer.parseInt (pageLimit);
        }
        catch (NumberFormatException e)
        {

        }

        itemParms.setOrderBy ("partNo");

        // Validate/format parameters
        if (orderBy != null)
        {
            if (!orderBy.equals (""))
            {
                if (lastParms != null)
                {
                    itemParms = lastParms;
                }
                //else do nothing

                lastParms = null;
                itemParms.setOrderBy (orderBy);
                itemParms.setAsc (!itemParms.isAsc());
                itemParms.setLastIndex (0);
            }
        }

        if (partNo != null)
        {
            if (!partNo.equals (""))
            {
                itemParms.setPartNo (partNo);
            }
        }

        if (desc != null)
        {
            if (!desc.equals (""))
            {
                itemParms.setDesc ("%" + desc + "%");
            }
        }

        if (upc != null)
        {
            if (!upc.equals (""))
            {
                itemParms.setUpc (upc);
            }
        }

////        if (mfg != null)
////        {
////            if (!mfg.equals ("Select a Mfg"))
////            {
////                itemParms.setMfg (mfg);
////            }
////        }

        itemParms.setId ("%");
        itemParms.setName ("%");
        itemParms.setCat (cat);
        itemParms.setLimit (limit);

        // If different parameters, reset index
        if (!itemParms.equals (lastParms))
        {
            itemParms.setLastIndex (lastIndex);
        }
        else
        {
            if (lastParms != null)
            {
                lastIndex = lastParms.getLastIndex();
                itemParms.setLastIndex (lastIndex);
            }
        }

        itemList = (Collection<IItems>)itemSvc.search (itemParms);

        if (itemList != null && itemList.size() >= 1)
        {
            Iterator itemItr = itemList.iterator();
            IItems item;

            // Get highest index key in list
            int tempIndex = 0;

            do
            {
                item = (IItems)itemItr.next();

                if (item.getIdItems() > tempIndex)
                {
                    tempIndex = item.getIdItems();
                }
                //else do nothing
            } while (itemItr.hasNext());

            itemParms.setLastIndex (tempIndex);

            // Validate/download Images
            if (validateImages())
            {
                {
                    System.err.println ("Success");
                }
            }
        }
        else
        {
            itemParms.setLastIndex (0);
        }

        session.setAttribute ("lastItemParms", itemParms);
        session.setAttribute ("itemList", itemList);
        session.setAttribute ("mfgList", mfgList);

        return itemList;
    }

    private boolean validateImages()
    {
        boolean result = true;
////        Iterator itemIter = itemList.iterator();
////        String imageURL;
////        String imageBigUrl;
////        URL dlUrl;
////        URL dlBigUrl;
////        IImages curImage;
////        IImages noImage;
////        Collection<IImages> imageList = null;
////        IItems item;
////        IBrand curMan;
////
////        noImage = imageSvc.findImages ("/images/ProdInfo/img_not_available.gif").get (0);
////
////        do
////        {
////            item = (IItems)itemIter.next();
////
////            if (item.getImagesCollection().size() < 1)
////            {
////                imageList = new ArrayList<IImages>();
////                curMan = brandSvc.findManufacturer (item.getManufactureridManufacturer().getIdManufacturer());
////
////                switch (item.getDist())
////                {
////                    case "H":
////                        {
////                            String tempPath = cfg.horDlPath
////                                            + curMan.getMfgKey()
////                                            + "/100/"
////                                            + item.getPartNo()
////                                            + "-100.jpg";
////
////                            try
////                            {
////                                dlUrl = new URL (tempPath);
////
////                                tempPath = cfg.horDlPath
////                                         + curMan.getMfgKey()
////                                         + "/450/"
////                                         + item.getPartNo()
////                                         + "-450.jpg";
////                                dlBigUrl = new URL (tempPath);
////
////                                imageURL = cfg.imgLocPath
////                                          + curMan.getMfgKey()
////                                          + "/100/"
////                                          + item.getPartNo()
////                                          + "-100.jpg";
////                                imageBigUrl = cfg.imgLocPath
////                                             + curMan.getMfgKey()
////                                             + "/450/"
////                                             + item.getPartNo()
////                                             + "-450.jpg";
////
////                                if (saveFile (dlUrl, cfg.imgPath + imageURL))
////                                {
////                                    curImage = new Images (0, imageURL, userSvc.findUsers (params.getUserInfo().getIdUsers()), new Date());
////                                    List<IItems> newList = new ArrayList();
////                                    item.setDefaultImage (curImage.getImagePath());
////
////                                    try
////                                    {
////                                        itemSvc.edit ((Items)item);
////                                    }
////                                    catch (Exception e)
////                                    {
////
////                                    }
////
////                                    newList.add (item);
////                                    curImage.setItemsCollection (newList);
////
////                                    try
////                                    {
////                                        imageSvc.edit ((Images)curImage);
////                                        imageList.add ((Images)curImage);
////                                    }
////                                    catch (Exception e)
////                                    {
////
////                                    }
////                                }
////                                else
////                                {
////                                    // Add this item to the noImage collection
////                                    Collection<IItems> newList;
////                                    newList = (Collection<IItems>)noImage.getItemsCollection();
////                                    item.setDefaultImage (noImage.getImagePath());
////
////                                    try
////                                    {
////                                        itemSvc.edit ((Items)item);
////                                    }
////                                    catch (Exception e)
////                                    {
////
////                                    }
////
////                                    newList.add (item);
////                                    noImage.setItemsCollection (newList);
////
////                                    // Add image to this item's image collection
////                                    imageList.add ((Images)noImage);
////                                }
////
////                                if (saveFile (dlBigUrl, cfg.imgPath + imageBigUrl))
////                                {
////                                    curImage = new Images (0, imageBigUrl, userSvc.findUsers (params.getUserInfo().getIdUsers()), new Date());
////                                    List<IItems> newList = new ArrayList();
////                                    newList.add (item);
////                                    curImage.setItemsCollection (newList);
////
////                                    try
////                                    {
////                                        imageSvc.edit ((Images)curImage);
////                                        imageList.add ((Images)curImage);
////                                    }
////                                    catch (Exception e)
////                                    {
////
////                                    }
////                                }
////                                else
////                                {
////                                    // Add this item to the noImage collection
////                                    Collection<IItems> newList;
////                                    newList = (Collection<IItems>)noImage.getItemsCollection();
////                                    newList.add (item);
////                                    noImage.setItemsCollection (newList);
////
////                                    // Add image to this item's image collection
////                                    imageList.add ((Images)noImage);
////                                }
////
////                                item.setImagesCollection (imageList);
////                                result = true;
////                            }
////                            catch (MalformedURLException ex)
////                            {
////                                java.util.logging.Logger.getLogger (InventoryManager.class.getName()).log(Level.SEVERE, null, ex);
////                            }
////                        }
////
////                        break;
////
////                    case "GP":
////                        {
////                            String tempPath = cfg.gpTinyDLPath
////                                            + curMan.getMfgKey().substring (0, 1).toLowerCase()
////                                            + "/t"
////                                            + item.getPartNo().toLowerCase()
////                                            + ".jpg";
////                            try
////                            {
////                                dlUrl = new URL (tempPath);
////
////                                tempPath = cfg.gpLargeDLPath
////                                         + curMan.getMfgKey().substring (0, 1).toLowerCase()
////                                         + "/"
////                                         + item.getPartNo().toLowerCase()
////                                         + ".jpg";
////                                dlBigUrl = new URL (tempPath);
////
////                                imageURL = cfg.imgLocPath
////                                          + curMan.getMfgKey()
////                                          + "/100/"
////                                          + item.getPartNo()
////                                          + "-100.jpg";
////
////                                imageBigUrl = cfg.imgLocPath
////                                             + curMan.getMfgKey()
////                                             + "/450/"
////                                             + item.getPartNo()
////                                             + "-450.jpg";
////
////                                if (saveFile (dlUrl, cfg.imgPath + imageURL))
////                                {
////                                    curImage = new Images (0, imageURL, usersJPA.findUsers (params.getUserInfo().getIdUsers()), new Date());
////                                    List<IItems> newList = new ArrayList();
////                                    item.setDefaultImage (curImage.getImagePath());
////
////                                    try
////                                    {
////                                        itemSvc.edit ((Items)item);
////                                    }
////                                    catch (Exception e)
////                                    {
////
////                                    }
////
////                                    newList.add ((Items)item);
////                                    curImage.setItemsCollection (newList);
////
////                                    try
////                                    {
////                                        itemSvc.edit ((Images)curImage);
////                                        imageList.add (curImage);
////                                    }
////                                    catch (Exception e)
////                                    {
////
////                                    }
////                                }
////                                else
////                                {
////                                    // Add this item to the noImage collection
////                                    List<IItems> newList;
////                                    newList = (List<IItems>)noImage.getItemsCollection();
////                                    item.setDefaultImage (noImage.getImagePath());
////
////                                    try
////                                    {
////                                        itemSvc.edit ((Items)item);
////                                    }
////                                    catch (Exception e)
////                                    {
////
////                                    }
////
////                                    newList.add ((Items)item);
////                                    noImage.setItemsCollection (newList);
////
////                                    // Add image to this item's image collection
////                                    imageList.add (noImage);
////                                }
////
////                                if (saveFile (dlBigUrl, cfg.imgPath + imageBigUrl))
////                                {
////                                    curImage = new Images (0, imageBigUrl, userSvc.findUsers (params.getUserInfo().getIdUsers()), new Date());
////                                    List<IItems> newList = new ArrayList();
////                                    newList.add (item);
////                                    curImage.setItemsCollection (newList);
////
////                                    try
////                                    {
////                                        imageSvc.edit (curImage);
////                                        imageList.add (curImage);
////                                    }
////                                    catch (Exception e)
////                                    {
////
////                                    }
////                                }
////                                else
////                                {
////                                    // Add this item to the noImage collection
////                                    Collection<IItems> newList;
////                                    newList = (Collection<IItems>)noImage.getItemsCollection();
////                                    newList.add (item);
////                                    noImage.setItemsCollection (newList);
////
////                                    // Add image to this item's image collection
////                                    imageList.add (noImage);
////                                }
////
////                                item.setImagesCollection (imageList);
////                                result = true;
////                            }
////                            catch (MalformedURLException ex)
////                            {
////
////                            }
////                        }
////
////                        break;
////
////                    default:
////                        break;
////                }
////            }
////        } while (itemIter.hasNext());

        return result;
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
////				objectList = priceSvc.find (Object);
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

////        prices = priceSvc.readPrices();
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
////                result = priceSvc.updatePrice (price, false);
                break;

            case "update":
////                result = priceSvc.updatePrice (price, true);
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
////        price = priceSvc.readPrice (price.getIdPrices());
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

    @Override
    public String getPageTitle()
    {
        return pageTitle;
    }

    @Override
    public void setPageTitle (String pageTitle)
    {
        this.pageTitle = pageTitle;
    }

    @Override
    public IImages getImage()
    {
        setSession();

        return null;
    }
}
