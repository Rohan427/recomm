package model.domain.interfaces;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author pgallen
 */
public abstract class Usr implements Serializable
{
    public Usr()
    {
    }

    @Override
    public abstract boolean equals (Object object);

    public abstract Integer getA ();

    public abstract void setA (Integer a);

    public abstract Integer getB ();

    public abstract void setB (Integer b);

    public abstract Integer getC ();

    public abstract void setC (Integer c);

    public abstract Date getCreation ();

    public abstract void setCreation (Date creation);

    public abstract Integer getD ();

    public abstract void setD (Integer d);

    public abstract String getDataSource ();

    public abstract void setDataSource (String dataSource);

    public abstract String getFirst ();

    public abstract void setFirst (String first);

    @Id
    public abstract Integer getIdUsers ();

    public abstract void setIdUsers (Integer idUsers);

    public abstract String getLast ();

    public abstract void setLast (String last);

    @Override
    public abstract int hashCode ();

    public abstract void inValidate ();

    public abstract boolean isPwReset ();

    public abstract void setPwReset (boolean pwReset);

    public abstract String getSecretKey ();

    public abstract void setSecretKey (String secretKey);

    public abstract String getUserName ();

    public abstract void setUserName (String userName);

    public abstract boolean isValid ();

    public abstract void setValid (boolean valid);

    @Override
    public abstract String toString ();
}
