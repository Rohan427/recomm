/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.business.interfaces;

import javax.servlet.http.HttpSession;
import model.domain.interfaces.IUsers;

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
}
