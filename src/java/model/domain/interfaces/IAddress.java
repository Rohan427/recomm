package model.domain.interfaces;

import java.util.Date;

/**
 *
 * @author pgallen
 */
public abstract class IAddress implements IDomainObject
{

    /**
     *
     */
    public IAddress ()
    {
    }

    /**
     *
     * @return
     */
    public abstract Integer getIdAddress();

    /**
     *
     * @param idAddress
     */
    public abstract void setIdAddress (Integer idAddress);

    /**
     *
     * @return
     */
    public abstract String getAddress1();

    /**
     *
     * @param address1
     */
    public abstract void setAddress1 (String address1);

    /**
     *
     * @return
     */
    public abstract String getAddress2();

    /**
     *
     * @param address2
     */
    public abstract void setAddress2 (String address2);

    /**
     *
     * @return
     */
    public abstract String getCity();

    /**
     *
     * @param city
     */
    public abstract void setCity (String city);

    /**
     *
     * @return
     */
    public abstract String getState();

    /**
     *
     * @param state
     */
    public abstract void setState (String state);

    /**
     *
     * @return
     */
    public abstract String getCountry();

    /**
     *
     * @param country
     */
    public abstract void setCountry (String country);

    /**
     *
     * @return
     */
    public abstract String getZipCode();

    /**
     *
     * @param zipCode
     */
    public abstract void setZipCode (String zipCode);

    /**
     *
     * @return
     */
    public abstract String getPhoine1();

    /**
     *
     * @param phone1
     */
    public abstract void setPhoine1 (String phone1);

    /**
     *
     * @return
     */
    public abstract String getPhone2();

    /**
     *
     * @param phone2
     */
    public abstract void setPhone2 (String phone2);

    /**
     *
     * @return
     */
    public abstract String getPhone3();

    /**
     *
     * @param phone3
     */
    public abstract void setPhone3 (String phone3);
	
    /**
     *
     * @return
     */
    public abstract Date getCreated();

    /**
     *
     * @param created
     */
    public abstract void setCreated (Date created);
	
    /**
     *
     * @return
     */
    public abstract boolean validate ();
}