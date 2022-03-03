package com.hashedin.rangmahal.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.hashedin.rangmahal.entity.BookingEntity;
import com.hashedin.rangmahal.entity.SeatEntity;

/**
 * @author Pratap Bhanu
 *
 */
public interface BookingRepositoryCustom {

	BookingEntity findIfAvailable(LocalDateTime bookingDate, String showTime, List<SeatEntity> seatsSelected);

}
