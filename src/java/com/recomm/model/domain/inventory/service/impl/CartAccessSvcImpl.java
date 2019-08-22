/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recomm.model.domain.inventory.service.impl;

import com.recomm.business.logging.Logger;
import com.recomm.model.domain.inventory.service.interfaces.ICartAccessSvc;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import com.recomm.model.domain.inventory.interfaces.ICart;
import com.recomm.model.domain.interfaces.IDomainObject;
import com.recomm.model.domain.interfaces.ISearchParms;
import com.recomm.model.domain.inventory.entity.Cart;
import com.recomm.model.domain.inventory.util.CartSearchParams;
import com.recomm.model.service.impl.HibernateSvc;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Service;

/**
 *
 * @author Paul G. Allen <pgallen@gmail.com>
 */
@Service
public class CartAccessSvcImpl extends HibernateSvc implements ICartAccessSvc, Serializable
{
    private static final long serialVersionUID = 41L;

    @Override
    public Collection<ICart> readCarts()
    {
        Collection<ICart> items = new ArrayList<ICart>();
        Transaction transaction;
        Query query;
        Iterator<ICart> iterator;

        if (invsession == null)
        {
            loadService ("inv");
        }

        transaction = invsession.beginTransaction();
        query = invsession.createQuery ("from cart");
        iterator = (Iterator<ICart>)query.iterate();

        while (iterator.hasNext())
        {
            items.add (iterator.next());
        }

        transaction.commit();

        return items;
    }

        /**
     *
     * @param cart
     * @param isUpdate
     * @return
     */
    @Override
	public boolean updateCart (ICart cart, boolean isUpdate)
	{
		boolean result = true;
        Collection<ICart> newList = new ArrayList<ICart>();
        Collection<ICart> cartList = new ArrayList<ICart>();

        if (isUpdate)
        {
            // TODO: complete update code later
            result = true;
        }
        else
        {
            cartList.add ((ICart)cart);
            newList = (Collection<ICart>)merge (cartList);
        }

        if (newList.isEmpty())
        {
            result = false;
        }

        return result;
	}

    /**
     *
     * @param idCart
     * @return
     */
    @Override
	public ICart readCart (Integer idCart)
	{
		return (ICart)find (new Cart (idCart));
	}

    /**
     *
     * @param cart
     * @return
     */
    @Override
	public boolean removeCart (ICart cart)
	{
		boolean result = true;
        Transaction transaction = null;

        if (cart != null)
        {
            initInvSession();

            if (invsession != null)
            {
                try
                {
                    transaction = invsession.beginTransaction();
                    invsession.createSQLQuery ("SET FOREIGN_KEY_CHECKS = 0").executeUpdate();
                    invsession.createSQLQuery ("DELETE FROM cart WHERE idCart = " + cart.getIdCart()).executeUpdate();
                    invsession.createSQLQuery ("SET FOREIGN_KEY_CHECKS = 1").executeUpdate();
                    transaction.commit();
                }
                catch (Exception e)
                {
                    result = false;

                    if (transaction != null)
                    {
                        try
                        {
                            Logger.log (CartAccessSvcImpl.class, e);
                            transaction.rollback();
                        }
                        catch (Exception ex)
                        {
                            Logger.log (CartAccessSvcImpl.class, "Rollback() failed");
                        }
                    }
                }
            }
            else
            {
                Logger.log (CartAccessSvcImpl.class, "CartAccessSvcImpl.save failed with a NULL session.");
                result = false;
            }
		}
        else
        {
            Logger.log (CartAccessSvcImpl.class, "CartAccessSvcImpl.removeCart failed with a NULL object.");
            result = false;
        }

        return result;
	}

    @Override
    public boolean save (Collection<?> object)
    {
        boolean result = true;
        Transaction transaction = null;
        Cart newItem;
        Iterator iterator;
        Collection<ICart> items;

		if (object != null)//validate
		{
            items = (Collection<ICart>) object;
            initInvSession();

            if (invsession != null)
            {
                try
                {
                    transaction = invsession.beginTransaction();
                    iterator = items.iterator();

                    while (iterator.hasNext() && result)
                    {
                        newItem = (Cart)iterator.next();
                        invsession.save (newItem);
                    }

                    transaction.commit();
                }
                catch (Exception e)
                {
                    result = false;

                    if (transaction != null)
                    {
                        try
                        {
                            Logger.log (CartAccessSvcImpl.class, e);
                            transaction.rollback();
                        }
                        catch (Exception ex)
                        {
                            Logger.log (CartAccessSvcImpl.class, "Rollback() failed");
                        }
                    }
                }
            }
            else
            {
                Logger.log (CartAccessSvcImpl.class, "CartAccessSvcImpl.save failed with a NULL session.");
                result = false;
            }
		}
        else
        {
            Logger.log (CartAccessSvcImpl.class, "CartAccessSvcImpl.persist failed with a NULL object.");
            result = false;
        }

		return result;
    }

    /**
     *
     * @param object
     * @return
     */
    @Override
	public Collection<ICart> find (IDomainObject object)
	{
        Cart cart = (Cart)object;
        Collection<ICart> cartList = new ArrayList<ICart>();
        NativeQuery result;
        List<Object[]> resultSet;

        initInvSession();

        if (invsession!= null)
        {
            String query = "SELECT * FROM cart" +
                           " WHERE customer_idCustomer LIKE '%" + cart.getCustomerIdCustomer()+ "%'" +
                           " AND Items_idItems LIKE '%" + cart.getItemsidItems()+ "%'";
            try
            {
                result = invsession.createSQLQuery (query);
                resultSet = (List<Object[]>)result.list();

                if (!resultSet.isEmpty())
                {
                    for (Object[] element : resultSet)
                    {
                        cart = new Cart();

                        try
                        {
                            cart.setIdCart (Integer.parseInt (element[0].toString()));

                            try
                            {
                                cart.setCustomerIdCustomer (Integer.parseInt(element[1].toString()));

                                try
                                {
                                    cart.setItemsidItems (Integer.parseInt (element[2].toString()));

                                    try
                                    {
                                        cart.setQty (Integer.parseInt (element[3].toString()));
                                    }
                                    catch (Exception e)
                                    {
                                        cart.setQty (0);
                                    }

                                    cart.setCreation (new Date (element[4].toString()));
                                    cartList.add (cart);
                                }
                                catch (Exception e)
                                {
                                }
                            }
                            catch (Exception e)
                            {
                            }
                        }
                        catch (Exception e)
                        {
                        }
                    }
                }
                // else do nothing
            }
            catch (Exception e)
            {
                cartList = new ArrayList<ICart>();
                Logger.log (CartAccessSvcImpl.class, e);
            }
        }
        else
        {
            Logger.log (CartAccessSvcImpl.class, "Cart.search failed with a NULL session.");
        }

        return cartList;
	}

    @Override
    public boolean delete (String type)
    {
        boolean result = false;
        Transaction transaction;

        if (invsession == null)
        {
            loadService ("inv");
        }

        transaction = invsession.beginTransaction();
        invsession.createSQLQuery ("SET FOREIGN_KEY_CHECKS = 0").executeUpdate();
        invsession.createSQLQuery ("TRUNCATE cart").executeUpdate();
        invsession.createSQLQuery ("SET FOREIGN_KEY_CHECKS = 1").executeUpdate();
        transaction.commit();
        result = true;

        return result;
    }

    @Override
    public boolean persist (Collection<?> object)
	{
		boolean result = true;
        Transaction transaction = null;
        Cart newCart;
        Iterator iterator;
        Collection<Cart> cartList;

		if (object != null)//validate
		{
            cartList = (Collection<Cart>) object;
            initInvSession();

            if (invsession != null)
            {
                try
                {
                    transaction = invsession.beginTransaction();
                    iterator = cartList.iterator();

                    while (iterator.hasNext() && result)
                    {
                        newCart = (Cart)iterator.next();
                        invsession.persist (newCart);
                    }

                    transaction.commit();
                }
                catch (Exception e)
                {
                    result = false;

                    if (transaction != null)
                    {
                        try
                        {
                            Logger.log (CartAccessSvcImpl.class, e);
                            transaction.rollback();
                        }
                        catch (Exception ex)
                        {
                            Logger.log (CartAccessSvcImpl.class, "Rollback() failed");
                        }
                    }
                }
            }
            else
            {
                Logger.log (CartAccessSvcImpl.class, "CartAccessSvcImpl.persist failed with a NULL session.");
            }
		}
        else
        {
            Logger.log (CartAccessSvcImpl.class, "CartAccessSvcImpl.persist failed with a NULL object.");
            result = false;
        }

		return result;
    }

    @Override
    public Collection<?> merge (Collection<?> object)
    {
        Transaction transaction = null;
        ICart newCart = null;
        Iterator iterator;
        Collection<ICart> cartList;
        Collection<ICart> newList = new ArrayList<ICart>();

        if (object != null)//validate
        {
            cartList = (Collection<ICart>) object;
            initInvSession();

            if (invsession != null)
            {
                try
                {
                    transaction = invsession.beginTransaction();
                }
                catch (IllegalStateException e)
                {

                }
                catch (Exception e)
                {
                    Logger.log (CartAccessSvcImpl.class, "Transaction failed");
                    close();
                }

                try
                {
                    iterator = cartList.iterator();

                    do
                    {
                        newCart = (ICart)iterator.next();
                        newCart = (ICart)invsession.merge (newCart);
                        newList.add (newCart);
                    } while (iterator.hasNext() && (newCart != null));

                    transaction.commit();
                    close();
                }
                catch (Exception e)
                {
                    Logger.log (CartAccessSvcImpl.class, e);
                    newList = new ArrayList<ICart>();
                    close();
                }
            }
            else
            {
                Logger.log (CartAccessSvcImpl.class, "CartAccessSvcImpl.merge failed with a NULL session.");
                newList = new ArrayList<ICart>();
                close();
            }
        }
        else
        {
            Logger.log (CartAccessSvcImpl.class, "CartAccessSvcImpl.merge failed with a NULL object.");
            newList = new ArrayList<ICart>();
            close();
        }

        return newList;
    }

    @Override
    public Collection<?> search (ISearchParms searchParms)
    {
        Cart cart = new Cart();
        Collection<Cart> cartList = new ArrayList<Cart>();
        NativeQuery result;
        List<Object[]> resultSet;
        CartSearchParams params = (CartSearchParams)searchParms;
        Iterator cartItr;
        Object[] cartObject;

        initInvSession();

        if (invsession!= null)
        {
            String query = "SELECT * FROM cart" +
                           " WHERE customer_idCustomer LIKE '" + params.getIdCustomer() + "'" +
                           " AND Items_idItems LIKE '" + params.getIdItem() + "'";
            try
            {
                result = invsession.createSQLQuery (query);
                resultSet = (List<Object[]>)result.list();

                if (!resultSet.isEmpty())
                {
                    cartItr = resultSet.iterator();

                    do
                    {
                        cart = new Cart();

                        cartObject = (Object[])cartItr.next();
                        DateFormat format = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss.SSS", Locale.ENGLISH);
                        Date date;

                        try
                        {
                            cart.setIdCart (Integer.parseInt (cartObject[0].toString()));

                            try
                            {
                                cart.setCustomerIdCustomer (Integer.parseInt(cartObject[1].toString()));

                                try
                                {
                                    cart.setItemsidItems (Integer.parseInt (cartObject[2].toString()));

                                    try
                                    {
                                        cart.setQty (Integer.parseInt (cartObject[3].toString()));
                                    }
                                    catch (Exception e)
                                    {
                                        cart.setQty (0);
                                        close();
                                    }

                                    date = format.parse (cartObject[4].toString());
                                    cart.setCreation (date);
                                    cartList.add (cart);
                                }
                                catch (Exception e)
                                {
                                }
                            }
                            catch (Exception e)
                            {
                            }
                        }
                        catch (Exception e)
                        {
                        }
                    } while (cartItr.hasNext());
                }
                // else do nothing
            }
            catch (Exception e)
            {
                cartList = new ArrayList<Cart>();
                Logger.log (CartAccessSvcImpl.class, e);
                close();
            }
        }
        else
        {
            Logger.log (CartAccessSvcImpl.class, "Cart.search failed with a NULL session.");
            close();
        }

        return cartList;
    }

    public void close()
    {
        if (invsession !=null)
        {
            try
            {
                invsession.flush();
                invsession.close();
            }
            catch (Exception e)
            {

            }
            finally
            {
                invsession = null;
            }
        }
    }
}
