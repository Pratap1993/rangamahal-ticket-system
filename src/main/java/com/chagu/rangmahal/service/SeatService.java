package com.chagu.rangmahal.service;

import com.chagu.rangmahal.model.request.AvailableSeatsRequestDto;
import com.chagu.rangmahal.model.request.BookingRequestDto;
import com.chagu.rangmahal.model.response.AvailableSeatsResponseDto;
import com.chagu.rangmahal.model.response.BookingResponseDto;

/**
 * @author Pratap Bhanu
 *
 */
public interface SeatService {

	AvailableSeatsResponseDto findAvailableSeats(AvailableSeatsRequestDto dto);

	BookingResponseDto newBooking(BookingRequestDto bookingResponseDto);

}
