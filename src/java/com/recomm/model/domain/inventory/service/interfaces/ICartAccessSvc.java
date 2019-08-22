/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recomm.model.domain.inventory.service.interfaces;

import com.recomm.model.service.interfaces.Ipersist;
import java.util.List;
import com.recomm.model.domain.inventory.interfaces.ICart;
import java.util.Collection;

/**
 *
 * @author Paul G. Allen <pgallen@gmail.com>
 */
public interface ICartAccessSvc extends Ipersist
{
    Collection<ICart> readCarts();

    boolean updateCart (ICart cart, boolean isUpdate);

    ICart readCart (Integer idCart);

    boolean removeCart (ICart cart);
}
