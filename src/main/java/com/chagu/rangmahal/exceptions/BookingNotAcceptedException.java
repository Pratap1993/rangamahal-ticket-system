package com.chagu.rangmahal.exceptions;

/**
 * @author Pratap Bhanu
 *
 */
public class BookingNotAcceptedException extends RuntimeException {

	private static final long serialVersionUID = -6480398484863242825L;

	public BookingNotAcceptedException(String exceptionMessage) {
		super(exceptionMessage);
	}

}
