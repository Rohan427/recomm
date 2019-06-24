/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service.interfaces;

import java.util.List;
import model.domain.interfaces.ICart;

/**
 *
 * @author Paul G. Allen <pgallen@gmail.com>
 */
public interface ICartAccessSvc extends Ipersist
{

    public List<ICart> findCartByCustomer(int custID);

    public ICart findCartByCustomerAndItem(int custID, int itemId);

}
