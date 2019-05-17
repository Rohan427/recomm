/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.business.interfaces;

import model.domain.interfaces.IUsers;

/**
 *
 * @author Paul G. Allen <pgallen@gmail.com>
 */
public interface IAuthenticate
{
    /**
     * @return the cfmPassword
     */
    String getCfmPassword();

    /**
     * @param cfmPassword the cfmPassword to set
     */
    void setCfmPassword (String cfmPassword);

    /**
     * @return the email
     */
    String getEmail();

    /**
     * @param email the email to set
     */
    void setEmail (String email);

    /**
     * @return the fname
     */
    String getFname ();

    /**
     * @param fname the fname to set
     */
    void setFname (String fname);

    /**
     * @return the lname
     */
    String getLname();

    /**
     * @param lname the lname to set
     */
    void setLname (String lname);

    /**
     * @return the login
     */
    String getLogin();

    /**
     * @param login the login to set
     */
    void setLogin (String login);

    /**
     * @return the password
     */
    String getPassword();

    /**
     * @param password the password to set
     */
    void setPassword (String password);

    String login();

    String logout();

    String register();

    String resetPassword();

    String sendLogin();
    
    IUsers getUserInfo();
    
    void setUserInfo (IUsers userInfo);
}
