/*
 * Copyright 2018, Random Logic Consulting and Paul G. ALlen. All Rights Reserved.
 */
package model.service.exceptions;

/**
 * DAO write exception
 * 
 * @author Paul G. Allen <pgallen@gmail.com>
 */
public class DAOWriteException extends Exception
{
	private static final long serialVersionUID = -8361938986073550630L;

    /**
     *
     * @param message
     */
	public DAOWriteException (final String message) 
	{
		super (message);		
	}

    /**
     *
     * @param message
     * @param nestedExcpt
     */
    public DAOWriteException (final String message, final Throwable nestedExcpt)
	{
		super (message, nestedExcpt);
	}
}