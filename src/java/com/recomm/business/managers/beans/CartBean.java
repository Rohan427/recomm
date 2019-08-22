/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recomm.business.managers.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import com.recomm.business.managers.impl.CartManager;
import com.recomm.business.managers.impl.WishManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Paul G. Allen <pgallen@gmail.com>
 */
@Component
@ManagedBean
@SessionScoped
public class CartBean
{
    @Autowired
    private CartManager cartManager;

    @Autowired
    private WishManager wishManager;

    /**
     * @return the pageTitle
     */
    public String getPageTitle()
    {
        return cartManager.getPageTitle();
    }

    /**
     * @param pageTitle the pageTitle to set
     */
    public void setPageTitle (String pageTitle)
    {
        cartManager.setPageTitle (pageTitle);
    }

    /**
     * @return the itemId
     */
    public String getItemId()
    {
        return cartManager.getItemId();
    }

    /**
     * @param itemId the itemId to set
     */
    public void setItemId (String itemId)
    {
        cartManager.setItemId (itemId);
    }

    public void cartAdd()
    {
        cartManager.setSession();
        cartManager.cartAdd();
    }

    public void wishlistAdd()
    {
        wishManager.setSession();
        wishManager.wishListAdd();
    }

    public void viewCart()
    {
        cartManager.setSession();
        cartManager.viewCart();
    }

    public void viewList()
    {
        wishManager.setSession();
        wishManager.viewList();
    }

    public void cartRemove()
    {
        cartManager.setSession();
        cartManager.cartRemove();
    }

    public void wishRemove()
    {
        wishManager.setSession();
        wishManager.wishlistRemove();
    }
}
