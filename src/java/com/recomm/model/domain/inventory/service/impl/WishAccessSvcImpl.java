/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recomm.model.domain.inventory.service.impl;

import com.recomm.model.domain.inventory.interfaces.IWishAccessSvc;
import com.recomm.business.logging.Logger;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import com.recomm.model.domain.interfaces.IDomainObject;
import com.recomm.model.domain.interfaces.ISearchParms;
import com.recomm.model.domain.interfaces.IWishlist;
import com.recomm.model.domain.inventory.entity.Wishlist;
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
public class WishAccessSvcImpl extends HibernateSvc implements Serializable, IWishAccessSvc
{
    private static final long serialVersionUID = 41L;

    @Override
    public Collection<IWishlist> readWishlists()
    {
        Collection<IWishlist> items = new ArrayList<IWishlist>();
        Transaction transaction;
        Query query;
        Iterator<IWishlist> iterator;

        if (invsession == null)
        {
            loadService ("inv");
        }

        transaction = invsession.beginTransaction();
        query = invsession.createQuery ("from cart");
        iterator = (Iterator<IWishlist>)query.iterate();

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
	public boolean updateWishlist (IWishlist cart, boolean isUpdate)
	{
		boolean result = true;
        Collection<IWishlist> wishlists = new ArrayList<IWishlist>();
        Collection<IWishlist> newList = new ArrayList<IWishlist>();

        if (isUpdate)
        {
            // TODO: complete update code later
            result = true;
        }
        else
        {
            wishlists.add ((IWishlist)cart);
            newList = (Collection<IWishlist>)merge (wishlists);
        }

        if (newList.isEmpty())
        {
            result = false;
        }

        return result;
	}

    /**
     *
     * @param idWishlist
     * @return
     */
    @Override
	public IWishlist readWishlist (Integer idWishlist)
	{
		return (IWishlist)find (new Wishlist (idWishlist));
	}

    /**
     *
     * @param wishlist
     * @return
     */
    @Override
	public boolean removeWishlist (IWishlist wishlist)
	{
		boolean result = true;
        Transaction transaction = null;

        if (wishlist != null)
        {
            initInvSession();

            if (invsession != null)
            {
                try
                {
                    transaction = invsession.beginTransaction();
                    invsession.createSQLQuery ("SET FOREIGN_KEY_CHECKS = 0").executeUpdate();
                    invsession.createSQLQuery ("DELETE FROM wishlist WHERE idwishlist = " + wishlist.getIdWishlist()).executeUpdate();
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
                            Logger.log (WishAccessSvcImpl.class, e);
                            transaction.rollback();
                            close();
                        }
                        catch (Exception ex)
                        {
                            Logger.log (WishAccessSvcImpl.class, "Rollback() failed");
                            close();
                        }
                    }
                }
            }
            else
            {
                Logger.log (WishAccessSvcImpl.class, "WishAccessSvcImpl.save failed with a NULL session.");
                result = false;
                close();
            }
		}
        else
        {
            Logger.log (WishAccessSvcImpl.class, "WishAccessSvcImpl.removeCart failed with a NULL object.");
            result = false;
            close();
        }

        return result;
	}

    @Override
    public boolean save (Collection<?> object)
    {
        boolean result = true;
        Transaction transaction = null;
        Wishlist newItem;
        Iterator iterator;
        Collection<IWishlist> wishlists;

		if (object != null)//validate
		{
            wishlists = (Collection<IWishlist>) object;
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
                    Logger.log (WishAccessSvcImpl.class, "Transaction failed");
                    close();
                }

                try
                {
                    iterator = wishlists.iterator();

                    while (iterator.hasNext() && result)
                    {
                        newItem = (Wishlist)iterator.next();
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
                            Logger.log (WishAccessSvcImpl.class, e);
                            transaction.rollback();
                            close();
                        }
                        catch (Exception ex)
                        {
                            Logger.log (WishAccessSvcImpl.class, "Rollback() failed");
                            close();
                        }
                    }
                }
            }
            else
            {
                Logger.log (WishAccessSvcImpl.class, "WishAccessSvcImpl.save failed with a NULL session.");
                result = false;
                close();
            }
		}
        else
        {
            Logger.log (WishAccessSvcImpl.class, "WishAccessSvcImpl.persist failed with a NULL object.");
            result = false;
            close();
        }

		return result;
    }

    /**
     *
     * @param object
     * @return
     */
    @Override
	public Collection<IWishlist> find (IDomainObject object)
	{
        Wishlist wishlist = (Wishlist)object;
        Collection<IWishlist> wishList = new ArrayList<IWishlist>();
        NativeQuery result;
        List<Object[]> resultSet;

        initInvSession();

        if (invsession!= null)
        {
            String query = "SELECT * FROM wishlist" +
                           " WHERE customer_idCustomer LIKE '%" + wishlist.getCustomerIdCustomer()+ "%'" +
                           " AND Items_idItems LIKE '%" + wishlist.getItemsidItems()+ "%'";
            try
            {
                result = invsession.createSQLQuery (query);
                resultSet = (List<Object[]>)result.list();

                if (!resultSet.isEmpty())
                {
                    for (Object[] element : resultSet)
                    {
                        wishlist = new Wishlist();

                        try
                        {
                            wishlist.setIdWishlist (Integer.parseInt (element[0].toString()));

                            try
                            {
                                wishlist.setCustomerIdCustomer (Integer.parseInt(element[1].toString()));

                                try
                                {
                                    wishlist.setItemsidItems (Integer.parseInt (element[2].toString()));

                                    try
                                    {
                                        wishlist.setQty (Integer.parseInt (element[3].toString()));
                                    }
                                    catch (Exception e)
                                    {
                                        wishlist.setQty (0);
                                    }

                                    wishlist.setCreation (new Date (element[4].toString()));
                                    wishList.add (wishlist);
                                }
                                catch (Exception e)
                                {
                                    close();
                                }
                            }
                            catch (Exception e)
                            {
                                close();
                            }
                        }
                        catch (Exception e)
                        {
                            close();
                        }
                    }
                }
                // else do nothing
            }
            catch (Exception e)
            {
                wishList = new ArrayList<IWishlist>();
                Logger.log (WishAccessSvcImpl.class, e);
                close();
            }
        }
        else
        {
            Logger.log (WishAccessSvcImpl.class, "Cart.search failed with a NULL session.");
            close();
        }

        return wishList;
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
        invsession.createSQLQuery ("TRUNCATE wishlist").executeUpdate();
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
        Wishlist newWishlist;
        Iterator iterator;
        Collection<Wishlist> cartList;

		if (object != null)//validate
		{
            cartList = (Collection<Wishlist>) object;
            initInvSession();

            if (invsession != null)
            {
                try
                {
                    transaction = invsession.beginTransaction();
                    iterator = cartList.iterator();

                    while (iterator.hasNext() && result)
                    {
                        newWishlist = (Wishlist)iterator.next();
                        invsession.persist (newWishlist);
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
                            Logger.log (WishAccessSvcImpl.class, e);
                            transaction.rollback();
                            close();
                        }
                        catch (Exception ex)
                        {
                            Logger.log (WishAccessSvcImpl.class, "Rollback() failed");
                            close();
                        }
                    }
                }
            }
            else
            {
                Logger.log (WishAccessSvcImpl.class, "WishtAccessSvcImpl.persist failed with a NULL session.");
                close();
            }
		}
        else
        {
            Logger.log (WishAccessSvcImpl.class, "WishtAccessSvcImpl.persist failed with a NULL object.");
            result = false;
            close();
        }

		return result;
    }

    @Override
    public Collection<?> merge (Collection<?> object)
    {
        Transaction transaction = null;
        IWishlist newWishlist;
        Iterator iterator;
        Collection<IWishlist> wishList;
        Collection<IWishlist> newList = new ArrayList<IWishlist>();

        if (object != null)//validate
        {
            wishList = (Collection<IWishlist>) object;
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
                    Logger.log (WishAccessSvcImpl.class, "Transaction failed");
                    invsession.flush();
                    invsession.close();
                }

                try
                {
                    iterator = wishList.iterator();

                    do
                    {
                        newWishlist = (IWishlist)iterator.next();
                        newWishlist = (IWishlist)invsession.merge (newWishlist);
                        newList.add (newWishlist);
                    } while (iterator.hasNext() && (newWishlist != null));

                    transaction.commit();
                }
                catch (Exception e)
                {
                    Logger.log (WishAccessSvcImpl.class, e);
                    newList = new ArrayList<IWishlist>();
                    close();
                }
            }
            else
            {
                Logger.log (WishAccessSvcImpl.class, "WishAccessSvcImpl.merge failed with a NULL session.");
                newList = new ArrayList<IWishlist>();
                close();
            }
        }
        else
        {
            Logger.log (WishAccessSvcImpl.class, "WishAccessSvcImpl.merge failed with a NULL object.");
            newList = new ArrayList<IWishlist>();
            close();
        }

        return newList;
    }

    @Override
    public Collection<?> search (ISearchParms searchParms)
    {
        Wishlist entry = new Wishlist();
        Collection<Wishlist> wishList = new ArrayList<Wishlist>();
        NativeQuery result;
        List<Object[]> resultSet;
        CartSearchParams params = (CartSearchParams)searchParms;
        Iterator wishItr;
        Object[] wishObject;

        initInvSession();

        if (invsession!= null)
        {
            String query = "SELECT * FROM wishlist" +
                           " WHERE customer_idCustomer LIKE '" + params.getIdCustomer() + "'" +
                           " AND Items_idItems LIKE '" + params.getIdItem() + "'";
            try
            {
                result = invsession.createSQLQuery (query);
                resultSet = (List<Object[]>)result.list();

                if (!resultSet.isEmpty())
                {
                    wishItr = resultSet.iterator();

                    do
                    {
                        entry = new Wishlist();

                        wishObject = (Object[])wishItr.next();
                        DateFormat format = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss.SSS", Locale.ENGLISH);
                        Date date;

                        try
                        {
                            entry.setIdWishlist (Integer.parseInt (wishObject[0].toString()));

                            try
                            {
                                entry.setCustomerIdCustomer (Integer.parseInt (wishObject[1].toString()));

                                try
                                {
                                    entry.setItemsidItems (Integer.parseInt (wishObject[2].toString()));

                                    try
                                    {
                                        entry.setQty (Integer.parseInt (wishObject[3].toString()));
                                    }
                                    catch (Exception e)
                                    {
                                        entry.setQty (0);
                                    }

                                    date = format.parse (wishObject[4].toString());
                                    entry.setCreation (date);
                                    wishList.add (entry);
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
                    } while (wishItr.hasNext());
                }
                // else do nothing
            }
            catch (Exception e)
            {
                wishList = new ArrayList<Wishlist>();
                Logger.log (WishAccessSvcImpl.class, e);
            }
        }
        else
        {
            Logger.log (WishAccessSvcImpl.class, "Wishlist.search failed with a NULL session.");
        }

        return wishList;
    }

    @Override
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
