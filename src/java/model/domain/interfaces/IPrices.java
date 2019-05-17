/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.domain.interfaces;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author pgallen
 */
public abstract class IPrices implements IDomainObject
{

    /**
     *
     */
    public IPrices ()
    {
    }

    /**
     *
     * @return
     */
    public abstract Integer getIdPrices();

    /**
     *
     * @param idPrices
     */
    public abstract void setIdPrices (Integer idPrices);

    /**
     *
     * @return
     */
    public abstract Date getCreation();

    /**
     *
     * @param creation
     */
    public abstract void setCreation (Date creation);

    /**
     *
     * @return
     */
    public abstract BigDecimal getPrice();

    /**
     *
     * @param price
     */
    public abstract void setPrice (BigDecimal price);
}
