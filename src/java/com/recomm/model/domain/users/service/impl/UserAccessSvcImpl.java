/*
 * Copyright (c) 2018 Random Logic Consulting and Paul G. Allen.
 * All Rights Reserved.
 */
package com.recomm.model.domain.users.service.impl;

import com.recomm.model.domain.interfaces.IDomainObject;
import com.recomm.model.domain.users.entity.Users;
import com.recomm.model.domain.users.service.interfaces.IUserAccessSvc;
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
import com.recomm.model.domain.users.interfaces.IUsers;
import com.recomm.model.service.impl.HibernateSvc;
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
    public Collection<Users> find (IDomainObject object)
    {
        Users user = null;
        String query = null;
        NativeQuery result;
        List<Object[]> resultSet;
        initUserSession();
        Iterator userItr;
        Object[] userObject;
        Collection<Users> users = new ArrayList<Users>();

        if (usersession != null)
        {
            user = (Users)object;
            query = "SELECT * FROM users WHERE UserName = '"
                  + user.getUserName() + "'";
            try
            {
                result = usersession.createSQLQuery (query);
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

                        user.setValid(true);

                        users.add (user);
                    } while (userItr.hasNext());
                }
                else
                {
                    users = new ArrayList<Users>();
                }
            }
            catch (Exception e)
            {
                users = new ArrayList<Users>();
                Logger.log (UserAccessSvcImpl.class, e);
                close();
            }
        }
        else
        {
            Logger.log (UserAccessSvcImpl.class, "UserAccessSvcImpl.find failed with a NULL session.");
            close();
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
            initUserSession();

            if (usersession != null)
            {
                try
                {
                    transaction = usersession.beginTransaction();
                    iterator = users.iterator();

                    while (iterator.hasNext() && result)
                    {
                        newUser = (Users)iterator.next();
                        usersession.persist (newUser);
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
                            close();
                        }
                        catch (Exception ex)
                        {
                            Logger.log (UserAccessSvcImpl.class, "Rollback() failed");
                            close();
                        }
                    }
                }
            }
            else
            {
                Logger.log (UserAccessSvcImpl.class, "UserAccessSvcImpl.persist failed with a NULL session.");
                result = false;
                close();
            }
		}
        else
        {
            Logger.log (UserAccessSvcImpl.class, "UserAccessSvcImpl.persist failed with a NULL object.");
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
        Users newUser;
        Iterator iterator;
        Collection<Users> users;

		if (object != null)//validate
		{
            users = (Collection<Users>) object;
            initUserSession();

            if (usersession != null)
            {
                try
                {
                    transaction = usersession.beginTransaction();
                    iterator = users.iterator();

                    while (iterator.hasNext() && result)
                    {
                        newUser = (Users)iterator.next();
                        usersession.save (newUser);
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
                            close();
                        }
                        catch (Exception ex)
                        {
                            Logger.log (UserAccessSvcImpl.class, "Rollback() failed");
                            close();
                        }
                    }
                }
            }
            else
            {
                Logger.log (UserAccessSvcImpl.class, "UserAccessSvcImpl.save failed with a NULL session.");
                result = false;
                close();
            }
		}
        else
        {
            Logger.log (UserAccessSvcImpl.class, "UserAccessSvcImpl.persist failed with a NULL object.");
            result = false;
            close();
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
        initUserSession();

        if (usersession != null)
        {
            try
            {
                query = usersession.createQuery ("from users");
                iterator = (Iterator<Users>)query.iterate();

                while (iterator.hasNext())
                {
                    users.add (iterator.next());
                }
            }
            catch (Exception e)
            {
                Logger.log (UserAccessSvcImpl.class, e);
                close();
            }
        }
        else
        {
            Logger.log (UserAccessSvcImpl.class, "UserAccessSvcImpl.readUsers failed with a NULL session.");
            close();
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
        boolean result = true;
        Collection<IUsers> users = new ArrayList<IUsers>();
         Collection<IUsers> newList = new ArrayList<IUsers>();

        if (isUpdate)
        {
            // TODO: complete update code later
            result = true;
        }
        else
        {
            users.add ((IUsers)user);
            newList = (Collection<IUsers>)merge (users);
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
        Transaction transaction = null;
        IUsers newUser = null;
        Iterator iterator;
        Collection<IUsers> users;
        Collection<IUsers> newList = new ArrayList<IUsers>();

        if (object != null)//validate
        {
            users = (Collection<IUsers>) object;
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
                    Logger.log (UserAccessSvcImpl.class, "Transaction failed");
                    invsession.flush();
                    invsession.close();
                }

                try
                {
                    iterator = users.iterator();

                    do
                    {
                        newUser = (IUsers)iterator.next();
                        newUser = (IUsers)usersession.merge (newUser);
                        newList.add (newUser);
                    } while (iterator.hasNext() && (newUser != null));

                    transaction.commit();
                    close();
                }
                catch (Exception e)
                {
                    Logger.log (UserAccessSvcImpl.class, e);
                    newList = new ArrayList<IUsers>();
                    close();
                }
            }
            else
            {
                Logger.log (UserAccessSvcImpl.class, "UserAccessSvcImpl.merge failed with a NULL session.");
                newList = new ArrayList<IUsers>();
                close();
            }
        }
        else
        {
            Logger.log (UserAccessSvcImpl.class, "UserAccessSvcImpl.merge failed with a NULL object.");
            newList = new ArrayList<IUsers>();
            close();
        }

        return newList;
    }

    @Override
    public Collection<?> search(ISearchParms searchParms)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void close()
    {
        if (usersession !=null)
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
}
