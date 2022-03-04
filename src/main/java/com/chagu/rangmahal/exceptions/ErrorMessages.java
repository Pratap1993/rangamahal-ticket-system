package com.chagu.rangmahal.exceptions;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * @author Pratap Bhanu
 *
 */
@RequiredArgsConstructor
public enum ErrorMessages {
	
	MISSING_REQUIRED_FIELD("Missing Required Field !!!"),
	RECORD_ALREADY_EXISTS("Record Already Exists !!!"),
	INTERNAL_SERVER_ERROR("Internal Server Error !!!"),
	NO_RECORD_FOUND("Searched Record Not Found !!!"),
	COULD_NOT_UPDATE_RECORD("Could Not Update The Record !!!"),
	SEAT_NOT_AVAILABLE("Searched Seat Not Available !!!"),
	BOOKING_NOT_OPENED("Booking Not Accepted At This Time!!!"),
	EMAIL_ADDRESS_NOT_VERIFIED("Email Address couldn't be verified !!!");

	@NonNull
	private String errorMessage;

	public String getErrorMessage() {
		return errorMessage;
	}

}
