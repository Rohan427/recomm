/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service.interfaces;


import java.util.Collection;
import model.domain.interfaces.IUsers;
import model.domain.users.Users;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Paul G. Allen <pgallen@gmail.com>
 */
public interface IUserAccessSvc extends Ipersist
{
    /**
     *
     * @param image
     * @param isUpdate
     * @return
     */
    public boolean updateUser (IUsers user, boolean isUpdate);
	
    /**
     *
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
     * @param image
     * @return
     */
    public boolean removeUser (IUsers user);
}
