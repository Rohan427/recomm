/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.business.managers;

import model.business.interfaces.ICartManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.domain.interfaces.ICart;
import model.domain.interfaces.ICartPK;
import model.domain.interfaces.ICustomer;
import model.domain.interfaces.IDomainObject;
import model.domain.interfaces.IItems;
import model.domain.interfaces.IUsers;
import model.domain.inventory.Cart;
import model.domain.inventory.CartPK;
import model.domain.inventory.Items;
import model.domain.users.Customer;
import model.domain.users.Users;
import model.service.exceptions.PreexistingEntityException;
import model.service.interfaces.IBrandAccessSvc;
import model.service.interfaces.ICartAccessSvc;
import model.service.interfaces.IImageAccessSvc;
import model.service.interfaces.IItemAccessSvc;
import model.service.interfaces.IPriceAccessSvc;
import model.service.interfaces.IUserAccessSvc;
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

    private int itemId;

    private HttpSession session;

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


    String resultPage;

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
        FacesContext facesContext = FacesContext.getCurrentInstance();
        session = (HttpSession) facesContext.getExternalContext().getSession (false);

        if (session == null)
        {
            userInfo = new Users();
            session.setAttribute ("userBean", userInfo);
            session.setAttribute ("expired", true);
////            resultPage = "/login.xhtml";
        }
        else
        {
            userInfo = (Users)session.getAttribute ("userBean");
        }
    }

    @Override
    public void cartAdd()
    {
        List<IItems> curCartList;
        IItems curItem;
        List<ICart> cListTable;
        ICartPK cListPK;
        ICart newCart = new Cart();
        int custID = 0;
        ListIterator cListIter;
        boolean exist = false;

        // Get the customerID
        custID = ((Customer)((List)(userInfo.getCustomerCollection())).get(0)).getIdCustomer();

        // Read the current cart list (check session object, read DB)
        curCartList = (List<IItems>)session.getAttribute ("cartList");

        // Create the new Cart entry
        cListPK = new CartPK (0, custID);
        newCart.setCustomer ((Customer)((List)(userInfo.getCustomerCollection())).get (0));
        newCart.setItemsidItems (itemId);
        newCart.setCreation (new Date());
        newCart.setCartPK (cListPK);
        newCart.setQty (1);

        if (curCartList == null)
        {
            cListTable = cartSvc.findCartByCustomer (custID);

            // No list stored
            if (cListTable == null || cListTable.isEmpty())
            {
                cListTable = new ArrayList<>();

                // Store the item reference in db
                try
                {
////                    cartSvc.save (newCart);
                    cListTable.add (newCart);
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
////                try
////                {
////                    cartSvc.save (newCart);
////                    cListTable.add (newCart);
////                }
////                catch (PreexistingEntityException e)
////                {
////                    exist = true;
////                }
////                catch (Exception ex)
////                {
                    // TODO: Report Error
////                }
            }

            // Build the item list
            curCartList = new ArrayList<>();
            cListIter = cListTable.listIterator();

            do
            {
                newCart = (Cart)cListIter.next();
                Collection<Items> items;
                Items[] itemArray;
                newCart = (Cart)cListIter.next();

                curItem = new Items (newCart.getItemsidItems());
                items = (Collection<Items>)itemSvc.find ((IDomainObject)curItem);

                itemArray = items.toArray (new Items[0]);
                curItem = itemArray[0];
                curItem.setQty (newCart.getQty());
                curCartList.add (curItem);
            } while (cListIter.hasNext());
        } // ENDIF: if (curCartList == null)
        // Found a current cart list, add the new item
        else
        {
            // Store the item reference in db
            try
            {
////                cartSvc.save (newCart);

                // Add item to list
////                curItem = itemSvc.find (itemId);
////                curItem.setQty (1);
////                curCartList.add (curItem);
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
        ICart newCart = new Cart();
        int custID = 0;
        ListIterator cListIter;

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
        cListPK = new CartPK (0, custID);
        newCart.setCustomer ((Customer)((List)(userInfo.getCustomerCollection())).get (0));
        newCart.setItemsidItems (itemId);
        newCart.setCreation (new Date());
        newCart.setCartPK (cListPK);
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
                Collection<Items> items;
                Items[] itemArray;
                newCart = (Cart)cListIter.next();

                curItem = new Items (newCart.getItemsidItems());
                items = (Collection<Items>)itemSvc.find ((IDomainObject)curItem);

                itemArray = items.toArray (new Items[0]);
                curItem = itemArray[0];
                curItem.setQty (newCart.getQty());
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
////                curItem = itemSvc.find (itemId);
////                curItem.setQty (1);
////                curCartList.add (curItem);
            }
            catch (Exception ex)
            {
                // TODO: Report Error
            }
        } // ENDIF-ELSE: if (curCartList == null)

        session.setAttribute ("cartList", curCartList);
        session.setAttribute ("guestCart", cListTable);
    }

    private void cartRemove()
    {
        List<IItems> curCartList;
        List<ICart> cListTable;
        ListIterator cListIter;
        IItems curItem;
        ICart newCart;
        int custID = 0;

        resultPage = "/orders/viewCart.jsp";


        // Get the customerID
        custID = ((Customer)((List)(userInfo.getCustomerCollection())).get(0)).getIdCustomer();

        // Get the item to remove
        newCart = cartSvc.findCartByCustomerAndItem (custID, itemId);

        // Remove it from the DB (Cart)
////        cartSvc.delete (newCart.getCartPK());

        // Remove it from the display
        cListTable = cartSvc.findCartByCustomer (custID);

        // Build the item list
        curCartList = new ArrayList<>();
        cListIter = cListTable.listIterator();

        while (cListIter.hasNext())
        {
            newCart = (Cart)cListIter.next();
            Collection<Items> items;
                Items[] itemArray;
                newCart = (Cart)cListIter.next();

                curItem = new Items (newCart.getItemsidItems());
                items = (Collection<Items>)itemSvc.find ((IDomainObject)curItem);

                itemArray = items.toArray (new Items[0]);
                curItem = itemArray[0];
            curItem.setQty (newCart.getQty());
            curCartList.add (curItem);
        }

////        curItem = itemSvc.find (itemId);

        if (!curCartList.isEmpty())
        {
////            curCartList.remove (curItem);
        }

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

        curCartList = (List<IItems>)session.getAttribute ("cartList");
        cListTable = (List<Cart>)session.getAttribute ("guestCart");

        // Create a new Cart without the item
        cListIter = cListTable.listIterator();

        while (cListIter.hasNext())
        {
            newCart = (Cart)cListIter.next();

            if (newCart.getItemsidItems() != itemId)
            {
                newCartListTable.add (newCart);
            }
        }

        // Build the item list
        curCartList = new ArrayList<>();
        cListIter = newCartListTable.listIterator();

        while (cListIter.hasNext())
        {
            Collection<Items> items;
            newCart = (Cart)cListIter.next();
            curItem = new Items (newCart.getItemsidItems());
            items = (Collection<Items>)itemSvc.find ((IDomainObject)curItem);

            itemArray = items.toArray (new Items[0]);
            curItem = itemArray[0];

            curItem.setQty (newCart.getQty());
            curCartList.add (curItem);
        }

        session.setAttribute ("cartList", curCartList);
        session.setAttribute ("guestCart", newCartListTable);
    }

    private void viewCart()
    {
        List<IItems> curCartList;
        IItems curItem;
        List<ICart> cListTable;
        ICart newCart;
        int custID = 0;
        ListIterator cListIter;

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
            custID = ((Customer)((List)(userInfo.getCustomerCollection())).get(0)).getIdCustomer();
        }

        // Read the current cart (check session object, read DB)
        curCartList = (List<IItems>)session.getAttribute ("cartList");

        if (curCartList == null)
        {
            if (!userInfo.getUserName().equalsIgnoreCase ("guest"))
            {
                cListTable = cartSvc.findCartByCustomer (custID);
            }
            else
            {
                cListTable = (List<ICart>)session.getAttribute ("guestCart");
            }

            if (cListTable == null)
            {
                cListTable = new ArrayList<>();
            }

            // Build the item list
            curCartList = new ArrayList<>();
            cListIter = cListTable.listIterator();

            while (cListIter.hasNext())
            {
                Collection<Items> items;
                Items[] itemArray;
                newCart = (Cart)cListIter.next();

                curItem = new Items (newCart.getItemsidItems());
                items = (Collection<Items>)itemSvc.find ((IDomainObject)curItem);

                itemArray = items.toArray (new Items[0]);
                curItem = itemArray[0];
                curItem.setQty (newCart.getQty());
                curCartList.add (curItem);
            }
        } // ENDIF: if (curCartList == null)

        session.setAttribute ("cartList", curCartList);
    }

    public void guestCartUpdate()
    {
        throw new UnsupportedOperationException ("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

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
