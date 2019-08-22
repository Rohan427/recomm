/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recomm.business.managers.impl;

import com.recomm.business.config.Config;
import com.recomm.business.util.beans.ServletParams;
import com.recomm.model.domain.interfaces.IWishlist;
import com.recomm.model.domain.inventory.entity.Wishlist;
import com.recomm.model.domain.inventory.interfaces.IItems;
import com.recomm.model.domain.inventory.interfaces.IWishAccessSvc;
import com.recomm.model.domain.inventory.service.interfaces.IBrandAccessSvc;
import com.recomm.model.domain.inventory.service.interfaces.ICartAccessSvc;
import com.recomm.model.domain.inventory.service.interfaces.IImageAccessSvc;
import com.recomm.model.domain.inventory.service.interfaces.IItemAccessSvc;
import com.recomm.model.domain.inventory.service.interfaces.IPriceAccessSvc;
import com.recomm.model.domain.inventory.util.CartSearchParams;
import com.recomm.model.domain.users.entity.AccessList;
import com.recomm.model.domain.users.entity.Customer;
import com.recomm.model.domain.users.entity.Users;
import com.recomm.model.domain.users.interfaces.ICustomer;
import com.recomm.model.domain.users.interfaces.IUsers;
import com.recomm.model.domain.users.service.interfaces.ICustomerAccessSvc;
import com.recomm.model.domain.users.service.interfaces.IUserAccessSvc;
import com.recomm.model.domain.users.util.CustomerSearchParams;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Paul G. Allen <pgallen@gmail.com>
 */
@Service
public class WishManager implements IWishManager
{
    private static final long serialVersionUID = 27L;
    private ServletParams params;
    List<IItems> itemList = null;
    private Config cfg;

    private IUsers userInfo;

    @ManagedProperty ("#{param.itemId}")
    private String itemId;

    @ManagedProperty ("#{param.pageTitle}")
    private String pageTitle = "Cart";

    @ManagedProperty ("#{param.orderBy}")
    private String orderBy = "partNo";

    String resultPage;

    @ManagedProperty ("#{param.sourcePage}")
    private String sourcePage;

    private HttpSession session;

    @Autowired
    private ICustomerAccessSvc custSvc;

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

    @Autowired
    private ICartAccessSvc cartSvc;

    @Autowired
    private IWishAccessSvc wishSvc;

    @Override
    public void setSession()
    {
        String temp;
        FacesContext facesContext = FacesContext.getCurrentInstance();
        session = (HttpSession) facesContext.getExternalContext().getSession (false);

        if (session == null)
        {
            //TODO: HAndle the error
        }
        else
        {
            userInfo = (Users)session.getAttribute ("userBean");

            if (userInfo == null)
            {
                userInfo = new Users();
                session.setAttribute ("userBean", userInfo);
                session.setAttribute ("expired", true);
            }
        }

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

        temp = facesContext.getExternalContext().getRequestParameterMap().get ("itemId");

        if (temp != null)
        {
            itemId = temp;
        }
    }

    @Override
    public void wishListAdd()
    {
        List<IItems> curWishList;
        IItems curItem;
        List<IWishlist> wListTable;
        IWishlist newWishlist;
        int custID = 0;
        ListIterator wListIter;
        CartSearchParams parms = new CartSearchParams();
        CustomerSearchParams custParms = new CustomerSearchParams();

        if (userInfo.isValid())
        {
            // Get the item from the DB
            curItem = itemSvc.readItem (Integer.parseInt (itemId));

            // Get the customerID
            if (userInfo.getCustomerCollection().isEmpty())
            {
                Collection<ICustomer> list = (List)custSvc.search (custParms);

                if (!list.isEmpty())
                {
                    userInfo.setCustomerCollection (list);
                    custID = list.iterator().next().getIdCustomer();
                }
                //else do nothing
            }
            else
            {
                custID = ((Customer)((List)(userInfo.getCustomerCollection())).get (0)).getIdCustomer();
            }

            // Read the current wishlist (check session object, read DB)
            curWishList = (List<IItems>)session.getAttribute ("wishlist");

            // Create the new Wishlist entry
            newWishlist = new Wishlist (0, custID);
            newWishlist.setItemsidItems (Integer.parseInt (itemId));
            newWishlist.setCreation (new Date());
            newWishlist.setQty (1);

            if (curWishList == null)
            {
                parms.setIdCustomer ("" + custID);
                wListTable = (List<IWishlist>)wishSvc.search (parms);

                // No list stored
                if (wListTable == null || wListTable.isEmpty())
                {
                    wListTable = new ArrayList<>();

                    // Store the item reference in db
                    try
                    {
                        wListTable.add (newWishlist);
                        wishSvc.save (wListTable);
                    }
                    catch (Exception ex)
                    {
                        ;// TODO: Report Error
                    }
                }
                // Found list, add new item
                else
                {
                    // See if item is already in the list
                    try
                    {
                        wListTable.add (newWishlist);
                        wishSvc.save (wListTable);
                    }
                    catch (Exception ex)
                    {
                        // TODO: Report Error
                    }
                }

                // Build the item list
                curWishList = new ArrayList<>();
                wListIter = wListTable.listIterator();

                do
                {
                    newWishlist = (Wishlist)wListIter.next();
                    curItem = itemSvc.readItem (newWishlist.getItemsidItems());
                    curWishList.add (curItem);
                } while (wListIter.hasNext());
            } // ENDIF: if (curWishList == null)
            // Found a current wishlist, add the new item
            else
            {
                // Store the item reference in db
                try
                {
                    // Add item to list
                    curWishList.add (curItem);
                    wishSvc.merge (curWishList);
                }
                catch (Exception ex)
                {
                    // TODO: Report Error
                }
            } // ENDIF-ELSE: if (curWishList == null)

            session.setAttribute ("wishlist", curWishList);
        }
        // else do nothing
    }

    @Override
    public void wishlistRemove()
    {
        List<IItems> curWishList = new ArrayList<>();
        IItems curItem;
        IWishlist newWishlist;
        List<IWishlist> wListTable;
        ListIterator wListIter;
        int custID = 0;
        int itemID = 0;
        CartSearchParams parms = new CartSearchParams();

        // Get the item from DB
        curItem = itemSvc.readItem (Integer.parseInt (itemId));

        // Get the customer ID
        custID = ((Customer)((List)(userInfo.getCustomerCollection())).get(0)).getIdCustomer();

        // Get the item to remove
        parms.setIdCustomer ("" + custID);
        parms.setIdItem ("" + itemId);

        wListTable = (List<IWishlist>)wishSvc.search (parms);

        // If an entry exists in DB, remove it
        if (!wListTable.isEmpty())
        {
            newWishlist = wListTable.get (0);

            // Remove it from the DB (Wishlist)
            wishSvc.removeWishlist ((IWishlist)newWishlist);
        }
        // else do nothing

        // Build the item list
        wListIter = wListTable.listIterator();

        while (wListIter.hasNext())
        {
            newWishlist = (IWishlist)wListIter.next();
            curItem = itemSvc.readItem (newWishlist.getItemsidItems());
            curWishList.add (curItem);
        }

        if (!curWishList.isEmpty())
        {
            curWishList.remove (curItem);
        }
        // else do nothing

        session.setAttribute ("wishlist", curWishList);
    }

    @Override
    public void viewList()
    {
        List<IItems> curWishList = new ArrayList<IItems>();
        IItems curItem;
        List<IWishlist> wListTable;
        IWishlist newItem;
        int custID = 0;
        ListIterator wListIter;
        CartSearchParams parms = new CartSearchParams();
        CustomerSearchParams custParms = new CustomerSearchParams();

        // Get the customerID
        if (userInfo.getCustomerCollection().isEmpty())
        {
            custParms.setIdUser (userInfo.getIdUsers().toString());
            Collection<ICustomer> list = (List)custSvc.search (custParms);

            if (!list.isEmpty())
            {
                userInfo.setCustomerCollection (list);
                custID = list.iterator().next().getIdCustomer();
            }
            //else do nothing
        }
        // else do nothing

        // Read the current wishlist (check session object, read DB)
        curWishList = (List<IItems>)session.getAttribute ("wishlist");

        if (curWishList == null)
        {
            parms.setIdCustomer ("" + custID);
            wListTable = (List<IWishlist>)wishSvc.search (parms);

            // No valid list, create empty one
            if (wListTable == null)
            {
                wListTable = new ArrayList<>();
            }

            // Build the item list
            curWishList = new ArrayList<>();
            wListIter = wListTable.listIterator();

            while (wListIter.hasNext())
            {
                newItem = (Wishlist)wListIter.next();
                curItem = itemSvc.readItem (newItem.getItemsidItems());
                curWishList.add (curItem);
            }
        } // ENDIF: if (curWishList == null)

        session.setAttribute ("wishlist", curWishList);
    }

    @Override
    public void moveToCart()
    {
        List<IItems> curWishList;
        IItems curItem;
        IWishlist newList;
        List<IWishlist> wListTable;
        ListIterator wListIter;
        int custID = 0;
        int itemID = 0;
        CartSearchParams parms = new CartSearchParams();

        resultPage = "/orders/cartAdd.jsp";

        // Get the item from DB
        curItem = itemSvc.readItem (Integer.parseInt (itemId));

        // Get the customerID
        custID = ((Customer)((List)(userInfo.getCustomerCollection())).get (0)).getIdCustomer();

        // Read the current wish list (check session object, read DB)
        curWishList = (List<IItems>)session.getAttribute ("wishlist");

        // Get the item to remove
        parms.setIdCustomer ("" + custID);
        parms.setIdItem ("" + itemId);

        wListTable = (List<IWishlist>)wishSvc.search (parms);
        newList = wListTable.get (0);

        // Get the item to remove
        curItem = itemSvc.readItem (Integer.parseInt (itemId));

        // Remove it from the DB (Wishlist)
        wishSvc.removeWishlist (newList);

        // Remove it from the display
        curWishList.remove (curItem);
        session.setAttribute ("wishlist", curWishList);
    }

    @Override
    public Collection<?> readAll(String type)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<?> readRange(String type, Integer min, Integer max)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<?> readNext(String type)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteAll(String type)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
