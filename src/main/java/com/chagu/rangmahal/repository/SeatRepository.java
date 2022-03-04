package com.chagu.rangmahal.repository;

import java.util.Optional;

import com.chagu.rangmahal.entity.SeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Pratap Bhanu
 *
 */
public interface SeatRepository extends JpaRepository<SeatEntity, Integer>, SeatRepositoryCustom {

	Optional<SeatEntity> findBySeatNameAndSeatType(String seatName, String seatType);

}
