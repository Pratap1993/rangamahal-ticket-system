package com.hashedin.rangmahal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hashedin.rangmahal.entity.SeatEntity;

/**
 * @author Pratap Bhanu
 *
 */
public interface SeatRepository extends JpaRepository<SeatEntity, Integer>, SeatRepositoryCustom {

	Optional<SeatEntity> findBySeatNameAndSeatType(String seatName, String seatType);

}
