package com.recomm.business.managers.exceptions;

/**
 *
 * @author pgallen
 */
public class PropertiesNotFoundException extends Exception
{
	private static final long serialVersionUID = -2301877273582952922L;

    /**
     *
     * @param message
     */
    public PropertiesNotFoundException (final String message) 
	{
		super (message);		
	}

    /**
     *
     * @param message
     * @param nestedExcpt
     */
    public PropertiesNotFoundException (final String message, final Throwable nestedExcpt)
	{
		super (message, nestedExcpt);
	}
}
