/*
 * Copyright 2018, Random Logic Consulting and Paul G. ALlen. All Rights Reserved.
 */
package model.service.exceptions;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * DAO read exception
 * 
 * @author Paul G. Allen <pgallen@gmail.com>
 */
public class DAOReadException extends Exception
{
	private static final long serialVersionUID = -808848991347956007L;
    private final static Logger log = LogManager.getLogger (DAOReadException.class.getName());

    /**
     *
     * @param message
     */
	public DAOReadException (final String message) 
	{
		super (message);
		log.error (message);
	}

    /**
     *
     * @param message
     * @param nestedExcpt
     */
    public DAOReadException (final String message, final Throwable nestedExcpt)
	{
		super (message, nestedExcpt);
		log.error (message);
		log.error (nestedExcpt.getMessage());
	}
}