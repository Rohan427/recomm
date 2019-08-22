/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recomm.model.domain.inventory.util;

import com.recomm.model.domain.interfaces.ISearchParms;

/**
 *
 * @author pgallen
 */

public class ItemSearchParams implements ISearchParms
{
    private String id = "%%";
    private String partNo = "%%";
    private String name = "%%";
    private String desc = "%%";
    private String mfg = "%%";
    private String upc = "%%";
    private String cat = "%%";
    private int limit = 25;
    private int lastIndex = 0;
    private String orderBy = "partNo";
    private boolean asc = true;

    public ItemSearchParams()
    {

    }

    public String getOrderBy()
    {
        return orderBy;
    }

    public void setOrderBy (String orderBy)
    {
        this.orderBy = orderBy;
    }

    public boolean isAsc()
    {
        return asc;
    }

    public void setAsc (boolean asc)
    {
        this.asc = asc;
    }

    public String getId()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getPartNo()
    {
        return partNo;
    }

    public void setPartNo (String partNo)
    {
        this.partNo = partNo;
    }

    public String getName()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getDesc()
    {
        return desc;
    }

    public void setDesc (String desc)
    {
        this.desc = desc;
    }

    public String getMfg()
    {
        return mfg;
    }

    public void setMfg (String mfg)
    {
        this.mfg = mfg;
    }

    public String getUpc()
    {
        return upc;
    }

    public void setUpc (String upc)
    {
        this.upc = upc;
    }

    public String getCat()
    {
        return cat;
    }

    public void setCat (String cat)
    {
        this.cat = cat;
    }

    public int getLimit()
    {
        return limit;
    }

    public void setLimit (int limit)
    {
        this.limit = limit;
    }

    public void setLastIndex (int lastIndex)
    {
        this.lastIndex = lastIndex;
    }

    public int getLastIndex()
    {
        return lastIndex;
    }

    @Override
    public boolean equals (Object object)
    {
        boolean result = false;
        ItemSearchParams other = (ItemSearchParams)object;

        if (other != null)
        {
            result = true;

            if (this.cat == null ? other.getCat() != null : !this.cat.equals (other.getCat()))
            {
                result = false;
            }

            if (this.desc == null ? other.getDesc() != null : !this.desc.equals (other.getDesc()))
            {
                result = false;
            }

            if (this.partNo == null ? other.getPartNo() != null : !this.partNo.equals (other.getPartNo()))
            {
                result = false;
            }

            if (this.name == null ? other.getName() != null : !this.name.equals (other.getName()))
            {
                result = false;
            }

            if (this.mfg == null ? other.getMfg() != null : !this.mfg.equals (other.getMfg()))
            {
                result = false;
            }

            if (this.id == null ? other.getId() != null : !this.id.equals (other.getId()))
            {
                result = false;
            }

            if (this.upc == null ? other.getUpc() != null : !this.upc.equals (other.getUpc()))
            {
                result = false;
            }
        }

        return result;
    }
}
