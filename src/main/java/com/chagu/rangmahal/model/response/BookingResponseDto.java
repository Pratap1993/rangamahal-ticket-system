package com.chagu.rangmahal.model.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.chagu.rangmahal.model.request.SeatRequestDto;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class BookingResponseDto implements Serializable {

	private static final long serialVersionUID = 3867824548036503065L;

	private String bookingReferrence;

	private String bookingStatus;

	private List<SeatRequestDto> seats = new ArrayList<>();

}
