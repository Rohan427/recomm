/*
 * Copyright (c) 2018 Random Logic Consulting and Paul G. Allen.
 * All Rights Reserved.
 */
package model.service.impl;

import model.domain.interfaces.IDomainObject;
import model.domain.users.Users;
import model.service.dao.HashedObjectWrapper;
import model.service.interfaces.IUserAccessSvc;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import model.business.error.Logger;
import model.domain.interfaces.IUsers;
import model.service.dao.HibernateSvc;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Service;

/**
 *
 * @author Paul G. Allen <pgallen@gmail.com>
 */
@Service
public class UserAccessSvcImpl extends HibernateSvc implements IUserAccessSvc, Serializable
{
    private static final long serialVersionUID = 43L;
    private HashedObjectWrapper hashtable = null;

    @Override
    public boolean delete (String type)
    {
        boolean result = false;
        Transaction transaction;

////        if (session == null)
////        {
////            loadService();
////        }
////
////        transaction = session.beginTransaction();
////        session.createSQLQuery ("SET FOREIGN_KEY_CHECKS = 0").executeUpdate();
////        session.createSQLQuery ("TRUNCATE Users").executeUpdate();
////        session.createSQLQuery ("SET FOREIGN_KEY_CHECKS = 1").executeUpdate();
////
////        transaction.commit();
////        result = true;

        return result;
    }

    @Override
    public Collection<Users> find (IDomainObject object)
    {
        Users user = null;
        String query = null;
        NativeQuery result;
        List<Object[]> resultSet;
        initSession();
        Iterator userItr;
        Object[] userObject;
        Collection<Users> users= new ArrayList<Users>();

        if (session != null)
        {
            user = (Users)object;
            query = "SELECT * FROM users WHERE UserName = '"
                  + user.getUserName() + "'";
            try
            {
                result = session.createSQLQuery (query);
                resultSet = (List<Object[]>)result.list();

                if (!resultSet.isEmpty())
                {
                    userItr = resultSet.iterator();

                    do
                    {
                        userObject = (Object[])userItr.next();
                        DateFormat format = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss.SSS", Locale.ENGLISH);
                        Date date;
                        user.setIdUsers (Integer.parseInt (userObject[0].toString()));
                        user.setFirst (userObject[1].toString());
                        user.setLast (userObject[2].toString());
                        user.setUserName (userObject[3].toString());
                        user.setSecretKey (userObject[4].toString());

                        user.setA (Integer.parseInt (userObject[5].toString()));
                        user.setA1 (Integer.parseInt (userObject[6].toString()));
                        user.setA2 (userObject[7].toString());

                        user.setB (Integer.parseInt (userObject[8].toString()));
                        user.setB1 (Integer.parseInt (userObject[9].toString()));
                        user.setB2 (userObject[10].toString());

                        user.setC (Integer.parseInt (userObject[11].toString()));
                        user.setC1 (Integer.parseInt (userObject[12].toString()));
                        user.setC2 (userObject[13].toString());

                        user.setD (Integer.parseInt (userObject[14].toString()));
                        user.setPwReset (Boolean.parseBoolean (userObject[15].toString()));

                        date = format.parse (userObject[16].toString());
                        user.setCreation (date);

                        users.add (user);
                    } while (userItr.hasNext());
                }
                else
                {
                    users = null;
                }
            }
            catch (Exception e)
            {
                users = null;
                Logger.log (UserAccessSvcImpl.class, e);
            }
        }
        else
        {
            Logger.log (UserAccessSvcImpl.class, "UserAccessSvcImpl.find failed with a NULL session.");
        }

        return users;
    }

    @Override
    public boolean persist (Collection<?> object)
    {
        boolean result = true;
        Transaction transaction = null;
        Users newUser;
        Iterator iterator;
        Collection<Users> users;

		if (object != null)//validate
		{
            users = (Collection<Users>) object;
            initSession();

            if (session != null)
            {
                try
                {
                    transaction = session.beginTransaction();
                    iterator = users.iterator();

                    while (iterator.hasNext() && result)
                    {
                        newUser = (Users)iterator.next();
                        session.persist (newUser);
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
                            Logger.log (UserAccessSvcImpl.class, e);
                            transaction.rollback();
                        }
                        catch (Exception ex)
                        {
                            Logger.log (UserAccessSvcImpl.class, "Rollback() failed");
                        }
                    }
                }
            }
            else
            {
                Logger.log (UserAccessSvcImpl.class, "UserAccessSvcImpl.persist failed with a NULL session.");
            }
		}
        else
        {
            Logger.log (UserAccessSvcImpl.class, "UserAccessSvcImpl.persist failed with a NULL object.");
            result = false;
        }

		return result;
    }

    @Override
    public boolean save (Collection<?> object)
    {
        boolean result = true;
        Transaction transaction = null;
        Users newUser;
        Iterator iterator;
        Collection<Users> users;

		if (object != null)//validate
		{
            users = (Collection<Users>) object;
            initSession();

            if (session != null)
            {
                try
                {
                    transaction = session.beginTransaction();
                    iterator = users.iterator();

                    while (iterator.hasNext() && result)
                    {
                        newUser = (Users)iterator.next();
                        session.save (newUser);
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
                            Logger.log (UserAccessSvcImpl.class, e);
                            transaction.rollback();
                        }
                        catch (Exception ex)
                        {
                            Logger.log (UserAccessSvcImpl.class, "Rollback() failed");
                        }
                    }
                }
            }
            else
            {
                Logger.log (UserAccessSvcImpl.class, "UserAccessSvcImpl.save failed with a NULL session.");
                result = false;
            }
		}
        else
        {
            Logger.log (UserAccessSvcImpl.class, "UserAccessSvcImpl.persist failed with a NULL object.");
            result = false;
        }

		return result;
    }

    @Override
    public Users readUser (Integer idusers)
    {
        return (Users)find (new Users (idusers));
    }

    @Override
    public Collection<Users> readUsers()
    {
        Collection<Users> users = new ArrayList<Users>();
        org.hibernate.query.Query query;
        Iterator<Users> iterator;
        initSession();

        if (session != null)
        {
            try
            {
                query = session.createQuery ("from users");
                iterator = (Iterator<Users>)query.iterate();

                while (iterator.hasNext())
                {
                    users.add (iterator.next());
                }
            }
            catch (Exception e)
            {
                Logger.log (UserAccessSvcImpl.class, e);
            }
        }
        else
        {
            Logger.log (UserAccessSvcImpl.class, "UserAccessSvcImpl.readUsers failed with a NULL session.");
        }

        return users;
    }

    @Override
    public boolean removeUser (IUsers user)
    {
        return true;
    }

    @Override
    public boolean updateUser (IUsers user, boolean isUpdate)
    {
        boolean result;
        Collection<Users> users = new ArrayList<Users>();

        if (isUpdate)
        {
            // TODO: complete update code later
            result = true;
        }
        else
        {
            users.add ((Users)user);
            result = merge (users);
        }

        return result;
    }

    @Override
    public boolean merge (Collection<?> object)
    {
        boolean result = true;
        Transaction transaction;
        Users newUser;
        Iterator iterator;
        Collection<Users> users;

        if (object != null)//validate
        {
            users = (Collection<Users>) object;
            initSession();

            if (session != null)
            {
                try
                {
                    transaction = session.beginTransaction();
                    iterator = users.iterator();

                    while (iterator.hasNext() && result)
                    {
                        newUser = (Users)iterator.next();
                        session.merge (newUser);
                    }

                    transaction.commit();
                }
                catch (Exception e)
                {
                    Logger.log (UserAccessSvcImpl.class, e);
                }
            }
            else
            {
                Logger.log (UserAccessSvcImpl.class, "UserAccessSvcImpl.merge failed with a NULL session.");
                result = false;
            }
        }
        else
        {
            Logger.log (UserAccessSvcImpl.class, "UserAccessSvcImpl.merge failed with a NULL object.");
            result = false;
        }

        return result;
    }
}
