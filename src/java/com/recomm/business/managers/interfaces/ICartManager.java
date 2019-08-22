/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recomm.business.managers.interfaces;

import javax.servlet.http.HttpSession;
import com.recomm.model.domain.users.interfaces.IUsers;

/**
 *
 * @author Paul G. Allen <pgallen@gmail.com>
 */
public interface ICartManager extends IDAOManager
{
    HttpSession getSession();

    IUsers getUserInfo();

    void setSession();

    void setUserInfo (IUsers userInfo);

    void cartAdd();

    void guestCartAdd();

    void cartUpdate();

    void guestCartUpdate();

    void guestCartRemove();

    void cartRemove();
}
