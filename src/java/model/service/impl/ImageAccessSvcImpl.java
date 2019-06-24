package model.service.impl;

import java.io.Serializable;

import model.domain.inventory.Images;
import model.service.dao.HashedObjectWrapper;
import model.service.interfaces.IImageAccessSvc;
import java.util.ArrayList;
import java.util.Collection;
import model.domain.interfaces.IDomainObject;
import java.util.Iterator;
import model.business.error.Logger;
import model.domain.interfaces.ISearchParms;
import model.service.dao.HibernateSvc;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

/**
 * Image access service
 *
 * @author Paul G. Allen <pgallen@gmail.com>
 */
@Service
public class ImageAccessSvcImpl extends HibernateSvc implements IImageAccessSvc, Serializable
{
    private static final long serialVersionUID = 44L;
	private HashedObjectWrapper hashtable;

    /**
     *
     * @return
     */
    @Override
    public Collection<Images> readImages()
    {
        Collection<Images> images = new ArrayList<Images>();
        Transaction transaction;
        Query query;
        Iterator<Images> iterator;

        if (invsession == null)
        {
            loadService ("inv");
        }
        // else do nothing

        transaction = invsession.beginTransaction();
        query = invsession.createQuery ("from Images");
        iterator = (Iterator<Images>)query.iterate();

        while (iterator.hasNext())
        {
            images.add (iterator.next());
        }

        transaction.commit();

        return images;
    }

    /**
     *
     * @param image
     * @param isUpdate
     * @return
     */
    @Override
	public boolean updateImage (Images image, boolean isUpdate)
	{
		boolean result;
        Collection<Images> images = new ArrayList<Images>();

        if (isUpdate)
        {
            // TODO: complete update code later
            result = true;
        }
        else
        {
            images.add ((Images)image);
            result = merge (images);
        }

        return result;
    }

    /**
     *
     * @param idImage
     * @return
     */
    @Override
	public Images readImage (Integer idImage)
	{
		return (Images)find (new Images (idImage));
	}

    /**
     *
     * @param brand
     * @return
     */
    @Override
	public boolean removeImage (Images brand)
	{
		// TODO Auto-generated method stub
		return true;
	}

    /**
     *
     * @param object
     * @return
     */
    @Override
	public Collection<Images> find (IDomainObject object)
	{
        Images image = (Images)object;
        Iterator userItr;
        Object[] userObject;
        Collection<Images> images = new ArrayList<Images>();

        if (invsession == null)
        {
            loadService ("inv");
        }
        // else do nothing

        object = (Images)invsession.get (Images.class, image.getIdImages());

		return images;
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
        // else do nothing

        transaction = invsession.beginTransaction();
        invsession.createSQLQuery ("SET FOREIGN_KEY_CHECKS = 0").executeUpdate();
        invsession.createSQLQuery ("TRUNCATE Images").executeUpdate();
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
        Images newImage;
        Iterator iterator;
        Collection<Images> images;

		if (object != null)//validate
		{
            images = (Collection<Images>) object;
            initInvSession();

            if (invsession != null)
            {
                try
                {
                    transaction = invsession.beginTransaction();
                    iterator = images.iterator();

                    while (iterator.hasNext() && result)
                    {
                        newImage = (Images)iterator.next();
                        invsession.persist (newImage);
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
                            Logger.log (ImageAccessSvcImpl.class, e);
                            transaction.rollback();
                        }
                        catch (Exception ex)
                        {
                            Logger.log (ImageAccessSvcImpl.class, "Rollback() failed");
                        }
                    }
                }
            }
            else
            {
                Logger.log (ImageAccessSvcImpl.class, "ImageAccessSvcImpl.persist failed with a NULL session.");
            }
		}
        else
        {
            Logger.log (ImageAccessSvcImpl.class, "ImageAccessSvcImpl.persist failed with a NULL object.");
            result = false;
        }

		return result;
    }

    @Override
    public boolean save (Collection<?> object)
    {
        boolean result = true;
        Transaction transaction = null;
        Images newImage;
        Iterator iterator;
        Collection<Images> images;

		if (object != null)//validate
		{
            images = (Collection<Images>) object;
            initInvSession();

            if (invsession != null)
            {
                try
                {
                    transaction = invsession.beginTransaction();
                    iterator = images.iterator();

                    while (iterator.hasNext() && result)
                    {
                        newImage = (Images)iterator.next();
                        invsession.save (newImage);
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
                            Logger.log (ImageAccessSvcImpl.class, e);
                            transaction.rollback();
                        }
                        catch (Exception ex)
                        {
                            Logger.log (ImageAccessSvcImpl.class, "Rollback() failed");
                        }
                    }
                }
            }
            else
            {
                Logger.log (ImageAccessSvcImpl.class, "ImageAccessSvcImpl.save failed with a NULL session.");
                result = false;
            }
		}
        else
        {
            Logger.log (ImageAccessSvcImpl.class, "ImageAccessSvcImpl.persist failed with a NULL object.");
            result = false;
        }

		return result;
    }

    @Override
    public boolean merge (Collection<?> object)
    {
        boolean result = true;
        Transaction transaction;
        Images newImage;
        Iterator iterator;
        Collection<Images> images;

        if (object != null)//validate
        {
            images = (Collection<Images>) object;
            initInvSession();

            if (invsession != null)
            {
                try
                {
                    transaction = invsession.beginTransaction();
                    iterator = images.iterator();

                    while (iterator.hasNext() && result)
                    {
                        newImage = (Images)iterator.next();
                        invsession.merge (newImage);
                    }

                    transaction.commit();
                }
                catch (Exception e)
                {
                    Logger.log (ImageAccessSvcImpl.class, e);
                }
            }
            else
            {
                Logger.log (ImageAccessSvcImpl.class, "ImageAccessSvcImpl.merge failed with a NULL session.");
                result = false;
            }
        }
        else
        {
            Logger.log (ImageAccessSvcImpl.class, "ImageAccessSvcImpl.merge failed with a NULL object.");
            result = false;
        }

        return result;
    }

    @Override
    public Collection<?> search (ISearchParms searchParms)
    {
        throw new UnsupportedOperationException ("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}