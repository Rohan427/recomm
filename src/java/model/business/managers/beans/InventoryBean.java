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
import model.business.managers.InventoryManager;
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
    @ManagedProperty ("#{param.pageTitle}")
    private String pageTitle = "Catalog";

    private String partNo;

    private String desc;

    private String upc;

    private String pageLimit;

    @ManagedProperty ("#{param.sourcePage}")
    private String sourcePage = "catalog.xhtml";

    @ManagedProperty ("#{param.orderBy}")
    private String orderBy;

    private Collection<IItems> itemList = new ArrayList<IItems>();

    @Autowired
    private IAuthenticate authenticate;

    @Autowired
    private InventoryManager manager;

    public String findProduct()
    {
        String resultPage = "catalog.xhtml";
        authenticate.setSession();

        manager.setDesc (desc);
        manager.setOrderBy (orderBy);
        manager.setPageLimit (pageLimit);
        manager.setPartNo (partNo);
        manager.setSourcePage (sourcePage);
        manager.setUpc (upc);
        manager.setSession (authenticate.getSession());
        itemList = manager.findProducts ("item");
        sourcePage = manager.getSourcePage();

        return resultPage;
    }

    /**
     * @return the pageTitle
     */
    public String getPageTitle()
    {
        return pageTitle;
    }

    /**
     * @param pageTitle the pageTitle to set
     */
    public void setPageTitle (String pageTitle)
    {
        this.pageTitle = pageTitle;
    }

    /**
     * @return the partNo
     */
    public String getPartNo()
    {
        return partNo;
    }

    /**
     * @param partNo the partNo to set
     */
    public void setPartNo (String partNo)
    {
        this.partNo = partNo;
    }

    /**
     * @return the desc
     */
    public String getDesc()
    {
        return desc;
    }

    /**
     * @param desc the desc to set
     */
    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    /**
     * @return the upc
     */
    public String getUpc()
    {
        return upc;
    }

    /**
     * @param upc the upc to set
     */
    public void setUpc(String upc)
    {
        this.upc = upc;
    }

    /**
     * @return the pageLimit
     */
    public String getPageLimit()
    {
        return pageLimit;
    }

    /**
     * @param pageLimit the pageLimit to set
     */
    public void setPageLimit(String pageLimit)
    {
        this.pageLimit = pageLimit;
    }

    /**
     * @return the sourcePage
     */
    public String getSourcePage()
    {
        return sourcePage;
    }

    /**
     * @param sourcePage the sourcePage to set
     */
    public void setSourcePage(String sourcePage)
    {
        this.sourcePage = sourcePage;
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
        return orderBy;
    }

    /**
     * @param orderBy the orderBy to set
     */
    public void setOrderBy (String orderBy)
    {
        this.orderBy = orderBy;
    }
}
