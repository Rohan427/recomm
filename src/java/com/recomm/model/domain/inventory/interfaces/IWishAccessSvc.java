/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recomm.model.domain.inventory.interfaces;

import com.recomm.model.domain.interfaces.IWishlist;
import com.recomm.model.service.interfaces.Ipersist;
import java.util.Collection;

/**
 *
 * @author Paul G. Allen <pgallen@gmail.com>
 */
public interface IWishAccessSvc extends Ipersist
{
    /**
     *
     * @param idWishlist
     * @return
     */
    IWishlist readWishlist(Integer idWishlist);

    Collection<IWishlist> readWishlists();

    /**
     *
     * @param wishlist
     * @return
     */
    boolean removeWishlist(IWishlist wishlist);

    /**
     *
     * @param cart
     * @param isUpdate
     * @return
     */
    boolean updateWishlist(IWishlist cart, boolean isUpdate);
}
