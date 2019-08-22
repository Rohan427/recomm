/*
 * Copyright 2018, Random Logic Consulting and Paul G. ALlen. All Rights Reserved.
 */
package com.recomm.model.service.exceptions;

/**
 * Service exception
 * 
 * @author Paul G. Allen <pgallen@gmail.com>
 */
public class ServiceException extends Exception
{
	private static final long serialVersionUID = 4490488210386693617L;

    /**
     *
     * @param message
     */
	public ServiceException (final String message) 
	{
		super (message);		
	}

    /**
     *
     * @param message
     * @param nestedExcpt
     */
    public ServiceException (final String message, final Throwable nestedExcpt)
	{
		super (message, nestedExcpt);
	}
}