package com.chagu.rangmahal.exceptions;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * @author Pratap Bhanu
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(value = { SeatNotAvailableException.class })
	public ResponseEntity<Object> handleSeatNotAvailableException(SeatNotAvailableException ex, WebRequest request) {
		CustomExceptionBean errorMessage = new CustomExceptionBean(new Date(), ex.getMessage());
		logger.error(ex.getMessage());
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = { BookingNotAcceptedException.class })
	public ResponseEntity<Object> handleBookingNotAcceptedException(BookingNotAcceptedException ex,
			WebRequest request) {
		CustomExceptionBean errorMessage = new CustomExceptionBean(new Date(), ex.getMessage());
		logger.error(ex.getMessage());
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = { UserServiceException.class })
	public ResponseEntity<Object> handleUserServiceException(UserServiceException ex, WebRequest request) {
		CustomExceptionBean errorMessage = new CustomExceptionBean(new Date(), ex.getMessage());
		logger.error(ex.getMessage());
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<Object> handleOtherException(Exception ex, WebRequest request) {
		CustomExceptionBean errorMessage = new CustomExceptionBean(new Date(), ex.getMessage());
		logger.error(ex.getMessage());
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
