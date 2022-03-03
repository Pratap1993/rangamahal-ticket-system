package com.hashedin.rangmahal.model.request;

import java.io.Serializable;

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
public class SeatRequestDto implements Serializable {

	private static final long serialVersionUID = 8334241603397684964L;

	private Integer seatId;

	private String seatName;

	private String seatType;

	private Integer seatPrice;

}
