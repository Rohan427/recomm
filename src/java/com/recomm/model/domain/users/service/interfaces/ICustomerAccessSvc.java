/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recomm.model.domain.users.service.interfaces;

import com.recomm.model.domain.interfaces.ISearchParms;
import com.recomm.model.domain.users.entity.Customer;
import com.recomm.model.domain.users.interfaces.ICustomer;
import com.recomm.model.service.interfaces.Ipersist;
import java.util.Collection;

/**
 *
 * @author Paul G. Allen <pgallen@gmail.com>
 */
public interface ICustomerAccessSvc  extends Ipersist
{
    Customer readCustomer(Integer idCustomer);

    Collection<Customer> readCustomers();

    boolean removeCustomer(ICustomer customer);

    Collection<?> search(ISearchParms searchParms);

    boolean updateCustomer(ICustomer customer, boolean isUpdate);
}
