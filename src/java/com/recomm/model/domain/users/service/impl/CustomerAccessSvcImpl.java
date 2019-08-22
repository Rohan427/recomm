/*
 * Copyright (c) 2018 Random Logic Consulting and Paul G. Allen.
 * All Rights Reserved.
 */
package com.recomm.model.domain.users.service.impl;

import com.recomm.model.domain.users.service.interfaces.ICustomerAccessSvc;
import com.recomm.model.domain.interfaces.IDomainObject;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import com.recomm.business.logging.Logger;
import com.recomm.model.domain.interfaces.ISearchParms;
import com.recomm.model.domain.inventory.util.CartSearchParams;
import com.recomm.model.domain.users.util.CustomerSearchParams;
import com.recomm.model.domain.users.entity.Address;
import com.recomm.model.domain.users.entity.Customer;
import com.recomm.model.domain.users.entity.Users;
import com.recomm.model.domain.users.interfaces.ICustomer;
import com.recomm.model.service.impl.HibernateSvc;
import java.util.Calendar;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Service;

/**
 *
 * @author Paul G. Allen <pgallen@gmail.com>
 */
@Service
public class CustomerAccessSvcImpl extends HibernateSvc implements ICustomerAccessSvc, Serializable
{
    @Override
    public boolean delete (String type)
    {
        boolean result = false;
        Transaction transaction;

////        if (usersession == null)
////        {
////            loadService();
////        }
////
////        transaction = usersession.beginTransaction();
////        usersession.createSQLQuery ("SET FOREIGN_KEY_CHECKS = 0").executeUpdate();
////        usersession.createSQLQuery ("TRUNCATE Users").executeUpdate();
////        usersession.createSQLQuery ("SET FOREIGN_KEY_CHECKS = 1").executeUpdate();
////
////        transaction.commit();
////        result = true;

        return result;
    }

    @Override
    public Collection<Customer> find (IDomainObject object)
    {
        Customer customer = null;
        String query = null;
        NativeQuery result;
        List<Object[]> resultSet;
        initUserSession();
        Iterator custeromItr;
        Object[] customerObject;
        Collection<Customer> customers = new ArrayList<Customer>();

        if (usersession != null)
        {
            customer = (Customer)object;
            query = "SELECT * FROM customer WHERE idCustomer = '"
                  + customer.getIdCustomer() + "'";
            try
            {
                result = usersession.createSQLQuery (query);
                resultSet = (List<Object[]>)result.list();

                if (!resultSet.isEmpty())
                {
                    custeromItr = resultSet.iterator();

                    do
                    {
                        customerObject = (Object[])custeromItr.next();
                        DateFormat format = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss.SSS", Locale.ENGLISH);
                        Date date;
                        customer.setIdCustomer (Integer.parseInt (customerObject[0].toString()));
                        customer.setFirstName (customerObject[1].toString());
                        customer.setMi (customerObject[2].toString());
                        customer.setLName (customerObject[3].toString());
                        customer.setAddress (new Address (0,
                                                          customerObject[4].toString(),
                                                          customerObject[5].toString(),
                                                          customerObject[6].toString(),
                                                          customerObject[7].toString(),
                                                          customerObject[8].toString(),
                                                          customerObject[9].toString(),
                                                          Calendar.getInstance().getTime()
                                                         )
                                            );

                        customer.getAddress().setPhone1 (customerObject[10].toString());
                        customer.getAddress().setPhone2 (customerObject[11].toString());
                        customer.getAddress().setPhone3 (customerObject[12].toString());

                        customer.setEmail (customerObject[13].toString());
                        customer.setUsersidUsers (new Users (Integer.parseInt (customerObject[14].toString())));

                        date = format.parse (customerObject[15].toString());
                        customer.setCreated (date);

                        customers.add (customer);
                    } while (custeromItr.hasNext());
                }
                else
                {
                    customers = new ArrayList<Customer>();
                }
            }
            catch (Exception e)
            {
                customers = new ArrayList<Customer>();
                Logger.log (CustomerAccessSvcImpl.class, e);
            }
        }
        else
        {
            Logger.log (CustomerAccessSvcImpl.class, "CustomerAccessSvcImpl.find failed with a NULL session.");
        }

        return customers;
    }

    @Override
    public boolean persist (Collection<?> object)
    {
        boolean result = true;
        Transaction transaction = null;
        Customer newCustomer;
        Iterator iterator;
        Collection<Customer> customers;

		if (object != null)//validate
		{
            customers = (Collection<Customer>) object;
            initUserSession();

            if (usersession != null)
            {
                try
                {
                    transaction = usersession.beginTransaction();
                    iterator = customers.iterator();

                    while (iterator.hasNext() && result)
                    {
                        newCustomer = (Customer)iterator.next();
                        usersession.persist (newCustomer);
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
                            Logger.log (CustomerAccessSvcImpl.class, e);
                            transaction.rollback();
                        }
                        catch (Exception ex)
                        {
                            Logger.log (CustomerAccessSvcImpl.class, "Rollback() failed");
                        }
                    }
                }
            }
            else
            {
                Logger.log (CustomerAccessSvcImpl.class, "CustomerAccessSvcImpl.persist failed with a NULL session.");
            }
		}
        else
        {
            Logger.log (CustomerAccessSvcImpl.class, "CustomerAccessSvcImpl.persist failed with a NULL object.");
            result = false;
        }

		return result;
    }

    @Override
    public boolean save (Collection<?> object)
    {
        boolean result = true;
        Transaction transaction = null;
        Customer newCustomer;
        Iterator iterator;
        Collection<Customer> customers;

		if (object != null)//validate
		{
            customers = (Collection<Customer>) object;
            initUserSession();

            if (usersession != null)
            {
                try
                {
                    transaction = usersession.beginTransaction();
                    iterator = customers.iterator();

                    while (iterator.hasNext() && result)
                    {
                        newCustomer = (Customer)iterator.next();
                        usersession.save (newCustomer);
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
                            Logger.log (CustomerAccessSvcImpl.class, e);
                            transaction.rollback();
                        }
                        catch (Exception ex)
                        {
                            Logger.log (CustomerAccessSvcImpl.class, "Rollback() failed");
                        }
                    }
                }
            }
            else
            {
                Logger.log (CustomerAccessSvcImpl.class, "CustomerAccessSvcImpl.save failed with a NULL session.");
                result = false;
            }
		}
        else
        {
            Logger.log (CustomerAccessSvcImpl.class, "CustomerAccessSvcImpl.persist failed with a NULL object.");
            result = false;
        }

		return result;
    }

    @Override
    public Customer readCustomer (Integer idCustomer)
    {
        return (Customer)find (new Customer (idCustomer));
    }

    @Override
    public Collection<Customer> readCustomers()
    {
        Collection<Customer> customers = new ArrayList<Customer>();
        org.hibernate.query.Query query;
        Iterator<Customer> iterator;
        initUserSession();

        if (usersession != null)
        {
            try
            {
                query = usersession.createQuery ("from customer");
                iterator = (Iterator<Customer>)query.iterate();

                while (iterator.hasNext())
                {
                    customers.add (iterator.next());
                }
            }
            catch (Exception e)
            {
                Logger.log (CustomerAccessSvcImpl.class, e);
            }
        }
        else
        {
            Logger.log (CustomerAccessSvcImpl.class, "CustomerAccessSvcImpl.readCustomers failed with a NULL session.");
        }

        return customers;
    }

    @Override
    public boolean removeCustomer (ICustomer customer)
    {
        return true;
    }

    @Override
    public boolean updateCustomer (ICustomer customer, boolean isUpdate)
    {
        boolean result = true;
        Collection<ICustomer> customers = new ArrayList<ICustomer>();
        Collection<ICustomer> newList = new ArrayList<ICustomer>();

        if (isUpdate)
        {
            // TODO: complete update code later
            result = true;
        }
        else
        {
            customers.add ((ICustomer)customer);
            newList = (Collection<ICustomer>)merge (customers);
        }

        if (newList.isEmpty())
        {
            result = false;
        }

        return result;
    }

    @Override
    public Collection<?> merge (Collection<?> object)
    {
        Transaction transaction;
        ICustomer newCustomer;
        Iterator iterator;
        Collection<ICustomer> customers;
        Collection<ICustomer> newList = new ArrayList<ICustomer>();

        if (object != null)//validate
        {
            customers = (Collection<ICustomer>) object;
            initUserSession();

            if (usersession != null)
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
                    Logger.log (CustomerAccessSvcImpl.class, "Transaction failed");
                    invsession.flush();
                    invsession.close();
                }

                try
                {
                    transaction = usersession.beginTransaction();
                    iterator = customers.iterator();

                    do
                    {
                        newCustomer = (ICustomer)iterator.next();
                        newCustomer = (ICustomer)usersession.merge (newCustomer);
                        newList.add (newCustomer);
                    } while (iterator.hasNext() && newCustomer != null);

                    transaction.commit();
                }
                catch (Exception e)
                {
                    Logger.log (CustomerAccessSvcImpl.class, e);
                    newList = new ArrayList<ICustomer>();
                }
            }
            else
            {
                Logger.log (CustomerAccessSvcImpl.class, "CustomerAccessSvcImpl.merge failed with a NULL session.");
                newList = new ArrayList<ICustomer>();
            }
        }
        else
        {
            Logger.log (CustomerAccessSvcImpl.class, "CustomerAccessSvcImpl.merge failed with a NULL object.");
        }

        return newList;
    }

    @Override
    public Collection<?> search (ISearchParms searchParms)
    {
        Customer customer = new Customer();
        Collection<Customer> customers = new ArrayList<Customer>();
        NativeQuery result;
        List<Object[]> resultSet;
        CustomerSearchParams params = (CustomerSearchParams)searchParms;
        Iterator customerItr;
        Object[] customerObject;

        initUserSession();

        if (usersession!= null)
        {
            String query = "SELECT * FROM customer" +
                           " WHERE idCustomer LIKE '" + params.getId() + "'" +
                           " AND FirstName LIKE '" + params.getFirstName() + "'" +
                           " AND LName LIKE '" + params.getLastName() + "'" +
                           " AND Address1 LIKE '" + params.getAddress1() + "'" +
                           " AND Address2 LIKE '" + params.getAddress2() + "'" +
                           " AND City LIKE '" + params.getCity() + "'" +
                           " AND State LIKE '" + params.getState() + "'" +
                           " AND Email LIKE '" + params.getEmail() + "'" +
                           " AND Users_idUsers LIKE '" + params.getIdUser() + "'";
            try
            {
                result = usersession.createSQLQuery (query);
                resultSet = (List<Object[]>)result.list();

                if (!resultSet.isEmpty())
                {
                    customerItr = resultSet.iterator();

                    do
                    {
                        customer = new Customer();

                        customerObject = (Object[])customerItr.next();
                        DateFormat format = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss.SSS", Locale.ENGLISH);
                        Date date;
                        customer.setIdCustomer (Integer.parseInt (customerObject[0].toString()));
                        customer.setFirstName (customerObject[1].toString());
                        customer.setMi (customerObject[2].toString());
                        customer.setLName (customerObject[3].toString());
                        customer.setAddress (new Address (0,
                                                          customerObject[4].toString(),
                                                          customerObject[5].toString(),
                                                          customerObject[6].toString(),
                                                          customerObject[7].toString(),
                                                          customerObject[8].toString(),
                                                          customerObject[9].toString(),
                                                          Calendar.getInstance().getTime()
                                                         )
                                            );

                        if (customerObject[10] != null)
                        {
                            customer.getAddress().setPhone1 (customerObject[10].toString());
                        }

                        if (customerObject[11] != null)
                        {
                            customer.getAddress().setPhone2 (customerObject[11].toString());
                        }

                        if (customerObject[12] != null)
                        {
                            customer.getAddress().setPhone3 (customerObject[12].toString());
                        }

                        customer.setEmail (customerObject[13].toString());
                        customer.setUsersidUsers (new Users (Integer.parseInt (customerObject[14].toString())));

                        date = format.parse (customerObject[15].toString());
                        customer.setCreated (date);

                        customers.add (customer);
                    } while (customerItr.hasNext());
                }
                else
                {
                    customers = new ArrayList<Customer>();
                }
            }
            catch (Exception e)
            {
                customers = new ArrayList<Customer>();
                Logger.log (CustomerAccessSvcImpl.class, e);
            }
        }
        else
        {
            Logger.log (CustomerAccessSvcImpl.class, "Customer.search failed with a NULL session.");
        }

        return customers;
    }

    public void close()
    {
        if (usersession !=null)
        {
            try
            {
                usersession.flush();
                usersession.close();
            }
            catch (Exception e)
            {

            }
            finally
            {
                usersession = null;
            }
        }
    }
}
