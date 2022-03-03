package com.hashedin.rangmahal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hashedin.rangmahal.entity.UserEntity;

/**
 * @author Pratap Bhanu
 *
 */
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

	Optional<UserEntity> findByEmail(String email);

}
