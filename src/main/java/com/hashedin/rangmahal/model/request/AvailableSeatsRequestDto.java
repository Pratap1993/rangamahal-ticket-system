package com.hashedin.rangmahal.model.request;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.hashedin.rangmahal.utils.DateTimeUtil;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Pratap Bhanu
 *
 */
@Getter
@Setter
@NoArgsConstructor
public class AvailableSeatsRequestDto implements Serializable {

	private static final long serialVersionUID = 1783677148243664588L;

	private LocalDateTime bookingDate;

	private String showTime;

	public AvailableSeatsRequestDto(String bookingDate, String showTime) {
		super();
		this.bookingDate = DateTimeUtil.getFromString(bookingDate);
		this.showTime = showTime.toUpperCase();
	}

}
