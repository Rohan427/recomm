/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service.interfaces;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Paul G. Allen <pgallen@gmail.com>
 */
public interface IDAOService
{
    public Session getUserSession();

    public Session getInvSession();

    public void loadService(String session);

    abstract SessionFactory getUserFactory();

    abstract SessionFactory getInvFactory();

    public void initUserSession();

    public void initInvSession();
}
