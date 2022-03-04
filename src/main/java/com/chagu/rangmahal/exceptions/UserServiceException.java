package com.chagu.rangmahal.exceptions;

/**
 * @author Pratap Bhanu
 *
 */
public class UserServiceException extends RuntimeException {

	private static final long serialVersionUID = -3880069851908752573L;

	public UserServiceException(String exceptionMessage) {
		super(exceptionMessage);
	}

}
