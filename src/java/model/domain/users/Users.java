/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.domain.users;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import model.domain.interfaces.IAccesList;
import model.domain.interfaces.IBrand;
import model.domain.interfaces.ICustomer;
import model.domain.interfaces.IImages;
import model.domain.interfaces.IItems;
import model.domain.interfaces.IUsers;
import org.hibernate.annotations.Proxy;

/**
 *
 * @author Paul G. Allen <pgallen@gmail.com>
 */
@Entity
@Proxy(lazy=false)
@Table (catalog = "customer", schema = "", uniqueConstraints =
        {
            @UniqueConstraint (columnNames =
            {
                "idUsers"
            })
})
@XmlRootElement
public class Users extends IUsers implements Serializable
{
    private static final long serialVersionUID = 6L;

    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Basic (optional = false)
    @Column (name="idUsers", nullable = false)
    @Id
    private Integer idUsers;

    @Basic(optional = false)
    private String first;

    @Basic(optional = false)
    private String last;

    @Basic(optional = false)
    private String secretKey;


    private Integer a;

    private String a2;


    private Integer b;


    private Integer c;


    private Integer d;

    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creation;

    private String userName;

    @Basic(optional = false)
    private boolean pwReset = false;

    @Transient
    private boolean valid = false;

    public Users()
    {
    }

    public Users (Integer idUsers)
    {
        this.idUsers = idUsers;
    }

    public Users (String userName)
    {
        this.userName = userName;
    }

    public Users (Integer idUsers,
                  String first,
                  String last,
                  String userName,
                  Date creation
                 )
    {
        this.idUsers = idUsers;
        this.first = first;
        this.last = last;
        this.userName = userName;
        this.creation = creation;
    }

    public Users (Integer idUsers,
                  String first,
                  String last,
                  String userName,
                  int a,
                  String a2,
                  int b,
                  int c,
                  int d,
                  Date creation
                 )
    {
        this.idUsers = idUsers;
        this.first = first;
        this.last = last;
        this.userName = userName;
        this.a = a;
        this.a2 = a2;
        this.b = b;
        this.c = c;
        this.d = d;
        this.creation = creation;
    }

    @Override
    public String getA2()
    {
        return this.a2;
    }

    @Override
    public void setA2 (String a2)
    {
        this.a2 = a2;
    }

    @Override
    public Collection<IAccesList> getAccessListCollection ()
    {
        throw new UnsupportedOperationException ("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setAccessListCollection (Collection<IAccesList> accessListCollection)
    {
        throw new UnsupportedOperationException ("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<ICustomer> getCustomerCollection ()
    {
        throw new UnsupportedOperationException ("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setCustomerCollection (Collection<ICustomer> customerCollection)
    {
        throw new UnsupportedOperationException ("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer getIdUsers()
    {
        return idUsers;
    }

    @Override
    public void setIdUsers (Integer idUsers)
    {
        this.idUsers = idUsers;
    }

    @Override
    public String getFirst()
    {
        return first;
    }

    @Override
    public void setFirst (String first)
    {
        this.first = first;
    }
    @Override
    public Collection<IImages> getImagesCollection ()
    {
        throw new UnsupportedOperationException ("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setImagesCollection (Collection<IImages> imagesCollection)
    {
        throw new UnsupportedOperationException ("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<IItems> getItemsCollection ()
    {
        throw new UnsupportedOperationException ("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setItemsCollection (Collection<IItems> itemsCollection)
    {
        throw new UnsupportedOperationException ("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getLast()
    {
        return last;
    }

    @Override
    public void setLast (String last)
    {
        this.last = last;
    }
    @Override
    public Collection<IBrand> getManufacturerCollection ()
    {
        throw new UnsupportedOperationException ("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setManufacturerCollection (Collection<IBrand> manufacturerCollection)
    {
        throw new UnsupportedOperationException ("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getUserName()
    {
        return userName;
    }

    @Override
    public void setUserName (String userName)
    {
        this.userName = userName;
    }

    @Override
    public String getSecretKey()
    {
        return secretKey;
    }

    @Override
    public void setSecretKey (String secretKey)
    {
        this.secretKey = secretKey;
    }

    @Override
    public Integer getA()
    {
        return a;
    }

    @Override
    public void setA (Integer a)
    {
        this.a = a;
    }

    @Override
    public Integer getB()
    {
        return b;
    }

    @Override
    public void setB (Integer b)
    {
        this.b = b;
    }

    @Override
    public Integer getC()
    {
        return c;
    }

    @Override
    public void setC (Integer c)
    {
        this.c = c;
    }

    @Override
    public Integer getD()
    {
        return d;
    }

    @Override
    public void setD (Integer d)
    {
        this.d = d;
    }

    @Override
    public Date getCreation()
    {
        return creation;
    }

    @Override
    public void setCreation (Date creation)
    {
        this.creation = creation;
    }

    @XmlTransient
    @Override
    public boolean isValid()
    {
        return valid;
    }

    @Override
    public void setValid (boolean valid)
    {
        this.valid = valid;
    }

    @Override
    public void setPwReset (boolean pwReset)
    {
        this.pwReset = pwReset;
    }

    @Override
    public boolean isPwReset()
    {
        return pwReset;
    }

    @Override
    public void inValidate()
    {
        this.idUsers = null;
        this.first = null;
        this.last = null;
        this.userName = null;
        this.creation = null;
        this.valid = false;
        this.secretKey = null;
    }

    @Override
    public boolean validate()
    {
        if ((this.idUsers == null)
            || (this.first == null)
            || (this.last == null)
            || (this.userName == null)
            || (this.creation == null)
            || (this.valid == false)
            || (this.secretKey == null)
           )
            {
                return false;
            }
            else
            {
                return true;
            }
    }

    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (idUsers != null ? idUsers.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users))
        {
            return false;
        }

        Users other = (Users) object;

        if ((this.idUsers == null && other.idUsers != null) || (this.idUsers != null && !this.idUsers.equals (other.idUsers)))
        {
            return false;
        }

        return true;
    }

    @Override
    public String toString()
    {
        return "Database.Users[ idUsers=" + idUsers + " ]";
    }
}
