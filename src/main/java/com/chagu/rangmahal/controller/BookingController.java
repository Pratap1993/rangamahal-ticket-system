package com.chagu.rangmahal.controller;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chagu.rangmahal.model.request.AvailableSeatsRequestDto;
import com.chagu.rangmahal.model.request.BookingRequestDto;
import com.chagu.rangmahal.model.response.AvailableSeatsResponseDto;
import com.chagu.rangmahal.model.response.BookingResponseDto;
import com.chagu.rangmahal.service.SeatService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * @author Pratap Bhanu
 *
 */
@RestController
@RequestMapping(value = "booking")
@RequiredArgsConstructor
public class BookingController {

	@NonNull
	private SeatService seatService;

	/**
	 * Returns a {@code AvailableSeatsResponseDto} that can be used to choose seats
	 * for booking.
	 *
	 * @param bookingDate: The date for which booking is being initialized. It
	 *                     should be in 'yyyy-MM-dd HH:mm' format.
	 * @param showTime:    The time for which booking is being initialized.
	 */
	@GetMapping(path = "/{bookingDate}/{showTime}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public AvailableSeatsResponseDto getAvailableSeats(@PathVariable String bookingDate,
			@PathVariable String showTime) {
		AvailableSeatsResponseDto dto = seatService
				.findAvailableSeats(new AvailableSeatsRequestDto(bookingDate, showTime));
		return dto;
	}

	/**
	 * Returns a {@code BookingResponseDto} containing Booking information for
	 * booking.
	 *
	 * @param bookingRequestDto: The initial booking request object
	 */
	@PostMapping(path = "/newBooking", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public BookingResponseDto newBooking(@Valid @RequestBody BookingRequestDto bookingRequestDto) {
		BookingResponseDto dto = seatService.newBooking(bookingRequestDto);
		return dto;
	}

}
