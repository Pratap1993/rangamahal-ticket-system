package com.chagu.rangmahal.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.chagu.rangmahal.entity.BookingEntity;
import com.chagu.rangmahal.entity.SeatEntity;

/**
 * @author Pratap Bhanu
 *
 */
public interface BookingRepositoryCustom {

	BookingEntity findIfAvailable(LocalDateTime bookingDate, String showTime, List<SeatEntity> seatsSelected);

}
