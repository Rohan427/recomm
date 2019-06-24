/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service.impl;

import model.service.interfaces.ICartAccessSvc;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import model.domain.interfaces.ICart;
import model.domain.interfaces.IDomainObject;
import model.domain.interfaces.ISearchParms;
import model.service.dao.HibernateSvc;
import org.springframework.stereotype.Service;

/**
 *
 * @author Paul G. Allen <pgallen@gmail.com>
 */
@Service
public class CartAccessSvcImpl extends HibernateSvc implements ICartAccessSvc, Serializable
{
    @Override
    public boolean persist (Collection<?> object)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<?> find (IDomainObject object)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete (String type)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean save (Collection<?> object)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean merge (Collection<?> object)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<?> search (ISearchParms searchParms)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ICart> findCartByCustomer (int custID)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ICart findCartByCustomerAndItem (int custID, int itemId)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
