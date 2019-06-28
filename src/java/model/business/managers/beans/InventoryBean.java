/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.business.managers.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import model.business.interfaces.IAuthenticate;
import model.business.interfaces.IInventoryManager;
import model.domain.interfaces.IImages;
import model.domain.interfaces.IItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Paul G. Allen <pgallen@gmail.com>
 */
@Component
@ManagedBean
@SessionScoped
public class InventoryBean implements Serializable
{
    private Collection<IItems> itemList = new ArrayList<IItems>();

    @Autowired
    private IAuthenticate authenticate;

    @Autowired
    private IInventoryManager manager;

    public void setAuthenticate (IAuthenticate authenticate)
    {
        this.authenticate = authenticate;
    }

    public void setManager (IInventoryManager manager)
    {
        this.manager = manager;
    }

    public String findProduct()
    {
        String resultPage = "catalog.xhtml";

        itemList = manager.findProducts ("item");

        return resultPage;
    }

    public IImages getImage()
    {
        return manager.getImage();
    }

    /**
     * @return the pageTitle
     */
    public String getPageTitle()
    {
        return manager.getPageTitle();
    }

    /**
     * @param pageTitle the pageTitle to set
     */
    public void setPageTitle (String pageTitle)
    {
        manager.setPageTitle (pageTitle);
    }

    /**
     * @return the partNo
     */
    public String getPartNo()
    {
        return manager.getPartNo();
    }

    /**
     * @param partNo the partNo to set
     */
    public void setPartNo (String partNo)
    {
        manager.setPartNo (partNo);
    }

    /**
     * @return the desc
     */
    public String getDesc()
    {
        return manager.getDesc();
    }

    /**
     * @param desc the desc to set
     */
    public void setDesc (String desc)
    {
        manager.setDesc (desc);
    }

    /**
     * @return the upc
     */
    public String getUpc()
    {
        return manager.getUpc();
    }

    /**
     * @param upc the upc to set
     */
    public void setUpc (String upc)
    {
        manager.setUpc (upc);
    }

    /**
     * @return the pageLimit
     */
    public String getPageLimit()
    {
        return manager.getPageLimit();
    }

    /**
     * @param pageLimit the pageLimit to set
     */
    public void setPageLimit (String pageLimit)
    {
        manager.setPageLimit (pageLimit);
    }

    /**
     * @return the sourcePage
     */
    public String getSourcePage()
    {
        return manager.getSourcePage();
    }

    /**
     * @param sourcePage the sourcePage to set
     */
    public void setSourcePage (String sourcePage)
    {
        manager.setSourcePage (sourcePage);
    }

    /**
     * @return the itemList
     */
    public Collection<IItems> getItemList()
    {
        return itemList;
    }

    /**
     * @param itemList the itemList to set
     */
    public void setItemList (Collection<IItems> itemList)
    {
        this.itemList = itemList;
    }

    /**
     * @return the orderBy
     */
    public String getOrderBy()
    {
        return manager.getOrderBy();
    }

    /**
     * @param orderBy the orderBy to set
     */
    public void setOrderBy (String orderBy)
    {
        manager.setOrderBy (orderBy);
    }
}
