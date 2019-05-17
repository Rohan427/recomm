/*
 * Copyright 2018, Random Logic Consulting and Paul G. ALlen. All Rights Reserved.
 */
package model.service.exceptions;

/**
 * Service Properties exception
 * 
 * @author Paul G. Allen <pgallen@gmail.com>
 */
public class ServicePropertiesException extends Exception
{
	private static final long serialVersionUID = -6224184999886080135L;

    /**
     *
     * @param message
     */
	public ServicePropertiesException (final String message) 
	{
		super (message);		
	}

    /**
     *
     * @param message
     * @param nestedExcpt
     */
    public ServicePropertiesException (final String message, final Throwable nestedExcpt)
	{
		super (message, nestedExcpt);
	}
}