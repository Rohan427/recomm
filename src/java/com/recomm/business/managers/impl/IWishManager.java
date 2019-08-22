/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recomm.business.managers.impl;

import com.recomm.business.managers.interfaces.IDAOManager;

/**
 *
 * @author Paul G. Allen <pgallen@gmail.com>
 */
public interface IWishManager  extends IDAOManager
{
    void moveToCart();

    void setSession();

    void viewList();

    void wishListAdd();

    void wishlistRemove();
}
