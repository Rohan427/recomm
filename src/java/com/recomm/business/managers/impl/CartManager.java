/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recomm.business.managers.impl;

import com.recomm.business.managers.interfaces.ICartManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import com.recomm.model.domain.inventory.interfaces.ICart;
import com.recomm.model.domain.inventory.interfaces.ICartPK;
import com.recomm.model.domain.users.interfaces.ICustomer;
import com.recomm.model.domain.inventory.interfaces.IItems;
import com.recomm.model.domain.users.interfaces.IUsers;
import com.recomm.model.domain.inventory.entity.Cart;
import com.recomm.model.domain.inventory.entity.Items;
import com.recomm.model.domain.users.entity.Customer;
import com.recomm.model.domain.users.entity.Users;
import com.recomm.model.domain.inventory.service.interfaces.IBrandAccessSvc;
import com.recomm.model.domain.inventory.service.interfaces.ICartAccessSvc;
import com.recomm.model.domain.inventory.service.interfaces.IImageAccessSvc;
import com.recomm.model.domain.inventory.service.interfaces.IItemAccessSvc;
import com.recomm.model.domain.inventory.service.interfaces.IPriceAccessSvc;
import com.recomm.model.domain.inventory.util.CartSearchParams;
import com.recomm.model.domain.users.util.CustomerSearchParams;
import com.recomm.model.domain.users.service.interfaces.ICustomerAccessSvc;
import com.recomm.model.domain.users.service.interfaces.IUserAccessSvc;
import javax.faces.bean.ManagedProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Paul G. Allen <pgallen@gmail.com>
 */
@Service
public class CartManager implements ICartManager
{
    private IUsers userInfo;

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

    @ManagedProperty ("#{param.itemId}")
    private String itemId;

    @ManagedProperty ("#{param.pageTitle}")
    private String pageTitle = "Cart";

    @ManagedProperty ("#{param.orderBy}")
    private String orderBy = "partNo";

    String resultPage;

    @ManagedProperty ("#{param.sourcePage}")
    private String sourcePage;

    /**
     * @return the priceSvc
     */
    public IPriceAccessSvc getPriceSvc()
    {
        return priceSvc;
    }

    /**
     * @param priceSvc the priceSvc to set
     */
    public void setPriceSvc (IPriceAccessSvc priceSvc)
    {
        this.priceSvc = priceSvc;
    }

    /**
     * @return the brandSvc
     */
    public IBrandAccessSvc getBrandSvc()
    {
        return brandSvc;
    }

    /**
     * @param brandSvc the brandSvc to set
     */
    public void setBrandSvc (IBrandAccessSvc brandSvc)
    {
        this.brandSvc = brandSvc;
    }

    /**
     * @return the imageSvc
     */
    public IImageAccessSvc getImageSvc()
    {
        return imageSvc;
    }

    /**
     * @param imageSvc the imageSvc to set
     */
    public void setImageSvc (IImageAccessSvc imageSvc)
    {
        this.imageSvc = imageSvc;
    }

    /**
     * @return the userSvc
     */
    public IUserAccessSvc getUserSvc()
    {
        return userSvc;
    }

    /**
     * @param userSvc the userSvc to set
     */
    public void setUserSvc (IUserAccessSvc userSvc)
    {
        this.userSvc = userSvc;
    }

    /**
     * @return the itemSvc
     */
    public IItemAccessSvc getItemSvc()
    {
        return itemSvc;
    }

    /**
     * @param itemSvc the itemSvc to set
     */
    public void setItemSvc (IItemAccessSvc itemSvc)
    {
        this.itemSvc = itemSvc;
    }

    /**
     * @return the cartSvc
     */
    public ICartAccessSvc getCartSvc()
    {
        return cartSvc;
    }

    /**
     * @param cartSvc the cartSvc to set
     */
    public void setCartSvc (ICartAccessSvc cartSvc)
    {
        this.cartSvc = cartSvc;
    }

    /**
     * @return the itemId
     */
    public String getItemId()
    {
        return itemId;
    }

    /**
     * @param itemId the itemId to set
     */
    public void setItemId (String itemId)
    {
        this.itemId = itemId;
    }

    /**
     * @return the pageTitle
     */
    public String getPageTitle()
    {
        return pageTitle;
    }

    /**
     * @param pageTitle the pageTitle to set
     */
    public void setPageTitle (String pageTitle)
    {
        this.pageTitle = pageTitle;
    }

    @Override
    public void setUserInfo (IUsers userInfo)
    {
        this.userInfo = userInfo;
    }

    @Override
    public IUsers getUserInfo()
    {
        return this.userInfo;
    }

    @Override
    public HttpSession getSession()
    {
        return this.session;
    }

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
    public void cartAdd()
    {
        List<IItems> curCartList;
        IItems curItem;
        List<ICart> cListTable;
        ICart newCart;
        int custID = 0;
        ListIterator cListIter;
        CartSearchParams parms = new CartSearchParams();
        CustomerSearchParams custParms = new CustomerSearchParams();

        // Get the item from DB
        curItem = itemSvc.readItem (Integer.parseInt (itemId));

        custParms.setIdUser (userInfo.getIdUsers().toString());

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

        // Read the current cart list (check session object, read DB)
        curCartList = (List<IItems>)session.getAttribute ("cartList");

        // Create the new Cart entry
        newCart = new Cart (0, custID);
        newCart.setItemsidItems (Integer.parseInt (itemId));
        newCart.setCreation (new Date());
        newCart.setQty (1);

        if (curCartList == null || curCartList.isEmpty())
        {
            parms.setIdCustomer ("" + custID);
            cListTable = (List<ICart>)cartSvc.search (parms);

            // No list stored
            if (cListTable == null || cListTable.isEmpty())
            {
                cListTable = new ArrayList<>();

                // Store the item reference in db
                try
                {
                    cListTable.add (newCart);
                    cartSvc.save (cListTable);
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
                    cListTable.add (newCart);
                    cartSvc.merge (cListTable);
                }
                catch (Exception ex)
                {
//                     TODO: Report Error
                }
            }

            // Build the item list
            curCartList = new ArrayList<>();
            cListIter = cListTable.listIterator();

            do
            {
                newCart = (Cart)cListIter.next();
                curItem = itemSvc.readItem (newCart.getItemsidItems());
                curCartList.add (curItem);
            } while (cListIter.hasNext());
        } // ENDIF: if (curCartList == null)
        // Found a current cart list, add the new item
        else
        {
            // Store the item reference in db
            try
            {
                // Add item to list
                curCartList.add (curItem);
                curCartList = (List<IItems>)cartSvc.merge (curCartList);
            }
            catch (Exception ex)
            {
                // TODO: Report Error
            }
        } // ENDIF-ELSE: if (curCartList == null)

        session.setAttribute ("cartList", curCartList);
    }

    @Override
    public void guestCartAdd()
    {
        List<IItems> curCartList;
        IItems curItem;
        List<ICart> cListTable;
        ICartPK cListPK;
        ICart newCart;
        int custID = 0;
        ListIterator cListIter;

        // Get the item from DB
        curItem = getItemSvc().readItem (Integer.parseInt (itemId));

        // Guest user, update user info if needed
        if (userInfo.getUserName().equalsIgnoreCase ("guest"))
        {
            if (userInfo.getCustomerCollection() == null || userInfo.getCustomerCollection().isEmpty())
            {
                Customer cust = new Customer ("guest", "guest", userInfo);

                userInfo.setCustomerCollection (new ArrayList<ICustomer>());
                userInfo.getCustomerCollection().add (cust);
                session.setAttribute ("userBean", userInfo);
                custID = cust.getIdCustomer();
            }
            else
            {
                custID = ((Customer)((List)(userInfo.getCustomerCollection())).get(0)).getIdCustomer();
            }
        }
        else
        {
            custID = ((Customer)((List)(userInfo.getCustomerCollection())).get(0)).getIdCustomer();
        }

        // Read the current cart list (check session object, read DB)
        curCartList = (List<IItems>)session.getAttribute ("cartList");
        cListTable = (List<ICart>)session.getAttribute ("guestCart");

        // Create the new Cart entry
        newCart = new Cart (0, custID);
        newCart.setItemsidItems (Integer.parseInt (itemId));
        newCart.setCreation (new Date());
        newCart.setQty (1);

        if (curCartList == null)
        {
            // No list stored
            if (cListTable == null || cListTable.isEmpty())
            {
                cListTable = new ArrayList<>();

                // Store the item reference in list
                cListTable.add (newCart);
            }
            // Found list, add new item
            else
            {
                // See if item is already in the list
                if (!cListTable.contains (newCart))
                {
                    cListTable.add (newCart);
                }
            }

            // Build the item list
            curCartList = new ArrayList<>();
            cListIter = cListTable.listIterator();

            do
            {
                newCart = (Cart)cListIter.next();
                curItem = getItemSvc().readItem (newCart.getItemsidItems());
                curCartList.add (curItem);
            } while (cListIter.hasNext());
        } // ENDIF: if (curCartList == null)
        // Found a current cart list, add the new item
        else
        {
            // Store the item reference in db
            try
            {
                // Add item to list
                curCartList.add (curItem);
            }
            catch (Exception ex)
            {
                // TODO: Report Error
            }
        } // ENDIF-ELSE: if (curCartList == null)

        session.setAttribute ("cartList", curCartList);
        session.setAttribute ("guestCart", cListTable);
    }

    @Override
    public void cartRemove()
    {
        List<IItems> curCartList = new ArrayList<>();
        List<ICart> cListTable;
        ListIterator cListIter;
        IItems curItem;
        ICart newCart;
        int custID = 0;
        CartSearchParams parms = new CartSearchParams();

        // Get the item from DB
        curItem = itemSvc.readItem (Integer.parseInt (itemId));

        // Get the customer ID
        custID = ((Customer)((List)(userInfo.getCustomerCollection())).get (0)).getIdCustomer();

        // Get the entry to remove
        parms.setIdCustomer ("" + custID);
        parms.setIdItem ("" + itemId);

        cListTable = (List<ICart>)cartSvc.search (parms);

        // If an entry exists in DB, remove it
        if (!cListTable.isEmpty())
        {
            newCart = cListTable.get (0);

            // Remove it from the DB (Cart)
            cartSvc.removeCart ((ICart)newCart);
        }
        // else do nothing

        // Build the item list
        cListIter = cListTable.listIterator();

        while (cListIter.hasNext())
        {
            newCart = (Cart)cListIter.next();
            curItem = getItemSvc().readItem (newCart.getItemsidItems());
            curCartList.add (curItem);
        }

        if (!curCartList.isEmpty())
        {
            curCartList.remove (curItem);
        }
        // else do nothing

        session.setAttribute ("cartList", curCartList);
    }

    @Override
    public void guestCartRemove()
    {
        List<IItems> curCartList;
        List<Cart> cListTable;
        List<Cart> newCartListTable = new ArrayList();
        ListIterator cListIter;
        IItems curItem;
        Cart newCart;
        Items[] itemArray;
        int itemID = 0;

        resultPage = "/orders/viewCart.jsp";

        // Get the item from DB
        curItem = getItemSvc().readItem (Integer.parseInt (itemId));

        curCartList = (List<IItems>)session.getAttribute ("cartList");
        cListTable = (List<Cart>)session.getAttribute ("guestCart");

        // Create a new Cart without the item
        cListIter = cListTable.listIterator();

        while (cListIter.hasNext())
        {
            newCart = (Cart)cListIter.next();

            if (newCart.getItemsidItems() != curItem.getIdItems())
            {
                newCartListTable.add (newCart);
            }
        }

        // Build the item list
        curCartList = new ArrayList<>();
        cListIter = newCartListTable.listIterator();

        while (cListIter.hasNext())
        {
            newCart = (Cart)cListIter.next();
            curItem = getItemSvc().readItem (newCart.getItemsidItems());
            curCartList.add (curItem);
        }

        session.setAttribute ("cartList", curCartList);
        session.setAttribute ("guestCart", newCartListTable);
    }

    public void viewCart()
    {
        List<IItems> curCartList =  new ArrayList<IItems>();
        IItems curItem;
        List<ICart> cListTable;
        ICart newCart;
        int custID = 0;
        ListIterator cListIter;
        CartSearchParams parms = new CartSearchParams();
        CustomerSearchParams custParms = new CustomerSearchParams();

        // Guest user, update user info if needed
        if (userInfo.getUserName().equalsIgnoreCase ("guest"))
        {
            if (userInfo.getCustomerCollection() == null || userInfo.getCustomerCollection().isEmpty())
            {
                Customer cust = new Customer ("guest", "guest", userInfo);

                userInfo.setCustomerCollection (new ArrayList<ICustomer>());
                userInfo.getCustomerCollection().add (cust);
                session.setAttribute ("userBean", userInfo);
                custID = cust.getIdCustomer();
            }
        }
        else
        {
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
        }

        // Read the current cart (check session object, read DB)
        curCartList = (List<IItems>)session.getAttribute ("cartList");

        if (curCartList == null)
        {
            if (!userInfo.getUserName().equalsIgnoreCase ("guest"))
            {
                parms.setIdCustomer ("" + custID);
                cListTable = (List<ICart>)cartSvc.search (parms);
            }
            else
            {
                cListTable = (List<ICart>)session.getAttribute ("guestCart");
            }

            // No valid list, create empty one
            if (cListTable == null)
            {
                cListTable = new ArrayList<>();
            }

            // Build the item list
            curCartList = new ArrayList<>();
            cListIter = cListTable.listIterator();

            while (cListIter.hasNext())
            {
                newCart = (Cart)cListIter.next();
                curItem = itemSvc.readItem (newCart.getItemsidItems());
                curCartList.add (curItem);
            }
        } // ENDIF: if (curCartList == null)

        session.setAttribute ("cartList", curCartList);
    }

    @Override
    public void guestCartUpdate()
    {
        throw new UnsupportedOperationException ("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void cartUpdate()
    {
        throw new UnsupportedOperationException ("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<?> readAll (String type)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<?> readRange (String type, Integer min, Integer max)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<?> readNext (String type)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteAll (String type)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
