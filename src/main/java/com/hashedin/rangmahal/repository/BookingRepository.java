package com.hashedin.rangmahal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hashedin.rangmahal.entity.BookingEntity;

/**
 * @author Pratap Bhanu
 *
 */
public interface BookingRepository extends JpaRepository<BookingEntity, Integer>, BookingRepositoryCustom {

}
