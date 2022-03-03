package com.hashedin.rangmahal.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.hashedin.rangmahal.entity.SeatEntity;

/**
 * @author Pratap Bhanu
 *
 */
public interface SeatRepositoryCustom {

	List<SeatEntity> findBookedSeatsForDateAndTime(LocalDateTime bookingDate, String showTime);
}
