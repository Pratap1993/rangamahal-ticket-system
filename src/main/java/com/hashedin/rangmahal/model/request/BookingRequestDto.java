package com.hashedin.rangmahal.model.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.hashedin.rangmahal.enums.ShowTimeEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Pratap Bhanu
 *
 */
@NoArgsConstructor
@Getter
@Setter
public class BookingRequestDto implements Serializable {

	private static final long serialVersionUID = 7390639083158006219L;

	private String bookingDateTime;

	private UserRequestDto user;

	private List<SeatRequestDto> seats = new ArrayList<SeatRequestDto>();

	private String showTime;

	public BookingRequestDto(String bookingDateTime, UserRequestDto user, List<SeatRequestDto> seats, String showTime) {
		super();
		this.bookingDateTime = bookingDateTime;
		this.user = user;
		this.seats = seats;
		if (showTime.equalsIgnoreCase(ShowTimeEnum.MORNING.name())) {
			this.showTime = ShowTimeEnum.MORNING.name();
		} else if (showTime.equalsIgnoreCase(ShowTimeEnum.MATINEE.name())) {
			this.showTime = ShowTimeEnum.MATINEE.name();
		} else {
			this.showTime = ShowTimeEnum.EVENING.name();
		}

	}

}
