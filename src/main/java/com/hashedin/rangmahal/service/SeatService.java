package com.hashedin.rangmahal.service;

import com.hashedin.rangmahal.model.request.AvailableSeatsRequestDto;
import com.hashedin.rangmahal.model.request.BookingRequestDto;
import com.hashedin.rangmahal.model.response.AvailableSeatsResponseDto;
import com.hashedin.rangmahal.model.response.BookingResponseDto;

/**
 * @author Pratap Bhanu
 *
 */
public interface SeatService {

	AvailableSeatsResponseDto findAvailableSeats(AvailableSeatsRequestDto dto);

	BookingResponseDto newBooking(BookingRequestDto bookingResponseDto);

}
