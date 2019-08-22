package com.recomm.business.managers.impl;

import com.rlsecurity.facade.RLSecurity;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import com.recomm.business.managers.interfaces.IAuthenticate;
import com.recomm.model.domain.users.interfaces.IUsers;
import com.recomm.model.domain.users.entity.Users;
import com.recomm.model.domain.users.service.interfaces.IUserAccessSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Paul G. Allen <pgallen@gmail.com>
 */
@Service
public class AuthenticateManager implements IAuthenticate
{
    private IUsers userInfo;

    private String login;

    private String fname;

    private String lname;

    private String email;

    private String password;

    private String cfmPassword;

    private HttpSession session;

    @Autowired
    private InventoryManager manager;

    @Autowired
    private IUserAccessSvc userSvc;

    String resultPage;

    /**
     * Creates a new instance of Authenticate
     */
    public AuthenticateManager()
    {
    }

    public void setUserSvc (IUserAccessSvc userSvc)
    {
        this.userSvc = userSvc;
    }

    @Override
    public void setUserInfo (IUsers userInfo)
    {
        this.userInfo = userInfo;
    }

    @Override
    public IUsers getUserInfo()
    {
        return this.userInfo;
    }

    /**
     * @return the login
     */
    @Override
    public String getLogin()
    {
        return login;
    }

    /**
     * @param login the login to set
     */
    @Override
    public void setLogin (String login)
    {
        this.login = login;
    }

    /**
     * @return the fname
     */
    @Override
    public String getFname()
    {
        return fname;
    }

    /**
     * @param fname the fname to set
     */
    @Override
    public void setFname (String fname)
    {
        this.fname = fname;
    }

    /**
     * @return the lname
     */
    @Override
    public String getLname()
    {
        return lname;
    }

    /**
     * @param lname the lname to set
     */
    @Override
    public void setLname (String lname)
    {
        this.lname = lname;
    }

    /**
     * @return the email
     */
    @Override
    public String getEmail()
    {
        return email;
    }

    /**
     * @param email the email to set
     */
    @Override
    public void setEmail (String email)
    {
        this.email = email;
    }

    /**
     * @return the password
     */
    @Override
    public String getPassword()
    {
        return password;
    }

    /**
     * @param password the password to set
     */
    @Override
    public void setPassword (String password)
    {
        this.password = password;
    }

    /**
     * @return the cfmPassword
     */
    @Override
    public String getCfmPassword()
    {
        return cfmPassword;
    }

    /**
     * @param cfmPassword the cfmPassword to set
     */
    @Override
    public void setCfmPassword (String cfmPassword)
    {
        this.cfmPassword = cfmPassword;
    }

    @Override
    public HttpSession getSession()
    {
        return this.session;
    }

    @Override
    public void setSession()
    {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        session = (HttpSession) facesContext.getExternalContext().getSession (false);

        if (session == null)
        {
            //TODO: HAndle the error
        }
        else
        {
            userInfo = (Users)session.getAttribute ("userBean");

            if (userInfo == null)
            {
                userInfo = new Users();
                session.setAttribute ("userBean", userInfo);
                session.setAttribute ("expired", true);
            }
        }
    }

    @Override
    public String login()
    {
        setSession();
        session.removeAttribute ("userBean");
        session.setAttribute ("expired", true);
        userInfo = new Users (login);
        resultPage = "/loginResult.xhtml";
        RLSecurity secureModule = new RLSecurity ("bcrypt");

        Collection<Users> users = (Collection<Users>)manager.find ("user", userInfo);
        Iterator userItr = users.iterator();

        // TODO: Need to update security library and remove use of Strings
        while (userItr.hasNext() && !userInfo.isValid())
        {
            userInfo = (Users)userItr.next();

            if (login.equals (userInfo.getUserName()))
            {
                if (secureModule.verify (userInfo.getA2().getBytes(),
                                         password.getBytes(),
                                         userInfo.getSecretKey().getBytes(),
                                         userInfo.getA(),
                                         userInfo.getB(),
                                         userInfo.getC(),
                                         userInfo.getD()
                                        )
                   )
                {
                    userInfo.setValid (true);
                    password = null;
                    userInfo.setSecretKey(null);
                }
                else
                {
                    userInfo.setValid (false);
                }
            }
            else
            {
                userInfo = new Users();
                session.setAttribute ("expired", false);
            }
        };

        session.setAttribute ("userBean", userInfo);
        return resultPage;
    }

    @Override
    public String logout()
    {
        setSession();
        resultPage = "/logoutResult.xhtml";

        userSvc.close();

        userInfo = new Users();
        session.setAttribute ("userBean", userInfo);
        session.setAttribute ("oldUser", userInfo);
        session.removeAttribute ("wishlist");
        session.removeAttribute ("cartList");

        return resultPage;
    }

    @Override
    public String register()
    {
        setSession();
        session.setAttribute ("expired", true);
        userInfo = new Users (login);
        resultPage = "/regResult.xhtml";
        RLSecurity secureModule = new RLSecurity ("bcrypt");

        Collection<Users> users = (Collection<Users>)manager.find ("user", userInfo);
        Iterator userItr = users.iterator();

        // See if user already in system. If it is, then it's not valid
        while (userItr.hasNext() && !userInfo.isValid())
        {
            userInfo = (Users)userItr.next();

            if (login.equals (userInfo.getUserName()))
            {
                userInfo.setValid (true);
            }
            else
            {
                userInfo.setValid (false);
            }
        }


        // Enter the user
        // TODO: Need to add email (requires Customer object)
        // TODO: Need to update security library and remove use of Strings
        // TODO: Argon2 parameters need to be taylored and moved out of here
        if (!userInfo.isValid())
        {
            if (password.equals (cfmPassword))
            {
                userInfo = new Users (0,
                                      fname,
                                      lname,
                                      login,
                                      524288,
                                      secureModule.genSalt (4),
                                      4,
                                      4,
                                      1024,
                                      Calendar.getInstance().getTime()
                                     );
                userInfo.setSecretKey (secureModule.hash (password.getBytes(),
                                                          userInfo.getA2().getBytes(),
                                                          userInfo.getA(),
                                                          userInfo.getB(),
                                                          userInfo.getC(),
                                                          userInfo.getD()
                                                         )
                                      );
                password = null;
                cfmPassword = null;
                userInfo.setValid (true);
                session.setAttribute ("expired", false);

                if (manager.update (userInfo, "create"))
                {
                    userInfo.setValid (true);
                    session.setAttribute ("expired", false);
                }
                else
                {
                    userInfo = new Users();
                }
            }
            else
            {
                userInfo.setValid (false);
            }
        }
        else
        {
            userInfo = new Users();
        }

        session.setAttribute ("userBean", userInfo);
        return resultPage;
    }

    @Override
    public String sendLogin()
    {
        setSession();
        session.removeAttribute ("userBean");
        session.setAttribute ("expired", true);
        userInfo = new Users (login);
        resultPage = "/view/auth/loginsent.xhtml";

        return resultPage;
    }

    @Override
    public String resetPassword()
    {
        setSession();
        session.removeAttribute ("userBean");
        session.setAttribute ("expired", true);
        userInfo = new Users (login);
        resultPage = "/view/auth/passwordReset.xhtml";

        return resultPage;
    }
}
