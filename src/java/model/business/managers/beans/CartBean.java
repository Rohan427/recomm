/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.business.managers.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.business.managers.CartManager;
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
    private CartManager manager;

    public void cartAdd()
    {
        manager.setSession();

    }

    public void wishlistAdd()
    {
        
    }
}
