package com.chagu.rangmahal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chagu.rangmahal.entity.BookingEntity;

/**
 * @author Pratap Bhanu
 *
 */
public interface BookingRepository extends JpaRepository<BookingEntity, Integer>, BookingRepositoryCustom {

}
