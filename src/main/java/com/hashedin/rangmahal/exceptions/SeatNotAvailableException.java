package com.hashedin.rangmahal.exceptions;

/**
 * @author Pratap Bhanu
 *
 */
public class SeatNotAvailableException extends RuntimeException {

	private static final long serialVersionUID = 7144209798848346196L;

	public SeatNotAvailableException(String exceptionMessage) {
		super(exceptionMessage);
	}

}
