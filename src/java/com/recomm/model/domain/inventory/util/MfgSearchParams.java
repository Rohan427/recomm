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

public class MfgSearchParams implements ISearchParms
{
    private String id = "%%";
    private String mfgKey = "%%";
    private String manName = "%%";
    private String address1 = "%%";
    private String address2 = "%%";
    private String city = "%%";
    private String state = "%%";
    private int limit = 25;
    private int lastIndex = 0;
    private String orderBy = "partNo";
    private boolean asc = true;

    public MfgSearchParams()
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
        MfgSearchParams other = (MfgSearchParams)object;

        if (other != null)
        {
            result = true;

            if (this.mfgKey == null ? other.getMfgKey() != null : !this.mfgKey.equals (other.getMfgKey()))
            {
                result = false;
            }

            if (this.manName == null ? other.getManName()!= null : !this.manName.equals (other.getManName()))
            {
                result = false;
            }

            if (this.address1 == null ? other.getAddress1()!= null : !this.address1.equals (other.getAddress1()))
            {
                result = false;
            }

            if (this.address2 == null ? other.getAddress2()!= null : !this.address2.equals (other.getAddress2()))
            {
                result = false;
            }

            if (this.city == null ? other.getCity()!= null : !this.city.equals (other.getCity()))
            {
                result = false;
            }

            if (this.id == null ? other.getId() != null : !this.id.equals (other.getId()))
            {
                result = false;
            }

            if (this.state == null ? other.getState()!= null : !this.state.equals (other.getState()))
            {
                result = false;
            }
        }

        return result;
    }

    /**
     * @return the mfgKey
     */
    public String getMfgKey()
    {
        return mfgKey;
    }

    /**
     * @param mfgKey the mfgKey to set
     */
    public void setMfgKey (String mfgKey)
    {
        this.mfgKey = mfgKey;
    }

    /**
     * @return the manName
     */
    public String getManName()
    {
        return manName;
    }

    /**
     * @param nmanName the manName to set
     */
    public void setManName (String manName)
    {
        this.manName = manName;
    }

    /**
     * @return the address1
     */
    public String getAddress1()
    {
        return address1;
    }

    /**
     * @param address1 the address1 to set
     */
    public void setAddress1 (String address1)
    {
        this.address1 = address1;
    }

    /**
     * @return the address2
     */
    public String getAddress2()
    {
        return address2;
    }

    /**
     * @param address2 the address2 to set
     */
    public void setAddress2 (String address2)
    {
        this.address2 = address2;
    }

    /**
     * @return the city
     */
    public String getCity()
    {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity (String city)
    {
        this.city = city;
    }

    /**
     * @return the state
     */
    public String getState()
    {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState (String state)
    {
        this.state = state;
    }
}
