/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.business.managers.beans;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.business.interfaces.IAuthenticate;
import model.domain.interfaces.IUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Paul G. Allen <pgallen@gmail.com>
 */
@Component
@ManagedBean
@SessionScoped
public class LoginBean implements Serializable
{
    private static final long serialVersionUID = 3614312547399778619L;

    @Autowired
    private IAuthenticate authenticate;

    public void setAuthenticate (IAuthenticate authenticate)
    {
        this.authenticate = authenticate;
    }

    /**
     * @return the login
     */
    public String getLogin()
    {
        return authenticate.getLogin();
    }

    /**
     * @param login the login to set
     */
    public void setLogin (String login)
    {
        authenticate.setLogin (login);
    }

    /**
     * @return the fname
     */
    public String getFname()
    {
        return authenticate.getFname ();
    }

    /**
     * @param fname the fname to set
     */
    public void setFname (String fname)
    {
        authenticate.setFname (fname);
    }

    /**
     * @return the lname
     */
    public String getLname()
    {
        return authenticate.getLname();
    }

    /**
     * @param lname the lname to set
     */
    public void setLname (String lname)
    {
        authenticate.setLname (lname);
    }

    /**
     * @return the email
     */
    public String getEmail()
    {
        return authenticate.getEmail();
    }

    /**
     * @param email the email to set
     */
    public void setEmail (String email)
    {
        authenticate.setEmail (email);
    }

    /**
     * @return the password
     */
    public String getPassword()
    {
        return authenticate.getCfmPassword();
    }

    /**
     * @param password the password to set
     */
    public void setPassword (String password)
    {
        authenticate.setPassword (password);
    }

    /**
     * @return the cfmPassword
     */
    public String getCfmPassword()
    {
        return authenticate.getCfmPassword();
    }

    /**
     * @param cfmPassword the cfmPassword to set
     */
    public void setCfmPassword (String cfmPassword)
    {
        authenticate.setCfmPassword (cfmPassword);
    }

    public IUsers getUSerInfo()
    {
        return authenticate.getUserInfo();
    }

    public void setUserInfo (IUsers userInfo)
    {
        authenticate.setUserInfo (userInfo);
    }

    public String login()
    {
        return authenticate.login();
    }

    public String logout()
    {
        return authenticate.logout();
    }

    public String register()
    {

        return authenticate.register();
    }

    public String sendLogin()
    {
        return authenticate.sendLogin();
    }

    public String resetPassword()
    {
        return authenticate.resetPassword();
    }

    public boolean validate()
    {
        return true;
    }
}
