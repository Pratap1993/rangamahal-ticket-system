package com.chagu.rangmahal.model.response;

import java.io.Serializable;
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
public class AvailableSeatsResponseDto implements Serializable {

	private static final long serialVersionUID = -1151585966215042022L;

	private List<SeatRequestDto> seatList;

}
