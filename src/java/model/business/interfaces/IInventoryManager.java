/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.business.interfaces;

import java.util.Collection;
import model.domain.interfaces.IDomainObject;
import model.domain.interfaces.IImages;
import model.domain.interfaces.IItems;
import model.domain.interfaces.IUsers;
import model.domain.inventory.Images;
import model.domain.inventory.Items;
import model.domain.inventory.Manufacturer;
import model.domain.inventory.Prices;
import model.domain.users.Users;
import model.service.interfaces.IBrandAccessSvc;
import model.service.interfaces.IImageAccessSvc;
import model.service.interfaces.IItemAccessSvc;
import model.service.interfaces.IPriceAccessSvc;
import model.service.interfaces.IUserAccessSvc;

/**
 *
 * @author Paul G. Allen <pgallen@gmail.com>
 */
public interface IInventoryManager extends IDAOManager
{
    /**
     * Deletes the DAO containing the objects given by <b>type</b>. Removes the
     * physical representation of the object records.
     *
     * @param type The DAO type to delete
     * @return true if DAO deleted, false if not
     */
    @Override
    boolean deleteAll (String type);

    void setBrandSvc (IBrandAccessSvc brandSvc);

    void setImageSvc (IImageAccessSvc imageSvc);

    void setItemSvc (IItemAccessSvc itemSvc);

    void setPriceSvc (IPriceAccessSvc priceSvc);

    void setUserSvc (IUserAccessSvc userSvc);

    Collection<?> find (String type, IDomainObject Object);

    /**
     *
     * @param item
     * @return
     */
    Items read (Items item);

    /**
     *
     * @param brand
     * @return
     */
    Manufacturer read (Manufacturer brand);

    /**
     *
     * @param price
     * @return
     */
    Prices read (Prices price);

    /**
     *
     * @param image
     * @return
     */
    Images read (Images image);

    /**
     *
     * @param user
     * @return
     */
    Users read (Users user);

    /**
     *
     * @param type
     * @return
     */
    @Override
    Collection<?> readAll (String type);

    /**
     *
     * @param type
     * @return
     */
    @Override
    Collection<?> readNext (String type);

    /**
     *
     * @param type
     * @param min
     * @param max
     * @return
     */
    @Override
    Collection<?> readRange (String type, Integer min, Integer max);

    //Items methods
    /**
     *
     * @param item
     * @param command
     * @return
     */
    boolean update (Items item, String command);

    //Brands methods
    /**
     *
     * @param brand
     * @param command
     * @return
     */
    boolean update (Manufacturer brand, String command);

    //Prices methods
    /**
     *
     * @param price
     * @param command
     * @return
     */
    boolean update (Prices price, String command);

    // Images methods
    /**
     *
     * @param image
     * @param command
     * @return
     */
    boolean update (Images image, String command);

    // Users methods
    /**
     *
     * @param user
     * @param command
     * @return
     */
    boolean update (IUsers user, String command);

    Collection<IItems> findProducts (String cat);

    String getOrderBy();

    void setOrderBy (String orderBy);

    String getSourcePage();

    IImages getImage();

    String getPageTitle();

    void setPageTitle (String pageTitle);

    String getPartNo();

    void setPartNo (String partNo);

    String getDesc();

    void setDesc (String desc);

    String getUpc();

    void setUpc (String upc);

    String getPageLimit();

    void setPageLimit (String pageLimit);

    void setSourcePage (String sourcePage);
}
