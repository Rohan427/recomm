/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recomm.model.domain.users.service.interfaces;


import com.recomm.model.service.interfaces.Ipersist;
import java.util.Collection;
import com.recomm.model.domain.users.interfaces.IUsers;
import com.recomm.model.domain.users.entity.Users;

/**
 *
 * @author Paul G. Allen <pgallen@gmail.com>
 */
public interface IUserAccessSvc extends Ipersist
{
    /**
     *
     * @param user
     * @param image
     * @param isUpdate
     * @return
     */
    public boolean updateUser (IUsers user, boolean isUpdate);

    /**
     *
     * @param idUser
     * @param idImage
     * @return
     */
    public Users readUser (Integer idUser);

    /**
     *
     * @return
     */
    Collection<Users> readUsers();

    /**
     *
     * @param user
     * @param image
     * @return
     */
    public boolean removeUser (IUsers user);
}
