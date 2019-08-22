/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recomm.model.domain.users.util;

import com.recomm.model.domain.interfaces.ISearchParms;

/**
 *
 * @author pgallen
 */

public class CustomerSearchParams implements ISearchParms
{
    private String id = "%%";
    private String email = "%%";
    private String firstName = "%%";
    private String lastName = "%%";
    private String idUser = "%%";
    private String address1 = "%%";
    private String address2 = "%%";
    private String city = "%%";
    private String state = "%%";
    private int limit = 25;
    private int lastIndex = 0;
    private String orderBy = "partNo";
    private boolean asc = true;

    public CustomerSearchParams()
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
        CustomerSearchParams other = (CustomerSearchParams)object;

        if (other != null)
        {
            result = true;

            if (this.email == null ? other.getEmail() != null : !this.email.equals (other.getEmail()))
            {
                result = false;
            }

            if (this.firstName == null ? other.getFirstName()!= null : !this.firstName.equals (other.getFirstName()))
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
     * @return the email
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail (String email)
    {
        this.email = email;
    }

    /**
     * @return the firstName
     */
    public String getFirstName()
    {
        return firstName;
    }

    /**
     * @param nmanName the firstName to set
     */
    public void setFirstName (String firstName)
    {
        this.firstName = firstName;
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

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the idUser
     */
    public String getIdUser() {
        return idUser;
    }

    /**
     * @param idUser the idUser to set
     */
    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }
}
